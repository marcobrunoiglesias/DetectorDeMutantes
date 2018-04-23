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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marco Iglesias
 */
@Entity
@Table(name = "secuencias_adn_procesadas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SecuenciasAdnProcesadas.findAll", query = "SELECT s FROM SecuenciasAdnProcesadas s")
    , @NamedQuery(name = "SecuenciasAdnProcesadas.findById", query = "SELECT s FROM SecuenciasAdnProcesadas s WHERE s.id = :id")
    , @NamedQuery(name = "SecuenciasAdnProcesadas.findByEsMutante", query = "SELECT s FROM SecuenciasAdnProcesadas s WHERE s.esMutante = :esMutante")
    , @NamedQuery(name = "SecuenciasAdnProcesadas.findBySecuencia", query = "SELECT s FROM SecuenciasAdnProcesadas s WHERE s.secuencia = :secuencia")})
public class SecuenciasAdnProcesadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_mutante")
    private boolean esMutante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2500)
    @Column(name = "secuencia")
    private String secuencia;

    public SecuenciasAdnProcesadas() {
    }

    public SecuenciasAdnProcesadas(Integer id) {
        this.id = id;
    }

    public SecuenciasAdnProcesadas(Integer id, boolean esMutante, String secuencia) {
        this.id = id;
        this.esMutante = esMutante;
        this.secuencia = secuencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getEsMutante() {
        return esMutante;
    }

    public void setEsMutante(boolean esMutante) {
        this.esMutante = esMutante;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
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
        if (!(object instanceof SecuenciasAdnProcesadas)) {
            return false;
        }
        SecuenciasAdnProcesadas other = (SecuenciasAdnProcesadas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SecuenciasAdnProcesadas[ id=" + id + " ]";
    }
    
}
