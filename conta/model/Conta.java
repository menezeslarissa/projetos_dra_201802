/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula_20180731.conta.model;

/**
 *
 * @author Aluno
 */
public class Conta {

    private int numero;
    private double saldo;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean sacarValor(double valor) {
        if (valor < saldo || valor == saldo) {
            this.saldo -= valor;
            return true;
        } else if (valor > saldo) {
            return false;
        }
        return false;
    }

    public boolean depositarValor(double valor) {
        if (valor < 0) {
            return false;
        } else {
            this.saldo += valor;
            return true;
        }
    }
}
