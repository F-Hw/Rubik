/*
 * Klasse bildet den Cube als zweidimensionales Array an
 * beschreibung siehe unten
 */
package CubeModel.Variation;

import java.util.Arrays;

/**
 *           U=2
 *         +++++++
 *         ++012++
 *         ++345++
 * F=0  R=1++678++L=4
 *++++++++++++++++++++
 *+012++012++012++012+
 *+345++345++345++345+
 *+678++678++678++678+
 *++++++++++++++++++++
 *         ++012++
 *         ++345++
 *         ++678++
 *         +++++++
 *           D=5
 * 
 * @author florian
 */
public class Faces {
    int[][] Faces = new int[9][6];
    
    // Colors nur in abhängigkeit der Position angegeben
    private static int F = 0;
    private static int R = 1;
    private static int U = 2;
    private static int B = 3;
    private static int L = 4;
    private static int D = 5;
    
    private final int RED     = 0;
    private final int GREEN   = 1;
    private final int BLUE    = 2;
    private final int YELLOW  = 3;
    private final int ORANGE  = 4;
    private final int WHITE   = 5;
    
    
    //                           Front, Right, Up,   Back,  Left,   Down
    private final int[] Colors = {BLUE, RED, YELLOW, GREEN, ORANGE, WHITE};
    
    private String[] EdgeNames = {"UF", "UR", "UB", "UL",
                                  "LF", "FR", "RB", "BL",
                                  "DF", "DR", "DB", "DL"};
    
    
    int[][] EdgesInput;
    int[][] CornersInput;
    
    // Norm Kantensteine mit Cubie Rotation {Seite 0, Seite 1}
    // Vgl. D. Joyner, S.220                Kantenstein Nr:
    private final int[][] normEdges = {{U, F},  // 1
                                 {U, R},  // 2
                                 {B, U},  // 3
                                 {L, U},  // 4
                                 {L, F},  // 5
                                 {F, R},  // 6
                                 {R, B},  // 7
                                 {B, L},  // 8
                                 {F, D},  // 9
                                 {R, D},  //10
                                 {D, B},  //11
                                 {D, L}}; //12
    
    /*
    final static int[][] normEdges = {{2, 0},  // 1
                                 {2, 1},  // 2
                                 {3, 2},  // 3
                                 {4, 2},  // 4
                                 {4, 0},  // 5
                                 {0, 1},  // 6
                                 {1, 3},  // 7
                                 {3, 4},  // 8
                                 {0, 5},  // 9
                                 {1, 5},  //10
                                 {5, 3},  //11
                                 {5, 4}}; //12
    */
    
    private String[] CornerNames = {"FRD", "BRD", "FRU", "BRU",
                                    "FLU", "BLU", "BDL", "FDL"};
    
   
    
    // Norm Ecken mit Cubie Rotation {Seite 0, Seite 1, Seite 2}
    // Vgl. D. Joyner, S.220                     Eckstein Nr:
    private final int[][] normCorners = {{D, R, F},  // 1
                                   {D, B, R},  // 2
                                   {U, F, R},  // 3
                                   {U, R, B},  // 4
                                   {U, L, F},  // 5
                                   {U, B, L},  // 6
                                   {D, L, B},  // 7
                                   {D, F, L}}; // 8
    
    
    /*
    final static int[][] normCorners = {{5, 1, 0},  // 1
                                   {5, 3, 1},  // 2
                                   {2, 0, 1},  // 3
                                   {2, 1, 3},  // 4
                                   {2, 4, 0},  // 5
                                   {2, 3, 4},  // 6
                                   {5, 4, 3},  // 7
                                   {5, 0, 4}}; // 8
    */
    
    //int[] ColDependent = {0,1,2,3,4,5}; //Farbe von F, R, U, B, L, D
    
