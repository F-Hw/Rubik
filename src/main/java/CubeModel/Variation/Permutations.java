/*
 * Klasse der Permutationen
 * hier wird der Cube durch vier Vektoren definiert
 * Corner Permutations = (0, ... , 7)
 * Edge Permutations = (0, ... , 12)
 * Corner Rotations = (0, 1, 2)^8
 * Edge Rotations = (0, 1)^12
 */
package CubeModel.Variation;



import java.util.Arrays;

/**
 *
 * @author florian
 */
public class Permutations {
    int[] Edges = new int[12]; //12
    int[] Corners = new int[8]; //8
    int[] EdgeRotation = new int[12]; //12, {0, 1}
    int[] CornerRotation = new int[8]; //8, {0, 1, 2}
    
    
    public String Solution;
    
    
    private final String Orientations = "FRUBLD";
    //Die Grundzuege definiert
    //                                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11}
    private final int[] Fe = new int[]{ 4, 1, 2, 3, 8, 0, 6, 7, 5, 9,10,11};
    private final int[] Re = new int[]{ 0, 5, 2, 3, 4, 9, 1, 7, 8, 6,10,11};
    private final int[] Ue = new int[]{ 1, 2, 3, 0, 4, 5, 6, 7, 8, 9,10,11};
    private final int[] Be = new int[]{ 0, 1, 6, 3, 4, 5,10, 2, 8, 9, 7,11};
    private final int[] Le = new int[]{ 0, 1, 2, 7, 3, 5, 6,11, 8, 9,10, 4};
    private final int[] De = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7,11, 8, 9,10};
    
    private final int[] Feo = new int[]{0,0,0,0,1,1,0,0,0,0,0,0};
    private final int[] Reo = new int[]{0,0,0,0,0,1,1,0,0,0,0,0};
    private final int[] Ueo = new int[]{0,1,0,1,0,0,0,0,0,0,0,0};
    private final int[] Beo = new int[]{0,0,1,0,0,0,0,0,0,0,1,0};
    private final int[] Leo = new int[]{0,0,0,1,0,0,0,0,0,0,0,1};
    private final int[] Deo = new int[]{0,0,0,0,0,0,0,0,1,0,1,0};
    
    private final int[][][] EdgeMoves = new int[][][]{{ Fe,  Re,  Ue,  Be,  Le , De},
                                                      {Feo, Reo, Ueo, Beo, Leo, Deo}};
    
    
    //                                {0,1,2,3,4,5,6,7}
    private final int[] Fc = new int[]{2,1,4,3,7,5,6,0};
    private final int[] Rc = new int[]{1,3,0,2,4,5,6,7};
    private final int[] Uc = new int[]{0,1,3,5,2,4,6,7};
    private final int[] Bc = new int[]{0,6,2,1,4,3,5,7};
    private final int[] Lc = new int[]{0,1,2,3,5,6,7,4};
    private final int[] Dc = new int[]{7,0,2,3,4,5,1,6};
    
    private final int[] Fco = new int[]{2,0,1,0,2,0,0,1};
    private final int[] Rco = new int[]{1,2,2,1,0,0,0,0};
    private final int[] Uco = new int[]{0,0,0,0,0,0,0,0};
    private final int[] Bco = new int[]{0,1,0,2,0,1,2,0};
    private final int[] Lco = new int[]{0,0,0,0,1,2,1,2};
    private final int[] Dco = new int[]{0,0,0,0,0,0,0,0};
    
    private final int [][][] CornerMoves = new int[][][]{{ Fc,  Rc,  Uc,  Bc,  Lc,  Dc},
                                                         {Fco, Rco, Uco, Bco, Lco, Dco}};
    
    public Permutations(){
        setNormCube();
    }
    
    public Permutations(int[] EdgePermutation, int[] CornerPermutation, int[] EdgeRot, int[] CornerRot){
        Edges = EdgePermutation;
        Corners = CornerPermutation;
        EdgeRotation = EdgeRot;
        CornerRotation = CornerRot;
        
        boolean CubeOk = true;
        
        for(int i = 0; i < Edges.length; i++){
            if(Edges[i] == -1){
                //System.out.println("Test");
                CubeOk = false; // platz für Errorhandler
            }
        }
        
        for(int i = 0; i < Corners.length; i++){
            if(Corners[i] == -1){
                CubeOk = false;
            }
        }
        
        if(!CubeOk) setNormCube();
        
    }
    
    public Permutations(String moves){
        //System.out.println("INITIALISIERUNG");
        setNormCube();
        //System.out.println("Initialisierung_Rotation:");
        Rotate(moves);
        
    }
    
    public Permutations(Faces faces){
        
        faces.toPermutations();
        
        boolean CubeOk = true;
        
        // Ueberprueft die Definition der Steine, falls ein Stein nicht existieren
        // kann (z.B. zwei gleiche Farben auf einem Stein), wird ein Normaler Cube generiert
        if(Edges.toString().contains("-1") || Corners.toString().contains("-1")){
            CubeOk = false; //Möglichkeit für einen Error
            setNormCube();
        }
        
    }
    
    private void setNormCube(){
        Edges = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Corners = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
        EdgeRotation = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        CornerRotation = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
    }
    
    // Gibt Orbit an:
    // getOrbit[0] = Permutation, [1] = Eckrotation, [2] = Kantenrotation
    public int[] getOrbit(){
        int[] Orbit;
        
        int sgnC = 0;
        int sgnE = 0;
        int rotC = 0;
        int rotE = 0;
        
        //Signum nach
        // https://github.com/wrandelshofer/CubeTwister/blob/master/src/main/java/org.kociemba.twophase/org/kociemba/twophase/CubieCube.java
        
        //System.out.println(Arrays.toString(Corners) + Arrays.toString(Edges));
        
        for(int j = 7; j >= 0; j--){
            for(int i = j - 1; i >= 0; i--){
                if(Corners[i] > Corners[j]) sgnC += 1;
            }
        }
        sgnC = (sgnC % 2);
        
        //System.out.println("Funktion: getOrbit - sgnC: " + sgnC);
        
        for(int j = 11; j >= 0; j--){
            for(int i = j - 1; i >= 0; i--){
                if(Edges[i] > Edges[j]) sgnE += 1;
            }
        }
        sgnE = (sgnE % 2);
        
        //System.out.println("Funktion: getOrbit - sgnE: " + sgnE);
        
        int parity = -1;
        
        if(sgnE == sgnC) parity = 1;
        
        //Summe der Eckrotationen mod 3
        for(int i = 0; i < 8; i++){
            rotC += CornerRotation[i];
        }
        rotC = rotC % 3;
        
        //Summe der Kantendrehungen mod 2
        for(int i = 0; i < 12; i++){
            rotE = rotE + EdgeRotation[i];
        }
        rotE = rotE % 2;
        
        
        Orbit = new int[]{parity, rotC, rotE};
        
        return Orbit;
    }
    
    //Funktion aendert einen "falsch zusammengesetzten" cube in einen Richtigen,
    // somit kann der Loesungsalgorithmus mit dem Wuerfel arbeiten
    // Die Steine, welche veraendert werden sind: BDL, FDL und DL
    private void repairCube(int[] Orbit){
        int[] CopyCorners = Corners;
        int[] CopyEdges = Edges;
        int[] CopyCornerRotation = CornerRotation;
        int[] CopyEdgeRotation = EdgeRotation;
        
        //Falls die Permutationen nicht im "normalen" Orbit sind, reicht es einen
        // Cubie zu tauschen.
        if(Orbit[0] != 1){
            //System.out.println("repairCube - Permutationsfehler");
            
            int hilf = Corners[7];
            int hilf1 = Corners[6];
            CopyCorners[6] = hilf;
            CopyCorners[7] = hilf1;
        }
        
        //Falls die Summe der Orientierungen der Ecken 1 ist, muss ein Stein noch
        // "zweimal" im Uhrzeigersinn gedreht werden um im "normalen" Orbit zu sein
        if(Orbit[1] == 1){
            //CopyEdgeRotation = EdgeRotation;
            CopyCornerRotation[7] = (CopyCornerRotation[7] + 2) % 3;
        }
        
        //Falls die Summe der Orientierungen der Ecken 1 ist, muss ein Stein noch
        // "einmal" im Uhrzeigersinn gedreht werden um im normalen Orbit zu sein
        if(Orbit[1] == 2){
            //CopyEdgeRotation = EdgeRotation;
            CopyCornerRotation[7] = (CopyCornerRotation[7] + 1) % 3;
        }
        
        //Falls die Summe der Orientierungen der Kanten 1 ist, muss ein Stein noch
        // gedreht werden um im "normalen" Orbit zu sein
        if(Orbit[2] == 1){
            //CopyCornerRotation = CornerRotation;
            CopyEdgeRotation[11] = (CopyEdgeRotation[11] + 1) % 2;
        }
        
        Save(CopyCorners, CopyEdges, CopyCornerRotation, CopyEdgeRotation);
    }
    
    // Fuehrt Rotationen aus
    // Mittels den Norm Rotationen, die definiert wurden
    
    private void Rotate(String Rotations){
        
        // Zuerst alle Drehungen in die Grundzuege umwandeln
        Rotations = Rotations.replace("f", "FFF");
        Rotations = Rotations.replace("r", "RRR");
        Rotations = Rotations.replace("u", "UUU");
        Rotations = Rotations.replace("b", "BBB");
        Rotations = Rotations.replace("l", "LLL");
        Rotations = Rotations.replace("d", "DDD");
        
        int n;
        for(int i = 0; i < Rotations.length(); i++){
            int[] CopyCorners = new int[8];
            int[] CopyEdges = new int[12];
            int[] CopyCornerRotation = new int[8];
            int[] CopyEdgeRotation = new int[12];
            n = Orientations.indexOf(Rotations.charAt(i));
            
            
            for(int j = 0; j < 8; j++){
                
                CopyCorners[j] = Corners[CornerMoves[0][n][j]];
                CopyCornerRotation[j] = (CornerRotation[CornerMoves[0][n][j]] + CornerMoves[1][n][j]) % 3;
            }
            
            for(int j = 0; j < 12; j++){
                
                CopyEdges[j] = Edges[EdgeMoves[0][n][j]];
                CopyEdgeRotation[j] = (EdgeRotation[EdgeMoves[0][n][j]] + EdgeMoves[1][n][j]) % 2;
            }
            
            Save(CopyCorners, CopyEdges, CopyCornerRotation, CopyEdgeRotation);
            
        }
        
        
    }
    
    private void Save(int[] CopyCorners, int[] CopyEdges, int[] CopyCornerRotation, int[] CopyEdgeRotation){
        Edges = CopyEdges;
        Corners = CopyCorners;
        EdgeRotation = CopyEdgeRotation;
        CornerRotation = CopyCornerRotation;
    }
    
    
    public String toMoves(){
        String Moves;
        
        Moves = Solution;
        
        return Moves;
    }
    
    // Noch zu implementieren:
    /*
    void toFaces(){
        
        
    }
    
    void Shuffle(){
        
    }
    
    void realShuffle(){
        
    }*/
    
    // Gibt den Output kuerzer an
    void setSolution(){
        
        Solution = Solution.replace("FFF", "f");
        Solution = Solution.replace("RRR", "r");
        Solution = Solution.replace("UUU", "u");
        Solution = Solution.replace("BBB", "b");
        Solution = Solution.replace("LLL", "l");
        Solution = Solution.replace("DDD", "d");
        
        Solution = Solution.replace("FFFF", "");
        Solution = Solution.replace("RRRR", "");
        Solution = Solution.replace("UUUU", "");
        Solution = Solution.replace("BBBB", "");
        Solution = Solution.replace("LLLL", "");
        Solution = Solution.replace("DDDD", "");
        
        Solution = Solution.replace("ffFF", "");
        Solution = Solution.replace("rrRR", "");
        Solution = Solution.replace("uuUU", "");
        Solution = Solution.replace("bbBB", "");
        Solution = Solution.replace("llLL", "");
        Solution = Solution.replace("ddDD", "");
        
        Solution = Solution.replace("FFff", "");
        Solution = Solution.replace("RRrr", "");
        Solution = Solution.replace("UUuu", "");
        Solution = Solution.replace("BBbb", "");
        Solution = Solution.replace("LLll", "");
        Solution = Solution.replace("DDdd", "");
    }
    
    // Loest den gesamten Cube
    public void Solve(){
        //Funktion repariert den Cube so, dass der Loesungsalgorithmus funktioniert
        // die Rueckgabe fuer das Modell findet in Form der Loesungszugfolge statt,
        // deshalb ist der "Falsche" Stein im Modell dann zu sehen.
        repairCube(getOrbit());
        
        BottomLayer();
        
        MiddleLayer();
        
        TopLayer();
        
        setSolution();
        
        
    }
    
    
    // Moves: F = 0; R = 1; U = 2; B = 3; L = 4; D = 5
    void BottomLayer(){
        Solution = "";
        // Steine von Interesse: Edge 8,9,10,11
        int[] E_1 = {8,11, 10, 9};
        for(int i = 0; i < 4; i++){
            String hilf = SolveEdge_1(E_1[i]);
            //Rotate(hilf); // Loest FD
            //System.out.println(hilf + "D");
            
            
            Rotate("D"); // Naechster Stein nach FD
            
            Solution += hilf + "D";
        }
        
        //Steine von Interesse: Corner 0,1,6,7
        int[] C_1 = {0,7,6,1};
        for(int i = 0; i < 4; i++){
            String hilf = SolveCorner_1(C_1[i]);
            
            Rotate("D"); // Wird eins weiter gedreht, um den naechsten Stein zu loesen
            
            Solution += hilf + "D";
        }
        
        // Ueberschuessige Zuege Aussortieren
        // FFF -> f, usw.
    }
    
    void MiddleLayer(){
        //Steine von Interesse: Edge 4,5,6,7
        // Move nimmt Steine von: Edge 3,0,1,2 
        int[] Edge = {5, 6, 7, 4}; //FR, RB, LB, FL
        final int[] fromEdge = {0, 1, 2, 3}; //UF, UR, UB, UL
        int[] positions = {-1,-1,-1,-1};
        int[] solveRotations = {1, 1, 0, 0}; // Orientierungen der Steine vor dem Einsetzen in den Mittleren Layer
        
        
        String Moves = "";
        
        
        for(int i = 0; i < 4; i++){
            int a;
            
            int hilf = 0;
            
            //Ueberpruefen in welcher Position die Zielsteine liegen
            for(int j = 0; j < 4; j++){
                a = findEdge(Edge[j]);
                positions[j] = a;
            }
            
            // Ueberpruefen, von welcher Seite der Zielstein geholt werden Muss
            // 0 = F, 1 = R, 2 = B, 3 = L
            for(int j = 0; j < 4; j++){
                if(Edge[j] == positions[i]) hilf = j;
            }
            
            //System.out.println("Durchlauf " + i + " vor Schleifen " + positions[0] + positions[1] + positions[2] + positions[3]);
            
            //System.out.println("Rotation des Steins: " + EdgeRotation[Edge[i]]);
            // Zielstein aus dem Layer 2 holen, falls dieser dort ist:
            if(positions[i] > 3){
                if(positions[i] != Edge[i] || EdgeRotation[Edge[i]] == 1){
                    Moves += translateMoves("URUUURRRUUUFFFUF", hilf);
                    Rotate(translateMoves("URUUURRRUUUFFFUF", hilf));
                    
                    //System.out.println(hilf + ": Angewandte Moves: " + translateMoves("URUUURRRUUUFFFUF", hilf));
                }
                
            }
            
            //System.out.println("Pos. vor der Rotation oben: "+ findEdge(Edge[i]));
            
            // Zielstein in die richtige Position zum Einsetzen in den Layer 2 drehen
            a = findEdge(Edge[i]);
            //System.out.println(a != Edge[i]);
            if(a != Edge[i]){
                while(a != fromEdge[i]){
                    
                    Moves += "U"; Rotate("U");
                    a = findEdge(Edge[i]);
                }
                
                //System.out.println("Pos. nach der Rotation oben: "+ findEdge(Edge[i]));
                
                //Zielstein in den Layer 2 einsetzen. Zug abhaengig von der Orientierung
                if(EdgeRotation[fromEdge[i]] == solveRotations[i]){
                    Moves += translateMoves("URUUURRRUUUFFFUF", i); Rotate(translateMoves("URUUURRRUUUFFFUF", i));
                } else {
                    Moves += translateMoves("UUFFFUFURUUURRR", i); Rotate(translateMoves("UUFFFUFURUUURRR", i));
                }
                
                //System.out.println("Pos. nach dem Einsetzen: "+ findEdge(Edge[i]));
                //System.out.println();
            }
            
        }
        
        Solution += Moves;
        
    }
    
    void TopLayer(){
        //String SolveEdgeRotation = TopEdgesRotation();
        //System.out.println(Arrays.toString(Corners));
        Solution += TopCornerPermutation();
        //System.out.println(Arrays.toString(Corners));
        Solution += TopCornerRotation();
        Solution += TopEdgePermutation();
        Solution += TopEdgeRotation();
        
    }
    
    // Löst die Ecken positionierung des oberen Layers
    String TopCornerPermutation(){
        String Moves = "";
        
        String M1 = "LLLUUULFUFFFLLLULUU"; //Tauscht FRU mit FLU
        String M2 = "ULLLUUULFUFFFLLLULU"; //Tauscht FRU mit BRU
        
        int[] Corner = new int[] {2,3,5,4}; //FRU, BRU, BLU, FLU
        
        
        
        for(int i = 0; i < 4; i++){
            
            int a = findCorner(Corner[i]);
            
            if(i == 0){
                //System.out.println("test");
                if(a == Corner[i + 1]) {Moves += "U"; Rotate("U");}
                if(a == Corner[i + 2]) {Moves += "UU"; Rotate("UU");}
                if(a == Corner[i + 3]) {Moves += "UUU"; Rotate("UUU");}
            }else{
                if(a == Corner[(i + 1)%4]) {Moves+= translateMoves(M2, i); Rotate(translateMoves(M2,i));}
                if(a == Corner[(i + 2)%4]) {Moves+= translateMoves(M2, (i + 1)%4) + translateMoves(M2, i); Rotate(translateMoves(M2, (i + 1)%4) + translateMoves(M2,i));}
                
            }
        }
        return Moves;
    }
    
    // Löst die Ecken Orientierung des oberen Layers
    String TopCornerRotation(){
        String Moves = "";
        String M = "RRRDDDRDRRRDDDRD";
        
        //So lange den Will Smith Move, bis alle Ecken Stimmen
        while(!Arrays.equals(CornerRotation, new int[]{0,0,0,0,0,0,0,0}) || !Arrays.equals(Corners, new int[]{0,1,2,3,4,5,6,7})){
            //System.out.println("while1 - TopCornerRot");
            while(CornerRotation[2] != 0){
                //System.out.println("while2 - TopCornerRot");
                Moves += M;
                Rotate(M);
            }
            
            Moves += "U";
            Rotate("U");
        }
        
        return Moves;
    }
    
    // Loest die Positionierung der oberen Kanten
    String TopEdgePermutation(){
        //Edges Interessant 0,1,2,3
        String Moves = "";
        
        String M = "LRRRFFFLLLRUULRRRFFFLLLR";
        
        int hilf = -1;
        
        for(int i = 0; i < 4; i++){
            if(findEdge(i) == i) hilf = i;
        }
        
        if(hilf == -1){
            Moves += M;
            Rotate(M);
        }
        
        for(int i = 0; i < 4; i++){
            if(findEdge(i) == i) hilf = i;
        }
        
        while(!Arrays.equals(Edges, new int[]{0,1,2,3,4,5,6,7,8,9,10,11})){
            //System.out.println("while1 - TopEdgePer");
            Moves += translateMoves(M, hilf);
            Rotate(translateMoves(M, hilf));
        }
        
        
        return Moves;
    }
    
    //Loest alle Rotationen der oberen Kanten
    String TopEdgeRotation(){
        String Moves = "";
        
        String M1 = "RRRUUUDBBUUDDFFFUUFUUDDBBUDDDRUU"; //Loest zwei gegenueberliegende Kanten
        String M2 = "FFFLLLRRRUUUDBBUUDDFFFUUFUUDDBBUDDDRUULF"; //Loest zwei anliegende Kanten
        
        int[] hilf = Arrays.copyOf(EdgeRotation, 4);
        
        if(Arrays.equals(hilf, new int[]{1,1,1,1})){
            Moves += M1;
            Rotate(M1);
        }
        
        hilf = Arrays.copyOf(EdgeRotation, 4);
        
        if(Arrays.equals(hilf, new int[]{0,0,1,1})){
            Moves += translateMoves(M2, 2);
            Rotate(translateMoves(M2, 2));
        }//UB u. UL
        if(Arrays.equals(hilf, new int[]{0,1,1,0})){
            Moves += translateMoves(M2, 1);
            Rotate(translateMoves(M2, 1));
        }//UR u. UB
        if(Arrays.equals(hilf, new int[]{1,0,0,1})){
            Moves += translateMoves(M2, 3);
            Rotate(translateMoves(M2, 3));
        }//UF u. UL
        if(Arrays.equals(hilf, new int[]{1,1,0,0})){
            Moves += M2;
            Rotate(M2);
        }//UF u. UR
        
        if(Arrays.equals(hilf, new int[]{1,0,1,0})){
            Moves += translateMoves(M1, 1);
            Rotate(translateMoves(M1, 1));
        }//UF u. UB
        if(Arrays.equals(hilf, new int[]{0,1,0,1})){
            Moves += M1;
            Rotate(M1);
        }//UR u. UL
        
        return Moves;
    }
    
    //Loest die kante vorne des unteren Layers
    String SolveEdge_1(int Edge){
        int a = findEdge(Edge);
        //System.out.println("Ziel: " + Edge + " EDGE GESUCHT " + a + "   ");
        //for(int k = 0; k < Edges.length; k++){
        //    System.out.print(Edges[k] + " ");
        //}
        
        String Moves = "";
        //System.out.print("Mit Stein " + a + " tauschen: ");
        switch (a){
            case 0: Moves += "FF"; Rotate("FF"); break; //UF
            case 1: Moves += "RRRFR"; Rotate("RRRFR"); break; //UR
            case 2: Moves += "URRRFR"; Rotate("URRRFR"); break; //UB
            case 3: Moves += "LFFFLLL"; Rotate("LFFFLLL"); break; //UL
            case 4: Moves += "FFF"; Rotate("FFF"); break; //LF
            case 5: Moves += "F"; Rotate("F"); break; //FR
            case 6: Moves += "BUUBBBFF"; Rotate("BUUBBBFF"); break; //RB
            case 7: Moves += "BBBUUBFF"; Rotate("BBBUUBFF"); break; //BL
            case 8: Moves += ""; Rotate(""); break; //DF
            case 9: Moves += "RF"; Rotate("RF"); break; //DR
            case 10: Moves += "BBUUFF"; Rotate("BBUUFF"); break; //DB
            case 11: Moves += "LLLFFF"; Rotate("LLLFFF"); break; //DL
            case -1: break;
        }
        
        
        
        //Fuer Stein 8 u. 9 muss EdgeRotation = 0 sein, fuer Stein 10 u. 11 gleich 1
        if(Edge == 8 && EdgeRotation[8] == 1) {Moves += "FDDDLD"; Rotate("FDDDLD");}
        if(Edge == 9 && EdgeRotation[8] == 1) {Moves += "FDDDLD"; Rotate("FDDDLD");}
        if(Edge == 10 && EdgeRotation[8] == 0) {Moves += "FDDDLD"; Rotate("FDDDLD");}
        if(Edge == 11 && EdgeRotation[8] == 0) {Moves += "FDDDLD"; Rotate("FDDDLD");}
        
        
        
        return Moves;
    }
    
    // Loest die Ecke vorne Rechts des unteren Layers
    String SolveCorner_1(int Corner){
        int a = findCorner(Corner);
        String Moves = "";
        
        switch(a){
            case 0: Moves += ""; Rotate(""); break; //FRD - stimmt
            case 1: Moves += "RRRURURURRR"; Rotate("RRRURURURRR"); break;//BRD - stimmt
            case 2: Moves += "RURRR"; Rotate("RURRR"); break;//FRU - stimmt
            case 3: Moves += "URURRR"; Rotate("URURRR"); break;//BRU - stimmt
            case 4: Moves += "UUURURRR"; Rotate("UUURURRR"); break;//FLU - stimmt
            case 5: Moves += "UURURRR"; Rotate("UURURRR"); break;//BLU - stimmt
            case 6: Moves += "LUULLLRURRR"; Rotate("LUULLLRURRR"); break;//BDL - stimmt
            case 7: Moves += "LLLUUULRURRR"; Rotate("LLLUUULRURRR"); break;//FDL - stimmt
            case -1: break;
        }
        
        // Rotation muss immer = 0 sein, damit Weiss unten ist
        if(CornerRotation[0] == 2) {Moves += "RURRRUUURURRRUUU"; Rotate("RURRRUUURURRRUUU");}
        if(CornerRotation[0] == 1) {Moves += "RURRRUUURURRRUUURURRRUUURURRRUUU"; Rotate("RURRRUUURURRRUUURURRRUUURURRRUUU");}
                
        return Moves;
    }
    
    //Setzt Kantenstein von Position 0 auf Postion 5
    String L2Move(){
        String Moves = "URUUURRRUUUFFFUF";
        return Moves;
    }
    
    //Dreht den Gesamten Wuerfel um 90 Grad
    // z.B. Seite F wird zu Seite L, Seite B zu R, usw.
    //wird hier nicht benoetigt
    String RotateCube(){
        int[] CopyCorners = new int[8];
        int[] CopyEdges = new int[12];
        int[] CopyCornerRotation = new int[8];
        int[] CopyEdgeRotation = new int[12];
        
        //Corners
        CopyCornerRotation[0] = CornerRotation[1];
        CopyCornerRotation[1] = CornerRotation[6];
        CopyCornerRotation[2] = CornerRotation[3];
        CopyCornerRotation[3] = CornerRotation[5];
        CopyCornerRotation[4] = CornerRotation[2];
        CopyCornerRotation[5] = CornerRotation[4];
        CopyCornerRotation[6] = CornerRotation[7];
        CopyCornerRotation[7] = CornerRotation[0];
        
        CopyCorners[0] = Corners[1];
        CopyCorners[1] = Corners[6];
        CopyCorners[2] = Corners[3];
        CopyCorners[3] = Corners[5];
        CopyCorners[4] = Corners[2];
        CopyCorners[5] = Corners[4];
        CopyCorners[6] = Corners[7];
        CopyCorners[7] = Corners[0];
        
        //Edges
        CopyEdgeRotation[0] = EdgeRotation[1];
        CopyEdgeRotation[1] = EdgeRotation[2];
        CopyEdgeRotation[2] = EdgeRotation[3];
        CopyEdgeRotation[3] = EdgeRotation[0];
        CopyEdgeRotation[4] = EdgeRotation[5];
        CopyEdgeRotation[5] = EdgeRotation[6];
        CopyEdgeRotation[6] = EdgeRotation[7];
        CopyEdgeRotation[7] = EdgeRotation[4];
        CopyEdgeRotation[8] = EdgeRotation[9];
        CopyEdgeRotation[9] = EdgeRotation[10];
        CopyEdgeRotation[10] = EdgeRotation[11];
        CopyEdgeRotation[11] = EdgeRotation[8];
        
        CopyEdges[0] = Edges[1];
        CopyEdges[1] = Edges[2];
        CopyEdges[2] = Edges[3];
        CopyEdges[3] = Edges[0];
        CopyEdges[4] = Edges[5];
        CopyEdges[5] = Edges[6];
        CopyEdges[6] = Edges[7];
        CopyEdges[7] = Edges[4];
        CopyEdges[8] = Edges[9];
        CopyEdges[9] = Edges[10];
        CopyEdges[10] = Edges[11];
        CopyEdges[11] = Edges[8];
        
        Save(CopyCorners, CopyEdges, CopyCornerRotation, CopyEdgeRotation);
        
        return "X"; // X = Zeichen fuer Drehung
    }
    
    private int findCorner(int Corner){
        for(int i = 0; i < 8; i++){
            if(Corners[i] == Corner) return i;
        }
        return -1;
    }
    
    private int findEdge(int Edge){
        for(int i = 0; i < 12; i++){
            if(Edges[i] == Edge) return i;
        }
        return -1;
    }
    
    //Simuliert eine vertikale Drehung um 90 grad, indem die Zuege geaendert werden
    public String translateMoves(String Moves, int Rot){
        char[] hilf;
        hilf = Moves.toCharArray();
        
        if(Rot == 0) {
            return Moves;
        }else{
        
        for(int j = 0; j < Rot; j++){
            for(int i = 0; i < hilf.length; i++){
                switch(hilf[i]){
                    case 'F': hilf[i] = 'R'; break;
                    case 'R': hilf[i] = 'B'; break;
                    case 'B': hilf[i] = 'L'; break;
                    case 'L': hilf[i] = 'F'; break;
                }
            } 
        }
        
        String b = new String(hilf);
        return b;}
    }
}
