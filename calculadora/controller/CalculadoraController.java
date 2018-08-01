/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_20180731.calculadora.controller;

import aula_20180731.calculadora.model.Calculadora;
import aula_20180731.calculadora.view.CalculadoraView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Aluno
 */
public class CalculadoraController {

    private CalculadoraView view;
    private Calculadora model;

    public CalculadoraController(CalculadoraView calcView, Calculadora calcModel) {
        this.view = calcView;
        this.model = calcModel;

        this.view.addButtonSomarListener(new SomarListener());
    }
    

    class SomarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double num1 = Double.parseDouble(view.getNum1());
                double num2 = Double.parseDouble(view.getNum2());

                model.setNum1(num1);
                model.setNum2(num2);
                
                view.setResultado(Double.toString(model.somar()));

            } catch(NumberFormatException ex){
                view.displayMensagemDeErro("Entre com dois valores numéricos!");
            }
        }

    }

}
