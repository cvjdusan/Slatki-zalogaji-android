package com.example.slatkizalogaji;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageButton;

import com.example.slatkizalogaji.buyer.AboutFragment;
import com.example.slatkizalogaji.buyer.BuyerFragment;
import com.example.slatkizalogaji.buyer.CartFragment;
import com.example.slatkizalogaji.buyer.NotificationsFragment;
import com.example.slatkizalogaji.buyer.ProductsFragment;
import com.example.slatkizalogaji.buyer.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuAdapter.OnMenuItemClickListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private boolean isDrawerOpen = false;
    private RecyclerView drawerRecyclerView;
    private MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        ImageButton btnOpenMenu = findViewById(R.id.btnOpenMenu);
        btnOpenMenu.setOnClickListener(view -> toggleDrawer());

        drawerRecyclerView = findViewById(R.id.drawerRecyclerView);
        setupDrawerMenu();

        showFragment(new BuyerFragment());
    }

    private void toggleDrawer() {
        if (isDrawerOpen) {
            drawerLayout.closeDrawer(drawerRecyclerView);
        } else {
            drawerLayout.openDrawer(drawerRecyclerView);
        }
        isDrawerOpen = !isDrawerOpen;
    }

    private void setupDrawerMenu() {
        List<String> menuItems = new ArrayList<>();
        menuItems.add("Pocetna");
        menuItems.add("Proizvodi");
        menuItems.add("Korpa");
        menuItems.add("Obavestenja");
        menuItems.add("O nama");
        menuItems.add("Profil");
        menuItems.add("Logout");

        menuAdapter = new MenuAdapter(this, menuItems, this);
        drawerRecyclerView.setAdapter(menuAdapter);
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onMenuItemClick(String item) {
        toggleDrawer();

        switch (item) {
            case "Pocetna":
                showFragment(new BuyerFragment());
                break;
            case "Proizvodi":
                showFragment(new ProductsFragment());
                break;
            case "Korpa":
                showFragment(new CartFragment());
                break;
            case "Obavestenja":
                showFragment(new NotificationsFragment());
                break;
            case "O nama":
                showFragment(new AboutFragment());
                break;
            case "Profil":
                showFragment(new ProfileFragment());
                break;
            case "Logout":
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
