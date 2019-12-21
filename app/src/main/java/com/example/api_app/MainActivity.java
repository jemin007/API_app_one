package com.example.api_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnShowEmp, btnRegisterEmp, btnUpdateEmp, btnSearchEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowEmp = findViewById(R.id.btnShowEmp);
        btnRegisterEmp = findViewById(R.id.btnRegisterEmp);
        btnSearchEmp = findViewById(R.id.btnSearchEmp);
        btnUpdateEmp = findViewById(R.id.btnUpdateEmp);

        btnShowEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowEmployee.class);
                startActivity(intent);
            }
        });

        btnRegisterEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterEmployee.class);
                startActivity(intent);
            }
        });

        btnUpdateEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateEmployee.class);
                startActivity(intent);
            }
        });

        btnSearchEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchEmployee.class);
                startActivity(intent);
            }
        });
    }
}
