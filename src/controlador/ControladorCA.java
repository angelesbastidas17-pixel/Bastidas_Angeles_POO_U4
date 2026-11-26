package controlador;

import Modelo.*;
import java.util.ArrayList;
import vista.*;

public class ControladorCA {
    
    private ArrayList<ContenidoAudiovisual> contenidos = new ArrayList<>();
    ArchivosCSV archivoCSV = new ArchivosCSV();
    NuevoContenido nuevoContenido = new NuevoContenido();
    private VistaCA vista;

    public ControladorCA(VistaCA vista) {
        this.vista = vista;
    }

    public void iniciar(){
        int opcion;
        do{
            opcion = vista.menu();
            switch(opcion){
                case 1:
                    archivoCSV.llenarClasesModeloConCsv(contenidos);
                    break;
                case 2:
                    mostrarContenido();
                    break;
                case 3:
                    nuevoContenido.agregarSerieDeTV(contenidos);
                    break;
                case 4:
                    archivoCSV.guardarEnCsv(contenidos);
                    break;
                case 0:
                    break;
                default:
                    vista.mostrarMensaje("\nOpción no valida");
            }
        }while(opcion != 0);
    }
  
    public void mostrarContenido(){
        vista.mostrarDatos(contenidos);
    }

}
