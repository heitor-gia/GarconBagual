package com.heitor.garconbagual.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.heitor.garconbagual.R;
import com.heitor.garconbagual.dao.ProdutoDAO;
import com.heitor.garconbagual.model.Produto;

public class CadastroProdutosActivity extends MyActvity {

    Button btSalvar;
    Button btDescartar;
    EditText etNome;
    EditText etPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);

        btSalvar = (Button) findViewById(R.id.btSave);
        btDescartar = (Button) findViewById(R.id.btDescartar);
        etNome = (EditText) findViewById(R.id.etProd);
        etPreco = (EditText) findViewById(R.id.etPrice);
        if(getIntent().getExtras()==null) {
            btSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Produto produto = new Produto();
                    produto.setNome(etNome.getText().toString());
                    produto.setPreco(Double.parseDouble(etPreco.getText().toString()));
                    AlertDialog.Builder dlg = new AlertDialog.Builder(CadastroProdutosActivity.this);
                    dlg.setMessage("Tem certeza de que quer cadastrar esse produto?");
                    dlg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ProdutoDAO pd = new ProdutoDAO(CadastroProdutosActivity.this);
                            pd.save(produto);
                            finish();
                        }
                    });
                    dlg.setNegativeButton("Não", null);
                    dlg.show();


                }
            });


        }else{
            etNome.setText(getIntent().getStringExtra("NOME"));
            etPreco.setText(getIntent().getDoubleExtra("PRECO", 0) + "");
            btSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Produto produto = new Produto();
                    produto.setNome(etNome.getText().toString());
                    produto.setPreco(Double.parseDouble(etPreco.getText().toString()));
                    produto.setId(getIntent().getIntExtra("ID", 0));
                    AlertDialog.Builder dlg = new AlertDialog.Builder(CadastroProdutosActivity.this);
                    dlg.setMessage("Tem certeza de que quer editar esse produto?");
                    dlg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ProdutoDAO pd = new ProdutoDAO(CadastroProdutosActivity.this);
                            pd.save(produto);
                            finish();
                        }
                    });
                    dlg.setNegativeButton("Não", null);
                    dlg.show();


                }

            });
        }

        btDescartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
