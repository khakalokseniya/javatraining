package serialization;

public class Film implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String film;
	private String actor;

	public Film(String film, String actor) {
		this.film = film;
		this.actor = actor;
	}
	
	public String tiString(){
		return (film + actor);
	}
}
