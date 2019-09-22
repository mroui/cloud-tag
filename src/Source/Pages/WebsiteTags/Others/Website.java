package Source.Pages.WebsiteTags.Others;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class Website {

    private String url;
    private Document docUrl;
    private List<String> tags;

//====================================================

    public Website(String url) {
        this.url = url;
        this.docUrl = null;
        this.tags = new ArrayList<>();
    }


    public String isValid() {
        String messageReturn = "#ERROR: Wrong URL.";
        try {
            this.docUrl = Jsoup.connect(this.url).get();
            Document docTags = Jsoup.parse(this.docUrl.body().text());
            String tags = docTags.body().text();
            return getOnlyTags(tags);
        } catch (Exception e) {
            if (e.getMessage().contains("Malformed URL:") || e.getMessage().equals(this.url))
                messageReturn = "#ERROR: The URL must be preceded https://";
            if (e.getMessage().contains("java.security.cert.CertificateException: No subject alternative DNS name matching"))
                messageReturn = "#ERROR: Not found alternative name of DNS matching to " + this.url;
            return messageReturn;
        }
    }

    //todo
    private String getOnlyTags(String text) {
        StringBuilder sb = new StringBuilder(text);
        StringBuilder temp = new StringBuilder();

        Pattern unicodePattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
        for(int i=0; i<sb.length(); i++) {
            if(unicodePattern.matcher(String.valueOf(sb.charAt(i))).matches()) {
                temp.append(sb.charAt(i));
            } else if (!temp.toString().equals("")) {   //if is 2x space or special characters side by side
                this.tags.add(temp.toString());
                temp = new StringBuilder();
            }
        }
        return getStringTagsFromList();
    }


    private String getStringTagsFromList() {
        StringBuilder resultTextTags = new StringBuilder();
        for (String x : this.tags) {
            resultTextTags.append(x);
            resultTextTags.append(' ');
        }
        return resultTextTags.toString();
    }
}
