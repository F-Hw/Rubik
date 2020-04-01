/*
 * Erstellt aus den Uebergebenen Farben von "Colors" das 3D Modell
 * Gibt eine SubScene fuers GUI
 * Code zum Grossteil von https://gist.github.com/jperedadnr/28534fcdce605b75382b
 */
package CubeModel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import CubeModel.Variation.Colors;

/**
 *
 * @author florian
 */
public class Cube{
    Group root = new Group();
    double Width = 300;
    double Heigth = 300;
    
    
    
    public static final float X_RED     = 0.5f / 7f;
    public static final float X_GREEN   = 1.5f / 7f;
    public static final float X_BLUE    = 2.5f / 7f;
    public static final float X_YELLOW  = 3.5f / 7f;
    public static final float X_ORANGE  = 4.5f / 7f;
    public static final float X_WHITE   = 5.5f / 7f;
    public static final float X_GRAY    = 6.5f / 7f;
    
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    
    private static final int RED     = 0;
    private static final int GREEN   = 1;
    private static final int BLUE    = 2;
    private static final int YELLOW  = 3;
    private static final int ORANGE  = 4;
    private static final int WHITE   = 5;
    private static final int GRAY    = 6;
    
    private static final Point3D pFLD   = new Point3D(-1.1,  1.1, -1.1);
    private static final Point3D pFD    = new Point3D(   0,  1.1, -1.1);
    private static final Point3D pFRD   = new Point3D( 1.1,  1.1, -1.1);
    private static final Point3D pFL    = new Point3D(-1.1,    0, -1.1);
    private static final Point3D pF     = new Point3D(   0,    0, -1.1);
    private static final Point3D pFR    = new Point3D( 1.1,    0, -1.1);
    private static final Point3D pFLU   = new Point3D(-1.1, -1.1, -1.1);
    private static final Point3D pFU    = new Point3D(   0, -1.1, -1.1);
    private static final Point3D pFRU   = new Point3D( 1.1, -1.1, -1.1);
    
    private static final Point3D pCLD   = new Point3D(-1.1,  1.1, 0);
    private static final Point3D pCD    = new Point3D(   0,  1.1, 0);
    private static final Point3D pCRD   = new Point3D( 1.1,  1.1, 0);
    private static final Point3D pCL    = new Point3D(-1.1,    0, 0);
    private static final Point3D pC     = new Point3D(   0,    0, 0);
    private static final Point3D pCR    = new Point3D( 1.1,    0, 0);
    private static final Point3D pCLU   = new Point3D(-1.1, -1.1, 0);
    private static final Point3D pCU    = new Point3D(   0, -1.1, 0);
    private static final Point3D pCRU   = new Point3D( 1.1, -1.1, 0);
    
    private static final Point3D pBLD   = new Point3D(-1.1,  1.1, 1.1);
    private static final Point3D pBD    = new Point3D(   0,  1.1, 1.1);
    private static final Point3D pBRD   = new Point3D( 1.1,  1.1, 1.1);
    private static final Point3D pBL    = new Point3D(-1.1,    0, 1.1);
    private static final Point3D pB     = new Point3D(   0,    0, 1.1);
    private static final Point3D pBR    = new Point3D( 1.1,    0, 1.1);
    private static final Point3D pBLU   = new Point3D(-1.1, -1.1, 1.1);
    private static final Point3D pBU    = new Point3D(   0, -1.1, 1.1);
    private static final Point3D pBRU   = new Point3D( 1.1, -1.1, 1.1);
    
    private static final List<Point3D> pointsFaceF = Arrays.asList(
            pFLD, pFD, pFRD, pFL, pF, pFR, pFLU, pFU, pFRU,
            pCLD, pCD, pCRD, pCL, pC, pCR, pCLU, pCU, pCRU,
            pBLD, pBD, pBRD, pBL, pB, pBR, pBLU, pBU, pBRU);
    
    
    
    
    
    public SubScene getNormCube(){
        
        Group sceneRoot = new Group();
        SubScene sub = new SubScene(sceneRoot, Width, Heigth, true, SceneAntialiasing.BALANCED);//Subscene statt Scene
        sub.setFill(Color.BLACK);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-10);
        sub.setCamera(camera);

