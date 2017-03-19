package main;

public abstract class MetaData {
	private String name;
	
	public void setName(String name) {
		this.name = name; 
	};
	
	public String toString() {
		return name;
	}
}
