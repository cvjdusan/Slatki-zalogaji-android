package com.example.slatkizalogaji.buyer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.slatkizalogaji.R;
import com.example.slatkizalogaji.models.Notification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    public NotificationsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        LinearLayout notificationsContainer = view.findViewById(R.id.notificationsContainer);

        // Delete maybe if not needed
        List<Notification> notifications = loadNotifications();
        for(Notification notification: notifications) {
            addNotification(notificationsContainer, notification);
        }

        addNotification(notificationsContainer, new Notification("Vasa narudzbina id 22 je prihvaćena", "", "success"));
        addNotification(notificationsContainer, new Notification("Vasa narudzbina id 10 je odbijena", "", "fail"));


        return view;
    }

    private List<Notification> loadNotifications() {
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String json = preferences.getString("notifications", null);

        if (json == null) {
            return new ArrayList<>();
        }

        Type type = new TypeToken<List<Notification>>() {}.getType();
        return new Gson().fromJson(json, type);
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