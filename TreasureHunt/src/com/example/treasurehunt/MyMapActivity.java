package com.example.treasurehunt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.treasurehunt.targetendpoint.Targetendpoint;
import com.example.treasurehunt.targetendpoint.model.Target;
import com.example.treasurehunt.gameendpoint.Gameendpoint;
import com.example.treasurehunt.gameendpoint.model.Game;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.AsyncTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;

public class MyMapActivity extends Activity implements OnMapClickListener {
	public static GoogleMap map;
	public static int noHints = 0;
	public static List <Target> hints;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		hints = new ArrayList<Target>();

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		if (map != null) {
			LatLng zoomPosition = new LatLng(45, 26);
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(zoomPosition, 5));
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(zoomPosition,
					10));
			map.setMyLocationEnabled(true);
			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		}
	}

	public class EndpointsTask extends AsyncTask<Context, Integer, Long> {
		protected Long doInBackground(Context... contexts) {

			
			// Target endpoint
			Targetendpoint.Builder endpointBuilder = new Targetendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					new HttpRequestInitializer() {
						public void initialize(HttpRequest httpRequest) {
						}
					});
			Targetendpoint endpoint = CloudEndpointUtils.updateBuilder(
					endpointBuilder).build();
			
			// Game endpoint
			Gameendpoint.Builder endpointBuilder2 = new Gameendpoint.Builder(
					AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
					new HttpRequestInitializer() {
						public void initialize(HttpRequest httpRequest) {
						}
					});
			Gameendpoint endpoint2 = CloudEndpointUtils.updateBuilder(
					endpointBuilder2).build();
			
			try {
				for (Target target : hints){
					endpoint.insertTarget(target).execute();
					Log.d("SERVER", "target adaugat");
				}
				Game game = new Game();
				Date date = new Date();
				game.setId("Joc" + date.toString());
				game.setDate(date.toString());
				game.setName("Joc");
				game.setArea("Area");
	
				endpoint2.insertGame(game).execute();
				Log.d("SERVER", "joc creat");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return (long) 0;
		}
	}

	public void createAction(View button) {

		if (noHints == 0) {

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
			alertDialogBuilder.setTitle("Hints Number");
			alertDialogBuilder
					.setMessage("Please add at least one hint mark!")
					.setCancelable(false)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
								}
							});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		} else {
			
			new EndpointsTask().execute(getApplicationContext());
			Toast.makeText(getApplicationContext(),
					"Your game has been successfully created!",
					Toast.LENGTH_SHORT).show();
			finish();
		}
	}

	public void addAction(View button) {
		map.setOnMapClickListener(this);
		Toast.makeText(getApplicationContext(),
				"Please select a place on the map to hide your treasure!",
				Toast.LENGTH_LONG).show();
	}

	public void helpAction(View button) {
		Toast.makeText(getApplicationContext(),
				"Please select a place on the map where to hide your hint!",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onMapClick(LatLng arg0) {
		Intent intent = new Intent(getBaseContext(), DestinationActivity.class);
		intent.putExtra("x", arg0.latitude);
		intent.putExtra("y", arg0.longitude);
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		for (Target point : hints)
			map.addMarker(new MarkerOptions().position(
					new LatLng(point.getLatitude(), point.getLongitude()))
					.title("Position"));
		super.onResume();
	}

}
