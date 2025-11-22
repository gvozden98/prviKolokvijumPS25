/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Ognjen
 */
public class Angazovanje implements Serializable {

    private int idProfesora;
    private int idPredmeta;

    public Angazovanje() {
    }

    public Angazovanje(int idProfesora, int idPredmeta) {
        this.idProfesora = idProfesora;
        this.idPredmeta = idPredmeta;
    }

    public int getIdPredmeta() {
        return idPredmeta;
    }

    public void setIdPredmeta(int idPredmeta) {
        this.idPredmeta = idPredmeta;
    }

    public int getIdProfesora() {
        return idProfesora;
    }

    public void setIdProfesora(int idProfesora) {
        this.idProfesora = idProfesora;
    }
}
