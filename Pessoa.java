public class Pessoa {
  private int codigo;
  private String nome;
  private String fone;
  private String email;

  public Pessoa(){}

  public Pessoa(String nome, String fone, String email){
    this(0, nome, fone, email); // chamando o construtor com 4 parametros com codigo zerado pq o codigo ja foi inicializado por ser instancia
  }

  public Pessoa(int codigo, String nome, String fone, String email){
    setCodigo(codigo);
    setNome(nome);
    setFone(fone);
    setEmail(email);
}
  
  public Pessoa(int codigo){
    this(codigo, null, null, null); //null é um valor padrão para variaveis de referencia
  }
  
  public int getCodigo() {
    return codigo;
  }

  public String getEmail() {
    return email;
  }

  public String getFone() {
    return fone;
  }
  public String getNome() {
    return nome;
  }
  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public void setFone(String fone) {
    this.fone = fone;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public String toString() {
    return String.format(
      "nome: %s, fone: %s, email: %s",
      nome,
      fone,
      email
    );
  }
}
