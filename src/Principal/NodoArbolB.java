
package Principal;

/**
 *
 * @author User
 */
public class NodoArbolB {
    int n; //Numero de claves de la página
    boolean leaf; //saber si el nodo es hoja
    int key[];  //almacenar la clave en el nodo
    NodoArbolB child[]; //arreglo hace referencia a los hijos

    //Constructores
    public NodoArbolB(int t) {
        n = 0;
        leaf = true;
        key = new int[((2 * t) - 1)];
        child = new NodoArbolB[(2 * t)];
    }

    //Función para la impresión
    public void imprimir() {
        System.out.print("[");
        //Para cada una de las claves  de la página:
        for (int i = 0; i < n; i++) {
            //Se imprimen las claves del arbol mientras el índice sea menor al numero de claves - 1 acompañado de unespacio
            if (i < n - 1) {
                System.out.print(key[i] + " ");
            } else {
                // Para la última clave del nodo unicamente se imprime el valor, sin el espacio
                System.out.print(key[i]);
            }
        }
        System.out.print("]");
    }
    
    //Busqueda en un nodo
    public int find(int k) {
        //Para cada clave del nodo se busca la coincidencia con la clave k. En caso de encontrarlo se retorna la clave del indice i del nodo
        for (int i = 0; i < n; i++) {
            if (key[i] == k) {
                return i;
            }
        }
        //Se retorna -1 cuando no se encuentra coincidencias
        return -1;
    }
}
