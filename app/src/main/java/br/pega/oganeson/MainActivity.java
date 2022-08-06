package br.pega.oganeson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
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
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                }
                            });
                }
            });

        } else {

        }
    }
}