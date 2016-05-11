package com.heitor.garconbagual.activities;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.heitor.garconbagual.R;
import com.heitor.garconbagual.dao.ProdutoDAO;
import com.heitor.garconbagual.model.Produto;
import com.heitor.garconbagual.utils.ProdutoView;

import java.util.ArrayList;

public class MainActivity extends MyActvity {


    private LinearLayout ll;
    private ProdutoDAO produtoDAO = new ProdutoDAO(this);
    public ArrayList<ProdutoView> arrayPV = new ArrayList<>();
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista);

        ll = (LinearLayout) findViewById(R.id.listaViews);


    }

    @Override
    protected void onResume() {
        super.onResume();
        ll.removeAllViewsInLayout();
        arrayPV.clear();
        Cursor cursor = produtoDAO.getAllCursor(this);
        if(cursor.moveToFirst()) {
            do{
                arrayPV.add(new ProdutoView(this, produtoDAO.getProdutoByCursor(cursor),ll));
            }while (cursor.moveToNext());
        }else {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Não existem produtos cadastrados! Você deseja cadastrar produtos?");
            dlg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainActivity.this, CadastroProdutosActivity.class);
                    startActivity(intent);
                }
            });
            dlg.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dlg.show();
        }

        for (int x=0;x<arrayPV.size();x++){
            arrayPV.get(x).inflateViews();
        }


    }

    public void enviar(View view){
        ArrayList<Integer> selectedIds = new ArrayList<>();
        for(int x = 0; x < arrayPV.size(); x++){
            if(!arrayPV.get(x).getSp().getSelectedItem().equals(0)){
                bundle.putInt(arrayPV.get(x).getProduto().getId()+"",Integer.parseInt(arrayPV.get(x).getSp().getSelectedItem().toString()));
                selectedIds.add(arrayPV.get(x).getProduto().getId());
            }
        }
        bundle.putIntegerArrayList("selectedIds",selectedIds);
        Intent intent = new Intent(this,ListaActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void limpar(View v){
        for(int x = 0; x < arrayPV.size(); x++){
            arrayPV.get(x).getCb().setChecked(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_item:
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setMessage("Versão 1.0\nDesenvolvido por: \nHeitor Gianastasio\n(heitor1209@gmail.com)");
                dlg.setNeutralButton("OK", null);
                dlg.show();
                return true;
            case R.id.lista_produtos_item:
                Intent intent = new Intent(this,ListaProdutosActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
