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
@Table(name = "spi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Spi.findAll", query = "SELECT s FROM Spi s")
    , @NamedQuery(name = "Spi.findByIDSpi", query = "SELECT s FROM Spi s WHERE s.iDSpi = :iDSpi")
    , @NamedQuery(name = "Spi.findByDuznost", query = "SELECT s FROM Spi s WHERE s.duznost = :duznost")
    , @NamedQuery(name = "Spi.findByRadnoVreme", query = "SELECT s FROM Spi s WHERE s.radnoVreme = :radnoVreme")
    , @NamedQuery(name = "Spi.findByDatum", query = "SELECT s FROM Spi s WHERE s.datum = :datum")})
public class Spi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSpi")
    private Integer iDSpi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "duznost")
    private String duznost;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "radnoVreme")
    private String radnoVreme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "Datum")
    private String datum;
    @JoinColumn(name = "FKBrIndeks", referencedColumnName = "brindeksa")
    @ManyToOne(optional = false)
    private Student fKBrIndeks;
    @JoinColumn(name = "FKOStalo", referencedColumnName = "IDOstalo")
    @OneToOne(cascade = {CascadeType.ALL})
    private Ostalospi fKOStalo;
    @OneToMany(mappedBy = "fKSpi")
    private Collection<Personalizovanaobavestenja> personalizovanaobavestenjaCollection;

    public Spi() {
    }

    public Spi(Integer iDSpi) {
        this.iDSpi = iDSpi;
    }

    public Spi(Integer iDSpi, String duznost, String radnoVreme, String datum) {
        this.iDSpi = iDSpi;
        this.duznost = duznost;
        this.radnoVreme = radnoVreme;
        this.datum = datum;
    }

    public Integer getIDSpi() {
        return iDSpi;
    }

    public void setIDSpi(Integer iDSpi) {
        this.iDSpi = iDSpi;
    }

    public String getDuznost() {
        return duznost;
    }

    public void setDuznost(String duznost) {
        this.duznost = duznost;
    }

    public String getRadnoVreme() {
        return radnoVreme;
    }

    public void setRadnoVreme(String radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
    
    @XmlTransient
    public Student getFKBrIndeks() {
        return fKBrIndeks;
    }

    public void setFKBrIndeks(Student fKBrIndeks) {
        this.fKBrIndeks = fKBrIndeks;
    }

    public Ostalospi getFKOStalo() {
        return fKOStalo;
    }

    public void setFKOStalo(Ostalospi fKOStalo) {
        this.fKOStalo = fKOStalo;
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
        hash += (iDSpi != null ? iDSpi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Spi)) {
            return false;
        }
        Spi other = (Spi) object;
        if ((this.iDSpi == null && other.iDSpi != null) || (this.iDSpi != null && !this.iDSpi.equals(other.iDSpi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Spi[ iDSpi=" + iDSpi + " ]";
    }
    
}
