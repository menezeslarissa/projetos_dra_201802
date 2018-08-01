/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_20180731.pessoa.model;

import java.time.LocalDate;

/**
 *
 * @author Aluno
 */
public class Pessoa {
   private LocalDate dataNascimento;

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
   
    public int calcIdade(int anoAtual){
      return anoAtual - dataNascimento.getYear();
    }
}
