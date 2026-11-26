package test;

import Modelo.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class DocumentalTest {
    
    private Documental crearConInvestigador(){
        return new Documental("Cosmos", 45, "Science", "Astronomy", new Investigador("Carl Sagan"));
    }
    
    private Documental crearSinInvestigador(){
        return new Documental("Cosmos", 45, "Science", "Astronomy", null);
    }
    
    @Test
    public void convertirACsvIncluyeInvestigador(){
        assertEquals("DOCUMENTAL,Cosmos,45,Science,Astronomy,Carl Sagan",crearConInvestigador().convertirACsv());
    }
    
    @Test
    public void convertirACsvSinInvestigador(){
        assertEquals("DOCUMENTAL,Cosmos,45,Science,Astronomy,",crearSinInvestigador().convertirACsv());
    }
}
