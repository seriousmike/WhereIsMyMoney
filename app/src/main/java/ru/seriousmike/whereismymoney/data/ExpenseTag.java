package ru.seriousmike.whereismymoney.data;

import java.util.List;

/**
 * Created by SeriousM on 21.04.2015.
 */
public class ExpenseTag {

	private long id;
	private String name;
	private List<ExpenseType> types;

	public ExpenseTag(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public ExpenseTag(String name) {
		this.name = name;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExpenseType> getTypes() {
		return types;
	}

	public void setTypes(List<ExpenseType> types) {
		this.types = types;
	}
}
