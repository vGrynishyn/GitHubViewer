package com.vgrynishyn.githubviewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private PersonAdapter mAdapter;
    private RecyclerView mRecyclerView;
    ArrayList<UserRepository> mReposInfo = new ArrayList<>();
    Context context = this;
    UserRepository ur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (isOnline(context)) {
            new getGitHubRepositoryContent().execute();
        } else {
            Toast.makeText(context, "Connection internet is failed.", Toast.LENGTH_SHORT).show();
        }

}
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    };

    private class getGitHubRepositoryContent extends AsyncTask<Object, Object, ArrayList<UserRepository>> implements com.vgrynishyn.githubviewer.getGitHubRepositoryContent {
        @Override
        protected ArrayList<UserRepository> doInBackground(Object... params) {
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://api.github.com/users/JakeWharton/repos");
                    reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    StringBuffer buffer = new StringBuffer();
                    int read;
                    char[] chars = new char[1024];
                    while ((read = reader.read(chars)) != -1)
                        buffer.append(chars, 0, read);
                    JSONArray jsonArr = new JSONArray(buffer.toString());
                    int length = jsonArr.length();
                    mReposInfo = new ArrayList<>(length);
                    for (int i = 0; i < length; i++) {
                        JSONObject jsonObj = jsonArr.getJSONObject(i);
                        mReposInfo.add(new UserRepository(
                                 jsonObj.getString("name"),
                                 jsonObj.getString("description"),
                                 jsonObj.getString("language"),
                                 jsonObj.getString("stargazers_count"),
                                 jsonObj.getString("forks"),
                                 jsonObj.getString("updated_at"),
                                 jsonObj.getString("open_issues"),
                                 jsonObj.getString("watchers")));
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null)
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            return mReposInfo;
        }

        @Override
        public void onPostExecute(ArrayList<UserRepository> ar) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            mAdapter = new PersonAdapter(ar);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private class ReposHolder extends RecyclerView.ViewHolder{

        private TextView mReposNameTV;
        private TextView mReposDescriptionTV;
        private TextView mReposLangTV;
        private TextView mReposStarsTV;
        private TextView mReposForksTV;
        private TextView mReposUpdateDateTV;
        protected LinearLayout LinLayout;

        public ReposHolder(View itemView) {
            super(itemView);
            mReposNameTV = (TextView) itemView.findViewById(R.id.reposNameView);
            mReposDescriptionTV = (TextView) itemView.findViewById(R.id.reposDescriptionView);
            mReposLangTV = (TextView) itemView.findViewById(R.id.reposLangView);
            mReposStarsTV = (TextView) itemView.findViewById(R.id.reposStarsView);
            mReposForksTV = (TextView) itemView.findViewById(R.id.reposForksView);
            mReposUpdateDateTV = (TextView) itemView.findViewById(R.id.reposUpdateDateView);
            LinLayout = (LinearLayout) itemView.findViewById(R.id.LinLayout);
        }

        public void bindCrime(UserRepository repos) {
            mReposNameTV.setText(repos.getName());
            mReposDescriptionTV.setText(repos.getDescription());
            mReposLangTV.setText(repos.getLanguage());
            mReposStarsTV.setText(repos.getStars());
            mReposForksTV.setText(repos.getForks());
            mReposUpdateDateTV.setText(repos.getUpdateDate());
        }
    }
    private class PersonAdapter extends RecyclerView.Adapter<ReposHolder> {

        private List<UserRepository> mRepos;
        public PersonAdapter(List<UserRepository> persons) {
            mRepos = persons;
        }

        @Override
        public ReposHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View view = li.inflate(R.layout.list_item_user, viewGroup, false);

            ReposHolder holder = new ReposHolder(view);
            holder.LinLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int itemPosition = mRecyclerView.indexOfChild(v);
                    Intent intent = new Intent(getApplicationContext(), ReposInfoActivity.class);
                    UserRepository ur = mReposInfo.get(itemPosition);
                    intent.putExtra("reposInfo", ur);
                    startActivity(intent);
                }
            });
            return new ReposHolder(view);
        }

        @Override
        public void onBindViewHolder(ReposHolder holder, int position) {
            UserRepository person = mRepos.get(position);
            holder.bindCrime(person);
       }

        @Override
        public int getItemCount() {
            return mRepos.size();
        }
    }
}


