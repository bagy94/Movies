package hr.agency04.bagy94.movies.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MovieAPICalls {
    @GET("movie/top_rated")
    Call<MoviesApiResponse> getTopRated(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesApiResponse> getPopular(@Query("api_key") String apiKey);
}
