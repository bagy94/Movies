package hr.agency04.bagy94.movies.ui.moviedetail;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import hr.agency04.bagy94.common_java.BaseRouter;
import hr.agency04.bagy94.common_java.BaseViewModel;
import hr.agency04.bagy94.movies.data.Movie;
import hr.agency04.bagy94.movies.repositories.GenresRepository;
import hr.agency04.bagy94.movies.repositories.MoviesRepository;

public class MovieDetailViewModel extends BaseViewModel<BaseRouter> {

    private GenresRepository mRepository;
    private MoviesRepository mMovieRepository;
    private MutableLiveData<Movie> mMovie = new MutableLiveData<>();
    private LiveData<List<String>> mGenres;
    private LiveData<List<Movie>> mMovieSimilar;

    public MovieDetailViewModel(@NonNull Application application) {
        super(new BaseRouter(), application);
        mRepository = new GenresRepository(application);
        mMovieRepository = new MoviesRepository(application);
        mGenres = Transformations.switchMap(mMovie, movie -> mRepository.getMovieGenres(movie));
        mMovieSimilar = Transformations.switchMap(mMovie, movie -> mMovieRepository.getSimilarMovies(movie.getId()));
    }

    public LiveData<List<String>> getGenres() {
        return mGenres;
    }

    public LiveData<List<Movie>> getSimilarMovies(Movie forMovie) {
        return mMovieSimilar;
    }

    public void setMovie(Movie m) {
        this.mMovie.setValue(m);
    }
}
