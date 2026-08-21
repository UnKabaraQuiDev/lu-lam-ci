package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SQLDataSource implements DataSource {

	private static final String URL = "jdbc:mysql://localhost:3306/scipr_h18";
	private static final String USER = "user";
	private static final String PASSWORD = "pass";

	protected Connection con;

	@Override
	public void load(PersonList pl) {
		Objects.requireNonNull(pl);

		try (Connection co = getConnection();
				Statement statement = co.createStatement();
				ResultSet rs = statement.executeQuery("SELECT * FROM person;")) {

			while (rs.next()) {
				final int id = rs.getInt("id");
				final String name = rs.getString("name");
				final String email = rs.getString("email");
				final String phone = rs.getString("phone");

				final Person p = new Person(id, name, email, phone);
				pl.add(p);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void save(PersonList pl) {
		Objects.requireNonNull(pl);

		try (Connection co = getConnection();
				PreparedStatement insertPs = co.prepareStatement(
						"INSERT INTO person (name, email, phone) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				PreparedStatement updatePs = co
						.prepareStatement("UPDATE person SET name = ?, email = ?, phone = ? WHERE id = ?")) {

			for (Person p : pl) {
				System.err.println(p);
				if (p.getId() <= 0) {
					insertPs.setString(1, p.getName());
					insertPs.setString(2, p.getEmail());
					insertPs.setString(3, p.getPhone());
					insertPs.executeUpdate();

					try (ResultSet generatedKeys = insertPs.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							p.setId(generatedKeys.getInt(1));
						} else {
							throw new SQLException("Insert failed, no ID obtained.");
						}
					}
				} else {
					updatePs.setString(1, p.getName());
					updatePs.setString(2, p.getEmail());
					updatePs.setString(3, p.getPhone());
					updatePs.setInt(4, p.getId());
					updatePs.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Person p) {
		Objects.requireNonNull(p);

		try (Connection co = getConnection();
				PreparedStatement ps = co
						.prepareStatement("UPDATE person SET name = ?, email = ?, phone = ? WHERE id = ?")) {

			ps.setString(1, p.getName());
			ps.setString(2, p.getEmail());
			ps.setString(3, p.getPhone());
			ps.setInt(4, p.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Person insert(PersonList pl) {
//		try (Connection co = getConnection();
//				PreparedStatement ps = co.prepareStatement("INSERT INTO person () VALUES ()",
//						Statement.RETURN_GENERATED_KEYS)) {
//
//			ps.executeUpdate();
//
//			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
//				if (generatedKeys.first()) {
//					final int newId = generatedKeys.getInt(1);
//					return new Person(newId, null, null, null);
//				} else {
//					throw new SQLException("Creating person failed, no ID obtained.");
//				}
//			}
//
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
		Person p = new Person();
		pl.add(p);
		return p;
	}

	public Connection getConnection() throws SQLException {
		if (con == null || con.isClosed()) {
			return con = DriverManager.getConnection(URL, USER, PASSWORD);
		}
		return con;
	}

}
