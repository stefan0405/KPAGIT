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
@Table(name = "sakt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sakt.findAll", query = "SELECT s FROM Sakt s")
    , @NamedQuery(name = "Sakt.findByIDSkat", query = "SELECT s FROM Sakt s WHERE s.iDSkat = :iDSkat")
    , @NamedQuery(name = "Sakt.findByCiscenjeOruzja", query = "SELECT s FROM Sakt s WHERE s.ciscenjeOruzja = :ciscenjeOruzja")
    , @NamedQuery(name = "Sakt.findByStrojevaObuka", query = "SELECT s FROM Sakt s WHERE s.strojevaObuka = :strojevaObuka")
    , @NamedQuery(name = "Sakt.findByRukovanjeSluzbenimOruzjem", query = "SELECT s FROM Sakt s WHERE s.rukovanjeSluzbenimOruzjem = :rukovanjeSluzbenimOruzjem")
    , @NamedQuery(name = "Sakt.findBySektorZaVanredneSituacije", query = "SELECT s FROM Sakt s WHERE s.sektorZaVanredneSituacije = :sektorZaVanredneSituacije")
    , @NamedQuery(name = "Sakt.findByTerenskaObukaGoc", query = "SELECT s FROM Sakt s WHERE s.terenskaObukaGoc = :terenskaObukaGoc")
    , @NamedQuery(name = "Sakt.findByNapomena", query = "SELECT s FROM Sakt s WHERE s.napomena = :napomena")
    , @NamedQuery(name = "Sakt.findByDatum", query = "SELECT s FROM Sakt s WHERE s.datum = :datum")})
public class Sakt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSkat")
    private Integer iDSkat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ciscenjeOruzja")
    private boolean ciscenjeOruzja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "strojevaObuka")
    private boolean strojevaObuka;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rukovanjeSluzbenimOruzjem")
    private boolean rukovanjeSluzbenimOruzjem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sektorZaVanredneSituacije")
    private boolean sektorZaVanredneSituacije;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "terenskaObukaGoc")
    private String terenskaObukaGoc;
    @Basic(optional = false)
    @Size(min = 0, max = 255)
    @Column(name = "napomena")
    private String napomena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "Datum")
    private String datum;
    @JoinColumn(name = "FKBrindeksa", referencedColumnName = "brindeksa")
    @ManyToOne(optional = false)
    private Student fKBrindeksa;
    @OneToMany(mappedBy = "fKSkat")
    private Collection<Personalizovanaobavestenja> personalizovanaobavestenjaCollection;

    public Sakt() {
    }

    public Sakt(Integer iDSkat) {
        this.iDSkat = iDSkat;
    }

    public Sakt(Integer iDSkat, boolean ciscenjeOruzja, boolean strojevaObuka, boolean rukovanjeSluzbenimOruzjem, boolean sektorZaVanredneSituacije, String terenskaObukaGoc, String napomena, String datum) {
        this.iDSkat = iDSkat;
        this.ciscenjeOruzja = ciscenjeOruzja;
        this.strojevaObuka = strojevaObuka;
        this.rukovanjeSluzbenimOruzjem = rukovanjeSluzbenimOruzjem;
        this.sektorZaVanredneSituacije = sektorZaVanredneSituacije;
        this.terenskaObukaGoc = terenskaObukaGoc;
        this.napomena = napomena;
        this.datum = datum;
    }

    public Integer getIDSkat() {
        return iDSkat;
    }

    public void setIDSkat(Integer iDSkat) {
        this.iDSkat = iDSkat;
    }

    public boolean getCiscenjeOruzja() {
        return ciscenjeOruzja;
    }

    public void setCiscenjeOruzja(boolean ciscenjeOruzja) {
        this.ciscenjeOruzja = ciscenjeOruzja;
    }

    public boolean getStrojevaObuka() {
        return strojevaObuka;
    }

    public void setStrojevaObuka(boolean strojevaObuka) {
        this.strojevaObuka = strojevaObuka;
    }

    public boolean getRukovanjeSluzbenimOruzjem() {
        return rukovanjeSluzbenimOruzjem;
    }

    public void setRukovanjeSluzbenimOruzjem(boolean rukovanjeSluzbenimOruzjem) {
        this.rukovanjeSluzbenimOruzjem = rukovanjeSluzbenimOruzjem;
    }

    public boolean getSektorZaVanredneSituacije() {
        return sektorZaVanredneSituacije;
    }

    public void setSektorZaVanredneSituacije(boolean sektorZaVanredneSituacije) {
        this.sektorZaVanredneSituacije = sektorZaVanredneSituacije;
    }

    public String getTerenskaObukaGoc() {
        return terenskaObukaGoc;
    }

    public void setTerenskaObukaGoc(String terenskaObukaGoc) {
        this.terenskaObukaGoc = terenskaObukaGoc;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
    @XmlTransient
    public Student getFKBrindeksa() {
        return fKBrindeksa;
    }

    public void setFKBrindeksa(Student fKBrindeksa) {
        this.fKBrindeksa = fKBrindeksa;
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
        hash += (iDSkat != null ? iDSkat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sakt)) {
            return false;
        }
        Sakt other = (Sakt) object;
        if ((this.iDSkat == null && other.iDSkat != null) || (this.iDSkat != null && !this.iDSkat.equals(other.iDSkat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Sakt[ iDSkat=" + iDSkat + " ]";
    }
    
}
