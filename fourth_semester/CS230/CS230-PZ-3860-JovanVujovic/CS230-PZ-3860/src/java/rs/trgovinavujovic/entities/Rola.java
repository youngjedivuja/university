/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.trgovinavujovic.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Jovan Vujovic
 */
@Entity
@Table(name = "rola")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rola.findAll", query = "SELECT r FROM Rola r")
    , @NamedQuery(name = "Rola.findByRolaId", query = "SELECT r FROM Rola r WHERE r.rolaId = :rolaId")
    , @NamedQuery(name = "Rola.findByNazivRole", query = "SELECT r FROM Rola r WHERE r.nazivRole = :nazivRole")})
public class Rola implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROLA_ID")
    private Integer rolaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "NAZIV_ROLE")
    private String nazivRole;
    @OneToMany(mappedBy = "rolaId")
    private List<Korisnik> korisnikList;

    public Rola() {
    }

    public Rola(Integer rolaId) {
        this.rolaId = rolaId;
    }

    public Rola(Integer rolaId, String nazivRole) {
        this.rolaId = rolaId;
        this.nazivRole = nazivRole;
    }

    public Integer getRolaId() {
        return rolaId;
    }

    public void setRolaId(Integer rolaId) {
        this.rolaId = rolaId;
    }

    public String getNazivRole() {
        return nazivRole;
    }

    public void setNazivRole(String nazivRole) {
        this.nazivRole = nazivRole;
    }

    @XmlTransient
    public List<Korisnik> getKorisnikList() {
        return korisnikList;
    }

    public void setKorisnikList(List<Korisnik> korisnikList) {
        this.korisnikList = korisnikList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolaId != null ? rolaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rola)) {
            return false;
        }
        Rola other = (Rola) object;
        if ((this.rolaId == null && other.rolaId != null) || (this.rolaId != null && !this.rolaId.equals(other.rolaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivRole;
    }
    
}
