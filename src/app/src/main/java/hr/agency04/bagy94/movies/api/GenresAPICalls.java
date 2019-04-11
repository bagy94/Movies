package hr.agency04.bagy94.movies.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenresAPICalls {
    @GET("genre/movie/list")
    Call<GenresApiResponse> getMovieGenres(@Query("api_key") String apiKey);
}
