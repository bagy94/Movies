package hr.agency04.bagy94.movies.api;


import android.content.Context;

import hr.agency04.bagy94.movies.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServiceProvider {
    private Retrofit mRetrofit;

    public APIServiceProvider(Context context) {
        String baseUrl = context.getResources().getString(R.string.BASE_API_URL);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        mRetrofit = new Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(GsonConverterFactory.create()).build();
    }

    volatile private static APIServiceProvider mInstance;

    public static APIServiceProvider getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new APIServiceProvider(context);
        }
        return mInstance;
    }

    public <T> T create(Class<T> serviceClass) {
        return mRetrofit.create(serviceClass);
    }
}
