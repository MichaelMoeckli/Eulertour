package eulertour;

import java.util.ArrayList;

public class Eulertour {

    private int kantenZaehler = 0;
    private int knotenZahl = 0;
    private Graph graph;
    private int ungeradeKanteAnzahl = 0;
    private boolean[][] matrix;
    private boolean halfEulertour = false;
    private int eulertourStart = 0;
    private int wrongFigureCounter = 0;
    private Boolean wrongFigure = false;


    public Eulertour(Graph graph) {

        this.graph = graph;
    }

    public String kantenAnzahl() {

        String ausgabe = "";

        matrix = graph.getMatrix();

        for (int i = 0; i < matrix.length; i++) {

            kantenZaehler = 0;

            for (int j = 0; j < matrix[i].length; j++) {

                if (j == 0) {
                    knotenZahl = i;
                }
                if (matrix[i][j] == true) {
                    kantenZaehler++;
                }

            }

            if (kantenZaehler % 2 != 0) {
                if (ungeradeKanteAnzahl < 1) {
                    eulertourStart = knotenZahl; //
                }
                ungeradeKanteAnzahl++;
            }

            ausgabe = ausgabe + "Die Kantenanzahl des Knotens: " + knotenZahl + " beträgt " + kantenZaehler + "\n";
        }

        return ausgabe;

    }



    private boolean areAllFalse() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == true)
                    return false;
            }
        }
        return true;
    }

    public String eulerweg() {

        int i = eulertourStart;
        int j = 0;
        int temp = 0;
        String wegStr = " " + eulertourStart + " → "; //Durch Array ersetzen
        ArrayList<Integer> weg = new ArrayList<Integer>();


        while (areAllFalse() == false && j < this.matrix.length && wrongFigure == false) {//Spalten

            wrongFigureCounter++;

            if (matrix[i][j] == true) {

                weg.add(j);

                this.matrix[i][j] = false;
                this.matrix[j][i] = false;
                temp = i;
                i = j;
                if (temp == this.matrix.length - 1){
                    j = 0;
                }else{
                    j = temp + 1;
                }

            } else if (j == this.matrix.length - 1 && this.matrix[i][j] == false) {

                j = 0;

            } else if (j == temp && weg.size() != 0) {
                weg.remove(weg.size()-1);
                this.matrix[temp][i] = true;
                this.matrix[i][temp] = true;
                i = temp;
                j++;
            } else if (wrongFigureCounter > 1000){
                System.out.println(wrongFigureCounter);
                wrongFigure = true;
            }
            else {
                j++;
            }
        }
        for (int a = 0; a < weg.size(); a++){
            if (wrongFigure){
                return "";
            }
            if (a == weg.size()-1) {
                wegStr += weg.get(a) + " ";
            }
            else{
                wegStr += weg.get(a) + " → ";
            }
        }
        return wegStr;
    }

    public int hasEulertour() {

        if(areAllFalse() ==  true && wrongFigureCounter == 0){
            return 5;
        }

        if (ungeradeKanteAnzahl == 0) {
            if (wrongFigure == true)
                return 4;
            else
                return 1;
        } else if (ungeradeKanteAnzahl == 2) {
            halfEulertour = true; //Wird für eulerweg benutzt um einen Knoten mit ungeranden Knoten zu wählen
            if (wrongFigure == true)
                return 4;
            else
                return 2;
        }  else {
            if (wrongFigure == true)
                return 4;
            else
                return 3;
        }

    }


    public void delete() {

        matrix = graph.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = false;
                this.matrix[j][i] = false;
            }
        }

    }

}
