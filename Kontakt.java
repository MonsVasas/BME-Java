package telefonkonyv;

public class Kontakt {

	private String vezeteknev, keresztnev, becenev, cim, 
	munkahelyi_szam, privat_szam, email;
	
	public Kontakt(String vezeteknev, String keresztnev, String becenev, 
			String cim, String munkahelyi_szam, String privat_szam, String email) {
		
		this.vezeteknev = vezeteknev;
		this.keresztnev = keresztnev;
		this.becenev = becenev;
		this.cim = cim;
		this.munkahelyi_szam = munkahelyi_szam;
		this.privat_szam = privat_szam;
		this.email = email;
	}

	public Kontakt() {}

	@Override
	public String toString() {
		return "\nKontakt [\nVezeteknév: " + vezeteknev + "\nKeresztnév: " + keresztnev + "\nBecenév: " + becenev + "\nCím: "
				+ cim + "\nMunkahelyi szám: " + munkahelyi_szam + "\nPrivát szám: " + privat_szam + "\nE-mail: " + email + "]";
	}

	public String getVnev() { return vezeteknev; }
	
	public void setVnev(String vezeteknev) { this.vezeteknev = vezeteknev; }
	
	public String getKnev() { return keresztnev; }
	
	public void setKnev(String keresztnev) { this.keresztnev = keresztnev; }
	
	public String getTeljesnev() { return vezeteknev + keresztnev; }
	
	public String getBnev() { return becenev; }
	
	public void setBnev(String becenev) { this.becenev = becenev; }
	
	public String getCim() { return cim; }
	
	public void setCim(String cim) { this.cim = cim; }
	
	public String getMszam() { return munkahelyi_szam; }
	
	public void setMszam(String munkahelyi_szam) { this.munkahelyi_szam = munkahelyi_szam; }
	
	public String getPszam() { return privat_szam; }
	
	public void setPszam(String privat_szam) { this.privat_szam = privat_szam; }
	
	public String getEmail() { return email; }
	
	public void setEmail(String email) { this.email = email; }
	
	
}
