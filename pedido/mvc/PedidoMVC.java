/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180807.pedido.mvc;

import atividade_20180807.pedido.controller.PedidoController;
import atividade_20180807.pedido.model.ItemPedido;
import atividade_20180807.pedido.model.Pedido;
import atividade_20180807.pedido.view.PedidoView;

/**
 *
 * @author larissa
 */
public class PedidoMVC {
    public static void main(String[] args) {
        ItemPedido itemPedido = new ItemPedido();
        Pedido pedido = new Pedido();
        PedidoView pedidoView = new PedidoView();
        PedidoController pedidoController = new PedidoController(pedidoView, pedido, itemPedido);
        
        pedidoView.setVisible(true);
    }
}
