package com.example.slatkizalogaji.buyer;
// src/com/example/slatkizalogaji/buyer/BuyerActivity.java

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slatkizalogaji.R;

import androidx.fragment.app.Fragment;

public class BuyerFragment extends Fragment {

    public BuyerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buyer, container, false);
    }
}