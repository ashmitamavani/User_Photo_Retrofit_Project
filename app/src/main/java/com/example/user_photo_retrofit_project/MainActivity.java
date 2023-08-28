package com.example.user_photo_retrofit_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Recyclerview_Adapter adapter;
    List<ModelClass>modelClassList=new ArrayList<>();
    ArrayList<Photos_Model_class>ProductList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter=new Recyclerview_Adapter(MainActivity.this,ProductList);
        recyclerView.setAdapter(adapter);

        Instance_class.CallAPI().showProduct().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                Log.d("TTT", "onResponse: "+response.body().get(0).getTitle().toString());

                modelClassList=response.body();
                for (int i=0;i<modelClassList.size();i++)
                {
                    String id= String.valueOf(response.body().get(i).getId());
                    String title=response.body().get(i).getTitle();
                    String url=response.body().get(i).getUrl();
                    String thumbnailUrl=response.body().get(i).getThumbnailUrl();
                    Photos_Model_class model=new Photos_Model_class(id,title,url,thumbnailUrl);
                    ProductList.add(model);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {
                Log.d("TTT", "onFailure: Error"+t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}