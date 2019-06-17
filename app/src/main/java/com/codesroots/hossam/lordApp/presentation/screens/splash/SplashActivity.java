package com.codesroots.hossam.lordApp.presentation.screens.splash;


import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();

    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new SplashViewModelFactory(getApplication());
    }
}
