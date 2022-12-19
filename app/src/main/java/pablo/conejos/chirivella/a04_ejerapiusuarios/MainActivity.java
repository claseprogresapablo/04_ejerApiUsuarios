package pablo.conejos.chirivella.a04_ejerapiusuarios;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;


import pablo.conejos.chirivella.a04_ejerapiusuarios.adapters.UsersAdapters;
import pablo.conejos.chirivella.a04_ejerapiusuarios.conexiones.ApiConexiones;
import pablo.conejos.chirivella.a04_ejerapiusuarios.conexiones.RetrofitObject;
import pablo.conejos.chirivella.a04_ejerapiusuarios.databinding.ActivityMainBinding;
import pablo.conejos.chirivella.a04_ejerapiusuarios.modelos.DataItem;
import pablo.conejos.chirivella.a04_ejerapiusuarios.modelos.General;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<DataItem> userList;
    private UsersAdapters adapter;
    private RecyclerView.LayoutManager lm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        userList = new ArrayList<>();

        adapter = new UsersAdapters(userList,this, R.layout.user_view_holder);
        lm = new LinearLayoutManager(this);

        binding.contentMain.contenedor.setAdapter(adapter);
        binding.contentMain.contenedor.setLayoutManager(lm);

        dogetUsers();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void dogetUsers() {

        Retrofit retrofit = RetrofitObject.getConection();
        ApiConexiones api = retrofit.create(ApiConexiones.class); //retrofit crea los metodos necesarios

        Call<General> getUsers= api.getUsuarios();

        getUsers.enqueue(new Callback<General>() { //cuando retorne retorna un arraylist de albumns
            @Override
            public void onResponse(Call<General> call, Response<General> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){//si ha ido bien se ejecutan ciertas cosas
                    General temp = response.body(); // en response.body() estan los obejtos que retorna

                    userList.addAll(temp.getData());
                    adapter.notifyItemRangeInserted(0,userList.size());
                }
            }

            @Override
            public void onFailure(Call<General> call, Throwable t) {//cuando algo falla

                Toast.makeText(MainActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE",t.getLocalizedMessage());

            }
        });    }


}