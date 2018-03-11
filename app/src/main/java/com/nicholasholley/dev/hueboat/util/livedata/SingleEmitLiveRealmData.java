package com.nicholasholley.dev.hueboat.util.livedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;

import com.nicholasholley.dev.hueboat.util.livedata.LiveRealmData;
import com.nicholasholley.dev.hueboat.util.log.Log;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Documentation
 */

public class SingleEmitLiveRealmData<T extends RealmModel> extends LiveRealmData<T> {

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @MainThread
    public void observe(LifecycleOwner owner, final Observer<RealmResults<T>> observer) {

        if (hasActiveObservers()) {
            Log.Companion.w("Multiple observers registered but only one will be notified of changes.");
        }

        // Observe the internal MutableLiveData
        super.observe(owner, new Observer<RealmResults<T>>() {
            @Override
            public void onChanged(@Nullable RealmResults<T> t) {
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    public SingleEmitLiveRealmData(@NotNull RealmResults<T> realmResults) {
        super(realmResults);
    }

    @MainThread
    public void setValue(@Nullable RealmResults<T> t) {
        mPending.set(true);
        super.setValue(t);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    public void call() {
        setValue(null);
    }
}