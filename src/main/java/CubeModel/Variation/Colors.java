/*
 * Uebersetzt die Inputs fuer die 3D Darstellung
 * jeder einzelne Cubie hat dort 6 Seiten, fuer diese muessen die Farben gegeben werden
 * Jeder cubie -> 27
 * 6 Seiten pro Cubie -> 6
 * Dimension [27][6]
 * Code unter anderm von https://gist.github.com/jperedadnr/28534fcdce605b75382b
 */
package CubeModel.Variation;

/**
 *
 * @author florian
 */
public final class Colors{
    
    private final int RED     = 0; 
    private final int GREEN   = 1; 
    private final int BLUE    = 2; 
    private final int YELLOW  = 3; 
    private final int ORANGE  = 4; 
    private final int WHITE   = 5; 
    private final int GRAY    = 6;
    
    private final int[] FLD  = new int[]{BLUE, GRAY, GRAY, GRAY, ORANGE, WHITE};
    private final int[] FD   = new int[]{BLUE, GRAY, GRAY, GRAY, GRAY, WHITE};
    private final int[] FRD  = new int[]{BLUE, RED, GRAY, GRAY, GRAY, WHITE};
    private final int[] FL   = new int[]{BLUE, GRAY, GRAY, GRAY, ORANGE, GRAY};
    private final int[] F    = new int[]{BLUE, GRAY, GRAY, GRAY, GRAY, GRAY};
    private final int[] FR   = new int[]{BLUE, RED, GRAY, GRAY, GRAY, GRAY};
    private final int[] FLU  = new int[]{BLUE, GRAY, YELLOW, GRAY, ORANGE, GRAY};
    private final int[] FU   = new int[]{BLUE, GRAY, YELLOW, GRAY, GRAY, GRAY};
    private final int[] FRU  = new int[]{BLUE, RED, YELLOW, GRAY, GRAY, GRAY};
    
    private final int[] CLD  = new int[]{GRAY, GRAY, GRAY, GRAY, ORANGE, WHITE};
    private final int[] CD   = new int[]{GRAY, GRAY, GRAY, GRAY, GRAY, WHITE};
    private final int[] CRD  = new int[]{GRAY, RED, GRAY, GRAY, GRAY, WHITE};
    private final int[] CL   = new int[]{GRAY, GRAY, GRAY, GRAY, ORANGE, GRAY};
    private final int[] C    = new int[]{GRAY, GRAY, GRAY, GRAY, GRAY, GRAY};
    private final int[] CR   = new int[]{GRAY, RED, GRAY, GRAY, GRAY, GRAY};
    private final int[] CLU  = new int[]{GRAY, GRAY, YELLOW, GRAY, ORANGE, GRAY};
    private final int[] CU   = new int[]{GRAY, GRAY, YELLOW, GRAY, GRAY, GRAY};
    private final int[] CRU  = new int[]{GRAY, RED, YELLOW, GRAY, GRAY, GRAY};
    
    private final int[] BLD  = new int[]{GRAY, GRAY, GRAY, GREEN, ORANGE, WHITE};
    private final int[] BD   = new int[]{GRAY, GRAY, GRAY, GREEN, GRAY, WHITE};
    private final int[] BRD  = new int[]{GRAY, RED, GRAY, GREEN, GRAY, WHITE};
    private final int[] BL   = new int[]{GRAY, GRAY, GRAY, GREEN, ORANGE, GRAY};
    private final int[] B    = new int[]{GRAY, GRAY, GRAY, GREEN, GRAY, GRAY};
    private final int[] BR   = new int[]{GRAY, RED, GRAY, GREEN, GRAY, GRAY};
    private final int[] BLU  = new int[]{GRAY, GRAY, YELLOW, GREEN, ORANGE, GRAY};
    private final int[] BU   = new int[]{GRAY, GRAY, YELLOW, GREEN, GRAY, GRAY};
    private final int[] BRU  = new int[]{GRAY, RED, YELLOW, GREEN, GRAY, GRAY};
    
    public int[][] cube = new int[27][6];
    
    int[][] copycube = new int[27][6];
    
