package hr.agency04.bagy94.movies.databinding;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;
import hr.agency04.bagy94.movies.R;

public class DataBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void setPosterImage(ImageView imageView, String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String url = imageView.getContext().getResources().getString(R.string.BASE_IMAGE_URL) + imageUrl;
            Picasso.get().load(url).error(R.drawable.ic_error_black_84dp).fit().into(imageView);
        } else {
            imageView.setImageResource(R.drawable.ic_image_placeholder);
        }
    }
}
