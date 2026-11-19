/**
 * Class SerieDeTV
 */
package Modelo;

// Subclase SerieDeTV que extiende de ContenidoAudiovisual
public class SerieDeTV extends ContenidoAudiovisual {
    private Temporada temporada;

    public SerieDeTV(String titulo, int duracionEnMinutos, String genero, int numeroTemp) {
        super(titulo, duracionEnMinutos, genero);
        this.temporada = new Temporada(numeroTemp);
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nDetalles de la serie de tv:");
        sb.append(super.toString());

        sb.append("\nTemporada: ").append(this.temporada.getNumeroTemp());
        
        return sb.toString();
    }
}