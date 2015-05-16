package view.panels;

import static model.PlayerState.*;
import static view.buttons.ButtonFactory.*;
import static view.config.Configuration.*;
import static view.config.Utility.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import model.PlayerState;
import controller.Loopable;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.Shuffable;

/**
 * Personalized JPanel for the PlayerPanel, 
 * this class "handles" the playing and
 * pausing of a chosen song.
 * 
 * This class has by default: 
 * -A label for the song name;
 * -A play button;
 * -A stop button;
 * -A Forward and Backward button;
 * 
 * Optionally:
 * 
 * -Add a Loop button;
 * -Add a Shuffle button;
 * -Add a label for elapsed time;-> still in work
 * -Add a progrese bar (slider); -> still in work
 * 
 * @author Alessandro
 *
 */
public class MusicPlayerPanel extends AbstractControllablePane<MusicPlayer> {

	private static final long serialVersionUID = 4164776505153007930L;

	private final  JLabel songName = new JLabel("< Nothing Else Matters >");
	private JLabel songTime;
	private JProgressBar jpb;
	
	private final PersonalJPanel north = new PersonalJPanel(new BorderLayout());
	private final PersonalJPanel infoPane= new PersonalJPanel(new BorderLayout());
	private final PersonalJPanel barPane= new PersonalJPanel(new BorderLayout(10, 30));
	private final PersonalJPanel labelsPane = new PersonalJPanel(new FlowLayout(1, 20, 10));

	/**
	 * Default build for this object, creates a raedy to use Music Player Panel
	 * with the given controller
	 * 
	 * @param mp
	 */
	public MusicPlayerPanel(final MusicPlayer mp) {
		super(new BorderLayout());
		this.setBorder(getADefaultPanelBorder());
		this.setController(mp);
		mp.addUpdatableObservers(this);

		songName.setBackground(WHITE);
		songName.setForeground(DARK_GREEN);
		labelsPane.add(songName);
		infoPane.add(labelsPane, BorderLayout.NORTH);
		infoPane.add(barPane, BorderLayout.CENTER);
		north.add(infoPane, BorderLayout.NORTH);
		this.getCommandPane().add(new CmdPane.Builder()
			.setBW(createButton(RW_B, getController(), false))
			.setPlay(createButton(PLAY_B, getController(), false))
			.setStop(createButton(STOP_B, getController(), false))
			.setFW(createButton(FW_B, getController(), false))
			.build(new FlowLayout(1, 10, 10)));
		this.getCommandPane().add(new CmdPane.Builder()
			.setLoop(createButton(LOOP_B, (Loopable)getController(), false))
			.setShuffle(createButton(SHUFFLE_B, (Shuffable)getController(), false))
			.build(new FlowLayout(1, -5, 0)));		
		
		north.add(this.getCommandPane().get(0), BorderLayout.CENTER);
		north.add(this.getCommandPane().get(1), BorderLayout.SOUTH);

		this.addUpdatableObservers(this.getCommandPane().get(0).getWrapper().getPlay().get(), 
				this.getCommandPane().get(0).getWrapper().getStop().get(),
				this.getCommandPane().get(1).getWrapper().getShuffle().get(), 
				this.getCommandPane().get(1).getWrapper().getLoop().get()
				);
		
		this.add(north, BorderLayout.NORTH);
	}
	
	@Override
	public void updateStatus(final PlayerState status) {
		// Notify all the observers
		super.updateStatus(status);
		// self update
		if (status.equals(RUNNING) || status.equals(SONGCHANGED)) {
			// change the name of the songs to the new one
			this.songName.setText(String.join("" ,"< ",
					convertURLPath(((MusicPlayer) getController())
					.getCurrentSong().get().getPath()), " >"));

		} else if (status.equals(STOPPED) || status.equals(REMOVED)) {
			// set the no-song string
			this.songName.setText("< Any song is playing >");
		}
	}

	/**
	 * Attach a new time label to this object
	 * 
	 * @param l
	 */
	public void setSongTimeLabel(final JLabel l) {
		if (checkObj(songTime)) {
			this.labelsPane.remove(songTime);
		}
		this.labelsPane.add(l, 1);
		this.songTime = l;
	}
	
	/**
	 * Attach a new progressbar to this object
	 * 
	 * @param jpb
	 */
	public void setProgressBar(final JProgressBar jpb){
		if (checkObj(this.jpb)) {
			this.barPane.remove(this.jpb);
		}
		this.barPane.add(jpb, BorderLayout.CENTER);
		this.jpb=jpb;
	}
}