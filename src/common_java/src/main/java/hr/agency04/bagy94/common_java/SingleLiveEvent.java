package hr.agency04.bagy94.common_java;

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class SingleLiveEvent extends LiveData {
    private AtomicBoolean mPendingObserver = new AtomicBoolean(false);

    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer observer) {
        if (hasActiveObservers()) {
            Log.d("SingleLiveEvent", "hasObservers()");
        }
        super.observe(owner, new Observer() {
            @Override
            public void onChanged(Object o) {
                if (mPendingObserver.compareAndSet(true, false)) {
                    observer.onChanged(o);
                }
            }
        });
    }

    @MainThread
    @Override
    protected void setValue(Object value) {
        mPendingObserver.set(true);
        super.setValue(value);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
