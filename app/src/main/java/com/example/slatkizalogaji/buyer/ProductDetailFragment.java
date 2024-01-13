package com.example.slatkizalogaji.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slatkizalogaji.R;

public class ProductDetailFragment extends Fragment {

    public ProductDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);


        Bundle bundle = getArguments();
        if (bundle != null) {
            String imageUrl = bundle.getString("imageUrl");
            String productName = bundle.getString("productName");


            ImageView imageViewProductDetail = view.findViewById(R.id.imageViewProductDetail);
            TextView textViewProductNameDetail = view.findViewById(R.id.textViewProductNameDetail);

            // slika i naziv proizvoda
            // Ovde treba implementirati logiku za postavljanje slike
            // i postavljanje naziva proizvoda
            // Na primer:
            // Glide.with(requireContext()).load(imageUrl).into(imageViewProductDetail);
            textViewProductNameDetail.setText(productName);


            TextView textViewProductDescription = view.findViewById(R.id.textViewProductDescription);
            TextView textViewProductIngredients = view.findViewById(R.id.textViewProductIngredients);
            TextView textViewProductPrice = view.findViewById(R.id.textViewProductPrice);
            EditText editTextQuantity = view.findViewById(R.id.editTextQuantity);
            Button btnAddToCart = view.findViewById(R.id.btnAddToCart);


            textViewProductDescription.setText("Ovo je opis proizvoda.");
            textViewProductIngredients.setText("Sastojci: Brašno, šećer, jaja,...");
            textViewProductPrice.setText("Cena: $10.99");


            btnAddToCart.setOnClickListener(v -> {

                String quantity = editTextQuantity.getText().toString();

            });


            TextView textViewCommentsHeader = view.findViewById(R.id.textViewCommentsHeader);
            RecyclerView recyclerViewComments = view.findViewById(R.id.recyclerViewComments);
            Button btnAddComment = view.findViewById(R.id.btnAddComment);


            textViewCommentsHeader.setText("Komentari");


            btnAddComment.setOnClickListener(v -> {

            });
        }

        return view;
    }
}
