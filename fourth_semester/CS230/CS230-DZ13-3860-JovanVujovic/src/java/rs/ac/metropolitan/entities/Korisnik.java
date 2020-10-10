/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.metropolitan.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jovan Vujovic
 */
@Entity
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")
    , @NamedQuery(name = "Korisnik.findByUserId", query = "SELECT k FROM Korisnik k WHERE k.userId = :userId")
    , @NamedQuery(name = "Korisnik.findByEmail", query = "SELECT k FROM Korisnik k WHERE k.email = :email")
    , @NamedQuery(name = "Korisnik.findByLozinka", query = "SELECT k FROM Korisnik k WHERE k.lozinka = :lozinka")
    , @NamedQuery(name = "Korisnik.findByIme", query = "SELECT k FROM Korisnik k WHERE k.ime = :ime")
    , @NamedQuery(name = "Korisnik.findByAdresa", query = "SELECT k FROM Korisnik k WHERE k.adresa = :adresa")
    , @NamedQuery(name = "Korisnik.findByGrad", query = "SELECT k FROM Korisnik k WHERE k.grad = :grad")
    , @NamedQuery(name = "Korisnik.findByDrzava", query = "SELECT k FROM Korisnik k WHERE k.drzava = :drzava")
    , @NamedQuery(name = "Korisnik.findByTelefon", query = "SELECT k FROM Korisnik k WHERE k.telefon = :telefon")})
public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "LOZINKA")
    private String lozinka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "IME")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ADRESA")
    private String adresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "GRAD")
    private String grad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "DRZAVA")
    private String drzava;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "TELEFON")
    private String telefon;
    @JoinColumn(name = "ROLA_ID", referencedColumnName = "ROLA_ID")
    @ManyToOne
    private Rola rolaId;

    public Korisnik() {
    }

    public Korisnik(Integer userId) {
        this.userId = userId;
    }

    public Korisnik(Integer userId, String email, String lozinka, String ime, String adresa, String grad, String drzava, String telefon) {
        this.userId = userId;
        this.email = email;
        this.lozinka = lozinka;
        this.ime = ime;
        this.adresa = adresa;
        this.grad = grad;
        this.drzava = drzava;
        this.telefon = telefon;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Rola getRolaId() {
        return rolaId;
    }

    public void setRolaId(Rola rolaId) {
        this.rolaId = rolaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Korisnik[ userId=" + userId + " ]";
    }
    
}
