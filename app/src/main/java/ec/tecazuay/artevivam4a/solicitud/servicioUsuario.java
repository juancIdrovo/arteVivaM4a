package ec.tecazuay.artevivam4a.solicitud;

import java.util.List;

import ec.tecazuay.artevivam4a.modelo.Estudiante;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface servicioUsuario {

    @GET("api/estudiantes")
    Call<List<Estudiante>> obtenerEstudiantes();

    // Otros métodos relacionados con estudiantes si es necesario...

    @POST("api/estudiantes/")  // Si tienes otros métodos de login, ajústalos según tu API
    Call<LoginRespuesta> userLogin(@Body LoginSolicitud loginRequest);

}
