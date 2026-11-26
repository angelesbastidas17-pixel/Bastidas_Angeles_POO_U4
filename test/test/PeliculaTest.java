package test;

import Modelo.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliculaTest {
    
    Pelicula pelicula;
    
    @Before
    public void preparar() {
        pelicula = new Pelicula("Avatar",125,"Acción","20th Century Studios");
    }
    
    @Test
    public void convertirACsvSinActores(){
        assertEquals("PELICULA,Avatar,125,Acción,20th Century Studios,",pelicula.convertirACsv());
    }
    
    @Test
    public void convertirACsvConActores(){
        pelicula.agregarActor(new Actor("Lucas P"));
        pelicula.agregarActor(new Actor("Lola C"));
        
        assertEquals("PELICULA,Avatar,125,Acción,20th Century Studios,Lucas P|Lola C",pelicula.convertirACsv());
    }
}
