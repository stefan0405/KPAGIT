/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleksandar
 */

@XmlRootElement(name = "lista")
@XmlAccessorType
public class PersonalizovanaobavestenjaMarshall implements Serializable{
    
    private Student s;
    private List<Obavestenje> fKObavestenja;
    private List<Spi> fKSpi;
    private List<Prakticnanastava> fKPrakticnaNastava;
    private List<Sakt> fKSkat;

    public PersonalizovanaobavestenjaMarshall(Student s, List<Obavestenje> fKObavestenja, List<Spi> fKSpi, List<Prakticnanastava> fKPrakticnaNastava, List<Sakt> fKSkat) {
        this.s = s;
        this.fKObavestenja = fKObavestenja;
        this.fKSpi = fKSpi;
        this.fKPrakticnaNastava = fKPrakticnaNastava;
        this.fKSkat = fKSkat;
    }



    public PersonalizovanaobavestenjaMarshall() {
    }
    
    public Student getS() {
        return s;
    }

    public void setS(Student s) {
        this.s = s;
    }

    public List<Obavestenje> getfKObavestenja() {
        return fKObavestenja;
    }

    public void setfKObavestenja(List<Obavestenje> fKObavestenja) {
        this.fKObavestenja = fKObavestenja;
    }

    public List<Spi> getfKSpi() {
        return fKSpi;
    }

    public void setfKSpi(List<Spi> fKSpi) {
        this.fKSpi = fKSpi;
    }

    public List<Prakticnanastava> getfKPrakticnaNastava() {
        return fKPrakticnaNastava;
    }

    public void setfKPrakticnaNastava(List<Prakticnanastava> fKPrakticnaNastava) {
        this.fKPrakticnaNastava = fKPrakticnaNastava;
    }

    public List<Sakt> getfKSkat() {
        return fKSkat;
    }

    public void setfKSkat(List<Sakt> fKSkat) {
        this.fKSkat = fKSkat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.s);
        hash = 97 * hash + Objects.hashCode(this.fKObavestenja);
        hash = 97 * hash + Objects.hashCode(this.fKSpi);
        hash = 97 * hash + Objects.hashCode(this.fKPrakticnaNastava);
        hash = 97 * hash + Objects.hashCode(this.fKSkat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonalizovanaobavestenjaMarshall other = (PersonalizovanaobavestenjaMarshall) obj;
        if (!Objects.equals(this.s, other.s)) {
            return false;
        }
        if (!Objects.equals(this.fKObavestenja, other.fKObavestenja)) {
            return false;
        }
        if (!Objects.equals(this.fKSpi, other.fKSpi)) {
            return false;
        }
        if (!Objects.equals(this.fKPrakticnaNastava, other.fKPrakticnaNastava)) {
            return false;
        }
        if (!Objects.equals(this.fKSkat, other.fKSkat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonalizovanaobavestenjaMarshall{" + "s=" + s + ", fKObavestenja=" + fKObavestenja + ", fKSpi=" + fKSpi + ", fKPrakticnaNastava=" + fKPrakticnaNastava + ", fKSkat=" + fKSkat + '}';
    }
    
}
