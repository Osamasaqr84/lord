package com.codesroots.hossam.lordApp.presentation.screens.callus;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.codesroots.hossam.lordApp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CallUsActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_us);
    }


    public void callus(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+9647726802533"));
        if (ActivityCompat.checkSelfPermission(CallUsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        startActivity(intent);
    }


    public void sendmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","info@lord.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
