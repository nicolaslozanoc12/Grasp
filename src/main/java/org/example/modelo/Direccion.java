package org.example.modelo;

public class Direccion {
    private int id;
    private final Municipio municipio;
    private final Departamento departamento;
    private final Pais pais;
    private final String calle;
    private final String carrera;
    private final String coordenada;
    private final String descripcion;

    public Direccion(Municipio municipio, Departamento departamento, Pais pais, String calle, String carrera, String coordenada, String descripcion) {
        this.municipio = municipio;
        this.departamento = departamento;
        this.pais = pais;
        this.calle = calle;
        this.carrera = carrera;
        this.coordenada = coordenada;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }
    public Municipio getMunicipio() {
        return municipio;
    }
    public Departamento getDepartamento() {
        return departamento;
    }
    public Pais getPais() {
        return pais;
    }
    public String getCalle() {
        return calle;
    }
    public String getCarrera() {
        return carrera;
    }
    public String getCoordenada() {
        return coordenada;
    }
    public String getDescripcion() {
        return descripcion;
    }
    @Override
    public String toString() {
        return
                "{ municipio=" + municipio.toString() +
                        ", departamento=" + departamento.toString() +
                        ", pais=" + pais.toString() +
                        ", calle='" + calle + '\'' +
                        ", carrera='" + carrera + '\'' +
                        ", coordenada='" + coordenada + '\'' +
                        ", descripcion='" + descripcion + '\'' +
                        '}';
    }

}
