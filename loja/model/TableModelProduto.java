/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.model;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author larissa
 */
public class TableModelProduto extends AbstractTableModel {

    private Produto produto;
    private Vector<Produto> produtos;
    
    public TableModelProduto(Vector<Produto> p){
        this.produtos = p;
    }
    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        produto = produtos.get(linha);
        
        switch(coluna){
            case 0:
                return produto.getIdProduto();
            case 1:
                return produto.getDescricao();
            case 2: 
                return produto.getQuantidade();
            case 3:
                return produto.getValor();
        }
        return null;
    }
    
    public String getColumnName(int coluna){
        switch(coluna){
            case 0:
                return "Id";
            case 1:
                return "Descriçao";
            case 2:
                return "Quantidade";
            case 3:
                return "Valor";
        }
        return null;
    }
    
}
