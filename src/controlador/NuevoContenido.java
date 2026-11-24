package controlador;

import Modelo.*;
import java.util.ArrayList;
import vista.*;

public class NuevoContenido {
    
    VistaCA vista = new VistaCA();
    public void agregarSerieDeTV(ArrayList<ContenidoAudiovisual> contenidos){
        String titulo = vista.leerTexto("Título de la serie: ");
        int duracion = vista.leerEntero("Duración en minutos: ");
        String genero = vista.leerTexto("Género: ");
        int numeroTemp = vista.leerEntero("Número de temporadas: ");

        SerieDeTV nuevaSerie = new SerieDeTV(titulo, duracion, genero, numeroTemp);
        contenidos.add(nuevaSerie);
        vista.mostrarMensaje("Serie agregada correctamente");
    }
}
