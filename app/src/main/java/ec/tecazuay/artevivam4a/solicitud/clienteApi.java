package ec.tecazuay.artevivam4a.solicitud;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class clienteApi {

    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoginInterceptor = new HttpLoggingInterceptor();
        httpLoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoginInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder( )
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.18.17:8080/")  // Cambia la URL base aquí
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    public static servicioUsuario getUserService(){
        servicioUsuario userService= getRetrofit().create(servicioUsuario.class);
        return userService;
    }

}
