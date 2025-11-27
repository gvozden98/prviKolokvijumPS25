/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import domen.PoreskaStopa;
import domen.Profesor;
import domen.Proizvod;
import domen.Proizvodjac;
import domen.Status;
import domen.Zvanje;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author Ognjen
 */
public class DatabaseBroker {

    private static DatabaseBroker instance;

    private DatabaseBroker() {

    }

    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }

    public List<Proizvod> vratiProizvode() throws Exception {
        List<Proizvod> proizvodi = new ArrayList<>();
        String sql = """
                     SELECT p.sifra, p.naziv, p.cena, pr.sifra AS sifraProizvodjaca, pr.naziv
                     AS nazivProizvodjaca, ps.sifra AS sifraStope, ps.skraceni_naziv, ps.iznos
                     FROM proizvod p JOIN proizvodjac pr ON p.proizvodjac_id = pr.sifra
                     JOIN poreska_stopa ps ON p.poreska_stopa_id = ps.sifra
                     """;

        PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PoreskaStopa stopa = new PoreskaStopa();
            stopa.setIznos(rs.getDouble("iznos"));
            stopa.setSkraceniNaziv(rs.getString("skraceni_naziv"));
            stopa.setSifra(rs.getLong("sifraStope"));

            Proizvodjac proizvodjac = new Proizvodjac();
            proizvodjac.setNaziv(rs.getString("nazivProizvodjaca"));
            proizvodjac.setSifra(rs.getLong("sifraProizvodjaca"));

            Proizvod proizvod = new Proizvod();
            proizvod.setCena(rs.getDouble("cena"));
            proizvod.setNaziv(rs.getString("naziv"));
            proizvod.setSifra(rs.getLong("sifra"));
            proizvod.setPoreskaStopa(stopa);
            proizvod.setProizvodjac(proizvodjac);

            proizvodi.add(proizvod);
        }
        return proizvodi;
    }

    public List<Profesor> vratiProfesore() throws Exception {
        List<Profesor> profesori = new LinkedList<>();

        String sql = "SELECT * FROM profesor";
        Statement st = DatabaseConnection.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Profesor p = new Profesor();
            p.setId(rs.getInt("id"));
            p.setIme(rs.getString("ime"));
            p.setPrezime(rs.getString("prezime"));
            String zvanjeStr = rs.getString("zvanje");
            p.setZvanje(Zvanje.valueOf(zvanjeStr));
            p.setStatus(Status.valueOf(rs.getString("status")));
            profesori.add(p);
        }
        rs.close();
        return profesori;

    }

    public void postaviProfesore(List<Profesor> profesori) throws Exception {
        String query = "UPDATE profesor SET ime = ?, prezime = ?, zvanje = ? WHERE id = ?";
        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            for (Profesor profesor : profesori) {
                ps.setString(1, profesor.getIme());
                ps.setString(2, profesor.getPrezime());
                ps.setString(3, profesor.getZvanje().toString());
                ps.setInt(4, profesor.getId());
                ps.addBatch();
            }
            int[] rs = ps.executeBatch();
            System.out.println("broj imzmenjenih je: " + rs.length);

            DatabaseConnection.getInstance().getConnection().commit();
        } catch (Exception e) {
            DatabaseConnection.getInstance().getConnection().rollback();
            e.printStackTrace();
            throw new Exception("Greska pri ubacivanju profe u bazu ->" + e.getMessage());

        }
    }
}
