package com.codesroots.hossam.lordApp.presentation.screens;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {


    LinearLayout layout_arabic,layout_eng;
    PreferenceHelper helper;
    String lang_already;
    String arabic = "0";
    String eng = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        layout_arabic=findViewById(R.id.layout_arabic);
        layout_eng=findViewById(R.id.layout_eng);
        helper=new PreferenceHelper(LanguageActivity.this);
        lang_already = helper.getLanguage();

        layout_arabic.setOnClickListener(v -> {

            if (lang_already != null && lang_already.equals("0")){
                Toast.makeText(LanguageActivity.this, R.string.already_arabic, Toast.LENGTH_SHORT).show();
            }
            else {
                String languageToLoad  = "ar";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                LanguageActivity.this.getResources().updateConfiguration(config,LanguageActivity.this.getResources().getDisplayMetrics());
                helper.setLanguage(arabic);
                Toast.makeText(LanguageActivity.this, R.string.arabic_change, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LanguageActivity.this, HomeActivity.class);
                startActivity(intent);
                LanguageActivity.this.finish();
            }
        });


        layout_eng.setOnClickListener(v -> {
            if (lang_already != null && lang_already.equals("1")){
                Toast.makeText(LanguageActivity.this, R.string.already_eng, Toast.LENGTH_SHORT).show();
            }

            else
            {
                String languageToLoad  = "en";
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                LanguageActivity.this.getResources().updateConfiguration(config, LanguageActivity.this.getResources().getDisplayMetrics());
                helper.setLanguage(eng);
                Toast.makeText(LanguageActivity.this, R.string.eng_change, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LanguageActivity.this, HomeActivity.class);
                startActivity(intent);
                LanguageActivity.this.finish();
            }
        });

    }
}
