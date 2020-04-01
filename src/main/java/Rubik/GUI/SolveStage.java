/*
 * Fenster in dem die Loesung und die Visiualisierung dargestellt wird
 */
package Rubik.GUI;

import CubeModel.Cube;
import CubeModel.Variation.Colors;
import CubeModel.Variation.Faces;
import CubeModel.Variation.Permutations;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author florian
 */
public class SolveStage extends Stage{
    
    
    VBox Cubes = new VBox(10);
    //Cube InputCube, SolvedCube;
    Label Solution = new Label("LÖSUNGSWEG");
    Label Orbit = new Label("(0,0,0,0) bedeutet Lösbarer Cube");
    Label SolutionLength = new Label("Länge der Lösung");
    Button Back = new Button("Zur Eingabe");
    VBox Help = new VBox(10);
    HBox root = new HBox(10);
    
    
    public SolveStage(String rot){
        Permutations permCube = new Permutations(rot);
        
        
        permCube.Solve();
        Solution.setText("Lösungsweg:\n" + permCube.Solution);
        
        SolutionLength.setText("Länge des Lösungswegs: " + permCube.Solution.length());
        
        
        Cube input = new Cube();
        SubScene a = input.getShuffleCube(rot + permCube.Solution);
        SubScene b = input.getShuffleCube(rot);
        Label Solve = new Label("Gelöster Cube");
        Label Input = new Label("Eigegebener Cube");
        
        Orbit.setText("Orbit des Würfels\n(Permutation, Ecken, Kanten) \n" 
                + "(" + permCube.getOrbit()[0] + ", "
                + permCube.getOrbit()[1] + ", "
                + permCube.getOrbit()[2] + ")");
        
        Orbit.setPrefHeight(60);
        Orbit.setPrefWidth(300);
        
        Solution.setPrefHeight(300);
        Solution.setPrefWidth(300);
        Solution.setWrapText(true);
        
        
        a.heightProperty().bind(root.heightProperty().divide(2.35));
        a.widthProperty().bind(root.widthProperty().divide(2));
        
        b.heightProperty().bind(root.heightProperty().divide(2.35));
        b.widthProperty().bind(root.widthProperty().divide(2));
        
        
        Cubes.getChildren().addAll(Solve, a, Input, b);
        
        Help.getChildren().addAll(Back, Orbit, Solution, SolutionLength);
        
        root.getChildren().addAll(Cubes, Help);
        
        this.setScene(new Scene(root));
        this.show();
        
        Back.setOnAction((ActionEvent t) -> {
            new StartStage();
            SolveStage.this.close();
        });
    }
    
    public SolveStage(int[][] faces){
        
        Colors Solved = new Colors(faces);
        Faces toSolve = new Faces(faces);
        Permutations permCube = toSolve.toPermutations();
        
        // Orbit abspeichern
        int[] orbit = permCube.getOrbit();
        
        //Loesen des Cubes (der Orbit wird zum Loesen veraendert vgl. "Permutations Solve()")
        permCube.Solve();
        
        // Den eingegebenen Cube mit dem Loesungsweg aendern
        Solved.Rotate(permCube.toMoves());
        
        
        Cube input = new Cube();
        SubScene a = input.getSpecificCube(Solved.returnFaces());
        
        SubScene b = input.getSpecificCube(faces);
        Label Solve = new Label("'Gelöster' Cube");
        Label Input = new Label("Eingegebener Cube");
        
        
        Orbit.setText("Orbit des Würfels\n(Permutation, Ecken, Kanten) \n" 
                + "(" + orbit[0] + ", "
                + orbit[1] + ", "
                + orbit[2] + ")");
        
        Solution.setText("Lösungsweg:\n" + permCube.Solution);
        
        SolutionLength.setText("Länge des Lösungswegs: " + permCube.Solution.length());
        
        Orbit.setPrefHeight(60);
        Orbit.setPrefWidth(300);
        
        Solution.setPrefHeight(300);
        Solution.setPrefWidth(300);
        Solution.setWrapText(true);
        
        
        
        a.heightProperty().bind(root.heightProperty().divide(2.35));
        a.widthProperty().bind(root.widthProperty().divide(2));
        
        b.heightProperty().bind(root.heightProperty().divide(2.35));
        b.widthProperty().bind(root.widthProperty().divide(2));
        
        
        Cubes.getChildren().addAll(Solve, a, Input, b);
        
        Help.getChildren().addAll(Back, Orbit, Solution, SolutionLength);
        
        root.getChildren().addAll(Cubes, Help);
        
        this.setScene(new Scene(root));
        this.show();
        
        Back.setOnAction((ActionEvent t) -> {
            new StartStage();
            SolveStage.this.close();
        });
    }
    
    public SolveStage(){
        
        Cube hilf = new Cube();
        
        
        SubScene test = hilf.getNormCube();
        
        root.getChildren().add(test);
        
        this.setScene(new Scene(root));
        this.show();
        
        Back.setOnAction((ActionEvent t) -> {
            new StartStage();
            SolveStage.this.close();
        });
    }
    
}
