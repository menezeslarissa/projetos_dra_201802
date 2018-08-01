/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_20180731.calculadora.mvc;

import aula_20180731.calculadora.controller.CalculadoraController;
import aula_20180731.calculadora.model.Calculadora;
import aula_20180731.calculadora.view.CalculadoraView;

/**
 *
 * @author Aluno
 */
public class CalculadoraMVC {

    public static void main(String[] args) {
        CalculadoraView calcView = new CalculadoraView();
        Calculadora calcModel = new Calculadora();

        CalculadoraController calcControl = new CalculadoraController(calcView, calcModel);
        calcView.setVisible(true);
   
    }
}
