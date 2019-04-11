package hr.agency04.bagy94.movies.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import hr.agency04.bagy94.movies.data.Genre;

public class GenresApiResponse {

    @SerializedName("genres")
    private List<Genre> genres;

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return
                "GenresApiResponse{" +
                        "genres = '" + genres + '\'' +
                        "}";
    }
}