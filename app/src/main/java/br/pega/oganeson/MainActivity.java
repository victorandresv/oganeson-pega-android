package br.pega.oganeson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

                }
            });

        } else {

        }
    }
}