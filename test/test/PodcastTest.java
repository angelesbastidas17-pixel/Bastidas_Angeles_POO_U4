package test;

import Modelo.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PodcastTest {
    
    private Podcast podcast;
    
    @Before
    public void preparar() {
        podcast = new Podcast("The Wild Project", 213, "Science", "Jordi Wild", 11);
    }
    
    @Test
    public void convertirACsvSinInvestigadores(){
        assertEquals("PODCAST,The Wild Project,213,Science,Jordi Wild;11,",podcast.convertirACsv());
    }
    
    @Test
    public void convertirACsvConInvestigadores(){
        
        podcast.agregarInvestigador(new Investigador("Maria"));
        podcast.agregarInvestigador(new Investigador("Jose"));
        assertEquals("PODCAST,The Wild Project,213,Science,Jordi Wild;11,Maria|Jose",podcast.convertirACsv());
    }
}
