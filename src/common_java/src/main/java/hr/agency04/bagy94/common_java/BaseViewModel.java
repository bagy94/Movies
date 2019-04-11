package hr.agency04.bagy94.common_java;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public abstract class BaseViewModel<ROUTER extends BaseRouter> extends AndroidViewModel {
    private ROUTER mRouter;

    public BaseViewModel(@NonNull ROUTER router, @NonNull Application application) {
        super(application);
        this.mRouter = router;
    }

    public void close() {
        mRouter.closeEvent.call();
    }

    public ROUTER getRouter() {
        return mRouter;
    }
}
