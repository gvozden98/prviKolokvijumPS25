/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import domen.Profesor;
import domen.Status;
import domen.Zvanje;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ognjen
 */
public class ProfesoriTableModel extends AbstractTableModel {

    private List<Profesor> profesori;

    String[] columns = {"id", "ime", "prezime", "zvanje", "status"};

    public ProfesoriTableModel(List<Profesor> profesori) {
        this.profesori = profesori;
    }

    @Override
    public int getRowCount() {
        if (profesori == null) {
            return 0;
        }
        return profesori.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profesor profesor = profesori.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                profesor.getId();
            case 1 ->
                profesor.getIme();
            case 2 ->
                profesor.getPrezime();
            case 3 ->
                profesor.getZvanje();
            case 4 ->
                profesor.getStatus();
            default ->
                null;
        };

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Profesor p = profesori.get(rowIndex);

        switch (columnIndex) {
            case 0 ->
                p.setId((Integer) aValue);
            case 1 ->
                p.setIme((String) aValue);
            case 2 ->
                p.setPrezime((String) aValue);
            case 3 -> {
                if (aValue instanceof Zvanje zvanje) {
                    p.setZvanje(zvanje);
                } else if (aValue instanceof String s) {
                    p.setZvanje(Zvanje.valueOf(s));
                }
            }
            case 4 ->
                p.setStatus((Status) aValue);
        }
        fireTableCellUpdated(rowIndex, columnIndex);

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Prva kolona (ID) nije editabilna
        return columnIndex != 0 && columnIndex != 4;
    }

    public List<Profesor> getProfesori() {
        return profesori;
    }

    public void setProfesori(List<Profesor> profesori) {
        this.profesori = profesori;
    }

}
