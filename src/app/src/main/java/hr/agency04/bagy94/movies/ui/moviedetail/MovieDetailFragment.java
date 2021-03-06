package hr.agency04.bagy94.movies.ui.moviedetail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import hr.agency04.bagy94.common_java.BaseFragment;
import hr.agency04.bagy94.movies.R;
import hr.agency04.bagy94.movies.adapters.MoviesAdapter;
import hr.agency04.bagy94.movies.data.Movie;
import hr.agency04.bagy94.movies.databinding.FragmentMovieDetailBinding;
import hr.agency04.bagy94.movies.utils.OnListItemSelected;

public class MovieDetailFragment extends BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding> implements OnListItemSelected<Movie> {
    private final static String ARG_MOVIE = "hr.agency04.bagy94.movies.ui.moviedetail.selectedMovie";
    private Movie mMovie;
    @Override
    protected int getViewId() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    protected Class<MovieDetailViewModel> getViewModelClass() {
        return MovieDetailViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mMovie != null) {
            onMovieSet(mMovie);
            requestGenresLabels();
            postSimilarMovies();
        }
    }

    public void onMovieSet(Movie movie) {
        mViewModel.setMovie(movie);
        mViewBinding.setMovie(movie);
    }

    private void postSimilarMovies() {
        final MoviesAdapter adapter = new MoviesAdapter(this, false);
        mViewBinding.similarMovies.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mViewBinding.similarMovies.setAdapter(adapter);
        mViewModel.getSimilarMovies(mMovie)
                .observe(this, adapter::onNewDataSet);
    }

    private void requestGenresLabels() {
        mViewModel.getGenres()
                .observe(this, genres -> {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < genres.size(); i++) {
                        builder.append(genres.get(i));
                        if ((i + 1) < genres.size()) {
                            builder.append("/");
                        }
                    }
                    mViewBinding.setGenresLabel(builder.toString());
                });
    }

    public static Bundle createArguments(Movie movie) {
        Bundle b = new Bundle();
        b.putSerializable(ARG_MOVIE, movie);
        return b;
    }

    @Override
    public void onParseArguments(Bundle arguments) {
        if (arguments != null && arguments.containsKey(ARG_MOVIE)) {
            mMovie = (Movie) arguments.getSerializable(ARG_MOVIE);
        }
    }

    @Override
    public void onListItemSelected(Movie movie) {
        if (movie != null) {
            onMovieSet(movie);
        }
    }
}
