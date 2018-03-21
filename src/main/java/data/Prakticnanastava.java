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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "prakticnanastava")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prakticnanastava.findAll", query = "SELECT p FROM Prakticnanastava p")
    , @NamedQuery(name = "Prakticnanastava.findByIDPrakticnaNastava", query = "SELECT p FROM Prakticnanastava p WHERE p.iDPrakticnaNastava = :iDPrakticnaNastava")
    , @NamedQuery(name = "Prakticnanastava.findByDatum", query = "SELECT p FROM Prakticnanastava p WHERE p.datum = :datum")})
public class Prakticnanastava implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPrakticnaNastava")
    private Integer iDPrakticnaNastava;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "Datum")
    private String datum;
    @OneToMany(mappedBy = "fKPrakticnaNastava")
    private Collection<Personalizovanaobavestenja> personalizovanaobavestenjaCollection;
    @JoinColumn(name = "FKLokacija", referencedColumnName = "IDLokacije")
    @ManyToOne(optional = false)
    private Lokacija fKLokacija;
    @JoinColumn(name = "FKBrIndeks", referencedColumnName = "brindeksa")
    @ManyToOne(optional = false)
    private Student fKBrIndeks;
    @JoinColumn(name = "FKOstaloPrakticna", referencedColumnName = "IDPrakticnaNastava")
    @OneToOne(cascade = {CascadeType.ALL})
    private Ostaloprakticna fKOstaloPrakticna;

    public Prakticnanastava() {
    }

    public Prakticnanastava(Integer iDPrakticnaNastava) {
        this.iDPrakticnaNastava = iDPrakticnaNastava;
    }

    public Prakticnanastava(Integer iDPrakticnaNastava, String datum) {
        this.iDPrakticnaNastava = iDPrakticnaNastava;
        this.datum = datum;
    }

    public Integer getIDPrakticnaNastava() {
        return iDPrakticnaNastava;
    }

    public void setIDPrakticnaNastava(Integer iDPrakticnaNastava) {
        this.iDPrakticnaNastava = iDPrakticnaNastava;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    @XmlTransient
    public Collection<Personalizovanaobavestenja> getPersonalizovanaobavestenjaCollection() {
        return personalizovanaobavestenjaCollection;
    }

    public void setPersonalizovanaobavestenjaCollection(Collection<Personalizovanaobavestenja> personalizovanaobavestenjaCollection) {
        this.personalizovanaobavestenjaCollection = personalizovanaobavestenjaCollection;
    }

    public Lokacija getFKLokacija() {
        return fKLokacija;
    }

    public void setFKLokacija(Lokacija fKLokacija) {
        this.fKLokacija = fKLokacija;
    }
    @XmlTransient
    public Student getFKBrIndeks() {
        return fKBrIndeks;
    }

    public void setFKBrIndeks(Student fKBrIndeks) {
        this.fKBrIndeks = fKBrIndeks;
    }

    public Ostaloprakticna getFKOstaloPrakticna() {
        return fKOstaloPrakticna;
    }

    public void setFKOstaloPrakticna(Ostaloprakticna fKOstaloPrakticna) {
        this.fKOstaloPrakticna = fKOstaloPrakticna;
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
        if (!(object instanceof Prakticnanastava)) {
            return false;
        }
        Prakticnanastava other = (Prakticnanastava) object;
        if ((this.iDPrakticnaNastava == null && other.iDPrakticnaNastava != null) || (this.iDPrakticnaNastava != null && !this.iDPrakticnaNastava.equals(other.iDPrakticnaNastava))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Prakticnanastava[ iDPrakticnaNastava=" + iDPrakticnaNastava + " ]";
    }
    
}
