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
@Table(name = "lokacija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lokacija.findAll", query = "SELECT l FROM Lokacija l")
    , @NamedQuery(name = "Lokacija.findByIDLokacije", query = "SELECT l FROM Lokacija l WHERE l.iDLokacije = :iDLokacije")
    , @NamedQuery(name = "Lokacija.findByNaziv", query = "SELECT l FROM Lokacija l WHERE l.naziv = :naziv")
    , @NamedQuery(name = "Lokacija.findByPolicijskaUprava", query = "SELECT l FROM Lokacija l WHERE l.policijskaUprava = :policijskaUprava")
    , @NamedQuery(name = "Lokacija.findByPolicijskaStanica", query = "SELECT l FROM Lokacija l WHERE l.policijskaStanica = :policijskaStanica")})
public class Lokacija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLokacije")
    private Integer iDLokacije;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "policijskaUprava")
    private String policijskaUprava;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "policijskaStanica")
    private String policijskaStanica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKLokacija")
    private Collection<Prakticnanastava> prakticnanastavaCollection;

    public Lokacija() {
    }

    public Lokacija(Integer iDLokacije) {
        this.iDLokacije = iDLokacije;
    }

    public Lokacija(Integer iDLokacije, String naziv, String policijskaUprava, String policijskaStanica) {
        this.iDLokacije = iDLokacije;
        this.naziv = naziv;
        this.policijskaUprava = policijskaUprava;
        this.policijskaStanica = policijskaStanica;
    }

    public Integer getIDLokacije() {
        return iDLokacije;
    }

    public void setIDLokacije(Integer iDLokacije) {
        this.iDLokacije = iDLokacije;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPolicijskaUprava() {
        return policijskaUprava;
    }

    public void setPolicijskaUprava(String policijskaUprava) {
        this.policijskaUprava = policijskaUprava;
    }

    public String getPolicijskaStanica() {
        return policijskaStanica;
    }

    public void setPolicijskaStanica(String policijskaStanica) {
        this.policijskaStanica = policijskaStanica;
    }

    @XmlTransient
    public Collection<Prakticnanastava> getPrakticnanastavaCollection() {
        return prakticnanastavaCollection;
    }

    public void setPrakticnanastavaCollection(Collection<Prakticnanastava> prakticnanastavaCollection) {
        this.prakticnanastavaCollection = prakticnanastavaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDLokacije != null ? iDLokacije.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lokacija)) {
            return false;
        }
        Lokacija other = (Lokacija) object;
        if ((this.iDLokacije == null && other.iDLokacije != null) || (this.iDLokacije != null && !this.iDLokacije.equals(other.iDLokacije))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Lokacija[ iDLokacije=" + iDLokacije + " ]";
    }
    
}
