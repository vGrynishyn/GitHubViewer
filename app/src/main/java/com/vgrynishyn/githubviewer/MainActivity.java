package com.vgrynishyn.githubviewer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button button;

    String token = "aee0b3226f82557b1d9a1c3d0dc5291f7020db43";

    //String accoungInfo = "{\"id\":3070104,\"name\":\"abs.io\",\"full_name\":\"JakeWharton/abs.io\",\"owner\":{\"login\":\"JakeWharton\",\"id\":66577,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/66577?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/JakeWharton\",\"html_url\":\"https://github.com/JakeWharton\",\"followers_url\":\"https://api.github.com/users/JakeWharton/followers\",\"following_url\":\"https://api.github.com/users/JakeWharton/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/JakeWharton/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/JakeWharton/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/JakeWharton/subscriptions\",\"organizations_url\":\"https://api.github.com/users/JakeWharton/orgs\",\"repos_url\":\"https://api.github.com/users/JakeWharton/repos\",\"events_url\":\"https://api.github.com/users/JakeWharton/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/JakeWharton/received_events\",\"type\":\"User\",\"site_admin\":false},\"private\":false,\"html_url\":\"https://github.com/JakeWharton/abs.io\",\"description\":\"Simple URL shortener for ActionBarSherlock using node.js and express.\",\"fork\":false,\"url\":\"https://api.github.com/repos/JakeWharton/abs.io\",\"forks_url\":\"https://api.github.com/repos/JakeWharton/abs.io/forks\",\"keys_url\":\"https://api.github.com/repos/JakeWharton/abs.io/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/JakeWharton/abs.io/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/JakeWharton/abs.io/teams\",\"hooks_url\":\"https://api.github.com/repos/JakeWharton/abs.io/hooks\",\"issue_events_url\":\"https://api.github.com/repos/JakeWharton/abs.io/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/JakeWharton/abs.io/events\",\"assignees_url\":\"https://api.github.com/repos/JakeWharton/abs.io/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/JakeWharton/abs.io/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/JakeWharton/abs.io/tags\",\"blobs_url\":\"https://api.github.com/repos/JakeWharton/abs.io/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/JakeWharton/abs.io/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/JakeWharton/abs.io/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/JakeWharton/abs.io/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/JakeWharton/abs.io/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/JakeWharton/abs.io/languages\",\"stargazers_url\":\"https://api.github.com/repos/JakeWharton/abs.io/stargazers\",\"contributors_url\":\"https://api.github.com/repos/JakeWharton/abs.io/contributors\",\"subscribers_url\":\"https://api.github.com/repos/JakeWharton/abs.io/subscribers\",\"subscription_url\":\"https://api.github.com/repos/JakeWharton/abs.io/subscription\",\"commits_url\":\"https://api.github.com/repos/JakeWharton/abs.io/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/JakeWharton/abs.io/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/JakeWharton/abs.io/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/JakeWharton/abs.io/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/JakeWharton/abs.io/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/JakeWharton/abs.io/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/JakeWharton/abs.io/merges\",\"archive_url\":\"https://api.github.com/repos/JakeWharton/abs.io/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/JakeWharton/abs.io/downloads\",\"issues_url\":\"https://api.github.com/repos/JakeWharton/abs.io/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/JakeWharton/abs.io/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/JakeWharton/abs.io/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/JakeWharton/abs.io/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/JakeWharton/abs.io/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/JakeWharton/abs.io/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/JakeWharton/abs.io/deployments\",\"created_at\":\"2011-12-29T18:02:34Z\",\"updated_at\":\"2017-08-09T14:39:21Z\",\"pushed_at\":\"2011-12-29T18:02:44Z\",\"git_url\":\"git://github.com/JakeWharton/abs.io.git\",\"ssh_url\":\"git@github.com:JakeWharton/abs.io.git\",\"clone_url\":\"https://github.com/JakeWharton/abs.io.git\",\"svn_url\":\"https://github.com/JakeWharton/abs.io\",\"homepage\":\"http://abs.io\",\"size\":108,\"stargazers_count\":6,\"watchers_count\":6,\"language\":\"JavaScript\",\"has_issues\":true,\"has_projects\":true,\"has_downloads\":true,\"has_wiki\":false,\"has_pages\":false,\"forks_count\":1,\"mirror_url\":null,\"archived\":false,\"open_issues_count\":0,\"forks\":1,\"open_issues\":0,\"watchers\":6,\"default_branch\":\"master\"}";

    private HttpsURLConnection urlConnection;
    private CookieManager cookieManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView);
    }


    public void clickButton(View view) throws IOException {

      //  formatJSONObjectData(loadJSONFromAsset());

        new getGitHubRepositoryContent().execute();
        //new RetrieveListOfStrings().execute();
        //formatJSONObjectData(accoungInfo);
        }

    private class getGitHubRepositoryContent extends AsyncTask<Object, Object, Void> {
        @Override
        protected Void doInBackground(Object... params) {
                BufferedReader reader = null;
                String name = "", description = "", lang = "";
                try {
                    URL url = new URL("https://api.github.com/users/vGrynishyn/repos");
                    reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    StringBuffer buffer = new StringBuffer();
                    int read;
                    char[] chars = new char[1024];
                    while ((read = reader.read(chars)) != -1)
                        buffer.append(chars, 0, read);

                    JSONArray jsonArr = new JSONArray(buffer.toString());

                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject jsonObj = jsonArr.getJSONObject(i);
                         name = jsonObj.getString("name");// get("name");
                         description = jsonObj.getString("description");
                         lang = jsonObj.getString("language");
                         String info = name+ "\n" + description+ "\n"  + lang;
                    }
                    System.out.println(name);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null)
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
//            String line = null;
//            try {
//                URL url = new URL("https://api.github.com/users/vGrynishyn/repos");
//
//                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
//                int responseCode = urlConnection.getResponseCode();
//
//                InputStream in ;
//                if (urlConnection.getResponseCode() < HttpsURLConnection.HTTP_BAD_REQUEST) {
//                    in = urlConnection.getInputStream();
//                } else {
//                    in = urlConnection.getErrorStream();
//                }
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
//                while (br.read() != -1) {
//                    line = br.readLine();
//                }
//
//                System.out.println("Response code: " + responseCode);
//                System.out.println(line);
//
//                JsonFactory fac = new JsonFactory();
//                JsonParser jp = null;
//                jp = fac.createParser(line);
//                StringBuilder strPar = new StringBuilder(" ");
//                jp.nextValue();
//                while(jp.nextValue() != JsonToken.END_ARRAY){
//                    while (jp.nextToken() != JsonToken.END_OBJECT) {
//                        String fieldName = jp.getCurrentName();
//                        jp.nextToken();
//
//                        if (fieldName.equals("id")){
//                            strPar.append(jp.getText()+"; ");
//                        }else if (fieldName.equals("name")){
//                            strPar.append(jp.getText()+"; ");
//                        }else if (fieldName.equals("full_name")){
//                            strPar.append(jp.getText()+"; ");
//                        }
//                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace(); //do something
//            }
            return null;
        }


        private String readUrl(String urlString) throws Exception {
            BufferedReader reader = null;
            try {
                URL url = new URL(urlString);
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuffer buffer = new StringBuffer();
                int read;
                char[] chars = new char[1024];
                while ((read = reader.read(chars)) != -1)
                    buffer.append(chars, 0, read);

                return buffer.toString();
            } finally {
                if (reader != null)
                    reader.close();
            }
        }
        public void main(String[] args) throws Exception {
            String url = "https://api.github.com/users/vGrynishyn/repos";
            String data = readUrl(url);
            JSONArray jsonArr = new JSONArray(data);

            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObj = jsonArr.getJSONObject(i);

                System.out.println(jsonObj.get("name"));
            }

        }

    }



//    public String loadJSONFromAsset() {
//        String json = null;
//        try {
//            InputStream is = getAssets().open("JakeWharton.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }

}


