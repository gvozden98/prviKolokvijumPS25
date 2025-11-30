/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package controller;

import db.DatabaseBroker;
import domen.Asistent;
import domen.Predmet;
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

    public void setAsistenti(List<Asistent> asistenti) throws Exception {
        broker.setAsistenti(asistenti);
    }

    public List<Predmet> getPredmeti() throws Exception {
        return broker.getPredmeti();
    }

    private static class ControllerHolder {

        private static final Controller INSTANCE = new Controller();
    }

    private final DatabaseBroker broker = DatabaseBroker.getInstance();

}
