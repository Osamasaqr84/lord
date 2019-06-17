package com.codesroots.hossam.lordApp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class PreferenceHelper {

	private static SharedPreferences app_prefs;
	private static String app_ref = "userdetails";
	private static String photo = "photo";
	private static String Token = "token";
	private static String UserId = "userid";
	private static String Language = "language";
	private final String CURRENTLAT = "latitude";
	private final String CURRENTLONG = "longtude";
	private final String CURRENTCATEGRY = "CURRENTCATEGRY";
	private static final String USER_GROUP_ID = "USERGROUPID";

	private Context context;


	public PreferenceHelper(Context context) {
		this.context = context;
		try {
			app_prefs = context.getSharedPreferences(app_ref,
					Context.MODE_PRIVATE);
		}
		catch (NullPointerException e)
		{
		}
	}


	public void setCURRENTLAT(String currentlat) {
		Editor edit = app_prefs.edit();
		edit.putString(CURRENTLAT, currentlat);
		edit.apply();
	}

	public void setCURRENTLONG(String currentlong) {
		Editor edit = app_prefs.edit();
		edit.putString(CURRENTLONG, currentlong);
		edit.apply();
	}

	public static  void setUSER_GROUP_ID(int group) {
		Editor edit = app_prefs.edit();
		edit.putInt(USER_GROUP_ID, group);
		edit.apply();
	}

	public void setCURRENTCATEGRY(String currentcategry) {

		Editor edit = app_prefs.edit();
		edit.putString(CURRENTCATEGRY, currentcategry);
		edit.apply();
	}

	public String getCURRENTCATEGRY() {
		return app_prefs.getString(CURRENTCATEGRY, null);
	}

	public String getCURRENTLAT() {
		return app_prefs.getString(CURRENTLAT, null);
	}

	public String getCURRENTLONG()  {
		return app_prefs.getString(CURRENTLONG, "0");
	}

	public static int getUSER_GROUP_ID()  {
		return app_prefs.getInt(USER_GROUP_ID, 0);
	}


	public String getToken() {
		return app_prefs.getString(Token,null);
	}

	public void setToken(String API_TOKEN) {
		Editor edit = app_prefs.edit();
		edit.putString(Token, API_TOKEN);
		edit.commit();
	}

	public static int getUserId() {
		return app_prefs.getInt(UserId,0);
	}

	public void setLanguage(String language) {
		Editor edit = app_prefs.edit();
		edit.putString(Language, language);
		edit.commit();
	}

	public String getLanguage() {
		return app_prefs.getString(Language,null);
	}

	public String getphoto() {
		return app_prefs.getString(photo,null);
	}

	public static void setUserId(int user_id) {
		Editor edit = app_prefs.edit();
		edit.putInt(UserId, user_id);
		edit.apply();
	}

	public void setphoto(String uri){
	Editor edit = app_prefs.edit();
	edit.putString(photo, uri);
	edit.apply();
}


    public void Logout(){
		setToken(null);
		setUserId(0);
		setphoto(null);

	}

	public void clearRequestData() {

 	}

}
