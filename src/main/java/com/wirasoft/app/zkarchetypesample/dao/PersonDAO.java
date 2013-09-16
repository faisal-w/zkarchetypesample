package com.wirasoft.app.zkarchetypesample.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wirasoft.app.zkarchetypesample.domain.Person;

public interface PersonDAO {
	
	public void insert(Person person);
	
	public void insertNamedParameter(Person person);
	
	public Person findById(int personId);
	
	public List<Person> findAll();
	
	public String findPersonNameById(int personId);
	
	public int findTotalPerson();

	public void deleteById(int id);
}
