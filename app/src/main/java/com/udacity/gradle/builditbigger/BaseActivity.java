package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by rish on 3/11/16.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    public void showProgressDialog(String loadingText) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(loadingText);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.setMessage(loadingText);
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

}
