package ec.tecazuay.artevivam4a;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoginInterceptor = new HttpLoggingInterceptor();
        httpLoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoginInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder( )
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.19:8080")
                .client(okHttpClient)
                .build();


        return retrofit;
    }
    public static UserService getService(){
        UserService userService= getRetrofit().create(UserService.class);
        return userService;
    }
}