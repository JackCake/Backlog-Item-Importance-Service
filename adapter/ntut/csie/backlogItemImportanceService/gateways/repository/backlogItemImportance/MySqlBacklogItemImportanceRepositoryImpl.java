package ntut.csie.backlogItemImportanceService.gateways.repository.backlogItemImportance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ntut.csie.backlogItemImportanceService.gateways.database.BacklogItemImportanceTable;
import ntut.csie.backlogItemImportanceService.gateways.database.SqlDatabaseHelper;
import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.BacklogItemImportanceRepository;

public class MySqlBacklogItemImportanceRepositoryImpl implements BacklogItemImportanceRepository {
	private SqlDatabaseHelper sqlDatabaseHelper;
	private BacklogItemImportanceMapper backlogItemImportanceMapper;
	
	public MySqlBacklogItemImportanceRepositoryImpl() {
		sqlDatabaseHelper = new SqlDatabaseHelper();
		backlogItemImportanceMapper = new BacklogItemImportanceMapper();
	}

	@Override
	public synchronized void save(BacklogItemImportance backlogItemImportance) throws Exception {
		sqlDatabaseHelper.connectToDatabase();
		PreparedStatement preparedStatement = null;
		try {
			sqlDatabaseHelper.transactionStart();
			BacklogItemImportanceData data = backlogItemImportanceMapper.transformToBacklogItemImportanceData(backlogItemImportance);
			String sql = String.format("Insert Into %s Values (?, ?) "
					+ "On Duplicate Key Update %s=?", 
					BacklogItemImportanceTable.tableName, BacklogItemImportanceTable.importance);
			preparedStatement = sqlDatabaseHelper.getPreparedStatement(sql);
			preparedStatement.setString(1, data.getBacklogItemId());
			preparedStatement.setInt(2, data.getImportance());
			preparedStatement.setInt(3, data.getImportance());
			preparedStatement.executeUpdate();
			sqlDatabaseHelper.transactionEnd();
		} catch(SQLException e) {
			sqlDatabaseHelper.transactionError();
			e.printStackTrace();
			throw new Exception("Sorry, there is the problem when save the importance of the backlog item. Please try again!");
		} finally {
			sqlDatabaseHelper.closePreparedStatement(preparedStatement);
			sqlDatabaseHelper.releaseConnection();
		}
	}

	@Override
	public synchronized void remove(BacklogItemImportance backlogItemImportance) throws Exception {
		sqlDatabaseHelper.connectToDatabase();
		PreparedStatement preparedStatement = null;
		try {
			sqlDatabaseHelper.transactionStart();
			String sql = String.format("Delete From %s Where %s = '%s'",
					BacklogItemImportanceTable.tableName,
					BacklogItemImportanceTable.backlogItemId,
					backlogItemImportance.getBacklogItemId());
			preparedStatement = sqlDatabaseHelper.getPreparedStatement(sql);
			preparedStatement.executeUpdate();
			sqlDatabaseHelper.transactionEnd();
		}  catch(SQLException e) {
			sqlDatabaseHelper.transactionError();
			e.printStackTrace();
			throw new Exception("Sorry, there is the problem when remove the importance of the backlog item. Please try again!");
		} finally {
			sqlDatabaseHelper.closePreparedStatement(preparedStatement);
			sqlDatabaseHelper.releaseConnection();
		}
	}

	@Override
	public synchronized BacklogItemImportance getBacklogItemImportanceByBacklogItemId(String backlogItemId) {
		sqlDatabaseHelper.connectToDatabase();
		ResultSet resultSet = null;
		BacklogItemImportance backlogItemImportance = null;
		try {
			String query = String.format("Select * From %s Where %s = '%s'",
					BacklogItemImportanceTable.tableName, 
					BacklogItemImportanceTable.backlogItemId, 
					backlogItemId);
			resultSet = sqlDatabaseHelper.getResultSet(query);
			while (resultSet.next()) {
				int importance = resultSet.getInt(BacklogItemImportanceTable.importance);
				
				BacklogItemImportanceData data = new BacklogItemImportanceData();
				data.setBacklogItemId(backlogItemId);
				data.setImportance(importance);

				backlogItemImportance = backlogItemImportanceMapper.transformToBacklogItemImportance(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqlDatabaseHelper.closeResultSet(resultSet);
			sqlDatabaseHelper.releaseConnection();
		}
		return backlogItemImportance;
	}
}
