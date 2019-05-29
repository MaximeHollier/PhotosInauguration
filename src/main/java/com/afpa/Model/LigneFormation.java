package com.afpa.Model;

public class LigneFormation {
    private String région;
    private String libellé;

    public LigneFormation(String région, String libellé) {
        this.région = région;
        this.libellé = libellé;
    }

    public String getRégion() {
        return région;
    }

    public void setRégion(String région) {
        this.région = région;
    }

    public String getLibellé() {
        return libellé;
    }

    public void setLibellé(String libellé) {
        this.libellé = libellé;
    }
}
