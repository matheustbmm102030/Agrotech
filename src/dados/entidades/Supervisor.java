package dados.entidades;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supervisor {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nome, endereco, telefone,rg,cpf,dataNasc;
    
    //Construtor vazio da JPA (OBRIGATÓRIO)
    public Supervisor(){}
    
    //Construtor
    public Supervisor(String n,String e,String t,String rgs,String cpfs,String dn){
        this.setNome(n);
        this.setEndereco(e);
        this.setTelefone(t);
        this.setRg(rgs);
        this.setCpf(cpfs);
        this.setDataNasc(dn);
    }

    public Integer getId() {  return id;}
    public String getNome() {  return nome;}
    public String getEndereco(){  return endereco;}    
    public String getTelefone() {  return telefone;}
    public String getRg() {  return rg;}
    public String getCpf() {  return cpf;}
    public String getDataNasc() {  return dataNasc;}
    
    public void setId(Integer id) {  this.id = id;}
    public void setNome(String nome) {  this.nome = nome;}
    public void setEndereco(String endereco) {  this.endereco = endereco;}
    public void setTelefone(String telefone) {  this.telefone = telefone;}
    public void setRg(String rg) {  this.rg = rg;}
    public void setCpf(String cpf) {  this.cpf = cpf;}
    public void setDataNasc(String dataNasc) {  this.dataNasc = dataNasc;}
    
    public String toString(){
    return nome;
    }
    
    
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
        final Supervisor other = (Supervisor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
