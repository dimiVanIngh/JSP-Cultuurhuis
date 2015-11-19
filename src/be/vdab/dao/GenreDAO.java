package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.Genre;

public class GenreDAO extends AbstractDAO {
	private static final String FIND_ALL_SQL = "select id,naam from genres order by naam asc";
	private static final String FIND_BY_ID = "select naam from genres where id = ?";

	public List<Genre> findAll() {
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL)) {
			List<Genre> genres = new ArrayList<>();
			while (resultSet.next()) {
				genres.add(resultSetRijNaarGenre(resultSet));
			}
			return genres;
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}
	public Genre findById(long id){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
			statement.setLong(1, id);
			try(ResultSet resultSet = statement.executeQuery()) {
				return resultSetRijNaarGenre(resultSet);
			}
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}

	private Genre resultSetRijNaarGenre(ResultSet resultSet) throws SQLException {
		return new Genre(resultSet.getLong("id"), resultSet.getString("naam"));
	}
}