package dev.tomco.a25a_10221_retrofit;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import dev.tomco.a25a_10221_retrofit.api.GenericController;
import dev.tomco.a25a_10221_retrofit.interfaces.GenericCallback;
import dev.tomco.a25a_10221_retrofit.utilities.Constants;

public class MainActivity extends AppCompatActivity {
    private MaterialButton main_BTN_getAll;
    private MaterialButton main_BTN_getOne;
    private MaterialButton main_BTN_getPlural;
    private MaterialButton main_BTN_post;

    private GenericController genericController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        init();


    }

    private void init() {
        genericController = new GenericController(
                Constants.BASE_URL,
                new GenericCallback() {
                    @Override
                    public void success(String data) {
                        Log.d("Success!", "Data:" + data);
                    }

                    @Override
                    public void error(String error) {
                        Log.d("ERROR!!!", "Error:" + error);
                    }
                });


        main_BTN_getAll.setOnClickListener(v ->  genericController.getAllObjectsImpl() );
        main_BTN_getOne.setOnClickListener(v -> genericController.getSingleObjectImpl() );
        main_BTN_getPlural.setOnClickListener(v -> genericController.getPluralObjectImpl() );
        main_BTN_post.setOnClickListener(v -> genericController.createItemImpl() );
    }

    private void findViews() {
        main_BTN_getAll = findViewById(R.id.main_BTN_getAll);
        main_BTN_getOne = findViewById(R.id.main_BTN_getOne);
        main_BTN_getPlural = findViewById(R.id.main_BTN_getPlural);
        main_BTN_post = findViewById(R.id.main_BTN_post);
    }
}