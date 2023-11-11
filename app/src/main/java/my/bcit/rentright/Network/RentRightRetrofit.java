package my.bcit.rentright.Network;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.converter.gson.GsonConverterFactory;

public class RentRightRetrofit {
    private static Retrofit retrofit;
    private static final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    private RentRightRetrofit() {}

    public static synchronized Retrofit getInstance() {

        if (retrofit == null) {

            //CookieManager cookieManager = new CookieManager();
            Interceptor headerInterceptor = chain -> {
                Request originalRequest = chain.request();
                Request newRequest = originalRequest.newBuilder()
                        .header("Accept", "application/json")
                        .header("x-requested-with", "XMLHttpRequest")
                        .build();
                return chain.proceed(newRequest);
            };
            CookieJar cookieJar = new CookieJar() {
                @NonNull
                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url.host(), cookies);
                }

                @NonNull
                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url.host());
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            };

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(25, TimeUnit.SECONDS)
                    .readTimeout(25, TimeUnit.SECONDS)
                    .writeTimeout(25, TimeUnit.SECONDS)
                    .addInterceptor(headerInterceptor)
                    .cookieJar(cookieJar)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://se-rentright-backend.lm902.cn")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
