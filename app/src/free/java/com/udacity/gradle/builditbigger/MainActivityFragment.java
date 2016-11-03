package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jokermakerandroid.JokeConstants;
import com.example.jokermakerandroid.JokesActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.Constants;
import com.udacity.gradle.builditbigger.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Button jokeButton;
    InterstitialAd mInterstitialAd;
    private String mJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeButton = (Button) root.findViewById(R.id.free_tell_joke_btn);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(Constants.DEBUG_TARGET_FOR_AD_1)
                .addTestDevice(Constants.DEBUG_TARGET_FOR_AD_2)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                openJokeActivity();
            }
        });

        loadInterstitialAd();

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).showProgressDialog("Loading");
                loadInterstitialAd();
                new GetJokesAsyncTask(new GetJokesAsyncTask.GetJokesAsyncTaskListener() {
                    @Override
                    public void onComplete(String result) {
                        ((BaseActivity) getActivity()).hideProgressDialog();
                        mJoke = result;
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            openJokeActivity();
                        }
                    }
                }).execute();
            }
        });

        return root;
    }
    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(Constants.DEBUG_TARGET_FOR_AD_1)
                .addTestDevice(Constants.DEBUG_TARGET_FOR_AD_2)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    public void openJokeActivity() {
        Intent intent = new Intent(getActivity(), JokesActivity.class);
        intent.putExtra(JokeConstants.EXTRAS_JOKE, mJoke);
        startActivity(intent);
    }
}
