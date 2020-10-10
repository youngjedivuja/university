/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.trgovinavujovic.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "kupac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kupac.findAll", query = "SELECT k FROM Kupac k")
    , @NamedQuery(name = "Kupac.findByKupacId", query = "SELECT k FROM Kupac k WHERE k.kupacId = :kupacId")
    , @NamedQuery(name = "Kupac.findByNaziv", query = "SELECT k FROM Kupac k WHERE k.naziv = :naziv")
    , @NamedQuery(name = "Kupac.findByKontaktTelefon", query = "SELECT k FROM Kupac k WHERE k.kontaktTelefon = :kontaktTelefon")})
public class Kupac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kupac_id")
    private Integer kupacId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "naziv")
    private String naziv;
    @Size(max = 64)
    @Column(name = "kontakt_telefon")
    private String kontaktTelefon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kupacId")
    private List<Porudzbina> porudzbinaList;

    public Kupac() {
    }

    public Kupac(Integer kupacId) {
        this.kupacId = kupacId;
    }

    public Kupac(Integer kupacId, String naziv) {
        this.kupacId = kupacId;
        this.naziv = naziv;
    }

    public Integer getKupacId() {
        return kupacId;
    }

    public void setKupacId(Integer kupacId) {
        this.kupacId = kupacId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    @XmlTransient
    public List<Porudzbina> getPorudzbinaList() {
        return porudzbinaList;
    }

    public void setPorudzbinaList(List<Porudzbina> porudzbinaList) {
        this.porudzbinaList = porudzbinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kupacId != null ? kupacId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kupac)) {
            return false;
        }
        Kupac other = (Kupac) object;
        if ((this.kupacId == null && other.kupacId != null) || (this.kupacId != null && !this.kupacId.equals(other.kupacId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naziv + " ;" + kontaktTelefon;
    }
    
}
