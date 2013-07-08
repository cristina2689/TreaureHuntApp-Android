package com.example.treasurehunt;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class Perimeter {
	private double R;
	private double r;
	private LatLng hintPos;
	
	public void setRadius(double R, double r){
		this.R = R;
		this.r = r;
	}
	
	public void setHint(LatLng hintPos){
		this.hintPos = hintPos;
	}
	
	public void addCircle()
    {
		if (SearchActivity.map != null) {
			CircleOptions circleOptions = new CircleOptions()
				.center(hintPos)
				.radius(R)
				.fillColor(Color.GRAY)
				.strokeColor(Color.GRAY);// In meters
			SearchActivity.map.addCircle(circleOptions);
			SearchActivity.map.setMyLocationEnabled(true);
			SearchActivity.map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	
		}
    }
}


