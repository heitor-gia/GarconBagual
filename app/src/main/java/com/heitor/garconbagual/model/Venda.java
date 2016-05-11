package com.heitor.garconbagual.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by heito on 13/10/2015.
 */
public class Venda {
    ArrayList<Produto> produtos;
    HashMap<String,Integer> quantidades;
    double total = 0;

    Venda(List<Produto> l){
        produtos = (ArrayList<Produto>) l;
        for (int x = 0; x < produtos.size(); x++){
            total+=produtos.get(x).getPreco();
        }


    }

    public double getTotal() {
        return total;
    }
}
