package it.polito.tdp.model;

import com.javadocmd.simplelatlng.LatLng;

public class Distretto {
	
	private int id;
	private LatLng latlng;
	
	public Distretto(int id, double geo_lon, double geo_lat) {
		super();
		this.id = id;
		latlng = new LatLng(geo_lat, geo_lon);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getGeo_lon() {
		return latlng.getLongitude();
	
	}
	
	public double getGeo_lat() {
		return latlng.getLatitude();
	}
	public void setCoordinate(double geo_lon, double geo_lat) {
		latlng.setLatitudeLongitude(geo_lat, geo_lon);;
	}
	public LatLng getLatlng() {
		return latlng;
	}
	public void setLatlng(LatLng latlng) {
		this.latlng = latlng;
	}
	
	

	
}
