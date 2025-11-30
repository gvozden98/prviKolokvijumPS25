/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package ui.controller;

import domen.Asistent;
import domen.Predmet;
import java.net.Socket;
import java.util.List;
import komunikacija.Operacija;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;

/**
 *
 * @author Ognjen
 */
public class Controller {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private Controller() {
        try {
            socket = new Socket("127.0.0.1", 9000);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (Exception e) {
            System.out.println("Nema hosta druze!" + e.getMessage());
        }

    }

    public static Controller getInstance() {
        return ControllerHolder.INSTANCE;
    }

    public List<Predmet> getPredmeti() throws Exception {
        Request request = new Request(new Predmet(), Operacija.GET_PREDMETI);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() == null) {
            return (List<Predmet>) response.getResult();
        }
        throw response.getException();
    }

    public void setAsistenti(List<Asistent> asistenti) throws Exception {
        Request request = new Request(asistenti, Operacija.SET_ASISTENTI);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        }
    }

    private static class ControllerHolder {

        private static final Controller INSTANCE = new Controller();
    }

    public void connect() {

    }
}
