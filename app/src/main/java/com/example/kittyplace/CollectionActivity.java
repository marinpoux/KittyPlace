package com.example.kittyplace;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CollectionActivity extends AppCompatActivity {

    //private int[] imgList = MainActivity.imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        getSupportActionBar().hide();

        Button btn_back = findViewById(R.id.btn_back_collection);
        btn_back.setOnClickListener(view -> {
            finish();
        });

        /*int kittyCount = MainActivity.kittyDB.getUserKittyCount(MainActivity.activeUser.getUserId());

        if (kittyCount > 0) {
            List<Kitty> kittyList = MainActivity.kittyDB.getOwnerKitties(MainActivity.activeUser.getUserId());

            LinearLayout layout = findViewById(R.id.layout_collection);

            for (int i=1 ; i< (kittyCount+1) ; i++) {

                ImageView kittyPic = new ImageView(CollectionActivity.this);
            }
        }*/

    }
}