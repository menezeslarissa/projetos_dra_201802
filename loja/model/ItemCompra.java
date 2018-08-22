/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.model;

/**
 *
 * @author larissa
 */
public class ItemCompra {
    private Compra Compra;
    private Produto produto;
    private int quantidade;
    private double valorProdutoDiaDaCompra;

    public Compra getCompra() {
        return Compra;
    }

    public void setCompra(Compra Compra) {
        this.Compra = Compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

  
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorProdutoDiaDaCompra() {
        return valorProdutoDiaDaCompra;
    }

    public void setValorProdutoDiaDaCompra(double valorProdutoDiaDaCompra) {
        this.valorProdutoDiaDaCompra = valorProdutoDiaDaCompra;
    }
    
    public double calcValorItem(){
        return valorProdutoDiaDaCompra * quantidade;
    }
    
}
