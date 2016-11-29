import java.io.*;

import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


//java -Xmx2048m -Xms2048m -DentityExpansionLimit=100000000 Javaparser
public class Javaparser {
    String authorName;
    
    SAXParser parser;
    DefaultHandler handler;
    File dblp_file=new File("dblp.xml");
    
    String search_Parameter;
    int search_Option;
    ArrayList<Publications>Search_result; 
    Author search_parameter_author;
    
   
    int counter=0;
    int search_counter=0;
	public Javaparser()
	{
//		try{
////			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
////			parser = parserFactory.newSAXParser();
////			handler = new CustomHandler();
//			
////			parser.parse(dblp_file, handler);
//	     	
//		}
////		catch (IOException e) {
////			e.printStackTrace();
////		}
//		catch (SAXException e){
//			e.printStackTrace();
//		}
//		catch (ParserConfigurationException e) {
//			e.printStackTrace();
//		}
		
	}
	public void parsing(String search_parameter,int search_option)
	{
		Search_result=new ArrayList<Publications>();
		search_Parameter=search_parameter;
		search_Option=search_option;	
		search_counter=0;
		counter=0;
		if(search_option==0)
		{
			search_parameter_author=Author.find_person_ret_author(search_parameter);
		}
		
//		try{
//		
//		parser.parse(dblp_file, handler);
//     	
//	}
//	catch (IOException e) {
//		e.printStackTrace();
//	} catch (SAXException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		
		try
		{
			dblp_file=new File("dblp.xml");
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parser = parserFactory.newSAXParser();
	      	handler = new CustomHandler();
	      	parser.parse(dblp_file, handler);
	    
	     	
		}	
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Scanner in = new Scanner(System.in);
		Author.reset_person();
		//new Javaparser(in.nextLine());
		//new Javaparser("Victor Khomenko");
		new new1();
	//	Query2.execute(500);
//		
//		int count_no=0;
//
//		int count_no0=0;
//		int count_no1=0;
//		int count_no2=0;
//		int count_no3=0;
//		System.out.println();

//		for(Author a: Author.ret_persons())
//		{
//			if(a.ret_key().equals("homepages/54/5373"))
//			{
//				a.print_author();
//			}
//		}
//		String s="homepages/96/6567";
//		Author.search_by_key(s);
//	for(Author a:Author.ret_persons())
//	{
//		if(a.ret_aliases().size()>=0)
//		{
//	
//			count_no+=1;
//		//	a.print_author();
//		}
//		if(a.ret_aliases().size()==0)
//		{
//	
//			count_no0+=1;
//		//	a.print_author();
//		}
//		if(a.ret_aliases().size()==1)
//		{
//	
//			count_no1+=1;
//			//a.print_author();
//		}
//		if(a.ret_aliases().size()==2)
//		{
//	
//			count_no2+=1;
//			//a.print_author();
//		}
//		if(a.ret_aliases().size()>2)
//		{
//	
//			count_no3+=1;
//			//a.print_author();
//		}
//	}
//	System.out.println("total: "+count_no+"	0: "+count_no0+"  1:  "+count_no1+"  2: "+count_no2+"  3: "+count_no3);
		Javaparser pJavaparser=new Javaparser();
//		pJavaparser.parsing("C. J. van Rijsbergen", 0);
//		Publications.display_according_to_option(1,pJavaparser.Search_result);
		pJavaparser.parsing("crime technology.", 1);
		Publications.display_according_to_option(2,pJavaparser.Search_result);
//		pJavaparser.parsing("David Eppstein", 0);
//		pJavaparser.parsing("Bringing geography to the practice of analyzing crime through technology.", 1);
//	
//		System.out.println(Author.ret_total_no_of_distinct_authors());
//		System.out.println(Author.ret_total_no_of_authors_names());
//		System.out.println(Author.find_person("Victor Khomenko"));
		
//	new new1();
//	Javaparser query1=new Javaparser();
//	query1.parsing("", 0);
//	Publications.display_according_to_option(1,query1.Search_result);
	}

	private class CustomHandler extends DefaultHandler
	{
			boolean author,title,pages,year,volume,journal,url;
			boolean ismatched;
			String tempdata;
			boolean ignore ;
//			Publications cur_Publication;
			private String temp_tag;
			private ArrayList<String> temp_authors;
			private String temp_title;
			private String temp_pages;
			private String temp_year;
			private String temp_volume;
			private String temp_journal;
			private String temp_url;
			
