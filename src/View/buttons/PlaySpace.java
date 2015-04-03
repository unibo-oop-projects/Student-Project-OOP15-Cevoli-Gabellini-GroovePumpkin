package View.buttons;

import javax.swing.ImageIcon;
import static Model.Utility.*;
import controller.Player;
import Model.PlayerState;
import View.Updatable;

/**
 * This class extends the PersonalJButton to build a
 * play and pause manager of the controller
 * 
 * @author Alessandro
 *
 */
public class PlaySpace extends PersonalJButton implements Updatable {

	private static final long serialVersionUID = -8958765355776362631L;
	public static final String PLAY = "Play";
	public static final String PAUSE = "Pause";

	/**
	 * 
	 * @param layout
	 *            to be attached
	 * @param buttonEnabled
	 *            if the button have to be enabled or not
	 */
	protected PlaySpace(final Player controller, final ImageIcon img,
			final boolean showTitle) {

		super(img);
		super.setController(controller);
		
		doShow(showTitle, PLAY);
		
		this.addActionListener(e -> {
			if (this.getIcon().equals(PLAY_IMG)) {
				controller.play();
			} else {
				controller.pause();
			}
		});
	}

	@Override
	public void updateStatus(final PlayerState status) {

		if (status.equals(PlayerState.RUNNING)) {
			this.setIcon(PAUSE_IMG);
			changeTitle(PAUSE);
			// start
		} else {
			this.setIcon(PLAY_IMG);
			changeTitle(PLAY);
			// pause
		}
	}
}
