package hr.agency04.bagy94.common_java;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

public class BaseRouter {
    SingleLiveEvent closeEvent = new SingleLiveEvent();

    public void observe(final LifecycleOwner lifecycleOwner) {
        closeEvent.observe(lifecycleOwner, new Observer() {
            @Override
            public void onChanged(Object o) {
                if (lifecycleOwner instanceof BaseFragment) {
                    ((BaseFragment) lifecycleOwner).back();
                }
            }
        });
    }
}
