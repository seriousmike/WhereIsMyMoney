package ru.seriousmike.whereismymoney.data;

import java.util.List;

/**
 * Created by SeriousM on 21.04.2015.
 */
public class ExpenseType {

	private long id;
	private String name;
	private List<ExpenseTags> tags;

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

	public List<ExpenseTags> getTags() {
		return tags;
	}

	public void setTags(List<ExpenseTags> tags) {
		this.tags = tags;
	}
}
