package ch.hsr.sunriseclock.sunriseclock.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ClockApi {
    @GET("configuration")
    Call<ApiConfiguration> getConfiguration(); //, Callback<ClockApi> response);


    @POST("configuration")
    Call<ApiConfiguration> setConfiguration(@Body ApiConfiguration configuration); //, Callback<ClockApi> response);
}