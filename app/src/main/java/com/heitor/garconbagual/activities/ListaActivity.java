package com.heitor.garconbagual.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.heitor.garconbagual.R;
import com.heitor.garconbagual.dao.ProdutoDAO;

import java.util.ArrayList;

public class ListaActivity extends MyActvity {

    private ListView listaFinal;
    private ArrayAdapter<String> adp;

    private EditText etDinheiro;
    private TextView tvTroco;
    private TextView tvTotal;

    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        listaFinal = (ListView) findViewById(R.id.listComanda);
        tvTotal = (TextView) findViewById(R.id.tvValorTotal);
        tvTroco = (TextView) findViewById(R.id.tvTroco);
        etDinheiro = (EditText) findViewById(R.id.editText);

        adp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        bundle = getIntent().getExtras();
        float total=0;

        ArrayList<Integer> selected = bundle.getIntegerArrayList("selectedIds");

        for (int x=0; x< selected.size();x++){
            int id = selected.get(x);
            String nome = new ProdutoDAO(this).getProdutoById(id).getNome();
            double preco = new ProdutoDAO(this).getProdutoById(id).getPreco();
            int quant = bundle.getInt(id+"");
            adp.add(quant+"x "+nome+"(R$"+preco+") = R$"+arredondar(preco * quant, 2));
            total+=(preco*quant);
        }

        selected.clear();

        tvTotal.setText("R$" + total + "0");

        listaFinal.setAdapter(adp);


    }


    public void calculaTroco(View view){
        if(!etDinheiro.getText().toString().isEmpty()){

            float dinheiro = Float.parseFloat(etDinheiro.getText().toString());
            float total = Float.parseFloat(tvTotal.getText().toString().replace("R$",""));
            double troco = dinheiro-total;
            if(troco>=0){
                tvTroco.setText(("R$"+ arredondar(troco,2)+""));
            }else Toast.makeText(this,"Ooo meu, tá fatando dinheiro aí!!", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this,"Te liga magrão!! O dinheiro tá em branco!!", Toast.LENGTH_LONG).show();
    }

    double arredondar(double valor, int casas) {
        double verif,arredondado = valor;

        int multp=1;
        for (int i =0;i<=casas;i++) multp*=10;
        arredondado *= multp;
        verif = arredondado;
        arredondado=Math.floor(arredondado);
        verif-=arredondado;
        verif=Math.floor(verif*10);

        if (verif > 4) arredondado++;
        arredondado /= multp;
        return arredondado;
    }

    @Override
    public void finish() {
        super.finish();
    }
}
