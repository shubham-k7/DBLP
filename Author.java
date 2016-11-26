import java.util.ArrayList;

import javax.print.attribute.standard.PrinterMessageFromOperator;

import java.awt.print.Printable;
import java.util.*;


public class Author {
	public static Map<String,Author> person= new HashMap<String,Author>();
	private ArrayList<String> aliases;
	private int no_of_publications;
	public Author(String s)
	{
		aliases= new ArrayList<String>();
		aliases.add(s);
		no_of_publications=0;
		person.put(s, this);
	}
	public ArrayList<String> ret_persons()
	{
		return aliases;
	}
	public void add_name(String nm)
	{	
		   if(!if_present(nm))
		   {
			   aliases.add(nm);
			   no_of_publications+=1;
			   
		   }
	}
	public Boolean if_present(String nm)
	{
		for(String s:aliases)
		{
			if(s.equals(nm))
			{
				return true;
			}
		}
		return false;
	}
	public String ret_name() {
		return aliases.get(0);
	}
	
	
}
class Publications{
	private String tag;
	private ArrayList<Author> authors;
	private String title;
	private String pages;
	private String year;
	private String volume;
	private String journal;
	private String url;
public Publications(String tg,ArrayList<Author>a,String t, String p,String y, String v, String j, String u)
{
	tag=tg;
	authors=a;
	title=toString();
	pages=p;
	year=y;
	volume=v=y;
	journal=j;
	url=u;
}
public void print()
{	
	if(tag!=null)
	{
		System.out.println("Tag:"+tag);
	}
	if(authors!=null)
	{
		for(Author s: authors)
		{
			System.out.println("author/editor:"+s.ret_name());
		}
	}
	if(title!=null)
	{
		System.out.println("title:"+title);
	}
	if(pages!=null)
	{
		System.out.println("pages: "+pages);
	}
	if(year!=null)
	{
		System.out.println("year:"+year);
	}
	if(volume!=null)
	{
		System.out.println("volume:"+volume);
	}
	if(journal!=null)
	{
		System.out.println("Journal/booktitle:"+journal);
	}
	if(url!=null)
	{
		System.out.println("URL:"+url);
	}
}
public ArrayList<Author> getauthors()
{
	return authors;

}



}
