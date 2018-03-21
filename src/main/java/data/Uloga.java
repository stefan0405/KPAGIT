/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "uloga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uloga.findAll", query = "SELECT u FROM Uloga u")
    , @NamedQuery(name = "Uloga.findByIDUloga", query = "SELECT u FROM Uloga u WHERE u.iDUloga = :iDUloga")
    , @NamedQuery(name = "Uloga.findByNaziv", query = "SELECT u FROM Uloga u WHERE u.naziv = :naziv")})
public class Uloga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUloga")
    private Integer iDUloga;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKUloga")
    private Collection<Administracija> administracijaCollection;

    public Uloga() {
    }

    public Uloga(Integer iDUloga) {
        this.iDUloga = iDUloga;
    }

    public Uloga(Integer iDUloga, String naziv) {
        this.iDUloga = iDUloga;
        this.naziv = naziv;
    }

    public Integer getIDUloga() {
        return iDUloga;
    }

    public void setIDUloga(Integer iDUloga) {
        this.iDUloga = iDUloga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public Collection<Administracija> getAdministracijaCollection() {
        return administracijaCollection;
    }

    public void setAdministracijaCollection(Collection<Administracija> administracijaCollection) {
        this.administracijaCollection = administracijaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDUloga != null ? iDUloga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uloga)) {
            return false;
        }
        Uloga other = (Uloga) object;
        if ((this.iDUloga == null && other.iDUloga != null) || (this.iDUloga != null && !this.iDUloga.equals(other.iDUloga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Uloga[ iDUloga=" + iDUloga + " ]";
    }
    
}
