package com.wirasoft.app.zkarchetypesample.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.wirasoft.app.zkarchetypesample.dao.PersonDAO;
import com.wirasoft.app.zkarchetypesample.domain.Person;
import com.wirasoft.app.zkarchetypesample.domain.PersonRowMapper;

public class SimpleJdbcPersonDAO extends SimpleJdbcDaoSupport implements PersonDAO{

	public void insert(Person person) {
		String sql = "INSERT INTO PERSON (ID, FIRST_NAME, LAST_NAME, MONEY) VALUES (?,?,?)";
		getSimpleJdbcTemplate().update(sql, person.getId(), 
											person.getFirstName(),
											person.getLastName(),
											person.getMoney());
	}

	public void insertNamedParameter(Person person) {
		String sql = "INSERT INTO PERSON (ID,FIRST_NAME,LAST_NAME,MONEY) VALUES (:id,:firstName,:lastName,:money)";
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", person.getId());
		parameters.put("firstName", person.getFirstName());
		parameters.put("lastName", person.getLastName());
		parameters.put("money", person.getMoney());
		
		getSimpleJdbcTemplate().update(sql, parameters);
	}

	public Person findById(int personId) {
		String sql = "SELECT * FROM PERSON WHERE ID = ?";
		Person person = getSimpleJdbcTemplate().queryForObject(
							sql, 
							ParameterizedBeanPropertyRowMapper.newInstance(Person.class), 
							personId);
		return person;
	}

	public List<Person> findAll() {
		String sql = "SELECT * FROM PERSON";
		
		List<Person> persons = 
				getSimpleJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Person.class));
		
		return persons;
	}

	public String findPersonNameById(int personId) {
		String sql = "SELECT FIRST_NAME FROM PERSON WHERE ID = ?";
		return getSimpleJdbcTemplate().queryForObject(
							sql, String.class, personId);
	}

	public int findTotalPerson() {
		String sql = "SELECT COUNT(*) FROM PERSON";
		return getSimpleJdbcTemplate().queryForInt(sql);
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
