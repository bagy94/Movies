package hr.agency04.bagy94.movies.repositories;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import hr.agency04.bagy94.movies.R;
import hr.agency04.bagy94.movies.api.APIServiceProvider;
import hr.agency04.bagy94.movies.api.GenresAPICalls;
import hr.agency04.bagy94.movies.api.GenresApiResponse;
import hr.agency04.bagy94.movies.data.Genre;
import hr.agency04.bagy94.movies.data.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenresRepository {
    private String mApiKey;
    private GenresAPICalls mCallProvider;
    private List<Genre> mGenres;
    private MutableLiveData<List<String>> movieGenres = new MutableLiveData<>();
    public GenresRepository(Application application) {
        mApiKey = application.getApplicationContext().getResources().getString(R.string.API_KEY);
        this.mCallProvider = APIServiceProvider.getInstance(application.getApplicationContext()).create(GenresAPICalls.class);
    }

    public LiveData<List<String>> getMovieGenres(Movie movie) {
        if (mGenres == null) {
            Call<GenresApiResponse> call = mCallProvider.getMovieGenres(mApiKey);
            call.enqueue(new Callback<GenresApiResponse>() {
                @Override
                public void onResponse(Call<GenresApiResponse> call, Response<GenresApiResponse> response) {
                    if (response.isSuccessful()) {
                        mGenres = response.body().getGenres();
                        find(movie.getGenreIds());
                    }
                }

                @Override
                public void onFailure(Call<GenresApiResponse> call, Throwable t) {

                }
            });
        } else {
            find(movie.getGenreIds());
        }
        return movieGenres;
    }

    private void find(List<Integer> genreIds) {
        List<String> genres = new ArrayList<>();
        for (Integer genreId : genreIds) {
            for (Genre g : mGenres) {
                if (genreId.equals(g.getId())) {
                    genres.add(g.getName());
                    break;
                }
            }
        }
        movieGenres.setValue(genres);
    }
}
