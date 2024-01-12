package com.example.slatkizalogaji.buyer;
// src/com/example/slatkizalogaji/buyer/BuyerActivity.java

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slatkizalogaji.R;
import com.example.slatkizalogaji.adapters.PromotionPagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class BuyerFragment extends Fragment {

    private ViewPager viewPager;
    private List<Fragment> promotionFragments;
    private int currentPage = 0;
    private final int DELAY_TIME = 3000; // Vreme u milisekundama između svake promene slike
    private Handler handler;

    public BuyerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        setupPromotions();

        // Postavite ViewPager adapter
        PromotionPagerAdapter pagerAdapter = new PromotionPagerAdapter(getChildFragmentManager(), promotionFragments);
        viewPager.setAdapter(pagerAdapter);

        handler = new Handler(Looper.getMainLooper());
        startAutoScroll();

        return view;
    }

    private void startAutoScroll() {
        handler.postDelayed(autoScrollRunnable, DELAY_TIME);
    }

    private void setupPromotions() {
        promotionFragments = new ArrayList<>();

        // Dodajte fragmente za svaku promociju
        promotionFragments.add(createPromotionFragment("Promocija 1", "Ovo je opis promocije 1.", R.drawable.cake1));
        promotionFragments.add(createPromotionFragment("Promocija 2", "Ovo je opis promocije 2.", R.drawable.cake2));
        promotionFragments.add(createPromotionFragment("Promocija 3", "Ovo je opis promocije 3.", R.drawable.cake3));

        // Možete popuniti podatke za svaku promociju u odgovarajućim fragmentima
        // Na primer, koristeći Bundle kako biste preneli podatke između BuyerFragment i PromotionFragment
    }

    private PromotionFragment createPromotionFragment(String title, String description, int imageResource) {
        PromotionFragment promotionFragment = new PromotionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("description", description);
        bundle.putInt("imageResource", imageResource);
        promotionFragment.setArguments(bundle);
        return promotionFragment;
    }

    private final Runnable autoScrollRunnable = new Runnable() {
        @Override
        public void run() {
            if (currentPage == promotionFragments.size()) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
            handler.postDelayed(this, DELAY_TIME);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Oslobodi Handler kada fragment bude uništen
        handler.removeCallbacks(autoScrollRunnable);
    }
}