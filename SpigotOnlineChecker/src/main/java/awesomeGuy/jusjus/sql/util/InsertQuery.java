package awesomeGuy.jusjus.sql.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertQuery extends Query {
	private boolean fistValue;

	private final List<Object> values;

	public InsertQuery(Connection connection, String sql) {
		super(connection, sql);
		fistValue = true;
		values = new ArrayList<Object>();
	}
 
	public InsertQuery value(Object value) {
		values.add(value);

		sql = sql.substring(0, sql.length() - 1);
		if (fistValue) {
			sql = sql.substring(0, sql.length() - 1);
			sql += ") VALUES (?)";

			fistValue = false;
		} else {
			sql += ", ?)";
		}
		return this;
	}

	public void execute() {
		PreparedStatement prest;

		try {
			if (connection == null)
				System.out.println("Er is geen connectie tot stand gekomen!");
			if (sql == null)
				System.out.println("Verzoek is null");
			prest = connection.prepareStatement(sql);

			int i = 1;
			for (Object object : values) {
				prest.setObject(i, object);

				i++;
			}
			prest.executeUpdate();
			prest.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public InsertQuery insert(String insert) {
		sql += insert + ", ";

		return this;
	}

}
