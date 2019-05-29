package com.afpa.Model;

public class LigneFormation {
    private String region;
    private String libelle;

    public LigneFormation(String region, String libelle) {
        this.region = region;
        this.libelle = libelle;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
