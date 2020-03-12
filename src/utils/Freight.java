package utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Freight {
	private boolean in = false;
	private String client;
	private String construction;
	private List<String> items;

	public Freight(boolean in, String client, String construction) {
		this.in = in;
		this.client = client;
		this.construction = construction;
	}

	public void addItem(String newItem){
		items.add(newItem);
	}



	// Getters
	public boolean isIn() {
		return in;
	}

	public String getClient() {
		return client;
	}

	public String getConstruction() {
		return construction;
	}

	public List<String> getItems() {
		return items;
	}

	// Setters
	public void setIn(boolean in) {
		this.in = in;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setConstruction(String construction) {
		this.construction = construction;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}
}
