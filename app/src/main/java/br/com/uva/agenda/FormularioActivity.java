package br.com.uva.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.uva.agenda.Modelo.Usuario;
import br.com.uva.agenda.dao.UsuarioDAO;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        /*Botão para salvar antigo
        Button botaoSalvar = (Button) findViewById(R.id.formulario_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = helper.pegaUsuario();
                Toast.makeText(FormularioActivity.this, "Usuário " + usuario.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_salvar:
                Usuario usuario = helper.pegaUsuario();
                UsuarioDAO dao = new UsuarioDAO(this);
                dao.insere(usuario);
                dao.close();
                Toast.makeText(FormularioActivity.this, "Usuário " + usuario.getNome() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
