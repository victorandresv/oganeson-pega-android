package br.pega.oganeson;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.pega.oganeson.models.Product;

public class ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Product product= new Product();
        product.setFirebaseId(getIntent().getStringExtra("FirebaseId"));
        product.setEnabled(getIntent().getBooleanExtra("Enabled", false));
        product.setSku(getIntent().getStringExtra("Sku"));
        product.setName(getIntent().getStringExtra("Name"));

        EditText sku = findViewById(R.id.sku);
        EditText name = findViewById(R.id.name);
        SwitchCompat enabled = findViewById(R.id.enabled);

        sku.setText(product.getSku());
        name.setText(product.getName());
        enabled.setChecked(product.getEnabled());

        sku.setEnabled(product.getFirebaseId().isEmpty());

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            Map<String, Object> data = new HashMap<>();
            data.put("Enabled", enabled.isChecked());
            data.put("Sku", sku.getText().toString());
            data.put("Name", name.getText().toString());
            if (product.getFirebaseId().isEmpty()){
                FirebaseFirestore
                        .getInstance()
                        .collection("products")
                        .document()
                        .set(data)
                        .addOnSuccessListener(unused -> finish());
            } else {
                FirebaseFirestore
                        .getInstance()
                        .collection("products")
                        .document(product.getFirebaseId())
                        .set(data)
                        .addOnSuccessListener(unused -> finish());
            }
        });

    }
}