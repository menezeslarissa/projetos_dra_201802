/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_20180731.conta.controller;

import aula_20180731.conta.model.Conta;
import aula_20180731.conta.view.ContaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 *
 * @author Aluno
 */
public class ContaController {

    private Conta contaModel;
    private ContaView contaView;

    public ContaController(Conta c, ContaView view) {
        this.contaModel = c;
        this.contaView = view;

        this.contaView.addBtnDepositar(new DepositarListener());
        this.contaView.addBtnSacar(new SacarListener());

    }

    class DepositarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double valor = contaView.getValor();
            if(contaModel.depositarValor(valor) == false){
               contaView.displayMensagemDeErro("Impossível depositar valores abaixo de 0!");
            }
                
            contaView.setSaldo(contaModel.getSaldo());
        }

    }

    class SacarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double valor = contaView.getValor();
            if (contaModel.sacarValor(valor)) {
                contaView.setSaldo(contaModel.getSaldo());
            } else {
                contaView.displayMensagemDeErro("Saldo insuficiente para saque!");
            }

        }

    }

}
