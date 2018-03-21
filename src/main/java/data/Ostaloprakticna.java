/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Denic
 */
@Entity
@Table(name = "ostaloprakticna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ostaloprakticna.findAll", query = "SELECT o FROM Ostaloprakticna o")
    , @NamedQuery(name = "Ostaloprakticna.findByIDPrakticnaNastava", query = "SELECT o FROM Ostaloprakticna o WHERE o.iDPrakticnaNastava = :iDPrakticnaNastava")
    , @NamedQuery(name = "Ostaloprakticna.findByIzostanak", query = "SELECT o FROM Ostaloprakticna o WHERE o.izostanak = :izostanak")
    , @NamedQuery(name = "Ostaloprakticna.findByPrekorednoDezurstvo", query = "SELECT o FROM Ostaloprakticna o WHERE o.prekorednoDezurstvo = :prekorednoDezurstvo")
    , @NamedQuery(name = "Ostaloprakticna.findByOpravdanje", query = "SELECT o FROM Ostaloprakticna o WHERE o.opravdanje = :opravdanje")
    , @NamedQuery(name = "Ostaloprakticna.findByNapomena", query = "SELECT o FROM Ostaloprakticna o WHERE o.napomena = :napomena")})
public class Ostaloprakticna implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPrakticnaNastava")
    private Integer iDPrakticnaNastava;
    @Basic(optional = false)
    @NotNull
    @Column(name = "izostanak")
    private boolean izostanak;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prekorednoDezurstvo")
    private boolean prekorednoDezurstvo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "opravdanje")
    private boolean opravdanje;
    @Basic(optional = false)
    @Size(min = 0, max = 255)
    @Column(name = "napomena")
    private String napomena;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fKOstaloPrakticna")
    private Prakticnanastava prakticnanastava;

    public Ostaloprakticna() {
    }

    public Ostaloprakticna(Integer iDPrakticnaNastava) {
        this.iDPrakticnaNastava = iDPrakticnaNastava;
    }

    public Ostaloprakticna(Integer iDPrakticnaNastava, boolean izostanak, boolean prekorednoDezurstvo, boolean opravdanje, String napomena) {
        this.iDPrakticnaNastava = iDPrakticnaNastava;
        this.izostanak = izostanak;
        this.prekorednoDezurstvo = prekorednoDezurstvo;
        this.opravdanje = opravdanje;
        this.napomena = napomena;
    }

    public Integer getIDPrakticnaNastava() {
        return iDPrakticnaNastava;
    }

    public void setIDPrakticnaNastava(Integer iDPrakticnaNastava) {
        this.iDPrakticnaNastava = iDPrakticnaNastava;
    }

    public boolean getIzostanak() {
        return izostanak;
    }

    public void setIzostanak(boolean izostanak) {
        this.izostanak = izostanak;
    }

    public boolean getPrekorednoDezurstvo() {
        return prekorednoDezurstvo;
    }

    public void setPrekorednoDezurstvo(boolean prekorednoDezurstvo) {
        this.prekorednoDezurstvo = prekorednoDezurstvo;
    }

    public boolean getOpravdanje() {
        return opravdanje;
    }

    public void setOpravdanje(boolean opravdanje) {
        this.opravdanje = opravdanje;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
    @XmlTransient
    public Prakticnanastava getPrakticnanastava() {
        return prakticnanastava;
    }

    public void setPrakticnanastava(Prakticnanastava prakticnanastava) {
        this.prakticnanastava = prakticnanastava;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPrakticnaNastava != null ? iDPrakticnaNastava.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ostaloprakticna)) {
            return false;
        }
        Ostaloprakticna other = (Ostaloprakticna) object;
        if ((this.iDPrakticnaNastava == null && other.iDPrakticnaNastava != null) || (this.iDPrakticnaNastava != null && !this.iDPrakticnaNastava.equals(other.iDPrakticnaNastava))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Ostaloprakticna[ iDPrakticnaNastava=" + iDPrakticnaNastava + " ]";
    }
    
}
