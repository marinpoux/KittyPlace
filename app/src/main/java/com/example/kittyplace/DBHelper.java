package com.example.kittyplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kittyplace.bean.Kitty;
import com.example.kittyplace.bean.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "KittyPlaceDB";

    // Table name: Note.
    private static final String TABLE_USERS = "USERS";
    private static final String C_USERS_ID = "user_id";
    private static final String C_USERS_NAME = "user_name";
    private static final String C_USERS_LASTNAME = "user_lastname";
    private static final String C_USERS_EMAIL = "user_email";
    private static final String C_USERS_LOGIN = "user_login";
    private static final String C_USERS_PASSWD = "user_password";
    private static final String C_USERS_WALLET = "user_wallet";

    private static final String TABLE_KITTIES = "KITTIES";
    private static final String C_KITTIES_ID = "kitty_id";
    private static final String C_KITTIES_IVAL_EUR = "kitty_ival_eur";
    private static final String C_KITTIES_IVAL_ETH = "kitty_ival_eth";
    private static final String C_KITTIES_IVAL_USD = "kitty_ival_usd";
    private static final String C_KITTIES_IVAL_BTC = "kitty_ival_btc";
    private static final String C_KITTIES_IDOWNER = "kitty_idowner";
    private static final String C_KITTIES_NICKNAME = "kitty_nickname";
    private static final String C_KITTIES_ONSALE = "kitty_onsale";

    public DBHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "BDHelper.onCreate ... ");
        // Scripts.
        String table_users = " CREATE TABLE " + TABLE_USERS + "("
                + C_USERS_ID + " INTEGER PRIMARY KEY,"
                + C_USERS_NAME + " TEXT,"
                + C_USERS_LASTNAME + " TEXT,"
                + C_USERS_EMAIL + " TEXT,"
                + C_USERS_LOGIN + " TEXT,"
                + C_USERS_PASSWD + " TEXT,"
                + C_USERS_WALLET + " FLOAT"
                + ")";

        String table_kitties = " CREATE TABLE " + TABLE_KITTIES + "("
                + C_KITTIES_ID + " INTEGER PRIMARY KEY,"
                + C_KITTIES_IVAL_ETH + " FLOAT,"
                + C_KITTIES_IVAL_EUR + " FLOAT,"
                + C_KITTIES_IVAL_USD + " FLOAT,"
                + C_KITTIES_IVAL_BTC + " FLOAT,"
                + C_KITTIES_IDOWNER + " INTEGER,"
                + C_KITTIES_NICKNAME + " TEXT,"
                + C_KITTIES_ONSALE + " INTEGER"
                + ")";

        // Execute Scripts.
        db.execSQL(table_users);
        db.execSQL(table_kitties);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "DBHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KITTIES);

        // Create tables again
        onCreate(db);
    }

    public void createDefaultUsersIfNeed() {
        int count = this.getUserCount();

        Log.i(TAG, "DBHelper.createDeaultUsersIfNeed ... " + count);

        if(count == 0) {
            Log.i(TAG, "DBHelper.createDeaultUsersIfNeed ... NEEDED");

            User user1 = new User(1,"marin", "schwartz",
                    "m.schwartz@ludus-academie.com", "marin.poux", "pouloulou");
            User user2 = new User(2,"pili", "poulou",
                    "pouloulou@miaou.fr", "mimou", "maou-maou");


            Log.i(TAG, user1.toString());
            Log.i(TAG, user2.toString());

            this.addUser(user1);
            this.addUser(user2);
        }
    }

    public void addUser(User user) {
        Log.i(TAG,"DBHelper.addUser ... " + user.getUserLogin());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(C_USERS_ID, user.getUserId());
        values.put(C_USERS_NAME, user.getUserName());
        values.put(C_USERS_LASTNAME, user.getUserLastName());
        values.put(C_USERS_EMAIL, user.getUserEmail());
        values.put(C_USERS_LOGIN, user.getUserLogin());
        values.put(C_USERS_PASSWD, user.getUserPasswd());
        values.put(C_USERS_WALLET, user.getUserWallet());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User getUser(int userId) {
        Log.i(TAG, "DBHelper.getUser ... " + userId);

        SQLiteDatabase db = this.getReadableDatabase();

        String query = " SELECT * FROM " + TABLE_USERS + " WHERE " + C_USERS_ID + " = " + userId;
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor != null) {

            cursor.moveToFirst();

            Log.i(TAG, "cursor not null");

            user.setUserId(Integer.parseInt(cursor.getString(0)));
            user.setUserName(cursor.getString(1));
            user.setUserLastname(cursor.getString(2));
            user.setUserEmail(cursor.getString(3));
            user.setUserLogin(cursor.getString(4));
            user.setUserPasswd(cursor.getString(5));
            user.setUserWallet(cursor.getFloat(6));

        }

        cursor.close();
        return user;
    }

    public int getUserCount() {
        Log.i(TAG, "DBHelper.getUserCount ... ");

        String countQuery = " SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public void createDefaultKittiesIfNeed() {
        int count = this.getKittyCount();

        Log.i(TAG, "DBHelper.createDeaultKittiesIfNeed ... " + count);

        if(count == 0) {
            Log.i(TAG, "DBHelper.createDeaultKittiesIfNeed ... NEEDED");

            Kitty kitty1 = new Kitty(1, 1, 1, 1, 1, 1, "minou", 0);
            Kitty kitty2 = new Kitty(2, 1, 1, 1, 1, 1, "blackie", 0);
            Kitty kitty3 = new Kitty(3, 1, 1, 1, 1, 1, "angel", 0);
            Kitty kitty4 = new Kitty(4, 1, 1, 1, 1, 1, "pepe", 0);
            Kitty kitty5 = new Kitty(5, 1, 1, 1, 1, 1, "Mr Miaou", 0);
            Kitty kitty6 = new Kitty(6, 1, 1, 1, 1, 1, "billie", 0);
            Kitty kitty7 = new Kitty(7, 1, 1, 1, 1, 1, "brilliant", 0);

            Log.i(TAG, kitty1.toString());

            this.addKitty(kitty1);
            this.addKitty(kitty2);
            this.addKitty(kitty3);
            this.addKitty(kitty4);
            this.addKitty(kitty5);
            this.addKitty(kitty6);
            this.addKitty(kitty7);
        }
    }

    public void addKitty(Kitty kitty) {
        Log.i(TAG,"DBHelper.addUser ... " + kitty.getKittyNickname());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(C_KITTIES_ID, kitty.getKittyId());
        values.put(C_KITTIES_IVAL_ETH, kitty.getKittyValEth());
        values.put(C_KITTIES_IVAL_EUR, kitty.getKittyValEur());
        values.put(C_KITTIES_IVAL_USD, kitty.getKittyValUsd());
        values.put(C_KITTIES_IVAL_BTC, kitty.getKittyValBtc());
        values.put(C_KITTIES_IDOWNER, kitty.getKittyIdOwner());
        values.put(C_KITTIES_NICKNAME, kitty.getKittyNickname());
        values.put(C_KITTIES_ONSALE, kitty.getKittyOnSale());

        db.insert(TABLE_KITTIES, null, values);
        db.close();
    }

    public Kitty getKitty(int kittyId) {
        Log.i(TAG, "DBHelper.getUser ... " + kittyId);

        SQLiteDatabase db = this.getReadableDatabase();

        String query = " SELECT * FROM " + TABLE_KITTIES + " WHERE " + C_KITTIES_ID + " = " + kittyId;
        String[] selectionArgs = {String.valueOf(kittyId)};

        Cursor cursor = db.rawQuery(query, null);

        Kitty kitty = new Kitty();

        if (cursor != null) {

            cursor.moveToFirst();

            Log.i(TAG, "cursor not null");

            kitty.setKittyId(Integer.parseInt(cursor.getString(0)));
            kitty.setKittyValEth(cursor.getFloat(1));
            kitty.setKittyValEur(cursor.getFloat(2));
            kitty.setKittyValUsd(cursor.getFloat(3));
            kitty.setKittyValBtc(cursor.getFloat(4));
            kitty.setKittyIdOwner(Integer.parseInt(cursor.getString(5)));
            kitty.setKittyNickname(cursor.getString(6));
            kitty.setKittyOnSale(Integer.parseInt(cursor.getString(7)));

        }

        cursor.close();
        return kitty;
    }

    public int getKittyCount() {
        Log.i(TAG, "DBHelper.getUserCount ... ");

        String countQuery = " SELECT * FROM " + TABLE_KITTIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int getUser(String userLogin) {
        Log.i(TAG, "DBHelper.getUserByLogin ... ");

        String query = "SELECT " + C_USERS_ID + " FROM " + TABLE_USERS
                + " WHERE " + C_USERS_LOGIN + " = '" + userLogin + "'";

        int userId;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor != null && cursor.getCount()>0) {

            Log.i(TAG, "cursor not null");

            cursor.moveToFirst();
            userId = cursor.getInt(0);
        }
        else {
            Log.i(TAG, "cursor null");

            userId = -1;
        }

        cursor.close();

        return userId;
    }

    public int getUserKittyCount(int userId) {
        Log.i(TAG, "DBHelper.getUserCount ... ");

        String countQuery = " SELECT * FROM " + TABLE_KITTIES
                + " WHERE " + C_KITTIES_IDOWNER + " = " + userId;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public List<Kitty> getOwnerKitties(int userId) {
        Log.i(TAG, "DBHelper.getOwnerKitties ... " );

        List<Kitty> kittyList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_KITTIES
                + " WHERE " + C_KITTIES_IDOWNER + " = " + userId;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.getCount()>0 && cursor.moveToFirst()) {
            do {
                Kitty kitty = new Kitty();
                kitty.setKittyId(Integer.parseInt(cursor.getString(0)));
                kitty.setKittyValEth(cursor.getFloat(1));
                kitty.setKittyValEur(cursor.getFloat(2));
                kitty.setKittyValUsd(cursor.getFloat(3));
                kitty.setKittyValBtc(cursor.getFloat(4));
                kitty.setKittyIdOwner(Integer.parseInt(cursor.getString(5)));
                kitty.setKittyNickname(cursor.getString(6));
                kitty.setKittyOnSale(Integer.parseInt(cursor.getString(7)));

                kittyList.add(kitty);
            } while (cursor.moveToNext());
        }
        return kittyList;
    }
}
