package com.example.api_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_app.API.EmployeeAPI;
import com.example.api_app.model.Employee;
import com.example.api_app.url.URL;

import javax.xml.validation.Validator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployee extends AppCompatActivity {

    private EditText etEmpNo;
    private Button btnSearchEmp_Search;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        etEmpNo = findViewById(R.id.etEmpNo);
        btnSearchEmp_Search = findViewById(R.id.btnSearchEmp_Search);
        tvData = findViewById(R.id.tvData);

        btnSearchEmp_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    loadData();

            }
        });

    }
    private void loadData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

        Call<Employee> listCall = employeeAPI.getEmployeeID(Integer.parseInt(etEmpNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchEmployee.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                String content = "";
                content += " ID: "+ response.body().getId() + "\n";
                content += " Name: "+ response.body().getEmployee_name() + "\n";
                content += " Age: "+ response.body().getEmployee_age() + "\n";
                content += " Salary: "+ response.body().getEmployee_salary() + "\n";

                tvData.setText(content);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SearchEmployee.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
