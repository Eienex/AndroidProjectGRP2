package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
    }

    public void SubmitRegistration(View v) {
        //Gets values
        EditText userName = findViewById(R.id.userName2);
        EditText password = findViewById(R.id.password2);
        EditText email = findViewById(R.id.email2);
        EditText address = findViewById(R.id.address2);
        EditText city = findViewById(R.id.city2);
        EditText state = findViewById(R.id.state2);
        EditText zip = findViewById(R.id.zipCode2);

        String userNameText = userName.getText().toString();


        //Shows that values have been retrieved
        Toast.makeText(getApplicationContext(), "Welcome : " + userName, Toast.LENGTH_SHORT).show();
    }
}
