package com.example.slatkizalogaji.buyer;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
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
    private String selectedProductType = "Cake";
    private RadioGroup radioGroupProductType;



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
                ImageView imageViewProduct = view.findViewById(R.id.imageViewProduct);

                Product product = getItem(position);
                if (product != null) {
                    textViewProductName.setText(product.getName());
                    Glide.with(requireContext())
                            .load(product.getImageResource())
                            .into(imageViewProduct);
                }

                return view;
            }
        };

        listViewProducts.setAdapter(adapter);

        listViewProducts.setOnItemClickListener((parent, view1, position, id) -> {
            Product selectedProduct = (Product) parent.getItemAtPosition(position);
            showProductDetails(selectedProduct);
        });

        radioGroupProductType = view.findViewById(R.id.radioGroup);
        radioGroupProductType.check(R.id.radioButtonCakes);
        updateProductList();

        radioGroupProductType.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioButtonCakes:
                    selectedProductType = "Cake";
                    break;
                case R.id.radioButtonPastries:
                    selectedProductType = "Pastery";
                    break;
            }
            updateProductList();
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
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, R.drawable.slika1, "Coko moko", "Uživajte u savršenoj kombinaciji najkvalitetnije pozarevačke čokolade u svakom zalogaju ovog neodoljivog kolača.", 1244, "čokoladna masa, sveže jaja, mleko, probrano brašno", "Cake"));
        productList.add(new Product(2, R.drawable.slika2, "Rafaelo kuglice", "Kuglice pogodne za sve vrste slavlja i tugovanja", 1750, "beli nugat, sveže jaja, mleko, čokolada, kokos, brašno", "Pastery"));
        productList.add(new Product(3, R.drawable.slika3, "8 mart torta", "Proslavite posebne trenutke sa našom tortom za 8. mart", 1650, "čokoladna krema, sveže jaja, mleko, probrano brašno", "Cake"));
        productList.add(new Product(4, R.drawable.slika4, "Kolacic", "Ovaj kolacic postoji samo kod nas! 1g najfinijeg nicega", 1350, "čokoladna masa, sveže jaja, mleko, probrano brašno", "Pastery"));
        productList.add(new Product(5, R.drawable.slika5, "Kolacina", "I ovo smo bas izmislili samo da vam uzmemo pare!", 1459, "čokoladna masa, sveže jaja, mleko, probrano brašno","Pastery"));
        productList.add(new Product(6, R.drawable.slika6, "Plazma torta", "Da se ne lazemo, ova torta se najlakse pravi!", 1551, "plazma keks, sveže jaja, mleko, čokolada, probrano brašno", "Cake"));
        productList.add(new Product(7, R.drawable.slika7, "Madjarica", "Ako ste iz Vranja ovo je prava torta za vas i vasu rodbinu!", 1244, "čokoladna masa, sveže jaja, mleko, probrano brašno","Cake"));
        productList.add(new Product(8, R.drawable.slika8, "Coko kifla", "Zamislajte da ste u Parizu uz ovaj lazni kroaaasan!", 1752, "beli nugat, sveže jaja, mleko, čokolada, kokos, brašno", "Pastery"));
        productList.add(new Product(9, R.drawable.slika9, "Cheesecake", "Najfiniji kozji sir plus maline iz nase baste.", 1654, "čokoladna krema, sveže jaja, mleko, probrano brašno", "Cake"));
        productList.add(new Product(10, R.drawable.slika10, "Posni kolac", "Da, tacno je. Niko ne voli posnu slavu.", 0, "Posno","Pastery"));
        productList.add(new Product(11, R.drawable.slika11, "Baklava", "Jedino vredno sto je ostalo nakon okupacije", 1453, "čokoladna masa, sveže jaja, mleko, probrano brašno","Pastery"));
        productList.add(new Product(12, R.drawable.slika12, "Vocna torta", "Torta od lubenice, lazemo naravno", 1551, "plazma keks, sveže jaja, mleko, čokolada, probrano brašno","Cake"));
        return productList;
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

    private void updateProductList() {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getType().equals(selectedProductType)) {
                filteredList.add(product);
            }
        }

        ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(requireContext(), R.layout.list_item_product, R.id.textViewProductName, filteredList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textViewProductName = view.findViewById(R.id.textViewProductName);
                ImageView imageViewProduct = view.findViewById(R.id.imageViewProduct);

                Product product = getItem(position);
                if (product != null) {
                    textViewProductName.setText(product.getName());
                    Glide.with(requireContext())
                            .load(product.getImageResource())
                            .into(imageViewProduct);
                }

                return view;
            }
        };

        listViewProducts.setAdapter(adapter);
    }
}
