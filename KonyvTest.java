package telefonkonyv;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;


class KonyvTest {

	static Konyv test = new Konyv();
	

	@Test
	void testKeresKontakt() {
		
		String keresett = "06501094778";
		
		try {
			
			Kontakt k1 = new Kontakt("Csala", "Bálint", "Nincs", "Budapest, Laborvez út. 3/B.", "Nincs", "06501094778", "csalabalint@gmail.com");
			test.addKontakt(k1);
			Kontakt k2 = test.keresKontakt(keresett);
			assertEquals(k1,k2);
			
		} catch (NoSuchContactException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	void testKeresKnev() {
		
		String keresett = "Vasas" + "Mónika";
		
		try {
			
			Kontakt k1 = new Kontakt("Vasas", "Mónika", "Móni", "Budapest, Amerikai út. 63", "Nincs", "06303114378", "monikavasas@gmail.com");
			test.addKontakt(k1);
			Kontakt k2 = test.keresKNev(keresett);
			assertEquals(k1,k2);
			
		} catch (NoSuchContactException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	void testKeresEmail() {
		
		String keresett = "bart08@gmail.com";
		
		try {
			
			Kontakt k1 = new Kontakt("Kocsis", "Bálint", "Bart", "Esztergom, Arany János út. 4", "Nincs", "Nincs", "bart08@gmail.com");
			test.addKontakt(k1);
			
			try {
				Kontakt k2 = test.keresEmail(keresett);
				assertEquals(k1, k2);
			} catch (EmailFormatException e) {
				e.getMessage();
			}
			
		} catch (NoSuchContactException e) {
			
			e.printStackTrace();
		}
	}

	@Test
	void testTorolKontakt() {
		
		try {
			Kontakt k1 = new Kontakt("Szlávik", "Sára", "Saci", "Füzesabony, Jókai út. 3", "06305896327", "Nincs", "szlaviksara@gmail.com");
			test.addKontakt(k1);
			String keresett = "06305896327";
			
			if(k1.getMszam().equals(keresett) || k1.getPszam().equals(keresett)) {
				
				test.torolKontakt(keresett);
				
			} else {
				
				fail();
			}
			
		} catch (NoSuchContactException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	void testTorolKnev() {
		
		String tnev = "Galvács" + "Zoltán";
		
		try {
			Kontakt k1 = new Kontakt("Galvács", "Zoltán", "Zoli", "Kecskemét, Rákóczi út. 4", "06305409124", "Nincs", "galvacszoltan@gmail.com");
			test.addKontakt(k1);
			
			
			
			if(k1.getTeljesnev().equals(tnev)) {
				
				test.torolKnev(tnev);
				
				
			} else {
				
				fail();
			}
		} catch (NoSuchContactException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	void testTorolEmail() {
		
		String email = "bmewontletyoudie@gmail.com";
		
		try {
			Kontakt k1 = new Kontakt("Kiss", "Péter", "Peti", "Balatonfüred, Szarka út. 5", "Nincs", "06303115246", "bmewontletyoudie@gmail.com");
			test.addKontakt(k1);
			
			if(k1.getEmail().equals(email)) {
				
				try {
					test.torolEmail(email);
				} catch (EmailFormatException e) {
					e.getMessage();
				}
				
			} else {
				
				fail();
			}
		} catch (NoSuchContactException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testListKontaktok() {
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		System.setOut(new PrintStream(o));
		Kontakt k1 = new Kontakt("Kónya", "Krisztián", "Krissz", "Budapest, Andrássy út. 32", "Nincs", "06509204782", "kriszkonya@gmail.com");
		test.addKontakt(k1);
		
		System.out.print(k1.toString() + "\n");
		
		assertEquals(test.listKontaktok(), o.toString());
	
	}
	
	@Test
	void testKonyvMentese() {
		
		assertThrows(NullPointerException.class, () -> test.konyvMentese(null));
		
	}
	
	
	@Test
	void testKonyvOlvasasa() {
		
		/*Az innen.txt tartalmazza a következő Kontaktot: 
		 * Kontakt [
			Vezeteknév: Kelemen
			Keresztnév: Natália
			Becenév: Nati
			Cím: Balatonfüred, Radnóti út. 5
			Munkahelyi szám: 06304567894
			Privát szám: 06706459875
			E-mail: natikelemen@gmail.com]

		 */
		
		try {
			
			test = Konyv.konyvOlvasasa("innen.txt");
			String keresett = "natikelemen@gmail.com";
			
			try {
				Kontakt k = test.keresEmail(keresett);
				assertNotNull(k);
			} catch (EmailFormatException e) {
				
				e.getMessage();
			}
			
			
			
		} catch (NoSuchContactException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testNoSuchContactException() {
		
		assertThrows(NoSuchContactException.class, () -> test.keresEmail("kisskrisztina@gmail.com"));
		
	}

}
