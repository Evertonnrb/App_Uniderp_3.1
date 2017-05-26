package br.com.nrbsistemas.uniderp_31.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ToggleButton;

import br.com.nrbsistemas.uniderp_31.R;
import br.com.nrbsistemas.uniderp_31.dao.AlunoDao;
import br.com.nrbsistemas.uniderp_31.dao.CursoDao;
import br.com.nrbsistemas.uniderp_31.dao.DisciplinaDao;
import br.com.nrbsistemas.uniderp_31.dao.ProfessorDao;
import br.com.nrbsistemas.uniderp_31.model.Curso;
import br.com.nrbsistemas.uniderp_31.model.Disciplina;
import br.com.nrbsistemas.uniderp_31.util.Mensagens;

public class LoginActivity extends AppCompatActivity {

    private AlunoDao alunoDao;
    private ProfessorDao professorDao;
    // private static final String TAG = "info";

    private ToggleButton mToggle;
    private EditText edtLogin, edtSenha;
    private Button btnLogar;
    private ImageView logoLogin;
    private int atualiza = 0;
    private static final int TEMPORIZADOR = 3000;
    private static final String TAG = "Contador";

    private Curso curso;
    private CursoDao cursoDao;
    private Disciplina disciplina;
    private DisciplinaDao disciplinaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        alunoDao = new AlunoDao(this);
        professorDao = new ProfessorDao(this);


        long resultado;
        curso = new Curso();
        cursoDao = new CursoDao(this);
        curso = cursoDao.buscarPorId(1);

        disciplina = new Disciplina();

        disciplina.setNome("Algoritimos");
        disciplina.setCurso(curso);
        resultado = disciplinaDao.salvarDisciplina(disciplina);
        if(resultado!= -1)
            Log.i(Mensagens.TAG,"Curso "+disciplina.getNome()+" "+disciplina.getCurso().getNome());
        /*curso = new Curso();
        curso.setNome("TADS");
        cursoDao = new CursoDao(this);
        resultado = cursoDao.salvarCurso(curso);
        if (resultado != -1)
            Log.i(Mensagens.TAG,"Curso salvo");
*/
        logoLogin = (ImageView) findViewById(R.id.img_logo_login);
        logoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _temporizador();
            }
        });
        mToggle = (ToggleButton) findViewById(R.id.toggle_id);
        mToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mToggle.isChecked()) {
                    edtLogin.setHint("Sou aluno RA");
                } else {
                    edtLogin.setHint("Sou professor ID");
                }
            }
        });
        mToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String entrar = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();
                if (isChecked) {
                    //Professor
                    professorDao.logar(entrar, senha);
                } else {
                    alunoDao.logar(entrar, senha);

                }
            }
        });
        edtLogin = (EditText) findViewById(R.id.edt_login);
        edtSenha = (EditText) findViewById(R.id.edt_senha);
        btnLogar = (Button) findViewById(R.id.btn_logar);
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();
                _logar();
            }
        });


    }

    public void _temporizador() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    _logimAdm();
                    if (atualiza == 5) {
                        _moduloAdm();
                    }
                } catch (Exception e) {
                    Log.i(TAG, "Erro no contar login");
                    e.printStackTrace();
                }
            }
        }, TEMPORIZADOR);
    }

    public void _logar() {
        String login = edtLogin.getText().toString();
        String senha = edtSenha.getText().toString();


        if (login.isEmpty() && senha.isEmpty()) {
            edtLogin.setError("Prencha o campo");
            edtSenha.setError("Prencha o campo");
        } else if (senha.isEmpty()) {
            edtSenha.setError("Informe a senha");
            _limpar();
        } else if ("adm".equals(login) && "123".equals(senha)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        if (mToggle.equals("Professor")) {

        }
        if (mToggle.equals("Aluno")) {

        } else {
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("Atenção")
                    .setMessage("Usuario e ou senha inválidos")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    })
                    .create()
                    .show();
            _limpar();
        }
    }

    //TODO mudar para o cliclo de vida
    public void _limpar() {
        edtLogin.setText("");
        edtSenha.setText("");
    }

    private void _autenticacao() {
        LayoutInflater li = getLayoutInflater();
        final View view = li.inflate(R.layout.authentica_adm, null);
    }

    private void _logimAdm() {
        atualiza += 1;
    }

    public void _moduloAdm() {
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.authentica_login, null);
        final EditText edtAutentica = (EditText) view.findViewById(R.id.edt_authentica);
        Button btnAutentica = (Button) view.findViewById(R.id.btn_authentica);
        AlertDialog.Builder v = new AlertDialog.Builder(this);
        v.setTitle("Autenticação necessária");
        btnAutentica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtAutentica.getText().toString().isEmpty()) {
                    //Toast.makeText(getApplicationContext(), "Falha na autenticação", Toast.LENGTH_SHORT).show();
                    Mensagens.MsgToastC(LoginActivity.this, "Falha na autenticação");
                } else if (edtAutentica.getText().toString().equals("12345")) {
                    startActivity(new Intent(LoginActivity.this, AdmActivity.class));
                    finish();

                } else {
                    edtAutentica.setError("Adm não autorizado");
                    edtAutentica.setText("");
                }
            }
        });
        v.setView(view);
        v.create();
        v.show();
    }

}