    public Faces(int[][] Face){
        Faces = Face;
    }
    
    
    // Erstellt "Norm" Cube
    // Alle Flaechen geloest mit Orientierung Front = Blue, Up = Yellow, Right = Red
    public Faces(){
        
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 9; j++){
                Faces[j][i] = Colors[i];
            }
        }
    }
    
    
    //Funktion Ubersetzt die Face-Eingabe zu Permutationen
    // FEHLER NOCH ENTHALTEN!
    // Falls bei einem EckenStein nur zwei Seitenfarben getauscht werden, kann 
    // dieser nicht existieren. Das Programm erkennt das noch nicht!
    public Permutations toPermutations(){
        
        //Edges
        //------------
        //Permutations & Orientations
        setEdges();
        // Initialisiere Variablen mit "-1".
        // Falls der eingegebene Cube nicht "normal" ist, existiert noch ein "-1",
        // so wird ueberprueft, ob der Cube weitergeben werden kann.
        int[] EdgePermutation = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        int[] EdgeOrientation = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        
        
        for(int i = 0; i < 12; i++){
            
            
            
            for(int j = 0; j < 12; j++){
                
                // Steine Vergleichen: Falls Stein uebereinstimmt Permutation und
                // Orientierung Schreiben.
                if(CubieSearch(EdgesInput[j], normEdges[i])){
                    
                    // NormPositions Stein i = Stein an Pos. j
                    EdgePermutation[i] = j;
                    
                    // Ueberpruefen, welche Seite EdgesInput[j][0] am Normcubie i
                    // hatte, dies ist Orientierung des Steins j.
                    
                    //System.out.println("toPermutation: EdgesInput j= "+j+" " + Arrays.toString(EdgesInput[j]));
                    //System.out.println("toPermutation: normEdges j: " + Arrays.toString(normEdges[j]));
                    
                    for(int k = 0; k < 2; k++){
                        if(EdgesInput[j][0] == normEdges[i][k])
                            EdgeOrientation[j] = k;
                    }
                    //System.out.println("Input referenzsseite: " + EdgesInput[j][0]+"|normEdge 0: "+normEdges[i][0] + "|normEdge 1: " + normEdges[i][1]);
                    //System.out.println(Arrays.toString(EdgeOrientation));
                }
            }
        }
        //------------------------------------------------------------------------
        
        
        //Corners
        //------------
        //Permutations & Orientations
        setCorners();
        // Initialisieren mit "-1", s.o.
        int[] CornerPermutation = {-1,-1,-1,-1,-1,-1,-1,-1};
        int[] CornerOrientation = {-1,-1,-1,-1,-1,-1,-1,-1};

        for(int i = 0; i < 8; i++){
            
            for(int j = 0; j < 8; j++){
                
                if(CubieSearch(CornersInput[j], normCorners[i])){
                    // Der Selbe Cubie wie an Normcube Pos i ist Cubie j
                    CornerPermutation[i] = j;
                    //System.out.println("Faces toPermutations, Corners equal");
                    
                    // Ueberpruefen, welche Position die Seite U/D (= CornersInput[j][0])
                    // am Normcubie i (= normCorners[i]) hat. Dies gibt Rotation des Steins.
                    for(int k = 0; k < 3; k++){
                        if(CornersInput[j][0] == normCorners[i][k]) 
                            CornerOrientation[j] = k;
                    }
                }
            }
        }
        
        
        
        Permutations Per = new Permutations(EdgePermutation, CornerPermutation, EdgeOrientation, CornerOrientation);

        
        
        return Per;
    }
    // Schreibe den Input in ein Array mit {Cubie, Seite 0, Seite 1, Seite 2}
    // Vgl. D. Joyner, S.220
    private void setCorners(){
        //                                                                  Eckstein Nr:
        CornersInput = new int[][]{{Faces[6][5], Faces[6][1], Faces[8][0]},  // 1
                                   {Faces[0][5], Faces[6][3], Faces[8][1]},  // 2
                                   {Faces[0][2], Faces[2][0], Faces[0][1]},  // 3
                                   {Faces[6][2], Faces[2][1], Faces[0][3]},  // 4
                                   {Faces[2][2], Faces[2][4], Faces[0][0]},  // 5
                                   {Faces[8][2], Faces[2][3], Faces[0][4]},  // 6
                                   {Faces[2][5], Faces[6][4], Faces[8][3]},  // 7
                                   {Faces[8][5], Faces[6][0], Faces[8][4]}}; // 8
        
        int a;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 3; j++){
                a = CornersInput[i][j];
                switch(a){
                    case 0:{CornersInput[i][j] = 1;break;}
                    case 1:{CornersInput[i][j] = 3;break;}
                    case 2:{CornersInput[i][j] = 0;break;}
                    case 3:{CornersInput[i][j] = 2;break;}
                }
            }
        }
        
    }
    // Schreibe den Input in ein Array mit {Cubie, Seite 0, Seite 1}
    // Vgl. D. Joyner, S.220  
    private void setEdges(){
        //                                                    KantenStein Nr:
        EdgesInput = new int[][]{{Faces[1][2], Faces[1][0]},  // 1
                                 {Faces[3][2], Faces[1][1]},  // 2
                                 {Faces[1][3], Faces[7][2]},  // 3
                                 {Faces[1][4], Faces[5][2]},  // 4
                                 {Faces[5][4], Faces[3][0]},  // 5
                                 {Faces[5][0], Faces[3][1]},  // 6
                                 {Faces[5][1], Faces[3][3]},  // 7
                                 {Faces[5][3], Faces[3][4]},  // 8
                                 {Faces[7][0], Faces[7][5]},  // 9
                                 {Faces[7][1], Faces[3][5]},  //10
                                 {Faces[1][5], Faces[7][3]},  //11
                                 {Faces[5][5], Faces[7][4]}}; //12
        
        int a;
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 2; j++){
                a = EdgesInput[i][j];
                switch(a){
                    case 0:{EdgesInput[i][j] = 1;break;}
                    case 1:{EdgesInput[i][j] = 3;break;}
                    case 2:{EdgesInput[i][j] = 0;break;}
                    case 3:{EdgesInput[i][j] = 2;break;}
                }
            }
        }
        
    }
    
    
    private boolean CubieSearch(int[] arr1, int[] arr2){
        int[] a = Arrays.copyOf(arr1, arr1.length);
        int[] b = Arrays.copyOf(arr2, arr2.length);
        Arrays.sort(a);
        Arrays.sort(b);
        return (Arrays.equals(a, b));
        
    }
    
    
}
