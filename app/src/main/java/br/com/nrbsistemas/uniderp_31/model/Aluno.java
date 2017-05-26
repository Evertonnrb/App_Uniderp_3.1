package br.com.nrbsistemas.uniderp_31.model;

/**
 * Created by Everton on 16/05/2017.
 */

public class Aluno {
    private Integer _id;
    private String nome;
    private String ra;
    private String senha;
    private Double n1;
    private Double n2;
    private Disciplina disciplina;

    public Aluno() {
    }

    public Aluno(Integer _id, String nome, String ra, String senha) {
        this._id = _id;
        this.nome = nome;
        this.ra = ra;
        this.senha = senha;
    }

    public Aluno(String nome, String ra, String senha) {
        this.nome = nome;
        this.ra = ra;
        this.senha = senha;
    }

    public Aluno(String nome, String ra, String senha, Disciplina disciplina) {
        this.nome = nome;
        this.ra = ra;
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

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Disciplina getCurso() {
        return disciplina;
    }

    public void setCurso(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Double getN1() {
        return n1;
    }

    public void setN1(Double n1) {
        this.n1 = n1;
    }

    public Double getN2() {
        return n2;
    }

    public void setN2(Double n2) {
        this.n2 = n2;
    }

    public double calculaMedia(Double n1, Double n2) {
        return (getN1() * .2) + (getN2() * .3);
    }

    public String imprimeSitucao() {
        if (calculaMedia(n1, n2) >= 7)
            return "Aprovado";
        else if (calculaMedia(n1, n2) > 3) {
            return "Exame";
        } else
            return "Reprovado";
    }
}
