/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Proizvod implements Serializable {

    private Long sifra;
    private String naziv;
    private double cena;
    private PoreskaStopa poreskaStopa;
    private Proizvodjac proizvodjac;

    public Proizvod() {
    }

    public Proizvod(Long sifra, String naziv, double cena, PoreskaStopa poreskaStopa, Proizvodjac proizvodjac) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.cena = cena;
        this.poreskaStopa = poreskaStopa;
        this.proizvodjac = proizvodjac;
    }

    public Long getSifra() {
        return sifra;
    }

    public void setSifra(Long sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public PoreskaStopa getPoreskaStopa() {
        return poreskaStopa;
    }

    public void setPoreskaStopa(PoreskaStopa poreskaStopa) {
        this.poreskaStopa = poreskaStopa;
    }

    public Proizvodjac getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(Proizvodjac proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
