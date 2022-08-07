package br.pega.oganeson;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import br.pega.oganeson.adapters.ProductListAdapter;
import br.pega.oganeson.models.Product;

public class ProductList extends AppCompatActivity {

    private ArrayList<Product> data;
    private ProductListAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        data = new ArrayList<>();

        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            setContentView(R.layout.activity_product_list);

            RecyclerView list = findViewById(R.id.list);
            productListAdapter = new ProductListAdapter(data, new ProductListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Product item) {
                    Log.e("KIK", item.getName());
                }
            });
            list.setAdapter(productListAdapter);

            FirebaseFirestore.getInstance()
                    .collection("products")
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        if (queryDocumentSnapshots.size() > 0){
                            for(DocumentSnapshot item: queryDocumentSnapshots.getDocuments()){
                                SetProduct(item);
                            }
                        }
                    });


        }
    }

    private void SetProduct(DocumentSnapshot item){
        Product product = new Product();
        product.setEnabled(true);
        product.setFirebaseId(item.getId());
        product.setSku(item.getData().get("sku").toString());
        product.setName(item.getData().get("name").toString());

        FirebaseFirestore.getInstance()
                .collection("products")
                .document(item.getId())
                .collection("thumbnail")
                .document("image")
                .get()
                .addOnSuccessListener(snap ->{
                    product.setThumbnailSmall(snap.getData().get("data").toString());
                    data.add(product);
                    productListAdapter.notifyDataSetChanged();
                });
    }
}