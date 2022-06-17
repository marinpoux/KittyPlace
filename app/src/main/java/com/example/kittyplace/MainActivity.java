package com.example.kittyplace;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kittyplace.bean.Crypto;
import com.example.kittyplace.bean.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    public static DBHelper kittyDB;
    public static SQLiteDatabase sqldb;

    public static User activeUser;
    public static Crypto coinGecko;

    //public static int[] imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        kittyDB = new DBHelper(this);

        sqldb = kittyDB.getReadableDatabase();
        //kittyDB.onUpgrade(sqldb,1,2);

        //setImgList();

        /*******************************/

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                //.penaltyDeath()
                .build());

        try {
            URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=ethereum&vs_currencies=eth%2Ceur%2Cusd%2Cbtc");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.i("first", "first");
            conn.getResponseCode();
            if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
                Log.i("second", "ok");
                InputStream inputStream = null;
                try {
                    inputStream = conn.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // ...que l'on transforme ici en String par simplicité d'usage (note :
                // il peut s'agit d'autre chose qu'un String pour
                // d'autres webservices, comme des images)
                String data = readStringData(inputStream);

                Log.i("Request", data);

                JSONObject js = new JSONObject(data);

                Log.i("cc", js.getJSONObject("ethereum").getString("eur"));

                coinGecko = new Crypto(js.getJSONObject("ethereum").getString("eth"),
                        js.getJSONObject("ethereum").getString("eur"),
                        js.getJSONObject("ethereum").getString("usd"),
                        js.getJSONObject("ethereum").getString("btc")
                );
            }
            else {
                Log.i("third", "error");
                String response = "FAILED"; // See documentation for more info on response handling
            }

        } catch (IOException | JSONException e) {
            //TODO Handle problems..
        }

        /********************************/

        kittyDB.createDefaultUsersIfNeed();
        kittyDB.createDefaultKittiesIfNeed();

        Log.i("crypto", coinGecko.toString());

        Log.i("user", kittyDB.getUser(1).toString());
        //Log.i("user", kittyDB.getUser(1).getUserLogin());
    }

    final Handler h = new Handler();
    final Runnable r = () -> {
        if(!isFinishing()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        h.postDelayed(r, 200);
    }

    @Override
    protected void onPause() {
        super.onPause();
        h.removeCallbacks(r);
    }


    private String readStringData(InputStream stream)  {
        BufferedReader reader = null;
        StringBuilder result = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
            //return null;

        } catch (IOException e) {
            Log.e(getClass().getSimpleName(), "not working", e);

        } finally {
            // On ferme tout les flux dans tout les cas
            if(reader != null){
                try {
                    reader.close();

                } catch (IOException exp2) {
                    Log.e(getClass().getSimpleName(), "not working again", exp2);
                }
            }
        }
        return null;
    }

    /*private void setImgList() {
        int[] textureArrayWin = {
                R.drawable.kitty_1,
                R.drawable.kitty_2,
                R.drawable.kitty_3,
                R.drawable.kitty_4,
                R.drawable.kitty_5,
                R.drawable.kitty_6,
                R.drawable.kitty_7
        };
        imgList = textureArrayWin;
    }*/
}