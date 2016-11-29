import java.util.ArrayList;
import java.util.Scanner;

public class Publications{
	
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
