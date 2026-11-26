package controlador;

import Modelo.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import vista.VistaCA;

public class ArchivosCSV {
    
    VistaCA vista = new VistaCA();
    
    String rutaArchivoAntes = "./contenido.csv";
    String rutaArchivoDespues = "./contenido_actualizado.csv";

    public String getRutaArchivoAntes() {
        return rutaArchivoAntes;
    }

    public String getRutaArchivoDespues() {
        return rutaArchivoDespues;
    }

    // se necesitan para las pruebas 
    public void setRutaArchivoAntes(String rutaArchivoAntes) {
        this.rutaArchivoAntes = rutaArchivoAntes;
    }

    public void setRutaArchivoDespues(String rutaArchivoDespues) {
        this.rutaArchivoDespues = rutaArchivoDespues;
    }
    
    public ArrayList<String[]> leerCSV() throws IOException{
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(rutaArchivoAntes), StandardCharsets.UTF_8))){
            
            String linea = br.readLine();
            if(linea == null) throw new IOException ("El archivo CSV esta vacio");
            
            ArrayList<String[]> lineasCSV = new ArrayList<>();
            while((linea = br.readLine()) != null){
                String [] palabras = linea.split(",",-1);
                lineasCSV.add(palabras);
            }
            return lineasCSV;
        }
    }
    
    public void escribirCSV(ArrayList<ContenidoAudiovisual> contenidos) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaArchivoDespues), StandardCharsets.UTF_8))){
            bw.write("TIPO,TITULO,DURACION,GENERO,ATRIBUTO_EXTRA,RELACIONADOS");
            bw.newLine();
            
            for(ContenidoAudiovisual contenido : contenidos){
                bw.write(contenido.convertirACsv());
                bw.newLine();
            }
        }
    }
    
    public ArrayList<ContenidoAudiovisual> llenarClasesModeloConCsv(ArrayList<ContenidoAudiovisual> contenidos){
        
        try {
            // lectura del archivo csv
            ArrayList<String[]> contenidoDelCSV = leerCSV();
            
            contenidos.clear();
            
            for(String[] linea : contenidoDelCSV){
                String tipo = linea[0];
                String titulo = linea[1];
                int duracion = Integer.parseInt(linea[2]);
                String genero = linea[3];
                String extra = linea[4];
                String relacionados = linea[5];

                switch(tipo){
                    case "PELICULA":
                        contenidos.add(crearobjetoPelicula(titulo, duracion, genero, extra, relacionados));
                        break;
                    case "SERIE":
                        contenidos.add(new SerieDeTV(titulo, duracion, genero, Integer.parseInt(extra)));
                        break;
                    case "DOCUMENTAL":
                        Investigador inv = relacionados.isEmpty() ? null : new Investigador(relacionados);
                        contenidos.add(new Documental(titulo, duracion, genero, extra, inv));
                        break;
                    case "PODCAST":
                        contenidos.add(crearobjetoPodcast(titulo, duracion, genero, extra, relacionados));
                        break;
                    case "VIDEOMUSICAL":
                        contenidos.add(new VideoMusical(titulo, duracion, genero, extra, relacionados));
                        break;
                }
            }
            
            vista.mostrarMensaje("Datos cargados exitosamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return contenidos;
    }

    private Pelicula crearobjetoPelicula(String titulo, int duracion, String genero, String estudio, String actoresTexto) {
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
        
    private Podcast crearobjetoPodcast(String titulo, int duracion, String genero, String extra, String invTexto) {
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
    
    public void guardarEnCsv(ArrayList<ContenidoAudiovisual> contenidos){
        if(contenidos.isEmpty()){
            vista.mostrarMensaje("No hay contenidos para guardar. Cargue o agregue datos primero");
            return;
        }
        try{
            escribirCSV(contenidos);
            vista.mostrarMensaje("Datos guardados en el CSV");
        } catch(IOException e){
            vista.mostrarMensaje("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
