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
import br.com.nrbsistemas.uniderp_31.dao.AlunoDao;
import br.com.nrbsistemas.uniderp_31.model.Aluno;
import br.com.nrbsistemas.uniderp_31.util.Mensagens;

public class CadastrarAlunos extends AppCompatActivity {

    private static final String TAG = "info";

    private Aluno aluno;
    private AlunoDao alunoDao;

    private EditText edtNome, edtRa, edtSenha, edtCurso;
    private Button btnCad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_alunos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        aluno = new Aluno();
        alunoDao = new AlunoDao(this);

        edtNome = (EditText) findViewById(R.id.edt_nome_aluno);
        edtRa = (EditText) findViewById(R.id.edt_ra);
        edtSenha = (EditText) findViewById(R.id.edt_senha);
        edtCurso = (EditText) findViewById(R.id.edt_curso);
        btnCad = (Button) findViewById(R.id.btn_cad_aluno);
        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarAluno();
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

    public void cadastrarAluno() {
        aluno.setNome(edtNome.getText().toString());
        aluno.setRa(edtRa.getText().toString());
        aluno.setSenha(edtSenha.getText().toString());
        alunoDao.salvarAluno(aluno);
        Mensagens.MsgToastC(this, "Aluno salvo");

    }

}
