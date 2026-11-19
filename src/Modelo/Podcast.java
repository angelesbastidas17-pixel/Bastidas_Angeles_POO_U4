package Modelo;

import java.util.ArrayList;

public class Podcast extends ContenidoAudiovisual{
    private String anfitrion;
    private int numeroEpisodio;
    private ArrayList<Investigador> investigadores;
    
    public Podcast(String titulo, int duracionEnMinutos, String genero, String anfitrion, int numeroEpisodio){
        super(titulo,duracionEnMinutos, genero);
        this.anfitrion = anfitrion;
        this.numeroEpisodio = numeroEpisodio;
        this.investigadores = new ArrayList<>();
    }

    public String getAnfitrion() {
        return anfitrion;
    }

    public void setAnfitrion(String anfitrion) {
        this.anfitrion = anfitrion;
    }

    public int getNumeroEpisodios() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodios(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }
    
    public void agregarInvestigador(Investigador investigador){
        investigadores.add(investigador);
    }
    
    public ArrayList<Investigador> getInvestigadores(){
        return investigadores;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nDetalles del podcast:");
        sb.append(super.toString());
        
        sb.append("\nAnfitrión: ").append(this.anfitrion);
        sb.append("\nNumero de episodios: ").append(this.numeroEpisodio);
        sb.append("\nInvestigadores: ");
        
        if (investigadores.isEmpty()){
            sb.append("Aun no se ha agregado ninguno");
        }
        for(int i = 0; i<investigadores.size();i++){
            sb.append("\n").append(investigadores.get(i).getNombre());
        }
        
        return sb.toString();
    }
}
