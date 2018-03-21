/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "obavestenje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Obavestenje.findAll", query = "SELECT o FROM Obavestenje o")
    , @NamedQuery(name = "Obavestenje.findByIDObavestenje", query = "SELECT o FROM Obavestenje o WHERE o.iDObavestenje = :iDObavestenje")
    , @NamedQuery(name = "Obavestenje.findByNaziv", query = "SELECT o FROM Obavestenje o WHERE o.naziv = :naziv")
    , @NamedQuery(name = "Obavestenje.findByDatum", query = "SELECT o FROM Obavestenje o WHERE o.datum = :datum")
    , @NamedQuery(name = "Obavestenje.findByKomentar", query = "SELECT o FROM Obavestenje o WHERE o.komentar = :komentar")})
public class Obavestenje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDObavestenje")
    private Integer iDObavestenje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "datum")
    private String datum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "komentar")
    private String komentar;
    @JoinColumn(name = "FKAdmin", referencedColumnName = "IDAdmin")
    @ManyToOne(optional = true)
    private Administracija fKAdmin;
    @OneToMany(mappedBy = "fKObavestenja")
    private Collection<Personalizovanaobavestenja> personalizovanaobavestenjaCollection;

    public Obavestenje() {
    }

    public Obavestenje(Integer iDObavestenje) {
        this.iDObavestenje = iDObavestenje;
    }

    public Obavestenje(Integer iDObavestenje, String naziv, String datum, String komentar) {
        this.iDObavestenje = iDObavestenje;
        this.naziv = naziv;
        this.datum = datum;
        this.komentar = komentar;
    }

    public Integer getIDObavestenje() {
        return iDObavestenje;
    }

    public void setIDObavestenje(Integer iDObavestenje) {
        this.iDObavestenje = iDObavestenje;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
    @XmlTransient
    public Administracija getFKAdmin() {
        return fKAdmin;
    }

    public void setFKAdmin(Administracija fKAdmin) {
        this.fKAdmin = fKAdmin;
    }

    @XmlTransient
    public Collection<Personalizovanaobavestenja> getPersonalizovanaobavestenjaCollection() {
        return personalizovanaobavestenjaCollection;
    }

    public void setPersonalizovanaobavestenjaCollection(Collection<Personalizovanaobavestenja> personalizovanaobavestenjaCollection) {
        this.personalizovanaobavestenjaCollection = personalizovanaobavestenjaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDObavestenje != null ? iDObavestenje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obavestenje)) {
            return false;
        }
        Obavestenje other = (Obavestenje) object;
        if ((this.iDObavestenje == null && other.iDObavestenje != null) || (this.iDObavestenje != null && !this.iDObavestenje.equals(other.iDObavestenje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Obavestenje[ iDObavestenje=" + iDObavestenje + " ]";
    }
    
}
