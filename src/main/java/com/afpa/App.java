package com.afpa;

import com.afpa.Model.LigneFormation;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class App extends Application
{

    private Excell excell;
    @Override
    public void start(Stage primaryStage) throws Exception {

        excell = new Excell("C:\\Users\\Maxime\\IdeaProjects\\TestPOI\\src\\main\\java\\com\\afpa\\Planning2019.xlsx");
        excell.creationObjet();

        TableView<LigneFormation> table = new TableView<>();

        ArrayList<TableColumn> tableColumnArrayList = new ArrayList<>();
        for(String titre : excell.getTitreColonne())
        {
            tableColumnArrayList.add(new TableColumn<LigneFormation,String>(titre));
            //System.out.println(titre);
        }

        int cpt = 0;
        for(TableColumn tableColumn : tableColumnArrayList)
        {
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(excell.getTitreColonne().get(cpt++)));
            table.getColumns().add(tableColumn);
        }

        table.setItems(excell.getListFormation());

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(excell.getChoiceboxList("Region"));
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            }
        });
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.getChildren().addAll(choiceBox,table);
        Stage window = primaryStage;
        window.setScene(new Scene(vBox,500,500));
        window.show();
    }

    /*public String getPr() throws IOException
    {

        ArrayList<Integer> arrayList = excell.compterPr();
        String sRet = "";
        for(Integer integer : arrayList)
        {
            sRet+=integer+"\n";
        }
        return sRet;
    }*/
}
