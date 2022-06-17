package com.example.kittyplace.bean;

import java.io.Serializable;

public class Crypto implements Serializable {

    private float eth;
    private float eur;
    private float usd;
    private float btc;

    public Crypto() {
        this.eth = 1;
        this.eur = 1;
        this.usd = 1;
        this.btc = 1;
    }

    public Crypto(float eth, float eur, float usd, float btc) {
        this.eth = eth;
        this.eur = eur;
        this.usd = usd;
        this.btc = btc;
    }

    public Crypto(String eth, String eur, String usd, String btc) {
        this.eth = Float.valueOf(eth).floatValue();
        this.eur = Float.valueOf(eur).floatValue();
        this.usd = Float.valueOf(usd).floatValue();
        this.btc = Float.valueOf(btc).floatValue();
    }

    public float getEth(){return eth;}
    public float getEur(){return eur;}
    public float getUsd(){return usd;}
    public float getBtc(){return btc;}

    public String getEthString(){return Float.toString(eth);}
    public String getEurString(){return Float.toString(eur);}
    public String getUsdString(){return Float.toString(usd);}
    public String getBtcString(){return Float.toString(btc);}

    public void setEth(float val){eth = val;}
    public void setEur(float val){eur = val;}
    public void setUsd(float val){usd = val;}
    public void setBtc(float val){btc = val;}

    public void setEth(String val){eth = Float.valueOf(val).floatValue();;}
    public void setEur(String val){eur = Float.valueOf(val).floatValue();;}
    public void setUsd(String val){usd = Float.valueOf(val).floatValue();;}
    public void setBtc(String val){btc = Float.valueOf(val).floatValue();;}

    public String toString(){
        String string = "eth = " + this.eth + "; eur = " + this.eur
                + "; usd = " + this.usd + "; btc = " + this.btc + ";";

        return string;
    }
}
