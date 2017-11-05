package com.vgrynishyn.githubviewer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class ReposInfoActivity extends Activity {

    UserRepository UR;
    TextView tvInfo;
    TextView tvTitle;
    TextView tvDescription;
    TextView tvStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_info);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvStars = (TextView) findViewById(R.id.tvStars);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            UR = (UserRepository) extras.getSerializable("reposInfo");
        }

        tvTitle.setText(UR.getName());
        tvDescription.setText(UR.getDescription());
        tvStars.setText(UR.getStars());
        String str = String.format(" Forks: %s \n Watchers: %s \n Issues: %s", UR.getForks(), UR.getWatchers(), UR.getWatchers());
        tvInfo.setText(str);
    }
}
