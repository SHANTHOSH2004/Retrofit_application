package com.example.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;

    RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView2);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()) // Required for JSON parsing
                .build();
        service = retrofit.create(RetrofitService.class);  // ✅ Correct


    }

    public void getData(View view) {
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("");
        service.getPosts().enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                List<Example> Example=response.body();
                for (Example e:Example){
                    textView.append(e.getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {

            }
        });

    }

    public void postData(View view) {
        progressBar.setVisibility(View.VISIBLE);

        PostModel pm=new PostModel("Aasssshjsbhj","santhispss",2);
        service.sentData(pm).enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                progressBar.setVisibility(View.INVISIBLE);
                textView.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

            }
        });
    }
}