/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import domen.Radnik;
import java.util.LinkedList;
import java.util.List;
import server.Server;

/**
 *
 * @author Ognjen
 */
public class Main {

    public static void main(String[] args) {
        List<Radnik> radnici = new LinkedList<>();
        Radnik r1 = new Radnik(1, "Milos", "Milosevic", "m@gmail.com", "123");
        Radnik r2 = new Radnik(2, "Pera", "Peric", "p@gmail.com", "123");
        Radnik r3 = new Radnik(3, "Djura", "Djuric", "dj@gmail.com", "123");
        radnici.add(r1);
        radnici.add(r2);
        radnici.add(r3);
        Server server = new Server(radnici);
        server.startServer();

    }
}
