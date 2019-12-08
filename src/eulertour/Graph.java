package eulertour;

public class Graph {
	
	//Matrix
	 private boolean[][] matrix = null;
	 private int kantenZahl = 0;
	 
	  public Graph(int knotenZahl) {

	    if (knotenZahl < 0)
	    	throw new IllegalArgumentException ("Graph mit negativer Knotenanzahl kann nicht erstellt werden.");

	    // Adjazenzmatrix anlegen 
	    	this.matrix = new boolean[knotenZahl][knotenZahl];
	  }

	  public int getKantenZahl (){

	  		return this.kantenZahl;
	  }

	  public int getKnotenZahl() {

	  		return this.matrix.length;
	  }

	  public boolean[][] getMatrix(){

			return this.matrix;

	  }

	  /*Kante einf�gen, falls Knoten nicht existiert oder Kante schon vorhanden return nichts. 
	  Sonst erstelle Kante und KantenZahl ++ */
	  public void kanteEinfuegen(int quellKnoten, int zielKnoten)
	  {

	    if (quellKnoten >= this.matrix.length || zielKnoten >= this.matrix.length)
	    	return;

	    if (this.matrix[quellKnoten][zielKnoten] == false) {
	    	this.matrix[quellKnoten][zielKnoten] = true;
	    	this.kantenZahl++;
	      }
	    }
	  
	  //Graph als Matrix mit Nullen und Einsen darstellen
	  public String toString(){

		  String point = " ";
		  for (int i = 0; i < this.matrix.length; i++){
			  for (int j = 0; j < this.matrix[i].length; j++)
				  if (this.matrix[i][j] == true)
					  point = point + "1 ";
				  else
					  point = point +"0 ";
			  point = point+"\n ";
		  }
		  	return point;

	  }


}
