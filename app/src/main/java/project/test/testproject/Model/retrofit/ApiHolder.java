package project.test.testproject.Model.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHolder {

    @GET("Api")
    Call<ResponseBody> getPosts();

}
