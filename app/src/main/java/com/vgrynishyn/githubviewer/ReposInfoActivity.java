package com.vgrynishyn.githubviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class ReposInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_info);
    }

  //  Bundle extras = getIntent().getExtras();
    //if (extras1 != null) {
    //String str= extras.getString("reposInfo");
    //}


        Intent i = getIntent();
        String info  = i.getStringExtra("reposInfo");
    String s = "";
    int in = 0;
}
