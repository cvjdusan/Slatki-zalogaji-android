package com.example.slatkizalogaji.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

public class PromotionPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> promotionFragments;

    public PromotionPagerAdapter(FragmentManager fm, List<Fragment> promotionFragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.promotionFragments = promotionFragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return promotionFragments.get(position);
    }

    @Override
    public int getCount() {
        return promotionFragments.size();
    }
}
