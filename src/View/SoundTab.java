package View;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import controller.GrooveBoxController;
import controller.MusicPlayer;
import static Model.Utility.*;

/**
 * This class creates an already populated JTabbedPane for the SoundFrame class
 * 
 * @author Alessandro
 *
 */
public class SoundTab extends JTabbedPane {

	private static final long serialVersionUID = 5184587254735736323L;
	private GroovePanel groovebox;
	private PlayerPanel player;
	
	public SoundTab(final int posistion, final MusicPlayer mp, final GrooveBoxController groove) {
		
		super(JTabbedPane.TOP);
		this.setDoubleBuffered(true);
		
		final PlayerPanel play= new PlayerPanel(mp);
		this.add("Play Music", play);
		
		final GroovePanel grooveBox= new GroovePanel(groove);
		this.add("Groove Box", grooveBox);	
		
		this.setBackgroundAt(0, WHITE);
		this.setBackgroundAt(1, WHITE);
		this.setForegroundAt(0, GRAY);
		this.setForegroundAt(1, GRAY);
	}
	
	/**
	 * 
	 * @return
	 */
	public GroovePanel getGroovePanel(){
		return groovebox;
	}
	/**
	 * 
	 * @return
	 */
	public PlayerPanel getPlaylistPanel(){
		return player;
	}
	
	/**
	 * 
	 * @param player
	 */
	public void setPlayerPanel(final PlayerPanel player){
		this.remove(this.player);
		this.player=player;
		this.add(this.player, BorderLayout.NORTH);
	}
	
	/**
	 * 
	 * @param groovebox
	 */
	public void setGroovePanel(final GroovePanel groovebox){
		this.remove(this.groovebox);
		this.groovebox=groovebox;
		this.add(this.groovebox, BorderLayout.NORTH);
	}
}
