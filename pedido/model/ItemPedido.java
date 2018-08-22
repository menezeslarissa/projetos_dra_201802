/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180807.pedido.model;

/**
 *
 * @author Aluno
 */
public class ItemPedido {
    private String produto;
    private double valor;
    private int quantidade;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double calcValorItem(){
        return valor * quantidade;
    }

    //@Override
//    public String toString() {
//        return  "Produto: " + produto + ", Quantidade: " + quantidade + ", Valor: " + valor;
//    }
    
}
