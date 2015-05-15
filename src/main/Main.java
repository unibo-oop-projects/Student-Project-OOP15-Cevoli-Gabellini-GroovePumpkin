package main;

import view.config.Configuration;
import view.frames.SoundFrame;
import controller.groovebox.GrooveBoxController;
import controller.groovebox.GrooveBoxPlayer;
import controller.musicplayer.MusicPlayer;
import controller.musicplayer.MusicPlayerFactory;

/**
 * From this site you can convert .mp3/.aac/.m4a into .wav/.midi
 * 
 * @link{http://media.io/it/
 * @link{http://audio.online-convert.com/
 * 
 * @author Alessandro
 *
 */
public final class Main {
	private static final MusicPlayer MP = MusicPlayerFactory
			.createLoopableAndShuffableMP();
	private static final GrooveBoxPlayer GBC = GrooveBoxController
			.getInstance();

	public static void main(final String... args) {
		new Configuration();
		new SoundFrame(MP, GBC);
	}

}
