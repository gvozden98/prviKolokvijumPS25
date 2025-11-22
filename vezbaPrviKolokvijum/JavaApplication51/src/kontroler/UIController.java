/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Angazovanje;
import domen.Predmet;
import domen.Profesor;
import domen.Radnik;
import java.net.Socket;
import java.util.List;
import komunikacija.Operacije;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;

/**
 *
 * @author Ognjen
 */
public class UIController {

    private Socket socket;
    private static UIController instance;
    private Sender sender;
    private Receiver receiver;

    private UIController() throws Exception {
        socket = new Socket("127.0.0.1", 9000);
        this.sender = new Sender(socket);
        this.receiver = new Receiver(socket);
    }

    public static UIController getInstance() throws Exception {
        if (instance == null) {
            instance = new UIController();
        }
        return instance;
    }

    public Radnik login(Radnik radnik) throws Exception {

        Request request = new Request(Operacije.LOGIN, radnik);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (Radnik) response.getResult();
        }
    }

    public Profesor dodajProfesora(Profesor profesor) throws Exception {
        Request request = new Request(Operacije.DODAJ_PROFESORA, profesor);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        } else {
            return profesor;
        }
    }

    public Predmet dodajPredmet(Predmet predmet) throws Exception {
        Request request = new Request(Operacije.DODAJ_PREDMET, predmet);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        } else {
            return predmet;
        }

    }

    public List<Profesor> getProfesori() throws Exception {
        Request request = new Request(Operacije.GET_PROFESORI, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (List<Profesor>) response.getResult();
        }
    }

    public Angazovanje dodajAngazovanje(Angazovanje angazovanje) throws Exception {
        Request request = new Request(Operacije.DODAJ_ANGAZOVANJE, angazovanje);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (Angazovanje) response.getResult();
        }

    }

    public List<Predmet> getPredmetProfesor(Profesor profesor) throws Exception {
        Request request = new Request(Operacije.GET_PROFESOR_PREDMET, profesor);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (List<Predmet>) response.getResult();
        }

    }

    public Angazovanje obrisiAngazovanjeProfesora(Angazovanje angazovanje) throws Exception {
        Request request = new Request(Operacije.OBRISI_ANGAZOVANJE, angazovanje);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (Angazovanje) response.getResult();
        }
    }

    public List<Predmet> getPredmeti() throws Exception {
        Request request = new Request(Operacije.GET_PREDMETI, new Predmet());
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (List<Predmet>) response.getResult();
        }
    }
}
