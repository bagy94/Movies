package hr.agency04.bagy94.movies.ui.movieslist;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import hr.agency04.bagy94.common_java.BaseViewModel;
import hr.agency04.bagy94.movies.data.Category;
import hr.agency04.bagy94.movies.data.Movie;
import hr.agency04.bagy94.movies.repositories.MoviesRepository;

public class MoviesListViewModel extends BaseViewModel<MoviesListRouter> {

    private MoviesRepository mMovieRepository;
    private MediatorLiveData<List<Movie>> mMovies = new MediatorLiveData<>();
    private Category mSelectedCategory;

    public MoviesListViewModel(Application application) {
        super(new MoviesListRouter(), application);
        this.mMovieRepository = new MoviesRepository(application);
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }

    public void setSelected(Category selected) {
        if (mSelectedCategory != selected) {
            if (selected == Category.TopRated) {
                mMovies.addSource(mMovieRepository.getTopRated(), data -> mMovies.setValue(data));
            } else {
                mMovies.addSource(mMovieRepository.getMostPopular(), data -> mMovies.setValue(data));
            }
            mSelectedCategory = selected;
        }
    }
}
