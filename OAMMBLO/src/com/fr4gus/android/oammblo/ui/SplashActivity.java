package com.fr4gus.android.oammblo.ui;

import com.fr4gus.android.oammblo.R;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

/**
 * Shows App logo for few seconds.
 * 
 * @author Franklin Garcia
 * Created Mar 25, 2012
 */
public class SplashActivity extends OammbloActivity {

	Handler miH;
 	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
         miH= new Handler();
        
    }
 	
	@Override
   	protected void onResume() {
   		super.onResume();
   		miH.postDelayed(new Runnable(){
        	public void run(){
				Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        		startActivity(intent);
        		finish();
        	};
        }, 3000);
   	}
}
