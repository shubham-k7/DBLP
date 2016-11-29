import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//java -Xmx2048m -Xms2048m -DentityExpansionLimit=100000000 Query1
public class Query1 {
	String authorName;

	SAXParser parser;
	DefaultHandler handler;
	File dblp_file;

	String search_Parameter;
	int search_Option;
	ArrayList<Publications> Search_result;
	Author search_parameter_author;

	int counter = 0;
	int search_counter = 0;

	public Query1() {

	}

	public void parsing(String search_parameter, int search_option) {
		Search_result = new ArrayList<Publications>();
		search_Parameter = search_parameter;
		search_Option = search_option;
		search_counter = 0;
		counter = 0;
		if (search_option == 0) {
			search_parameter_author = Author.find_person_ret_author(search_parameter);
		}
		try {
			dblp_file = new File("dblp.xml");
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parser = parserFactory.newSAXParser();
			handler = new CustomHandler();
			parser.parse(dblp_file, handler);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	// public static void main(String[] args) {
	//
	// }

	private class CustomHandler extends DefaultHandler {
		boolean author, title, pages, year, volume, journal, url;
		boolean ismatched;
		String tempdata;
		boolean ignore;
		// Publications cur_Publication;
		private String temp_tag;
		private ArrayList<String> temp_authors;
		private String temp_title;
		private String temp_pages;
		private String temp_year;
		private String temp_volume;
		private String temp_journal;
		private String temp_url;

		public CustomHandler() {
			author = title = pages = year = volume = journal = url = false;
			ismatched = false;
			tempdata = null;
			ignore = false;
			// temp_authors=new ArrayList<Author>();
			temp_authors = new ArrayList<String>();
			temp_title = "";
			temp_pages = null;
			temp_year = null;
			temp_volume = null;
			temp_journal = null;
			temp_url = null;
		}

		@Override
		public void startElement(String namespaceURI, String localName, String rawName, Attributes atts)
				throws SAXException {
			tempdata = "";
			if (rawName.equalsIgnoreCase("article") || rawName.equalsIgnoreCase("inproceedings")
					|| rawName.equalsIgnoreCase("proceedings") || rawName.equalsIgnoreCase("book")
					|| rawName.equalsIgnoreCase("incollection") || rawName.equalsIgnoreCase("phdthesis")
					|| rawName.equalsIgnoreCase("mastersthesis") || rawName.equalsIgnoreCase("www")) {

				ignore = false;
				ismatched = false;

				temp_authors = new ArrayList<String>();
				temp_title = null;
				temp_pages = null;
				temp_year = null;
				temp_volume = null;
				temp_journal = null;
				temp_url = null;
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

		@Override
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

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {

			if (author) {
				temp_authors.add(tempdata);
				author = false;
			}

			else if (title) {
				temp_title = tempdata;
				title = false;
			} else if (pages) {
				temp_pages = tempdata;
				pages = false;
			} else if (year) {
				temp_year = tempdata;
				year = false;
			} else if (volume) {
				temp_volume = tempdata;
				volume = false;
			} else if (journal) {
				temp_journal = tempdata;
				journal = false;
			} else if (url) {
				temp_url = tempdata;
				url = false;
			}
			tempdata = "";
			if (qName.equalsIgnoreCase("article") || qName.equalsIgnoreCase("inproceedings")
					|| qName.equalsIgnoreCase("proceedings") || qName.equalsIgnoreCase("book")
					|| qName.equalsIgnoreCase("incollection") || qName.equalsIgnoreCase("phdthesis")
					|| qName.equalsIgnoreCase("mastersthesis") || qName.equalsIgnoreCase("www")) {
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
							temp = new Publications(temp_tag, temp_authors, temp_title, temp_pages, temp_year,
									temp_volume, temp_journal, temp_url, count_words, count_characters);
							// temp.print();
							Search_result.add(temp);
							search_counter += 1;
						}

					}
				} else if (search_Option == 1) {
					int count_words = 0;
					int count_characters = 0;
					if (temp_title != null) {
						boolean ismatching = false;
						// String[]part1=temp_title.split("\\W");
						String[] part2 = search_Parameter.split("\\W");

						// for(String temp:part2)
						// {
						// if((temp.length()>=4))
						// {
						// for(String s:part1)
						// {
						//
						// if(s.equalsIgnoreCase(temp))
						// { ismatching=true;
						// count_words++;
						// count_characters+=s.length();
						// }
						// }
						// }
						//
						// }
						ismatching = false;
						for (String s1 : part2) {
							if (s1.length()>3&&temp_title.contains(s1)) {
								ismatching = true;
								count_words++;
							}
//							}else {
//								
//								break;
//							}
						}

						// if(temp_title.equals(search_Parameter))
						// {
						//
						// Publications temp;
						// temp=new Publications(temp_tag, temp_authors,
						// temp_title, temp_pages,temp_year, temp_volume,
						// temp_journal, temp_url);
						// //temp.print();
						// Search_result.add(temp);
						//
						// search_counter+=1;
						//
						//
						// }
						if (ismatching) {
							Publications temp;
							temp = new Publications(temp_tag, temp_authors, temp_title, temp_pages, temp_year,
									temp_volume, temp_journal, temp_url, count_words, count_characters);
							Search_result.add(temp);
							search_counter++;
						}
					}
				}
			}

		}
	}

	public static void displayall(ArrayList<Publications> publicationsoutput, int i, int j) {
		int counter = 1;
		for (Publications p : publicationsoutput) {
			if (Integer.parseInt(p.ret_year()) > j) {

			} else if (Integer.parseInt(p.ret_year()) >= i) {
				System.out.println(counter++);
				p.print();

				System.out.println();
			}
		}
	}

	public static void display_according_to_option(int option, ArrayList<Publications> publicationsoutput) {
		Sorting_output.sort_by_date(publicationsoutput);
		switch (option) {

		case 1:
			displayall(publicationsoutput, 0, Integer.MAX_VALUE);
			break;
		case 2:
			Sorting_output.sort_by_relevance(publicationsoutput);
			displayall(publicationsoutput, 0, Integer.MAX_VALUE);
			break;
		case 3:
			Scanner in = new Scanner(System.in);
			displayall(publicationsoutput, Integer.parseInt(in.nextLine()), Integer.MAX_VALUE);
			in.close();

			break;
		case 4:
			Scanner in1 = new Scanner(System.in);
			displayall(publicationsoutput, Integer.parseInt(in1.nextLine()), Integer.parseInt(in1.nextLine()));
			in1.close();

			break;
		default:
			break;

		}

	}
}
