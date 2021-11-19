package jp.trans_it.world.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jp.trans_it.world.model.entity.Country;

public class CountryDAO {
	private static final String DB_DRIVER = "jdbc:mysql://localhost/world";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "password";

	private Connection connect() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection
			= DriverManager.getConnection(DB_DRIVER, DB_USER, DB_PASSWORD);
		return connection;
	}

	public List<Country> findAll(String sort, String direction)
			throws SQLException, ClassNotFoundException {
		String sql = "SELECT Name, Continent, Population, SurfaceArea FROM country";
		if(sort != null && !sort.isEmpty()) {
			sql = sql + " ORDER BY " + sort;
		}
		if(direction != null && !direction.isEmpty()) {
			sql = sql + " " + direction;
		}

		List<Country> list = new ArrayList<Country>();

		Connection connection = this.connect();
		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			Country country = new Country();

			country.setName(resultSet.getString("Name"));
			country.setContinent(resultSet.getString("Continent"));
			country.setPopulation(resultSet.getInt("Population"));
			country.setSurfaceArea(resultSet.getDouble("SurfaceArea"));

			list.add(country);
		}

		resultSet.close();
		statement.close();
		connection.close();

		return list;
	}
}
