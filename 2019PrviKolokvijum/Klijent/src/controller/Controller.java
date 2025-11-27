/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package controller;

import domen.PoreskaStopa;
import domen.Profesor;
import domen.Proizvod;
import domen.Proizvodjac;
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
            System.out.println("greska bato");
        }

    }

    public static Controller getInstance() {
        return ControllerHolder.INSTANCE;
    }

    private static class ControllerHolder {

        private static final Controller INSTANCE = new Controller();
    }

    public Proizvod setProizvod(Proizvod proizvod) throws Exception {
        Request request = new Request(proizvod, Operacija.SET_PROIZVOD);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (Proizvod) response.getResult();
        }
    }

    public List<Proizvod> getProizvodi() throws Exception {
        Request request = new Request(new PoreskaStopa(), Operacija.GET_PROIZVODI);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (List<Proizvod>) response.getResult();
        }
    }

    public List<Proizvodjac> getProizvodjaci() throws Exception {
        Request request = new Request(new PoreskaStopa(), Operacija.GET_PROIZVODJACI);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (List<Proizvodjac>) response.getResult();
        }
    }

    public List<PoreskaStopa> getPoreskaStopa() throws Exception {
        Request request = new Request(new PoreskaStopa(), Operacija.GET_PORESKA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (List<PoreskaStopa>) response.getResult();
        }
    }

    public List<Profesor> getProfesori() throws Exception {
        Request request = new Request(new Profesor(), Operacija.GET_PROFESORI);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (List<Profesor>) response.getResult();
        }
    }

    public Object setProfesori(List<Profesor> profesori) throws Exception {
        Request request = new Request(profesori, Operacija.SET_PROFESORI);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return response.getResult();
        }

    }

    public void connect() {

    }

}
