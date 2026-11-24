package vista;

import controlador.ControladorCA;

public class Main {

    public static void main(String[] args) {

        VistaCA vista = new VistaCA();
        ControladorCA controlador = new ControladorCA(vista);

        controlador.iniciar();
    }
    
}
