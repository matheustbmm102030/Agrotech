package dados.entidades;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Insumo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    private Integer quantidade;
    
    //Construtor vazio da JPA (OBRIGATÓRIO)
    public Insumo(){}
    
    //Construtor
    public Insumo(String t, Integer q){
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
        final Insumo other = (Insumo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public String toString(){
        return this.getTipo();
    }
    
}
