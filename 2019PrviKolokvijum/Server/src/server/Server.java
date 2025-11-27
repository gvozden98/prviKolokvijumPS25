/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import com.mysql.cj.conf.PropertyKey;
import controller.Controller;
import domen.PoreskaStopa;
import domen.Profesor;
import domen.Proizvod;
import domen.Proizvodjac;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;

/**
 *
 * @author Ognjen
 */
public class Server {

    public void startServer(List<Proizvodjac> proizvodjaci, List<Proizvod> proizvodi, List<PoreskaStopa> stopa) {
        try (ServerSocket ss = new ServerSocket(9000)) {
            System.out.println("Server je pokrenut! Cekam klijenta...");

            try (Socket s = ss.accept()) {
                System.out.println("Klijent konektovan!");

                Sender sender = new Sender(s);
                Receiver receiver = new Receiver(s);
                Response response = new Response();
                while (true) {
                    try {
                        Request request = (Request) receiver.receive();

                        switch (request.getOperacija()) {
                            case GET_PORESKA:
                                response.setResult(stopa);
                                break;

                            case GET_PROIZVODI:
                                response.setResult(proizvodi);
                                break;

                            case GET_PROIZVODJACI:
                                response.setResult(proizvodjaci);
                                break;
                            case SET_PROIZVOD:
                                Proizvod noviProizvod = (Proizvod) request.getArgument();
                                response.setResult(handleSetProizvod(proizvodi, noviProizvod));
                                break;
                            case GET_PROFESORI:
                                response.setResult(handleGetProfesori());
                                break;
                            case SET_PROFESORI:
                                List<Profesor> profesori = (List<Profesor>) request.getArgument();
                                handleSetProfesori(profesori);
                                response.setResult(true);
                                break;
                            default:
                                throw new AssertionError();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        response.setException(e);
                    }
                    sender.send(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Greska pri konektovanju klijenta!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Greska pri startovanju servera!");
        }

    }

    public Proizvod handleSetProizvod(List<Proizvod> proizvodi, Proizvod proizvod) {
        for (int i = 0; i < proizvodi.size(); i++) {
            if (Objects.equals(proizvod.getSifra(), proizvodi.get(i).getSifra())) {
                proizvodi.set(i, proizvod); // this actually updates the list
                break;
            }
        }
        return proizvod;
    }

    private List<Profesor> handleGetProfesori() throws Exception {
        return Controller.getInstance().getProfesori();
    }

    private void handleSetProfesori(List<Profesor> profesori) throws Exception {
        Controller.getInstance().setProfesori(profesori);
    }
}
