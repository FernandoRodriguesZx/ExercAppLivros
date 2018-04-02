package com.example.opet.exercapplivros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {

    private FirebaseAuth mAuth;
    private EditText editLogin;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editSenha);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser currentUser){
        if(currentUser != null)
            callActivity(DashboardActivity.class);
    }

    public void signIn(View v) {
        String email = editLogin.getText().toString();
        String senha = editSenha.getText().toString();
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(MainActivity.this, "Falha na autenticação.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void signUp(View V){
        callActivity(CadastroLoginActivity.class);
    }

    private void callActivity(Class newActivity) {
        Intent newIntent = new Intent(MainActivity.this,newActivity);
        startActivity(newIntent);
        finish();
    }
}
