package br.com.nrbsistemas.uniderp_31.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.nrbsistemas.uniderp_31.helper.SqlLiteHelper;
import br.com.nrbsistemas.uniderp_31.model.Aluno;
import br.com.nrbsistemas.uniderp_31.model.Professor;

/**
 * Created by Everton on 24/05/2017.
 */

public class ProfessorDao {

    private SqlLiteHelper databaseHelper;
    private SQLiteDatabase database;

    public ProfessorDao(Context context) {
        databaseHelper = new SqlLiteHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Professor criarProfessor(Cursor cursor) {
        Professor model = new Professor(
                cursor.getInt(cursor.getColumnIndex(SqlLiteHelper.Professores.ID)),
                cursor.getString(cursor.getColumnIndex(SqlLiteHelper.Professores.NOME_P)),
                cursor.getString(cursor.getColumnIndex(SqlLiteHelper.Professores.LOGIN)),
                cursor.getString(cursor.getColumnIndex(SqlLiteHelper.Professores.SENHA))
        );
        return model;
    }

    public long salvarProfessor(Professor professor) {
        ContentValues values = new ContentValues();
        values.put(SqlLiteHelper.Professores.NOME_P, professor.getNome());
        values.put(SqlLiteHelper.Professores.LOGIN, professor.getLogin());
        values.put(SqlLiteHelper.Professores.SENHA, professor.getSenha());
        if (professor.get_id() != null) {
            return getDatabase().update(
                    SqlLiteHelper.Professores.TB_PROFESSOR,
                    values,
                    "_id = ?",
                    new String[]{professor.get_id().toString()});
        }
        return getDatabase().insert(SqlLiteHelper.Alunos.TB_ALUNO, null, values);
    }

    public boolean remover(int id) {
        return getDatabase().delete(SqlLiteHelper.Professores.TB_PROFESSOR, " _id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Professor buscarPorId(int id) {
        Cursor cursor = getDatabase().query(SqlLiteHelper.Professores.TB_PROFESSOR,
                SqlLiteHelper.Professores.COLUNAS_SEMCHAVE,
                "_id = ?",
                new String[]{Integer.toString(id)}
                , null,
                null,
                null);
        if (cursor.moveToNext()) {
            Professor model = criarProfessor(cursor);
            cursor.close();
            return model;
        }
        return null;
    }

    public List<Professor> listarProfessores() {
        Cursor cursor = getDatabase().query(SqlLiteHelper.Professores.TB_PROFESSOR,
                SqlLiteHelper.Professores.COLUNAS_SEMCHAVE,
                null, null, null, null, null);

        List<Professor> professores= new ArrayList<>();
        while (cursor.moveToNext()) {
            Professor model = criarProfessor(cursor);
            cursor.close();
            professores.add(model);
        }
        return professores;
    }

    public boolean logar(String login, String senha) {
        Cursor cursor = getDatabase().query(
                SqlLiteHelper.Professores.TB_PROFESSOR
                , null
                , "LOGIN = ? AND SENHA = ?"
                , new String[]{login, senha}
                , null, null, null);
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

    public void fechar() {
        database.close();
        databaseHelper = null;
    }
}
