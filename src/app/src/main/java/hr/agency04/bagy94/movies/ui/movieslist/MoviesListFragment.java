package hr.agency04.bagy94.movies.ui.movieslist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import hr.agency04.bagy94.common_java.BaseFragment;
import hr.agency04.bagy94.movies.R;
import hr.agency04.bagy94.movies.adapters.MoviesAdapter;
import hr.agency04.bagy94.movies.data.Category;
import hr.agency04.bagy94.movies.data.Movie;
import hr.agency04.bagy94.movies.databinding.FragmentMoviesListBinding;
import hr.agency04.bagy94.movies.ui.moviedetail.MovieDetailFragment;
import hr.agency04.bagy94.movies.utils.OnListItemSelected;

public class MoviesListFragment extends BaseFragment<MoviesListViewModel, FragmentMoviesListBinding> implements OnListItemSelected<Movie> {

    private MoviesAdapter mMoviesAdapter;

    private AdapterView.OnItemSelectedListener onSpinnerItemClickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    mViewModel.setSelected(Category.TopRated);
                    break;
                case 1:
                    mViewModel.setSelected(Category.Popular);
                    break;
                default:
                    Log.d(MoviesListFragment.class.getName(), "Case not found");
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Snackbar.make(getView(), getString(R.string.message_must_pick_category), Snackbar.LENGTH_SHORT).show();
        }
    };

    @Override
    protected int getViewId() {
        return R.layout.fragment_movies_list;
    }

    @Override
    protected Class<MoviesListViewModel> getViewModelClass() {
        return MoviesListViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMoviesAdapter = new MoviesAdapter(this);
        mViewBinding.movies.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewBinding.movies.setAdapter(mMoviesAdapter);
        mViewBinding.movies.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mViewBinding.selectCategory.setOnItemSelectedListener(onSpinnerItemClickListener);

        mViewModel.getMovies().observe(this, movies -> mMoviesAdapter.onNewDataSet(movies));
    }

    @Override
    public void onListItemSelected(Movie movie) {
        Bundle args = MovieDetailFragment.createArguments(movie);
        navigate(R.id.openDetails, args);
    }
}
