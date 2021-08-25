import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

class pageCrawler {
	String htmlCode;
	
	public pageCrawler(String urlString) {
		this.htmlCode = this.crawlPage(urlString);
	}

	public String getPageHTML() {
		return this.escapeHTML(this.htmlCode);
	}
	
	protected String crawlPage(String urlString) {
	    URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line;
	    String lines = "";

	    try {
	        url = new URL(urlString);
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));
	        while ((line = br.readLine()) != null) {
	            lines += this.minifyLine(line);
	        }
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	    return lines;
	}
	
	protected String minifyLine(String line) {
		String cleanLine = line.replace("\n", "");
		cleanLine = line.replace("\r", "");
		cleanLine = line.replace("\t", "");
		cleanLine = line.replace("  ", "");
		return cleanLine;
	}
	
	public String escapeHTML(String s) {
	    StringBuilder out = new StringBuilder(Math.max(16, s.length()));
	    for (int i = 0; i < s.length(); i++) {
	        char c = s.charAt(i);
	        if (c > 127 || c == '"' || c == '\'' || c == '<' || c == '>' || c == '&') {
	            out.append("&#");
	            out.append((int) c);
	            out.append(';');
	        } else {
	            out.append(c);
	        }
	    }
	    return out.toString();
	}
}
