package Modelo;

public class VideoMusical extends ContenidoAudiovisual{
    private String nombreArtista;
    private String discografica;

    public VideoMusical(String titulo, int duracionEnMinutos, String genero, String nombreArtista, String discografica) {
        super(titulo, duracionEnMinutos, genero);
        this.nombreArtista = nombreArtista;
        this.discografica = discografica;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getDiscografica() {
        return discografica;
    }

    public void setDiscografica(String discografica) {
        this.discografica = discografica;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nDetalles del video musical:");
        sb.append(super.toString());
        
        sb.append("\nNombre del artista: ").append(this.nombreArtista);
        sb.append("\nDiscográfica: ").append(this.discografica);
        
        return sb.toString();
    }
    
    @Override
    public String convertirACsv(){
        return "VIDEOMUSICAL," + super.convertirACsv() + "," + nombreArtista + "," + discografica;
    }
    
}
