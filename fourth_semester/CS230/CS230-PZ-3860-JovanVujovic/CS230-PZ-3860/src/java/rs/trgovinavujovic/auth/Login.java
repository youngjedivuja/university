package rs.trgovinavujovic.auth;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import rs.trgovinavujovic.entities.Korisnik;

/**
 *
 * @author Jovan Vujovic
 */

@Named
@ApplicationScoped
public class Login implements Serializable{
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "CS230-PZ-3860PU")
    private EntityManager em;

    private String email;
    private String lozinka;

    private String rola = "";

    private FacesContext context;
    private Korisnik korisnik;
    
    public Login() {
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

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    /**
     * Izvrsava login ukoliko postoji korisnik sa unetim emailom i ukoliko je lozinka tacna.
     * 
     * @return naziv stranice
     */
    public String login() {
        context = FacesContext.getCurrentInstance();
        Korisnik korisnik = null;
        boolean validan = false;

        try {
            korisnik = (Korisnik) em.createNamedQuery("Korisnik.findByEmail").setParameter("email", email).getSingleResult();
            validan = proveriLozinku(korisnik);
        } catch (NoResultException e) {
            validan = false;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ne postoji nalog sa tim emailom", ""));
        }

        if (validan) {
            HttpSession session = SessionUtil.getSession();
            session.setMaxInactiveInterval(60 * 90);

            session.setAttribute("email", korisnik.getEmail());

            session.setAttribute("rola", korisnik.getRolaId().getNazivRole());

            this.korisnik = korisnik;

            if (korisnik.getRolaId().getNazivRole().equalsIgnoreCase("Administrator")) {
                return "welcomeAdmin";
            } else {
                return "welcomeZaposleni";
            }
        } else {
            return "index";
        }
    }

    /**
     * Proverava da li se lozinka prosledjenog korisnika poklapa sa lozinkom u bazi.
     * @param korisnik korisnik za proveru
     * 
     * @return true ako se poklapa, false ako se ne pokalapa
     */
    private boolean proveriLozinku(Korisnik korisnik) {
        String pravaLozinka = korisnik.getLozinka();
        String unetaLozinka = lozinka;
        if (pravaLozinka.equals(unetaLozinka)) {
            return true;
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pogrešna lozinka", ""));
            return false;
        }
    }

    /**
     * Setuje podatke na prazne vrednosti i zatvara sesiju tj. izvrsava logout.
     * 
     * @return naziv stranice
     */
    public String logout() {
        email = "";
        lozinka = "";
        rola = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml";
    }

}
