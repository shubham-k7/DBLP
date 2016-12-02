import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
/** Class for predicting
 */
public class Query3 {
	String[] s1;
	static Author[] a1=new Author[5];
	Map<String, HashMap<String, Integer>> data;
	double[] result={0,0,0,0,0};
	Map<String, String> last_year=new HashMap<String, String>();
	int size=5;
	
	public static void main(String[] args) {
		
		Query3 q_3=new Query3();
		
		String[] s=new String[5];
		
		String[] a=new String[5];
		
		a[0]="2014";
		a[1]="2014";
		a[2]="2014";
		a[3]="2014";
		a[4]="2014";
		s[4]="Peter Meer";
		s[1]="Dorin Comaniciu";
		s[0]="Geoffrey E. Hinton";
		s[3]="David J. Brown";
		s[2]="Peter J. Denning";
		
		new  EntityResolver();
		
		System.out.println("done resolving");
		
		a1[0]=Author.find_person_ret_author(s[0]);
		a1[1]=Author.find_person_ret_author(s[1]);
		a1[2]=Author.find_person_ret_author(s[2]);
		a1[3]=Author.find_person_ret_author(s[3]);
		a1[4]=Author.find_person_ret_author(s[4]);
		q_3.initialize_s_lastyear(s, a);
		
//		Boolean b;
//		for(String t1:s)
//		{
//			b=Author.find_person(t1);
//			System.out.println(t1);
//		}
		
		
		q_3.parse();
		
//		for(int i=0;i<5;i++)
//		{
//			double[] b=q_3.ret_array_year(s[i]);
//
//			for(int j=0;j<b.length;j++)
//			{
//				System.out.println(b[j]);
//			}
//		}
		q_3.predict_values();
//		q_3.display();
		q_3.display_res();
		
	}
	void display()
	{	System.out.println("dis");
	for(int i=1;i<2;i++)
		for(String temp:data.get(s1[i]).keySet())
		{
 
			System.out.println(" "+temp+" "+data.get(s1[i]).get(temp));
		}
	}
	void display_res()
	{
		for(int i=0;i<5;i++)
		{
			System.out.println(s1[i]+"   "+result[i]);
		}
	}
    public void initialize_s_lastyear(String[] s,String[] a) {
		s1 = s;
		for(int i=0;i<s.length;i++)
		{
			
			data.put(s[i],new HashMap<String,Integer>());
			last_year.put(s[i], a[i]);
		}
	}
    private double noramlize(double value) {
    	value/=2;
		if(value>5)
			return value-2;
		return value;
	}
	public Query3()
	{
		data=new HashMap<String,HashMap<String,Integer>>();
		s1=null;
	}
 public void parse()
	{
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			DefaultHandler handler = new CustomHandler1();
			parser.parse(new File("dblp.xml"), handler);
		}
		catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private class CustomHandler1 extends DefaultHandler {
		boolean author;
		boolean  year;
		String tempdata;
		boolean ignore;
		private String temp_tag;
		private ArrayList<String> temp_authors;
		private String temp_year;
		public CustomHandler1() {
			author = false;
			year = false;
			tempdata = null;
			ignore = false;
			temp_authors = new ArrayList<String>();
			temp_year = null;
			}
		public void startElement(String namespaceURI, String localName, String rawName, Attributes atts)
				throws SAXException {
			tempdata = "";
			if (rawName.equalsIgnoreCase("article") || rawName.equalsIgnoreCase("inproceedings")
					|| rawName.equalsIgnoreCase("proceedings") || rawName.equalsIgnoreCase("book")
					|| rawName.equalsIgnoreCase("incollection") || rawName.equalsIgnoreCase("phdthesis")
					|| rawName.equalsIgnoreCase("mastersthesis") || rawName.equalsIgnoreCase("www")) {
				ignore = false;
				temp_authors = new ArrayList<String>();
				temp_year = null;
				if ((atts.getLength() > 0) && ((temp_tag = atts.getValue("key")) != null)
						&& rawName.equalsIgnoreCase("www")) {
					String[] parts = temp_tag.split("/");
					if ((parts[0]).equals("homepages")) {
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

		}
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
					if (temp_authors!=null&&temp_authors.size()!=0&&temp_year!=null&&(!temp_year.equals(""))) {
//						System.out.println(" "+temp_authors.get(0)+"  "+temp_year);
						for(String temp1:temp_authors)
						{
							for(int i=0;i<5;i++)
							{
								if(a1[i].if_present(temp1))
								{
									//System.out.println(" "+s1[i]+"  "+temp_year);
									data.put(s1[i],data.get(s1[i]));
									if(data.get(s1[i]).keySet()==null||!(data.get(s1[i]).keySet().contains(temp_year)))
									data.get(s1[i]).put(temp_year,1);
									else
										data.get(s1[i]).put(temp_year,data.get(s1[i]).get(temp_year)+1);
									break;
								}
							}
						}
					}
				}		
		}
	}
	public void predict_values()
	{Prediction_of_Values p1=new Prediction_of_Values();
		for(int i=0;i<5;i++)
		{
			
			result[i]=noramlize(p1.predict_value(ret_array_year(s1[i]),ret_array_values(s1[i]), 2014));
			
			
		}
	}
	
	public double[] ret_array_year(String tstr)
	{
		int x=data.get(tstr).keySet().size();
		double []a=new double[x];
		int i=0;
		for(String stemp:data.get(tstr).keySet())
		{
		
			if(i<2014)
			a[i]=Integer.parseInt(stemp);//Integer.parseInt(last_year.get(stemp))
			i++;
		}
		Arrays.sort(a);
		return a;
	}
	
	private double[] ret_array_values(String tstr)
	{
		int x=data.get(tstr).keySet().size();
		double[]a=new double[x];
		double[]b=new double[x];
		b=ret_array_year(tstr);
		int i=0;
		for(;i<x;)
		{String temp="";
		int gg=(int)b[i];
		temp+=gg;
			a[i]=data.get(tstr).get(temp);
			i++;	
		}
		return a;
	}
}