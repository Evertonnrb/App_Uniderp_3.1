package br.com.nrbsistemas.uniderp_31.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.nrbsistemas.uniderp_31.R;
import br.com.nrbsistemas.uniderp_31.model.Admin;

/**
 * Created by Everton on 24/05/2017.
 */

public class AdmAdapter extends BaseAdapter {
    private final List<Admin> listaAdm;
    private final Context context;

    //Lista do tipo ADM com contexto
    public AdmAdapter(List<Admin> listaAdm, Context context) {
        this.listaAdm = listaAdm;
        this.context = context;
    }

    /**
     * getCount conta a quantidade de linhas da list view
     *
     * @return retorna o tamanho da lista
     */
    @Override
    public int getCount() {
        return listaAdm.size();
    }

    /**
     * @param position
     * @return retorna a posição dos elementos da list view
     */
    @Override
    public Object getItem(int position) {
        return listaAdm.get(position);
    }

    /**
     * @param position
     * @return o id do objeto em sua respectiva posição
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return renderiza a list view com a posição,id,contexto
     * inflando a list view opcões_adm
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //inflando a view
        View view = LayoutInflater.from(context).inflate(R.layout.opcoes_adm, parent, false);
        //configurando a view
        TextView txt_opc = (TextView) view.findViewById(R.id.txt_opcoes_adm);
        ImageView img_opc = (ImageView) view.findViewById(R.id.img_opc_adm);
        Admin admin = listaAdm.get(position);
        img_opc.setImageResource(admin.getImg());
        txt_opc.setText(admin.getOpcao());

        return view;
    }
}