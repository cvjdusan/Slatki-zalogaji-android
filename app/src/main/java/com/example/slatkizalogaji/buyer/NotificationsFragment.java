package com.example.slatkizalogaji.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.slatkizalogaji.R;

public class NotificationsFragment extends Fragment {

    public NotificationsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        LinearLayout notificationsContainer = view.findViewById(R.id.notificationsContainer);

        addNotification(notificationsContainer, "Nova poruka", "Sadržaj nove poruke.");
        addNotification(notificationsContainer, "Obaveštenje", "Neki važan događaj.");

        return view;
    }

    private void addNotification(LinearLayout container, String title, String content) {
        View notificationView = LayoutInflater.from(getContext()).inflate(R.layout.notification_item, container, false);

        TextView textViewTitle = notificationView.findViewById(R.id.textViewTitle);
        TextView textViewContent = notificationView.findViewById(R.id.textViewContent);

        textViewTitle.setText(title);
        textViewContent.setText(content);

        container.addView(notificationView);
    }
}