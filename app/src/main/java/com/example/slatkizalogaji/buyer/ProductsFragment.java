package com.example.slatkizalogaji.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.slatkizalogaji.R;

public class ProductsFragment extends Fragment {

    private RadioGroup radioGroup;
    private ListView listViewProducts;

    public ProductsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        radioGroup = view.findViewById(R.id.radioGroup);
        listViewProducts = view.findViewById(R.id.listViewProducts);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.list_item_product, R.id.textViewProductName, getSampleProductList());
        listViewProducts.setAdapter(adapter);

        listViewProducts.setOnItemClickListener((parent, view1, position, id) -> {
            String productName = (String) parent.getItemAtPosition(position);
            String imageUrl = "URL slike proizvoda";
            showProductDetails(imageUrl, productName);
        });



        radioGroup.setOnCheckedChangeListener((group, checkedId) -> refreshProductList());

        return view;
    }

    private void showProductDetails(String imageUrl, String productName) {

        ProductDetailFragment detailFragment = new ProductDetailFragment();


        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", imageUrl);
        bundle.putString("productName", productName);
        detailFragment.setArguments(bundle);


        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void refreshProductList() {
    }

    private String[] getSampleProductList() {
        return new String[]{"Proizvod 1", "Proizvod 2", "Proizvod 3", "Proizvod 4", "Proizvod 5"};
    }
}
