package com.example.slatkizalogaji.buyer;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.slatkizalogaji.R;
import com.example.slatkizalogaji.helpers.ProductHelper;
import com.example.slatkizalogaji.models.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    private ListView listViewProducts;
    private List<Product> productList;

    public ProductsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        listViewProducts = view.findViewById(R.id.listViewProducts);

        productList = ProductHelper.loadProductList(requireActivity());

      //  if (productList == null) {
            productList = createSampleProductList();
            saveProductList(productList);
       // }

        ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(requireContext(), R.layout.list_item_product, R.id.textViewProductName, productList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textViewProductName = view.findViewById(R.id.textViewProductName);

                Product product = getItem(position);
                if (product != null) {
                    textViewProductName.setText(product.getName());
                }

                return view;
            }
        };

        listViewProducts.setAdapter(adapter);

        listViewProducts.setOnItemClickListener((parent, view1, position, id) -> {
            Product selectedProduct = (Product) parent.getItemAtPosition(position);
            showProductDetails(selectedProduct);
        });

        return view;
    }

    private void showProductDetails(Product product) {
        ProductDetailFragment detailFragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("productId", product.getId());
        detailFragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.container, detailFragment)
                .addToBackStack(null)
                .commit();
    }

    private List<Product> createSampleProductList() {
        List<Product> sampleProducts = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            Product product = new Product(i,"image" + i, "Naziv " + i, "Opis proizvoda " + i, 9.99 * i, "Recept proizvoda " + i);
            sampleProducts.add(product);
        }
        return sampleProducts;
    }

    private void saveProductList(List<Product> productList) {
        SharedPreferences preferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(productList);
        editor.putString("productList", json);
        editor.apply();
    }

    private List<Product> loadProductList() {
        SharedPreferences preferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("productList", null);
        Type type = new TypeToken<List<Product>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
