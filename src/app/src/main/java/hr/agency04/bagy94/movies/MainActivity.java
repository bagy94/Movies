package hr.agency04.bagy94.movies;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import hr.agency04.bagy94.movies.ui.movieslist.MoviesListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MoviesListFragment.newInstance())
                .commitNow();
        }
    }
}
