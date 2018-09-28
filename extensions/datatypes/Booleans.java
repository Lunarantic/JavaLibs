package extensions.datatypes;

public class Booleans {
	private boolean status;

	public Booleans() {
		this.status = false;
	}
	
	public Booleans(boolean status) {
		this.status = status;
	}
	
	public boolean update(boolean status) {
		this.status = status;
		return status;
	}
	
	public boolean getValue() {
		return status;
	}
}
