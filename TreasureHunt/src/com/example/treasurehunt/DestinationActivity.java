package com.example.treasurehunt;

import com.example.treasurehunt.targetendpoint.model.Target;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DestinationActivity extends Activity {

	private Button save;
	private Button cancel;
	private double gpsx;
	private double gpsy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destination);

		Intent intent = getIntent();
		gpsx = intent.getDoubleExtra("x", 0);
		gpsy = intent.getDoubleExtra("y", 0);
		TextView x = (TextView) findViewById(R.id.xValue);
		TextView y = (TextView) findViewById(R.id.yValue);
		x.setText(String.valueOf(gpsx));
		y.setText(String.valueOf(gpsy));

		save = (Button) findViewById(R.id.save);
		save.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				Target currentHint = new Target();
				
				
				EditText et = (EditText) findViewById(R.id.etname);
				currentHint.setName(et.getText().toString());
		
				currentHint.setLatitude(gpsx);
				currentHint.setLongitude(gpsy);
				
				et = (EditText) findViewById(R.id.etdescription);
				currentHint.setDescription(et.getText().toString());
				
				et = (EditText) findViewById(R.id.etquestion);
				currentHint.setQuestion(et.getText().toString());
				
				et = (EditText) findViewById(R.id.etanswer);
				currentHint.setAnswer(et.getText().toString());
				
				MyMapActivity.hints.add(currentHint);
				MyMapActivity.noHints++;
				finish();
			}

		});

		cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.create_destination, menu);
		return true;
	}

}
