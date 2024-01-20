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
import com.example.slatkizalogaji.models.Promotion;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class BuyerFragment extends Fragment {

    private final Promotion[] promotions = {
            new Promotion("Požarevačka čokolada", "Uživajte u najboljoj čokoladi iz Požarevca!", R.drawable.cake1),
            new Promotion("Torta od vanile", "Probajte požarevačku vanilu!", R.drawable.cake2),
            new Promotion("Rafaelo kuglice", "Fantasticne kuglice po najboljoj ceni!", R.drawable.slika2)
    };


    private ViewPager viewPager;
    private List<Fragment> promotionFragments;
    private int currentPage = 0;
    private final int DELAY_TIME = 3000;
    private Handler handler;

    public BuyerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        setupPromotions();

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

        for (Promotion promotion : promotions) {
            promotionFragments.add(createPromotionFragment(promotion));
        }
    }

    private PromotionFragment createPromotionFragment(Promotion promotion) {
        PromotionFragment promotionFragment = new PromotionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", promotion.getTitle());
        bundle.putString("description", promotion.getDescription());
        bundle.putInt("imageResource", promotion.getImageResourceId());
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
        handler.removeCallbacks(autoScrollRunnable);
    }
}