package com.blackmoon.Utils;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.R.array;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;

public class ConfigData {
	public static JSONArray allProducts = new JSONArray();
	public static String storeName ="nhong_demo";
	public static JSONObject productClicked;
	public static ActionBar actionBar;
	public static Context activityMain;
	
	public static HashMap<Long, Object> hashMapId = new HashMap<Long, Object>(); 
	
	
	

}
