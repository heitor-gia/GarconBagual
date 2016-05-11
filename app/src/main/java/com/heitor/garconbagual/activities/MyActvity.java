package com.heitor.garconbagual.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

/**
 * @author MAYCON CARDOSO
 *         Esta foi uma activiy que criamos para juntar tudo que é semelhante
 *         em todas as activitys que usamos.
 *
 *         Neste primeiro momento, só existe os metodos do ciclo de vida explicado em aula.
 *         Dentro de cada método foi colocado uma mensagem no LOG, que mostra o andamento do ciclo de vida.
 *
 *         Para acessar o grafíco do ciclo de vida mostrando em aula.
 *         Acesse: https://dariomungoi.files.wordpress.com/2015/01/basic-lifecycle.png
 *
 *         Artigo falando sobre o ciclo de vida:
 *         http://www.devmedia.com.br/entendendo-o-ciclo-de-vida-de-uma-aplicacao-android/22922
 */
public class MyActvity extends Activity {
    // =================================================================================================
    // CICLO DE VIDA
    // =================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("CURSO", this.getClass().getSimpleName() + " -> onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("CURSO", this.getClass().getSimpleName() + " -> onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("CURSO", this.getClass().getSimpleName() + " -> onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("CURSO", this.getClass().getSimpleName() + " -> onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("CURSO", this.getClass().getSimpleName() + " -> onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("CURSO", this.getClass().getSimpleName() + " -> onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("CURSO", this.getClass().getSimpleName() + " -> onDestroy");
    }
    // =================================================================================================
    //
    //
    //
    // ====================================================-==============================================
    // METODOS
    // =================================================================================================
    protected void vibrar(long timeMiliseconds){
        /**
         * Lembram que eu falei, que existe uma série de serviços do sistema.
         * O Vibrator Service é um deles.
         *
         * A classe Vibrator consegue manipular o hardware, fazendo com que o mesmo vibre durante o tempo informado.
         *
         * Documentação: https://developer.android.com/reference/android/os/Vibrator.html
         */
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(timeMiliseconds);
    }

    protected void vibrar(){
        vibrar(1000);
    }
    // =================================================================================================
}
