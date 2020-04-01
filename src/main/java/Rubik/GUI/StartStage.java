/*
 * Fenster mit dem das Program gestartet wird, hier sind Eingaben möglich
 */
package Rubik.GUI;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author florian
 */
public class StartStage extends Stage{
    
    Label Introduction = new Label();
    ToolBar Norm = new ToolBar(new Button("F"), new Button("R"), new Button("U"),
                               new Button("B"), new Button("L"), new Button("D"));
    ToolBar Inverse = new ToolBar(new Button("f"), new Button("r"), new Button("u"),
                                  new Button("b"), new Button("l"), new Button("d"));
    Label ButtonInput = new Label();
    Button ButtonSolve = new Button("Lösen");
    TextArea TextInput = new TextArea();
    Button TextSolve = new Button("Lösen");
    Button FaceInput = new Button("Seiteneingabe Öffnen");
    HBox root = new HBox(10);
    
    static String buttoninput = new String();
    
    
    
    public StartStage(){
        
        
        
        Introduction.setText("Dies ist ein Rubik's Cube Solver für den 3x3x3 RubiksCube.\n"
                + "Die Eingabe eines neuen Würfels ist auf drei verschiedene Arten "
                + "möglich: \n"
                + "1. Durch das Drücken der Knöpfe (Großbuchstabe = Drehung im UZS, Kleinbuchstabe gegen UZS)\n"
                + "2. Durch Eingeben der Rotationen per Tastatur\n"
                + "3. Durch das Eingeben der einzelnen Seiten\n"
                + "\n"
                + "'Lösen' öffnet ein neues Fenster in dem der Rubik's Cube in eingegebener und gelöster Form zu sehen ist. "
                + "Dort wird auch die errechnete Lösung und der Orbit des Cubes angegeben. \n"
                + "\n"
                + "'Seiteneingabe Öffnen' öffnet ein neues Fenster, in dem alle "
                + "Seitenflächen einzeln eingelesen werden können.");
        
        Introduction.setWrapText(true);
        Introduction.setPrefHeight(400);
        Introduction.setPrefWidth(250);
        
        
        
        //ButtonInput.setPrefWidth(200);
        HBox Button = new HBox(5);
        ButtonInput.setPrefWidth(180);
        Button.getChildren().addAll(ButtonInput, ButtonSolve);
        
        
        HBox Text = new HBox(5);
        TextInput.setPrefHeight(60);
        TextInput.setPrefWidth(180);
        TextInput.setWrapText(true);
        Text.getChildren().addAll(TextInput, TextSolve);
        
        VBox Controls = new VBox(5);
        Controls.getChildren().addAll(Norm, Inverse, Button, Text, FaceInput);
        
        
        root.getChildren().addAll(Introduction, Controls);
        this.setScene(new Scene(root));
        this.show();
        
        
        
        
        
        
        Norm.getItems().stream().filter(n -> (n instanceof Button))
                .forEach(n -> ((Button)n).addEventHandler(ActionEvent.ACTION, (e) ->{
            ButtonInput.setText(ButtonInput.getText() + ((Button)n).getText());
        }));
        
        Inverse.getItems().stream().filter(n -> (n instanceof Button))
                .forEach(n -> ((Button)n).addEventHandler(ActionEvent.ACTION, (e) ->{
            ButtonInput.setText(ButtonInput.getText() + ((Button)n).getText());
        }));
        
        ButtonSolve.addEventHandler(ActionEvent.ACTION, (e) ->{
            new SolveStage(ButtonInput.getText());
            StartStage.this.close();
        });
        
        TextSolve.addEventHandler(ActionEvent.ACTION, (e) ->{
            new SolveStage(TextInput.getText());
            StartStage.this.close();
        });
        
        FaceInput.addEventHandler(ActionEvent.ACTION, (e) ->{
            new FaceInputStage();
            StartStage.this.close();
        });
    }
    
}


