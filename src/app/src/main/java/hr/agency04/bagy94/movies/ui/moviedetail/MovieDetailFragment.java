package hr.agency04.bagy94.movies.ui.moviedetail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import hr.agency04.bagy94.common_java.BaseFragment;
import hr.agency04.bagy94.movies.R;
import hr.agency04.bagy94.movies.data.Movie;
import hr.agency04.bagy94.movies.databinding.FragmentMovieDetailBinding;

public class MovieDetailFragment extends BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding> {
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
            mViewBinding.setMovie(mMovie);
            mViewModel.getGenres(mMovie.getGenreIds())
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
}
