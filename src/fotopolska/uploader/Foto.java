package fotopolska.uploader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Foto {
    Document doc;
    String number;
    String uploader;
    
    String city;
    
    String image;
    String name;
    String desc;
    String location;
    String date;
    String source;
    String author;
    String authorLink;
    String coord;
    String license;
    String extra;
    
    public Foto(String number) throws IOException {
        this.doc = Jsoup.connect("http://fotopolska.eu/" + number + ",foto.html").get();
        this.number = number;
        this.uploader = setUploader();
        
        this.desc = setDesc();
        this.location = setLocation();
        this.date = setDate();
        this.author = setAuthor();
        this.authorLink = setAuthorLink();
        this.coord = setCoord();
        this.license = setLicense();
        
        this.image = setImage();
        this.name = setName();
    }
    
    /**
     * SET
     */
    
    private String setImage() {
        Elements e = doc.select("#FotoLoader img");
        if(!e.isEmpty())
            return "http://fotopolska.eu" + e.get(0).attr("src");
        else
            return "??";
    }
    
    private String setName() {
        Elements e = doc.getElementsByTag("title");
        String text = "??";
        
        if(!e.isEmpty()) {
            if(!e.text().equals("fotopolska.eu - Polska na fotografii"))
                text = e.text().replace("<i>", "").replace("</i", "").replace("<br>", "") + " (" + number + ")";
            else {
                if(city!=null) 
                    text = city + " - fotopolska.eu (" + number + ")";
                else
                   text = "fotopolska.eu (" + number + ")"; 
            } 
        }
        return text + ".jpg";
    }
    
    private String setDesc() {
        Element e = doc.select("#ed_fn" + number).get(0);
        return e.text();
    }
    
    private String setLocation() {
        Elements e = doc.select("table td.sz7 b");
        if(e!=null || !e.isEmpty()) {
            String text = "{{Building address\n |Street name = \n |House number = \n |House name = \n |Postal code = \n |City = \n" +
                    " |State = \n |Country = PL\n |Listing = \n}}\n";
            String[] texts = e.get(0).text().split(" / ");
            if(texts.length> 1) {
                if(!texts[1].contains("kolejowe")) {
                    //województwo
                    text = text.replace("|State = ", "|State = " + texts[1].substring(5));

                    //miasto
                    if(texts[2].contains("Powiat")) {
                        text = text.replace("|City = ", "|City = " + texts[3]);
                        city = texts[3];
                    }
                    else {
                        if(!texts[3].contains("ul.")) text = text.replace("|City = ", "|City = " + texts[2] + " (" + texts[3] + ")");
                        else text = text.replace("|City = ", "|City = " + texts[2]);
                        city = texts[2];
                    }

                    for(int i=3; i<texts.length; ++i) {
                        if(texts[i].contains("ul. ") || texts[i].contains("Plac")) {
                            String street = texts[i];
                            if(street.contains("ul. ")) street = street.substring(4);
                            //numer
                            if(street.contains(" ")) {
                                String nr = street.substring(street.lastIndexOf(" ")+1);
                                if(nr.matches("[0-9-]{1,10}")) {
                                    street = street.replace(nr, "");
                                    
                                    text = text.replace("|House number = ", "|House number = " + nr);
                                    text = text.replace("|Street name = ", "|Street name = " + street); 
                                } else
                                   text = text.replace("|Street name = ", "|Street name = " + street);  
                            //nie ma numeru
                            } else {
                               text = text.replace("|Street name = ", "|Street name = " + street); 
                            }
                            break;
                        }
                    }
                    return text;
                }
            }
            return "";
        } else
            return "";
    }
    
    private String setDate() {
        String text = "?";
        if(!doc.select("div#exif").isEmpty()) {
            Element e = doc.select("div#exif").get(0);
            text = e.text().replaceAll(".*([0-9]{4}:[0-9]{2}:[0-9]{2}).*", "$1").replace(":", "-");
        } else {
            String[] month = {"styczeń", "luty", "marzec", "kwiecień", "maj", "czerwiec", "lipiec", "sierpień", "wrzesień", "październik", "listopad", "grudzień"};
            Element e = doc.select(".sz7b").get(0);

            text = e.text();
            for(int i=0; i<month.length; ++i) {
                if(text.contains(month[i])) {
                    String nr = i+1+"";
                    if(i<10) nr = "0"+nr;
                    text = text.replace(month[i],nr).replace(" ", "-");
                    break;
                }
            }
            text = text.replaceAll("([0-9]{4})-([0-9]{2})-([0-9])", "$1-$2-0$3");
            
            if(text.contains("Zdjęcie z lat"))
                text = text.replace("Zdjęcie z lat ", "");
            if(text.contains("Zdjęcie z roku"))
                text = text.replace("Zdjęcie z roku ", "");
        }
        return text;
    }
    
    private String setAuthor() {
        Element e = doc.select(".zoltemale").get(0);
        return e.text().replace("*", "").replace("+", "")/*.substring(0, e.text().length()-1)*/;
    }
    
    private String setUploader() {
        Elements e = doc.select("#adm");
        if(!e.isEmpty())
            return e.get(0).text();
        else {
            e = doc.select("#reg");
            if(!e.isEmpty())
                return e.get(0).text().replace("*", "");
            else
                return "???";
        }
    }
    
    private String setAuthorLink() {
        Element e = doc.select(".zoltemale").get(0);
        String s = e.attr("href");
        if(s.contains("zrodlo=261&") || s.contains("zrodlo=12&") || s.contains("zrodlo=61&"))
            return "Wikimedia Commons";
        else
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
        if(!e.isEmpty()) {
            String s = e.get(0).text();
            if(s.equals("Public Domain")) s = "PD-author";
            if(s.contains("CC-BY-SA")) s = s.replaceAll("CC-BY-SA ([0-9\\.]{1,3})", "cc-by-sa-$1");
            
            return "{{" + s + "|[" + authorLink + " " + author + " / fotopolska.eu]}}\n";
        } else
            return "??";
    }
    
    /*
     * OTHER
     */
    public boolean isUploadable() {
        if(authorLink.equals("Wikimedia Commons") || license.equals("??") || image.equals("??") || author.contains("Nemo5576"))
            return false;
        else
            return true;      
    }
    
    public String getFileName() {
        return name;
    }
    
    public URL getFileSource() throws MalformedURLException {
        return new URL(image);
    }
    
    public String getWikiText() {
        String text = "=={{int:filedesc}}==\n{{Information\n" +
            "|description = {{pl|1=" + desc + "}}\n" + location +
            "|date = " + date + "\n" +
            "|source = [http://fotopolska.eu/" + number + ",foto.html Fotopolska.eu]" + "\n" +
            "|author = [" + authorLink + " " + author + "]\n" +
            "|permission = \n" +
            "|other_versions = \n" +
            "|other_fields = \n" +
            "}}\n" +
            coord + 
            "\n=={{int:license-header}}==\n" + license + "{{Fotopolska review|Yarl|2012-10-10}}\n{{Watermark}}\n\n[[Category:" + city + "]]";
        
        return text;
    }
    
    @Override
    public String toString() {
        String text = image + "\n" + "File:" + name + "\n" + getWikiText() + "\n\n";
        return text;
    }
}
