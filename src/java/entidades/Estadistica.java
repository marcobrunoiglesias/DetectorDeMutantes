/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marco Iglesias
 */
@Entity
@Table(name = "estadistica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadistica.findAll", query = "SELECT e FROM Estadistica e")
    , @NamedQuery(name = "Estadistica.findById", query = "SELECT e FROM Estadistica e WHERE e.id = :id")
    , @NamedQuery(name = "Estadistica.findByCountMutantDna", query = "SELECT e FROM Estadistica e WHERE e.countMutantDna = :countMutantDna")
    , @NamedQuery(name = "Estadistica.findByCountHumanDna", query = "SELECT e FROM Estadistica e WHERE e.countHumanDna = :countHumanDna")})
public class Estadistica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "count_mutant_dna")
    private int countMutantDna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "count_human_dna")
    private int countHumanDna;

    public Estadistica() {
    }

    public Estadistica(Integer id) {
        this.id = id;
    }

    public Estadistica(Integer id, int countMutantDna, int countHumanDna) {
        this.id = id;
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(int countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public int getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(int countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadistica)) {
            return false;
        }
        Estadistica other = (Estadistica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Estadistica[ id=" + id + " ]";
    }
    
}
