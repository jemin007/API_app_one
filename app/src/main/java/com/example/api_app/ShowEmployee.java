package com.example.api_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_app.API.EmployeeAPI;
import com.example.api_app.model.Employee;
import com.example.api_app.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowEmployee extends AppCompatActivity {
    TextView txtMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);

        txtMain = findViewById(R.id.txtMain);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

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

                for (Employee emp : employeeList) {
                    String data ="";
                    data += " Name is: " + emp.getEmployee_name() + "\n";
                    data += " Salary is: " + emp.getEmployee_salary() + "\n";
                    data += "---------------------" + "\n";

                    txtMain.append(data);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Msg","onFailure: " + t.getLocalizedMessage());
                Toast.makeText(ShowEmployee.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
