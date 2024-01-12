package com.example.slatkizalogaji.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.slatkizalogaji.R;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileFragment extends Fragment {

    private EditText editTextFirstName, editTextLastName, editTextAddress,
            editTextMobilePhone, editTextUsername, editTextPassword, editTextNewPassword;
    private Button btnUpdate;

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

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfileData();
            }
        });

        return view;
    }

    private void updateProfileData() {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String address = editTextAddress.getText().toString();
        String mobilePhone = editTextMobilePhone.getText().toString();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String newPassword = editTextNewPassword.getText().toString();
    }
}