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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Denic
 */
@Entity
@Table(name = "personalizovanaobavestenja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personalizovanaobavestenja.findAll", query = "SELECT p FROM Personalizovanaobavestenja p")
    , @NamedQuery(name = "Personalizovanaobavestenja.findByIdPObavestenja", query = "SELECT p FROM Personalizovanaobavestenja p WHERE p.idPObavestenja = :idPObavestenja")})
public class Personalizovanaobavestenja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPObavestenja")
    private Integer idPObavestenja;
    @JoinColumn(name = "FKObavestenja", referencedColumnName = "IDObavestenje")
    @ManyToOne
    private Obavestenje fKObavestenja;
    @JoinColumn(name = "FKSpi", referencedColumnName = "IDSpi")
    @ManyToOne
    private Spi fKSpi;
    @JoinColumn(name = "FKPrakticnaNastava", referencedColumnName = "IDPrakticnaNastava")
    @ManyToOne
    private Prakticnanastava fKPrakticnaNastava;
    @JoinColumn(name = "FKSkat", referencedColumnName = "IDSkat")
    @ManyToOne
    private Sakt fKSkat;

    public Personalizovanaobavestenja() {
    }

    public Personalizovanaobavestenja(Integer idPObavestenja) {
        this.idPObavestenja = idPObavestenja;
    }

    public Integer getIdPObavestenja() {
        return idPObavestenja;
    }

    public void setIdPObavestenja(Integer idPObavestenja) {
        this.idPObavestenja = idPObavestenja;
    }

    public Obavestenje getFKObavestenja() {
        return fKObavestenja;
    }

    public void setFKObavestenja(Obavestenje fKObavestenja) {
        this.fKObavestenja = fKObavestenja;
    }

    public Spi getFKSpi() {
        return fKSpi;
    }

    public void setFKSpi(Spi fKSpi) {
        this.fKSpi = fKSpi;
    }

    public Prakticnanastava getFKPrakticnaNastava() {
        return fKPrakticnaNastava;
    }

    public void setFKPrakticnaNastava(Prakticnanastava fKPrakticnaNastava) {
        this.fKPrakticnaNastava = fKPrakticnaNastava;
    }

    public Sakt getFKSkat() {
        return fKSkat;
    }

    public void setFKSkat(Sakt fKSkat) {
        this.fKSkat = fKSkat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPObavestenja != null ? idPObavestenja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personalizovanaobavestenja)) {
            return false;
        }
        Personalizovanaobavestenja other = (Personalizovanaobavestenja) object;
        if ((this.idPObavestenja == null && other.idPObavestenja != null) || (this.idPObavestenja != null && !this.idPObavestenja.equals(other.idPObavestenja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Personalizovanaobavestenja[ idPObavestenja=" + idPObavestenja + " ]";
    }
    
}
