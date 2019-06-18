package com.codesroots.hossam.lordApp.presentation.screens.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesroots.hossam.lordApp.helper.GpsTracker;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.MainFragment;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.dealsOffersFragment.DealsOffersFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.favoriteFragment.FavoriteFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.loginFragment.LoginFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.menuFragment.MenuFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment.MYOrderFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.notificationFragment.NotificationsFragment;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    public TextView title;
    GpsTracker gpsTracker;
    PreferenceHelper preferenceHelper;
    public BottomNavigationView footer;
    String check_lang;
    ImageView favorit;
    ///////// defind attachBaseContext to install font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Make to run your application only in portrait mode

        title = findViewById(R.id.title);
        favorit = findViewById(R.id.favorit);
        footer = findViewById(R.id.navigation);
        footer.setOnNavigationItemSelectedListener(this);
        footer.getMenu().findItem(R.id.navigation_home).setChecked(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).commit();
        preferenceHelper = new PreferenceHelper(this);
        check_lang = preferenceHelper.getLanguage();
        favorit.setOnClickListener(this);

        //////// get current user location

        notification();

        gpsTracker = new GpsTracker(HomeActivity.this);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            preferenceHelper.setCURRENTLAT(String.valueOf(latitude));
            preferenceHelper.setCURRENTLONG(String.valueOf(longitude));
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentManager fm = getSupportFragmentManager();
        switch (menuItem.getItemId()) {

            case R.id.navigation_dashboard:

                for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new NotificationsFragment()).addToBackStack(null).commit();
                break;

            case R.id.navigation_order:

                if (PreferenceHelper.getUserId()>0) {
                    for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                        fm.popBackStack();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MYOrderFragment()).addToBackStack(null).commit();
                }
                else
                    Snackbar.make(footer,getText(R.string.loginfirst),Snackbar.LENGTH_LONG).show();

                break;

            case R.id.navigation_more:

                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MenuFragment()).addToBackStack(null).commit();
                break;

            case R.id.navigation_home:

                for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();
                break;

            case R.id.navigation_deals:
                for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new DealsOffersFragment()).addToBackStack(null).commit();
                break;

        }
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case  R.id.favorit:
                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
                    getSupportFragmentManager().popBackStack();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new FavoriteFragment()).addToBackStack(null).commit();

                break;
        }
    }

    ////////////////////Notification
    private void notification() {
        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .init();

        OneSignal.sendTag("id",String.valueOf(PreferenceHelper.getUserId()));
        OneSignal.sendTag("user_group_id",String.valueOf(PreferenceHelper.getUSER_GROUP_ID()));
        OneSignal.sendTag("store_id","0");
    }

    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
        }
    }
}
