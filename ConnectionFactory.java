import java.sql.*;
public class ConnectionFactory {
  
  public static Connection obterConexao() throws Exception{
    String host = "localhost";
    String port = "5432";
    String db = "20231_ipi_poo_pessoas";
    String user = "postgres";
    String password = "123456";
    String stringDeConexao = String.format(
      "jdbc:postgresql://%s:%s/%s",
      host, port, db
    );
    return DriverManager.getConnection(
      stringDeConexao, 
      user, 
      password
    );
    //cláusula catch or declare
  }
}
