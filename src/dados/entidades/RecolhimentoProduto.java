package dados.entidades;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RecolhimentoProduto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Integer quantidade;
    
    @ManyToOne(optional=false)
    private Equipe prestadora;
    
    @ManyToOne(optional=false)
    private Produto produtos;

    private String dataEntrega;
    
    //Construtor vazio da JPA (OBRIGATÓRIO)
    public RecolhimentoProduto(){}
    
    //Construtor
    public RecolhimentoProduto(Integer qt, Equipe ps, Produto p,String de){
        this.setQuantidade(qt);
        this.setPrestadora(ps);
        this.setProdutos(p);
        this.setDataEntrega(de);
    }

    public Integer getId() {  return id;}
    public Equipe getPrestadora() {  return prestadora;}
    public Produto getProdutos() {  return produtos;}
    public String getDataEntrega() {  return dataEntrega;}
    public Integer getQuantidade() {return quantidade;}

    
    public void setId(Integer id) {  this.id = id;}
    public void setPrestadora(Equipe prestadora) {  this.prestadora = prestadora;}
    public void setProdutos(Produto produtos) {  this.produtos = produtos;}
    public void setDataEntrega(String dataEntrega) {  this.dataEntrega = dataEntrega;}
    public void setQuantidade(Integer quantidade) {this.quantidade = quantidade;}

    
    
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
        final RecolhimentoProduto other = (RecolhimentoProduto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
