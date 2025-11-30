/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.Asistent;
import domen.Predmet;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ognjen
 */
public class AsistentiTableModel extends AbstractTableModel {

    private List<Asistent> asistenti;

    private String[] columns = {"Sifra", "Ime", "Prezime", "Titula", "Predmet"};

    public AsistentiTableModel(List<Asistent> asistenti) {
        this.asistenti = asistenti;
    }

    public AsistentiTableModel() {
    }

    @Override
    public int getRowCount() {
        if (asistenti == null) {
            return 0;
        }
        return asistenti.size();
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
        Asistent asistent = asistenti.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                asistent.getSifra();
            case 1 ->
                asistent.getIme();
            case 2 ->
                asistent.getPrezime();
            case 3 ->
                asistent.getTitula();
            case 4 ->
                asistent.getPredmet();
            default ->
                null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Asistent asistent = asistenti.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {

                asistent.setSifra(Integer.valueOf(aValue.toString()));
            }
            case 1 ->
                asistent.setIme((String) aValue);
            case 2 ->
                asistent.setPrezime((String) aValue);
            case 3 ->
                asistent.setTitula((String) aValue);
            case 4 ->
                asistent.setPredmet((Predmet) aValue);
        };
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 ->
                Integer.class;       // ili Long.class, kako ti je u Asistent
            case 1, 2, 3 ->
                String.class;
            case 4 ->
                domen.Predmet.class; // VAŽNO: JTable sada zna da je ovo Predmet
            default ->
                Object.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void addEmptyRow() {
        Asistent prazan = new Asistent(0, "", "", null, "");
        int newIndex = asistenti.size();
        asistenti.add(prazan);
        fireTableRowsInserted(newIndex, newIndex);
    }

    public void removeRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= asistenti.size()) {
            return;  // ništa ne radi ako je loš index
        }
        asistenti.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void clearTable() {
        asistenti.clear();
        fireTableDataChanged();
    }

    public Asistent getAsistentAt(int row) {
        return asistenti.get(row);
    }

    public List<Asistent> getAsistenti() {
        return asistenti;
    }

    public void setAsistenti(List<Asistent> asistenti) {
        this.asistenti = asistenti;
    }

}
