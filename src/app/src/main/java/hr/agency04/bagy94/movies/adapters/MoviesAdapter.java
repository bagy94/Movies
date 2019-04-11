package hr.agency04.bagy94.movies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import hr.agency04.bagy94.movies.R;
import hr.agency04.bagy94.movies.data.Movie;
import hr.agency04.bagy94.movies.databinding.MovieItemBinding;
import hr.agency04.bagy94.movies.utils.OnListItemSelected;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> mMovies;
    private OnListItemSelected<Movie> mItemClickListener;

    public MoviesAdapter(OnListItemSelected<Movie> mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(inflater, R.layout.movie_item, parent, false);
        MovieViewHolder vh = new MovieViewHolder(movieItemBinding, mItemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public void onNewDataSet(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding itemBinding;
        private OnListItemSelected<Movie> mListener;

        public MovieViewHolder(MovieItemBinding binding, OnListItemSelected<Movie> mListener) {
            super(binding.getRoot());
            itemBinding = binding;
            binding.getRoot().setOnClickListener(v -> {
                Movie m = binding.getMovie();
                if (m != null) {
                    mListener.onListItemSelected(m);
                }
            });
        }

        private void bind(Movie movie) {
            itemBinding.setMovie(movie);
            itemBinding.executePendingBindings();
        }
    }
}
