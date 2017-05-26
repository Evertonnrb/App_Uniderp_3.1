package br.com.nrbsistemas.uniderp_31.model;

/**
 * Created by Everton on 16/05/2017.
 */

public class Disciplina {
    private Integer _id;
    private String nome;
    private Curso curso;

    public Disciplina(){}

    public Disciplina(Integer _id, String nome) {
        this._id = _id;
        this.nome = nome;
    }

    public Disciplina(String nome, Curso curso) {
        this.nome = nome;
        this.curso = curso;
    }

    public Disciplina(Integer _id, String nome, Curso curso) {
        this._id = _id;
        this.nome = nome;
        this.curso = curso;
    }

    public long get_id() {
        return _id;
    }
    public void set_id(Integer _id) {
        this._id = _id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
