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

	public List<Voorstelling> findAllById(int id){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_GENRE)) {
			statement.setInt(1, id);
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

	private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet) throws SQLException {
		return new Voorstelling(resultSet.getInt("id"), resultSet.getString("titel"), resultSet.getString("uitvoerders"), resultSet.getDate("datum"), resultSet.getBigDecimal("prijs"), resultSet.getInt("vrijeplaatsen"));
	}
}
