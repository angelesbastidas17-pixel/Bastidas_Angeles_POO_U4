package controlador;

import ManejoArchivosCSV.ArchivosCSV;
import Modelo.*;
import java.io.*;
import java.util.ArrayList;

public class ControladorCA {
    
//    // poner en la vista
//    public void mostrarContenidos(){
//        if (contenidos.isEmpty()) {
//            System.out.println("No hay contenidos cargados en el sistema.");
//            return;
//        }
//        System.out.println("\n--- LISTA DE CONTENIDOS (" + contenidos.size() + ") ---");
//        for (ContenidoAudiovisual contenido : contenidos) {
//            System.out.println(contenido.toString());
//            System.out.println("--------------------------------");
//        }
//    }
    
    public ArrayList<ContenidoAudiovisual> llenarClasesModeloConCsv(){
        ArrayList<ContenidoAudiovisual> listaContenidos = new ArrayList<>();
        
        ArchivosCSV archivoCSV = new ArchivosCSV();
        try {
            // lectura del archivo csv
            ArrayList<String[]> contenidoDelCSV = archivoCSV.leerCSV();
            
            for(String[] linea : contenidoDelCSV){
                String tipo = linea[0];
                String titulo = linea[1];
                int duracion = Integer.parseInt(linea[2]);
                String genero = linea[3];
                String extra = linea[4];
                String relacionados = linea[5];

                switch(tipo){
                    case "PELICULA":
                        listaContenidos.add(crearPelicula(titulo, duracion, genero, extra, relacionados));
                        break;
                    case "SERIE":
                        listaContenidos.add(new SerieDeTV(titulo, duracion, genero, Integer.parseInt(extra)));
                        break;
                    case "DOCUMENTAL":
                        Investigador inv = relacionados.isEmpty() ? null : new Investigador(relacionados);
                        listaContenidos.add(new Documental(titulo, duracion, genero, extra, inv));
                        break;
                    case "PODCAST":
                        listaContenidos.add(crearPodcast(titulo, duracion, genero, extra, relacionados));
                        break;
                    case "VIDEOMUSICAL":
                        listaContenidos.add(new VideoMusical(titulo, duracion, genero, extra, relacionados));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return listaContenidos;
    }

    private Pelicula crearPelicula(String titulo, int duracion, String genero, String estudio, String actoresTexto) {
        Pelicula pelicula = new Pelicula(titulo, duracion, genero, estudio);
        if (!actoresTexto.isEmpty()) {
            for (String nombre : actoresTexto.split("\\|")) {
                if (!nombre.trim().isEmpty()) {
                    pelicula.agregarActor(new Actor(nombre.trim()));
                }
            }
        }
        return pelicula;
    }
        
    private Podcast crearPodcast(String titulo, int duracion, String genero, String extra, String invTexto) {
        String[] partes = extra.split(";");
        String anfitrion = partes[0].trim();
        int episodio = Integer.parseInt(partes[1].trim());
        Podcast podcast = new Podcast(titulo, duracion, genero, anfitrion, episodio);

        if (!invTexto.isEmpty()) {
            for (String nombre : invTexto.split("\\|")) {
                if (!nombre.trim().isEmpty()) {
                    podcast.agregarInvestigador(new Investigador(nombre.trim()));
                }
            }
        }
        return podcast;
    }  
    
    

}
