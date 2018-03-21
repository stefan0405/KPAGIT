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
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l")
    , @NamedQuery(name = "Login.findByIDLog", query = "SELECT l FROM Login l WHERE l.iDLog = :iDLog")
    , @NamedQuery(name = "Login.findByKorIme", query = "SELECT l FROM Login l WHERE l.korIme = :korIme")
    , @NamedQuery(name = "Login.findByLozinka", query = "SELECT l FROM Login l WHERE l.lozinka = :lozinka")})
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLog")
    private Integer iDLog;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "korIme")
    private String korIme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lozinka")
    private String lozinka;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fKLog")
    private Student student;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fKLogin")
    private Administracija administracija;

    public Login() {
    }

    public Login(Integer iDLog) {
        this.iDLog = iDLog;
    }

    public Login(Integer iDLog, String korIme, String lozinka) {
        this.iDLog = iDLog;
        this.korIme = korIme;
        this.lozinka = lozinka;
    }

    public Integer getIDLog() {
        return iDLog;
    }

    public void setIDLog(Integer iDLog) {
        this.iDLog = iDLog;
    }

    public String getKorIme() {
        return korIme;
    }

    public void setKorIme(String korIme) {
        this.korIme = korIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    @XmlTransient
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Administracija getAdministracija() {
        return administracija;
    }

    public void setAdministracija(Administracija administracija) {
        this.administracija = administracija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDLog != null ? iDLog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.iDLog == null && other.iDLog != null) || (this.iDLog != null && !this.iDLog.equals(other.iDLog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Login[ iDLog=" + iDLog + " ]";
    }
    
}
