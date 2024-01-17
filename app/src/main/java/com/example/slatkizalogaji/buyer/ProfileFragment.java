package com.example.slatkizalogaji.buyer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.slatkizalogaji.R;
import com.example.slatkizalogaji.models.CartItem;
import com.example.slatkizalogaji.models.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private EditText editTextFirstName, editTextLastName, editTextAddress,
            editTextMobilePhone, editTextUsername, editTextPassword, editTextNewPassword;
    private Button btnUpdate;

    private User currentUser;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editTextFirstName = view.findViewById(R.id.editTextFirstName);
        editTextLastName = view.findViewById(R.id.editTextLastName);
        editTextAddress = view.findViewById(R.id.editTextAddress);
        editTextMobilePhone = view.findViewById(R.id.editTextMobilePhone);
        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        editTextNewPassword = view.findViewById(R.id.editTextNewPassword);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        currentUser = loadCurrentUser();

        if (currentUser != null) {
            editTextFirstName.setText(currentUser.getName());
            editTextLastName.setText(currentUser.getSurname());
            editTextAddress.setText(currentUser.getAddress());
            editTextMobilePhone.setText(currentUser.getPhone());
            editTextUsername.setText(currentUser.getUsername());
            editTextPassword.setText(currentUser.getPassword());
        }

        btnUpdate.setOnClickListener(v -> updateProfileData());

        return view;
    }

    private void updateProfileData() {
        if (currentUser != null) {
            currentUser.setName(editTextFirstName.getText().toString());
            currentUser.setSurname(editTextLastName.getText().toString());
            currentUser.setAddress(editTextAddress.getText().toString());
            currentUser.setPhone(editTextMobilePhone.getText().toString());
            currentUser.setUsername(editTextUsername.getText().toString());
            currentUser.setPassword(editTextNewPassword.getText().toString());
            saveCurrentUser(currentUser);
        }
    }

    private User loadCurrentUser() {
        String json = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("currentUser", null);
        Type type = new TypeToken<User>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    private void saveCurrentUser(User user) {
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("currentUser", new Gson().toJson(user));
        editor.apply();
    }
}
