/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_20180731.pessoa.controller;

import aula_20180731.pessoa.model.Pessoa;
import aula_20180731.pessoa.view.PessoaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Aluno
 */
public class PessoaController {
    Pessoa modelPessoa = new Pessoa();
    PessoaView viewPessoa = new PessoaView();

    public PessoaController(Pessoa p, PessoaView pv) {
        this.modelPessoa = p;
        this.viewPessoa = pv;
        
        viewPessoa.addCalcularListener(new CalcularListener());
    }
    
    class CalcularListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
        
           DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
           modelPessoa.setDataNascimento(LocalDate.parse(viewPessoa.getDataNasc(), formatador));
           LocalDate localDate = LocalDate.now();
           int anoAtual = localDate.getYear();
           viewPessoa.setIdade(Integer.toString(modelPessoa.calcIdade(anoAtual)));
        }
        
    }
}
