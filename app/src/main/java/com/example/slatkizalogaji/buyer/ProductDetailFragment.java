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

import com.bumptech.glide.Glide;
import com.example.slatkizalogaji.R;
import com.example.slatkizalogaji.adapters.CommentAdapter;
import com.example.slatkizalogaji.helpers.ProductHelper;
import com.example.slatkizalogaji.models.Product;

import java.util.List;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.slatkizalogaji.R;
import com.example.slatkizalogaji.helpers.ProductHelper;
import com.example.slatkizalogaji.models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailFragment extends Fragment {

    private List<Product> productList;
    private Product currentProduct;
    private SharedPreferences sharedPreferences;

    public ProductDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        sharedPreferences = requireActivity().getPreferences(getActivity().MODE_PRIVATE);

        productList = ProductHelper.loadProductList(requireActivity());

        Bundle bundle = getArguments();
        if (bundle != null) {
            int productId = bundle.getInt("productId");
            currentProduct = findProductById(productId);

            ImageView imageViewProductDetail = view.findViewById(R.id.imageViewProductDetail);
            TextView textViewProductNameDetail = view.findViewById(R.id.textViewProductNameDetail);
            TextView textViewProductDescription = view.findViewById(R.id.textViewProductDescription);
            TextView textViewProductIngredients = view.findViewById(R.id.textViewProductIngredients);
            TextView textViewProductPrice = view.findViewById(R.id.textViewProductPrice);
            EditText editTextQuantity = view.findViewById(R.id.editTextQuantity);
            Button btnAddToCart = view.findViewById(R.id.btnAddToCart);
            TextView textViewCartMessage = view.findViewById(R.id.textViewCartMessage);

            textViewProductNameDetail.setText(currentProduct.getName());

            Glide.with(requireContext()).load(currentProduct.getImageResource()).into(imageViewProductDetail);
            textViewProductDescription.setText(currentProduct.getDescription());
            textViewProductIngredients.setText(currentProduct.getRecipe());
            textViewProductPrice.setText(currentProduct.getPrice() + " RSD");


            btnAddToCart.setOnClickListener(v -> {
                int quantity = Integer.parseInt(editTextQuantity.getText().toString());
                addToCart(currentProduct, quantity);
                textViewCartMessage.setText("Uspesno dodato: " + quantity + " " + currentProduct.getName());
                textViewCartMessage.setVisibility(View.VISIBLE);
            });


            TextView textViewCommentsHeader = view.findViewById(R.id.textViewCommentsHeader);
            RecyclerView recyclerViewComments = view.findViewById(R.id.recyclerViewComments);
            Button btnAddComment = view.findViewById(R.id.btnAddComment);
            EditText editTextComment = view.findViewById(R.id.editTextComment);

            textViewCommentsHeader.setText("Komentari");

            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
            recyclerViewComments.setLayoutManager(layoutManager);
            CommentAdapter commentAdapter = new CommentAdapter(currentProduct.getComments());
            recyclerViewComments.setAdapter(commentAdapter);

            btnAddComment.setOnClickListener(v -> {
                String comment = editTextComment.getText().toString();
                addComment(currentProduct, comment);
                commentAdapter.notifyDataSetChanged();
            });
        }

        return view;
    }

    private void addToCart(Product product, int quantity) {
        List<Product> cartProducts = loadCartProducts();

        if (cartProducts == null) {
            cartProducts = new ArrayList<>();
        }

        for (int i = 0; i < quantity; i++) {
            cartProducts.add(product);
        }

        saveCartProducts(cartProducts);
    }

    private List<Product> loadCartProducts() {
        String json = sharedPreferences.getString("cartProducts", null);

        if (json == null) {
            return new ArrayList<>();
        }

        Type type = new TypeToken<List<Product>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    private void addComment(Product product, String comment) {
        if (currentProduct.getComments() == null) {
            currentProduct.setComments(new ArrayList<>());
        }
        currentProduct.getComments().add(comment);
        saveProductList(productList);
    }

    private Product findProductById(int productId) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == productId) {
                return productList.get(i);
            }
        }
        return null;
    }

    private void saveProductList(List<Product> productList) {
        Gson gson = new Gson();
        String json = gson.toJson(productList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("productList", json);
        editor.apply();
    }

    private void saveCartProducts(List<Product> cartProducts) {
        Gson gson = new Gson();
        String json = gson.toJson(cartProducts);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cartProducts", json);
        editor.apply();
    }
}
