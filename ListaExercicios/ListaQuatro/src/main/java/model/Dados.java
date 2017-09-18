package model;

import sun.awt.image.ImageWatched;

import java.io.Serializable;
import java.util.List;

public class Dados implements Serializable {

    List<Deputado> dados;
    List<Link> links;

    public List<Deputado> getDados() {
        return dados;
    }

    public void setDados(List<Deputado> dados) {
        this.dados = dados;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public class Link {

        String rel;
        String href;

        public String getRel(){
            return rel;
        }
        public void setRel(String rel){
            this.rel = rel;
        }
        public String getHref(){
            return href;
        }
        public void setHref(String href){
            this.href = href;
        }

    }
}
