package com.example.slatkizalogaji.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.slatkizalogaji.R;
import com.example.slatkizalogaji.models.CartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItemList;
    private OnCartItemChangeListener listener;

    public CartAdapter(List<CartItem> cartItemList, OnCartItemChangeListener listener) {
        this.cartItemList = cartItemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.bind(cartItem);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public interface OnCartItemChangeListener {
        void onQuantityChanged();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewProductName;
        private TextView textViewQuantity;
        private TextView textViewPrice;
        private Button btnIncreaseQuantity;
        private Button btnDecreaseQuantity;
        private ImageView imageView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            btnIncreaseQuantity = itemView.findViewById(R.id.btnIncreaseQuantity);
            btnDecreaseQuantity = itemView.findViewById(R.id.btnDecreaseQuantity);
            imageView = itemView.findViewById(R.id.imageViewProduct);




            btnIncreaseQuantity.setOnClickListener(v -> {

                CartItem cartItem = cartItemList.get(getAdapterPosition());
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                notifyDataSetChanged();
                listener.onQuantityChanged();
            });

            btnDecreaseQuantity.setOnClickListener(v -> {
                CartItem cartItem = cartItemList.get(getAdapterPosition());
                if (cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    notifyDataSetChanged();
                    listener.onQuantityChanged();
                }
            });
        }

        public void bind(CartItem cartItem) {
            textViewProductName.setText(cartItem.getProduct().getName());
            textViewQuantity.setText(String.valueOf(cartItem.getQuantity()));
            textViewPrice.setText(String.format("%.2f", cartItem.getPrice()) + "RSD");
            Glide.with(itemView.getContext()).load(cartItem.getProduct().getImageResource()).into(imageView);
        }
    }
}
