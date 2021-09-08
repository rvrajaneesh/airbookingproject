package com.search.model;

public class searchByTwo {
	
	private String source;
	private String destination;
	public searchByTwo(String source, String destination) {
		super();
		this.source = source;
		this.destination = destination;
	}
	public searchByTwo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "Search2 [source=" + source + ", destination=" + destination + "]";
	}
	
	
	
}
