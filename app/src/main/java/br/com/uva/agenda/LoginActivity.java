package br.com.uva.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button botao_login = (Button) findViewById(R.id.botao_login);
        botao_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView textLogin = (TextView) findViewById(R.id.login);
        TextView textSenha = (TextView) findViewById(R.id.senha);

        String login = textLogin.getText().toString();
        String senha = textSenha.getText().toString();

        if("Admin".equals(login) && "123456".equals(senha)){
            //Trocar de tela
            Intent it = new Intent(this, ListaUsuariosActivity.class);
            startActivity(it);
        }

    }
}
