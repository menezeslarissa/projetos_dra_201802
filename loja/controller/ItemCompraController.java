/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.controller;

import atividade_20180814.loja.dao.CompraDAO;
import atividade_20180814.loja.dao.ItemCompraDAO;
import atividade_20180814.loja.dao.ProdutoDAO;
import atividade_20180814.loja.exception.ItemCompraException;
import atividade_20180814.loja.model.Compra;
import atividade_20180814.loja.model.ItemCompra;
import atividade_20180814.loja.model.Produto;
import atividade_20180814.loja.model.TableModelItemCompra;
import atividade_20180814.loja.view.ItemCompraView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author larissa
 */
public class ItemCompraController {

    private ItemCompra item;
    private ItemCompraView view;
    private Compra c;
    private Vector<Produto> produtos;
    private ItemCompraDAO dao;
    private TableModelItemCompra modelo;
    private Vector<ItemCompra> itens = new Vector<>();

    public ItemCompraController(ItemCompra i, ItemCompraView view) {
        this.c = new Compra();
        this.item = i;
        this.view = view;
        produtos = new Vector<>();

        iniciarCompra();
        carregarProdutos();
        limparTabela();
        carregarMenuPopUp();
        view.setMenuPopup(view.getPopup());

        view.AddActionAdicionar(new AdicionarItem());
        view.AddActionVoltar(new VoltarListener());
        view.AddActionFinalizar(new FinalizarListener());
       
    }

   
    class AdicionarItem implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                camposVazios();
                ItemCompra item = new ItemCompra();
                item.setCompra(c);
                item.setProduto(getProdutoSelecionado());
                System.out.println(getProdutoSelecionado().getIdProduto());
                System.out.println(item.getProduto().getDescricao() + item.getProduto().getIdProduto());
                item.setQuantidade(Integer.parseInt(view.getQuantidade().getText()));
                item.setValorProdutoDiaDaCompra(Double.parseDouble(view.getValor().getText()));
                c.addItemLista(item);
                dao = new ItemCompraDAO();
                dao.registrarItem(item);
                limparCampos();
                carregarTabelaTodosItens();
                //  carregarMenuPopUp();
                view.setValorTotalDaCompra(Double.toString(c.calcValorTotal()));
            } catch (ItemCompraException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    class FinalizarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (c.calcValorTotal() <= 0) {
                view.showMessage("Adicione itens para finalizar a compra!");
            } else {
                view.msgFinal("ID Compra = " + c.getIdCompra() + "\nValor Total da Compra = " + c.calcValorTotal(), JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
            }

        }

    }

    public void carregarTabelaTodosItens() {
        ItemCompraDAO dao = new ItemCompraDAO();
        itens = dao.listarItensCompraAtual(c.getIdCompra());
        modelo = new TableModelItemCompra(itens);
        view.setTableModel(modelo);
        //carregarMenuPopUp();
    }

    public void limparTabela() {
        ItemCompraDAO dao = new ItemCompraDAO();
        itens = dao.listarItensCompra();
        itens.clear();
        modelo = new TableModelItemCompra(itens);
        view.setTableModel(modelo);

    }

    public void carregarProdutos() {
        view.getCombo().removeAllItems();
        ProdutoDAO dao = new ProdutoDAO();
        for (Produto p : dao.listarProduto()) {
            view.getCombo().addItem(p.getDescricao());
            this.produtos.add(p);
        }
    }

    class VoltarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }

    }

    private void realizarAcao(MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() > 1) {

            }
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            view.getPopup().show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }

    public void tabelaProdMouseReleased(MouseEvent evt) {
        realizarAcao(evt);
    }

    public void carregarMenuPopUp() {
        JMenuItem itemExcluir = new JMenuItem("Excluir");

        itemExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão do registro?", "Excluir", JOptionPane.YES_NO_OPTION);

                if (resposta == JOptionPane.YES_OPTION) {
                    try {
                        ItemCompraDAO dao = new ItemCompraDAO();
                        System.out.println("excluir: " + c.getIdCompra());
                        System.out.println("id prod: " + modelo.getIdProd());
                        dao.delItem(c.getIdCompra(), modelo.getIdProd());
                        System.out.println(modelo.getIdProd());
                        JOptionPane.showMessageDialog(null, "Registro excluído com sucesso.");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                carregarTabelaTodosItens();

            }
        });

        view.getPopup().add(itemExcluir);
    }

    public Produto getProdutoSelecionado() {
        return this.produtos.get(view.getCombo().getSelectedIndex());
    }

    public void iniciarCompra() {
        CompraDAO dao = new CompraDAO();
        c.setIdCompra(dao.finalizarCompra(c));
        System.out.println(c.getIdCompra());
    }

    public Vector<ItemCompra> getLista(){
        ItemCompraDAO dao = new ItemCompraDAO();
        return dao.listarItensCompra();
    }
    public void limparCampos() {
        view.getQuantidade().setText("");
        view.getValor().setText("");
    }

    public void camposVazios() throws ItemCompraException {
        if (view.getQuantidade().getText().equals("") || view.getQuantidade().getText().equals("") || view.getValor().getText().equals("")
                || view.getValor().getText().equals("")) {
            throw new ItemCompraException(view, "Preencha todos os campos!");
        }
    }

}
