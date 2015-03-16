package controller;

import java.net.URL;
import java.util.List;

import Model.PlayerState;
import View.Updatable;

/**
 * 
 * This interface represents the contract of the music player itself,
 * that is the part of the "Music Player" that deals with taking songs and start them
 * @author Matteo Gabellini
 *
 */
public interface MusicPlayer extends Player{
	
	/**
	 * This method add an Updatable object to the controller datastructure
	 * @param component
	 */
	void addUpdatableObserver(final Updatable component);

	/**
	 * This method notify to all added Updatable object the changed status of the controller
	 */
	void notifyToUpdatable(final PlayerState state);
	
	/**
	 * this method load the next song
	 */
	void goToNextSong();
	
	/**
	 * this method load the previous song
	 */
	void goToPreviousSong();
	
	/**
	 * this method load the song of the playlist specified by the index
	 * @param index
	 */
	void goToSong(int index);
	
	/**
	 * This method take the URL of the song and try to load the track
	 * @param songPath
	 */
	void loadSong(URL songPath);
	
	
	/**
	 * a getter for the current song
	 * @return the song that will be played if will be call the method play
	 */
	URL getCurrentSong();
	
	/**
	 * This method set the shuffle mode
	 * 
	 * @param true if we want to active the shuffle mode or false for deactive if was be already activated
	 */
	void setShuffleMode(boolean active);
	
	boolean isShuffleModeActive();
	
	boolean isLoopModeActive();
	
	/**
	 * This method implements the logic for add the URL of a audio file to the playlist
	 * @param songPath is the resource locator of the sound file
	 * @throws IllegalArgumentException if parameter is null
	 */
	void addSong(URL songPath) throws IllegalArgumentException;
	
	/**
	 * This method implements the logic for remove a song from the playList
	 * @param index rapprent the position in the playlist
	 * @throws IllegalArgumentException if parameter is incorrect
	 */
	void removeSong(int index) throws IllegalArgumentException;
	
	/**
	 * This method permits to load the playlist specified like parameter
	 * @param playList to load
	 * @throws IllegalArgumentException if parameter is null
	 */
	void loadPlayList(List<URL> playList) throws  IllegalArgumentException;
	
	/**
	 * This method return the current playlist
	 * @return a copy of the current playlist
	 */
	List<URL> getPlayList();
}
