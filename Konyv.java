package telefonkonyv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Konyv {

	
	private ArrayList<Kontakt> konyv;
	
	public Konyv() {
		
		konyv = new ArrayList<>();
	}
	
	public Konyv(ArrayList<Kontakt> k) {
		
		konyv = k;
	}
	
	public List<Kontakt> getKonyv() {
		
		return konyv;
	}
	
	public void setKonyv(ArrayList<Kontakt> k) { 
		
		konyv = k;
	}
	
	/**Kontakt hozzáadása a telefonkönyvhöz.*/
	public void addKontakt(Kontakt k) {
		
		konyv.add(k);
		
	}
	
	/**Kontaktok listázása.*/
	public String listKontaktok() {
		
		String kontaktok= "";
		for(Kontakt k : konyv) {
			
			kontaktok+= k.toString() + "\n";
			
		} return kontaktok;
	}
	
	/**Kontakt keresése privát vagy munkahelyi telefonszám alapján.
	 * Nem megfelelő formátum vagy nem létező kontakt esetén kivételt dob.*/
	public Kontakt keresKontakt(String szam) throws NumberFormatException, NoSuchContactException {
		
		for (Kontakt k: konyv) {
			
			if(k.getPszam().equals(szam) || k.getMszam().equals(szam)) {
				
				return k;
			}
			
		} throw new NoSuchContactException(); 
		
	}
	
	/**Kontakt keresése teljes név alapján.
	 * Nem létező kontakt esetén kivételt dob.*/
	public Kontakt keresKNev(String teljesnev) throws NoSuchContactException {
		
		for (Kontakt k: konyv) {
			
			if(k.getTeljesnev().equals(teljesnev)) {
				
				return k;
			}
		} throw new NoSuchContactException();
	}
	
	/**Kontakt törlése privát vagy munkahelyi telefonszám alapján.
	 * Nem létező kontakt esetén kivételt dob.*/
	public boolean torolKontakt(String szam) throws NoSuchContactException {
		
		for (Kontakt k : konyv) {
				
			if(k.getPszam().equals(szam) || k.getMszam().equals(szam)) {
						
				konyv.remove(k);
				return true; } 
			
		}  throw new NoSuchContactException();
		
	}
	
	/**Kontakt törlése teljes név alapján.
	 * Nem létező kontakt esetén kivételt dob.*/
	public boolean torolKnev(String tnev) throws NoSuchContactException {
		
		for(Kontakt k: konyv) {
			
			if(k.getTeljesnev().equals(tnev)) {
				
				konyv.remove(k);
				return true;
			}
		} throw new NoSuchContactException();
	}
	
	/**Kontakt keresése e-mail alapján.
	 * Nem létező kontakt esetén kivételt dob.*/
	public Kontakt keresEmail(String email) throws NoSuchContactException, EmailFormatException {
		
		for (Kontakt k: konyv) {
			
			if(k.getEmail().equals(email)) {
				
				return k;
			}
		} throw new NoSuchContactException();
	}
	
	/**Kontakt törlése e-mail alapján.
	 * Nem létező kontakt esetén kivételt dob.*/
	public boolean torolEmail(String email) throws NoSuchContactException, EmailFormatException {
		
		for (Kontakt k: konyv) {
			
			if(k.getEmail().equals(email)) {
				 
				konyv.remove(k);
				return true;
			}
		} throw new NoSuchContactException();
	}
	
	/**Telefonkönyv mentése szöveges fájlba.*/
	public void konyvMentese(String hova) {
		
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(hova), "utf-8"))) {
			
            for(Kontakt k : konyv) {
            	
                bw.write("Kontakt [ Vezetéknév: " +k.getVnev());
                bw.write("Keresztnév: " + k.getKnev());
                bw.write("Becenév: " + k.getBnev());
                bw.write("Cím: " + k.getCim());
                bw.write("Munkahelyi szám: " + k.getMszam());
                bw.write("Privát szám: " + k.getPszam());
                bw.write("E-mail: " + k.getEmail() + "]\n");
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**Egy, már meglévő telefonkönyv importálása szövegfájlból.
	 * Visszaad egy új telefonkönyvet.*/
	static Konyv konyvOlvasasa(String honnan) {
		
		ArrayList<Kontakt> uj_konyv =
				new ArrayList<Kontakt>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(honnan))) {
            String vnev, knev, bnev, cim, mszam, pszam, email;
            
            while((vnev = br.readLine()) != null &&
            		(knev = br.readLine()) != null &&
            		(bnev = br.readLine()) != null &&
            		(cim = br.readLine()) != null &&
            		(mszam = br.readLine()) != null &&
            		(pszam = br.readLine()) != null &&
            		(email = br.readLine()) != null) {
            		uj_konyv.add(new Kontakt(vnev, knev, bnev, cim, mszam, pszam, email));
            }
            
            } catch (IOException e) {
            	e.printStackTrace();
            	
            } return new Konyv(uj_konyv); 
	
	}
		
	
}
