package com.example.jokermakerandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokesActivity extends AppCompatActivity {

    private TextView jokesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        jokesTextView = (TextView) findViewById(R.id.tv_jokes);
        String joke = getIntent().getStringExtra(JokeConstants.EXTRAS_JOKE);
        if (joke == null)
            jokesTextView.setText("Oops. Unable to load joke.");
        jokesTextView.setText(joke);
    }
}
