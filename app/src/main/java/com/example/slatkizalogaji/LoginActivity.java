package com.example.slatkizalogaji;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.slatkizalogaji.buyer.BuyerFragment;
import com.example.slatkizalogaji.models.Product;
import com.example.slatkizalogaji.models.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    private List<User> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        User buyer1 = new User("dusan", "dusan","dusanovic","dusanova","12345", "dusan","buyer");
        User buyer2 = new User("pera", "pera","perovic","peranova","7649", "pera","buyer");

        personList.add(buyer1);
        personList.add(buyer2);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(view -> handleLogin());
    }

    private void handleLogin() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        User foundUser = null;

        for(User user : personList) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                foundUser = user;
                break;
            }
        }

        if (foundUser != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("username", username);
            saveUser(foundUser);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Neispravni podaci.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUser(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString("currentUser", json);
        editor.apply();
    }

}
