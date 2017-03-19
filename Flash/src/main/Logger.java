package main;

import java.util.ArrayList;

public class Logger {
	private int tabcnt;
	private String line;
	{
		tabcnt=0;
		line="";
	}
	public void enter(Object obj, String fName, ArrayList<Object>params)
	{
		for(int i=0;i<tabcnt;i++)
		{
			line=line.concat("\t");
		}
		line=line.concat("->");
		line=line.concat(obj.toString());
		line=line.concat(".");
		line=line.concat("(");
		for(int i=0;i<params.size();i++)
		{
			line=line.concat(params.toString());
			if(params.size()!=(i+1)) line=line.concat(",");
		}
		line=line.concat(")");
		System.out.println(line);
		line="";
		tabcnt++;
	}
	public void exit(String re)
	{
		tabcnt--;
		for(int i=0;i<tabcnt;i++)
		{
			line=line.concat("\t");
		}
		line=line.concat("<-");
		line=line.concat("return: ");
		if(re!=null || re!="") line=line.concat(re);
		System.out.println(line);
		line="";
		
	}
}
