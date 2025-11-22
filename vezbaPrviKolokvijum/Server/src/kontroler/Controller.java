/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import db.DbBroker;
import domen.Angazovanje;
import domen.Predmet;
import domen.Profesor;
import java.util.List;

/**
 *
 * @author Ognjen
 */
public class Controller {

    public Profesor dodajProfesora(Profesor profesor) throws Exception {
        DbBroker broker = new DbBroker();
        return broker.dodajProfesora(profesor);
    }

    public List<Profesor> getProfesori() throws Exception {
        DbBroker broker = new DbBroker();
        return broker.getProfesori();
    }

    public void dodajPredmet(Predmet predmet) throws Exception {
        DbBroker broker = new DbBroker();
        broker.dodajPredmet(predmet);
    }

    public void dodajAngazovanje(Angazovanje angazovanje) throws Exception {
        DbBroker broker = new DbBroker();
        broker.dodajAngazovanje(angazovanje);
    }

    public List<Predmet> prikaziPredmeteZaProfesora(Profesor profesor) throws Exception {
        DbBroker broker = new DbBroker();
        return broker.prikaziPredmeteZaProfesora(profesor);
    }

    public void obrisiAngazovanjeProfesora(Angazovanje angazovanje) throws Exception {
        DbBroker broker = new DbBroker();
        broker.obrisiAngazovanjeProfesora(angazovanje);
    }

    public List<Predmet> getPredmeti() {
        DbBroker broker = new DbBroker();
        return broker.vratiPredmete();
    }
}
