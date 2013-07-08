package com.example.treasurehunt;

import java.io.IOException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.hardware.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity implements LocationListener{
	
	public static GoogleMap map;
	public static TextView tex1;
	public static TextView tex2;
	public static LatLng hint;
	public static boolean back;
	
	private LocationManager locationManager;
	private boolean isStarted;
	private ProgressDialog progress;
	private Preview prev;
	private double thetaV;
	private double thetaH;
	
	class Preview extends SurfaceView implements SurfaceHolder.Callback {
		private SurfaceHolder mHolder;
		private Camera mCamera;
	
		
		@SuppressWarnings("deprecation")
		Preview(Context context) {
			super(context);

			mHolder = getHolder();
			mHolder.setFixedSize(1, 1);
			mHolder.addCallback(this);
			mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			thetaV = -1;
			thetaH = -1;
		}

		public void surfaceCreated(SurfaceHolder holder) {
			Log.d("SEARCH ", "surfaceCreated");
			mCamera = Camera.open();
			try {
				mCamera.setPreviewDisplay(holder);
			} catch (IOException exception) {
				mCamera.release();
				mCamera = null;
			}
		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			Log.d("SEARCH ", "surfaceDestroyed");
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}

		public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

			ViewGroup.LayoutParams params = prev.getLayoutParams();
			params.width = 0;
			params.height = 0;
			prev.setLayoutParams(params);

			Camera.Parameters p = mCamera.getParameters();
			int zoom = p.getZoomRatios().get(p.getZoom()).intValue();
			Camera.Size sz = p.getPreviewSize();
			double aspect = (double) sz.width / (double) sz.height;
			thetaV = Math.toRadians(p.getVerticalViewAngle());
			thetaH = 2d * Math.atan(aspect * Math.tan(thetaV / 2));
			thetaV = 2d * Math.atan(100d * Math.tan(thetaV / 2d) / zoom);
			thetaH = 2d * Math.atan(100d * Math.tan(thetaH / 2d) / zoom);

			Log.d("Theta H: ", " = " + Math.toDegrees(thetaH));
			Log.d("Theta V: ", " = " + Math.toDegrees(thetaV));
			mCamera.setParameters(p);
			mCamera.startPreview();
			
			Utils.cameraViewAngle = Math.toDegrees(thetaV);
			Utils.deltaDistance = 50;
			
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			Utils.width = metrics.widthPixels;
			
			hint = new LatLng(44.4361, 26.0484);

			
			Perimeter perimeter = new Perimeter();
			perimeter.setHint(hint);
			perimeter.setRadius(100000, Utils.deltaDistance);
			perimeter.addCircle();
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	
		progress = new ProgressDialog(this);
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		//sprogress.setMessage("Pleas")
		progress.show();
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		
		isStarted = false;
		back = false;
		
		prev = new Preview(this);
		RelativeLayout parent = (RelativeLayout) findViewById(R.id.search_layout);
		parent.addView(prev);
		
		final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			String data = "GPS is closed!";
			Toast.makeText(getApplicationContext(), (String) data, Toast.LENGTH_LONG).show();
			this.buildAlertMessageNoGps();
		}
		
		tex1 = (TextView) findViewById(R.id.latitude_textview);
		tex2 = (TextView) findViewById(R.id.longitude_textview);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.searchMap)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		map.setMyLocationEnabled(true);
	}
	
	
	@Override
	protected void onPause(){
	    super.onPause();
	    if (prev.mCamera != null) {
	    	prev.mCamera.stopPreview();
	    	prev.mCamera.release();
	    	prev.mCamera = null;
	    }
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (back)
			finish();
		if (prev.mCamera != null) {
			prev.mCamera.stopPreview();
			prev.mCamera.release();
			prev.mCamera = null;
			finish();
		}
	
	}
	
	
	@Override
	public void onLocationChanged(Location location) {

		Utils.userLocation = location;
		if (tex1 != null && tex2 != null) {
			String text1 = "Latitude: " + location.getLatitude();
			String text2 = "Longitude: " + location.getLongitude();
			tex1.setText(text1);
			tex2.setText(text2);
			progress.dismiss();
		}
		if (Utils.userLocation != null){
			if (Utils.isNear(hint) && !isStarted){
				isStarted = true;
				Intent intent = new Intent(this, CameraActivity.class);
				startActivity(intent);
			}
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.d("GPS", "disable");
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.d("GPS", "enable");
	}
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.d("GPS", "status");
	}
	
	private void buildAlertMessageNoGps() {

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Your GPS is disabled, do you want to enable it?")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, final int id) {
						startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, final int id) {
						dialog.cancel();
					}
				});

		final AlertDialog alert = builder.create();
		alert.show();
	}
}