    // Konstruktor stellt den Normalen Würfel her
    public Colors(){
        setNormCube();
    }
    
    //Konstruktor 
    public Colors(String rot){
        setNormCube();
        Rotate(rot);
    }
    
    public Colors(int[][] faces){
        getSides(faces);
    }
    
    private void save(){
        for(int i = 0; i < 27; i++){
            for(int j = 0; j < 6; j++){
                cube[i][j] = copycube[i][j];
            }
        }
    }
    
    private void setNormCube(){
        cube = new int[][]{
            FLD, FD, FRD, FL, F, FR, FLU, FU, FRU,
            CLD, CD, CRD, CL, C, CR, CLU, CU, CRU,
            BLD, BD, BRD, BL, B, BR, BLU, BU, BRU
        };
    }
    
    public int[][] getNormCube(){
        return new int[][]{
            FLD, FD, FRD, FL, F, FR, FLU, FU, FRU,
            CLD, CD, CRD, CL, C, CR, CLU, CU, CRU,
            BLD, BD, BRD, BL, B, BR, BLU, BU, BRU
        };
    }
    
    public int[][] getCube(){
        return cube;
        
    }
    
    private void write(){
        for(int i = 0; i < 27; i++){
            for(int j = 0; j < 6; j++){
                copycube[i][j] = cube[i][j];
            }
        }
    }
    
    public void getPermutations(Permutations Cube){
        
    }
    
    private void getSides(int[][] Faces){
        setNormCube();
        write();
        //Übersetzer zwischen dem Input und dem 3D Modell
        
        //F
        copycube[6][0] =  Faces[0][0];
        copycube[7][0] =  Faces[1][0];
        copycube[8][0] =  Faces[2][0];
        copycube[3][0] =  Faces[3][0];
        copycube[4][0] =  Faces[4][0];
        copycube[5][0] =  Faces[5][0];
        copycube[0][0] =  Faces[6][0];
        copycube[1][0] =  Faces[7][0];
        copycube[2][0] =  Faces[8][0];
        //R
        copycube[8][1] =  Faces[0][1];
        copycube[17][1] =  Faces[1][1];
        copycube[26][1] =  Faces[2][1];
        copycube[5][1] =  Faces[3][1];
        copycube[14][1] =  Faces[4][1];
        copycube[23][1] =  Faces[5][1];
        copycube[2][1] =  Faces[6][1];
        copycube[11][1] =  Faces[7][1];
        copycube[20][1] =  Faces[8][1];
        //U
        copycube[8][2] =  Faces[0][2];
        copycube[7][2] =  Faces[1][2];
        copycube[6][2] =  Faces[2][2];
        copycube[17][2] =  Faces[3][2];
        copycube[16][2] =  Faces[4][2];
        copycube[15][2] =  Faces[5][2];
        copycube[26][2] =  Faces[6][2];
        copycube[25][2] =  Faces[7][2];
        copycube[24][2] =  Faces[8][2];
        //B
        copycube[26][3] =  Faces[0][3];
        copycube[25][3] =  Faces[1][3];
        copycube[24][3] =  Faces[2][3];
        copycube[23][3] =  Faces[3][3];
        copycube[22][3] =  Faces[4][3];
        copycube[21][3] =  Faces[5][3];
        copycube[20][3] =  Faces[6][3];
        copycube[19][3] =  Faces[7][3];
        copycube[18][3] =  Faces[8][3];
        //L
        copycube[24][4] =  Faces[0][4];
        copycube[15][4] =  Faces[1][4];
        copycube[6][4] =  Faces[2][4];
        copycube[21][4] =  Faces[3][4];
        copycube[12][4] =  Faces[4][4];
        copycube[3][4] =  Faces[5][4];
        copycube[18][4] =  Faces[6][4];
        copycube[9][4] =  Faces[7][4];
        copycube[0][4] =  Faces[8][4];
        //D
        copycube[20][5] =  Faces[0][5];
        copycube[19][5] =  Faces[1][5];
        copycube[18][5] =  Faces[2][5];
        copycube[11][5] =  Faces[3][5];
        copycube[10][5] =  Faces[4][5];
        copycube[9][5] =  Faces[5][5];
        copycube[2][5] =  Faces[6][5];
        copycube[1][5] =  Faces[7][5];
        copycube[0][5] =  Faces[8][5];
        
        
        
        save();
    }
    
