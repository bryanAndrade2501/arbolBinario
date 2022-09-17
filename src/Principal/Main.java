
package Principal;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    int newval;
   public static void main(String[] args) {
        //grado minimo del Arbol B es t=3
        //Cada nodo soporta 2t hijos y 2t-1 claves
        Scanner entrada = new Scanner(System.in);

        int t = 3;
        //Se crea el arbol B segun t
        ArbolB arbolB = new ArbolB(t);
        
        //Valores a ingresar primera ronda
        int[] valoresUno = {25, 11, 5, 13, 129};
        System.out.println("Se insertan los siguientes valores: " );
        for(int i = 0; i < valoresUno.length; i++){
            System.out.print(valoresUno[i] + " ");
        }
        System.out.println();
        
        for(int i=0; i<valoresUno.length; i++) {
            arbolB.insertar(valoresUno[i]);
        }
        
        //Mostrando arbol B por pantalla en preorder
        System.out.println("Arbol B en Pre Order: ");
        arbolB.showBTree();
        System.out.println("");
        
        //Valores a ingresar segunda ronda
        System.out.println("Ingrese el Valor que desea Insetar: ");
        int newval = entrada.nextInt();
        arbolB.insertar(newval);
        
        //Mostrando arbol B por pantalla en preorder
        System.out.println("Arbol B en Pre Order: ");
        arbolB.showBTree();
        System.out.println("");
        
        //Valores a ingresar tercera ronda
        System.out.println("Ingrese el Valor que desea Insetar: ");
        newval = entrada.nextInt();
        arbolB.insertar(newval);//80
        System.out.println("Ingrese el Valor que desea Insetar: ");
        newval = entrada.nextInt();
        arbolB.insertar(newval);//70
        System.out.println("Ingrese el Valor que desea Insetar: ");
        newval = entrada.nextInt();
        arbolB.insertar(newval);//90
        //Mostrando arbol B por pantalla en preorder
        System.out.println("Arbol B en Pre Order: ");
        arbolB.showBTree();
        System.out.println("");

        //Buscar
        System.out.println("Ingrese el Valor que desea Buscar: ");
        int busqueda = entrada.nextInt();
        System.out.println("\nBuscando el nodo con el valor " + busqueda + " en el arbol B:");
        arbolB.buscarNodoPorClave(busqueda);
        
        //IMPLEMENTAR
        System.out.println("\nEl valor maximo del arbol B es : " + arbolB.buscarClaveMayor());
        
        System.out.print("El nodo minimo de la raiz del arbol B es :");
        arbolB.mostrarClavesNodoMinimo();
        
        System.out.println("");
        System.out.println("-- FIN --");
    }
}
