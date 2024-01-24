package ec.tecazuay.artevivam4a;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface UserService {

    @POST("api/estudiantes")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("api/estudiantes")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);

}
