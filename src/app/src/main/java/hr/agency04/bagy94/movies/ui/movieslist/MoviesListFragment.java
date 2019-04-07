package hr.agency04.bagy94.movies.ui.movieslist;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import hr.agency04.bagy94.movies.R;

public class MoviesListFragment extends Fragment {

    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }

    private MoviesListViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movies_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MoviesListViewModel.class);
        // TODO: Use the ViewModel
    }

}
