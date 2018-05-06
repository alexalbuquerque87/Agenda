package br.com.uva.agenda;

import android.widget.EditText;

import br.com.uva.agenda.Modelo.Usuario;

public class FormularioHelper {
    private EditText campoNome;
    private EditText campoEndereco;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);

    }

    public Usuario pegaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(campoNome.getText().toString());
        usuario.setEndereco(campoEndereco.getText().toString());
        return usuario;
    }
}
