import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class Query1 {
	String authorName;
	File dblp_file;
	String search_Parameter;/* !<Initialize using function parsing */
	int search_Option;/* !<Initialize using function parsing store */
	ArrayList<Publications> Search_result; /* !< ArrayList of type Publications used for storing all the search Result*/
	Author search_parameter_author; /* !< Stores the author referred to by the search parameter */
	public ArrayList<Publications> ret_searchresult() {
		return Search_result;
	}
	int counter = 0;
	int search_counter = 0;
	public Publications ret_Search_result(int i) {
		return Search_result.get(i);
	}

	public void parsing(String search_parameter, int search_option) {
		Search_result = new ArrayList<Publications>();
		search_Parameter = search_parameter;
		search_Option = search_option;
		search_counter = counter = 0;
		if (search_option == 0) {
			search_parameter_author = Author.find_person_ret_author(search_parameter);
			if(Author.find_person(search_parameter)==false)
			{
				return;
			}
		}
		try {
			dblp_file = new File("dblp.xml");
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parser = parserFactory.newSAXParser();
			DefaultHandler handler = new CustomHandler();
			parser.parse(dblp_file, handler);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Performs a linear regression on the data points {@code (y[i], x[i])}.
	 * @param x    the values of the predictor variable
	 * @param y    the corresponding values of the response variable
	 * @throws IllegalArgumentException if the lengths of the two arrays are not equal
	 */
	private class CustomHandler extends DefaultHandler {
		boolean author, title, pages, year, volume, journal, url,ignore,ismatched;
		private String tempdata,temp_tag,temp_title,temp_pages,temp_year, temp_volume,temp_journal, temp_url;
		private ArrayList<String> temp_authors;
		public CustomHandler() {
			author = title = pages = year = volume = journal = url = ignore = ismatched = false;
			temp_authors = new ArrayList<String>();
			temp_title = "";
		}
		public void startElement(String namespaceURI, String localName, String rawName, Attributes atts)throws SAXException {
			tempdata = "";
			if (rawName.equalsIgnoreCase("article") || rawName.equalsIgnoreCase("inproceedings")|| rawName.equalsIgnoreCase("proceedings") || rawName.equalsIgnoreCase("book")|| rawName.equalsIgnoreCase("incollection") || rawName.equalsIgnoreCase("phdthesis")|| rawName.equalsIgnoreCase("mastersthesis") || rawName.equalsIgnoreCase("www")) {
				ignore =ismatched = false;
				temp_authors = new ArrayList<String>();
				tempdata = temp_pages = temp_year = temp_volume = temp_journal =temp_url = null;
				if ((atts.getLength() > 0) && ((temp_tag = atts.getValue("key")) != null)&& rawName.equalsIgnoreCase("www")) {
					String[] parts = temp_tag.split("/");
					if ((parts[0]).equals("homepages")) {
						counter += 1;
						ignore = true;
					}
				}
			} else if (!ignore) {
				if (rawName.equalsIgnoreCase("author") || rawName.equalsIgnoreCase("editor")) {
					author = true;
				} else if (rawName.equalsIgnoreCase("journal") || rawName.equalsIgnoreCase("Booktitle")) {
					journal = true;
				}
				else if (rawName.equalsIgnoreCase("title")) {
					title = true;
				}
				else if (rawName.equalsIgnoreCase("year")) {
					year = true;
				}
				else if (rawName.equalsIgnoreCase("pages")) {
					pages = true;
				} else if (rawName.equalsIgnoreCase("volume")) {
					volume = true;
				}
				else if (rawName.equalsIgnoreCase("url")) {
					url = true;
				}
			}
		}
		public void characters(char ch[], int start, int length) throws SAXException {
			if (author) {
				tempdata += new String(ch, start, length);
			}
			else if (title) {
				tempdata += new String(ch, start, length);
			} else if (pages) {
				tempdata += new String(ch, start, length);
			} else if (year) {
				tempdata += new String(ch, start, length);
			} else if (volume) {
				tempdata += new String(ch, start, length);
			} else if (journal) {
				tempdata += new String(ch, start, length);
			} else if (url) {
				tempdata += new String(ch, start, length);
			}
		}
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (author) {
				temp_authors.add(tempdata);
				author = false;
			}
			else if (title) {
				temp_title = tempdata;title = false;
			} else if (pages) {
				temp_pages = tempdata;pages = false;
			} else if (year) {
				temp_year = tempdata;year = false;
			} else if (volume) {
				temp_volume = tempdata;volume = false;
			} else if (journal) {
				temp_journal = tempdata;journal = false;
			} else if (url) {
				temp_url = tempdata;url = false;
			}
			tempdata = "";
			if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings")|| qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book")|| qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis")|| qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www")) {
				if (search_Option == 0) {
					int count_words = 0;
					int count_characters = 0;
					if (temp_authors != null) {
						for (String s1 : search_parameter_author.ret_aliases()) {
							for (String s2 : temp_authors) {
								if (s1.equals(s2)) {
									ismatched = true;
									break;
								}
							}
						}
						if (ismatched) {
							Publications temp;
							temp = new Publications(temp_tag, temp_authors, temp_title, temp_pages, temp_year,temp_volume, temp_journal, temp_url, count_words, count_characters);
							Search_result.add(temp);
							search_counter += 1;
						}
					}
				} else if (search_Option == 1) {
					int count_words,count_words1,count_words2,count_characters; 
					count_characters = count_words=count_words1=count_words2=count_characters=0;
						if (temp_title != null) {
						boolean ismatching = false;
						String[] part1 = temp_title.split("\\W");
						String[] part2 = search_Parameter.split("\\W");
						ismatching = false;
						for (String s1 : part2) {
							if (s1.length() > 3) {
								for (String s2 : part1) {
									if (s1.equalsIgnoreCase(s2)) {
										ismatching = true;
										count_words1++;
										break;
									}
								}
							}
						}
						for (String s1 : part1) {
							if (s1.length() > 3) {
								for (String s2 : part2) {
									if (s1.equalsIgnoreCase(s2)) {
										ismatching = true;
										count_words2++;
										break;
									}
								}
							}
						}
						if (ismatching) {
							Publications temp;
							if (temp_authors == null || temp_authors.equals("")) {} else {
								count_words=Math.min(count_words2, count_words1);
								temp = new Publications(temp_tag, temp_authors, temp_title, temp_pages, temp_year,temp_volume, temp_journal, temp_url, count_words, count_characters);
								Search_result.add(temp);
								search_counter++;
							}
						}
					}
				}
			}
		}
	}
}
