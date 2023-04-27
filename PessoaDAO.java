import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

}
