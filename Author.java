//import javax.print.attribute.standard.PrinterMessageFromOperator;
//
//import com.sun.org.apache.bcel.internal.generic.RETURN;
//
//import jdk.internal.org.objectweb.asm.tree.analysis.Value;
//
//import java.awt.print.Printable;
import java.util.*;




public class Author {
	private static ArrayList<Author> person= new ArrayList<Author>();
	public static void add_person(Author temp)
	{
		person.add(temp);
		
	}
	public static void reset_person()
	{
		person=new ArrayList<Author>();
	}
	public static Boolean find_person(String name)
	{
		Boolean truth_Value =false;
		for(Author a: person)
		{
			if(a.if_present(name))
			{
				truth_Value=true;
				break;
			}
		}
		return truth_Value;
		
	}
	public static ArrayList<Author> ret_persons()
	{
		return person;
	}
	public static Author  find_person_ret_author(String name)
	{
		for(Author a: person)
		{
			if(a.if_present(name))
			{
				return a;
			}
		}
		return null;
		
	}
	public static int ret_total_no_of_distinct_authors()
	{
		if(person!=null)
			return person.size();
		return -1;
	}
	public static int ret_total_no_of_authors_names()
	{int sum=0;
		if(person!=null)
		{
			for(Author a : person)
			{
				sum+=(a.ret_aliases()).size();
			}
			return sum;
		}
		return -1;
	}
	public static void search_by_key(String s)
	{
		for(Author a:person)
		{
			if(a.ret_key().equals(s))
			{
				a.print_author();
			}
		}
	}
	private ArrayList<String> aliases;
	private int no_of_publications;
	private String key;
	public String ret_key()
	{
		return key;
	}
	public int ret_no_of_publications()
	{
		return no_of_publications;
	}
	public void set_key(String s)
	{
		key=s;
	}
	public void print_author()
	{
		System.out.println("Authors:");
		for(String a:aliases)
		{
			System.out.println(a);
		}
	}
	public Author()
	{
		aliases= new ArrayList<String>();
		no_of_publications=0;
//		person.add( this);
	}
	public void increment_no_of_publications(int i)
	{
		no_of_publications+=i;
	}
	public ArrayList<String> ret_aliases()
	{
		return aliases;
	}
	public void add_name(String nm)
	{	
		   if(!if_present(nm))
		   {
			   aliases.add(nm);
			   
			   
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
	private ArrayList<String> authors;
	private String title;
	private String pages;
	private String year;
	private String volume;
	private String journal;
	private String url;
	private int matched_words;
	private int matched_characters;
public int ret_matched_words()
{
	return matched_words;
}
public int ret_matched_characters()
{
	return matched_characters;
}
public Publications(String tg,ArrayList<String>a,String t, String p,String y, String v, String j, String u,int cw,int cc)
{
	tag=tg;
	authors=a;
	title=t;
	pages=p;
	year=y;
	volume=v=y;
	journal=j;
	url=u;
	matched_characters=cc;
	matched_words=cw;
}
public void print()
{	System.out.println("Matched Words: "+matched_words);
	System.out.println("Matched Characters: "+matched_characters);
	if(tag!=null)
	{
		System.out.println("Tag:"+tag);
	}
	if(authors!=null)
	{
		for(String s: authors)
		{
			System.out.println("author/editor:"+s);
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
public ArrayList<String> getauthors()
{
	return authors;

}
public String ret_year()
{
	return year;
}
public static void displayall(ArrayList<Publications> publicationsoutput,int i,int j)
{ int counter=1;
	for(Publications p: publicationsoutput)
	{
		if(Integer.parseInt(p.ret_year())>j)
		{
			
		}
		else if(Integer.parseInt(p.ret_year())>=i)
		{
		System.out.println(counter++);
		p.print();
			
		System.out.println();
		}
	}
}
public static void display_according_to_option(int option,ArrayList<Publications> publicationsoutput)
{
Sorting_output.sort_by_date(publicationsoutput);
switch (option) {


case 1:displayall(publicationsoutput, 0, Integer.MAX_VALUE);
	break;
case 2:
	Sorting_output.sort_by_relevance(publicationsoutput);
	displayall(publicationsoutput, 0, Integer.MAX_VALUE);
	break;
case 3:
	Scanner in=new Scanner(System.in);
	displayall(publicationsoutput, Integer.parseInt(in.nextLine()),Integer.MAX_VALUE);
	in.close();
		
	break;
case 4:
	Scanner in1=new Scanner(System.in);
	displayall(publicationsoutput, Integer.parseInt(in1.nextLine()),Integer.parseInt(in1.nextLine()));
	in1.close();
	
	break;
default:
	break;
	
}
	
}
}
