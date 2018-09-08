/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.model;

import atividade_20180814.loja.util.DataUtil;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author larissa
 */
public class TableModelCompra extends AbstractTableModel {
    private Compra compra;
    private List<Compra> compras;
    
    public TableModelCompra(List<Compra> compras){
        this.compras = compras;
    }

    @Override
    public int getRowCount() {
        return compras.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
       compra= compras.get(linha);
       switch(coluna){
           case 0:
              return compra.getIdCompra();
           case 1:
               
              return DataUtil.ConverterDataEmTexto(compra.getDataDaCompra());
           case 2:
               return compra.getItens().size();
           case 3:
               return compra.calcValorTotal();
       }
       return null;
    }
   
    public String getColumnName(int coluna){
        switch(coluna){
            case 0:
                return "Id Compra";
            case 1:
                return "Data";
            case 2:
                return "Quantidade de itens";
            case 3:
                return "Valor Total";
        }
        return null;
    }
    
}
