package com.androair.andromultiads;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.applovin.adview.AppLovinAdView;
import com.applovin.mediation.AppLovinExtras;
import com.applovin.mediation.ApplovinAdapter;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.mopub.mobileads.MoPubView;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.Mrec;

public class AndroAdsBanner {
    public static void SmallBanner(Activity activity, RelativeLayout layAds, String selectAds, String idBanner, String Hpk1,
                                   String Hpk2, String Hpk3, String Hpk4, String Hpk5 ) {
        switch (selectAds) {
            case "ADMOB":
                /*Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .build();*/
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                AdView adView;
                adView = new AdView(activity);
                adView.setAdUnitId(idBanner);
                layAds.addView(adView);
                AdSize adSize = getAdSize(activity);
                adView.setAdSize(adSize);
                adView.loadAd(request);
                break;

            case "APPLOVIN-M":
                MaxAdView adView2;
                adView2 = new MaxAdView(idBanner, activity);
                final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                adView2.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                layAds.addView(adView2);
                adView2.loadAd();
                break;

            case "MOPUB":
                MoPubView moPubView;
                moPubView = new MoPubView(activity);
                moPubView.setAdUnitId(idBanner);
                layAds.addView(moPubView);
                moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                break;
            case "STARTAPP":
                Banner startAppBanner = new Banner(activity);
                RelativeLayout.LayoutParams bannerParameters =
                        new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layAds.addView(startAppBanner, bannerParameters);
                break;
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                Bundle bannerExtras = new Bundle();
                bannerExtras.putString( "zone_id", idBanner );
                builder.addCustomEventExtrasBundle( AppLovinCustomEventBanner.class, bannerExtras );

                boolean isTablet3 = AppLovinSdkUtils.isTablet( activity );
                AppLovinAdSize adSize3 = isTablet3 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                AppLovinAdView adView3 = new AppLovinAdView( adSize3, activity );
                layAds.addView(adView3);
                adView3.loadNextAd();

                break;
        }

    }

    public static void SmallBannerAdmob(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup,String Hpk1,
                                        String Hpk2, String Hpk3, String Hpk4, String Hpk5 ) {
        /*Bundle extras = new FacebookExtras()
                .setNativeBanner(true)
                .build();
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                .build();*/
        Bundle extras = new AppLovinExtras.Builder()
                .setMuteAudio(true)
                .build();
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                .build();
        AdView adView;
        adView = new AdView(activity);
        adView.setAdUnitId(idBanner);
        layAds.addView(adView);
        AdSize adSize = getAdSize(activity);
        adView.setAdSize(adSize);
        adView.loadAd(request);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        MaxAdView adView;
                        adView = new MaxAdView(idBannerBackup, activity);
                        final boolean isTablet = AppLovinSdkUtils.isTablet(activity);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, isTablet ? 90 : 50);
                        adView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
                        layAds.addView(adView);
                        adView.loadAd();
                        break;
                    case "MOPUB":
                        MoPubView moPubView;
                        moPubView = new MoPubView(activity);
                        moPubView.setAdUnitId(idBannerBackup);
                        layAds.addView(moPubView);
                        moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                        break;
                    case "STARTAPP":
                        Banner startAppBanner = new Banner(activity);
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString( "zone_id", idBannerBackup );
                        builder.addCustomEventExtrasBundle( AppLovinCustomEventBanner.class, bannerExtras );

                        boolean isTablet2 = AppLovinSdkUtils.isTablet( activity );
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        AppLovinAdView adView2 = new AppLovinAdView( adSize, activity );
                        layAds.addView(adView2);
                        adView2.loadNextAd();

