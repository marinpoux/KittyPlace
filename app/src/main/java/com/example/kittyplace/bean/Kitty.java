package com.example.kittyplace.bean;

import java.io.Serializable;

public class Kitty implements Serializable {

    private int kittyId;
    private float kittyValEth;
    private float kittyValEur;
    private float kittyValUsd;
    private float kittyValBtc;
    private int kittyIdOwner;
    private String kittyNickname;
    private int kittyOnSale;

    public Kitty() {
        kittyId = 0;
        kittyValEth = 0;
        kittyValEur = 0;
        kittyValUsd = 0;
        kittyValBtc = 0;
        kittyIdOwner = Integer.parseInt(null);
        kittyNickname = "";
        kittyOnSale = 0;
    }

    public Kitty(int id, float ival_eth, float ival_eur, float ival_usd, float ival_btc){
        kittyId = id;
        kittyValEth = ival_eth;
        kittyValEur = ival_eur;
        kittyValUsd = ival_usd;
        kittyValBtc = ival_btc;
        kittyIdOwner = Integer.parseInt(null);
        kittyNickname = "";
        kittyOnSale = 0;
    }

    public Kitty(int id, float ival_eth, float ival_eur, float ival_usd, float ival_btc, int idOwner){
        kittyId = id;
        kittyValEth = ival_eth;
        kittyValEur = ival_eur;
        kittyValUsd = ival_usd;
        kittyValBtc = ival_btc;
        kittyIdOwner = idOwner;
        kittyNickname = "";
        kittyOnSale = 0;
    }

    public Kitty(int id, float ival_eth, float ival_eur, float ival_usd, float ival_btc, int idOwner, String nickname, int onSale){
        kittyId = id;
        kittyValEth = ival_eth;
        kittyValEur = ival_eur;
        kittyValUsd = ival_usd;
        kittyValBtc = ival_btc;
        kittyIdOwner = idOwner;
        kittyNickname = nickname;
        kittyOnSale = onSale;
    }

    public Kitty(Kitty k){
        this.kittyId = k.kittyId;
        this.kittyValEth = k.kittyValEth;
        this.kittyValEur = k.kittyValEur;
        this.kittyValUsd = k.kittyValUsd;
        this.kittyValBtc = k.kittyValBtc;
        this.kittyIdOwner = k.kittyIdOwner;
        this.kittyNickname = k.kittyNickname;
        this.kittyOnSale = k.kittyOnSale;
    }

    public int getKittyId(){return kittyId;}
    public float getKittyValEth(){return kittyValEth;}
    public float getKittyValEur(){return kittyValEur;}
    public float getKittyValUsd(){return kittyValUsd;}
    public float getKittyValBtc(){return kittyValBtc;}
    public int getKittyIdOwner(){return kittyIdOwner;}
    public String getKittyNickname(){return kittyNickname;}
    public int getKittyOnSale(){return kittyOnSale;}
    public Kitty getKitty(){return this;}

    public void setKittyId(int id){kittyId = id;}
    public void setKittyValEth(float val_eth){kittyValEth = val_eth;}
    public void setKittyValEur(float val_eur){kittyValEur = val_eur;}
    public void setKittyValUsd(float val_usd){kittyValUsd = val_usd;}
    public void setKittyValBtc(float val_btc){kittyValBtc = val_btc;}
    public void setKittyIdOwner(int idOwner){kittyIdOwner = idOwner;}
    public void setKittyNickname(String nickname){kittyNickname = nickname;}
    public void setKittyOnSale(int onSale){kittyOnSale = onSale;}

    public String toString() {

        String string = "Kitty id = " + this.kittyId + "; val eur = " + this.kittyValEur
                + "; val eth = " + this.kittyValEth + "; val usd = " + this.kittyValUsd
                + "; val btc = " + this.kittyValBtc + "; owner = " + this.kittyIdOwner
                + "; nickname = " + this.kittyNickname + "; on sale = " + this.kittyOnSale + ";";

        return string;
    }
}
