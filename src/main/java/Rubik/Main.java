/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rubik;

import javafx.application.Application;
import javafx.stage.Stage;

import Rubik.GUI.StartStage;


/**
 *
 * @author florian
 */
public class Main extends Application{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        new StartStage();
        
    }
    
}

