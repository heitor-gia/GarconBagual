package com.heitor.garconbagual.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.heitor.garconbagual.R;
import com.heitor.garconbagual.model.Produto;

import java.util.List;

/**
 * Created by heito on 16/10/2015.
 */
public class ProdutoAdapter extends ArrayAdapter<Produto> {
    public ProdutoAdapter(Context context, int resource, List<Produto> objects) {
        super(context, resource, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View linha = LayoutInflater.from(getContext()).inflate(R.layout.linha_produto, null);

        TextView tvNome= (TextView) linha.findViewById(R.id.tvNome);
        TextView tvPreco= (TextView) linha.findViewById(R.id.tvPreco);

        Produto produto = getItem(position);
        tvNome.setText(produto.getNome());
        tvPreco.setText("/R$"+produto.getPreco());

        return linha;
    }
}
