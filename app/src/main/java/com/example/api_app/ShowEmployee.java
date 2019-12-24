package com.example.api_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_app.API.EmployeeAPI;
import com.example.api_app.Recycler.EmployeeAdapter;
import com.example.api_app.model.Employee;
import com.example.api_app.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.api_app.url.URL.createInstance;

public class ShowEmployee extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);

        recyclerView = findViewById(R.id.txtMain);

        createInstance();
        EmployeeAPI employeeAPI = URL.createInstance().create(EmployeeAPI.class);

        Call<List<Employee>> listCall = employeeAPI.getAllEmployees();

        //Asynschronous call

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(ShowEmployee.this, "Error Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Employee> employeeList = response.body();

                EmployeeAdapter employeeAdapter = new EmployeeAdapter(ShowEmployee.this,employeeList);
                recyclerView.setAdapter(employeeAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ShowEmployee.this));


            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Msg","onFailure: " + t.getLocalizedMessage());
                Toast.makeText(ShowEmployee.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
