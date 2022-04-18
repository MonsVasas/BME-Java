package telefonkonyv;
import java.util.*;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	
	private static String getInput()  {
		
		return sc.next().trim();
	}
	
	/**Ellenőrzi Regex kifejezésekkel a telefonszám formátumának helyességét.*/
	public static String checkNumber(String szam) throws NumberFormatException {
		
		if(szam.matches("[0-9]{11}") || szam.matches("Nincs") || szam.matches("")) {
			
			return szam;
			
		} throw new NumberFormatException("Nem megfelelő formátum!");
	}
	
	/**Ellenőrzi Regex kifejezésekkel az e-mail cím formátumának helyességét.*/
	public static String checkEmail(String e_mail) throws EmailFormatException {
		
		if(e_mail.matches("^(.+)@(.+)$")) {
			
			return e_mail;
		} throw new EmailFormatException();
		
	}
	
	public static void main(String[] args)  {
		
		sc.useDelimiter("\n");
		int i = 0;
		Konyv uj = new Konyv();
		
		while(i == 0) {
			
			System.out.println("\nMenü\n[1] Új kontakt felvétele\n[2] Kontaktok listázása\n"
					+ "\nKontakt törlése...\n\t[3] Telefonszám alapján\n\t[4] Név alapján\n\t[5] E-mail alapján"
					+ "\nKontakt keresése...\n\t[6] Telefonszám alapján\n\t[7] Név alapján\n\t[8] E-mail cím alapján\n"
					+ "\n[9] Telefonkönyv mentése\n[10] Meglévő telefonkönyv betöltése\n\n[11] Kilépés");
			
			System.out.print("\nVálasztott menüpont: ");
			
			try {
				
				int n = Integer.parseInt(sc.next().trim());
				
				if (n == 1) {
					
					
					try {
						
						Kontakt k = new Kontakt();
						System.out.print("Vezetéknév: ");
						k.setVnev(getInput());
					    System.out.print("Keresztnév: ");
					    k.setKnev(getInput());
					    System.out.print("Becenév: ");
					    k.setBnev(getInput());
					    System.out.print("Cím: ");
					    k.setCim(getInput());
					    System.out.print("Munkahelyi szám: ");
					    k.setMszam(checkNumber(getInput()));
					    System.out.print("Privát szám: ");
					    k.setPszam(checkNumber(getInput()));
					    System.out.print("E-mail: ");
					    try {
					    	k.setEmail(checkEmail(getInput()));
					    } catch (EmailFormatException e) { System.err.println(e.getMessage()); }
					    uj.addKontakt(k);
					    
					} catch(NumberFormatException e) {
						
						System.err.println(e.getMessage());
						
					}
					
				}
				
				else if (n == 2) {
					
					System.out.print(uj.listKontaktok());
				}
				
				else if(n == 3) {
					
					System.out.println("Törölni kívánt kontakt telefonszáma: ");
					String tszam = getInput();
					System.out.println("Biztosan törölni szeretnéd a kontaktot? (I/N)");
					String valasz = getInput();
					
					if(valasz.equalsIgnoreCase("I")) {
						
						try {
							
							if(uj.torolKontakt(tszam)) {
								
								System.out.println("Kontakt törölve.");
								
							}
							
						} catch (NoSuchContactException e) {
								
								e.printStackTrace();
							}
						
					} else if(valasz.equalsIgnoreCase("N")) {
						
						System.out.println("Rendben, művelet visszavonva.");
					}
				}
				
				else if (n== 4) {
					
					System.out.print("Törölni kívánt kontakt vezetékneve: ");
					String v_nev = getInput();
					System.out.print("Keresztneve: ");
					String k_nev = getInput();
					String t_nev = v_nev+k_nev;
					
					try {
						if(uj.torolKnev(t_nev)) {
							
							System.out.println("Kontakt törölve.");
						}
					} catch (NoSuchContactException e) {
						
						e.printStackTrace();
					}
				}
				
				else if(n == 5) {
					
					System.out.print("Törölni kívánt kontakt e-mail címe: ");
					String email = getInput();
					
					try {
						
						checkEmail(email);
					
						try {
							
							if (uj.torolEmail(email)) {
								
								System.out.println("Kontakt törölve.");
							}
						} catch (NoSuchContactException e) {
							
							e.printStackTrace();
						}
					} catch (EmailFormatException e) {
						
						e.getMessage();
					}
				}
				
				else if (n == 6) {
					
					System.out.println("Keresett kontakt telefonszáma (privát/munkahelyi): ");
					String kszam = getInput();
					
					try {
						
						checkNumber(kszam);
						
						try {
							Kontakt k = uj.keresKontakt(kszam);
							
							if (k!= null) {
									System.out.println(k.toString());
							}
								
						
						} catch (NoSuchContactException e) {
							
							e.printStackTrace();
							
						} 
						
					} catch (NumberFormatException e) {
						
						System.err.println(e.getMessage());
					}
					
				}
				
				else if (n==7) {
					
					System.out.print("Keresett kontakt vezetékneve: ");
					String vnev = getInput();
					System.out.print("Keresztneve: ");
					String knev = getInput();
					
					String teljes_nev = vnev + knev;
					
					try {
						
						Kontakt k = uj.keresKNev(teljes_nev);
						
						if(k!=null) {
							
							System.out.println(k.toString());
							
						}
					} catch (NoSuchContactException e) {
						
						e.printStackTrace();
					}
				}
				
				else if (n==8) {
					
					System.out.print("Keresett kontakt e-mail címe:");
					String e_mail = getInput();
					
					try {
						
						checkEmail(e_mail);
						try {
							
							Kontakt k = uj.keresEmail(e_mail);
							
							if(k!=null) {
								
								System.out.println(k.toString());
							}
						} catch (NoSuchContactException e) {
							
							e.printStackTrace();
						}
					} catch (EmailFormatException e) { e.getMessage(); }
				}
				else if (n==9) {
				
					System.out.print("Fájlnév: ");
					String hova = getInput() + ".txt";
					uj.konyvMentese(hova);
					System.out.println("Sikeres mentés!");
				}
				
				else if(n==10) {
					
					System.out.print("Fájlnév: ");
					String innen = getInput() + ".txt";
					uj = Konyv.konyvOlvasasa(innen);
					System.out.println("Sikeres importálás!");
				}
				
				else if (n== 11){
					
					System.exit(0);
				}
				
				else {
					
					System.err.println("Nincs ilyen menüpont!");
				}
				
			} catch (NumberFormatException e) {
				
				System.err.print("A formátum nem megfelelő!\n"
						+ "Numerikusan add meg a választott menüpontot!");
			}
			
		} sc.close();
                 

	}

}
