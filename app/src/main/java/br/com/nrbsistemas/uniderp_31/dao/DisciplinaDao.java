package br.com.nrbsistemas.uniderp_31.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.nrbsistemas.uniderp_31.helper.SqlLiteHelper;
import br.com.nrbsistemas.uniderp_31.model.Curso;
import br.com.nrbsistemas.uniderp_31.model.Disciplina;

/**
 * Created by Everton on 24/05/2017.
 */

public class DisciplinaDao {
    private SqlLiteHelper databaseHelper;
    private SQLiteDatabase database;

    public DisciplinaDao(Context context) {
        databaseHelper = new SqlLiteHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Disciplina criarDisciplina(Cursor cursor) {
        Disciplina model = new Disciplina(
                cursor.getInt(cursor.getColumnIndex(SqlLiteHelper.Disciplinas.ID)),
                cursor.getString(cursor.getColumnIndex(SqlLiteHelper.Disciplinas.NOME_D))
        );
        return model;
    }

    public long salvarDisciplina(Disciplina disciplina) {
        ContentValues values = new ContentValues();
        values.put(SqlLiteHelper.Disciplinas.NOME_D,disciplina.getNome());
        return getDatabase().insert(SqlLiteHelper.Cursos.TB_CURSO, null, values);
    }

    public boolean remover(int id) {
        return getDatabase().delete(SqlLiteHelper.Disciplinas.ID, " _id = ?", new String[]{Integer.toString(id)}) > 0;
    }

    public Disciplina buscarDisciplinaPorId(int id) {
        Cursor cursor = getDatabase().query(SqlLiteHelper.Disciplinas.TB_DISCIPLINA,
                SqlLiteHelper.Disciplinas.COLUNAS_SEMCHAVE,
                "_id = ?",
                new String[]{Integer.toString(id)}
                , null,
                null,
                null);
        if (cursor.moveToNext()) {
            Disciplina model = criarDisciplina(cursor);
            cursor.close();
            return model;
        }
        return null;
    }

    public List<Disciplina> listarDisciplinas() {
        Cursor cursor = getDatabase().query(SqlLiteHelper.Disciplinas.TB_DISCIPLINA,
                SqlLiteHelper.Disciplinas.COLUNAS_SEMCHAVE,
                null, null, null, null, null);

        List<Disciplina> disciplinas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Disciplina model = criarDisciplina(cursor);
            cursor.close();
            disciplinas.add(model);
        }
        return disciplinas;
    }

    public void fechar() {
        database.close();
        databaseHelper = null;
    }
}
