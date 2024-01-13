package com.example.slatkizalogaji.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slatkizalogaji.R;
import com.example.slatkizalogaji.adapters.CartAdapter;
import com.example.slatkizalogaji.models.CartItem;

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

        });


        updateTotalPrice();

        return view;
    }

    private List<CartItem> getDefaultCartItems() {
        List<CartItem> defaultCartItems = new ArrayList<>();


        defaultCartItems.add(new CartItem("Proizvod 1", 2, 10.99));
        defaultCartItems.add(new CartItem("Proizvod 2", 1, 15.99));
        defaultCartItems.add(new CartItem("Proizvod 3", 3, 7.99));

        return defaultCartItems;
    }

    private void updateTotalPrice() {

        double total = 0.0;
        for (CartItem cartItem : cartItemList) {
            total += (cartItem.getPrice() * cartItem.getQuantity());
        }


        textViewTotalPrice.setText("Ukupna cena: $" + String.format("%.2f", total));
    }
}
