/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.exception;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author larissa
 */
public class ItemCompraException extends Exception {
    public ItemCompraException(Component c, String msg ){
        JOptionPane.showMessageDialog(c, msg);
    }
}
