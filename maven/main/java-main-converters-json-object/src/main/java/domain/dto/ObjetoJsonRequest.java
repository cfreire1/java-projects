package domain.dto;

import java.util.List;

public class ObjetoJsonRequest {

    private String rut;
    private String mensaje;
    private DatosPersonales datosPersonales;
    private List<Direccion> direcciones;

    public ObjetoJsonRequest (){
     // Default
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
}
