package domain.dto;

public class Direccion {

    private String calle;
    private String numero;
    private String codigoPostal;
    private String regionId;
    private String ciudadId;
    private String comunaId;

    public Direccion() {
        //default
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(String ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getComunaId() {
        return comunaId;
    }

    public void setComunaId(String comunaId) {
        this.comunaId = comunaId;
    }
}
