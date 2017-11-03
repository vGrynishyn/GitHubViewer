package com.vgrynishyn.githubviewer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

    Button button;
    private PersonAdapter mAdapter;
    private RecyclerView mRecyclerView;
    //private static UserRepository sUserRepository;
    ArrayList<UserRepository> mReposInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }


    public void clickButton(View view) throws IOException {
        new getGitHubRepositoryContent().execute();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PersonAdapter(mReposInfo);
        mRecyclerView.setAdapter(mAdapter);
        }

    private class getGitHubRepositoryContent extends AsyncTask<Object, Object, Void> {
        @Override
        protected Void doInBackground(Object... params) {
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://api.github.com/users/vGrynishyn/repos");
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
                                 jsonObj.getInt("stargazers_count"),
                                 jsonObj.getInt("forks"),
                                 jsonObj.getString("updated_at")));
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
            return null;
        }
    }

    private class ReposHolder extends RecyclerView.ViewHolder{

        private TextView mPersonNameTextView;
        private TextView mPersonAdressTextView;
        private TextView mPersonAgeTextView;
        private UserRepository mUserRepos;


        public ReposHolder(View itemView) {
            super(itemView);
            mPersonNameTextView = (TextView) itemView.findViewById(R.id.personNameView);
            mPersonAdressTextView = (TextView) itemView.findViewById(R.id.personAdressView);
            mPersonAgeTextView = (TextView) itemView.findViewById(R.id.personAgeView);
        }
        //Метод, связывающий ранее добытые в конструкторе ссылки с данными модели
        public void bindCrime(UserRepository repos) {
            mUserRepos = repos;
            mPersonNameTextView.setText("jdsflksjaf");
            mPersonAdressTextView.setText("sdkfjlsdkfj");
            mPersonAgeTextView.setText("sdkjflks");

//            mPersonNameTextView.setText(mUserRepos.getName());
//            mPersonAdressTextView.setText(mUserRepos.getLanguage());
//            mPersonAgeTextView.setText(mUserRepos.getDescription());
        }

    }
    private class PersonAdapter extends RecyclerView.Adapter<ReposHolder> {

        private List<UserRepository> mPersons;
        public PersonAdapter(List<UserRepository> persons) {
            mPersons = persons;
        }

        //Создаёт пустую вьюшку,оборачивает её в ReposHolder.
        //Дальше забота по наполнению этой вьюшки ложиться именно на объект ReposHolder'а
        @Override
        public ReposHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = getLayoutInflater();
            View view = li.inflate(R.layout.list_item_user, parent, false);
            return new ReposHolder(view);
        }

        //Дёргает метод холдера при выводе нового элемента списка на экран,
        //передавая ему актуальный объект модели для разбора и представления
        @Override
        public void onBindViewHolder(ReposHolder holder, int position) {
            UserRepository person = mPersons.get(position);
            holder.bindCrime(person);

        }

        //Возвращает размер хранилища моделей
        @Override
        public int getItemCount() {
            return mPersons.size();
        }
    }
}


