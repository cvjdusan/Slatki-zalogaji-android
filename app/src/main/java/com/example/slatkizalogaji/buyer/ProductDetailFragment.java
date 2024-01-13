package com.example.slatkizalogaji.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.example.slatkizalogaji.R;

public class ProductDetailFragment extends Fragment {

    public ProductDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        // Dobavite informacije o proizvodu iz Bundle objekta
        Bundle bundle = getArguments();
        if (bundle != null) {
            String imageUrl = bundle.getString("imageUrl");
            String productName = bundle.getString("productName");

            // Postavite informacije o proizvodu u odgovarajuće elemente na ekranu
            ImageView imageViewProductDetail = view.findViewById(R.id.imageViewProductDetail);
            TextView textViewProductNameDetail = view.findViewById(R.id.textViewProductNameDetail);

            // Postavite sliku i naziv proizvoda
            // Ovde treba implementirati logiku za postavljanje slike (možete koristiti biblioteku za prikazivanje slika)
            // i postavljanje naziva proizvoda
            // Na primer:
            // Glide.with(requireContext()).load(imageUrl).into(imageViewProductDetail);
            textViewProductNameDetail.setText(productName);
        }

        return view;
    }
}
