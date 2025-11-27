/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package controller;

import db.DatabaseBroker;
import domen.Profesor;
import domen.Proizvod;
import java.util.List;

/**
 *
 * @author Ognjen
 */
public class Controller {

    private Controller() {

    }

    public static Controller getInstance() {
        return ControllerHolder.INSTANCE;
    }

    private static class ControllerHolder {

        private static final Controller INSTANCE = new Controller();
    }

    private final DatabaseBroker broker = DatabaseBroker.getInstance();

    public List<Proizvod> getProizvodi() throws Exception {
        return broker.vratiProizvode();
    }

    public List<Profesor> getProfesori() throws Exception {
        return broker.vratiProfesore();
    }

    public void setProfesori(List<Profesor> profesori) throws Exception {
        broker.postaviProfesore(profesori);
    }

}
