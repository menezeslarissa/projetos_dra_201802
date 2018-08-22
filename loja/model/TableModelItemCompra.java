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
public class TableModelItemCompra extends AbstractTableModel{
    private ItemCompra item;
    private Vector<ItemCompra> itens;
    
    public TableModelItemCompra(Vector<ItemCompra> itensCompra){
        this.itens = itensCompra;
    }

    @Override
    public int getRowCount() {
        return itens.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
       item= itens.get(linha);
       switch(coluna){
           case 0:
              return item.getCompra().getIdCompra();
           case 1:
              return item.getProduto().getDescricao();
           case 2:
               return item.getQuantidade();
           case 3:
               return item.getValorProdutoDiaDaCompra();
           case 4:
               return item.calcValorItem();
       }
       return null;
    }
    
    public Integer getIdProd(){
        return item.getProduto().getIdProduto();
    }
    public String getColumnName(int coluna){
        switch(coluna){
            case 0:
                return "Id Compra";
            case 1:
                return "Produto";
            case 2:
                return "Quantidade";
            case 3:
                return "Valor";
            case 4:
                return "Valor Total";
        }
        return null;
    }
    
    
    
}