			public CustomHandler() {
				author=title=pages=year=volume=journal=url=false;
				ismatched=false;
				tempdata=null;
				ignore=false;
			//	temp_authors=new ArrayList<Author>();
				temp_authors=new ArrayList<String>();
				temp_title="";
				temp_pages=null;
				temp_year=null;
				temp_volume=null;
				temp_journal=null;
				temp_url=null;
			}
			@Override
			public void startElement(String namespaceURI, String localName,String rawName, Attributes atts) throws SAXException {
				tempdata="";
				if (rawName.equalsIgnoreCase("article")||rawName.equalsIgnoreCase("inproceedings")||rawName.equalsIgnoreCase("proceedings")||rawName.equalsIgnoreCase("book")||rawName.equalsIgnoreCase("incollection")||rawName.equalsIgnoreCase("phdthesis")||	rawName.equalsIgnoreCase("mastersthesis")||rawName.equalsIgnoreCase("www"))
				{
					
					ignore=false;
					ismatched=false;
	
					temp_authors=new ArrayList<String>();
					temp_title=null;
					temp_pages=null;
					temp_year=null;
					temp_volume=null;
					temp_journal=null;
					temp_url=null;
					if ((atts.getLength()>0) && ((temp_tag = atts.getValue("key"))!=null)&&rawName.equalsIgnoreCase("www")) 
					{
						String[] parts = temp_tag.split("/");
						if((parts[0]).equals("homepages"))
						{
							counter+=1;
							ignore=true;
						}
						
					}
					
				}
				else if(!ignore)
				{
						 if(rawName.equalsIgnoreCase("author")||rawName.equalsIgnoreCase("editor"))
							{
								author=true;
							}
						 else if(rawName.equalsIgnoreCase("journal")||rawName.equalsIgnoreCase("Booktitle"))
								{
									journal=true;
								}

							     else if(rawName.equalsIgnoreCase("title"))
								{
									title=true;
								}

							     else if(rawName.equalsIgnoreCase("year"))
								{
									year=true;
								}

							     else  if(rawName.equalsIgnoreCase("pages"))
								{
									pages=true;
								}	
							     else
							     if(rawName.equalsIgnoreCase("volume"))
								{
									volume=true;
								}

							     else if(rawName.equalsIgnoreCase("url"))
								{
									url=true;
								}
				
					}
					
				
			}
		
			@Override
			public void characters(char ch[], int start, int length) throws SAXException {
				//tempdata=new String(ch, start, length);
				
				if(author)	
				{
					tempdata+=new String(ch, start, length);
				}
				
					
				else if(title)
					{
					tempdata+=new String(ch, start, length);
					}
					else if(pages)
					{
						tempdata+=new String(ch, start, length);
					}
					else if(year)
					{
						tempdata+=new String(ch, start, length);
					}
					else if(volume)
					{
						tempdata+=new String(ch, start, length);
					}
					else if(journal)
					{
						tempdata+=new String(ch, start, length);
					}
					else if(url)
					{
						tempdata+=new String(ch, start, length);
					}
				
				
				
				
				
			}
		       
		    

		    @Override
		    public void endElement(String uri, String localName, String qName) throws SAXException {
		    	
		    	if(author)	
				{
					temp_authors.add(tempdata);
					author=false;
				}
				
					
				else if(title)
					{
						//System.out.println(tempdata);
						temp_title=tempdata;
						title=false;
					}
					else if(pages)
					{
						//System.out.println(tempdata);
						temp_pages=tempdata;
						pages=false;
					}
					else if(year)
					{
						temp_year=tempdata;
						//System.out.println(tempdata);
						year=false;
					}
					else if(volume)
					{
						temp_volume=tempdata;
						//System.out.println(tempdata);
						volume=false;
					}
					else if(journal)
					{
						temp_journal=tempdata;
						//System.out.println(tempdata);
						journal=false;
					}
					else if(url)
					{
						temp_url=tempdata;
						//System.out.println(tempdata);
						url=false;
					}
		    	tempdata="";
		    	if (qName.equalsIgnoreCase("article")||qName.equalsIgnoreCase("inproceedings")||qName.equalsIgnoreCase("proceedings")||qName.equalsIgnoreCase("book")||qName.equalsIgnoreCase("incollection")||qName.equalsIgnoreCase("phdthesis")||	qName.equalsIgnoreCase("mastersthesis")||qName.equalsIgnoreCase("www"))
		    	{
		    	//counter++;
//		    		if(ismatched==true)
//					{	
//		    			System.out.println();
//					}
//				    ismatched=false;
		    		if(search_Option==0)
		    		{
		    		int count_words=0;
		    		int count_characters=0;
		    			if(temp_authors!=null)
		    			{
		    				for(String s1:search_parameter_author.ret_aliases()){
		    					for(String s2:temp_authors)
		    					{
		    						if(s1.equals(s2))
		    						{
		    							ismatched=true;
		    							break;
		    						}
		    					}
		    				}
		    				
		    					if(ismatched)
		    					{
		    						Publications temp;
		    						temp=new Publications(temp_tag, temp_authors, temp_title, temp_pages,temp_year, temp_volume, temp_journal, temp_url,count_words,count_characters);
		    						//temp.print();
		    						Search_result.add(temp);
		    						  search_counter+=1;	
		    					}
		    				
		    			}
		    		}
		    		else if(search_Option==1)
		    		{
		    			int count_words=0;
			    		int count_characters=0;
		    			if(temp_title!=null)
		    		{	boolean ismatching=false;
//		    			String[]part1=temp_title.split("\\W");
		    			String[]part2=search_Parameter.split("\\W");
		    			
//		    			for(String temp:part2)
//		    			{
//		    				if((temp.length()>=4))
//		    				{
//		    					for(String s:part1)
//		    					{
//		    						
//		    						if(s.equalsIgnoreCase(temp))
//		    						{	ismatching=true;
//		    							count_words++;
//		    							count_characters+=s.length();
//		    						}
//		    					}
//		    				}
//		    				
//		    			}
		    			
		    			for(String s1:part2)
		    			{
		    				if(temp_title.contains(s1))
		    				{
		    					ismatching=true;
		    					count_words++;
		    					
		    				}
		    				else
		    				{
		    					ismatching=false;
		    					break;
		    				}
		    			}
		    			
		    			
		    			
		    			//		    			if(temp_title.equals(search_Parameter))
//		    			{
//		    				
//		    				Publications temp;
//    						temp=new Publications(temp_tag, temp_authors, temp_title, temp_pages,temp_year, temp_volume, temp_journal, temp_url);
//    						//temp.print();
//    						Search_result.add(temp);
//		    			  
//		    			  search_counter+=1;
//		    			  
//		    				
//		    			}
		    			if(ismatching)
		    			{
		    				Publications temp;
		    				temp=new Publications(temp_tag, temp_authors, temp_title, temp_pages,temp_year, temp_volume, temp_journal, temp_url,count_words,count_characters);
		    				Search_result.add(temp);
		    				search_counter++;
		    			}
		    		}
		    		}
				}
		    	
		    	if(qName.equals("dblp"))
		    	{
		    		System.out.println("It rocks "+ counter+" result "+search_counter);
//		    		System.out.println("It rocks"+ counter);
		    	}
			      // System.out.println(x);
		    }
		
	}
	
}
class new1
{

