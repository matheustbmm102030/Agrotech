package dados.entidades;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MateriaPrima {
    
    private Integer id;
    private String tipo;
    private Integer quantidade;
    
    //Construtor vazio da JPA (OBRIGATÓRIO)
    public MateriaPrima(){}
    
    //Construtor
    public MateriaPrima(String t, Integer q){
        this.setTipo(t);
        this.setQuantidade(q);
    }

    public Integer getId() {  return id;}
    public String getTipo() {  return tipo;}
    public Integer getQuantidade() {  return quantidade;}

    public void setId(Integer id) {  this.id = id;}
    public void setTipo(String tipo) {  this.tipo = tipo;}
    public void setQuantidade(Integer quantidade) {  this.quantidade = quantidade;}




    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MateriaPrima other = (MateriaPrima) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
