/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import domen.PoreskaStopa;
import domen.Proizvod;
import domen.Proizvodjac;
import java.util.ArrayList;
import java.util.List;
import server.Server;

/**
 *
 * @author Ognjen
 */
public class Main {

    public static void main(String[] args) {

        List<Proizvodjac> proizvodjaci = new ArrayList<>();
        List<PoreskaStopa> poreskeStope = new ArrayList<>();
        List<Proizvod> proizvodi = new ArrayList<>();

        // Dummy proizvođači
        proizvodjaci.add(new Proizvodjac(1L, "Imlek"));
        proizvodjaci.add(new Proizvodjac(2L, "Nestle"));
        proizvodjaci.add(new Proizvodjac(3L, "Bambi"));

        // Dummy poreske stope
        poreskeStope.add(new PoreskaStopa(1L, "PDV 10%", 0.10));
        poreskeStope.add(new PoreskaStopa(2L, "PDV 20%", 0.20));

        // Dummy proizvodi
        proizvodi.add(new Proizvod(1L, "Čokolada", 120.0, poreskeStope.get(1), proizvodjaci.get(1)));
        proizvodi.add(new Proizvod(2L, "Jogurt", 80.0, poreskeStope.get(0), proizvodjaci.get(0)));
        proizvodi.add(new Proizvod(3L, "Plazma", 150.0, poreskeStope.get(1), proizvodjaci.get(2)));

        Server server = new Server();
        server.startServer(proizvodjaci, proizvodi, poreskeStope);
    }
}
