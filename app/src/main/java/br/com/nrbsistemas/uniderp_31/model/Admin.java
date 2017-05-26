package br.com.nrbsistemas.uniderp_31.model;

import java.util.ArrayList;
import java.util.List;

import br.com.nrbsistemas.uniderp_31.R;

/**
 * Created by Everton on 27/03/2017.
 */

public class Admin {
    private int img;
    private String opcao;

    public Admin(int img, String opcao) {
        this.img = img;
        this.opcao = opcao;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    /**
     * opcoesAdm carrega na lista asopções
     * com icones estáticos
     */
    public static List<Admin> opcoesAdm() {
        List<Admin> opc = new ArrayList<>();
        opc.add(new Admin(R.drawable.ic_person_add_black_24dp, "\tCadastrar Professores"));
        opc.add(new Admin(R.drawable.estudande, "\tCadastrar Alunos"));
        opc.add(new Admin(R.drawable.grade, "\tPesquisar alunos"));
        // opc.add(new Admin(R.drawable.settings,"\tConfigurações"));
        opc.add(new Admin(R.drawable.del, "\tExcluir"));

        return opc;
    }
}
