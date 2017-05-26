package br.com.nrbsistemas.uniderp_31.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.nrbsistemas.uniderp_31.helper.SqlLiteHelper;
import br.com.nrbsistemas.uniderp_31.model.Aluno;

/**
 * Created by Everton on 24/05/2017.
 */

public class AlunoDao {
    private SqlLiteHelper databaseHelper;
    private SQLiteDatabase database;

    public AlunoDao(Context context) {
        databaseHelper = new SqlLiteHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Aluno criarAluno(Cursor cursor) {
        Aluno model = new Aluno(
                cursor.getInt(cursor.getColumnIndex(SqlLiteHelper.Alunos.ID)),
                cursor.getString(cursor.getColumnIndex(SqlLiteHelper.Alunos.NOME_A)),
                cursor.getString(cursor.getColumnIndex(SqlLiteHelper.Alunos.RA)),
                cursor.getString(cursor.getColumnIndex(SqlLiteHelper.Alunos.SENHA))
        );
        return model;
    }

    public long salvarAluno(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put(SqlLiteHelper.Alunos.NOME_A, aluno.getNome());
        values.put(SqlLiteHelper.Alunos.RA,aluno.getRa());
        values.put(SqlLiteHelper.Alunos.SENHA,aluno.getSenha());
        if (aluno.get_id() != null) {
            return getDatabase().update(
                    SqlLiteHelper.Alunos.TB_ALUNO,
                    values,
                    "_id = ?",
                    new String[]{aluno.get_id().toString()});
        }
        return getDatabase().insert(SqlLiteHelper.Alunos.TB_ALUNO, null, values);
    }

    public boolean remover(int id) {
        return getDatabase().delete(SqlLiteHelper.Alunos.TB_ALUNO, " _id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Aluno buscarPorId(int id) {
        Cursor cursor = getDatabase().query(SqlLiteHelper.Alunos.TB_ALUNO,
                SqlLiteHelper.Alunos.COLUNAS_SEM_CHAVE,
                "_id = ?",
                new String[]{Integer.toString(id)}
                , null,
                null,
                null);
        if (cursor.moveToNext()) {
            Aluno model = criarAluno(cursor);
            cursor.close();
            return model;
        }
        return null;
    }

    public List<Aluno> listarAlunos() {
        Cursor cursor = getDatabase().query(SqlLiteHelper.Alunos.TB_ALUNO,
                SqlLiteHelper.Alunos.COLUNAS_SEM_CHAVE,
                null, null, null, null, null);

        List<Aluno> alunos= new ArrayList<>();
        while (cursor.moveToNext()) {
            Aluno model = criarAluno(cursor);
            //cursor.close();
            alunos.add(model);
        }
        return alunos;
    }

    public boolean logar(String ra, String senha) {
        Cursor cursor = getDatabase().query(
                SqlLiteHelper.Alunos.TB_ALUNO
                , null
                , "LOGIN = ? AND SENHA = ?"
                , new String[]{ra, senha}
                , null, null, null);
        if (cursor.moveToFirst()){
            return true;
        }
        return false;
    }

    public void fechar() {
        database.close();
        databaseHelper = null;
    }
}
