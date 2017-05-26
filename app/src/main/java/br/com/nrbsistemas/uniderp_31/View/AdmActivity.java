package br.com.nrbsistemas.uniderp_31.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.nrbsistemas.uniderp_31.R;
import br.com.nrbsistemas.uniderp_31.adapter.AdmAdapter;
import br.com.nrbsistemas.uniderp_31.model.Admin;

public class AdmActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Admin> opc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm);

        ListView listView = (ListView) findViewById(R.id.lista);
        opc = Admin.opcoesAdm();
        AdmAdapter adapter = new AdmAdapter(opc, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_adm, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        if (item.getItemId() == R.id.menu_adm_sair) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Admin admin = opc.get(position);
        switch (position) {
            case 0:
                startActivity(new Intent(this, CadastrarProfessor.class));
                break;
            case 1:
                startActivity(new Intent(this, CadastrarAlunos.class));
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "Cadastrar alunos", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getApplicationContext(), "Exclusões/Trancamentos", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
