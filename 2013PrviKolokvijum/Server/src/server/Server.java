/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import domen.Asistent;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;

/**
 *
 * @author Ognjen
 */
public class Server {

    public void startServer() {
        try (ServerSocket ss = new ServerSocket(9000)) {
            System.out.println("Server pokrenut na portu 9000");
            try (Socket socket = ss.accept()) {
                System.out.println("Klijent se konektovao!");
                Receiver receiver = new Receiver(socket);
                Sender sender = new Sender(socket);
                Response response = new Response();
                while (true) {
                    try {
                        Request request = (Request) receiver.receive();
                        switch (request.getOperacija()) {
                            case GET_ASISTENTI:

                                break;
                            case GET_PREDMETI:
                                response.setResult(Controller.getInstance().getPredmeti());
                                break;
                            case SET_ASISTENTI:
                                Controller.getInstance().setAsistenti((List< Asistent>) request.getArgument());
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
                System.out.println("Greska pri konektovanju korisnika!");
            }
        } catch (Exception e) {
            System.out.println("Greska pri pokretanju servera!");
        }
    }
}
