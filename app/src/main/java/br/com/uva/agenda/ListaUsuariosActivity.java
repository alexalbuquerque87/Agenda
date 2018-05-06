package br.com.uva.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.uva.agenda.Modelo.Usuario;
import br.com.uva.agenda.dao.UsuarioDAO;

public class ListaUsuariosActivity extends AppCompatActivity {

    private ListView listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        listaUsuarios = (ListView) findViewById(R.id.lista_usuarios);

        Button novoUsuario = (Button) findViewById(R.id.novo_usuario);
        novoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent(ListaUsuariosActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        registerForContextMenu(listaUsuarios);

    }

    private void carregaLista() {
        UsuarioDAO dao = new UsuarioDAO(this);
        List<Usuario> usuarios = dao.buscaUsuarios();
        dao.close();

        ListView listaUsuarios = (ListView) findViewById(R.id.lista_usuarios);
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, usuarios);
        listaUsuarios.setAdapter((adapter));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Usuario usuario = (Usuario) listaUsuarios.getItemAtPosition(info.position);

                UsuarioDAO dao = new UsuarioDAO(ListaUsuariosActivity.this);
                dao.deleta(usuario);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
}
