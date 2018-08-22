/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.dao;

import atividade_20180814.loja.connection.FabricaConexao;
import atividade_20180814.loja.model.Compra;
import atividade_20180814.loja.model.ItemCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author larissa
 */
public class CompraDAO {
    private Connection connection;
    
    public CompraDAO(){
        connection = FabricaConexao.getConnection();
    }
    
    public int finalizarCompra(Compra compra){
        String sql = "insert into compra values ()";
        try{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            final ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idGerado = rs.getInt(1);
            connection.close();;
            return idGerado;
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    
//    public Vector<ItemCompra> consultarItensCompra(int idCompra){
//        
//        select 
//    }
}
