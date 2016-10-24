package ch.hsr.sunriseclock.sunriseclock.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ClockApi {
    @GET("/configuration")
    Call<Configuration> getConfiguration(@Path("configuration") String configuration, Callback<ClockApi> response);


    // TODO this must probably be changed to the right Retrofit-Call.
    @POST("/configuration")
    Call<Configuration> setConfiguration(@Body Configuration configuration, Callback<ClockApi> response);
}