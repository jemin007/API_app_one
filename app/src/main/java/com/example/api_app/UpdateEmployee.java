package com.example.api_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.api_app.API.EmployeeAPI;
import com.example.api_app.model.Employee;
import com.example.api_app.model.EmployeeCUD;
import com.example.api_app.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.api_app.url.URL.createInstance;

public class UpdateEmployee extends AppCompatActivity {

    private EditText etEmpNo_Update, etEmpNo_UpdateTwo, etEmpSal_Update, etEmpAge_Update;
    private Button btnSearchEmp_Update, btnUpdateEmp_Update, btnDeleteEmp_Update;

//    Retrofit retrofit;
//    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        etEmpNo_Update = findViewById(R.id.etEmpNo_Update);
        etEmpNo_UpdateTwo = findViewById(R.id.etEmpNo_UpdateTwo);
        etEmpSal_Update = findViewById(R.id.etEmpSal_Update);
        etEmpAge_Update = findViewById(R.id.etEmpAge_Update);
        btnSearchEmp_Update = findViewById(R.id.btnSearchEmp_Update);
        btnDeleteEmp_Update= findViewById(R.id.btnDeleteEmp_Update);
        btnUpdateEmp_Update = findViewById(R.id.btnUpdateEmp_Update);

        btnSearchEmp_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        btnUpdateEmp_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });

        btnDeleteEmp_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });

    }


    private void loadData(){
        createInstance();
        EmployeeAPI employeeAPI = URL.createInstance().create(EmployeeAPI.class);
        Call<Employee> listCall = employeeAPI.getEmployeeID(Integer.parseInt(etEmpNo_Update.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etEmpNo_UpdateTwo.setText(response.body().getEmployee_name());
                etEmpSal_Update.setText(Float.toString(response.body().getEmployee_salary()));
                etEmpAge_Update.setText(Integer.toString(response.body().getEmployee_age()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateEmployee.this, "Error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateEmployee(){
        createInstance();
        EmployeeAPI employeeAPI = URL.createInstance().create(EmployeeAPI.class);
        EmployeeCUD employee = new EmployeeCUD(
                etEmpNo_UpdateTwo.getText().toString(),
                Float.parseFloat(etEmpSal_Update.getText().toString()),
                Integer.parseInt(etEmpAge_Update.getText().toString())
        );

        Call <Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etEmpNo_Update.getText().toString()),employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateEmployee.this, "Suceesfully Updated!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateEmployee.this, "Error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteEmployee(){
        createInstance();
        EmployeeAPI employeeAPI = URL.createInstance().create(EmployeeAPI.class);
        Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(etEmpNo_Update.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateEmployee.this, "Succesfully Deleted!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateEmployee.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
