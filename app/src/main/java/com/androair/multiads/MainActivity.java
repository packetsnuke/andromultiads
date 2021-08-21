package com.androair.multiads;

import static com.androair.multiads.AdsSetting.HPK1;
import static com.androair.multiads.AdsSetting.HPK2;
import static com.androair.multiads.AdsSetting.HPK3;
import static com.androair.multiads.AdsSetting.HPK4;
import static com.androair.multiads.AdsSetting.HPK5;
import static com.androair.multiads.AdsSetting.INITIALIZE_SDK;
import static com.androair.multiads.AdsSetting.MAIN_ADS_BANNER;
import static com.androair.multiads.AdsSetting.MAIN_ADS_REWARDS;
import static com.androair.multiads.AdsSetting.NATIVE_ADS_ADMOB;
import static com.androair.multiads.AdsSetting.SELECT_ADS;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.androair.andromultiads.AndroAdsGDPR;
import com.androair.andromultiads.AndroAdsOpenAds;
import com.androair.andromultiads.AndroAdsBanner;
import com.androair.andromultiads.AndroAdsInitialize;
import com.androair.andromultiads.AndroAdsInterstitial;
import com.androair.andromultiads.AndroAdsNative;
import com.androair.andromultiads.AndroAdsReward;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layAds = findViewById(R.id.layAds);
        FrameLayout nativeads = findViewById(R.id.laynative);
        RelativeLayout layAdsmall = findViewById(R.id.laysAdsmall);
        AndroAdsGDPR.loadGdpr(MainActivity.this,SELECT_ADS,true);
        AndroAdsInitialize.SelectAds(MainActivity.this, AdsSetting.SELECT_ADS, INITIALIZE_SDK);
        AndroAdsBanner.MediumBanner(MainActivity.this, layAds,AdsSetting.SELECT_ADS, AdsSetting.MAIN_ADS_BANNER,HPK1
                ,HPK2,HPK3,HPK4,HPK5);
        AndroAdsBanner.SmallBanner(MainActivity.this, layAdsmall,AdsSetting.SELECT_ADS, AdsSetting.MAIN_ADS_BANNER,HPK1
                ,HPK2,HPK3,HPK4,HPK5);
        AndroAdsInterstitial.LoadIntertitial(MainActivity.this, AdsSetting.SELECT_ADS, AdsSetting.MAIN_ADS_INTERTITIAL,HPK1
                ,HPK2,HPK3,HPK4,HPK5 );
        AndroAdsNative.SmallNativeAdmob(MainActivity.this,SELECT_ADS, AdsSetting.BACKUP_ADS, nativeads, NATIVE_ADS_ADMOB,MAIN_ADS_BANNER, HPK1
                ,HPK2,HPK3,HPK4,HPK5);
        AndroAdsReward.LoadReward(MainActivity.this, SELECT_ADS, MAIN_ADS_REWARDS );
        AndroAdsOpenAds.ShowOpen(MainActivity.this);
    }

    public void showads(View view){
        AndroAdsInterstitial.ShowIntertitial(MainActivity.this,AdsSetting.SELECT_ADS,
                AdsSetting.MAIN_ADS_INTERTITIAL, 0,HPK1
                ,HPK2,HPK3,HPK4,HPK5);

    }

    public void showreward(View view){
        AndroAdsReward.ShowReward(MainActivity.this,SELECT_ADS,MAIN_ADS_REWARDS);

    }

    public void onResume(){
        super.onResume();
        if (AndroAdsReward.unlockreward){
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
        }
    }
}