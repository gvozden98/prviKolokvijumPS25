/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import domen.Angazovanje;
import domen.Predmet;
import domen.Profesor;
import domen.Radnik;
import java.net.Socket;
import java.util.List;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;
import kontroler.Controller;
import server.Server;

/**
 *
 * @author Ognjen
 */
public class ClientThread extends Thread {

    Server server;
    Socket clientSocket;
    Sender sender;
    Receiver receiver;
    Controller controller;
    int brojPokusaja;

    public ClientThread(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        receiver = new Receiver(clientSocket);
        sender = new Sender(clientSocket);
        controller = new Controller();
        brojPokusaja = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperacija()) {
                        case LOGIN:
                            handleLogin(request, response);
                            break;
                        case DODAJ_PROFESORA:
                            handleDodavanjeProfesora(request, response);
                            break;
                        case GET_PROFESORI:
                            handleGetProfesori(request, response);
                            break;
                        case DODAJ_PREDMET:
                            handleDodavanjePredmeta(request, response);
                            break;
                        case DODAJ_ANGAZOVANJE:
                            handleDodavanjeAngazovanja(request, response);
                            break;
                        case GET_PROFESOR_PREDMET:
                            handleProfesorPredmet(request, response);
                            break;
                        case OBRISI_ANGAZOVANJE:
                            handleDeleteAngazovanje(request, response);
                            break;
                        case GET_PREDMETI:
                            handleGetPredmeti(request, response);
                            break;
                        default:
                            throw new AssertionError();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Greska pri obradi operacije!");
                    response.setException(e);
                }
                if (brojPokusaja >= 3) {
                    response.setException(new Exception("3 pokusaja iskoriscena!"));
                    sender.send(response);
                    clientSocket.close();
                    break;
                } else {
                    sender.send(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Greska pri primanju objekta!");
            }

        }
    }

    private Radnik checkLogin(Radnik radnik) {
        for (Radnik radnik1 : server.radnici) {
            if (radnik1.getEmail().equals(radnik.getEmail()) && radnik1.getSifra().equals(radnik.getSifra())) {
                return radnik1;
            }
        }
        return null;
    }

    private void handleLogin(Request request, Response response) throws Exception {
        Radnik loginRadnik = (Radnik) request.getArgument();
        Radnik ulogovan = checkLogin(loginRadnik);
        if (ulogovan == null) {
            brojPokusaja++;
            response.setException(new Exception("Pogresni kredencijali!"));
            response.setResult(null);
        } else {
            response.setResult(ulogovan);
        }
    }

    private void handleDodavanjeProfesora(Request request, Response response) throws Exception {
        Profesor profesor = (Profesor) request.getArgument();
        controller.dodajProfesora(profesor);
        response.setResult(profesor);
    }

    private void handleGetProfesori(Request request, Response response) throws Exception {
        List<Profesor> profesori = controller.getProfesori();
        response.setResult(profesori);
    }

    private void handleDodavanjePredmeta(Request request, Response response) throws Exception {
        Predmet predmet = (Predmet) request.getArgument();
        controller.dodajPredmet(predmet);
        response.setResult(predmet);
    }

    private void handleDodavanjeAngazovanja(Request request, Response response) throws Exception {
        Angazovanje angazovanje = (Angazovanje) request.getArgument();
        controller.dodajAngazovanje(angazovanje);
        response.setResult(angazovanje);
    }

    public void handleProfesorPredmet(Request request, Response response) throws Exception {
        Profesor profesor = (Profesor) request.getArgument();
        List<Predmet> predmeti = controller.prikaziPredmeteZaProfesora(profesor);
        response.setResult(predmeti);
    }

    public void handleDeleteAngazovanje(Request request, Response response) throws Exception {
        Angazovanje angazovanje = (Angazovanje) request.getArgument();
        controller.obrisiAngazovanjeProfesora(angazovanje);
        response.setResult(angazovanje);
    }

    private void handleGetPredmeti(Request request, Response response) {
        List<Predmet> predmeti = controller.getPredmeti();
        response.setResult(predmeti);
    }
}
