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

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Button jokeButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeButton = (Button) root.findViewById(R.id.paid_tell_joke_btn);

        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).showProgressDialog("Loading");
                new GetJokesAsyncTask(new GetJokesAsyncTask.GetJokesAsyncTaskListener() {
                    @Override
                    public void onComplete(String result) {
                        ((BaseActivity) getActivity()).hideProgressDialog();
                        openJokeActivity(result);
                    }
                }).execute();
            }
        });

        return root;
    }

    public void openJokeActivity(String joke) {
        Intent intent = new Intent(getActivity(), JokesActivity.class);
        intent.putExtra(JokeConstants.EXTRAS_JOKE, joke);
        startActivity(intent);
    }
}
