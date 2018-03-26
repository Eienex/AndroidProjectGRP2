package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;
    public Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }

    public void Submit(View v){
        //Gets values
        EditText e = findViewById(R.id.editText);
        String test1 = e.getText().toString();
        EditText f = findViewById(R.id.editText2);
        String test2 = f.getText().toString();

        //Shows that values have been retrieved
        try {
            customer = AppDatabase.getAppDatabase(this)
                    .customerDao()
                    .getCustomerInfo(test1, test2);


            if (customer == null) {
                Toast.makeText(getApplicationContext(), "Username does not exist", Toast.LENGTH_SHORT).show();
            } else {
                //Mark the user logged in
                SharedPreferences.Editor editor = sharedPreferences.edit();
                customer = AppDatabase.getAppDatabase(this).customerDao().getCustomerByUsername(customer.getCustUsername());

                editor.putString("LoginStatus", "Logged In");
                editor.putString("UserRole", customer.getCustType());
                editor.commit();
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "Welcome back " + customer.getCustFName() + "!", Toast.LENGTH_SHORT).show();

            }
        }
        catch(Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void RegUser(View v){
        startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.Register"));
    }

}
