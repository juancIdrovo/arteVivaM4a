package ec.tecazuay.artevivam4a.solicitud;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface servicioUsuario {

    @POST("tecazuay/logina/")
    Call<LoginRespuesta> userLogin(@Body LoginSolicitud loginRequest);

}
