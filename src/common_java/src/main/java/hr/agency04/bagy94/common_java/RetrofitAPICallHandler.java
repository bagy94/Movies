package hr.agency04.bagy94.common_java;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitAPICallHandler<RESPONSE> {

    public LiveData<RESPONSE> getData(Call<RESPONSE> apiCall) {
        final MutableLiveData<RESPONSE> data = new MutableLiveData<>();
        apiCall.enqueue(new Callback<RESPONSE>() {
            @Override
            public void onResponse(Call<RESPONSE> call, Response<RESPONSE> response) {
                if (response.isSuccessful()) {
                    onResponseReceived(data, response);
                } else {
                    onServiceFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<RESPONSE> call, Throwable t) {
                onServiceFail(t.getMessage());
            }
        });
        return data;
    }

    protected void onResponseReceived(MutableLiveData<RESPONSE> data, Response<RESPONSE> response) {
        if (response.isSuccessful()) {
            data.setValue(response.body());
        } else {
            onServiceFail(response.message());
        }
    }

    protected void onServiceFail(String message) {
        Log.w(RetrofitAPICallHandler.class.getName(), "On remote service failed->" + message);
    }
}
