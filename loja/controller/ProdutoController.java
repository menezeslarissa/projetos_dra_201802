/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.controller;

import atividade_20180814.loja.dao.ProdutoDAO;
import atividade_20180814.loja.exception.ItemCompraException;
import atividade_20180814.loja.model.Produto;
import atividade_20180814.loja.model.TableModelProduto;
import atividade_20180814.loja.view.AlterarProdutoView;
import atividade_20180814.loja.view.Principal;
import atividade_20180814.loja.view.ProdutoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author larissa
 */
public class ProdutoController {

    Produto modelProduto;
    ProdutoView viewProd;
    TableModelProduto modelo;

    public ProdutoController(ProdutoView v, Produto p, TableModelProduto model) {
        this.modelProduto = p;
        this.viewProd = v;
        this.modelo = model;

        carregarTodosProd();

        carregarMenuPopUp();

        viewProd.AddActionAdicionar(new AdicionarProdListener());
        viewProd.AddActionVoltar(new VoltarListener());
        viewProd.AddActionPesquisar(new PesquisarProdutos());
        //viewProd.addActionMenuItemAlterar(new AlterarProdutoListener());
    }

    class AdicionarProdListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                camposVazios();
                modelProduto.setDescricao(viewProd.getDescricao());
                modelProduto.setQuantidade(viewProd.getQuantidade());
                modelProduto.setValor(viewProd.getValor());

                ProdutoDAO dao = new ProdutoDAO();
                boolean cadastro = dao.addProduto(modelProduto);
                if (cadastro) {
                    viewProd.showMessage("Produto cadastrado com sucesso!");
                } else {
                    viewProd.showMessage("Cadastro nao realizado!");
                }
                limparCampos();
                carregarTabela();
                carregarMenuPopUp();
            } catch (ItemCompraException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    class VoltarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewProd.dispose();
        }

    }

    class PesquisarProdutos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProdutoDAO dao = new ProdutoDAO();
            dao.pesquisarPorTexto(viewProd.getTextoPesquisar());
            carregarTabela();
            carregarMenuPopUp();
        }

    }

    public void carregarTabela() {
        ProdutoDAO dao = new ProdutoDAO();
        String texto = viewProd.getTextoPesquisar();
        modelo = new TableModelProduto(dao.pesquisarPorTexto(texto));
        viewProd.setTableModel(modelo);
        viewProd.getTabela().setComponentPopupMenu(viewProd.getPopup());
        //System.out.println(viewProd.getTabela().getSelectedRow());
    }

    public void carregarTodosProd() {
        ProdutoDAO dao = new ProdutoDAO();
        modelo = new TableModelProduto(dao.listarProduto());
        viewProd.setTableModel(modelo);
        viewProd.getTabela().setComponentPopupMenu(viewProd.getPopup());
        //System.out.println(viewProd.getTabela().getSelectedRow());
    }

    private Produto selecionarProduto(MouseEvent evt) {
        int linha = viewProd.getTabela().rowAtPoint(evt.getPoint());
        System.out.println(linha);

        if (linha >= 0) {
            viewProd.getTabela().setRowSelectionInterval(linha, linha);

            //System.out.println(linha);
            linha = viewProd.getTabela().getSelectedRow();
            //System.out.println(linha);
            ProdutoDAO dao = new ProdutoDAO();

            this.modelProduto = dao.pesquisarPorTexto(viewProd.getTextoPesquisar()).get(linha);

        }
        return modelProduto;
    }

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {
        carregarTabela();
        carregarMenuPopUp();
    }

    private void realizarAcao(MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() > 1) {

            }
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            viewProd.getPopup().show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }

    public void tabelaProdMouseReleased(MouseEvent evt) {
        selecionarProduto(evt);
        realizarAcao(evt);
    }

    public int pegarId() {
        int linha = viewProd.getTabela().getSelectedRow();
        int id = Integer.parseInt(viewProd.getTabela().getValueAt(linha, 0).toString());
        return id;
    }

    public void carregarMenuPopUp() {
        viewProd.getPopup().removeAll();
        JMenuItem itemAlterar = new JMenuItem("Alterar");
        JMenuItem itemExcluir = new JMenuItem("Excluir");

        itemAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlterarProdutoView view = new AlterarProdutoView();
                int id = pegarId();
                AlterarProdutoController c = new AlterarProdutoController(id, view, viewProd);
                view.setVisible(true);
                //carregarTabela();
            }
        });
        //carregarTabela();

        itemExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão do registro?", "Excluir", JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {
                    try {
                        ProdutoDAO dao = new ProdutoDAO();
                        System.out.println("excluir: " + pegarId());
                        dao.delProduto(pegarId());
                        System.out.println(modelProduto.getIdProduto());
                        JOptionPane.showMessageDialog(null, "Registro excluído com sucesso.");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                carregarTabela();

            }
        });
        viewProd.getPopup().add(itemAlterar);
        viewProd.getPopup().add(itemExcluir);
        carregarTabela();

    }

    public void limparCampos() {
        viewProd.getTxtFieldDescricao().setText("");
        viewProd.getTxtFieldQuantidade().setText("");
        viewProd.getTxtFieldValor().setText("");
    }

    public void camposVazios() throws ItemCompraException {

        if (viewProd.getTxtFieldQuantidade().getText().equals("") || viewProd.getTxtFieldQuantidade().equals("") || viewProd.getTxtFieldValor().getText().equals("")
                || viewProd.getTxtFieldValor().getText().equals("")) {
            throw new ItemCompraException(viewProd, "Preencha todos os campos!");
        }
    }

}
