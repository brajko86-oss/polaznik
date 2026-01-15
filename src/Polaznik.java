import java.util.Objects;

public class Polaznik implements Comparable<Polaznik> {
    String ime;
    String prezime;
    String email;

    public Polaznik(String ime, String prezime, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String polaznici() {
        return ime + " " + prezime + " (" + email + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Polaznik polaznik = (Polaznik) o;
        return email.equalsIgnoreCase(polaznik.email);
    }

    @Override
    public int hashCode() {
        return email.toLowerCase().hashCode();
    }

    public int compareTo(Polaznik p) {
        int cmp = this.email.compareToIgnoreCase(p.email);
        if (cmp != 0) return cmp;

        cmp = this.prezime.compareToIgnoreCase(p.prezime);
        if (cmp != 0) return cmp;

        cmp = this.ime.compareToIgnoreCase(p.ime);
        return cmp;
    }
}