	private SAXParser parser;
	private	DefaultHandler handler;
	private File dblp_file;


	public new1()
	{
	
		try
		{
			dblp_file=new File("dblp.xml");
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parser = parserFactory.newSAXParser();
	      	handler = new entityresolver();
	      
	      	parser.parse(dblp_file, handler);
	    
	     	
		}	
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			e.printStackTrace();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private class entityresolver extends DefaultHandler
	{


		boolean author;
		boolean is_person_record;
		String temp_authorName;
		String key;
		Author tempauthor;
				
				
		public entityresolver() 
		{
					author=false;
					is_person_record=false;
					tempauthor=null;

		}
		@Override
		public void startElement(String namespaceURI, String localName,String rawName, Attributes atts) throws SAXException 
		{
			if (rawName.equalsIgnoreCase("www"))
			{is_person_record=false;
				if ((atts.getLength()>0) && ((key = atts.getValue("key"))!=null)) 
				{
					String[] parts = key.split("/");
				
					if((parts[0]).equals("homepages"))
					{
						//System.out.println(key);
						tempauthor= new Author();
						is_person_record=true;
						temp_authorName="";
					}
					
				}
			
			}
			else if(is_person_record==true)
			{
				if(rawName.equalsIgnoreCase("author")||rawName.equalsIgnoreCase("editor"))
				{
					 
					author=true;
				}
			}
			
//			else if(ismatched==	true)
//			{
//			     
//			     else if(rawName.equalsIgnoreCase("url"))
//				{
//					url=true;
//				}
//			}
		}

		@Override
		public void characters(char ch[], int start, int length) throws SAXException {
			String tempdata="";
				tempdata+=new String(ch, start, length);
//				if(is_person_record==true&&key.equals("homepages/54/5373"))
//				{
//					System.out.println(tempdata);
//				}
			if(author)	
			{
//				System.out.println(tempdata);
				temp_authorName+=tempdata;
					
				
			}
			
			
			
		}		
			       
			    

	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException
	     {
	    	
	    	if(author)	
			{//	System.out.println(temp_authorName);
	    		if(temp_authorName=="")
	    		{
	    			System.out.println("some error");
	    		}
	    		tempauthor.add_name(temp_authorName);
	    		temp_authorName="";
				author=false;
			}
	    	if (qName.equalsIgnoreCase("www")&&(is_person_record==true))
	    	{
//	    		System.out.println(Author.ret_total_no_of_distinct_authors());

	    		
	    		if(tempauthor.ret_aliases().size()==0)
	    		{
	    			//System.out.println(key);//cross ref to other homepages
	    		}
	    		else {
	    			tempauthor.set_key(key);
	    			Author.add_person(tempauthor);
				}
	    		
	    		tempauthor=null;
			    is_person_record=false;
			}
	    	
	    	if(qName.equals("dblp"))
	    	{
	    		System.out.println("It rocks");
	    	}
		      // System.out.println(x);
	    }
			
	}
	

}

