/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Asistent;
import domen.Predmet;
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

    public List<Predmet> getPredmeti() throws Exception {
        List<Predmet> predmeti = new LinkedList<>();
        String sql = "SELECT * from tpredmet";
        PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Predmet predmet = new Predmet();
            predmet.setSifra(rs.getInt("Sifra"));
            predmet.setNazivPredmeta(rs.getString("NazivPredmeta"));
            predmet.setBrojBodova(rs.getInt("BrojBodova"));
            predmet.setSemestar(rs.getInt("Semestar"));
            predmeti.add(predmet);
        }
        return predmeti;
    }

    public void setAsistenti(List<Asistent> asistenti) throws Exception {
        String sql = "INSERT INTO tasistent (Sifra, Ime, Prezime, PredmetID, Titula) VALUES (?,?,?,?,?)";

        try (PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql)) {

            for (Asistent asistent : asistenti) {
                ps.setInt(1, asistent.getSifra());
                ps.setString(2, asistent.getIme());
                ps.setString(3, asistent.getPrezime());
                ps.setInt(4, asistent.getPredmet().getSifra());
                ps.setString(5, asistent.getTitula());
                ps.executeUpdate();
            }
            DatabaseConnection.getInstance().getConnection().commit();
        } catch (Exception e) {
            DatabaseConnection.getInstance().getConnection().rollback();
            e.printStackTrace();
            throw e;
        }
    }
}
