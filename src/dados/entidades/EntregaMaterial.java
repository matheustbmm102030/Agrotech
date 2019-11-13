package dados.entidades;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class EntregaMaterial {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional=false)
    private Equipe prestadora;
    
    @ManyToOne(optional=false)
    private Insumo insumo1;
    @ManyToOne(optional=false)
    private Insumo insumo2;
    @ManyToOne(optional=false)
    private Insumo insumo3;
    @ManyToOne(optional=false)
    private Insumo insumo4;
    @ManyToOne(optional=false)
    private Insumo insumo5;
    @ManyToOne(optional=false)
    private Insumo insumo6;
    @ManyToOne(optional=false)
    private Insumo insumo7;
    private String dataEntrega;
    
    //Construtor vazio da JPA (OBRIGATÓRIO)
    public EntregaMaterial(){}
    
    //Construtor
    public EntregaMaterial(Equipe ps, Insumo i1, Insumo i2, Insumo i3, Insumo i4,String de){
        this.setPrestadora(ps);
        this.setInsumo1(i1);
        this.setInsumo1(i2);
        this.setInsumo1(i3);
        this.setInsumo1(i4);
        this.setDataEntrega(de);
    }

    public void setId(Integer id) {  this.id = id;}
    public void setPrestadora(Equipe prestadora) {  this.prestadora = prestadora;}
    public void setInsumo1(Insumo insumo1) {  this.insumo1 = insumo1;}
    public void setInsumo2(Insumo insumo2) {  this.insumo2 = insumo2;}
    public void setInsumo3(Insumo insumo3) {  this.insumo3 = insumo3;}
    public void setInsumo4(Insumo insumo4) {  this.insumo4 = insumo4;}
    public void setDataEntrega(String dataEntrega) {  this.dataEntrega = dataEntrega;}

    public Integer getId() {  return id;}
    public Equipe getPrestadora() {  return prestadora;}
    public Insumo getInsumo1() {  return insumo1;}
    public Insumo getInsumo2() {  return insumo2;}
    public Insumo getInsumo3() {  return insumo3;}
    public Insumo getInsumo4() {  return insumo4;}
    public String getDataEntrega() {  return dataEntrega;}



    
    
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
