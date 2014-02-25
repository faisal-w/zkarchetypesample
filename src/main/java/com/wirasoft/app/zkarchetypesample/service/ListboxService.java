package com.wirasoft.app.zkarchetypesample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//import com.wirasoft.app.zkarchetypesample.dao.PersonDAO;
import com.wirasoft.app.zkarchetypesample.dao.PersonDAO;
import com.wirasoft.app.zkarchetypesample.domain.Person;

@Service("listBoxService")
public class ListboxService {
	
	@Autowired
    @Qualifier("jdbcPersonDao")
	PersonDAO personDao;
	
	public List<Person> getData(){
		List<Person> tempList = new ArrayList<Person>();
		
		tempList = personDao.findAll();
		System.out.println("Getting in Service, calling data...");
		
		/*
		Person arneis = new Person(1,"Arneis", "Fleur", 1500L);
		tempList.add(arneis);
		Person thorfinn = new Person(2,"Thorfinn", "Bjorn", 10L);
		tempList.add(thorfinn);
		Person jonn = new Person(3,"Jonn", "Johannson", 2300L);
		tempList.add(jonn);
		*/
		
		return tempList;
	}
	
	public void deleteById(int id){
		personDao.deleteById(id);
	}
	
	public void simpanPerson(Person person){
		personDao.insert(person);
	}

}
