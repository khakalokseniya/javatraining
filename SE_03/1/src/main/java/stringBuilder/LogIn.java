package stringBuilder;

public class LogIn {

	public static void main(String[] args) {
	CrazyLogger login = new CrazyLogger();
	login.addLog(" my login");
	login.addLog(" another log");
	
	System.out.println(login.getLog());
	}
}
