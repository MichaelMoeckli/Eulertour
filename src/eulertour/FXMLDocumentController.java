
package eulertour;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FXMLDocumentController implements Initializable {

    private Eulertour eulertour;
    private double tempLineXstart;
    private double tempLineYstart;
    int setpoint = 1; //3 boolean 1 == punktsetzbar; 2 == nochmal drücken ausserhalb Radius; 3 == Reset button

    @FXML
    private Label  hasEuler, clicklb, eulertourAusgabe;


    @FXML
    private Pane clickpane;

    //Speichert die Punkte & Linien
    ArrayList<Points> pointList = new ArrayList<Points>();
    //Graph erstellen
    Graph g1 = new Graph(15);

    @FXML //soll die Eulertour berechnen
    private void handleButtonCalculate(ActionEvent event) {
        eulertour = new Eulertour(g1);
        eulertour.kantenAnzahl();
        eulertourAusgabe.setText("");
        eulertourAusgabe.setFont(Font.font(15));
        if (eulertour.hasEulertour() == 1) {
            hasEuler.setText(" Eulerian cycle (start = end) ");
            eulertourAusgabe.setText(eulertour.eulerweg());
            eulertourAusgabe.setStyle("-fx-background-radius:10; -fx-background-color:#9CD777;");
            hasEuler.setStyle("-fx-background-radius:10; -fx-background-color:#9CD777;");
        } else if (eulertour.hasEulertour() == 2) {
            hasEuler.setText(" Eulerian trail (start != end) ");
            eulertourAusgabe.setText(eulertour.eulerweg());
            eulertourAusgabe.setStyle("-fx-background-radius:10; -fx-background-color:#9CD777;");
            hasEuler.setStyle("-fx-background-radius:10; -fx-background-color:#9CD777;");
        } else {
            hasEuler.setText(" Has no Eulerian path ");
            hasEuler.setStyle("-fx-background-radius:10; -fx-background-color:#F8876F;");
        }


        if (eulertour.hasEulertour() == 4) {
            hasEuler.setText(" Please make another figure (Do not draw more than one figure) ");
            hasEuler.setStyle("-fx-background-radius:10; -fx-background-color:#F8876F;");
        }

        if (eulertour.hasEulertour() == 5) {
            eulertourAusgabe.setText("");
            hasEuler.setText(" min. 2 nodes have to be connected ");
            hasEuler.setStyle("-fx-background-radius:10; -fx-background-color:#F8876F;");
        }

        setpoint = 3;
        clicklb.setText(" click <Reset> to draw a new figure ");
        clicklb.toFront();


    }

    @FXML
    private void handleButtonDelete(ActionEvent event) {
        eulertour = new Eulertour(g1);
        eulertour.delete();
        pointList.clear();
        clickpane.getChildren().clear();
        setpoint = 1;
        tempLineYstart = 0;
        tempLineXstart = 0;
        eulertourAusgabe.setText("");
        hasEuler.setText("");
        clicklb.setText(" click to create a node ");
        clicklb.setStyle("-fx-background-radius:10; -fx-background-color:#9CD777;");

    }

    EventHandler<MouseEvent> handelCircleClick = new EventHandler<MouseEvent>() {
        Points center = new Points(0, 0, 0);
        int index = 0;
        int tempindexstart = 0;

        @Override
        public void handle(MouseEvent e) {

            for (int i = 0; i < pointList.size(); i++) {

                double x2 = pointList.get(i).posX;
                double y2 = pointList.get(i).posY;
                double dis = Math.sqrt((x2 - e.getSceneX() + 18) * (x2 - e.getSceneX() + 18) + (y2 - e.getSceneY() + 87) * (y2 - e.getSceneY() + 87));
                if (dis < 15) {
                    center = pointList.get(i);
                    index = i;
                }
            }
            if (tempLineXstart != 0 && tempLineYstart != 0 && tempindexstart != index && setpoint != 3) {
                clicklb.setText("");
                setLine(tempLineXstart, tempLineYstart, center.posX, center.posY, tempindexstart, index);
                tempLineXstart = 0;
                tempLineYstart = 0;
            }else {
                tempLineXstart = center.posX;
                tempLineYstart = center.posY;
                tempindexstart = index;
            }
        }
    };

    @FXML //MouseEvent gibt Koordinaten von Maus mit. Durch clicken soll dort ein Punkt/Linie erstellt werden
    private void handleButtonMouseClicked(MouseEvent event) {

        for (int i = 0; i < pointList.size(); i++) {

            double x2 = pointList.get(i).posX;
            double y2 = pointList.get(i).posY;
            double dis = Math.sqrt((x2 - event.getSceneX() + 18) * (x2 - event.getSceneX() + 18) + (y2 - event.getSceneY() + 87) * (y2 - event.getSceneY() + 87));
            if (dis < 35 && setpoint == 1) {
                setpoint = 2;
            }
        }
        if (setpoint == 1 && pointList.size() < 15) {
            clicklb.setText("");
            setPoint(event.getSceneX() - 18, event.getSceneY() - 87);
            //Label Veränderungen
            if (pointList.size() == 15) {
                clicklb.setText(" you cannot create more than 15 nodes ");
                clicklb.setStyle("-fx-background-radius:10; -fx-background-color:#F8876F;");
            } else if (pointList.size() >= 2 || pointList.size() < 15) {
                clicklb.setText(" Click on two nodes to connect them ");
            }
        } else if (setpoint == 2) {
            setpoint = 1;
        }

    }


    public void setPoint(double x, double y) {

        //Punkte erstellen
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(15);
        circle.setFill(Color.PINK);
        circle.addEventFilter(MouseEvent.MOUSE_CLICKED, handelCircleClick);
        clickpane.getChildren().add(circle);


        //Nummer Text erstellen
        Text nummer = new Text();
        String punktNummer = Integer.toString(pointList.size());
        nummer.setText(punktNummer);
        nummer.setX(x - 3);
        nummer.setY(y + 4);
        nummer.addEventFilter(MouseEvent.MOUSE_CLICKED, handelCircleClick);
        clickpane.getChildren().add(nummer);

        Points p = new Points(x, y, 0);
        pointList.add(p);

    }

    public void setLine(double startX, double startY, double endX, double endY, int startknoten, int endknoten) {
        Line line = new Line();
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStartX(startX);
        line.setStartY(startY);
        clickpane.getChildren().add(line);
        line.toBack();


        //Graphen Line einfügen
        g1.kanteEinfuegen(startknoten, endknoten);
        g1.kanteEinfuegen(endknoten, startknoten);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clicklb.setStyle("-fx-background-radius:10; -fx-background-color:#9CD777;");
        clicklb.toFront();
        // TODO
    }
}
