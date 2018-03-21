/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Denic
 */
@Embeddable
public class StudentPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "IDStudent")
    private int iDStudent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FKSmer")
    private int fKSmer;

    public StudentPK() {
    }

    public StudentPK(int iDStudent, int fKSmer) {
        this.iDStudent = iDStudent;
        this.fKSmer = fKSmer;
    }

    public Integer getIDStudent() {
        return iDStudent;
    }

    public void setIDStudent(int iDStudent) {
        this.iDStudent = iDStudent;
    }

    public int getFKSmer() {
        return fKSmer;
    }

    public void setFKSmer(int fKSmer) {
        this.fKSmer = fKSmer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iDStudent;
        hash += (int) fKSmer;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentPK)) {
            return false;
        }
        StudentPK other = (StudentPK) object;
        if (this.iDStudent != other.iDStudent) {
            return false;
        }
        if (this.fKSmer != other.fKSmer) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.StudentPK[ iDStudent=" + iDStudent + ", fKSmer=" + fKSmer + " ]";
    }
    
}
