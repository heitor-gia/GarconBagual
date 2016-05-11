package com.heitor.garconbagual.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.heitor.garconbagual.database.DBHelper;
import com.heitor.garconbagual.model.Produto;

import java.util.ArrayList;

/**
 * Created by heito on 14/10/2015.
 */
public class ProdutoDAO {
    private Context mContext;
    private DBHelper helper;

    public ProdutoDAO(Context context){
        mContext=context;
        helper = new DBHelper(mContext);
    }

    public static final String PRODUTO_TABLE= "PRODUTO";
    public static final String NAME_COLUMN= "NOME";
    public static final String PRICE_COLUMN= "PRECO";
    public static final String ID_COLUMN= "_id";


    public static final String sqlCreateTable =
            "CREATE TABLE "+PRODUTO_TABLE+" ("+
                    ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                    NAME_COLUMN+" TEXT NOT NULL , "+
                    PRICE_COLUMN+" DOUBLE NOT NULL)";

    public Long save(Produto produto){
        ContentValues values = new ContentValues();
        values.put(ID_COLUMN,produto.getId());
        values.put(NAME_COLUMN,produto.getNome());
        values.put(PRICE_COLUMN, produto.getPreco());

        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            Long id = db.insertOrThrow(PRODUTO_TABLE,null,values);
            Log.i("DATABASE", "INSERT:" + produto.getNome());
            return id;
        }catch (SQLiteConstraintException ex){
            Long id = db.replace(PRODUTO_TABLE, null, values);
            Log.i("DATABASE", "UPDATE:" + produto.getNome());
            return (long) produto.getId();
        }catch (Throwable e){
            throw e;
        }finally {
            db.close();
        }
    }

    public ArrayList<Produto> getAll(){
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] colunas = new String[]{ID_COLUMN,NAME_COLUMN,PRICE_COLUMN};
        String where = null;
        String[] whereArgs = null;
        String orderBy = null;
        String groupBy = null;
        String having = null;
        String limit = null;

        Cursor cursor = db.query(PRODUTO_TABLE,colunas,where,whereArgs,groupBy,having,orderBy,limit);
        ArrayList<Produto> produtos = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                produtos.add(getProdutoByCursor(cursor));
            }while (cursor.moveToNext());
        }

        return produtos;
    }

    public boolean delete(Produto produto){
        SQLiteDatabase db = helper.getWritableDatabase();
        int qntLinhasAfetadas =db.delete(PRODUTO_TABLE,ID_COLUMN+" = ?",new String[]{produto.getId()+""});
        db.close();
        return (qntLinhasAfetadas > 0);
    }

    public Produto getProdutoByCursor(Cursor cursor){
        Produto produto = new Produto();
        produto.setId(cursor.getInt(cursor.getColumnIndex(ID_COLUMN)));
        produto.setNome(cursor.getString(cursor.getColumnIndex(NAME_COLUMN)));
        produto.setPreco(cursor.getDouble(cursor.getColumnIndex(PRICE_COLUMN)));

        return produto;
    }

    public Produto getProdutoById(int id){
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] colunas = new String[]{ID_COLUMN,NAME_COLUMN,PRICE_COLUMN};
        String where = ID_COLUMN+" = ?";
        String[] whereArgs = new String[]{id+""};
        String orderBy = null;
        String groupBy = null;
        String having = null;
        String limit = null;

        Cursor cursor = db.query(PRODUTO_TABLE,colunas,where,whereArgs,groupBy,having,orderBy,limit);
        if(cursor.moveToFirst()){
        Produto produto = new Produto();
        produto.setId(cursor.getInt(cursor.getColumnIndex(ID_COLUMN)));
        produto.setNome(cursor.getString(cursor.getColumnIndex(NAME_COLUMN)));
        produto.setPreco(cursor.getDouble(cursor.getColumnIndex(PRICE_COLUMN)));
        return produto;
        }else return null;
    }

    public Produto getFirstProduto(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] colunas = new String[]{ID_COLUMN,NAME_COLUMN,PRICE_COLUMN};
        Cursor cursor = db.query(PRODUTO_TABLE,colunas,null,null,null,null,null,null);
        cursor.moveToFirst();
        return getProdutoByCursor(cursor);
    }

    static public Cursor getAllCursor(Context context){
        SQLiteDatabase db = new DBHelper(context).getReadableDatabase();
        String[] colunas = new String[]{ID_COLUMN,NAME_COLUMN,PRICE_COLUMN};
        return db.query(PRODUTO_TABLE,colunas,null,null,null,null,null,null);
    }

}
