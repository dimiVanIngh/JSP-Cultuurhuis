package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import be.vdab.entities.Reservatie;

public class ReservatieDAO extends AbstractDAO{

	private static final String INSERT_SQL = "insert into reservaties(klantid,voorstellingid,plaatsen) values (?,?,?)";
	private static final String SQL_UPDATE_LOWER_PLAATSEN = "update voorstellingen set vrijeplaatsen = vrijeplaatsen - ? where id = ? and vrijeplaatsen >= ?";
	
	public boolean bevestigReservatie(Reservatie reservatie) throws DAOException{
		boolean inserted = false;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
				PreparedStatement statementUpdate = connection.prepareStatement(SQL_UPDATE_LOWER_PLAATSEN)) {			
			connection.setAutoCommit(false);
			statement.setLong(1, reservatie.getKlant().getId());
			statement.setLong(2, reservatie.getVoorstelling().getId());
			statement.setInt(3, reservatie.getAantalPlaatsen());
			if(statement.executeUpdate() == 0){
				connection.rollback();
			} else{
				try{
					statementUpdate.setInt(1, reservatie.getAantalPlaatsen());
					statementUpdate.setLong(2, reservatie.getVoorstelling().getId());
					statementUpdate.setInt(3, reservatie.getAantalPlaatsen());
					if(statementUpdate.executeUpdate() == 0){
						connection.rollback();
					} else {
						connection.commit();
						inserted = true;
					}
				}catch(SQLException ex){
				}
			}
		}catch(SQLException ex){
			throw new DAOException(ex);
		}
		return inserted;
	}
	
}