        PhongMaterial mat = new PhongMaterial();
        // image can be found here http://i.stack.imgur.com/uN4dv.png
        mat.setDiffuseMap(new Image(getClass().getResourceAsStream("palette.png")));

        Group meshGroup = new Group();
        
        AtomicInteger cont = new AtomicInteger();
        
        Colors col = new Colors();
        
        
        //int[][] hilf = Colors.getNormCube();
        
        for (int i = 0; i < 27; i++){
            MeshView meshP = new MeshView();
            meshP.setMesh(createCube(col.getNormCube()[i]));
            meshP.setMaterial(mat);
            
            Point3D pt = pointsFaceF.get(cont.getAndIncrement());
            meshP.getTransforms().addAll(new Translate(pt.getX(), pt.getY(), pt.getZ()));
            meshGroup.getChildren().add(meshP);
        }
        
        Rotate rotateX = new Rotate(30, 0, 0, 0, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(20, 0, 0, 0, Rotate.Y_AXIS);
        meshGroup.getTransforms().addAll(rotateX, rotateY);
        
        sceneRoot.getChildren().addAll(meshGroup, new AmbientLight(Color.WHITE));
        
        sub.setOnMousePressed(me -> {
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        sub.setOnMouseDragged(me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            rotateX.setAngle(rotateX.getAngle()-(mousePosY - mouseOldY));
            rotateY.setAngle(rotateY.getAngle()+(mousePosX - mouseOldX));
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
        });
        
        return sub;
    }
    
    
    public SubScene getShuffleCube(String rot){
        
        Group sceneRoot = new Group();
        SubScene sub = new SubScene(sceneRoot, Width, Heigth, true, SceneAntialiasing.BALANCED);//Subscene statt Scene
        sub.setFill(Color.BLACK);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-10);
        sub.setCamera(camera);

        PhongMaterial mat = new PhongMaterial();
        // image can be found here http://i.stack.imgur.com/uN4dv.png
        mat.setDiffuseMap(new Image(getClass().getResourceAsStream("palette.png")));
        
        

        Group meshGroup = new Group();
        
        AtomicInteger cont = new AtomicInteger();
        
        Colors col = new Colors(rot);
        //col.Rotate(rot);
        
        
        
        for (int i = 0; i < 27; i++){
            MeshView meshP = new MeshView();
            meshP.setMesh(createCube(col.getCube()[i]));
            meshP.setMaterial(mat);
            
            Point3D pt = pointsFaceF.get(cont.getAndIncrement());
            meshP.getTransforms().addAll(new Translate(pt.getX(), pt.getY(), pt.getZ()));
            meshGroup.getChildren().add(meshP);
        }
        
        Rotate rotateX = new Rotate(30, 0, 0, 0, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(20, 0, 0, 0, Rotate.Y_AXIS);
        meshGroup.getTransforms().addAll(rotateX, rotateY);
        
        sceneRoot.getChildren().addAll(meshGroup, new AmbientLight(Color.WHITE));
        
        sub.setOnMousePressed(me -> {
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        sub.setOnMouseDragged(me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            rotateX.setAngle(rotateX.getAngle()-(mousePosY - mouseOldY));
            rotateY.setAngle(rotateY.getAngle()+(mousePosX - mouseOldX));
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
        });
        return sub;
    }
    
    public SubScene getSpecificCube(int[][] Faces){
        
        Group sceneRoot = new Group();
        SubScene sub = new SubScene(sceneRoot, Width, Heigth, true, SceneAntialiasing.BALANCED);//Subscene statt Scene
        sub.setFill(Color.BLACK);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-10);
        sub.setCamera(camera);

        PhongMaterial mat = new PhongMaterial();
        // image can be found here http://i.stack.imgur.com/uN4dv.png
        mat.setDiffuseMap(new Image(getClass().getResourceAsStream("palette.png")));

        Group meshGroup = new Group();
        
        AtomicInteger cont = new AtomicInteger();
        
        Colors col = new Colors(Faces);
        //col.getSides(Faces);
        
        
        
        for (int i = 0; i < 27; i++){
            MeshView meshP = new MeshView();
            meshP.setMesh(createCube(col.getCube()[i]));
            meshP.setMaterial(mat);
            
            Point3D pt = pointsFaceF.get(cont.getAndIncrement());
            meshP.getTransforms().addAll(new Translate(pt.getX(), pt.getY(), pt.getZ()));
            meshGroup.getChildren().add(meshP);
        }
        
        Rotate rotateX = new Rotate(30, 0, 0, 0, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(20, 0, 0, 0, Rotate.Y_AXIS);
        meshGroup.getTransforms().addAll(rotateX, rotateY);
        
        sceneRoot.getChildren().addAll(meshGroup, new AmbientLight(Color.WHITE));
        
        sub.setOnMousePressed(me -> {
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        sub.setOnMouseDragged(me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            rotateX.setAngle(rotateX.getAngle()-(mousePosY - mouseOldY));
            rotateY.setAngle(rotateY.getAngle()+(mousePosX - mouseOldX));
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
        });
        return sub;
    }
    
    public SubScene getCubefromColors(Colors col){
        
        Group sceneRoot = new Group();
        SubScene sub = new SubScene(sceneRoot, Width, Heigth, true, SceneAntialiasing.BALANCED);//Subscene statt Scene
        sub.setFill(Color.BLACK);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-10);
        sub.setCamera(camera);

        PhongMaterial mat = new PhongMaterial();
        // image can be found here http://i.stack.imgur.com/uN4dv.png
        mat.setDiffuseMap(new Image(getClass().getResourceAsStream("palette.png")));

        Group meshGroup = new Group();
        
        AtomicInteger cont = new AtomicInteger();
        
        //Colors col = new Colors(Faces);
        //col.getSides(Faces);
        
        
        
        for (int i = 0; i < 27; i++){
            MeshView meshP = new MeshView();
            meshP.setMesh(createCube(col.getCube()[i]));
            meshP.setMaterial(mat);
            
            Point3D pt = pointsFaceF.get(cont.getAndIncrement());
            meshP.getTransforms().addAll(new Translate(pt.getX(), pt.getY(), pt.getZ()));
            meshGroup.getChildren().add(meshP);
        }
        
        Rotate rotateX = new Rotate(30, 0, 0, 0, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(20, 0, 0, 0, Rotate.Y_AXIS);
        meshGroup.getTransforms().addAll(rotateX, rotateY);
        
        sceneRoot.getChildren().addAll(meshGroup, new AmbientLight(Color.WHITE));
        
        sub.setOnMousePressed(me -> {
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        sub.setOnMouseDragged(me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            rotateX.setAngle(rotateX.getAngle()-(mousePosY - mouseOldY));
            rotateY.setAngle(rotateY.getAngle()+(mousePosX - mouseOldX));
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
        });
        return sub;
    }
    
    private TriangleMesh createCube(int[] face) {
        TriangleMesh m = new TriangleMesh();
        
        
        

        // POINTS
        m.getPoints().addAll(
             0.5f,  0.5f,  0.5f,
             0.5f, -0.5f,  0.5f,
             0.5f,  0.5f, -0.5f,
             0.5f, -0.5f, -0.5f,
            -0.5f,  0.5f,  0.5f,
            -0.5f, -0.5f,  0.5f,
            -0.5f,  0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f
        );

        // TEXTURES
        m.getTexCoords().addAll(
            X_RED, 0.5f, 
            X_GREEN, 0.5f,
            X_BLUE, 0.5f, 
            X_YELLOW, 0.5f, 
            X_ORANGE, 0.5f,  
            X_WHITE, 0.5f,
            X_GRAY, 0.5f
        );

            // FACES
        m.getFaces().addAll(
            2,face[0],3,face[0],6,face[0],      // F      
            3,face[0],7,face[0],6,face[0],  

            0,face[1],1,face[1],2,face[1],      // R     
            2,face[1],1,face[1],3,face[1],         

            1,face[2],5,face[2],3,face[2],      // U   
            5,face[2],7,face[2],3,face[2],

            0,face[3],4,face[3],1,face[3],      // B      
            4,face[3],5,face[3],1,face[3],       

            4,face[4],6,face[4],5,face[4],      // L      
            6,face[4],7,face[4],5,face[4],    

            0,face[5],2,face[5],4,face[5],      // D      
            2,face[5],6,face[5],4,face[5]         
        );
        return m;
    }
    
}
