package com.fr4gus.android.oammblo.ui;

import com.fr4gus.android.oammblo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;



public class LoginActivity extends OammbloActivity implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        View boton = findViewById(R.id.button1);
        boton.setOnClickListener(this);
    }
    
    public void onClick(View vista){
    	if(vista.getId()==findViewById(R.id.button1).getId()){
    		Intent i= new Intent(this,TimeLineActivity.class);
    		startActivity(i);
    	}
    }
}
