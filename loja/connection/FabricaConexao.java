/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author larissa
 */
public class FabricaConexao {

    public static Connection getConnection (){
        String host = "jdbc:mysql://localhost/compras";
        String user = "root";
        String password = "root";

        try {
            return DriverManager.getConnection(host, user, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
