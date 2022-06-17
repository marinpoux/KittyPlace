package com.example.kittyplace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide();

        ImageButton btn_collection = findViewById(R.id.btn_collection);
        ImageButton btn_market = findViewById(R.id.btn_market);

        btn_collection.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, CollectionActivity.class);
            startActivity(intent);
        });

        btn_market.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity.this, MarketActivity.class);
            startActivity(intent);
        });
    }
}