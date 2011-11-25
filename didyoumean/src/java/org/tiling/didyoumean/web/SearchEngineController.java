package org.tiling.didyoumean.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.tiling.didyoumean.SearchEngine;
import org.tiling.didyoumean.SearchResult;

public class SearchEngineController extends SimpleFormController {
    
    private SearchEngine searchEngine;

    public void setSearchEngine(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }
    
	protected boolean isFormSubmission(HttpServletRequest request) {
		return request.getParameter("query") != null;
	}

	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
			Object command, BindException errors) throws Exception {
		
		SearchForm searchForm = (SearchForm) command;
        try {
			SearchResult result = searchEngine.search(searchForm.getQuery());
			Map map = new HashMap();
			map.put("result", result);
			return showForm(request, errors, getSuccessView(), map);
		} catch (ParseException e) {
			errors.rejectValue("query", "error.query");
			return showForm(request, errors, getFormView());
		}		
		
    }
	
}