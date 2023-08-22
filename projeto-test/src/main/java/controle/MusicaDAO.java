package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Musicas;

public class MusicaDAO {
	
	public ArrayList<Musicas> listar(){
		ConexaoBanco c = ConexaoBanco.getInstancia();
		
		Connection con = c.conectar();
		ArrayList<Musicas> musicass = new ArrayList();
		
		String query = "SELECT FROM * MOVIES";
		
		try {
           PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			int movieId = rs.getInt("id_musica");
			String nomeMovie = rs.getString("nome_musica");
			
			Musicas m = new Musicas();
			m.setIdMusica(movieId);
			m.setNomeMusica(nomeMovie);
			
			musicass.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		c.fecharConexao();
		
		return musicass;
			
	}
	
	
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
	public boolean excluir(Musicas m) {
		ConexaoBanco c = ConexaoBanco.getInstancia();
		Connection con = c.conectar();
		
		String query = "DELETE FROM musicas WHERE id_musica = ?";
		
		try {
			PreparedStatement ms = con.prepareStatement(query);
			ms.setInt(1,  m.getIdMusica());
			ms.executeUpdate();
			
			c.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return false;
	}
	public boolean atualizar(Musicas m) {
		ConexaoBanco c = ConexaoBanco.getInstancia();
		Connection con = c.conectar();
		
		String query = "UPDATE musicas SET " + "nome_musica = ? WHERE id_musica = ?";
		
		try {
			PreparedStatement ms = con.prepareStatement(query);
			ms.setString(1, m.getNomeMusica());
			ms.setInt(1,  m.getIdMusica());
			ms.executeUpdate();
			
			c.fecharConexao();
			
			return true;
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return false;
	}
}
