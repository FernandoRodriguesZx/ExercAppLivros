package com.example.opet.exercapplivros;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CadastrarLivroActivity extends Activity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private List<LivroLista> lista;
    private EditText editTitulo;
    private EditText editEdicao;
    private EditText editValor;
    private TextView textLista;
    private TextView textTotal;
    private CheckBox editFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_lista);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference(mAuth.getCurrentUser().getUid());
        lista = new ArrayList<>();

        editTitulo = findViewById(R.id.editTituloLivro);
        editEdicao = findViewById(R.id.editEdicaoLivro);
        editValor = findViewById(R.id.editValorLivro);
        textLista = findViewById(R.id.textLista);
        textTotal = findViewById(R.id.textTotal);
        editFavorito = findViewById(R.id.editFavorito);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<LivroLista>> listGeneric = new GenericTypeIndicator<List<LivroLista>>() {};
                List<LivroLista> itens = dataSnapshot.getValue(listGeneric);
                String data = "";
                double valor_total = 0;
                if(itens != null) {
                    for (LivroLista item : itens) {
                        data += "Livro: " + item.getTitulo() + " Edicao: " + item.getEdicao() + " Valor Unitário: " + item.getValor_unitário() + "\n";
                        valor_total += item.getEdicao() * item.getValor_unitário();
                    }
                    textLista.setText("Lista de livros: \n"+data);
                    textTotal.setText("Valor total: " + String.valueOf(valor_total));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void salvarLivroLista(View view){
        String titulo = editTitulo.getText().toString();
        double edicao = Double.parseDouble(editEdicao.getText().toString());
        double valor = Double.parseDouble(editValor.getText().toString());

        lista.add(new LivroLista(titulo,edicao,valor));
        Toast.makeText(this, "Livro inserido na lista!", Toast.LENGTH_SHORT).show();
    }

    public void salvarLista(View v){
        mDatabase.setValue(lista);
        Toast.makeText(CadastrarLivroActivity.this,"Cadastro realizado com sucesso!",Toast.LENGTH_SHORT).show();

    }
}
