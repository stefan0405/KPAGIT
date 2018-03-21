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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findByIDStudent", query = "SELECT s FROM Student s WHERE s.studentPK.iDStudent = :iDStudent")
    , @NamedQuery(name = "Student.findByBrindeksa", query = "SELECT s FROM Student s WHERE s.brindeksa = :brindeksa")
    , @NamedQuery(name = "Student.findByIme", query = "SELECT s FROM Student s WHERE s.ime = :ime")
    , @NamedQuery(name = "Student.findByPrezime", query = "SELECT s FROM Student s WHERE s.prezime = :prezime")
    , @NamedQuery(name = "Student.findByFKSmer", query = "SELECT s FROM Student s WHERE s.studentPK.fKSmer = :fKSmer")
    , @NamedQuery(name = "Student.findByGodina", query = "SELECT s FROM Student s WHERE s.godina = :godina")
    , @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudentPK studentPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "brindeksa")
    private String brindeksa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "godina")
    private int godina;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKBrindeksa")
    private Collection<Sakt> saktCollection;
    @JoinColumn(name = "FKSmer", referencedColumnName = "IDSmer", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Smer smer;
    @JoinColumn(name = "FKLog", referencedColumnName = "IDLog")
    @OneToOne(optional = false)
    private Login fKLog;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKBrIndeks")
    private Collection<Spi> spiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKBrIndeks")
    private Collection<Prakticnanastava> prakticnanastavaCollection;

    public Student() {
    }

    public Student(StudentPK studentPK) {
        this.studentPK = studentPK;
    }

    public Student(StudentPK studentPK, String brindeksa, String ime, String prezime, int godina, String email) {
        this.studentPK = studentPK;
        this.brindeksa = brindeksa;
        this.ime = ime;
        this.prezime = prezime;
        this.godina = godina;
        this.email = email;
    }

    public Student(int iDStudent, int fKSmer) {
        this.studentPK = new StudentPK(iDStudent, fKSmer);
    }

    public StudentPK getStudentPK() {
        return studentPK;
    }

    public void setStudentPK(StudentPK studentPK) {
        this.studentPK = studentPK;
    }

    public String getBrindeksa() {
        return brindeksa;
    }

    public void setBrindeksa(String brindeksa) {
        this.brindeksa = brindeksa;
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

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Sakt> getSaktCollection() {
        return saktCollection;
    }

    public void setSaktCollection(Collection<Sakt> saktCollection) {
        this.saktCollection = saktCollection;
    }
    @XmlTransient
    public Smer getSmer() {
        return smer;
    }

    public void setSmer(Smer smer) {
        this.smer = smer;
    }
    @XmlTransient
    public Login getFKLog() {
        return fKLog;
    }

    public void setFKLog(Login fKLog) {
        this.fKLog = fKLog;
    }

    @XmlTransient
    public Collection<Spi> getSpiCollection() {
        return spiCollection;
    }

    public void setSpiCollection(Collection<Spi> spiCollection) {
        this.spiCollection = spiCollection;
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
        hash += (studentPK != null ? studentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentPK == null && other.studentPK != null) || (this.studentPK != null && !this.studentPK.equals(other.studentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Student[ studentPK=" + studentPK + " ]";
    }
    
}
