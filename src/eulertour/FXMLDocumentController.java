/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eulertour;

import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Master
 */
public class FXMLDocumentController implements Initializable {
    //Booleans damit nur bei Knopfdruck erstellt wird
    Boolean setPoints = false;
    Boolean setLine = false;
    Boolean firstPointSet = false;
    double startX;
    double startY;

    int startknoten = 0;
    int endknoten;

    //Speichert die Punkte & Linien
    ArrayList<eulertour.Points> pointList = new ArrayList<eulertour.Points>();
    //Graph erstellen
    Graph g1 = new Graph(10);

    @FXML
    private Label label;

    @FXML
    private Button button1, button2, button3;
    @FXML
    private AnchorPane pane;

    @FXML
    private Label eulertourAusgabe;

    @FXML
    private Label hasEuler;

    @FXML
    //erlaubt die Erstellung eines neuen Punktes bei Ausführung des MouseEvents brutal spagetthi code aber funktioniert bis jetzt
    private void handleButtonNewPoint(ActionEvent event) {
        System.out.println("You clicked me!");
        setPoints = true;
        setLine = false; // damit man nicht punkte und Linien gleichzeitig erstellen kann

    }

    @FXML //erlaubt die Erstellung von einer Linie
    private void handleButtonNewLine(ActionEvent event) {
        System.out.println("You clicked me!");
        setLine = true;
        setPoints = false; //damit man nicht punkte und Linien gleichzeitig erstellen kann


    }

    @FXML //soll die Eulertour berechnen
    private void handleButtonCalculate(ActionEvent event) {
        System.out.println("You clicked me!");
        Eulertour eulertour = new Eulertour(g1);
        System.out.println(eulertour.kantenAnzahl());

       if(eulertour.hasEulertour() == 1){
           hasEuler.setText("Eulertour (Startpunkt = Endepunkt)");
           eulertourAusgabe.setText(eulertour.eulerweg());
       }else if (eulertour.hasEulertour() == 2 ) {
           hasEuler.setText("Halb-Eulertour (Startpunkt != Endpunkt)");
           eulertourAusgabe.setText(eulertour.eulerweg());
       }else {
           hasEuler.setText("Has no Eulertour");
       }

        System.out.print(g1.toString());

    }

    @FXML //MouseEvent gibt Koordinaten von Maus mit. Durch clicken soll dort ein Punkt/Linie erstellt werden
    private void handleButtonMouseClicked(MouseEvent event) {
        if (setPoints) {
            //Punkte erstellen
            Circle circle = new Circle();
            circle.setCenterX(event.getSceneX());
            circle.setCenterY(event.getSceneY());
            circle.setRadius(15);
            circle.setFill(Color.DARKSLATEBLUE);
            pane.getChildren().add(circle);

            //Nummer Text erstellen
            Text nummer = new Text();
            String punktNummer = Integer.toString(pointList.size());
            nummer.setText(punktNummer);
            nummer.setX(event.getSceneX()-3);
            nummer.setY(event.getSceneY()+4);
            pane.getChildren().add(nummer);

            setPoints = false;
            eulertour.Points p = new eulertour.Points(event.getSceneX(), event.getSceneY(), 0);
            pointList.add(p);
            System.out.println(pointList.get(0).posX + " " + pointList.get(0).posY);


        } else if (setLine) {
            // To do: zweiter Koordinaten punkt durch clicken hinzufügen (evt. 2. MouseEvent) danach
            //überprüfen ob die beiden clicks in der Hitbox eines Kreises passiert sind
            //dann Linie erstellen und speicher


            for (int i = 0; i < pointList.size(); i++) {

                double x1 = event.getSceneX();
                double y1 = event.getSceneY();
                double x2 = pointList.get(i).getPosX();
                double y2 = pointList.get(i).getPosY();
                double dis = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

                if (dis <= 15) {

                    Line line = new Line();

                    if (firstPointSet) {

                        endknoten = i;

                        //If Schleife um Doppelklick zu vermeiden
                        if (startknoten == endknoten) {
                            endknoten = 0;

                        } else {
                            //Liniezeichen
                            line.setEndX(x2);
                            line.setEndY(y2);
                            line.setStartX(startX);
                            line.setStartY(startY);
                            pane.getChildren().add(line);
                            line.toBack();


                            //Graphen Line einfügen
                            g1.kanteEinfuegen(startknoten, endknoten);
                            g1.kanteEinfuegen(endknoten, startknoten);
                            setLine = false;
                            firstPointSet = false;

                        }

                    } else {

                        startX = pointList.get(i).getPosX();
                        startY = pointList.get(i).getPosY();
                        startknoten = i;
                        System.out.println("Start;" + startknoten);
                        firstPointSet = true;

                    }
                }

                System.out.println("");
                System.out.println("Graph nach dem einf�gen einiger Kanten:");
                System.out.print(g1.toString());

            }

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
