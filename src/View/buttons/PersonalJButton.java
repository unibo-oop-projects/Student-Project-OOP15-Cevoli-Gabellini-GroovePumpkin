package View.buttons;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import static Model.Utility.*;

/**
 * A personal JButton class that stores all the imagines for the oject of this
 * class and the color of Background and Foreground; It manages also the border
 * of the button
 * 
 * NOTE: 
 * Avrei voluto implementare un pattern creazionale quale Factory o Strategy o Decorator
 * ma a livello di riuso del codice/uso della memoria (decorator) non mi convincevano:
 * l'unico che si presterebbe nel modo migliore sarebbe lo Strategy e che se riesco, reimplementer� 
 * anzich� l'ammucchiata di classi.
 * 
 * @author Alessandro
 *
 */
public class PersonalJButton extends JButton {

	private static final long serialVersionUID = -7937866815197131576L;
	
	private Object controller;

	/**
	 * Basic constructor for the personal JButton
	 */
	protected PersonalJButton() {
		this.setBorder(getADefaultBorder());
		this.setBackground(WHITE);
		this.setForeground(GRAY);
	}

	/**
	 * @param img to be applied to the button
	 */
	public PersonalJButton(final ImageIcon img) {
		this();
		this.setIcon(img);
	}

	/**
	 * @param title to be shown upon the button
	 * @param img to be applied to the button
	 */
	public PersonalJButton(final ImageIcon img, final String title) {
		this(img);
		this.setTitledBorder(title);
	}
	
	/**
	 * I needed his method for changing the name of the titled border gotten
	 * 
	 * @return the TitledBorder if it exists, otherways null
	 */
	private TitledBorder getTitledBorder() {

		if (this.getBorder() instanceof CompoundBorder
				&& ((CompoundBorder) this.getBorder())
						.getOutsideBorder() instanceof TitledBorder){
			return ((TitledBorder) ((CompoundBorder) this.getBorder())
					.getOutsideBorder());
		}
		
		return null;
	}
	
	protected void doShow(final boolean b, final String t){
		if(b){
			this.setTitledBorder(t);
		}
	}
	
	/**
	 * This method change the previous title show by the 
	 * titledborder (if exist), otherways it do nothing
	 * 
	 * @param newTitle
	 */
	public void changeTitle(final String newTitle){
		TitledBorder tb= this.getTitledBorder();
		if (tb != null) {
			tb.setTitle(newTitle);
		}
	}
	
	/**
	 * 
	 * @return the controller associated with this button
	 */
	public Object getController(){
		return controller;
	}
	
	/**
	 * 
	 * @return the controller associated with this button
	 */
	public void setController(final Object controller){
		this.controller=controller;
	}
	
	/**
	 * 
	 * @param name
	 *            to be shown on the TitledBorder
	 */ 
	public void setTitledBorder(final String title) {
		this.setBorder(getACompoundTitledBorder(title));
	}
}