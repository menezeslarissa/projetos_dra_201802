/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.controller;

import atividade_20180814.loja.model.Compra;
import atividade_20180814.loja.util.DataUtil;
import atividade_20180814.loja.view.FinalizarCompraView;
import atividade_20180814.loja.view.HistoricoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Aluno
 */
public class FinalizarCompraController {

    private final FinalizarCompraView fView;
    private static Compra c;
    private ItemCompraController itc;
    private Integer id;

    public FinalizarCompraController(FinalizarCompraView view, Compra compra, Integer idCompra) {
        this.fView = view;
        this.c = compra;
        this.id = idCompra;

        mostrarInfo();
        fView.addActionHistorico(new HistoricoListener());
        fView.addAcaoVoltar(new VoltarListener());
    }

    class HistoricoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            HistoricoView view = new HistoricoView();
            //Compra compra = new Compra();
            HistoricoController controller = new HistoricoController(view, c);
            view.setVisible(true);

        }

    }

    //açao de voltar
    class VoltarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fView.dispose();
        }

    }

    private void mostrarInfo() {
        
        fView.setData(DataUtil.ConverterDataEmTexto(c.getDataDaCompra()));
        fView.setValorTotal(Double.toString(c.calcValorTotal()));
        fView.setId(Integer.toString(this.id));
    }

}
