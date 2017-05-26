package br.com.nrbsistemas.uniderp_31.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Everton on 24/05/2017.
 */

public class SqlLiteHelper extends SQLiteOpenHelper {
    private static final String DB = "escola";
    private static final int VERSAO = 2;

    public SqlLiteHelper(Context context) {
        super(context, DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //tabela curso
        db.execSQL("CREATE TABLE curso(_id INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT)");

        //tabela disciplina
        db.execSQL("CREATE TABLE disciplina(_id INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT" +
                ", curso_id INTEGER,FOREIGN KEY (curso_id) REFERENCES curso(_id))");

        //tabela aluno
        db.execSQL("CREATE TABLE aluno(_id INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT," +
                "ra TEXT, senha TEXT,n1 DOUBLE,n2 DOUBLE," +
                "disciplina_id INTEGER, FOREIGN KEY (disciplina_id) REFERENCES disciplina(_id))");

        //tabela professor
        db.execSQL("CREATE TABLE professor(_id INTEGER PRIMARY KEY AUTOINCREMENT,nome TEXT," +
                "login TEXT, senha TEXT,disciplina_id INTEGER, FOREIGN KEY (disciplina_id) REFERENCES disciplina(_id))");

        db.execSQL("insert into aluno (nome,ra,senha) values ('Bob Sponja','1111','0000')");
        db.execSQL("insert into aluno (nome,ra,senha) values ('Burnna Surfistinha','2222','1111')");

        db.execSQL("insert into professor (nome,login,senha) values ('Bonner','jornal','bonner')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static class Cursos{
        public static final String TB_CURSO = "curso";
        public static final String ID = "_id";
        public static final String NOME_C = "nome";
        public static final String[] COLUNAS= {ID,NOME_C};
        public static final String[] COLUNAS_SEMID= {NOME_C};
    }
    public static class Disciplinas{
        public static final String TB_DISCIPLINA = "disciplina";
        public static final String ID = "_id";
        public static final String NOME_D = "nome";
        public static final String CURSO_ID = "curso_id";
        public static final String[] COLUNAS= {ID,NOME_D,CURSO_ID};
        public static final String[] COLUNAS_SEMCHAVE= {ID,NOME_D};
        public static final String[] COLUNAS_SEMID= {NOME_D,CURSO_ID};
    }
    public static class Alunos{
        public static final String TB_ALUNO = "aluno";
        public static final String ID = "_id";
        public static final String NOME_A = "nome";
        public static final String RA = "ra";
        public static final String SENHA = "senha";
        public static final String N1= "n1";
        public static final String N2= "n2";
        public static final String DISCIPLINA_ID= "disciplina_id";
        public static final String[] COLUNAS= {ID,NOME_A,RA,SENHA,N1,N2,DISCIPLINA_ID};
        public static final String[] COLUNAS_SEM_ID= {NOME_A,RA,SENHA,N1,N2,DISCIPLINA_ID};
        public static final String[] COLUNAS_SEM_CHAVE= {ID,NOME_A,RA,SENHA,N1,N2};
    }
    public static class Professores{
        public static final String TB_PROFESSOR = "professor";
        public static final String ID = "_id";
        public static final String NOME_P = "nome";
        public static final String LOGIN = "login";
        public static final String SENHA = "senha";
        public static final String DISCIPLINA_ID= "disciplina_id";
        public static final String[] COLUNAS= {ID,NOME_P,LOGIN,SENHA,DISCIPLINA_ID};
        public static final String[] COLUNAS_SEMCHAVE= {ID,NOME_P,LOGIN,SENHA};
        public static final String[] COLUNAS_SEMID= {NOME_P,LOGIN,SENHA};
        public static final String[] COLUNAS_COMCHAVE= {NOME_P,LOGIN,SENHA,DISCIPLINA_ID};

    }
}
