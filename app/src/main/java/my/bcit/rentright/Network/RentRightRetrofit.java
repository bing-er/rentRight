package my.bcit.rentright.Network;

import java.util.concurrent.TimeUnit;
import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

public class RentRightRetrofit {
    private static Retrofit retrofit;

    private RentRightRetrofit() {}

    public static synchronized Retrofit getInstance() {

        if (retrofit == null) {

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(25, TimeUnit.SECONDS)
                    .readTimeout(25, TimeUnit.SECONDS)
                    .writeTimeout(25, TimeUnit.SECONDS)
                    .build();

            retrofit =  new Retrofit.Builder()
                    .baseUrl("http://localhost:7001/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
