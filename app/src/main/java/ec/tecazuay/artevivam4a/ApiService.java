package ec.tecazuay.artevivam4a;

import java.util.List;

import ec.tecazuay.artevivam4a.modelo.Matricula;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("api/matriculas/estudiante/{idEstudiante}")
    Call<List<Matricula>> getMatriculas(@Path("idEstudiante") String idEstudiante);
    // Otros métodos si es necesario

}
