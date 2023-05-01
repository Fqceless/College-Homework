package controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import view.BaseView;

public abstract class BaseController {

    protected DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    protected DecimalFormat DOLLAR_FORMAT = new DecimalFormat("$#,##0.00");
    protected DecimalFormat DOUBLE_FORMAT = new DecimalFormat(".000");
    protected DecimalFormat INTEGER_FORMAT = new DecimalFormat("#,###");
    protected Map<String, String> keyVals = new HashMap<String, String>();
    protected BaseView view;
    protected String ACT_SEARCHFORM = "searchform";
    protected String ACT_SEARCH = "search";
    protected String ACT_DETAIL = "details";
    protected String ACT_ROSTER = "roster";
    protected String SSP_PLAYER = "player";
    protected String SSP_TEAM = "team";

    protected final void process(String query) {
        String q = decodeURL(query);
        parseQuery(q);
        performAction();
    }
    
    protected abstract void performAction();
    
    public abstract void init(String query);

    protected final String decodeURL(String s) {
        // spaces are replaced by '+' in textfields
        s = s.replaceAll("\\+", " ");
        // '=&' indicates the field was left blank; 
        // replace w/ space so String.split on '&' works
        s = s.replaceAll("=&", "= &");
        return s;
    }

    protected final void parseQuery(String query) {
        String[] queries;
        if (query.contains("&")) {
            queries = query.split("&");
        } else {
            queries = new String[]{query};
        }
        for (String q : queries) {
            String[] kvPair = q.split("=");
            String k = kvPair[0].trim();
            String v = kvPair[1].trim();
            keyVals.put(k, v);
            System.out.println("found keyvals=[" + k + "] [" + v + "]");
        }
    }

    public String response() {
        System.out.println("returning the dynamic webpage");
        return view.buildPage();
    }
    
    protected String formatDate(Date d) {
    	String dstr = "";
        if (d!=null) {
        	dstr = DATE_FORMAT.format(d);
        }
        return dstr;
    }
    
}