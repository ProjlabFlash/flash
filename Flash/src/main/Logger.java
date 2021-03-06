package main;

import java.util.ArrayList;

public class Logger {
	private int tabcnt;
	private String line;
	private boolean init;
	{
		tabcnt=0;
		line="";
		init=false;
	}
	public void setInit(boolean asd){init=asd;}
	public void enter(Object obj, String fName, ArrayList<Object>params)
	{
		
		if(!init){
		for(int i=0;i<tabcnt;i++)
		{
			line=line.concat("\t");
		}
		line=line.concat("->");
		line=line.concat(obj.toString());
		line=line.concat(".");
		line=line.concat(fName);
		line=line.concat("(");
		
		if (params != null)
		for(int i=0;i<params.size();i++)
		{
			if(params.get(i)==null) line=line.concat("null");
			else line=line.concat(params.get(i).toString());
			if(params.size()!=(i+1)) line=line.concat(",");
		}
		
		line=line.concat(")");
		System.out.println(line);
		line="";
		tabcnt++;
		}
	}
	
	public void enter(Object obj, String fName)
	{
		
		if(!init){
		for(int i=0;i<tabcnt;i++)
		{
			line=line.concat("\t");
		}
		line=line.concat("->");
		line=line.concat(obj.toString());
		line=line.concat(".");
		line=line.concat(fName);
		line=line.concat("()");
		System.out.println(line);
		line="";
		tabcnt++;
		}
	}
	public void exit(String re)
	{
		if(!init){
		tabcnt--;
		for(int i=0;i<tabcnt;i++)
		{
			line=line.concat("\t");
		}
		line=line.concat("<-");
		line=line.concat("return: ");
		if(re!=null || re!=null) line=line.concat(re);
		System.out.println(line);
		line="";
		}
	}
}
