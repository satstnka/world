package cc.shinbi.world.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import jp.trans_it.world.model.dao.CountryDAO;
import jp.trans_it.world.model.entity.Country;

public class CountryTest {

	@Test
	public void test() throws SQLException, ClassNotFoundException {
		CountryDAO dao = new CountryDAO();
		List<Country> list = dao.findAll("Population", "DESC");

		for(Country country : list) {
			System.out.println(
				String.format(
					"%s - Continent: %s, Populatin: %d, Surface Area: %.2f",
					country.getName(),
					country.getContinent(),
					country.getPopulation(),
					country.getSurfaceArea()
				)
			);
		}
	}
}
