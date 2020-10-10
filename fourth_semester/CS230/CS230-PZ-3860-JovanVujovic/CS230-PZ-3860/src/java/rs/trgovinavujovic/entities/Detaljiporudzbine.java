/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.trgovinavujovic.entities;

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
 * @author Jovan Vujovic
 */
@Entity
@Table(name = "detaljiporudzbine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detaljiporudzbine.findAll", query = "SELECT d FROM Detaljiporudzbine d")
    , @NamedQuery(name = "Detaljiporudzbine.findByDetaljiPorudzbineId", query = "SELECT d FROM Detaljiporudzbine d WHERE d.detaljiPorudzbineId = :detaljiPorudzbineId")
    , @NamedQuery(name = "Detaljiporudzbine.findByKolicina", query = "SELECT d FROM Detaljiporudzbine d WHERE d.kolicina = :kolicina")})
public class Detaljiporudzbine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DETALJI_PORUDZBINE_ID")
    private Integer detaljiPorudzbineId;
    @Column(name = "KOLICINA")
    private Long kolicina;
    @JoinColumn(name = "PORUDZBINA_ID", referencedColumnName = "PORUDZBINA_ID")
    @ManyToOne(optional = false)
    private Porudzbina porudzbinaId;
    @JoinColumn(name = "PROIZVOD_ID", referencedColumnName = "PROIZVOD_ID")
    @ManyToOne(optional = false)
    private Proizvod proizvodId;

    public Detaljiporudzbine() {
    }

    public Detaljiporudzbine(Integer detaljiPorudzbineId) {
        this.detaljiPorudzbineId = detaljiPorudzbineId;
    }

    public Integer getDetaljiPorudzbineId() {
        return detaljiPorudzbineId;
    }

    public void setDetaljiPorudzbineId(Integer detaljiPorudzbineId) {
        this.detaljiPorudzbineId = detaljiPorudzbineId;
    }

    public Long getKolicina() {
        return kolicina;
    }

    public void setKolicina(Long kolicina) {
        this.kolicina = kolicina;
    }

    public Porudzbina getPorudzbinaId() {
        return porudzbinaId;
    }

    public void setPorudzbinaId(Porudzbina porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    public Proizvod getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(Proizvod proizvodId) {
        this.proizvodId = proizvodId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detaljiPorudzbineId != null ? detaljiPorudzbineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detaljiporudzbine)) {
            return false;
        }
        Detaljiporudzbine other = (Detaljiporudzbine) object;
        if ((this.detaljiPorudzbineId == null && other.detaljiPorudzbineId != null) || (this.detaljiPorudzbineId != null && !this.detaljiPorudzbineId.equals(other.detaljiPorudzbineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rs.trgovinavujovic.entities.Detaljiporudzbine[ detaljiPorudzbineId=" + detaljiPorudzbineId + " ]";
    }
    
}
