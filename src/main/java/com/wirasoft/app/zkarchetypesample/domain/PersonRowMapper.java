package com.wirasoft.app.zkarchetypesample.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonRowMapper implements RowMapper{
	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person person = new Person();
		person.setId(rs.getInt("ID"));
		person.setFirstName(rs.getString("FIRST_NAME"));
		person.setLastName(rs.getString("LAST_NAME"));
		person.setMoney(rs.getDouble("MONEY"));
		return person;
	}

}
