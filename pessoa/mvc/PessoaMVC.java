/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_20180731.pessoa.mvc;

import aula_20180731.pessoa.controller.PessoaController;
import aula_20180731.pessoa.model.Pessoa;
import aula_20180731.pessoa.view.PessoaView;

/**
 *
 * @author Aluno
 */
public class PessoaMVC {
    public static void main(String[] args) {
        Pessoa model = new Pessoa();
        PessoaView view = new PessoaView();
        
        PessoaController control = new PessoaController(model, view);
        
        view.setVisible(true);
    }
}
