package ManejoArchivosCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ArchivosCSV {
    
    public ArrayList<String[]> leerCSV() throws IOException{
        File file = new File("./contenido.csv");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
            
            String linea = br.readLine();
            if(linea == null) throw new IOException ("El archivo CSV está vacío");
            
            ArrayList<String[]> lineasCSV = new ArrayList<>();
            while((linea = br.readLine()) != null){
                String [] palabras = linea.split(",",-1);
                lineasCSV.add(palabras);
            }
            return lineasCSV;
        }
    }
}
