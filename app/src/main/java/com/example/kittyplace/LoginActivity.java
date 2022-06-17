package com.example.kittyplace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText et_login = findViewById(R.id.edittext_loggin);
        EditText et_passwd = findViewById(R.id.edittext_password);

        Button btn_game = findViewById(R.id.btn_kittyFinder);
        btn_game.setOnClickListener(view -> {
            Toast.makeText(this, "OPEN KITTY FINDER GAME APP", Toast.LENGTH_SHORT).show();
            finish();
        });

        Button btn_login = findViewById(R.id.btn_signin);
        btn_login.setOnClickListener(view -> {

            String login = et_login.getText().toString();
            String passwd = et_passwd.getText().toString();

            int userId = MainActivity.kittyDB.getUser(login);

            if (userId < 0)
                Toast.makeText(this, "Utilisateur inexistant !", Toast.LENGTH_SHORT).show();
            else {
                MainActivity.activeUser = MainActivity.kittyDB.getUser(userId);

                Log.i("active user", MainActivity.activeUser.toString());
                Log.i("password", "passwd = " + passwd + "; user password = " + MainActivity.activeUser.getUserPasswd() + ";");

                if (passwd.equals(MainActivity.activeUser.getUserPasswd())) {

                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    LoginActivity.this.startActivity(intent);
                    finish();
                }

                else {
                    Toast.makeText(this, "Mot de passe invalide !", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}