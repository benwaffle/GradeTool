package data;

import java.util.ArrayList;

public class Course {
	private String name;
	private ArrayList<Assignment> asmts;
	private long id;
	
	public Course(String name, long id){
		this.name = name;
		this.id = id;
		asmts = new ArrayList<Assignment>();
	}
	
	public String getName(){
		return name;
	}
	
	public long getId(){
		return id;
	}
	
	public void addAsmt(Assignment a){
		asmts.add(a);
	}
	
	public ArrayList<Assignment> getAsmts(){
		return asmts;
	}
}
