package test;

import controlador.ArchivosCSV;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class ArchivosCSVTest {
    
    @Rule
    public TemporaryFolder carpetaTemporal = new TemporaryFolder();
    
    private ArchivosCSV archivos;
    private Path entrada;
    
    @Before
    public void preparar() {
        archivos = new ArchivosCSV();
        entrada = new File(carpetaTemporal.getRoot(), "entrada.csv").toPath();
        archivos.setRutaArchivoAntes(entrada.toString());
    }
    
    @Test
    public void leerCSVConArchivoVacio() throws IOException{
        Files.write(entrada, new byte[0]);
        try{
            archivos.leerCSV();
            fail("Debió lanzar IOException");
        } catch(IOException e){
            assertTrue(e.getMessage().contains("vacio"));
        }
    }
    
    @Test
    public void leerCSVConArchivoInexistente(){
        assertThrows(IOException.class, () -> archivos.leerCSV());
    }
}
