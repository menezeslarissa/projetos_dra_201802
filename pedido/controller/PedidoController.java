/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180807.pedido.controller;

import atividade_20180807.pedido.model.ItemPedido;
import atividade_20180807.pedido.model.Pedido;
import atividade_20180807.pedido.view.PedidoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class PedidoController {
    PedidoView pedidoView;
    Pedido pedidoModel;
    ItemPedido itemPedidoModel;
    List<ItemPedido> itens;
    
    public PedidoController(PedidoView pedidoView, Pedido pedidoModel, ItemPedido itemPedidoModel) {
        this.pedidoView = pedidoView;
        this.pedidoModel = pedidoModel;
        this.itemPedidoModel = itemPedidoModel;
        
        itens = new ArrayList<>();
        
        this.pedidoView.addItemListener(new ItemListener());  
        this.pedidoView.addNovoPedidoListener(new NovoPedidoListener());
        this.pedidoView.excluirItens(new ExcluirItemListener());
    }
    
    class ItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            itemPedidoModel = new ItemPedido();
            itemPedidoModel.setProduto(pedidoView.getNome());
            itemPedidoModel.setQuantidade(pedidoView.getQuantidade()); 
            itemPedidoModel.setValor(pedidoView.getValor());
            itens.add(itemPedidoModel);
            pedidoModel.setItens(itens);
            
            pedidoView.displayMensagem("Item adicionado!");
            
            pedidoView.mostrarItens(pedidoModel.toString());
            pedidoView.setValorTotal(pedidoModel.calcValorTotal());
            pedidoView.limparCampos();
        }  
}
    
    class NovoPedidoListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            itens.clear();
            pedidoView.limparCampos();
            pedidoView.limpar();
        }
    
}
    class ExcluirItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            pedidoView.limparCampos();
            pedidoView.limpar();
            itens.clear();
        }
     
    }
    
}
