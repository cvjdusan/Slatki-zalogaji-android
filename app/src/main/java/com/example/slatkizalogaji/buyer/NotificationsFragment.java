package com.example.slatkizalogaji.buyer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.slatkizalogaji.R;
import com.example.slatkizalogaji.models.Notification;

public class NotificationsFragment extends Fragment {

    public NotificationsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        LinearLayout notificationsContainer = view.findViewById(R.id.notificationsContainer);

        addNotification(notificationsContainer, new Notification("Vasa narudzbina id 23, narucena 12.12.2023. je prihvacena", "", "success"));
        addNotification(notificationsContainer, new Notification("Vasa narudzbina id 10, narucena 10.07.2022. je odbijena", "", "fail"));

        return view;
    }

    private void addNotification(LinearLayout container, Notification notification) {
        View notificationView = LayoutInflater.from(getContext()).inflate(R.layout.notification_item, container, false);

        TextView textViewTitle = notificationView.findViewById(R.id.textViewTitle);
        TextView textViewContent = notificationView.findViewById(R.id.textViewContent);
        String type = notification.getType();

        if ("success".equals(type)) {
            notificationView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        } else if ("fail".equals(type)) {
            notificationView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        }

        textViewTitle.setText(notification.getTitle());
        textViewContent.setText(notification.getContent());

        container.addView(notificationView);
    }
}