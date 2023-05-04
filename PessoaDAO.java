import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.*;
//data access object
public class PessoaDAO {
  //CRUD: CREATE, READ, UPDATE, DELETE
  public void cadastrar(Pessoa pessoa) throws Exception{
    //1. Especificar o comando SQL
    String sql = "INSERT INTO tb_pessoa (nome, fone, email) VALUES (?, ?, ?)";
    //2. Abrir uma conexão com o banco
    // var factory = new ConnectionFactory();
    Connection conexao = ConnectionFactory.obterConexao();
    //3. Preparar o comando SQL
    PreparedStatement ps = conexao.prepareStatement(sql);
    //4. Substituir os eventuais placeholders
    ps.setString(1, pessoa.getNome());
    ps.setString(2, pessoa.getFone());
    ps.setString(3, pessoa.getEmail());
    //5. Executar o comando
    ps.execute();
    //6. fecha as coisas  
    ps.close();
    conexao.close();
  }

  public void apagar (Pessoa p) throws Exception{
    //1. Especificar o comando SQL
    String sql = "DELETE FROM tb_pessoa WHERE cod_pessoa = ?";
    //2. Abrir uma conexão
    var conexao = ConnectionFactory.obterConexao();
    //3. Preparar o comando
    var ps = conexao.prepareStatement(sql);
    //4. Substituir os eventuais placeholders
    ps.setInt(1, p.getCodigo());
    //5. Executar
    ps.execute();
    //6. Fechar as coisas
    ps.close();
    conexao.close();    
  }

  Pessoa buscarPeloCodigo(int codigo) throws Exception{
    String sql = "SELECT nome, fone, email FROM tb_pessoa WHERE cod_pessoa = ?";
    //try-with-resources
    try(
      Connection conexao = ConnectionFactory.obterConexao();
      PreparedStatement ps = conexao.prepareStatement(sql);
    ){
      ps.setInt(1, codigo);
      ResultSet rs = ps.executeQuery();
      return rs.next() ? new Pessoa(rs.getString("nome"), rs.getString("fone"), rs.getString("email")) : null;
    }
  }

  List <Pessoa> Listar() throws Exception {
    List <Pessoa> pessoas = new LinkedList <> ();
    //var pessoas = new LinkedList <Pessoa> (); // linkedlist == lista duplamente ligada
    //1. Especificar o comando SQL (SELECT)
    String sql = "SELECT * FROM tb_pessoa";
    //2. Abri uma conexão com Postgre SQL
    //3. Preparar comando
    try(
      var conexao = ConnectionFactory.obterConexao();
        var ps = conexao.prepareStatement(sql);
          var rs = ps.executeQuery()){
            //Manipular os dados da tebla

          while(rs.next()){ // nao precisa de == true pq expressão ja é booleana
            int codigo = rs.getInt("cod_pessoa");
            String nome = rs.getString("nome");
            String fone = rs.getString("fone");
            String email = rs.getString("email");
            var p = new Pessoa(codigo, nome, fone, email);
            pessoas.add(p);
            } 
            //7. Fechar os recursos
            //ja foi feito pelo try-with-resources
            return pessoas;
          }
  }

}
