package ru.seriousmike.whereismymoney.data;

import java.util.List;

/**
 * Created by SeriousM on 21.04.2015.
 */
public class ExpenseType {

	private long id;
	private String name;
	private List<ExpenseTag> tags;

	public ExpenseType(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public ExpenseType(String name) {
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

	public List<ExpenseTag> getTags() {
		return tags;
	}

	public void setTags(List<ExpenseTag> tags) {
		this.tags = tags;
	}
}
