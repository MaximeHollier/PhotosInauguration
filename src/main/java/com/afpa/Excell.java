package com.afpa;

import com.afpa.Model.LigneFormation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Excell {

    private String fileName;
    private Workbook workbook;
    private ObservableList<LigneFormation> listFormation;
    private ArrayList<String> titreColonne;

    public Excell(String url) throws IOException
    {
        this.listFormation = FXCollections.observableArrayList();
        this.fileName = url;
        chargerFichier();
        titreColonne = new ArrayList<>();
    }

    private void chargerFichier()throws IOException {
        this.workbook = WorkbookFactory.create(new File(this.fileName));
    }

    /*public ArrayList<Integer> compterPr()
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Sheet sheet = this.workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        Integer cpt ;
        while (rowIterator.hasNext())
        {
            cpt=0;
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            while(cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                String cellValue = new DataFormatter().formatCellValue(cell);
                if(cellValue.equals("PR")){cpt++;}

            }
            arrayList.add(cpt);

        }
        return arrayList;
    }*/

    public void creationObjet()
    {
        Sheet sheet = this.workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();
            String region = "";
            String lib="";

            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = new DataFormatter().formatCellValue(cell);

                //Ajout des titres de colonnes dans une liste
                if (cell.getRowIndex()==11 && (cell.getColumnIndex()==0||cell.getColumnIndex()==7)) {
                    titreColonne.add(cellValue);
                }
                if(cell.getRowIndex()>12 && cell.getColumnIndex()==0)
                {
                    region = cellValue;
                }
                else if(cell.getRowIndex()>12 && cell.getColumnIndex()==7)
                {
                    lib=cellValue;
                }

            }
            if(!region.isEmpty()&&!lib.isEmpty())
                this.listFormation.add(new LigneFormation(region,lib));

        }
    }
    public ObservableList<LigneFormation> getListFormation() {
        return listFormation;
    }

    public ArrayList<String> getTitreColonne() {
        return titreColonne;
    }

    public ObservableList<String> getChoiceboxList(String type)
    {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for(LigneFormation ligneFormation : this.listFormation)
        {
            if(type.equals("Region")) {
                if (!isInList(ligneFormation.getRégion(), observableList)) {
                    observableList.add(ligneFormation.getRégion());
                }
            }
            else if(type.equals("Libelle"))
            {
                if (!isInList(ligneFormation.getLibellé(), observableList)) {
                    observableList.add(ligneFormation.getLibellé());
                }
            }

        }
        return observableList;
    }
    private boolean isInList(String s, ObservableList<String> list)
    {
        for(String string : list)
        {
            if(s.equals(string))
                return true;
        }
        return false;
    }

    public ObservableList<LigneFormation> getListFormationFiltreByRegion(String region)
    {
        ObservableList<LigneFormation> observableList = FXCollections.observableArrayList();
        for(LigneFormation ligneFormation : this.listFormation)
        {
            if(ligneFormation.getRégion().equals(region))
                observableList.add(ligneFormation);
        }
        return observableList;
    }
}
