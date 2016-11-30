import com.sun.org.apache.xpath.internal.operations.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Class for predicting
 *
 */
public class Query3 {
	String[] s1;
	Map<String, Map<String,Integer>> data;
	int[] result={0,0,0,0,0};
	String[] last_year;
	int size=5;
    public void initialize(String[] s,String[] a) {
		s1 = s;
		last_year=a;
		for(string stemp:s)
		{
			data.put(stemp,new HashMap<String,Integer>());
		}

	}
	public Query3()
	{
		data=new HashMap<String,HashMap<String,Integer>>();
		s1=null;

	}
	void public parse()
	{
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			DefaultHandler handler = new CustomHandler();
			parser.parse(new File("dblp.xml"), handler);
		}
		catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}
	private class CustomHandler extends DefaultHandler {

		boolean author;
		boolean  year;
		String tempdata;
		boolean ignore,ismatched;
		private String temp_tag;
		private ArrayList<String> temp_authors;
		private String temp_year;
		public CustomHandler() {

			author = false;
			 year = false;
			tempdata = null;
			ignore = false;
			temp_authors = new ArrayList<String>();
			temp_year = null;
			ismatched=false;

		}

		public void startElement(String namespaceURI, String localName, String rawName, Attributes atts)
				throws SAXException {
			tempdata = "";
			if (rawName.equalsIgnoreCase("article") || rawName.equalsIgnoreCase("inproceedings")
					|| rawName.equalsIgnoreCase("proceedings") || rawName.equalsIgnoreCase("book")
					|| rawName.equalsIgnoreCase("incollection") || rawName.equalsIgnoreCase("phdthesis")
					|| rawName.equalsIgnoreCase("mastersthesis") || rawName.equalsIgnoreCase("www")) {

				ignore = false;
				ismatched=false;

				temp_authors = new ArrayList<String>();
				temp_year = null;
				if ((atts.getLength() > 0) && ((temp_tag = atts.getValue("key")) != null)
						&& rawName.equalsIgnoreCase("www")) {
					String[] parts = temp_tag.split("/");
					if ((parts[0]).equals("homepages")) {
						counter += 1;
						ignore = true;
					}

				}

			} else if (!ignore) {
				if (rawName.equalsIgnoreCase("author") || rawName.equalsIgnoreCase("editor")) {
					author = true;
				}
				else if (rawName.equalsIgnoreCase("year")) {
					year = true;
				}

			}
		}

		public void characters(char ch[], int start, int length) throws SAXException {
			if (author) {
				tempdata += new String(ch, start, length);
			}
			else if (year) {
				tempdata += new String(ch, start, length);


		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
				if (author) {
					temp_authors.add(tempdata);
					author = false;
				}
				else if (year) {
					temp_year = tempdata;
					year = false;
				}
				tempdata = "";
				if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings")
						|| qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book")
						|| qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis")
						|| qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www")) {
					if (temp_authors != null) {
						for(string temp1:temp_authors)
						{
							for(string temp2:s1)
							{
								if(Author.find_person_ret_author(temp2).if_present(temp1))
								{
									data.put(temp2,data.(temp2))
									if(data.get(temp2).keySet()==null||!(data.get(temp2).keySet().contains(temp_year)))
									data.get(temp2).put(temp_year,1);
									else
										data.get(temp2).put(temp_year,data.get(temp2).get(temp_year)+1);
								}
							}
						}
					}
				}
				}
		}

	}
	public void predict_values()
	{
 		//	Prediction_of_Values(0);
	}
	private int[] ret_array(String tstr)
	{
      //  ArrayList<Integer>

	}


}
