package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Collection;

public class HelloController {

    @FXML
    private ListView<String> lb1;
    @FXML
    private ListView<String> lb2;
    @FXML
    private ListView<String> lb3;
    @FXML
    private Label burgerCount;





    public void View1(){
        lb1.setItems(FoodQueue.queue1Names);
    }
    public void View2(){
        lb2.setItems(FoodQueue.queue2Names);
    }
    public void View3(){
        lb3.setItems(FoodQueue.queue3Names);
    }
    public void View4(){

        burgerCount.setText(String.valueOf(FoodQueue.burgerStock));
    }


}