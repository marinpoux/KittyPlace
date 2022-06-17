package com.example.kittyplace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MarketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        getSupportActionBar().hide();

        Button btn_back = findViewById(R.id.btn_back_market);
        btn_back.setOnClickListener(view -> {
            finish();
        });

        TextView tv_eth = findViewById(R.id.tv_eth2);
        TextView tv_eur = findViewById(R.id.tv_eur2);
        TextView tv_btc = findViewById(R.id.tv_btc2);
        TextView tv_usd = findViewById(R.id.tv_usd2);

        tv_eth.setText(MainActivity.coinGecko.getEthString());
        tv_eur.setText(MainActivity.coinGecko.getEurString());
        tv_btc.setText(MainActivity.coinGecko.getBtcString());
        tv_usd.setText(MainActivity.coinGecko.getUsdString());
    }
}