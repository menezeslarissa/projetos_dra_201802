/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.controller;

import atividade_20180814.loja.dao.CompraDAO;
import atividade_20180814.loja.model.Compra;
import atividade_20180814.loja.model.ItemCompra;
import atividade_20180814.loja.model.TableModelCompra;
import atividade_20180814.loja.view.HistoricoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;

/**
 *
 * @author larissa
 */
public class HistoricoController {

    private HistoricoView hView;
    private Compra c;
    private TableModelCompra modelo;
    private List<Compra> compras;

    /**
     *
     * @param view
     * @param compra
     */
    public HistoricoController(HistoricoView view, Compra compra) {
        this.hView = view;
        this.c = compra;

        carregarTabela();
        carregarMenuPopUp();
        hView.setMenuPopup(hView.getPopup());
        
        hView.addAcaoVoltar(new VoltarListener());
    }

    //açao de voltar
    class VoltarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            hView.dispose();
        }

    }

    private void carregarTabela() {
        CompraDAO dao = new CompraDAO();
        compras = new ArrayList<>();
        compras = dao.listarCompras();

        for (int i = 0; i < compras.size(); i++) {
            compras.get(i).setItens(dao.consultarItensCompra(compras.get(i).getIdCompra()));
        }
        modelo = new TableModelCompra(compras);
        hView.setTableModel(modelo);

    }

    private void carregarMenuPopUp() {
        JMenuItem itemMostrar = new JMenuItem("Mostrar itens");

        itemMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompraDAO dao = new CompraDAO();
                List<ItemCompra> itens = new ArrayList<>();
                itens = dao.consultarItensCompra(getCompraSelecionada());
                String s = "";
                for(ItemCompra item : itens){
                    s+="Produto: " +item.getProduto().getDescricao() + " - ";
                    s+="Quantidade: " +item.getQuantidade() + "\n";
                }
                hView.displayMsg(s);
            }
        });

        hView.getPopup().add(itemMostrar);
    }
    private Integer getCompraSelecionada() {
        return this.compras.get(hView.getTabela().getSelectedRow()).getIdCompra();
    }

    public void tabelaProdMouseReleased(MouseEvent evt) {
        realizarAcao(evt);
    }

    private void realizarAcao(MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() > 1) {

            }
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            hView.getPopup().show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }

  

}
