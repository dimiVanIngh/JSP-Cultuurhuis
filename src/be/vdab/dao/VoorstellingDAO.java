package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Genre;
import be.vdab.entities.Voorstelling;

public class VoorstellingDAO extends AbstractDAO{

	private static final String FIND_BY_GENRE = "select id,titel,uitvoerders,datum,prijs,vrijeplaatsen from voorstellingen where genreid = ?";
	private static final String FIND_BY_ID = "select titel,uitvoerders,datum,prijs,vrijeplaatsen from voorstellingen where id = ?";

	public List<Voorstelling> findAllById(long id){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_GENRE)) {
			statement.setLong(1, id);
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
				return resultSetRijNaarVoorstelling(resultSet,id);
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}

	private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet) throws SQLException {
		return new Voorstelling(resultSet.getLong("id"), resultSet.getString("titel"), resultSet.getString("uitvoerders"),resultSet.getTimestamp("datum"), resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijeplaatsen"));
	}
	private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet,long id) throws SQLException {
		return new Voorstelling(id, resultSet.getString("titel"), resultSet.getString("uitvoerders"),resultSet.getTimestamp("datum"), resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijeplaatsen"));
	}
}
