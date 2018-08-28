/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.dao;

import atividade_20180814.loja.connection.FabricaConexao;
import atividade_20180814.loja.model.Compra;
import atividade_20180814.loja.model.ItemCompra;
import atividade_20180814.loja.model.Produto;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author larissa
 */
public class ItemCompraDAO {

    Connection connection;

    public ItemCompraDAO() {
        this.connection = FabricaConexao.getConnection();
    }

    public boolean registrarItem(ItemCompra item) throws MySQLIntegrityConstraintViolationException, SQLException{
        String sql = "insert into itemcompra (itemcompracompid, itemcompraproid, itemcompraqtde, itemcompravalor) values (?,?,?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, item.getCompra().getIdCompra());
            ps.setInt(2, item.getProduto().getIdProduto());
            ps.setInt(3, item.getQuantidade());
            ps.setDouble(4, item.getValorProdutoDiaDaCompra());
            ps.execute();
            connection.close();
            return true;
        } catch (MySQLIntegrityConstraintViolationException ex) {
           throw new MySQLIntegrityConstraintViolationException(); 
        }
    }
    
    public Vector<ItemCompra> listarItensCompraAtual(int idCompra){
        Vector<ItemCompra> itens = new Vector<>();
       
        String sql = "select itemcompracompid, itemcompraproid, itemcompraqtde, itemcompravalor, proid, prodescricao, provalor, proqtde from itemcompra "
                + "inner join produto where itemcompraproid = proid and "
                + "itemcompracompid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idCompra);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ItemCompra i = new ItemCompra();
                    Compra c = new Compra();
                    c.setIdCompra(rs.getInt("itemcompracompid"));
                    i.setCompra(c);
                    Produto p = new Produto();
                    p.setIdProduto(rs.getInt("proid"));
                    p.setDescricao(rs.getString("prodescricao"));
                    p.setQuantidade(rs.getInt("proqtde"));
                    p.setValor(rs.getDouble("provalor"));
                    i.setProduto(p);
                    i.setQuantidade(rs.getInt("itemcompraqtde"));
                    i.setValorProdutoDiaDaCompra(rs.getDouble("itemcompravalor"));

                    itens.add(i);
                }
            }

            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return itens;
    }
    
    public Vector<ItemCompra> listarItensCompra() {
        Vector<ItemCompra> itens = new Vector<>();
       
        String sql = "select itemcompracompid, itemcompraproid, itemcompraqtde, itemcompravalor, proid, prodescricao, provalor, proqtde from itemcompra "
                + "inner join produto where itemcompraproid = proid";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ItemCompra i = new ItemCompra();
                    Compra c = new Compra();
                    c.setIdCompra(rs.getInt("itemcompracompid"));
                    i.setCompra(c);
                    Produto p = new Produto();
                    p.setIdProduto(rs.getInt("proid"));
                    p.setDescricao(rs.getString("prodescricao"));
                    p.setQuantidade(rs.getInt("proqtde"));
                    p.setValor(rs.getDouble("provalor"));
                    i.setProduto(p);
                    i.setQuantidade(rs.getInt("itemcompraqtde"));
                    i.setValorProdutoDiaDaCompra(rs.getDouble("itemcompravalor"));

                    itens.add(i);
                }
            }

            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return itens;
    }
    
 
    
    
    public boolean delItem(int idCompra, int idProd) {
        String sql = "delete from itemcompra where itemcompracompid = ? and itemcompraproid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idCompra);
            ps.setInt(2, idProd);
            ps.execute();

            connection.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
