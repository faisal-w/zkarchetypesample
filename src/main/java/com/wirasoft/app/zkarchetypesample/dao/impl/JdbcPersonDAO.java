package com.wirasoft.app.zkarchetypesample.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.wirasoft.app.zkarchetypesample.dao.PersonDAO;
import com.wirasoft.app.zkarchetypesample.domain.Person;

@Component(value="jdbcPersonDao")
public class JdbcPersonDAO extends JdbcDaoSupport implements PersonDAO{

	//protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	public JdbcPersonDAO(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public void insert(Person person) {
		String sql = "INSERT INTO PERSON (ID,FIRST_NAME,LAST_NAME,MONEY) VALUES(?,?,?,?)";
		try{
			getJdbcTemplate().update(sql, 
					 new Object[]{
						person.getId(),
						person.getFirstName(),
						person.getLastName(),
						person.getMoney()
					 });			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertNamedParameter(Person person) {
		// NOT SUPPORTED
	}

	public Person findById(int personId) {
		String sql = "SELECT * FROM PERSON WHERE ID = ?";
		
		Person person = (Person) getJdbcTemplate().queryForObject(
									sql, 
									new Object[]{personId},
									new BeanPropertyRowMapper(Person.class));
		
		return person;
	}

	public List<Person> findAll() {
		String sql = "SELECT * FROM PERSON";
		
		List<Person> persons = new ArrayList<Person>();
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		for(Map<String, Object> row : rows){
			Person person = new Person();
			person.setId((Integer)(row.get("ID")));
			person.setFirstName((String)(row.get("FIRST_NAME")));
			person.setLastName((String)(row.get("LAST_NAME")));
			person.setMoney((Double)(row.get("MONEY")));
			persons.add(person);
		}
		
		return persons;
	}

	public String findPersonNameById(int personId) {
		String sql = "SELECT FIRST_NAME FROM PERSON WHERE ID = ?";
		return (String) getJdbcTemplate().queryForObject(
								sql, new Object[]{personId}, String.class);
	}

	@SuppressWarnings("deprecation")
	public int findTotalPerson() {
		String sql = "SELECT COUNT(*) FROM PERSON";
		return getJdbcTemplate().queryForInt(sql);
	}
	
	public void deleteById(int id){
		System.out.println("DELETE PROCEDURE");
		String sql = "DELETE FROM PERSON WHERE ID = ?";
		getJdbcTemplate().update(sql, new Object[]{id});
	}

}
