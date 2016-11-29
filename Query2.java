import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;





public class Query2 {
	SAXParserFactory parserFactory;
	SAXParser parser;
	DefaultHandler handler;
	private static Query2 UniqueInstance=null;
	private static Map<String, Integer> name_count=null;

	private Query2() {
		try {
			parserFactory = SAXParserFactory.newInstance();
			parser = parserFactory.newSAXParser();
			handler = new CustomHandler();
			//parser.parse(new File("dblp.xml"), handler);
		} 
		 catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 
	}

	// TODO Auto-generated constructor stub

	private class CustomHandler extends DefaultHandler {

		boolean author;
	//	String tag;
		String aname;
		
		public CustomHandler() {

			// TODO Auto-generated constructor stub
			author = false;
		}

		public void startElement(String namespaceURI, String localName, String rawName, Attributes atts)
				throws SAXException {
			aname="";
		//	if ((atts.getLength()>0) && ((tag = atts.getValue("key"))!=null));
			
			
			if (rawName.equalsIgnoreCase("author") || rawName.equalsIgnoreCase("editor")) {
				author = true;
			}
		}

		public void characters(char ch[], int start, int length) throws SAXException {
			String tempdata = "";
			tempdata+=new String(ch, start, length);
			if(author)
			{
				aname+=tempdata;
			}

		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (author) {
				if(name_count.keySet().contains(aname))
				{
					name_count.put(aname, name_count.get(aname)+1);
				}
				else{
					System.out.println("error");
					System.exit(0);
				}
				//System.out.println(tempdata);
				author = false;
			}
//			if (qName.equals("dblp")) {
//				// System.out.println("It rocks");
//				remove_www_homepage_count();
//			}
		}

	}

	private void remove_www_homepage_count() {
		for (Author a : Author.ret_persons()) {
			a.increment_no_of_publications(-(a.ret_aliases().size()));
			
		}
	}

	public static void execute(int n) {
		if(UniqueInstance==null||name_count==null)
		{
			UniqueInstance=new Query2();
			name_count=new HashMap<String, Integer>();
			for(Author a : Author.ret_persons()) {
				for(String s:a.ret_aliases())
				{
					name_count.put(s, new Integer(0));
					
				}
			}
			UniqueInstance.calcPublicationCount();
			UniqueInstance.remove_www_homepage_count();
		}
		
		
		for(Author a : Author.ret_persons()) {
			int cur_author_publications=a.ret_no_of_publications();
			if(cur_author_publications>n)
			System.out.println("Author Name: "+a.ret_name()+ "     No of Publications: "+cur_author_publications);
		}

	}
	private void calcPublicationCount()
	{
		try {
			
			parser.parse(new File("dblp.xml"), handler);
			for(Author a : Author.ret_persons()) {
				for(String s:a.ret_aliases())
				{
					a.increment_no_of_publications(name_count.get(s));
				}
			
			}
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
