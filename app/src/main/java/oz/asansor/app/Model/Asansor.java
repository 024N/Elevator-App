package oz.asansor.app.Model;

import java.io.Serializable;

public class Asansor implements Serializable {

    private String kat;
    private String evSahibi;
    private String documentID;

    public Asansor() {
    }

    public Asansor(String kat, String evSahibi) {
        this.kat = kat;
        this.evSahibi = evSahibi;
    }

    public Asansor(String kat, String evSahibi, String documentID) {
        this.kat = kat;
        this.evSahibi = evSahibi;
        this.documentID = documentID;
    }

    public String getKat() {
        return kat;
    }

    public void setKat(String kat) {
        this.kat = kat;
    }

    public String getEvSahibi() {
        return evSahibi;
    }

    public void setEvSahibi(String evSahibi) {
        this.evSahibi = evSahibi;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    @Override
    public String toString() {
        return "Asansor{" +
                "kat='" + kat + '\'' +
                ", evSahibi='" + evSahibi + '\'' +
                ", documentID='" + documentID + '\'' +
                '}';
    }
}