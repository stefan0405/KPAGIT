/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Denic
 */
@Entity
@Table(name = "ostalospi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ostalospi.findAll", query = "SELECT o FROM Ostalospi o")
    , @NamedQuery(name = "Ostalospi.findByIDOstalo", query = "SELECT o FROM Ostalospi o WHERE o.iDOstalo = :iDOstalo")
    , @NamedQuery(name = "Ostalospi.findByJaviose", query = "SELECT o FROM Ostalospi o WHERE o.javiose = :javiose")
    , @NamedQuery(name = "Ostalospi.findByZakasnjenje", query = "SELECT o FROM Ostalospi o WHERE o.zakasnjenje = :zakasnjenje")
    , @NamedQuery(name = "Ostalospi.findByIzostanak", query = "SELECT o FROM Ostalospi o WHERE o.izostanak = :izostanak")
    , @NamedQuery(name = "Ostalospi.findByOpravdanje", query = "SELECT o FROM Ostalospi o WHERE o.opravdanje = :opravdanje")
    , @NamedQuery(name = "Ostalospi.findByNapomena", query = "SELECT o FROM Ostalospi o WHERE o.napomena = :napomena")})
public class Ostalospi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDOstalo")
    private Integer iDOstalo;
    @Column(name = "javiose")
    private Boolean javiose;
    @Column(name = "zakasnjenje")
    private Boolean zakasnjenje;
    @Column(name = "izostanak")
    private Boolean izostanak;
    @Column(name = "opravdanje")
    private Boolean opravdanje;
    @Size(max = 255)
    @Column(name = "napomena")
    private String napomena;
    @OneToOne(mappedBy = "fKOStalo")
    private Spi spi;

    public Ostalospi() {
    }

    public Ostalospi(Integer iDOstalo) {
        this.iDOstalo = iDOstalo;
    }

    public Integer getIDOstalo() {
        return iDOstalo;
    }

    public void setIDOstalo(Integer iDOstalo) {
        this.iDOstalo = iDOstalo;
    }

    public Boolean getJaviose() {
        return javiose;
    }

    public void setJaviose(Boolean javiose) {
        this.javiose = javiose;
    }

    public Boolean getZakasnjenje() {
        return zakasnjenje;
    }

    public void setZakasnjenje(Boolean zakasnjenje) {
        this.zakasnjenje = zakasnjenje;
    }

    public Boolean getIzostanak() {
        return izostanak;
    }

    public void setIzostanak(Boolean izostanak) {
        this.izostanak = izostanak;
    }

    public Boolean getOpravdanje() {
        return opravdanje;
    }

    public void setOpravdanje(Boolean opravdanje) {
        this.opravdanje = opravdanje;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
    @XmlTransient
    public Spi getSpi() {
        return spi;
    }

    public void setSpi(Spi spi) {
        this.spi = spi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDOstalo != null ? iDOstalo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ostalospi)) {
            return false;
        }
        Ostalospi other = (Ostalospi) object;
        if ((this.iDOstalo == null && other.iDOstalo != null) || (this.iDOstalo != null && !this.iDOstalo.equals(other.iDOstalo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Ostalospi[ iDOstalo=" + iDOstalo + " ]";
    }
    
}
