package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.vdab.entities.Adres;
import be.vdab.entities.Klant;

public class KlantDAO extends AbstractDAO{
	private static final String FIND_BY_GEBRUIKERSNAAM = "select id,voornaam,familienaam,straat,huisnr,postcode,gemeente,wachtwoord from klanten where gebruikersnaam = ?";
	private static final String INSERT_SQL = "insert into klanten(voornaam,familienaam,straat,huisnr,postcode,gemeente,gebruikersnaam,wachtwoord) values (?,?,?,?,?,?,?,?)";

	public Klant findByGebruikersnaam(String gebruikersnaam){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_GEBRUIKERSNAAM)) {
			statement.setString(1, gebruikersnaam);
			try(ResultSet resultSet = statement.executeQuery()) {
				return resultSetRijNaarKlant(resultSet,gebruikersnaam);
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}
		
	public boolean insertKlant(Klant klant){
		boolean inserted = false;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_GEBRUIKERSNAAM)) {
			statement.setString(1, klant.getVoornaam());
			statement.setString(1, klant.getFamilienaam());
			statement.setString(1, klant.getAdres().getStraat());
			statement.setString(1, klant.getAdres().getHuisnummer());
			statement.setString(1, klant.getAdres().getPostcode());
			statement.setString(1, klant.getAdres().getGemeente());
			statement.setString(1, klant.getGebruikersnaam());
			statement.setString(1, klant.getWachtwoord());
			if(statement.executeUpdate() != 0){
				inserted = true;
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
		return inserted;
	}
	private Klant resultSetRijNaarKlant(ResultSet resultSet) throws SQLException {
		return new Klant(resultSet.getLong("id"), resultSet.getString("voornaam"),resultSet.getString("familienaam"),
				new Adres(resultSet.getString("straat"),resultSet.getString("huisnr"),resultSet.getString("postcode"),resultSet.getString("gemeente"))
				,resultSet.getString("gebruikersnaam"),resultSet.getString("wachtwoord"));
	}
	private Klant resultSetRijNaarKlant(ResultSet resultSet, String gebruikersnaam) throws SQLException {
		return new Klant(resultSet.getLong("id"), resultSet.getString("voornaam"),resultSet.getString("familienaam"),
				new Adres(resultSet.getString("straat"),resultSet.getString("huisnr"),resultSet.getString("postcode"),resultSet.getString("gemeente"))
				,gebruikersnaam,resultSet.getString("wachtwoord"));
	}
}
