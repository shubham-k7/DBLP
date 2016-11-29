import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EntityResolver {

	private SAXParser parser;
	private DefaultHandler handler;
	private File dblp_file;
	public EntityResolver() {

		try {
			dblp_file = new File("dblp.xml");
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parser = parserFactory.newSAXParser();
			handler = new EntityHandler();

			parser.parse(dblp_file, handler);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private class EntityHandler extends DefaultHandler {

		boolean author;
		boolean is_person_record;
		String temp_authorName;
		String key;
		Author tempauthor;

		public EntityHandler() {
			author = false;
			is_person_record = false;
			tempauthor = null;

		}

		@Override
		public void startElement(String namespaceURI, String localName, String rawName, Attributes atts)
				throws SAXException {
			if (rawName.equalsIgnoreCase("www")) {
				is_person_record = false;
				if ((atts.getLength() > 0) && ((key = atts.getValue("key")) != null)) {
					String[] parts = key.split("/");

					if ((parts[0]).equals("homepages")) {
						// System.out.println(key);
						tempauthor = new Author();
						is_person_record = true;
						temp_authorName = "";
					}

				}

			} else if (is_person_record == true) {
				if (rawName.equalsIgnoreCase("author") || rawName.equalsIgnoreCase("editor")) {

					author = true;
				}
			}
		}

		@Override
		public void characters(char ch[], int start, int length) throws SAXException {
			String tempdata = "";
			tempdata += new String(ch, start, length);
			if (author) {

				temp_authorName += tempdata;

			}

		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {

			if (author) {
				if (temp_authorName == "") {
					System.out.println("some error");
				}
				tempauthor.add_name(temp_authorName);
				temp_authorName = "";
				author = false;
			}
			if (qName.equalsIgnoreCase("www") && (is_person_record == true)) {
				if (tempauthor.ret_aliases().size() == 0) {
					// cross ref to other homepages
				} else {
					tempauthor.set_key(key);
					Author.add_person(tempauthor);
				}

				tempauthor = null;
				is_person_record = false;
			}

		}

	}
}
