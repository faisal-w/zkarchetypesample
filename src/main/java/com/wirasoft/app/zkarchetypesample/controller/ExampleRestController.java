package com.wirasoft.app.zkarchetypesample.controller;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wirasoft.app.zkarchetypesample.domain.Person;
import com.wirasoft.app.zkarchetypesample.service.ListboxService;

@Controller
@RequestMapping("/personAll")
public class ExampleRestController {

	@Resource(name="listBoxService")
	private ListboxService listBoxService;

	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody 
	Person getPersonInJSON(){
		List<Person> listData = listBoxService.getData();
		for (Person person : listData) {
			System.out.println("extracted data : "+person.getFirstName());
		}
		return listData.get(0);
	}
	
}
