package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static String url = "jdbc:mysql://localhost:3306/cadastro";
    private static String user = "root";
    private static String password = "";

    private static Connection conexao;
    
    public static Connection getconexao(){
        
        try{
            
            if(conexao == null){

                return DriverManager.getConnection(url, user, password);

            } else{
                
                return conexao;
            }

        }catch(SQLException e){
            System.out.println("Erro ao conectar com banco de dados!");
            e.printStackTrace();
            return null;
        }
    }

}
