package br.pega.oganeson.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.pega.oganeson.R;
import br.pega.oganeson.models.Product;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>{

    public interface OnItemClickListener {
        void onItemClick(Product item);
    }

    private final ArrayList<Product> data;
    private final OnItemClickListener listener;

    public ProductListAdapter(ArrayList<Product> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getSku().setText(data.get(position).getSku());
        viewHolder.getName().setText(data.get(position).getName());

        String imageString = data.get(position).getThumbnailSmall();
        byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        viewHolder.getImage().setImageBitmap(decodedByte);

        viewHolder.bind(data.get(position), listener);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item, viewGroup, false);

        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView sku;
        private final TextView name;
        private final ImageView image;

        public ViewHolder(View view) {
            super(view);
            sku = view.findViewById(R.id.sku);
            name = view.findViewById(R.id.name);
            image = view.findViewById(R.id.image);
        }

        public TextView getSku() {
            return sku;
        }

        public TextView getName() {
            return name;
        }

        public ImageView getImage() {
            return image;
        }

        public void bind(final Product item, final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}
