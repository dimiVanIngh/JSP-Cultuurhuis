package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KlantDAO extends AbstractDAO{
	private static final String FIND_BY_GEBRUIKERSNAAM = "select id from klanten where gebruikersnaam = ?";
	private static final String INSERT_SQL = "insert into klanten(voornaam,familienaam,straat,huisnr,postcode,gemeente,gebruikersnaam,wachtwoord) values (?,?,?,?,?,?,?,?)";

	public Klant findByGebruikersnaam(String gebruikersnaam){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_GEBRUIKERSNAAM)) {
			statement.setString(1, gebruikersnaam);
			try(ResultSet resultSet = statement.executeQuery()) {
				return resultSetRijNaarGenre(resultSet);
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}

	private Klant resultSetRijNaarGenre(ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getLong("id"), resultSet.getString("naam"));
	}
	
	public boolean isGebruikersnaamBezet(String gebruikersnaam){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_GEBRUIKERSNAAM)) {
			statement.setString(1, gebruikersnaam);
			try(ResultSet resultSet = statement.executeQuery()) {
				if(resultSet.next()){
					return true;
				} else return false;
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}
}
