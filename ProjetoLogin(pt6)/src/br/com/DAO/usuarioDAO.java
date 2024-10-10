
package br.com.DAO;

import br.com.DTO.usuarioDTO;
import br.com.View.telaPrincipal;
import br.com.View.telaUsuario;
import java.sql.*;
import javax.swing.JOptionPane;


public class usuarioDAO {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void IncluirUsuario(usuarioDTO udto){
        String sql = "insert into usuarios(id, nome, email, nome_usuario, senha) values(?,?,?,?,?)";
        
        conexao = new conexaoDAO().conector();
        
        try {
            
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, udto.getIdUsuario());
            pst.setString(2, udto.getNomeUsuario());
            pst.setString(3, udto.getEmailUsuario());
            pst.setString(4, udto.getLoginUsuario());
            pst.setString(5, udto.getSenhaUsuario());
             
            int add = pst.executeUpdate();
            if(add > 0){
            pst.close();
            JOptionPane.showMessageDialog(null, "Usuários inserido com sucesso!");
            limpar();
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "inserir usuário" + e);
        }
    }
    
    public void logar(usuarioDTO udto){
        
         
        String sql = "select * from usuarios where nome_usuario = ? and senha = ?";
        conexao = new conexaoDAO().conector();
        try{
            //preparar a consulta no banco, em função do que foi inserido nas caixas de texto
            pst = conexao.prepareStatement(sql);
            pst.setString(1, udto.getLoginUsuario());
            pst.setString(2, udto.getSenhaUsuario());
            
            //executar a query
            rs = pst.executeQuery();
            if(rs.next()){
            telaPrincipal principal = new telaPrincipal();
            principal.setVisible(true);//mudamos a visualização da tela 
            }else{
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválidos!!!");
            }

            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"Tela de login" + e);
        }
        
    }
    
    public void editar (usuarioDTO udto){
        
        String sql = "update usuarios set nome = ?, email = ?, nome_usuario = ?, senha = ? where id = ?";
        conexao = conexaoDAO.conector();
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(5, udto.getIdUsuario());
            pst.setString(1, udto.getNomeUsuario());
            pst.setString(2, udto.getEmailUsuario());
            pst.setString(3, udto.getLoginUsuario());
            pst.setString(4, udto.getSenhaUsuario());
            
            int add = pst.executeUpdate();
            if(add > 0){
            conexao.close();
            JOptionPane.showMessageDialog(null, "Usuários editado com sucesso!");
            limpar();
            
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método editar " + e);
        }
        
    }
    
    public void pesquisar (usuarioDTO udto){
    
        String sql = "select * from usuarios where id = ? ";
        conexao = conexaoDAO.conector();
        
        
        try {
            
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, udto.getIdUsuario());
            rs = pst.executeQuery();
            
            
            if(rs.next()){
                telaUsuario.txtNome.setText(rs.getString(2));
                telaUsuario.txtEmailUsuario.setText(rs.getString(3));
                telaUsuario.txtNomeDeUsuario.setText(rs.getString(4));
                telaUsuario.txtSenha.setText(rs.getString(5));
                conexao.close();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"método pesquisar(DAO)" + e);
            
        }
    
    }
    
    public void deletar(usuarioDTO udto){
        
        String sql = "delete from usuarios where id = ?";
        conexao = new conexaoDAO().conector();
        
        try {
            
           pst = conexao.prepareStatement(sql);
            pst.setInt(1, udto.getIdUsuario());
            
              int add = pst.executeUpdate();
            if(add > 0){
            conexao.close();
            JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
            limpar();
            
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método deletar(DAO)" + e);
        }
        
    }
    
    public void limpar(){
    
        telaUsuario.txtIDusuario.setText(null);
        telaUsuario.txtNome.setText(null);
        telaUsuario.txtEmailUsuario.setText(null);
        telaUsuario.txtNomeDeUsuario.setText(null);
        telaUsuario.txtSenha.setText(null);
        
    }
    
    

        
    
    
   
    
}
