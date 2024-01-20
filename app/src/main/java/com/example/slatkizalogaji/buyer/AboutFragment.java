package com.example.slatkizalogaji.buyer;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.widget.TextView;

import com.example.slatkizalogaji.R;

public class AboutFragment extends Fragment {

    private TextView textViewDescription, textViewAddress;

    public AboutFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        textViewDescription = view.findViewById(R.id.textViewDescription);
        textViewAddress = view.findViewById(R.id.textViewAddress);


        textViewDescription.setText("Mi smo SLATKI ZALOGAJ! Najbolja (i jedina) poslasticarnica u Velikom Gradistu");
        textViewAddress.setText("Lokacija: Ulica vlaha 133");

        return view;
    }
}