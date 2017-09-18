package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Deputado implements Serializable{

    @JsonProperty("id")
    private String id;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("siglaPartido")
    private String siglaPartido;

    @JsonProperty("uriPartido")
    private String uriPartido;

    @JsonProperty("siglaUf")
    private String siglaUf;

    @JsonProperty("idLegislatura")
    private String idLegislatura;

    @JsonProperty("urlFoto")
    private String urlFoto;

    public Deputado() { }

    public Deputado(String id, String uri, String nome, String siglaPartido, String uriPartido, String siglaUf, String idLegislatura, String urlFoto) {
        this.id = id;
        this.uri = uri;
        this.nome = nome;
        this.siglaPartido = siglaPartido;
        this.uriPartido = uriPartido;
        this.siglaUf = siglaUf;
        this.idLegislatura = idLegislatura;
        this.urlFoto = urlFoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public String getUriPartido() {
        return uriPartido;
    }

    public void setUriPartido(String uriPartido) {
        this.uriPartido = uriPartido;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public String getIdLegislatura() {
        return idLegislatura;
    }

    public void setIdLegislatura(String idLegislatura) {
        this.idLegislatura = idLegislatura;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + " Nome: " + this.getNome() + " Partido: " + this.getSiglaPartido();
    }

}
