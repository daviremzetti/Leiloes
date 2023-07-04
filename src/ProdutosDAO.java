/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean cadastrarProduto(ProdutosDTO produto) throws SQLException, ClassNotFoundException {
        conectaDAO conexao = new conectaDAO();
        boolean cadastrado = false;
        try {
            conn = conexao.connectDB();
            if (conn != null) {
                prep = conn.prepareStatement("insert into produtos values (?,?,?,?)");
                prep.setInt(1, 0);
                prep.setString(2, produto.getNome());
                prep.setInt(3, produto.getValor());
                prep.setString(4, produto.getStatus());
                prep.executeUpdate();
                cadastrado = true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Falha no cadastro no banco de dados", "ERRO DE CADASTRO", JOptionPane.ERROR_MESSAGE);
        }
        return cadastrado;
    }


public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}
