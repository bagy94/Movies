package hr.agency04.bagy94.movies.repositories;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import hr.agency04.bagy94.common_java.RetrofitAPICallHandler;
import hr.agency04.bagy94.movies.R;
import hr.agency04.bagy94.movies.api.APIServiceProvider;
import hr.agency04.bagy94.movies.api.MovieAPICalls;
import hr.agency04.bagy94.movies.api.MoviesApiResponse;
import hr.agency04.bagy94.movies.data.Movie;
import retrofit2.Call;

public class MoviesRepository {
    private String mApiKey;
    private MovieAPICalls mCallProvider;
    private RetrofitAPICallHandler<MoviesApiResponse> mCallHandler = new RetrofitAPICallHandler<>();

    public MoviesRepository(Application application) {
        mApiKey = application.getApplicationContext().getResources().getString(R.string.API_KEY);
        this.mCallProvider = APIServiceProvider.getInstance(application.getApplicationContext()).create(MovieAPICalls.class);
    }

    public LiveData<List<Movie>> getTopRated() {
        Call<MoviesApiResponse> call = mCallProvider.getTopRated(mApiKey);
        return Transformations.map(mCallHandler.getData(call), response -> response.getResults());
    }

    public LiveData<List<Movie>> getMostPopular() {
        Call<MoviesApiResponse> call = mCallProvider.getPopular(mApiKey);
        return Transformations.map(mCallHandler.getData(call), response -> response.getResults());
    }
}
