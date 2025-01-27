package dev.tomco.a25a_10221_retrofit.interfaces;

import java.util.List;

import dev.tomco.a25a_10221_retrofit.model.Item;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GenericService {
    @GET("objects")
    Call<ResponseBody> getAllObjects();

    @GET("objects/{itemID}")
    Call<ResponseBody> getSingleObject(@Path("itemID") String itemId);

    @GET("objects")
    Call<ResponseBody> getPluralObject(@Query("id") List<String> ids);

    @POST("objects")
    Call<Void> createItem(@Body Item item);

}
