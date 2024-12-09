package dev.tomco.a25a_10221_retrofit.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import dev.tomco.a25a_10221_retrofit.interfaces.GenericCallback;
import dev.tomco.a25a_10221_retrofit.interfaces.GenericService;
import dev.tomco.a25a_10221_retrofit.model.Item;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenericController {

    private GenericCallback genericCallback;
    private Retrofit retrofit;
    private GenericService genericService;

    Callback<ResponseBody> responseBodyCallback =
            new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            String data = response.body().string();
                            genericCallback.success(data);
                        } catch (IOException e) {
                            genericCallback.error(response.errorBody().toString());
                            throw new RuntimeException(e);
                        }
                    } else {
                        genericCallback.error(response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                    genericCallback.error(throwable.getMessage());
                }
            };
    Callback<Void> voidCallback =
            new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        genericCallback.success(response.message());
                    } else {
                        genericCallback.error(response.message());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable throwable) {
                    genericCallback.error(throwable.getMessage());
                }
            };

    public GenericController(String baseUrl, GenericCallback genericCallback) {
        this.genericCallback = genericCallback;
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        genericService = retrofit.create(GenericService.class);
    }


    public void getAllObjectsImpl() {
        Call<ResponseBody> call = genericService.getAllObjects();
        call.enqueue(responseBodyCallback);

    }

    public void getSingleObjectImpl() {
        Call<ResponseBody> call = genericService.getSingleObject("4");
        call.enqueue(responseBodyCallback);

    }

    public void getPluralObjectImpl() {
        Call<ResponseBody> call = genericService.getPluralObject(Arrays.asList("3", "5", "10"));
        call.enqueue(responseBodyCallback);

    }

    public void createItemImpl() {
        Map<String, Object> data = new HashMap<>();
        data.put("year", 2019);
        data.put("price", 1849.99);
        data.put("CPU model", "Intel Core i9");
        data.put("Hard disk size", "1 TB");
        Item item = new Item();
        item.setName("Apple MacBook Pro 16")
                .setData(data);

        Call<Void> call = genericService.createItem(item);
        call.enqueue(voidCallback);

    }


}
