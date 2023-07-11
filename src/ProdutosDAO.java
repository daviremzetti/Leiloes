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

    public ArrayList<ProdutosDTO> listarProdutos() throws ClassNotFoundException {
        conectaDAO conexao = new conectaDAO();
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        try {
            conn = conexao.connectDB();
            prep = conn.prepareStatement("select * from produtos");
            resultset = prep.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("id");
                String nome = resultset.getString("nome");
                int valor = resultset.getInt("valor");
                String status = resultset.getString("status");
                ProdutosDTO novoProduto = new ProdutosDTO();
                novoProduto.setId(id);
                novoProduto.setNome(nome);
                novoProduto.setValor(valor);
                novoProduto.setStatus(status);
                listagem.add(novoProduto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Falha na consulta no banco de dados", "ERRO DE CONSULTA", JOptionPane.ERROR_MESSAGE);
        }

        return listagem;
    }

    public void venderProdutos(int id) throws SQLException, ClassNotFoundException {
        conectaDAO conexao = new conectaDAO();
        boolean encontrado = false;

        try {
            conn = conexao.connectDB();
            prep = conn.prepareStatement("select status from produtos where id = ?");
            prep.setInt(1, id);
            resultset = prep.executeQuery();
            while (resultset.next()) {
                String status = resultset.getString("status");
                if (status.equals("A Venda")) {
                    prep = conn.prepareStatement("update produtos set status = 'Vendido' where id = ?");
                    prep.setInt(1, id);
                    prep.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
                    encontrado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Produto já se encontra vendido");
                    encontrado = true;
                }
            }
            
            if(encontrado == false){
                JOptionPane.showMessageDialog(null, "Produto não vendido", "ID INEXISTENTE", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Falha na conexão");
        }
    }

}
