package br.com.nrbsistemas.uniderp_31.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.nrbsistemas.uniderp_31.R;
import br.com.nrbsistemas.uniderp_31.dao.ProfessorDao;
import br.com.nrbsistemas.uniderp_31.model.Professor;
import br.com.nrbsistemas.uniderp_31.util.Mensagens;

public class CadastrarProfessor extends AppCompatActivity {

    private Professor professor;
    private ProfessorDao professorDao;

    private EditText edtNome,edtLogin,edtSenha;
    private Button btnSalvar,btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_professor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        professor = new Professor();
        professorDao = new ProfessorDao(this);

        edtNome = (EditText)findViewById(R.id.edt_nome_pro);
        edtLogin = (EditText)findViewById(R.id.edt_login);
        edtSenha = (EditText)findViewById(R.id.edt_senha);
        btnSalvar = (Button)findViewById(R.id.btn_salvar);
        btnEditar = (Button)findViewById(R.id.btn_editar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPro();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void salvarPro(){
        professor.setNome(edtNome.getText().toString());
        professor.setLogin(edtLogin.getText().toString());
        professor.setSenha(edtSenha.getText().toString());
        try{
            professorDao.salvarProfessor(professor);
            Mensagens.MsgToastC(this,"Professor salvo");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
