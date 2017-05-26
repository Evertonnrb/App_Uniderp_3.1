package br.com.nrbsistemas.uniderp_31.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.nrbsistemas.uniderp_31.helper.SqlLiteHelper;
import br.com.nrbsistemas.uniderp_31.model.Curso;

/**
 * Created by Everton on 24/05/2017.
 */

public class CursoDao {

    private SqlLiteHelper databaseHelper;
    private SQLiteDatabase database;

    public CursoDao(Context context) {
        databaseHelper = new SqlLiteHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (database == null) {
            database = databaseHelper.getWritableDatabase();
        }
        return database;
    }
    private Curso criarCurso(Cursor cursor) {
        Curso model = new Curso(
                cursor.getInt(cursor.getColumnIndex(SqlLiteHelper.Cursos.ID)),
                cursor.getString(cursor.getColumnIndex(SqlLiteHelper.Cursos.NOME_C))
        );
        return model;
    }

    public long salvarCurso(Curso curso) {
        ContentValues values = new ContentValues();
        values.put(SqlLiteHelper.Cursos.NOME_C,curso.getNome());
        if (curso.get_id() != null) {
            return getDatabase().update(
                    SqlLiteHelper.Cursos.TB_CURSO,
                    values,
                    "_id = ?",
                    new String[]{curso.get_id().toString()});
        }
        return getDatabase().insert(SqlLiteHelper.Cursos.TB_CURSO, null, values);
    }

    public boolean remover(int id) {
        return getDatabase().delete(SqlLiteHelper.Cursos.TB_CURSO, " _id = ?", new String[]{Integer.toString(id)}) > 0;
    }
    public Curso buscarPorId(int id) {
        Cursor cursor = getDatabase().query(SqlLiteHelper.Cursos.TB_CURSO,
                SqlLiteHelper.Cursos.COLUNAS,
                "_id = ?",
                new String[]{Integer.toString(id)}
                , null,
                null,
                null);
        if (cursor.moveToNext()) {
            Curso model = criarCurso(cursor);
            cursor.close();
            return model;
        }
        return null;
    }
    public List<Curso> listarCursos() {
        Cursor cursor = getDatabase().query(SqlLiteHelper.Cursos.TB_CURSO,
                SqlLiteHelper.Cursos.COLUNAS,
                null, null, null, null, null);

        List<Curso> cursos= new ArrayList<>();
        while (cursor.moveToNext()) {
            Curso model = criarCurso(cursor);
            cursor.close();
            cursos.add(model);
        }
        return cursos;
    }

    public void fechar() {
        database.close();
        databaseHelper = null;
    }
}
