package dados.entidades;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EntregaMaterial {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private PrestadoraDeServico prestadora;
    private MateriaPrima materiais;
    private String dataEntrega;
    
    //Construtor vazio da JPA (OBRIGATÓRIO)
    public EntregaMaterial(){}
    
    //Construtor
    public EntregaMaterial(PrestadoraDeServico ps, MateriaPrima mp,String de){
        this.setPrestadora(ps);
        this.setMateriais(mp);
        this.setDataEntrega(de);
    }

    public Integer getId() {  return id;}
    public PrestadoraDeServico getPrestadora() {  return prestadora;}
    public MateriaPrima getMateriais() {  return materiais;}
    public String getDataEntrega() {  return dataEntrega;}

    
    public void setId(Integer id) {  this.id = id;}
    public void setPrestadora(PrestadoraDeServico prestadora) {  this.prestadora = prestadora;}
    public void setMateriais(MateriaPrima materiais) {  this.materiais = materiais;}
    public void setDataEntrega(String dataEntrega) {  this.dataEntrega = dataEntrega;}

    
    
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
        final EntregaMaterial other = (EntregaMaterial) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
