/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180807.pedido.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class Pedido {

    private List<ItemPedido> itens;

    public Pedido() {
        itens = new ArrayList<>();
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void addItem(ItemPedido item) {
        itens.add(item);
    }

    public double calcValorTotal() {
        double soma = 0;
        for (ItemPedido p : itens) {
            soma += p.calcValorItem();
        }
        return soma;
    }

    @Override
    public String toString() {
        String s = "";
       for(ItemPedido p : this.itens){
           s+="Produto: " + p.getProduto();
           s+=" || Quantidade: " + p.getQuantidade();
           s+=" || Valor: " + p.getValor();
           s+="\n";
       }
       return s;
    }

}
