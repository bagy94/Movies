package hr.agency04.bagy94.movies.ui.moviedetail;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import hr.agency04.bagy94.common_java.BaseRouter;
import hr.agency04.bagy94.common_java.BaseViewModel;
import hr.agency04.bagy94.movies.data.Genre;
import hr.agency04.bagy94.movies.data.Movie;
import hr.agency04.bagy94.movies.repositories.GenresRepository;
import hr.agency04.bagy94.movies.repositories.MoviesRepository;

public class MovieDetailViewModel extends BaseViewModel<BaseRouter> {

    private GenresRepository mRepository;
    private MoviesRepository mMovieRepository;
    private LiveData<List<Genre>> mMovieGenres;
    private LiveData<List<String>> mGenres;

    public MovieDetailViewModel(@NonNull Application application) {
        super(new BaseRouter(), application);
        mRepository = new GenresRepository(application);
        mMovieRepository = new MoviesRepository(application);
        mMovieGenres = mRepository.getMovieGenres();
    }

    public LiveData<List<String>> getGenres(List<Integer> genreIds) {
        if (mGenres == null) {
            mGenres = Transformations.map(mMovieGenres, genres -> {
                final List<String> list = new ArrayList<>();
                for (Integer id : genreIds) {
                    for (Genre g : genres) {
                        if (id.equals(g.getId())) {
                            list.add(g.getName());
                        }
                    }
                }
                return list;
            });
        }
        return mGenres;
    }

    public LiveData<List<Movie>> getSimilarMovies(Movie forMovie) {
        return mMovieRepository.getSimilarMovies(forMovie.getId());
    }
}
