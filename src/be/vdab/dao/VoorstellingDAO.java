package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Voorstelling;

public class VoorstellingDAO extends AbstractDAO{

	private static final String FIND_BY_GENRE = "select id,titel,uitvoerders,datum,prijs,vrijeplaatsen from voorstellingen where genreid = ?";
	private static final String FIND_BY_GENRE_NO_EXPIRED = "select id,titel,uitvoerders,datum,prijs,vrijeplaatsen from voorstellingen where genreid = ? and datum > DATE(NOW())";
	private static final String FIND_BY_ID = "select id,titel,uitvoerders,datum,prijs,vrijeplaatsen from voorstellingen where id = ?";
	private static final String FIND_AANTAL_PLAATSEN_BY_ID = "select vrijeplaatsen from voorstellingen where id = ?";

	public List<Voorstelling> findAllById(long genreId){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_GENRE)) {
			statement.setLong(1, genreId);
		      List<Voorstelling> voorstellingen = new ArrayList<>();
		      try (ResultSet resultSet = statement.executeQuery()) {
		        while (resultSet.next()) {
		          voorstellingen.add(resultSetRijNaarVoorstelling(resultSet));
		        }
		        return voorstellingen;
		       }
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}
	
	public List<Voorstelling> findAllByIdWithoutExpired(long genreId){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_GENRE_NO_EXPIRED)) {
			statement.setLong(1, genreId);
		      List<Voorstelling> voorstellingen = new ArrayList<>();
		      try (ResultSet resultSet = statement.executeQuery()) {
		        while (resultSet.next()) {
		          voorstellingen.add(resultSetRijNaarVoorstelling(resultSet));
		        }
		        return voorstellingen;
		       }
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}
	
	public Voorstelling findById(long id){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setLong(1, id);
			try(ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSetRijNaarVoorstelling(resultSet, id);
				}
				return null;
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}
	
	public int getAantalVrijePlaatsen(long id){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setLong(1, id);
			try(ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt("vrijeplaatsen");
				}
				return 0;
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}

	private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet) throws SQLException {
		return resultSetRijNaarVoorstelling(resultSet, resultSet.getLong("id"));
	}
	private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet,long id) throws SQLException {
		return new Voorstelling(id, resultSet.getString("titel"), resultSet.getString("uitvoerders"),resultSet.getTimestamp("datum"), resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijeplaatsen"));
	}
}
