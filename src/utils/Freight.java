package utils;

import java.util.*;

public class Freight {
	private boolean in = false;
	private String client;
	private String construction;
	private ArrayList<String> items;

	public Freight(boolean in, String client, String construction) {
		this.in = in;
		this.client = client;
		this.construction = construction;
		items = new ArrayList<String>();
	}

	public void addItem(String newItem){
		items.add(newItem);
	}

	//Formatter
	public String toString(){
		return client + "\n" + construction + "\n" + ((items.isEmpty())? "-": items.toString().replace(", ","\n"));
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

	public void setItems(ArrayList<String> items) {
		this.items = items;
	}
}
