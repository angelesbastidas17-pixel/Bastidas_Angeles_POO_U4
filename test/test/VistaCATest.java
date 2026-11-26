package test;

import java.io.ByteArrayInputStream;
import org.junit.Test;
import static org.junit.Assert.*;
import vista.*;

public class VistaCATest {
    
    private VistaCA vistaConEntrada(String texto){
        System.setIn(new ByteArrayInputStream(texto.getBytes()));
        return new VistaCA();
    }
    
    @Test
    public void leerNumero(){
        VistaCA vista = vistaConEntrada("abc\n\n3.5\n7\n");
        int resultado = vista.leerEntero("Ingrese un numero: ");
        assertEquals(7,resultado);
    }
}
