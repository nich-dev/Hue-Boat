package com.nicholasholley.dev.hueboat.util.log;

import com.google.firebase.crash.FirebaseCrash;
import android.util.Log;

import timber.log.Timber;

/**
 * Thanks hanspeide
 * https://gist.github.com/hanspeide/5387d59e316dea9f34060703cea180cb
 */

public class FirebaseTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }

        FirebaseCrash.log(message);

        if (t != null) {
            FirebaseCrash.report(t);
        }
    }
}