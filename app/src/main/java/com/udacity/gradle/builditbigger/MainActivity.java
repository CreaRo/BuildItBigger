package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jokermakerandroid.JokeConstants;
import com.example.jokermakerandroid.JokesActivity;
import com.example.rish.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        /*
        Intent jokeActivityIntent = new Intent(MainActivity.this, JokesActivity.class);
        jokeActivityIntent.putExtra(JokeConstants.EXTRAS_JOKE, Joker.getJoke());
        startActivity(jokeActivityIntent);
        */
        new GetJokeAsyncTask().execute();
    }

    class GetJokeAsyncTask extends AsyncTask<Void, Void, String> {
        private final String TAG = GetJokeAsyncTask.class.getSimpleName();
        private MyApi myApiService = null;

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
            Intent intent = new Intent(MainActivity.this, JokesActivity.class);
            intent.putExtra(JokeConstants.EXTRAS_JOKE, result);
            startActivity(intent);
        }
    }

}
