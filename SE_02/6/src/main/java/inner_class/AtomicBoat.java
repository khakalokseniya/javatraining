package inner_class;

public class AtomicBoat {
	public void getSail() {
		System.out.println("I'm going to sail!");
	}

	private class Engine{
		AtomicBoat boat = new AtomicBoat();
		boat.getSail();		
	};

}