                        break;
                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });


    }

    public static void MediumBanner(Activity activity, RelativeLayout layAds, String selectAds, String idBanner,String Hpk1,
                                    String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        switch (selectAds) {
            case "ADMOB": {
                /*Bundle extras = new FacebookExtras()
                        .setNativeBanner(true)
                        .build();
                AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                        .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                        .build();*/
                Bundle extras = new AppLovinExtras.Builder()
                        .setMuteAudio(true)
                        .build();
                AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                        .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                        .build();
                AdView adView;
                adView = new AdView(activity);
                adView.setAdUnitId(idBanner);
                layAds.addView(adView);
                adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
                adView.loadAd(request);
                break;
            }
            case "APPLOVIN-M": {
                MaxAdView adView;
                adView = new MaxAdView(idBanner, MaxAdFormat.MREC, activity);
                final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                adView.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                layAds.addView(adView);
                adView.loadAd();
                break;
            }
            case "MOPUB":
                MoPubView moPubView;
                moPubView = new MoPubView(activity);
                moPubView.setAdUnitId(idBanner);
                layAds.addView(moPubView);
                moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                break;
            case "STARTAPP":
                Mrec startAppBanner = new Mrec(activity);
                RelativeLayout.LayoutParams bannerParameters =
                        new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layAds.addView(startAppBanner, bannerParameters);
                break;
            case "APPLOVIN-D":
                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                Bundle bannerExtras = new Bundle();
                bannerExtras.putString( "zone_id", idBanner );
                builder.addCustomEventExtrasBundle( AppLovinCustomEventBanner.class, bannerExtras );

                boolean isTablet2 = AppLovinSdkUtils.isTablet( activity );
                AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                AppLovinAdView adView2 = new AppLovinAdView( adSize, activity );
                layAds.addView(adView2);
                adView2.loadNextAd();
                break;
        }
    }

    public static void MediumBannerAdmob(Activity activity, RelativeLayout layAds, String selectAdsBackup, String idBanner, String idBannerBackup,String Hpk1,
                                         String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        /*Bundle extras = new FacebookExtras()
                .setNativeBanner(true)
                .build();
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .addNetworkExtrasBundle(FacebookAdapter.class, extras)
                .build();*/
        Bundle extras = new AppLovinExtras.Builder()
                .setMuteAudio(true)
                .build();
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .addNetworkExtrasBundle(ApplovinAdapter.class, extras)
                .build();
        AdView adView;
        adView = new AdView(activity);
        adView.setAdUnitId(idBanner);
        layAds.addView(adView);
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
        adView.loadAd(request);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                switch (selectAdsBackup) {
                    case "APPLOVIN-M": {
                        MaxAdView adView;
                        //adView = new MaxAdView(APPLOVIN_BANNER, (Activity) MainActivity.this);
                        adView = new MaxAdView(idBannerBackup, MaxAdFormat.MREC, activity);
                        //AppLovinAdView adView = new AppLovinAdView(AppLovinAdSize.BANNER, context);
                        final int widthPx = AppLovinSdkUtils.dpToPx(activity, 300);
                        final int heightPx = AppLovinSdkUtils.dpToPx(activity, 250);
                        adView.setLayoutParams(new ConstraintLayout.LayoutParams(widthPx, heightPx));
                        layAds.addView(adView);
                        adView.loadAd();
                        break;
                    }
                    case "MOPUB":
                        MoPubView moPubView;
                        moPubView = new MoPubView(activity);
                        moPubView.setAdUnitId(idBannerBackup);
                        layAds.addView(moPubView);
                        moPubView.loadAd(MoPubView.MoPubAdSize.HEIGHT_50);
                        break;
                    case "STARTAPP":
                        Mrec startAppBanner = new Mrec(activity);
                        RelativeLayout.LayoutParams bannerParameters =
                                new RelativeLayout.LayoutParams(
                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        layAds.addView(startAppBanner, bannerParameters);
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString( "zone_id", idBannerBackup );
                        builder.addCustomEventExtrasBundle( AppLovinCustomEventBanner.class, bannerExtras );

                        boolean isTablet2 = AppLovinSdkUtils.isTablet( activity );
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        AppLovinAdView adView2 = new AppLovinAdView( adSize, activity );
                        layAds.addView(adView2);
                        adView2.loadNextAd();
                        break;
                }
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }


    private static AdSize getAdSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }
}
