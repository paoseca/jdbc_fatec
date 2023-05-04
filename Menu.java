import java.util.List;

import javax.swing.JOptionPane;

public class Menu {
  public static void main(String[] args) {
    //1. Cadastrar
    //2. Atualizar
    //3. Remover
    //4. Visualizar dados de uma pessoa
    //5. Visualizar todas as pessoas
    //6. Sair
    String opcoes = "1-Cadastrar\n2-Atualizar\n3-Remover\n4-Visualizar uma pessoa\n5-Visualizar todas as pessoas\n6-Sair";
    int op = 6;
    PessoaDAO dao = new PessoaDAO();
    do{
      try{
        op = Integer.parseInt(JOptionPane.showInputDialog(opcoes));
        switch(op){
          case 1:
            String nome = JOptionPane.showInputDialog("Nome?");
            String fone = JOptionPane.showInputDialog("Fone?");
            String email = JOptionPane.showInputDialog("Email?");
            dao.cadastrar(new Pessoa(nome, fone, email));
            JOptionPane.showMessageDialog(null, "Cadastro ok!");
          break;
          case 2:
          //atualizar
          break;
          case 3:{
            int codigo = 
            Integer.parseInt(JOptionPane.showInputDialog("Código?"));
            dao.apagar(new Pessoa(codigo));
            JOptionPane.showMessageDialog(null, "Ok, apagou!");
            break;
          }
          case 4:{
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código?"));
            Pessoa p = dao.buscarPeloCodigo(codigo);
            if (p != null){
              JOptionPane.showMessageDialog(null, p);
            }
            else
              JOptionPane.showMessageDialog(null, "Pessoa não existe");
            break;
          }
          case 5:{
            List<Pessoa> pessoas = dao.Listar();
            StringBuilder sb = new StringBuilder("");
            //for each | enhanced for
            for(Pessoa p : pessoas) {
              sb.append(p).append("\n");
            }
            JOptionPane.showMessageDialog((null), sb);

          /*for (int i = 0; i < pessoas.size(); i++) {
            System.out.println(pessoas.get(i));
          }*/

           /* String s = "";
            s = s + "a"; // s = a
            s = s + "b"; // s + b = ab
            s = s + "c"; // s + c = ab + c = abc
            cada vez que ocorre uma concatenação um objeto string é construído. se tiver 1 milhao de strings, vao criar 1 milhao de objetos
            Strings em java sao imutaveis */ 
          }
        }
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }while (op != 6);
  }

}
