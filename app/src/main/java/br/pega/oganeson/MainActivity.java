package br.pega.oganeson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import br.pega.oganeson.helpers.DialogHelper;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = this;

        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            setContentView(R.layout.activity_main);

            EditText email = findViewById(R.id.email);
            EditText password = findViewById(R.id.password);
            findViewById(R.id.btnLogin).setOnClickListener(view -> {
                if(!email.getText().toString().contains("@")){
                    email.setError(getResources().getString(R.string.empty_email));
                } else if(password.getText().toString().isEmpty()){
                    password.setError(getResources().getString(R.string.empty_password));
                } else {
                    FirebaseAuth
                            .getInstance()
                            .signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnFailureListener(e -> DialogHelper.Alert(context, "Error", e.getLocalizedMessage()))
                            .addOnSuccessListener(authResult -> {
                                finish();
                                startActivity(new Intent(context, ProductList.class));
                            });
                }
            });

        } else {
            finish();
            startActivity(new Intent(context, ProductList.class));
        }
    }
}