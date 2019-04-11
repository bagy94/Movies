package hr.agency04.bagy94.movies.repositories;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import hr.agency04.bagy94.common_java.RetrofitAPICallHandler;
import hr.agency04.bagy94.movies.R;
import hr.agency04.bagy94.movies.api.APIServiceProvider;
import hr.agency04.bagy94.movies.api.GenresAPICalls;
import hr.agency04.bagy94.movies.api.GenresApiResponse;
import hr.agency04.bagy94.movies.data.Genre;
import retrofit2.Call;

public class GenresRepository {
    private String mApiKey;
    private GenresAPICalls mCallProvider;
    private RetrofitAPICallHandler<GenresApiResponse> mCallHandler = new RetrofitAPICallHandler<>();
    private LiveData<List<Genre>> mGenres;

    public GenresRepository(Application application) {
        mApiKey = application.getApplicationContext().getResources().getString(R.string.API_KEY);
        this.mCallProvider = APIServiceProvider.getInstance(application.getApplicationContext()).create(GenresAPICalls.class);
    }

    public LiveData<List<Genre>> getMovieGenres() {
        if (mGenres == null) {
            Call<GenresApiResponse> call = mCallProvider.getMovieGenres(mApiKey);
            mGenres = Transformations.map(mCallHandler.getData(call), response -> response.getGenres());
        }
        return mGenres;
    }


}
