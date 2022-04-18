package telefonkonyv;

final class EmailFormatException extends Exception {

	public EmailFormatException() {
		
		super("Nem megfelelő e-mail cím formátum!");
	}
}
