/**
 * Class Documental
 */
package Modelo;

// Subclase Documental que extiende de ContenidoAudiovisual
public class Documental extends ContenidoAudiovisual {
    private String tema;
    private Investigador investigador;

    public Documental(String titulo, int duracionEnMinutos, String genero, String tema, Investigador investigador) {
        super(titulo, duracionEnMinutos, genero);
        this.tema = tema;
        this.investigador = investigador;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nDetalles del documental:");
        sb.append(super.toString());
        
        sb.append("\nTema: ").append(this.tema);
        sb.append("\nInvestigador: ").append(investigador.getNombre());
        
        return sb.toString();
    }
    
    @Override
    public String convertirACsv(){
        String nombreInv = "";
        if (investigador != null){
            nombreInv = investigador.getNombre();
        }
        return "DOCUMENTAL,"+super.convertirACsv()+","+tema+","+nombreInv;
    }
}