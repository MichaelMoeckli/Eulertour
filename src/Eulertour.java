
public class Eulertour {

    private int kantenZaehler = 0;
    private int knotenZahl = 0;
    private Graph graph;
    private int ungeradeKanteAnzahl = 0;
    private boolean[][] matrix;
    private boolean halfEulertour = false;
    private int EulertourStart = 0;

    public Eulertour (Graph graph){

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
                if (ungeradeKanteAnzahl < 1){
                    EulertourStart = knotenZahl; //
                }
                ungeradeKanteAnzahl++;
            }

            ausgabe = ausgabe + "Die Kantenanzahl des Knotens: " + knotenZahl + " beträgt " + kantenZaehler + "\n";
        }

            return ausgabe;

    }

    public boolean hasEulertour() {
        if (ungeradeKanteAnzahl == 0) {
            System.out.println("Eulertour (Startpunkt = Endepunkt)");
            return true;
        }else if( ungeradeKanteAnzahl == 2){
            halfEulertour = true; //Wird für eulerweg benutzt um einen Knoten mit ungeranden Knoten zu wählen
            System.out.println("Halb-Eulertour (Startpunkt != Endpunkt)");
            return true;
        }
        else {
            System.out.println("Has no Eulertour");
            return false;
        }

    }

    private void printMatrix(){
        int i = 0;
        while (i< this.matrix.length){
            int j = 0;
            String row = "";
            while (j<this.matrix[i].length){
                row += this.matrix[i][j];
                j++;
            }
            i++;
            System.out.println(row);
        }
    }

    private boolean areAllFalse(){
        for (int i = 0; i < matrix.length; i++){
            for(int j = 0; j <matrix[i].length; j++) {
                if (matrix[i][j] == true) return false;
            }
        }
        return true;
    }

    public String eulerweg() {

        int i = EulertourStart;
        int j = 0;
        int temp = 0;
        String weg = "" + EulertourStart; //Durch Array ersetzen

        while (j < this.matrix.length) {//Spalten
            //if (weg != "0" && i == 0 && j == 0){ //Geht nur bei Eulerkreis -> STart ungleich Ende -> Start muss bei ungeraden Kantenanzahl sein
             if(areAllFalse()==true) {
                 return weg;
            }
            System.out.println(i + " " + j + " " + matrix[i][j]);
            if (matrix[i][j] == true) {

                weg += " " + j;

                this.matrix[i][j] = false;
                this.matrix[j][i] = false;
                temp = i;
                i = j;
                j = temp;

            }else if (j == this.matrix.length - 1 && this.matrix[i][j] == false) {

                j = 0;

            }else {

                j++;
            }
        }

        return weg;
    }

}
