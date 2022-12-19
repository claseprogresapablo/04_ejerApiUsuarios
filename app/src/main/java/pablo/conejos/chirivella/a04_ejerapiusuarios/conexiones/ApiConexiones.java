package pablo.conejos.chirivella.a04_ejerapiusuarios.conexiones;

import java.util.ArrayList;

import pablo.conejos.chirivella.a04_ejerapiusuarios.modelos.General;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiConexiones {

    @GET("/api/users")
    Call<General> getUsuarios();
}
