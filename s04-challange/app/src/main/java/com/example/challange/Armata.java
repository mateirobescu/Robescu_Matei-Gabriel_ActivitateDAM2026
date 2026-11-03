package com.example.challange;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

// aRMata - Robescu Matei
public class Armata implements Parcelable {
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

    protected Armata(Parcel in) {
        nrSoldati = in.readInt();
        tara = in.readString();
        esteInRazboi = in.readByte() != 0;
        nrTancuri = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(nrSoldati);
        dest.writeString(tara);
        dest.writeByte((byte) (esteInRazboi ? 1 : 0));
        dest.writeInt(nrTancuri);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Armata> CREATOR = new Creator<Armata>() {
        @Override
        public Armata createFromParcel(Parcel in) {
            return new Armata(in);
        }

        @Override
        public Armata[] newArray(int size) {
            return new Armata[size];
        }
    };

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

    public void setNrSoldati(int nrSoldati) {
        this.nrSoldati = nrSoldati;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public void setEsteInRazboi(boolean esteInRazboi) {
        this.esteInRazboi = esteInRazboi;
    }

    public void setNrTancuri(int nrTancuri) {
        this.nrTancuri = nrTancuri;
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
