package org.tiling.nutch.intro;

import java.io.IOException;

import org.apache.nutch.searcher.Hit;
import org.apache.nutch.searcher.HitDetails;
import org.apache.nutch.searcher.Hits;
import org.apache.nutch.searcher.NutchBean;
import org.apache.nutch.searcher.Query;

public class SearchApp {

	private static final int NUM_HITS = 10;

	public static void main(String[] args)
			throws IOException {
		
		if (args.length == 0) {
			String usage = "Usage: SearchApp query";
			System.err.println(usage);
			System.exit(-1);
		}

		NutchBean bean = new NutchBean();
		Query query = Query.parse(args[0]);
		Hits hits = bean.search(query, NUM_HITS);
		
		for (int i = 0; i < hits.getLength(); i++) {
			Hit hit = hits.getHit(i);
			HitDetails details = bean.getDetails(hit);
			
			String title = details.getValue("title");
			String url = details.getValue("url");
			String summary =
				bean.getSummary(details, query);
			
			System.out.print(title);
			System.out.print(" (");
			System.out.print(url);
			System.out.println(")");
			System.out.println("\t" + summary);
			System.out.println();
		}

	}
	
}
