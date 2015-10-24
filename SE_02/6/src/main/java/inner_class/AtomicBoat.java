package inner_class;

public class AtomicBoat {

	private class EngineForAtomicBoat {
		public void engineStart() {
			System.out.println("I'm going to sail!");
		}
	}

	public static void main(String[] args) {
		AtomicBoat atom = new AtomicBoat();
		AtomicBoat.EngineForAtomicBoat boat = atom.new EngineForAtomicBoat();
		boat.engineStart();
	}

}
