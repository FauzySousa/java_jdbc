package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import model.Usuario;

public class UsuarioDAO {
    
    

    public void cadastrar(Usuario u){
       
        String sql = "INSERT INTO usuario (NOME,LOGIN,SENHA,EMAIL) VALUES (?, ?, ?, ? )";

        try(Connection conn = Conexao.getconexao();
            PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1,u.getNome());
                ps.setString(2,u.getLogin());
                ps.setString(3,u.getPassword());
                ps.setString(4,u.getEmail());

                ps.executeUpdate();
                System.out.println("usuario cadastrado com sucesso!");

            }catch(SQLException e){

                System.out.println("erro ao cadastrar usuario!");
                e.printStackTrace();
            }
    }

    public List<Usuario> listar(){
       
        String sql = "SELECT * FROM usuario";
        List<Usuario> lisuser = new ArrayList<Usuario>();

        try(Connection conn = Conexao.getconexao();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setCodigo(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setLogin(rs.getString("login"));
                    u.setPassword(rs.getString("senha"));
                    u.setEmail(rs.getString("email"));

                    lisuser.add(u);
                }
                           
        } catch (Exception e) {
            System.out.println("erro ao listar");
            e.printStackTrace();
            
        }

        return lisuser;
    }

    public void atualizar(Usuario u){
        
        String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, email = ? WHERE id = ?";

        try(Connection conn = Conexao.getconexao();
            PreparedStatement ps = conn.prepareStatement(sql)){
                
                ps.setString(1, u.getNome());
                ps.setString(2, u.getLogin());
                ps.setString(3, u.getPassword());
                ps.setString(4, u.getEmail());
                ps.setInt(5, u.getCodigo());

                ps.executeUpdate();
                System.out.println("usuario atualizado com sucesso!");

        } catch (Exception e) {

            System.out.println("erro ao atualizar usuario!");
            e.printStackTrace();

        }
    }

    public void deletar(int id){
        String sql = "DELETE FROM usuario WHERE id = ?";

        try(Connection conn = Conexao.getconexao();
            PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("usuario deletado com sucesso!");

            }catch(SQLException e){
                System.out.println("erro ao deletar usuario!");
                e.printStackTrace();


            }
    }

}
