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