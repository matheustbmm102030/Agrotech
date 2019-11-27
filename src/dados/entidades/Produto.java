package dados.entidades;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer quantidade;
    private double custoProducao;
    
    //Construtor vazio da JPA (OBRIGATÓRIO)
    public Produto(){}
    
    //Construtor
    public Produto(String n, Integer q, double cp){
        this.setNome(n);
        this.setCustoProducao(cp);
        this.setQuantidade(q);
    }

    public Integer getId() {  return id;}
    public String getNome() {  return nome;}
    public double getCustoProducao() {  return custoProducao;}
    public Integer getQuantidade() {  return quantidade;}

    public void setId(Integer id) {  this.id = id;}
    public void setNome(String nome) {  this.nome = nome;}
    public void setCustoProducao(double custoProducao) {  this.custoProducao = custoProducao;}
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    public String toString(){
        return this.getNome();
    }
}