    public int[][] returnFaces(){
        int[][] Faces = new int[9][6];
        
        Faces[0][0]=cube[6][0]; 
        Faces[1][0]=cube[7][0];
        Faces[2][0]=cube[8][0];
        Faces[3][0]=cube[3][0];
        Faces[4][0]=cube[4][0];
        Faces[5][0]=cube[5][0];
        Faces[6][0]=cube[0][0];
        Faces[7][0]=cube[1][0];
        Faces[8][0]=cube[2][0];
        //R
        Faces[0][1]=cube[8][1];
        Faces[1][1]=cube[17][1];
        Faces[2][1]=cube[26][1];
        Faces[3][1]=cube[5][1];
        Faces[4][1]=cube[14][1];
        Faces[5][1]=cube[23][1];
        Faces[6][1]=cube[2][1];
        Faces[7][1]=cube[11][1];
        Faces[8][1]=cube[20][1];
        //U
        Faces[0][2]=cube[8][2];
        Faces[1][2]=cube[7][2];
        Faces[2][2]=cube[6][2];
        Faces[3][2]=cube[17][2];
        Faces[4][2]=cube[16][2];
        Faces[5][2]=cube[15][2];
        Faces[6][2]=cube[26][2];
        Faces[7][2]=cube[25][2];
        Faces[8][2]=cube[24][2];
        //B
        Faces[0][3]=cube[26][3];
        Faces[1][3]=cube[25][3];
        Faces[2][3]=cube[24][3];
        Faces[3][3]=cube[23][3];
        Faces[4][3]=cube[22][3];
        Faces[5][3]=cube[21][3];
        Faces[6][3]=cube[20][3];
        Faces[7][3]=cube[19][3];
        Faces[8][3]=cube[18][3];
        //L
        Faces[0][4]=cube[24][4];
        Faces[1][4]=cube[15][4];
        Faces[2][4]=cube[6][4];
        Faces[3][4]=cube[21][4];
        Faces[4][4]=cube[12][4];
        Faces[5][4]=cube[3][4];
        Faces[6][4]=cube[18][4];
        Faces[7][4]=cube[9][4];
        Faces[8][4]=cube[0][4];
        //D
        Faces[0][5]=cube[20][5];
        Faces[1][5]=cube[19][5];
        Faces[2][5]=cube[18][5];
        Faces[3][5]=cube[11][5];
        Faces[4][5]=cube[10][5];
        Faces[5][5]=cube[9][5];
        Faces[6][5]=cube[2][5];
        Faces[7][5]=cube[1][5];
        Faces[8][5]=cube[0][5];
        
        return Faces;
    }
    
