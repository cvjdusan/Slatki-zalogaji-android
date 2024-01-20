package com.example.slatkizalogaji.buyer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slatkizalogaji.R;
import com.example.slatkizalogaji.adapters.CartAdapter;
import com.example.slatkizalogaji.models.CartItem;
import com.example.slatkizalogaji.models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private RecyclerView recyclerViewCart;
    private TextView textViewTotalPrice;
    private Button btnOrder;

    private List<CartItem> cartItemList;
    private CartAdapter cartAdapter;

    public CartFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerViewCart = view.findViewById(R.id.recyclerViewCart);
        textViewTotalPrice = view.findViewById(R.id.textViewTotalPrice);
        btnOrder = view.findViewById(R.id.btnOrder);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerViewCart.setLayoutManager(layoutManager);


        cartItemList = getDefaultCartItems();
        cartAdapter = new CartAdapter(cartItemList, () -> {
            updateTotalPrice();
        });


        recyclerViewCart.setAdapter(cartAdapter);


        btnOrder.setOnClickListener(v -> {
            if(cartItemList.size() == 0){
                Toast.makeText(getContext(), "Molimo dodajte proizvode u korpu!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Naruceno!", Toast.LENGTH_SHORT).show();
                emptyCart();
                updateView();
            }
        });


        updateTotalPrice();

        return view;
    }

    private void updateView() {
        cartItemList.clear();
        List<CartItem> defaultCartItems = new ArrayList<>();
        cartItemList.addAll(defaultCartItems);
        cartAdapter.notifyDataSetChanged();
        updateTotalPrice();
    }

    private void emptyCart() {
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("cartProducts");
        editor.apply();
    }


    private List<CartItem> loadCartProducts() {
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String json = preferences.getString("cartProducts", null);

        if (json == null) {
            return new ArrayList<>();
        }

        Type type = new TypeToken<List<CartItem>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    private List<CartItem> getDefaultCartItems() {
        List<CartItem> defaultCartItems = loadCartProducts();
//        List<Product> cartProducts = loadCartProducts();
//        for (Product product : cartProducts) {
//            defaultCartItems.add(new CartItem(product.getName(), 1, product.getPrice()));
//        }

        return defaultCartItems;
    }

    private void updateTotalPrice() {

        double total = 0.0;
        for (CartItem cartItem : cartItemList) {
            total += (cartItem.getPrice() * cartItem.getQuantity());
        }


        textViewTotalPrice.setText("Ukupna cena: " + String.format("%.2f", total) + " RSD");
    }
}
