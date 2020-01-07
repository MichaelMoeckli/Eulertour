
package eulertour;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Points {

    double posX;
    double posY;
    int lines;

    public Points(double posX, double poxY, int lines) {
        this.posX = posX;
        this.posY = poxY;
        this.lines = lines;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPoxY(double poxY) {
        this.posY = poxY;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }
}
