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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author larissa
 */
public class CompraDAO {

    private Connection connection;
    private int idGerado;
    private Calendar dataCompra;

    public CompraDAO() {
        connection = FabricaConexao.getConnection();
    }

    public void finalizarCompra(Compra compra) {
        String sql = "insert into compra (compdata) values (sysdate())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            final ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            this.idGerado = rs.getInt(1);
            //connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Integer getIdCompra() {
        return this.idGerado;
    }

    public Date getDataCompra(){
        return retornarData(idGerado);
    }
    public List<Compra> listarCompras() {
        List<Compra> compras = new ArrayList<>();
        String sql = "select compid, compdata from compra";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("compid"));
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs.getDate("compdata"));
                compra.setDataDaCompra(cal);
                compras.add(compra);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return compras;
    }

    public List<ItemCompra> consultarItensCompra(int idCompra){
        List<ItemCompra> itens = new ArrayList<>();
        String sql = "select itemcompracompid, itemcompraproid, itemcompraqtde, itemcompravalor"
                + "from itemcompra"
                + "where itemcompracompid = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, idCompra);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                ItemCompra item = new ItemCompra();
                Produto p = new Produto();
                ProdutoDAO dao = new ProdutoDAO();
                item.setProduto(dao.pesquisarPorId(rs.getInt("itemcompraproid")));
                item.setQuantidade(rs.getInt("itemcompraqtde"));
                item.setValorProdutoDiaDaCompra(rs.getDouble("itemcompravalor"));
                itens.add(item);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return itens;
    }
    
    private Compra pesquisarCompraPorId(int idCompra){
        String sql = "select compid, compdata from compra where compid = ?";
        Compra c;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idCompra);
            ResultSet rs = ps.executeQuery();
            c = new Compra();
            while (rs.next()) {
                c.setIdCompra(rs.getInt("compid"));
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs.getDate("compdata"));
                c.setDataDaCompra(cal);
            }
           connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
        return c;
    }
    
    private Date retornarData(int idCompra){
        String sql = "select compdata from compra where compid = ?";
        Date data = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idCompra);
            ResultSet rs = ps.executeQuery();
            rs.next(); 
            //cal = Calendar.getInstance();
            data = rs.getDate("compdata");
            //System.out.println(cal);
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        } 
        return data;
    }

    
    public Vector<ItemCompra> listarItensCompra(){
        Vector<ItemCompra> itens = new Vector<>();
        String sql = "select itemcompraproid, itemcompracompid, itemcompraqtde, itemcompravalor"
                + "from itemcompra";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                ItemCompra item = new ItemCompra();
                ProdutoDAO dao = new ProdutoDAO();
               
                Compra c = new Compra();
                c.setIdCompra(rs.getInt("itemcompracompid"));
                item.setCompra(c);
                item.setProduto(dao.pesquisarPorId(rs.getInt("itemcompraproid")));
                item.setQuantidade(rs.getInt("itemcompraqtde"));
                item.setValorProdutoDiaDaCompra(rs.getDouble("itemcompravalor"));
                itens.add(item);
            }
           // connection.close();
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return itens;
    }
}
