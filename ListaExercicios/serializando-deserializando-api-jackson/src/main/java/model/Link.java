package model;

public class Link {

    String rel;
    String href;

    public Link() { }

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