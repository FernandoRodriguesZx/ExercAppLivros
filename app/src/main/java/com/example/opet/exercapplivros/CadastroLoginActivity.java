package com.example.opet.exercapplivros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroLoginActivity extends Activity {

    private FirebaseAuth mAuth;
    private EditText editLogin;
    private EditText editSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);
        mAuth = FirebaseAuth.getInstance();
        editLogin = findViewById(R.id.editLoginCad);
        editSenha = findViewById(R.id.editSenhaCad);
    }

    public void signUpCad(View v) {
        String email = editLogin.getText().toString();
        String senha = editSenha.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w("ERRORCAD", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastroLoginActivity.this, "Falha na autenticação.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser currentUser){
        if(currentUser != null)
            callActivity(DashboardActivity.class);
        else
            callActivity(CadastroLoginActivity.class);
    }

    private void callActivity(Class newActivity) {
        Intent newIntent = new Intent(CadastroLoginActivity.this,newActivity);
        startActivity(newIntent);
        finish();
    }
}
