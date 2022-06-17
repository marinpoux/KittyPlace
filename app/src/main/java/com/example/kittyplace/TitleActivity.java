package com.example.kittyplace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        ImageView imgV = findViewById(R.id.app_intro);
        imgV.setOnClickListener(view -> {
            Intent intent = new Intent(TitleActivity.this, LoginActivity.class);
            TitleActivity.this.startActivity(intent);
            finish();
        });
    }
}