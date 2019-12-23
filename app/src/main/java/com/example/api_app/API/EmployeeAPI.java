package com.example.api_app.API;

import com.example.api_app.model.Employee;
import com.example.api_app.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeAPI {

    //Get all the employees
    @GET("employees")
    Call<List<Employee>> getAllEmployees();


    //Get employee on basis of EmpID
    @GET("employee/{empID}")
    Call<Employee> getEmployeeID(@Path("empID") int empID);

    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD employeeCUD);

    //Update employee based on ID
    @PUT("update/{empID}")
    Call<Void> updateEmployee(@Path("empID") int empId, @Body EmployeeCUD emp);

    //Delete based on empId
    @DELETE("delete/{empID}")
    Call<Void> deleteEmployee(@Path("empID") int empId);

}
