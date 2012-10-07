package fotopolska.uploader;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Foto {
    Document doc;
    String number;
    String uploader;
    
    String image;
    String name;
    String desc;
    String date;
    String source;
    String author;
    String authorLink;
    String coord;
    String license;
    String extra;
    
    public Foto(String url) throws IOException {
        this.doc = Jsoup.connect(url).get();
        this.number = url.replaceAll(".*fotopolska.eu/", "").replace(",foto.html", "");
        this.uploader = setUploader();
        
        this.image = setImage();
        this.name = setName();
        this.desc = setDesc();
        this.date = setDate();
        this.author = setAuthor();
        this.authorLink = setAuthorLink();
        this.coord = setCoord();
        this.license = setLicense();
    }
    
    /**
     * SET
     */
    
    private String setImage() {
        Elements e = doc.select("#FotoLoader img");
        if(!e.isEmpty())
            return "http://fotopolska.eu" + e.get(0).attr("src");
        else
            return "???";
    }
    
    private String setName() {
        Elements e = doc.getElementsByTag("title");
        if(!e.isEmpty())
            return e.text() + " (" + number + ")";
        else
            return "???";
    }
    
    private String setDesc() {
        Element e = doc.select("#ed_fn" + number).get(0);
        return e.text();
    }
    
    private String setDate() {
        String[] month = {"styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", "lipiec", "sierpień", "wrzesień", "październik", "listopad", "grudzień"};
        Element e = doc.select(".sz7b").get(0);
            
        String text = e.text();
        for(int i=0; i<month.length; ++i) {
            if(text.contains(month[i])) {
                String nr = i+1+"";
                if(i<10) nr = "0"+nr;
                text = text.replace(month[i],nr).replace(" ", "-");
                break;
            }
        }
        if(text.contains("Zdjęcie z lat"))
            text = text.replace("Zdjęcie z lat ", "");
        if(text.contains("Zdjęcie z roku"))
            text = text.replace("Zdjęcie z roku ", "");
         
        return text;
    }
    
    private String setAuthor() {
        Element e = doc.select(".zoltemale").get(0);
        return e.text()/*.substring(0, e.text().length()-1)*/;
    }
    
    private String setUploader() {
        Elements e = doc.select("#adm");
        if(!e.isEmpty())
            return e.get(0).text();
        else {
            e = doc.select("#reg");
            if(!e.isEmpty())
                return e.get(0).text();
            else
                return "???";
        }
    }
    
    private String setAuthorLink() {
        Element e = doc.select(".zoltemale").get(0);
        return "http://fotopolska.eu" + e.attr("href");
    }
    
    private String setCoord() {
        Element e = doc.getElementsByTag("script").get(4);
        String text = e.html();
        text = text.substring(234, 270).replace(", ", "|");
        
        if(text.matches("[0-9\\.\\|]*"))
            return "{{Object location dec|" + text + "}}\n";
        else
            return "";
    }
    
    private String setLicense() {
        Elements e = doc.select("table.fopis div.sz7 a");
        if(!e.isEmpty())
            return "{{" + e.get(0).text() + "}}\n";
        else
            return "{{?????}}\n";
    }
    
    @Override
    public String toString() {
        String text =  image + "\n" + "File:" + name + "\n" +
            "{{Information\n" +
            "|description = {{pl|1=" + desc + "}}\n" +
            "|date = " + date + "\n";
        if(uploader.equals(author))
            text += "|source = {{own}}" + "\n";
        else
            text += "|source = ??" + "\n";
        text += "|author = [" + authorLink + " " + author + "]\n" +
            "|permission = \n" +
            "|other_versions = \n" +
            "|other_fields = \n" +
            "}}\n" +
            coord + license + 
            "---------\n";
        return text;
    }
}
