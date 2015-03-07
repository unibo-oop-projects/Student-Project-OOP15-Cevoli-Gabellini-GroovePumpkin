package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;

import Model.PlaylistTableModel;
import View.buttons.PersonalJButton;

/**
 * Personalized Panel for the PlayBackPanel class, 
 * this class "haldles" the playlist,
 * adding or removing song from it.
 * 
 * @author Alessandro
 *
 */
public class PlaylistPanel extends PersonalJPanel {

	private static final long serialVersionUID = 5045956389400601388L;

	/**
	 * FILE deve essere esteso o wrappato da una classe SONG, che permetta una
	 * migliore interazione con la JTable
	 */

	private List<File> songs;

	/**
	 * Creation of an anonymous class as a model for the JTable class
	 * 
	 */
	private final TableModel dataModel;

	private final JTable playlist;

	public PlaylistPanel(final List<File> songs) {
		super(new BorderLayout());
		this.setBuiltInBorder();
		
		this.dataModel= new PlaylistTableModel(songs);
		this.playlist= new JTable(dataModel);
		this.songs= songs;
		
		final JScrollPane jsp = new JScrollPane();
		this.populateJSP(jsp);

		final PersonalJPanel buttonRow = new PersonalJPanel(new FlowLayout());
		this.populateButtonRow(buttonRow);

		this.add(jsp, BorderLayout.CENTER);
		this.add(buttonRow, BorderLayout.SOUTH);
	}

	private void populateJSP(JScrollPane jsp) {
		playlist.setBackground(WHITE);
		playlist.setForeground(GRAY);
		playlist.setRowSelectionAllowed(true);
		
		jsp.setViewportView(playlist);
		jsp.setBackground(WHITE);
		jsp.setForeground(GRAY);
	}

	private void populateButtonRow(final PersonalJPanel buttonRow) {
		final PersonalJButton add = new PersonalJButton("Add");

		add.addActionListener(e->{
			// aggiungi una canzone

			// NOTA IL CODICE SEGUENTE DEVE ANDARE NEL MODEL
			JFileChooser chooser = new JFileChooser(System
					.getProperty("user.home"));

			/*
			 * I've built an anonymous class for the file filter, it'll
			 * problably go as a properly stand-alone class
			 */
			chooser.addChoosableFileFilter(new FileFilter() {

				@Override
				public String getDescription() {
					return "*.midi, *.wav";
				}

				@Override
				public boolean accept(File f) {

					if (f.isDirectory() || f.getName().endsWith(".midi")
							|| f.getName().endsWith(".wav")) {
						return true;
					}

					return false;
				}
			});

			chooser.setVisible(true);
			int val = chooser.showOpenDialog(PlaylistPanel.this);
			if (val == JFileChooser.APPROVE_OPTION) {
				final File f = chooser.getSelectedFile();
				songs.add(f);
				System.out.println(f.getName());
			} else if (val != JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(PlaylistPanel.this,
						"An Error has occurred", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}

			// ricrea la nuova tabella
			playlist.tableChanged(new TableModelEvent(dataModel));
		});
		
		final PersonalJButton remove = new PersonalJButton("Remove");

		remove.addActionListener(e->{
			// rimuovi una canzone
			try {
				songs.remove(playlist.getSelectedRow());
				playlist.tableChanged(new TableModelEvent(dataModel));
			} catch (Exception ex) {
				// do nothing
			}
		});

		buttonRow.add(add);
		buttonRow.add(remove);
	}
}