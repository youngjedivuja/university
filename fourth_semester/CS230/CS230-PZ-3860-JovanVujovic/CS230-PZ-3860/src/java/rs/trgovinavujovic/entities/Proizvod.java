/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.trgovinavujovic.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jovan Vujovic
 */
@Entity
@Table(name = "proizvod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proizvod.findAll", query = "SELECT p FROM Proizvod p")
    , @NamedQuery(name = "Proizvod.findByProizvodId", query = "SELECT p FROM Proizvod p WHERE p.proizvodId = :proizvodId")
    , @NamedQuery(name = "Proizvod.findByNaziv", query = "SELECT p FROM Proizvod p WHERE p.naziv = :naziv")
    , @NamedQuery(name = "Proizvod.findByCena", query = "SELECT p FROM Proizvod p WHERE p.cena = :cena")
    , @NamedQuery(name = "Proizvod.findByTezina", query = "SELECT p FROM Proizvod p WHERE p.tezina = :tezina")
    , @NamedQuery(name = "Proizvod.findByOpis", query = "SELECT p FROM Proizvod p WHERE p.opis = :opis")
    , @NamedQuery(name = "Proizvod.findByRokTrajanja", query = "SELECT p FROM Proizvod p WHERE p.rokTrajanja = :rokTrajanja")
    , @NamedQuery(name = "Proizvod.findByKolicinaNaStanju", query = "SELECT p FROM Proizvod p WHERE p.kolicinaNaStanju = :kolicinaNaStanju")})
public class Proizvod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PROIZVOD_ID")
    private Integer proizvodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "NAZIV")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CENA")
    private float cena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEZINA")
    private float tezina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "OPIS")
    private String opis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROK_TRAJANJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rokTrajanja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KOLICINA_NA_STANJU")
    private long kolicinaNaStanju;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proizvodId")
    private List<Detaljiporudzbine> detaljiporudzbineList;

    public Proizvod() {
    }

    public Proizvod(Integer proizvodId) {
        this.proizvodId = proizvodId;
    }

    public Proizvod(Integer proizvodId, String naziv, float cena, float tezina, String opis, Date rokTrajanja, long kolicinaNaStanju) {
        this.proizvodId = proizvodId;
        this.naziv = naziv;
        this.cena = cena;
        this.tezina = tezina;
        this.opis = opis;
        this.rokTrajanja = rokTrajanja;
        this.kolicinaNaStanju = kolicinaNaStanju;
    }

    public Integer getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(Integer proizvodId) {
        this.proizvodId = proizvodId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public float getTezina() {
        return tezina;
    }

    public void setTezina(float tezina) {
        this.tezina = tezina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getRokTrajanja() {
        return rokTrajanja;
    }

    public void setRokTrajanja(Date rokTrajanja) {
        this.rokTrajanja = rokTrajanja;
    }

    public long getKolicinaNaStanju() {
        return kolicinaNaStanju;
    }

    public void setKolicinaNaStanju(long kolicinaNaStanju) {
        this.kolicinaNaStanju = kolicinaNaStanju;
    }

    @XmlTransient
    public List<Detaljiporudzbine> getDetaljiporudzbineList() {
        return detaljiporudzbineList;
    }

    public void setDetaljiporudzbineList(List<Detaljiporudzbine> detaljiporudzbineList) {
        this.detaljiporudzbineList = detaljiporudzbineList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proizvodId != null ? proizvodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proizvod)) {
            return false;
        }
        Proizvod other = (Proizvod) object;
        if ((this.proizvodId == null && other.proizvodId != null) || (this.proizvodId != null && !this.proizvodId.equals(other.proizvodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return proizvodId + "; " + naziv + "; " + cena;
    }
    
}
