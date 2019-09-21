package Source.websiteTags;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Website {

    private String url;
    private Document docUrl;
    private List<String> tags;

//====================================================

    Website(String url) {
        this.url = url;
        this.docUrl = null;
        this.tags = new ArrayList<>();
    }


    String isValid() {
        String messageReturn = "#BŁĄD: Błędny adres URL.";
        try {
            this.docUrl = Jsoup.connect(this.url).get();
            Document docTags = Jsoup.parse(this.docUrl.body().text());
            String tags = docTags.body().text();
            return getOnlyTags(tags);
        } catch (Exception e) {
            if (e.getMessage().equals("protocol = https host = null"))
                messageReturn = "#BŁĄD: Błędny adres URL";
            if (e.getMessage().contains("Malformed URL:"))
                messageReturn = "#BŁĄD: Adres URL musi poprzedzać https://";
            if (e.getMessage().equals(this.url))
                messageReturn = "#BŁĄD: Adres URL musi poprzedzać https://";
            if (e.getMessage().contains("java.security.cert.CertificateException: No subject alternative DNS name matching"))
                messageReturn = "#BŁĄD: Nie znaleziono alternatywnej nazwy DNS pasującej do " + this.url;
            return messageReturn;
        }
    }


    private String getOnlyTags(String text) {
        List<Character> polishChars = new ArrayList<>(Arrays.asList( 'ą', 'ę', 'ó', 'ł', 'ż', 'ź', 'ć', 'ś', 'ń',
                                                                     'Ą', 'Ę', 'Ó', 'Ł', 'Ż', 'Ź', 'Ć', 'Ś', 'Ń'));
        StringBuilder sb = new StringBuilder(text);
        StringBuilder temp = new StringBuilder();

        for(int i=0; i<sb.length(); i++) {
            if(((sb.charAt(i)>=65) && (sb.charAt(i)<=90) || ((sb.charAt(i)>=97) && (sb.charAt(i)<=122)) || polishChars.contains(sb.charAt(i)))) {
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
