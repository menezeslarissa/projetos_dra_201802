/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.controller;

import atividade_20180814.loja.dao.ProdutoDAO;
import atividade_20180814.loja.model.Produto;
import atividade_20180814.loja.model.TableModelProduto;
import atividade_20180814.loja.view.AlterarProdutoView;
import atividade_20180814.loja.view.ProdutoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author larissa
 */
public class AlterarProdutoController {

    Produto modelProduto;
    AlterarProdutoView viewProd;
    Integer idProd;
    ProdutoView view;
    TableModelProduto modelo;

    public AlterarProdutoController(int id, AlterarProdutoView view, ProdutoView v) {
        this.viewProd = view;
        this.idProd = id;
        this.view = v;
       // System.out.println(idProd);

        carregarDados();
        viewProd.AddActionSalvar(new AlterarProduto());
        viewProd.AddActionVoltar(new VoltarListener());
    }

    class AlterarProduto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProdutoDAO dao = new ProdutoDAO();
           // modelProduto = dao.pesquisarPorId(idProd);
            modelProduto.setDescricao(viewProd.getDescricao());
            modelProduto.setQuantidade(viewProd.getQuantidade());
            modelProduto.setValor(viewProd.getValor());

            if (dao.alteraProduto(modelProduto)) {
                viewProd.showMessage("Produto alterado com sucesso!");
                carregarTabela();
            } else {
                viewProd.showMessage("Erro ao alterar produto!");
            }

        }

    }
    
    class VoltarListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {  
            carregarTabela();
            viewProd.dispose();
          
        }
        
    }
    
    
    public void carregarTabela(){
        ProdutoDAO dao = new ProdutoDAO();
        modelo = new TableModelProduto(dao.listarProduto());
        view.setTableModel(modelo);
        view.getTabela().setComponentPopupMenu(view.getPopup());
    }

    public void carregarDados() {
        ProdutoDAO dao = new ProdutoDAO();
        modelProduto = new Produto();
        modelProduto = dao.pesquisarPorId(idProd);
        viewProd.setDescricao(modelProduto.getDescricao());
        viewProd.setQtde(Integer.toString(modelProduto.getQuantidade()));
        viewProd.setValor(Double.toString(modelProduto.getValor()));
    }
}
