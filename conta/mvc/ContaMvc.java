/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_20180731.conta.mvc;

import aula_20180731.conta.controller.ContaController;
import aula_20180731.conta.model.Conta;
import aula_20180731.conta.view.ContaView;

/**
 *
 * @author Suporte
 */
public class ContaMvc {
    public static void main(String[] args) {
        ContaView view = new ContaView();
        Conta model = new Conta();
        ContaController controller = new ContaController(model, view);
        
        view.setVisible(true);
    }
}
