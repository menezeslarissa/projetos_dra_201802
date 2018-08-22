/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_20180814.loja.dao;

import atividade_20180814.loja.connection.FabricaConexao;
import atividade_20180814.loja.model.Produto;
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
public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() {
        this.connection = FabricaConexao.getConnection();
    }

    public boolean addProduto(Produto produto) {
        String sql = "insert into produto (prodescricao, proqtde, provalor) values (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, produto.getDescricao());
            ps.setInt(2, produto.getQuantidade());
            ps.setDouble(3, produto.getValor());
            ps.execute();

            final ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            produto.setIdProduto(rs.getInt(1));
            System.out.println(produto.getIdProduto());
            connection.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean delProduto(int id) {
        String sql = "delete from produto where proid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

            connection.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Produto pesquisarPorId(int id) {
        String sql = "select proid, prodescricao, proqtde,provalor from produto where proid = ?";
        Produto p;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            p = new Produto();
            while (rs.next()) {
                p.setIdProduto(rs.getInt("proid"));
                p.setDescricao(rs.getString("prodescricao"));
                p.setQuantidade(rs.getInt("proqtde"));
                p.setValor(rs.getDouble("provalor"));
            }
           connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
        return p;
    }

    public boolean alteraProduto(Produto produto) {
        String sql = "update produto set prodescricao=?, proqtde=?, provalor=? where proid=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, produto.getDescricao());
            ps.setInt(2, produto.getQuantidade());
            ps.setDouble(3, produto.getValor());
            ps.setInt(4, produto.getIdProduto());
            ps.execute();
            
            connection.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 

    }

    public Vector<Produto> listarProduto() {
        Vector<Produto> produtos = new Vector<>();
        String sql = "select proid, prodescricao, proqtde, provalor from produto";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("proid"));
                p.setDescricao(rs.getString("prodescricao"));
                p.setQuantidade(rs.getInt("proqtde"));
                p.setValor(rs.getDouble("provalor"));
                produtos.add(p);
            }
            connection.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return produtos;
    }

    public Vector<Produto> pesquisarPorTexto(String texto) {
        Vector<Produto> produtos = new Vector<>();
        String comando = "";
        try {
            PreparedStatement instrucao = null;

            if (texto.trim().equals("")) {
                comando = "select proid, prodescricao, proqtde, provalor FROM produto ";
                instrucao = connection.prepareStatement(comando);
            } else {

                comando = "select proid, prodescricao, proqtde, provalor FROM produto  "
                        + " WHERE prodescricao LIKE ?";
                instrucao = connection.prepareStatement(comando);
                instrucao.setString(1, "%" + texto + "%");

            }

            ResultSet resultado = instrucao.executeQuery();

            while (resultado.next()) {
                Produto p = new Produto();

                p.setIdProduto(resultado.getInt("proid"));
                p.setDescricao(resultado.getString("prodescricao"));
                p.setQuantidade(resultado.getInt("proqtde"));
                p.setValor(resultado.getDouble("provalor"));
                produtos.add(p);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }
}
