package com.heitor.garconbagual.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.heitor.garconbagual.model.Produto;

/**
 * Created by heito on 14/10/2015.
 */
public class ProdutoView{
    private CheckBox cb;
    private Spinner sp;
    private Produto produto;
    private Context context;
    private LinearLayout ll;

    public ProdutoView(final Activity context,Produto produto, final View view){

        LinearLayout linearLayout = (LinearLayout)view;
        this.context=context;


        ll = new LinearLayout(context);
        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setPadding(5, 5, 5, 5);

        linearLayout.addView(ll);

        cb = new CheckBox(context);


        sp = new Spinner(context);


        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) sp.setEnabled(true);
                else {
                    sp.setSelection(0);
                    sp.setEnabled(false);
                }
            }
        });

        this.produto = produto;
    }

    public void inflateViews(){

        cb.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        cb.setVisibility(View.VISIBLE);
        Log.i("Nome", "nome:" + produto.getNome());
        cb.setText(produto.getNome());
        cb.setTextColor(context.getResources().getColor(android.R.color.black));
        ll.addView(cb);

        ArrayAdapter<Integer> adapterInt  = new ArrayAdapter<Integer>(context,android.R.layout.simple_spinner_dropdown_item);
        adapterInt.addAll(0,1,2,3,4,5,6,7,8,9,10);

        sp.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        sp.setVisibility(View.VISIBLE);
        sp.setAdapter(adapterInt);
        sp.setEnabled(false);
        ll.addView(sp);
    }

    public Spinner getSp() {

        return sp;
    }

    public CheckBox getCb() {


        return cb;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }



}
