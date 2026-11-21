package vista;

import java.util.Scanner;

public class VistaCA {
    
    Scanner scan = new Scanner(System.in);
    
    public int menu(){
        while(true){
            System.out.println("Ingrese una opcion: ");
            System.out.println("1. Ingresar datos desde el .csv");
            System.out.println("2. Mostrar datos cargados");
            // ingresar un dato nuevo de 1 sola cosa? pero voy a tener que crear de todos anyways, pero solo inicializar??
            System.out.println("3. Guardar estado en un archivo .csv");
            // 4. ¿Ejecutar pruebas unitarias?
            System.out.println("4. Salir");
        }
    }
}
