/*
 * Fenster für die spezifische Eingabe aller Seiten
 */
package Rubik.GUI;


import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author florian
 */
public class FaceInputStage extends Stage{
    Button Solve = new Button("Seiteneingabe Lösen");
    Label Description = new Label("Gebe hier für jede Seitenfläche Y;B;G;R;O;W ein");
    VBox root = new VBox(5);
    String Cols = "BRYGOW"; //Cols für die Seitenfläche, damit Blue = Front, Yellow = Top, usw.
    String ColsTranslate = "RGBYOW"; //Zahlen für die Übersetzung zum Modell:
                                     // Front = 0, Right = 1, Up = 2, Back = 3
                                     // Left = 4, Down = 5
    
    
    private class InputPane extends GridPane{
        TextField[] a = new TextField[9];
        
        private InputPane(int side){
            for (int i = 0; i < 9; i++){
                
                    a[i] = new TextField();
                    GridPane.setConstraints(a[i], (i%3), (i/3));
                    //this.add(a[i], (i%3), (i/3));
                    a[i].setText(Character.toString(Cols.charAt(side)));
                    a[i].setAlignment(Pos.CENTER);
            }
            
            this.getChildren().addAll(a);
        }
    }
    InputPane[] Faces = new InputPane[6];
    
    
    
    public FaceInputStage(){
        
        GridPane Input = new GridPane();
        
        for(int i = 0; i < 6; i++){
            Faces[i] = new InputPane(i);
            Input.add(Faces[i], i%3, i/3);
        }
        
        Input.setHgap(10);
        Input.setVgap(10);
        
        Input.setPrefWidth(500);
        Input.setPrefHeight(200);
        
        HBox Up = new HBox(10, Solve, Description);
        
        
        
        root.getChildren().addAll(Up, Input);
        
        
        
        this.setScene(new Scene(root));
        this.show();
        
        Solve.setOnAction((ActionEvent t) -> {
            int[][] par = new int[9][6];
            
            //Gibt für jede Seite die Richtige Zahl an das Modell weiter.
            //Für falsche oder fehlende Eingaben wird die Seite Grau.
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 9; j++){
                    if(ColsTranslate.startsWith(Faces[i].a[j].getText()) && ColsTranslate.substring(1).startsWith(Faces[i].a[j].getText())){
                        par[j][i] = 6;
                    } else {
                        if(!ColsTranslate.contains(Faces[i].a[j].getText())){
                            par[j][i] = 6;
                        } else {
                            par[j][i] = ColsTranslate.indexOf(Faces[i].a[j].getText());
                        }
                    }
                }
            }
            
            new SolveStage(par);
            FaceInputStage.this.close();
        });
    }
    
    
}