    public void Rotate(String rot) {
        
        //Gibt dem Modell nur die Zuege F,R,U,B,L,D weiter
        rot = rot.replace("f", "FFF");
        rot = rot.replace("r", "RRR");
        rot = rot.replace("u", "UUU");
        rot = rot.replace("b", "BBB");
        rot = rot.replace("l", "LLL");
        rot = rot.replace("d", "DDD");
        
        
        for (int i = 0; i < rot.length(); i++){
            
            
            
            //copycube = cube;
            //setNormCube();
            write();
            //int[][] copycube = cube;
            switch(rot.charAt(i)){//cube[Steine (1-27)][Seiten (1-6)]
                case 'L': {//20 Flaechen aendern sich bei einem Grundzug
                    copycube[0][0] = cube[6][2];    //FLD = ULF
                    copycube[0][4] = cube[6][4];    //LFD = LUF
                    copycube[0][5] = cube[6][0];    //DFL = FUL
                    copycube[3][0] = cube[15][2];   //FL = UL
                    copycube[3][4] = cube[15][4];   //LF = LU
                    copycube[6][0] = cube[24][2];   //FLU = UBL
                    copycube[6][2] = cube[24][3];   //ULF = BUL
                    copycube[6][4] = cube[24][4];   //LUF = LBU
                    copycube[9][4] = cube[3][4];    //LD = LF
                    copycube[9][5] = cube[3][0];    //DL = FL
                    copycube[15][2] = cube[21][3];  //UL = BL
                    copycube[15][4] = cube[21][4];  //LU = LB
                    copycube[18][3] = cube[0][5];   //BLD = DLF
                    copycube[18][4] = cube[0][4];   //LBD = LDF
                    copycube[18][5] = cube[0][0];   //DBL = FDL
                    copycube[21][3] = cube[9][5];   //BL = DL
                    copycube[21][4] = cube[9][4];   //LB = LD
                    copycube[24][2] = cube[18][3];  //UBL = BLD
                    copycube[24][3] = cube[18][5];  //BLU = DLB
                    copycube[24][4] = cube[18][4];  //LBU = LDB
                } break;
                case 'R': {
                    copycube[2][0] = cube[20][5];   //FRD = DRB
                    copycube[2][1] = cube[20][1];   //RFD = RDB
                    copycube[2][5] = cube[20][3];   //DFR = BRD
                    copycube[5][0] = cube[11][5];   //FR = DR
                    copycube[5][1] = cube[11][1];   //RF = RD..
                    copycube[8][0] = cube[2][5];    //FRU = DFR
                    copycube[8][1] = cube[2][1];    //RFU = RFD
                    copycube[8][2] = cube[2][0];    //UFR = FRD
                    copycube[11][1] = cube[23][1];  //RD = RB
                    copycube[11][5] = cube[23][3];  //DR = BR
                    copycube[17][1] = cube[5][1];   //RU = RF
                    copycube[17][2] = cube[5][0];   //UR = FR
                    copycube[20][1] = cube[26][1];  //RBD = RBU
                    copycube[20][3] = cube[26][2];  //BRD = UBR
                    copycube[20][5] = cube[26][3];  //DRB = BRU
                    copycube[23][1] = cube[17][1];  //RB = RU
                    copycube[23][3] = cube[17][2];  //BR = UR
                    copycube[26][1] = cube[8][1];   //RUB = RFU
                    copycube[26][2] = cube[8][0];   //URB = FUR
                    copycube[26][3] = cube[8][2];   //BRU = URF
                } break;
                case 'U': {
                    copycube[6][0] = cube[8][1];    //FUL = RFU
                    copycube[6][2] = cube[8][2];    //UFL = UFR
                    copycube[6][4] = cube[8][0];    //LUF = FRU
                    copycube[7][0] = cube[17][1];   //FU = RU
                    copycube[7][2] = cube[17][2];   //UF = UR
                    copycube[8][0] = cube[26][1];   //FRU = RUB
                    copycube[8][1] = cube[26][3];   //RFU = BRU
                    copycube[8][2] = cube[26][2];   //UFR = FRD
                    copycube[15][2] = cube[7][2];   //UL = UF
                    copycube[15][4] = cube[7][0];   //LU = FU
                    copycube[17][1] = cube[25][3];  //RU = BU
                    copycube[17][2] = cube[25][2];  //UR = UB
                    copycube[24][2] = cube[6][2];   //UBL = UFR
                    copycube[24][3] = cube[6][4];   //BUL = LFU
                    copycube[24][4] = cube[6][0];   //LUB = FUL
                    copycube[25][2] = cube[15][2];  //UB = UL
                    copycube[25][3] = cube[15][4];  //BU = LU
                    copycube[26][1] = cube[24][3];  //RUB = BUL
                    copycube[26][2] = cube[24][2];  //URB = UBL
                    copycube[26][3] = cube[24][4];  //BRU = LUB
                } break;
                case 'D': {
                    copycube[0][0] = cube[18][4];   //FLD = LBD
                    copycube[0][4] = cube[18][3];   //LFD = BLD
                    copycube[0][5] = cube[18][5];   //DFL = DBL
                    copycube[1][0] = cube[9][4];    //FD = LD
                    copycube[1][5] = cube[9][5];    //DF = DL
                    copycube[2][0] = cube[0][4];    //FRD = LFD
                    copycube[2][1] = cube[0][0];    //RFD = FLD
                    copycube[2][5] = cube[0][5];    //DFR = DFL
                    copycube[9][4] = cube[19][3];   //LD = BD
                    copycube[9][5] = cube[19][5];   //DL = DB
                    copycube[11][1] = cube[1][0];   //RD = FD
                    copycube[11][5] = cube[1][5];   //DR = DF
                    copycube[18][3] = cube[20][1];  //BLD = RBD
                    copycube[18][4] = cube[20][3];  //LBD = BRD
                    copycube[18][5] = cube[20][5];  //DLB = DRB
                    copycube[19][3] = cube[11][1];  //BD = RD
                    copycube[19][5] = cube[11][5];  //DB = DR
                    copycube[20][1] = cube[2][0];   //RBD = FRD
                    copycube[20][3] = cube[2][1];   //BRD = RFD
                    copycube[20][5] = cube[2][5];   //DRB = DFR
                } break;
                case 'F': {
                    copycube[0][0] = cube[2][0];    //FLD = FRD
                    copycube[0][4] = cube[2][5];    //LFD = DFR
                    copycube[0][5] = cube[2][1];    //DFL = RFD
                    copycube[1][0] = cube[5][0];    //FD = FR
                    copycube[1][5] = cube[5][1];    //DF = RF
                    copycube[2][0] = cube[8][0];    //FRD = FRU
                    copycube[2][1] = cube[8][2];    //RFD = UFR
                    copycube[2][5] = cube[8][1];    //DFR = RFU
                    copycube[3][0] = cube[1][0];    //FL = FD
                    copycube[3][4] = cube[1][5];    //LF = DF
                    copycube[5][0] = cube[7][0];    //FR = FU
                    copycube[5][1] = cube[7][2];    //RF = UF
                    copycube[6][0] = cube[0][0];    //FUL = FLD
                    copycube[6][2] = cube[0][4];    //UFL = LFD
                    copycube[6][4] = cube[0][5];    //LFU = DFL
                    copycube[7][0] = cube[3][0];    //FU = FL
                    copycube[7][2] = cube[3][4];    //UF = LF
                    copycube[8][0] = cube[6][0];    //FRU = FUL
                    copycube[8][1] = cube[6][2];    //RFU = UFL
                    copycube[8][2] = cube[6][4];    //UFR = LFU
                } break;
                case 'B': {
                    copycube[18][3] = cube[24][3];  //BLD = BUL
                    copycube[18][4] = cube[24][2];  //LBD = UBL
                    copycube[18][5] = cube[24][4];  //DBL = LUB
                    copycube[19][3] = cube[21][3];  //BD = BL
                    copycube[19][5] = cube[21][4];  //DB = LB
                    copycube[20][1] = cube[18][5];  //RBD = DBL
                    copycube[20][3] = cube[18][3];  //BRD = BLD
                    copycube[20][5] = cube[18][4];  //DRB = LBD
                    copycube[21][3] = cube[25][3];  //BL = BU
                    copycube[21][4] = cube[25][2];  //LB = UB
                    copycube[23][1] = cube[19][5];  //RB = DB
                    copycube[23][3] = cube[19][3];  //BR = BD
                    copycube[24][2] = cube[26][1];  //UBL = RUB
                    copycube[24][3] = cube[26][3];  //BUL = BRU
                    copycube[24][4] = cube[26][2];  //LUB = URB
                    copycube[25][2] = cube[23][1];  //UB = RB
                    copycube[25][3] = cube[23][3];  //BU = BR
                    copycube[26][1] = cube[20][5];  //RUB = DRB
                    copycube[26][2] = cube[20][1];  //URB = RBD
                    copycube[26][3] = cube[20][3];  //BRU = BRD
                } break;
                default: System.out.println("Kein korrekter Zug an Stelle: " + (i+1)); break;
            }
            
            save();
            
        }
    }
    
}
