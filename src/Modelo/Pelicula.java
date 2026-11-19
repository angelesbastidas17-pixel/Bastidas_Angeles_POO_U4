/**
 * Class Pelicula
 */
package Modelo;

// Subclase Pelicula que extiende de ContenidoAudiovisual

import java.util.ArrayList;

public class Pelicula extends ContenidoAudiovisual {
    private String estudio;
    private ArrayList<Actor> actores;

    public Pelicula(String titulo, int duracionEnMinutos, String genero, String estudio) {
        super(titulo, duracionEnMinutos, genero);
        this.estudio = estudio;
        this.actores = new ArrayList<>();
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
    
    public void agregarActor(Actor actor){
        actores.add(actor);
    }
    
    public ArrayList<Actor> getActores(){
        return actores;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nDetalles de la película:");
        sb.append(super.toString()); //trae el resultado del metodo de la superclase que tiene los 4 primeros atributos heredados.
        
        sb.append("\nEstudio: ").append(estudio);
        sb.append("\nActores: ");
        
        if (actores.isEmpty()){
            sb.append("Aun no se ha agregado ninguno");
        }
        for(int i = 0; i<actores.size();i++){
            sb.append("\n").append(actores.get(i).getNombre());
        }
        
        return sb.toString();
    }
}