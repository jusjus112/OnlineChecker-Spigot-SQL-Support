package awesomeGuy.jusjus.sql.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateQuery extends Query {
	private boolean firsValue;

	public CreateQuery(Connection connection, String sql) {
		super(connection, sql);
		firsValue = true;
	}

	public CreateQuery create(String value) {
		if (!firsValue) { 
			sql = sql.substring(0, sql.length() - 1);

			sql += ", ";
		} else {
			firsValue = false;
		}

		sql += value + ")";

		return this;
	}

	public void execute() {
		Statement statement;

		try {
			statement = connection.createStatement();

			statement.execute(sql);

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
