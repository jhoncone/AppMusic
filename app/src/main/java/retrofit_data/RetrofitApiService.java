package retrofit_data;

import com.example.musics.model.Disco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiService {
    @GET("discos.php")
    Call<List<Disco>> getDiscos();//llamada a la api
}
