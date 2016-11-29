//import javax.print.attribute.standard.PrinterMessageFromOperator;
//
//import com.sun.org.apache.bcel.internal.generic.RETURN;
//
//import jdk.internal.org.objectweb.asm.tree.analysis.Value;
//
//import java.awt.print.Printable;
import java.util.*;


//!  Author Class
/*!
Used for storing Authors, their aliases and no of publications which are initialized through respective Query functions
*/

public class Author {
	private static ArrayList<Author> person= new ArrayList<Author>();/*!< private member used to store the author references which gets initialized by class Entity Resolver */
	
	
	/**
     * Adds the input person to the person records {@code (temp)}.
     *
     * @param  temp the values of the author variable to be added
     */	
	public static void add_person(Author temp)
	{
		person.add(temp);
		
	}
	 //! A static member for entirely resetting the database of authors. Used at start to reset and intitalise 
    /*!
   
    */
	public static void reset_person()
	{
		person=new ArrayList<Author>();
	}
	  //! A static member for referencing kth position of ArrayList
    /*!
      \param k an integer argument.
     
      \return The author reference at k th position of arraylist
    
    */
	public static Author ret_person(int k)
	{
		return person.get(k);
	}
	  //! A static member taking one argument String as input and returning boolean value whether it is present or not
    /*!
      \param name is a String argument
      \return if present
    */
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
	  //! A static member returning entire ArrayList
    /*!
    */
	public static ArrayList<Author> ret_persons()
	{
		return person;
	}
	  //! A static member taking one argument String as input and returning Autho reference
    /*!
      \@param name is String argument.
      \return The author reference else null.
    */
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
	//redundant operations for testing only: start
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
	//end
	
	//data stored in Author variable : start
	private ArrayList<String> aliases;
	private int no_of_publications;
	private String key;
	//end
	//Standard set, get and print Operations performed on Author variable : start
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
	//end
	 //!Constructor for Author
    /*!
   		Initializing variables to valid values
    */
	public Author()
	{
		aliases= new ArrayList<String>();
		no_of_publications=0;
	}
	//Standard Operations performed on Author variable members : start
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
	//end
	
}