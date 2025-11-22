/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Ognjen
 */
public class Predmet implements Serializable {

    private int sifra;
    private String naziv;
    private String kod;
    private int brESPB;

    public Predmet() {
    }

    public Predmet(int sifra, String naziv, String kod, int brESPB) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.kod = kod;
        this.brESPB = brESPB;
    }

    public Predmet(String naziv, String kod, int brESPB) {
        this.naziv = naziv;
        this.kod = kod;
        this.brESPB = brESPB;
    }

    public int getBrESPB() {
        return brESPB;
    }

    public void setBrESPB(int brESPB) {
        this.brESPB = brESPB;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
