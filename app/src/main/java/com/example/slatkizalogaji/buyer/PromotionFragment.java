package com.example.slatkizalogaji.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.slatkizalogaji.R;

public class PromotionFragment extends Fragment {

    public PromotionFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_promotion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ImageView promotionImage = view.findViewById(R.id.promotionImage);
        TextView promotionTitle = view.findViewById(R.id.promotionTitle);
        TextView promotionDescription = view.findViewById(R.id.promotionDescription);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("title", "");
            String description = bundle.getString("description", "");
            int imageResource = bundle.getInt("imageResource", 0);

            promotionTitle.setText(title);
            promotionDescription.setText(description);
            promotionImage.setImageResource(imageResource);
        }
    }
}
