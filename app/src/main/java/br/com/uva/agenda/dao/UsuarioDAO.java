package br.com.uva.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.uva.agenda.Modelo.Usuario;

public class UsuarioDAO extends SQLiteOpenHelper{
    public UsuarioDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Usuarios (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Usuarios;";
        db.execSQL(sql);
        onCreate(db);

    }

    public void insere(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", usuario.getNome());
        dados.put("endereco", usuario.getEndereco());

        db.insert("Usuarios", null, dados);
    }

    public void deleta(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {usuario.getId().toString()};
        db.delete("Usuarios", "id = ?", params);
    }

    public List<Usuario> buscaUsuarios() {
        String sql = "SELECT * FROM Usuarios";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Usuarios;", null);
        List<Usuario> usuarios = new ArrayList<Usuario>();
        while (c.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setId(c.getLong(c.getColumnIndex("id")));
            usuario.setNome(c.getString(c.getColumnIndex("nome")));
            usuario.setEndereco(c.getString(c.getColumnIndex("endereco")));
            usuarios.add(usuario);
        }
        c.close();
        return usuarios;
    }
}
