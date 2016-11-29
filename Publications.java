import java.util.ArrayList;

public class Publications {

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

	public int ret_matched_words() {
		return matched_words;
	}

	public int ret_matched_characters() {
		return matched_characters;
	}

	public Publications(String tg, ArrayList<String> a, String t, String p, String y, String v, String j, String u,
			int cw, int cc) {
		tag = tg;
		authors = a;
		title = t;
		pages = p;
		year = y;
		volume = v = y;
		journal = j;
		url = u;
		matched_characters = cc;
		matched_words = cw;
	}

	public void print() {
		System.out.println("Matched Words: " + matched_words);
		System.out.println("Matched Characters: " + matched_characters);
		if (tag != null) {
			System.out.println("Tag:" + tag);
		}
		if (authors != null) {
			for (String s : authors) {
				System.out.println("author/editor:" + s);
			}
		}
		if (title != null) {
			System.out.println("title:" + title);
		}
		if (pages != null) {
			System.out.println("pages: " + pages);
		}
		if (year != null) {
			System.out.println("year:" + year);
		}
		if (volume != null) {
			System.out.println("volume:" + volume);
		}
		if (journal != null) {
			System.out.println("Journal/booktitle:" + journal);
		}
		if (url != null) {
			System.out.println("URL:" + url);
		}

	}

	public ArrayList<String> getauthors() {
		return authors;
	}

	public String ret_year() {
		return year;
	}
	/** 
     *  The function converts the output into a format supported by JTable. (Object [8])
     */
	public String[] gui_output_format(String search_parameter, int search_option, int index) {
		String[] output = {"0","-","-","-","-","-","-","-"};
		index=index+1;
		output[0]="";
		output[0]+=index;
		if (authors != null) {
			if(authors.equals("")||authors.size()==0)
		{
			//System.out.println("Error tag:"+tag);
		}
		else if (search_option == 1) {
				
				output[1] = authors.get(0);
			} else {
				for(String s: authors)
				{
					Author temp_author= Author.find_person_ret_author(search_parameter);
					if(temp_author.if_present(s))
					{
						output[1]=s;
						break;
					}
				}
			}
		}
		if (title != null) {
			output[2]=title;
		}
		if (pages != null) {
			output[3]=pages;
		}
		if (year != null) {
			output[4]=year;
		}
		if (volume != null) {
			output[5]=volume;
		}
		if (journal != null) {
			output[6]=journal;
		}
		if (url != null) {
			output[7]=url;
		}
		return output;

	}

}
