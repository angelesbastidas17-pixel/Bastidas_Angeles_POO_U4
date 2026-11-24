package vista;

import Modelo.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaCA {
    
    Scanner scan = new Scanner(System.in);
    
    public int menu(){
        System.out.println("\nIngrese una opcion: ");
        System.out.println("1. Cargar datos desde el .csv");
        System.out.println("2. Mostrar datos cargados");
         System.out.println("3. Agregar contenido (Serie de TV)"); 
        System.out.println("4. Guardar estado en un archivo .csv");
        System.out.println("0. Salir");
        try{
            return Integer.parseInt(scan.nextLine());
        }catch(NumberFormatException e){
            return -1;
        }
    }
    
    public void mostrarDatos(ArrayList<ContenidoAudiovisual>contenidos){
        if (contenidos.isEmpty()) {
            System.out.println("No hay contenidos cargados en el sistema. Primero cargue por favor con la opcion correspondiente");
            return;
        }
        System.out.println("\nLISTA DE CONTENIDOS");
        for (ContenidoAudiovisual contenido : contenidos) {
            System.out.println(contenido.toString());
        }
    }
    
    public String leerTexto(String mensaje){
        System.out.println(mensaje);
        return scan.nextLine().trim();
    }
    
    public int leerEntero(String mensaje){
        while(true){
            System.out.println(mensaje);
            try{
                return Integer.parseInt(scan.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Ingrese un numero entero valido");
            }
        }
    }
    
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
}
