/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author user
 */
public abstract class BaseView {
    
    protected String title;
    protected StringBuffer body = new StringBuffer();

    public abstract void buildSearchForm();
    
    public final String buildPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\r\n");
        sb.append("<HTML>\r\n");
        sb.append("<HEAD><TITLE>");
        sb.append(title);
        sb.append("</TITLE>");
        sb.append("<link rel='stylesheet' href='css.css' type=\"text/css\">");
        sb.append("</HEAD>\r\n");
        sb.append("<BODY>\r\n");
        sb.append("<h2>MLB - ");
        sb.append(title);
        sb.append("</h2>\r\n");
        sb.append(body);
        sb.append("<br/><br/>\r\n");
        sb.append("<a href=\"index.htm\">Home</a>\r\n");
        sb.append("</BODY>\r\n");
        sb.append("</HTML>\r\n");
        return sb.toString();
    }
    
    public final void buildLinkToSearch() {
        body.append("<br/><br/>\r\n");
        body.append("<a href=\"");
        body.append(title.toLowerCase());
        body.append(".ssp?action=searchform\">Search for a ");
        body.append(title);
        body.append("</a>\r\n");  
    }
    
    public final void printMessage(String msg) {
        body.append("<p>");
        body.append(msg);
        body.append("</p>\r\n");
    }
    
    public final void printSearchResultsMessage(String name, boolean exact) {
        body.append("<p>");
        body.append(title);
        if (exact) {
            body.append("s with name matching '");
        } else {
            body.append("s with name containing '");
        } 
        body.append(name);
        body.append("':</p>\r\n");
        
    }

    public final void buildTable(String[][] table) {
        body.append("<table border=1>\r\n");
        // print table header row
        body.append("<tr>");
        for (int i = 0; i < table[0].length; i++) {
            body.append("<th>");
            body.append(table[0][i]);
            body.append("</th>\r\n");
        }
        body.append("</tr>\r\n");
        // print table rows
        for (int row = 1; row < table.length; row++) {
            body.append("<tr>\r\n");
            for (int col = 0; col < table[row].length; col++) {
                body.append("<td>");
                body.append(table[row][col]);
                body.append("</td>\r\n");
            }
            body.append("</tr>\r\n");
        }
        body.append("</table>\r\n");
    }
    
    /** 
     * Encode a link in the proper format.
     * 
     * @param key String[] of keys of the different args--length must match val[]
     * @param val String[] of vals of the different args--length must match key[]
     * @param display is what will be displayed as the link to click on
     * @param action is the action to take
     * @param ssp is either 'player' or 'team'
     */
    public final String encodeLink(String[] key, String[] val, String display, String action, String ssp) {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"");
        sb.append(ssp);
        sb.append(".ssp?");
        for (int i=0; i<key.length; i++) {
        	sb.append(key[i]);
        	sb.append("=");
        	sb.append(encodeURL(val[i]));
        	sb.append("&");
        }
        sb.append("action=");
        sb.append(action);
        sb.append("\">");
        sb.append(display);
        sb.append("</a>");
        return sb.toString();
    }
   
    protected final String encodeURL(String s) {
        s = s.replace(" ", "+");
        return s;
    }
}
