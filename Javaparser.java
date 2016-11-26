import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//java -Xmx2048m -Xms2048m -DentityExpansionLimit=100000000 Javaparser
public class Javaparser {
    String authorName;
    int counter=0;
	public Javaparser(String aName)
	{authorName=aName;
		try{
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
	     DefaultHandler handler = new CustomHandler();
	     parser.parse(new File("dblp.xml"), handler);
	     	
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (SAXException e){
			e.printStackTrace();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		//new Javaparser(in.nextLine());
		new Javaparser("Victor Khomenko");
	}

	private class CustomHandler extends DefaultHandler
	{
			boolean author,title,pages,year,volume,journal,url;
			boolean ismatched;
			String tempdata;
			private String tag1;
			private ArrayList<Author> authors1;
			private String title1;
			private String pages1;
			private String year1;
			private String volume1;
			private String journal1;
			private String url1;
			
			public CustomHandler() {
				author=title=pages=year=volume=journal=url=false;
				ismatched=false;
				tempdata=null;

			}
			@Override
			public void startElement(String namespaceURI, String localName,String rawName, Attributes atts) throws SAXException {
				if (rawName.equalsIgnoreCase("article")||rawName.equalsIgnoreCase("inproceedings")||rawName.equalsIgnoreCase("proceedings")||rawName.equalsIgnoreCase("book")||rawName.equalsIgnoreCase("incollection")||rawName.equalsIgnoreCase("phdthesis")||	rawName.equalsIgnoreCase("mastersthesis")||rawName.equalsIgnoreCase("wwww"))
				{
					ismatched=false;
				}
				 if(rawName.equalsIgnoreCase("author")||rawName.equalsIgnoreCase("editor"))
				{
					author=true;
				}
				else if(ismatched==	true)
				{
				     if(rawName.equalsIgnoreCase("journal")||rawName.equalsIgnoreCase("Booktitle"))
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
				tempdata=new String(ch, start, length);
				if(author)	
				{
					if(tempdata.equals(authorName))
					{
						ismatched=true;
//						System.out.println(tempdata);
						
					}
					author=false;
				}
				else if(ismatched==true)
				{	
					
					if(title)
					{
						System.out.println(tempdata);
						title=false;
					}
					else if(pages)
					{
						System.out.println(tempdata);
						pages=false;
					}
					else if(year)
					{
						System.out.println(tempdata);
						year=false;
					}
					else if(volume)
					{
						System.out.println(tempdata);
						volume=false;
					}
					else if(journal)
					{
						System.out.println(tempdata);
						journal=false;
					}
					else if(url)
					{
						System.out.println(tempdata);
						url=false;
					}
				}
				
				
			}
		       
		    

		    @Override
		    public void endElement(String uri, String localName, String qName) throws SAXException {
		    	if (qName.equalsIgnoreCase("article")||qName.equalsIgnoreCase("inproceedings")||qName.equalsIgnoreCase("proceedings")||qName.equalsIgnoreCase("book")||qName.equalsIgnoreCase("incollection")||qName.equalsIgnoreCase("phdthesis")||	qName.equalsIgnoreCase("mastersthesis")||qName.equalsIgnoreCase("www"))
		    	{
		    	counter++;
		    		if(ismatched==true)
					{	
		    			System.out.println();
					}
				    ismatched=false;
				}
		    	
		    	if(qName.equals("dblp"))
		    	{
		    		System.out.println("It rocks"+ counter);
		    	}
			      // System.out.println(x);
		    }
		
	}
	
}
