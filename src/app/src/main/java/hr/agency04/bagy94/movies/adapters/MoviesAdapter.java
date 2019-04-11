package hr.agency04.bagy94.movies.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import hr.agency04.bagy94.movies.BR;
import hr.agency04.bagy94.movies.R;
import hr.agency04.bagy94.movies.data.Movie;
import hr.agency04.bagy94.movies.utils.OnListItemSelected;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> mMovies;
    private OnListItemSelected<Movie> mItemClickListener;
    private boolean isVertical = true;

    public MoviesAdapter(OnListItemSelected<Movie> mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public MoviesAdapter(OnListItemSelected<Movie> mItemClickListener, boolean isVertical) {
        this.mItemClickListener = mItemClickListener;
        this.isVertical = isVertical;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding movieItemBinding = null;
        if (isVertical) {
            movieItemBinding = DataBindingUtil.inflate(inflater, R.layout.movie_item, parent, false);
        } else {
            movieItemBinding = DataBindingUtil.inflate(inflater, R.layout.similar_movie_item, parent, false);
        }
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
        private ViewDataBinding itemBinding;
        private Movie mMovie;
        private OnListItemSelected<Movie> mListener;

        public MovieViewHolder(ViewDataBinding binding, OnListItemSelected<Movie> mListener) {
            super(binding.getRoot());
            itemBinding = binding;
            itemBinding.getRoot().setOnClickListener(v -> {
                if (mMovie != null) {
                    mListener.onListItemSelected(mMovie);
                }
            });
        }

        private void bind(Movie movie) {
            mMovie = movie;
            itemBinding.setVariable(BR.movie, movie);
            itemBinding.executePendingBindings();
        }
    }
}
