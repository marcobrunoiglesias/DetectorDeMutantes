/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marco Iglesias
 */
@Entity
@XmlRootElement
public class Dna implements Serializable{
    private String[] dna;
    

    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    public Dna(Integer id) {
        this.id = id;
    }
    
    
    
    public Dna() {
    }

    public Dna(String[] dna) {
        this.dna = dna;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
