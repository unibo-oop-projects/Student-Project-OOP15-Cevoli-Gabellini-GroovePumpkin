package model;

public class LoopManager implements LoopableComponent{	
	private boolean loop;
	
	@Override
	public void setLoopMode(final boolean value) {
		this.loop = value;
	}
	
	@Override
	public boolean isLoopModeActive() {
		return this.loop;
	}
}
