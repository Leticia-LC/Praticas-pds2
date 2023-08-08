package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Musicas;

public class MusicaDAO {
	
	public boolean inserir(Musicas m) {
		
		// instanciar a classe
		ConexaoBanco c = ConexaoBanco.getInstancia();
		
		//abrir a conexaop com o banco de dados
		Connection con = c.conectar();
		
		String query = "INSERT INTO musicas (idMusica, nomeMusica) VALUES (001, 'Looking Out For You')";
				
				try {
					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1, m.getIdMusica());
					ps.setString(2, m.getNomeMusica());
					//consolidar a execucao do comando no banco
					ps.executeUpdate();
					
					//fecha a conexao
					c.fecharConexao();
					
					return true;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		return false;
	}

}
