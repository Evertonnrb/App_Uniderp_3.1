package br.com.nrbsistemas.uniderp_31.model;

/**
 * Created by Everton on 16/05/2017.
 */

public class Curso {
    private Integer _id;
    private String nome;

    public Curso(){}

    public Curso(Integer _id, String nome) {
        this._id = _id;
        this.nome = nome;
    }

    public Curso(String nome) {
        this.nome = nome;
    }

    public Integer get_id() {
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
}
