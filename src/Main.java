import java.sql.SQLSyntaxErrorException;

public class Main {

    public static void main(String[] args) {

        Graph g1 = new Graph(8);

        System.out.println ("Graph nach der Erstellung:");
        System.out.print (g1.toString());

        //Testdaten -> has eulertour
        //Zahlen müssen nebeneinander sein
        //Test 1
        //Haus von Nikolaus
/*
        g1.kanteEinfuegen (0,1);
        g1.kanteEinfuegen (0,2);
        g1.kanteEinfuegen (0,3);
        g1.kanteEinfuegen (0,4);

        g1.kanteEinfuegen (1,0);
        g1.kanteEinfuegen (1,2);

        g1.kanteEinfuegen (2,0);
        g1.kanteEinfuegen (2,1);
        g1.kanteEinfuegen (2,3);
        g1.kanteEinfuegen (2,4);

        g1.kanteEinfuegen (3,0);
        g1.kanteEinfuegen (3,2);
        g1.kanteEinfuegen (3,4);

        g1.kanteEinfuegen (4,0);
        g1.kanteEinfuegen (4,2);
        g1.kanteEinfuegen (4,3);
*/
        //Test3

        g1.kanteEinfuegen (0,4);
        g1.kanteEinfuegen (0,5);

        g1.kanteEinfuegen (1,5);
        g1.kanteEinfuegen (1,6);

        g1.kanteEinfuegen (2,6);
        g1.kanteEinfuegen (2,7);

        g1.kanteEinfuegen (3,4);
        g1.kanteEinfuegen (3,7);

        g1.kanteEinfuegen (4,0);
        g1.kanteEinfuegen (4,3);
        g1.kanteEinfuegen (4,5);
        g1.kanteEinfuegen (4,7);


        g1.kanteEinfuegen (5,0);
        g1.kanteEinfuegen (5,1);
        g1.kanteEinfuegen (5,4);
        g1.kanteEinfuegen (5,6);

        g1.kanteEinfuegen (6,1);
        g1.kanteEinfuegen (6,2);
        g1.kanteEinfuegen (6,5);
        g1.kanteEinfuegen (6,7);

        g1.kanteEinfuegen (7,2);
        g1.kanteEinfuegen (7,3);
        g1.kanteEinfuegen (7,4);
        g1.kanteEinfuegen (7,6);


        //Test 2
        /*
        g1.kanteEinfuegen (0,1);
        g1.kanteEinfuegen (0,2);
        g1.kanteEinfuegen (1,0);
        g1.kanteEinfuegen (1,2);
        g1.kanteEinfuegen (2,0);
        g1.kanteEinfuegen (2,1);
        g1.kanteEinfuegen (2,3);
        g1.kanteEinfuegen (2,4);
        g1.kanteEinfuegen (3,2);
        g1.kanteEinfuegen (3,4);
        g1.kanteEinfuegen (4,3);
        g1.kanteEinfuegen (4,2);
        */

        System.out.println ("");
        System.out.println ("Graph nach dem einf�gen einiger Kanten:");
        System.out.print (g1.toString());

        System.out.println ("");
        System.out.println ("Graph besitzt " + g1.getKantenZahl() + " Kanten");
        System.out.println ("Graph besitzt " + g1.getKnotenZahl() + " Knoten");

        Eulertour eulertour = new Eulertour(g1);

        System.out.println(eulertour.kantenAnzahl());


        if(eulertour.hasEulertour()==true){
            System.out.println("Weg " + eulertour.eulerweg());
        }else {
            System.out.println("Has no Eulertour");
        }
        System.out.print (g1.toString());
    }
}
