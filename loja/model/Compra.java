/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author larissa
 */
public class Compra {
    private int idCompra;
    private Calendar dataDaCompra;
    private List<ItemCompra> itens;
    
    public Compra(){
        this.itens = new ArrayList<>();
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public List<ItemCompra> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompra> itens) {
        this.itens = itens;
    }
    
    public double calcValorTotal(){
        double soma = 0;
        for (ItemCompra ic : itens) {
            soma += ic.calcValorItem();
        }
        return soma;
    }
    
    public boolean addItemLista(ItemCompra item){
        this.itens.add(item);
        return true;
    }

    public Calendar getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(Calendar dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    @Override
    public String toString() {
        return "Compra{" + "idCompra=" + idCompra + ", dataDaCompra=" + dataDaCompra + ", itens=" + itens.toString() + '}';
    }
    
    
    
}
