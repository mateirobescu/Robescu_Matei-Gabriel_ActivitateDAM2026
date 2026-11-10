package com.example.challange;

public class ImagineArmate {
    private String urlImagine;
    private String urlDescriere;
    private String descriere;

    public ImagineArmate(String urlImagine, String urlDescriere, String descriere) {
        this.urlImagine = urlImagine;
        this.urlDescriere = urlDescriere;
        this.descriere = descriere;
    }

    public String getUrlImagine() {
        return urlImagine;
    }

    public String getUrlDescriere() {
        return urlDescriere;
    }

    public String getDescriere() {
        return descriere;
    }
}

