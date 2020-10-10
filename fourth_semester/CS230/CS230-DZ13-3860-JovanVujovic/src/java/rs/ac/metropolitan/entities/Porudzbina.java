/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.metropolitan.entities;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "porudzbina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Porudzbina.findAll", query = "SELECT p FROM Porudzbina p")
    , @NamedQuery(name = "Porudzbina.findByPorudzbinaId", query = "SELECT p FROM Porudzbina p WHERE p.porudzbinaId = :porudzbinaId")
    , @NamedQuery(name = "Porudzbina.findByIznos", query = "SELECT p FROM Porudzbina p WHERE p.iznos = :iznos")
    , @NamedQuery(name = "Porudzbina.findByAdresaDostave", query = "SELECT p FROM Porudzbina p WHERE p.adresaDostave = :adresaDostave")
    , @NamedQuery(name = "Porudzbina.findByDatum", query = "SELECT p FROM Porudzbina p WHERE p.datum = :datum")
    , @NamedQuery(name = "Porudzbina.findByStatus", query = "SELECT p FROM Porudzbina p WHERE p.status = :status")})
public class Porudzbina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PORUDZBINA_ID")
    private Integer porudzbinaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IZNOS")
    private float iznos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ADRESA_DOSTAVE")
    private String adresaDostave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "STATUS")
    private String status;
    @JoinColumn(name = "kupac_id", referencedColumnName = "kupac_id")
    @ManyToOne(optional = false)
    private Kupac kupacId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "porudzbinaId")
    private List<Detaljiporudzbine> detaljiporudzbineList;

    public Porudzbina() {
    }

    public Porudzbina(Integer porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    public Porudzbina(Integer porudzbinaId, float iznos, String adresaDostave, Date datum, String status) {
        this.porudzbinaId = porudzbinaId;
        this.iznos = iznos;
        this.adresaDostave = adresaDostave;
        this.datum = datum;
        this.status = status;
    }

    public Integer getPorudzbinaId() {
        return porudzbinaId;
    }

    public void setPorudzbinaId(Integer porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    public float getIznos() {
        return iznos;
    }

    public void setIznos(float iznos) {
        this.iznos = iznos;
    }

    public String getAdresaDostave() {
        return adresaDostave;
    }

    public void setAdresaDostave(String adresaDostave) {
        this.adresaDostave = adresaDostave;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Kupac getKupacId() {
        return kupacId;
    }

    public void setKupacId(Kupac kupacId) {
        this.kupacId = kupacId;
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
        hash += (porudzbinaId != null ? porudzbinaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Porudzbina)) {
            return false;
        }
        Porudzbina other = (Porudzbina) object;
        if ((this.porudzbinaId == null && other.porudzbinaId != null) || (this.porudzbinaId != null && !this.porudzbinaId.equals(other.porudzbinaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Porudzbina[ porudzbinaId=" + porudzbinaId + " ]";
    }
    
}
