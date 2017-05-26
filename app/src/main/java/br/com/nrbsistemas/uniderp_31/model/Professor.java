package br.com.nrbsistemas.uniderp_31.model;

import br.com.nrbsistemas.uniderp_31.model.Disciplina;

/**
 * Created by Everton on 16/05/2017.
 */

public class Professor {
    private Integer _id;
    private String nome;
    private String login;
    private String senha;
    private Disciplina disciplina;

    public Professor(){}

    public Professor(Integer _id, String nome, String login, String senha) {
        this._id = _id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Professor(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public Professor(Integer _id, String nome, String login, String senha, Disciplina disciplina) {
        this._id = _id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.disciplina = disciplina;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
