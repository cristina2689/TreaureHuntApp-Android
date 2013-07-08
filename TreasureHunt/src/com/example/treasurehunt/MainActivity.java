package com.example.treasurehunt;


import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void createGameAction(View button){
		MyMapActivity.noHints = 0;
//		MyMapActivity.hints = new ArrayList<Target>();
		Intent intent = new Intent(this, MyMapActivity.class);
		startActivity(intent);
	}
	
	public void joinGameAction(View button){
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}
	
	public void helpAction(View button){
		Toast.makeText(getApplicationContext(),
				"You have two posibilities: create new game and place hints on the map in order to challenge friends to find them or you can join an existing game nearby you.",
				Toast.LENGTH_LONG).show();
	}
	public void contactAction(View button){
		
	}

	public void exitAction(View button){
		finish();
	}

}
