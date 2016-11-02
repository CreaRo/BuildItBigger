package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    CountDownLatch signal;
    String resultString = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        signal.countDown();
    }

    public void testGetJokeAsync() throws InterruptedException {
        new GetJokesAsyncTask(new GetJokesAsyncTask.GetJokesAsyncTaskListener() {
            @Override
            public void onComplete(String result) {
                signal.countDown();
                resultString = result;
            }
        }).execute();
        signal.await();

        assertNotNull(resultString);
        assertFalse(resultString.equals(""));
    }

}