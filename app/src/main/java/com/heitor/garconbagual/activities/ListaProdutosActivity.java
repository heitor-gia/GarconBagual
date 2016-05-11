package com.heitor.garconbagual.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.heitor.garconbagual.R;
import com.heitor.garconbagual.adapters.ProdutoAdapter;
import com.heitor.garconbagual.dao.ProdutoDAO;
import com.heitor.garconbagual.model.Produto;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaProdutosActivity extends MyActvity {

    private ListView lv;
    private ProdutoDAO pdao = new ProdutoDAO(this);
    private ProdutoAdapter produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        lv = (ListView) findViewById(R.id.listaprod);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Produto> pdt = pdao.getAll();
        produtos = new ProdutoAdapter(this,android.R.layout.simple_list_item_1,pdt);


        lv.setAdapter(produtos);
        registerForContextMenu(lv);
    }

    public final String EDITAR = "Editar";
    public final String EXCLUIR = "Excluir";


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista,menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.addProdutoItem:
                Intent intent = new Intent(this,CadastroProdutosActivity.class);
                startActivity(intent);
                return true;
            default:
                super.onMenuItemSelected(featureId,item);
                return true;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Escolha a opção");
        menu.add(0,1,0,EXCLUIR);
        menu.add(0,2,1,EDITAR);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getTitle().equals(EXCLUIR)){
            final Produto produto = produtos.getItem(position);
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Deseja exluir este produto");
            dlg.setNegativeButton("Não", null);
            dlg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pdao.delete(produto);
                    finish();
                }
            });
            dlg.show();
        }
        if(item.getTitle().equals(EDITAR)){
            final Produto produto = produtos.getItem(position);
            Intent intent = new Intent(this,CadastroProdutosActivity.class);
            intent.putExtra("ID",produto.getId());
            intent.putExtra("NOME",produto.getNome());
            intent.putExtra("PRECO",produto.getPreco());
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }
}
