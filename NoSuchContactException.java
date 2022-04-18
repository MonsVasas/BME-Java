package telefonkonyv;

@SuppressWarnings("serial")
final class NoSuchContactException extends Exception {

	public NoSuchContactException() {
		
		super("Nincs ilyen kontakt.");
	}
}
