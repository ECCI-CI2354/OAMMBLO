package com.fr4gus.android.oammblo.ui;

import com.fr4gus.android.oammblo.R;
import com.fr4gus.android.oammblo.R.layout;
import com.fr4gus.android.oammblo.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TimeLineActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_timeline, menu);
        return true;
    }
}
