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
@Table(name = "administracija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administracija.findAll", query = "SELECT a FROM Administracija a")
    , @NamedQuery(name = "Administracija.findByIDAdmin", query = "SELECT a FROM Administracija a WHERE a.iDAdmin = :iDAdmin")
    , @NamedQuery(name = "Administracija.findByIme", query = "SELECT a FROM Administracija a WHERE a.ime = :ime")
    , @NamedQuery(name = "Administracija.findByPrezime", query = "SELECT a FROM Administracija a WHERE a.prezime = :prezime")})
public class Administracija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDAdmin")
    private Integer iDAdmin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "prezime")
    private String prezime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKAdmin")
    private Collection<Obavestenje> obavestenjeCollection;
    @JoinColumn(name = "FKUloga", referencedColumnName = "IDUloga")
    @ManyToOne(optional = false)
    private Uloga fKUloga;
    @JoinColumn(name = "FKLogin", referencedColumnName = "IDLog")
    @OneToOne(optional = false)
    private Login fKLogin;

    public Administracija() {
    }

    public Administracija(Integer iDAdmin) {
        this.iDAdmin = iDAdmin;
    }

    public Administracija(Integer iDAdmin, String ime, String prezime) {
        this.iDAdmin = iDAdmin;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Integer getIDAdmin() {
        return iDAdmin;
    }

    public void setIDAdmin(Integer iDAdmin) {
        this.iDAdmin = iDAdmin;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @XmlTransient
    public Collection<Obavestenje> getObavestenjeCollection() {
        return obavestenjeCollection;
    }

    public void setObavestenjeCollection(Collection<Obavestenje> obavestenjeCollection) {
        this.obavestenjeCollection = obavestenjeCollection;
    }

    public Uloga getFKUloga() {
        return fKUloga;
    }

    public void setFKUloga(Uloga fKUloga) {
        this.fKUloga = fKUloga;
    }

    public Login getFKLogin() {
        return fKLogin;
    }

    public void setFKLogin(Login fKLogin) {
        this.fKLogin = fKLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDAdmin != null ? iDAdmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administracija)) {
            return false;
        }
        Administracija other = (Administracija) object;
        if ((this.iDAdmin == null && other.iDAdmin != null) || (this.iDAdmin != null && !this.iDAdmin.equals(other.iDAdmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Administracija[ iDAdmin=" + iDAdmin + " ]";
    }
    
}
