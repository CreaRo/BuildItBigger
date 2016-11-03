package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.jokermakerandroid.JokeConstants;
import com.example.jokermakerandroid.JokesActivity;
import com.example.rish.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by rish on 3/11/16.
 */

class GetJokesAsyncTask extends AsyncTask<Void, Void, String> {
    private final String TAG = GetJokesAsyncTask.class.getSimpleName();
    private MyApi myApiService = null;
    GetJokesAsyncTaskListener listener;

    public GetJokesAsyncTask(GetJokesAsyncTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://builditbigger-nanodegree.appspot.com/_ah/api/");
            myApiService = builder.build();
        }

        try {
            Log.d(TAG, "Attempting to connect to URL");
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onComplete(result);
    }

    public interface GetJokesAsyncTaskListener {
        void onComplete(String result);
    }
}
