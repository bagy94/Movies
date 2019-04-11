package hr.agency04.bagy94.movies.ui.movieslist;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import hr.agency04.bagy94.common_java.BaseRouter;
import hr.agency04.bagy94.common_java.SingleLiveEvent;

public class MoviesListRouter extends BaseRouter {
    private final SingleLiveEvent movieSelected = new SingleLiveEvent();

    @Override
    public void observe(final LifecycleOwner lifecycleOwner) {
        super.observe(lifecycleOwner);
        movieSelected.observe(lifecycleOwner, new Observer() {
            @Override
            public void onChanged(Object o) {
                if (lifecycleOwner instanceof MoviesListFragment) {

                }
            }
        });
    }
}
