/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import domen.Radnik;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;
import thread.ClientThread;

/**
 *
 * @author Ognjen
 */
public class Server {

    public List<Radnik> radnici = new LinkedList<>();
    public int loginCounter = 0;

    public Server(List<Radnik> radnici) {
        this.radnici = radnici;
    }

    public void startServer() {
        boolean connected = false;
        try (ServerSocket ss = new ServerSocket(9000)) {
            System.out.println("Server je pokrenut na portu 9000!");
            while (!connected) {
                System.out.println("Cekaamo konekciju...");
                Socket klijentSocket = ss.accept();
                System.out.println("Klijent je konektovan!");
                ClientThread klijent = new ClientThread(klijentSocket, this);
                klijent.start();
                connected = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Greska pri startovanju servera!");
        }
    }

}
