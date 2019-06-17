package com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.codesroots.hossam.lordApp.R;

public class ProductInfoActivity extends AppCompatActivity {

    TextView description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        description = findViewById(R.id.description);

      String info =  getIntent().getStringExtra("product_info");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            description.setText(Html.fromHtml(info, Html.FROM_HTML_MODE_COMPACT));
        }

        else
            description.setText(Html.fromHtml(info));

    }
}
