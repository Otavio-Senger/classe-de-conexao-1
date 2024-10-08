
package br.com.DAO;

import br.com.DTO.usuarioDTO;
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
            
            pst.execute();
            pst.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "inserir usuário" + e);
        }
    }
    
   
    
}
