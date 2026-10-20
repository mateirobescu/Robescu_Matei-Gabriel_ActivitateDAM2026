package com.example.challange;

import java.io.Serializable;

// aRMata - Robescu Matei
public class Armata implements Serializable {
    //proprieta pt id serializabil

    int nrSoldati;
    String tara;

    boolean esteInRazboi;

    int nrTancuri;

    public Armata(int nrSoldati, String tara, boolean esteInRazboi, int nrTancuri) {
        this.nrSoldati = nrSoldati;
        this.tara = tara;
        this.esteInRazboi = esteInRazboi;
        this.nrTancuri = nrTancuri;
    }

    public int getNrSoldati() {
        return nrSoldati;
    }

    public String getTara() {
        return tara;
    }

    public boolean isEsteInRazboi() {
        return esteInRazboi;
    }

    public int getNrTancuri() {
        return nrTancuri;
    }

    @Override
    public String toString() {
        return "Armata{" +
                "nrSoldati=" + nrSoldati +
                ", tara='" + tara + '\'' +
                ", esteInRazboi=" + esteInRazboi +
                ", nrTancuri=" + nrTancuri +
                '}';
    }
}
