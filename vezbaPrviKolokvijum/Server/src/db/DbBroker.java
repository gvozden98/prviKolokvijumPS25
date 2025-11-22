/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Angazovanje;
import domen.Predmet;
import domen.Profesor;
import domen.Radnik;
import domen.Zvanje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ognjen
 */
public class DbBroker {

    public Profesor dodajProfesora(Profesor profesor) throws Exception {
        try {
            String query = "INSERT INTO profesor (ime,prezime,zvanje,emailKorisnika) VALUES(?,?,?,?)";
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, profesor.getIme());
            ps.setString(2, profesor.getPrezime());
            ps.setString(3, profesor.getZvanje().name());
            ps.setString(4, profesor.getEmail());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                profesor.setId(rs.getInt(1));
            }
            DbConnection.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnection.getInstance().getConnection().rollback();
            throw new Exception("Greska prilikom dodavanja profe!");
        }
        return profesor;
    }

    public List<Profesor> getProfesori() throws Exception {
        List<Profesor> profesori = new ArrayList<>();
        try {
            String query = "SELECT id,ime,prezime,zvanje,emailKorisnika FROM profesor";
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Profesor profesor = new Profesor();
                profesor.setId(rs.getInt("id"));
                profesor.setIme(rs.getString("ime"));
                profesor.setPrezime(rs.getString("prezime"));
                String zvanjeStr = rs.getString("zvanje");
                profesor.setZvanje(Zvanje.valueOf(zvanjeStr));
                profesor.setEmail(rs.getString("emailKorisnika"));
                profesori.add(profesor);
            }
            DbConnection.getInstance().getConnection().commit();

        } catch (Exception e) {
            e.printStackTrace();
            DbConnection.getInstance().getConnection().rollback();
            throw new Exception("Greska prilikom dohvatanja profesora!");
        }
        return profesori;
    }

    public void dodajPredmet(Predmet predmet) throws Exception {
        try {
            String query = "INSERT INTO predmet (sifra,naziv,kod,ESPB) VALUES (?,?,?,?)";
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, predmet.getSifra());
            ps.setString(2, predmet.getNaziv());
            ps.setString(3, predmet.getKod());
            ps.setInt(4, predmet.getBrESPB());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Greska prilikom dohvatanja profesora!");
        }
    }

    public void dodajAngazovanje(Angazovanje angazovanje) throws Exception {
        try {
            int broj = brojAngazovanjaProfesora(angazovanje.getIdProfesora());
            if (broj >= 3) {
                throw new Exception("Profesor moze biti angazovan na najvise 3 predmeta!");
            }
            String query = "INSERT INTO angazovanje (idProfesora,idPredmeta) VALUES (?,?)";
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, angazovanje.getIdProfesora());
            ps.setInt(2, angazovanje.getIdPredmeta());
            ps.executeUpdate();
            DbConnection.getInstance().getConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
            DbConnection.getInstance().getConnection().rollback();
            throw new Exception("Greska prilikom dodavanja angazovanja!");
        }
    }

    private int brojAngazovanjaProfesora(int idProfesora) throws Exception {
        int broj = 0;
        try {
            String query = "SELECT COUNT(*) AS broj FROM angazovanje where idProfesora=?";
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareCall(query);
            ps.setInt(1, idProfesora);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                broj = rs.getInt("broj");
            }

        } catch (Exception e) {
            e.printStackTrace();
            DbConnection.getInstance().getConnection().rollback();
            throw new Exception("Greska prilikom uzimanja profe i provere broja angazovanja!");
        }
        return broj;
    }

    public List<Predmet> prikaziPredmeteZaProfesora(Profesor profesor) throws Exception {
        List<Predmet> lista = new ArrayList<>();
        try {
            String query = """
        SELECT p.sifra, p.naziv, p.kod, p.ESPB
        FROM angazovanje a
        JOIN predmet p ON p.sifra = a.idPredmeta
        WHERE a.idProfesora = ?
               """;
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, profesor.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Predmet p = new Predmet();
                p.setSifra(rs.getInt("sifra"));
                p.setNaziv(rs.getString("naziv"));
                p.setKod(rs.getString("kod"));
                p.setBrESPB(rs.getInt("ESPB"));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public void obrisiAngazovanjeProfesora(Angazovanje angazovanje) throws Exception {
        try {
            String query = "DELETE FROM angazovanje where idPredmeta = ? and idProfesora=?";
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, angazovanje.getIdPredmeta());
            ps.setInt(2, angazovanje.getIdProfesora());
            ps.executeUpdate();
            DbConnection.getInstance().getConnection().commit();

        } catch (Exception e) {
            e.printStackTrace();
            DbConnection.getInstance().getConnection().rollback();
            throw new Exception("Greska prilikom brisanja angazovanja!");
        }
    }

    public List<Predmet> vratiPredmete() throws Exception {
        List<Predmet> predmeti = new ArrayList<>();
        try {
            String query = "SELECT * FROM predmet";
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Predmet predmet = new Predmet();
                predmet.setSifra(rs.getInt("sifra"));
                predmet.setBrESPB(rs.getInt("ESPB"));
                predmet.setNaziv(rs.getString("naziv"));
                predmet.setKod(rs.getString("kod"));
                predmeti.add(predmet);
            }
            DbConnection.getInstance().getConnection().commit();

        } catch (Exception e) {
            e.printStackTrace();
            DbConnection.getInstance().getConnection().rollback();
            throw new Exception("Greska prilikom dohvatanja profesora!");
        }
        return predmeti;
    }

}
