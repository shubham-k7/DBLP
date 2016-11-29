import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorting_output {
	
	public static void sort_by_date( ArrayList<Publications>Search_result)
	{
		
		Collections.sort(Search_result, new Comparator<Publications>(){
		       
			@Override
			public int compare(Publications o1, Publications o2) {
				int year1=Integer.parseInt(o1.ret_year());
				int year2=Integer.parseInt(o2.ret_year());
				return year2-year1;
			}
		    });

	}
	
	public static void sort_by_relevance( ArrayList<Publications>Search_result)
	{
		 Collections.sort(Search_result, new Comparator<Publications>(){
		       
			@Override
			public int compare(Publications o1, Publications o2) {
				if(o1.ret_matched_words()!=o2.ret_matched_words())
				{
					return o2.ret_matched_words()- o1.ret_matched_words();
				}
				else{
					return o2.ret_matched_characters()- o1.ret_matched_characters();
				}
				
			}
		    });
	}
	
	
}
