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
@Table(name = "smer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Smer.findAll", query = "SELECT s FROM Smer s")
    , @NamedQuery(name = "Smer.findByIDSmer", query = "SELECT s FROM Smer s WHERE s.iDSmer = :iDSmer")
    , @NamedQuery(name = "Smer.findByNaziv", query = "SELECT s FROM Smer s WHERE s.naziv = :naziv")})
public class Smer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDSmer")
    private Integer iDSmer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smer")
    private Collection<Student> studentCollection;

    public Smer() {
    }

    public Smer(Integer iDSmer) {
        this.iDSmer = iDSmer;
    }

    public Smer(Integer iDSmer, String naziv) {
        this.iDSmer = iDSmer;
        this.naziv = naziv;
    }

    public Integer getIDSmer() {
        return iDSmer;
    }

    public void setIDSmer(Integer iDSmer) {
        this.iDSmer = iDSmer;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDSmer != null ? iDSmer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Smer)) {
            return false;
        }
        Smer other = (Smer) object;
        if ((this.iDSmer == null && other.iDSmer != null) || (this.iDSmer != null && !this.iDSmer.equals(other.iDSmer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Smer[ iDSmer=" + iDSmer + " ]";
    }
    
}
