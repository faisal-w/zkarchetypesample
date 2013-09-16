package com.wirasoft.app.zkarchetypesample.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.select.annotation.Listen;

import com.wirasoft.app.zkarchetypesample.domain.Person;
import com.wirasoft.app.zkarchetypesample.service.ListboxService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ListboxExampleController extends SelectorComposer<Window>{

	private static final long serialVersionUID = 1L;

	@Wire
	Listbox listboxData;
	@Wire
	Textbox txtId,txtFirstName,txtLastName,txtMoney;
	int nummer;
	
	@WireVariable
	private ListboxService listBoxService;
	
	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		
		List<Person> listData = listBoxService.getData();
		//nummer = 0;
		listboxData.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem item, Object data, int index)
					throws Exception {
				//nummer++;
				final Person person = (Person) data;
				new Listcell(person.getId()+"").setParent(item);
				new Listcell(person.getFirstName()).setParent(item);
				new Listcell(person.getLastName()).setParent(item);
				new Listcell(person.getMoney()+"").setParent(item);
				
				Listcell lcForBtn = new Listcell();
				Button btnDel = new Button("Obliterate");
				btnDel.addEventListener("onClick", new EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						deleteRow(person.getId());
					}
				});
				btnDel.setParent(lcForBtn);
				lcForBtn.setParent(item);
				
				item.setValue(data);
			}
		});
		ListModelList lml = new ListModelList(listData, true);
		listboxData.setModel(lml);
		
	}

	void deleteRow(int id){
		try{
			listBoxService.deleteById(id);
			refreshData();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	void refreshData(){
		List<Person> listData = listBoxService.getData();
		ListModelList lml = new ListModelList(listData, true);
		listboxData.setModel(lml);
	}
	
	@Listen("onClick = button#btnSimpan")
	public void simpan(Event event){
		System.out.println("TOMBOL SIMPAN");
		if(!txtId.getValue().isEmpty() || 
		   !txtFirstName.getValue().isEmpty() || 
		   !txtLastName.getValue().isEmpty() || 
		   !txtMoney.getValue().isEmpty()){
			Person person = new Person();
			person.setId(Integer.parseInt(txtId.getValue()));
			person.setFirstName(txtFirstName.getValue());
			person.setLastName(txtLastName.getValue());
			person.setMoney(Double.valueOf(txtMoney.getValue()));
			listBoxService.simpanPerson(person);
			
			refreshData();
		}else{
			alert("Masih ada form yang kosong");
		}
	}
	
}
