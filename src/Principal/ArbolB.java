
package Principal;
/**
 *
 * @author User
 */
public class ArbolB {
    NodoArbolB root;
    int t;

    //Constructor
    public ArbolB(int t) {
        this.t = t;
        root = new NodoArbolB(t);
    }

    //Buscar la clave mayor
    public int buscarClaveMayor() {
        int claveMaxima = getClaveMayor(this.root);
        return claveMaxima;
    }

    private int getClaveMayor(NodoArbolB current) {
        //En caso de que el nodo actual sea vacio, se retorna 0
        if (current == null) {
            return 0;
        }
        
        //Mientras el nodo que se toma no sea hoja, current tomará el valor de su nodo hijo n
        while (!current.leaf) {
            current = current.child[current.n];
        }
        //se llama a la función claveMayorPorNodo para el nuevo valor de current
        return claveMayorPorNodo(current);
    }

    private int claveMayorPorNodo(NodoArbolB current) {
        //se retorna el valor de la clave en posición n-1 del nodo current
        return current.key[current.n - 1];
    }

    public void mostrarClavesNodoMinimo() {
        //Se crea una variable temporal que tomará el valor retornado de la función buscarNodoMinimo tomando como parámetro el nodo raíz delarbol.
        NodoArbolB temp = buscarNodoMinimo(root);
        //Si el valor temp retornado es nulo, se muestra el mensaje señalado.
        if (temp == null) {
            System.out.println("Sin minimo");
        } else {
        //Se imprime el nodo temp
            temp.imprimir();
        }
    }

    public NodoArbolB buscarNodoMinimo(NodoArbolB nodoActual) { //La función toma como parámetro un elemento de tipo Nodo
        
        //En caso de que la raíz sea nula, se retorna null
        if (root == null) {
            return null;
        }
        //Una variable auxiliar toma el valor de la raíz
        NodoArbolB aux = root;

        //Siempre que la raíz no sea hoja, el auxiliar tomará el valor del nodo hijo en la posición 0
        while (!aux.leaf) {
            aux = aux.child[0];
        }
        //Se retorna el valor de la variable auxilair
        return aux;
    }

    public void buscarNodoPorClave(int num) { //Definición del método buscarNodoPorClave, toma como parámetro un valor entero. 
        //Se llama a la función search y se almacena la información devuelta en la variable temp
        NodoArbolB temp = search(root, num);
   
        if (temp == null) {
            //En caso de que el valor de temp sea nulo se imprime el mensaje marcado
            System.out.println("No se ha encontrado un nodo con el valor ingresado");
        } else {
            //Por otro lado, se imprime eel nodo que duvuelve la función search almacenado en temp
            print(temp);
        }
    }

    //Búsqueda
    private NodoArbolB search(NodoArbolB actual, int key) { //Definción del metodo search, toma como parámetros un Nodo y la clave a buscar
        int i = 0;
        //Se realiza la busqueda mientras existan nodos por recorrer y la clave sea mayor a la clave anterior
        while (i < actual.n && key > actual.key[i]) { 
            i++;
        }

        //en caso de encontrar coincidencia con la clave se retorna el nodo actual
        if (i < actual.n && key == actual.key[i]) {
            return actual;
        }

        //si el nodo actual es hoja se retorna null
        if (actual.leaf) {
            return null;
        } else {
        //si el nodo actual no es hoja, se llama a la funcion de busqueda de forma recursiva para los nodos hijos
            return search(actual.child[i], key);
        }
    }

    //Inserción de valores
    public void insertar(int key) {
        NodoArbolB r = root;

        //Si el nodo está lleno lo debo separar
        if (r.n == ((2 * t) - 1)) {
            //Se llama a la función split que divide al nodo
            NodoArbolB s = new NodoArbolB(t);
            root = s;
            s.leaf = false;
            s.n = 0;
            s.child[0] = r;
            split(s, 0, r);
            nonFullInsert(s, key);
        } else {
            //En caso de no tener nodo llebo, se inserta la clave en su posición correspondiente
            nonFullInsert(r, key);
        }
    }
    
    //dividir 
    private void split(NodoArbolB x, int i, NodoArbolB y) {

        //Nodo auxiliar que será hijo
        NodoArbolB z = new NodoArbolB(t);
        z.leaf = y.leaf;
        z.n = (t - 1);

        //Se hace una copia de las últimas claves del nodo y al inicio del nodo z
        for (int j = 0; j < (t - 1); j++) {
            z.key[j] = y.key[(j + t)];
        }

        //Comprobar si no es hoja hay que reasignar los nodos hijo
        if (!y.leaf) {
            for (int k = 0; k < t; k++) {
                z.child[k] = y.child[(k + t)];
            }
        }

        //asigno el tamaño de y 
        y.n = (t - 1);                                                     
        //mover los hijos de x para darle espacio a z
        for (int j = x.n; j > i; j--) {
            x.child[(j + 1)] = x.child[j];
        }
        
        //reasignaciones
        x.child[(i + 1)] = z;                                   
        for (int j = x.n; j > i; j--) {
            x.key[(j + 1)] = x.key[j];
        }
        
        //Agregamos la clave situada en el valor medio
        x.key[i] = y.key[(t - 1)];                               
        x.n++;                                                   
    }

    private void nonFullInsert(NodoArbolB x, int key) {
        //Si es una hoja
        if (x.leaf) {
            int i = x.n; 
            //Se encuentra la posición correspondiente 
            while (i >= 1 && key < x.key[i - 1]) {
                //Las claves del nodo se desplazan una posición 
                x.key[i] = x.key[i - 1];
                i--;
            }
            //Se ingresa la clave en la posición correspondiente
            x.key[i] = key;
            x.n++; //El número de claves de una página aumenta en uno
        } else { //En caso de no ser hoja
            int j = 0;
            //Se debe cumplir la condición de los arboles B
            //Se recorre el arreglo de claves mientras existan elementos y hasta encontrar su posición 
            while (j < x.n && key > x.key[j]) {
                j++;
            }
            //Se debe cumplir la condición de los arboles B
            if (x.child[j].n == (2 * t - 1)) {
                //En caso de cumplirse, se llama a la función split para ingresar el elemento
                split(x, j, x.child[j]);
                //Se busca la posición nueva de la key
                if (key > x.key[j]) {
                    j++;
                }
            }
            //Se realiza la inserción en el nodo correspondiente
            nonFullInsert(x.child[j], key);
        }
    }

    public void showBTree() {
        print(root);//Llama a la función print tomando como parámetro la raíz del arbol
    }

    private void print(NodoArbolB n) {
        n.imprimir(); //Imprime los elementos del nodo
        if (!n.leaf) { //En caso de no ser hoja
            //Se realiza la impresión de los nodos hijos
            for (int j = 0; j <= n.n; j++) {
                if (n.child[j] != null) {
                    System.out.println(" ");
                    System.out.println("|");
                    print(n.child[j]);
                }
            }
        }
    }
}
