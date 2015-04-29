package view.config;

import static view.config.Utility.*;

import javax.swing.UIManager;

public class Configuration {

	public Configuration() {

		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (final Exception e) {
		//
		// }

		UIManager.put("Button.select", LIGHT_GRAY);
		UIManager.put("TabbedPane.selected", WHITE);
		UIManager.put("TabbedPane.highlight", WHITE);
		UIManager.put("TabbedPane.focus", WHITE);
		UIManager.put("TabbedPane.selectHighlight", WHITE);
		UIManager.put("TabbedPane.background", DARK_GRAY);
		UIManager.put("TabbedPane.foreground", WHITE);
		UIManager.put("TabbedPane.lightHighlight", WHITE);
		UIManager.put("TabbedPane.shadow", GRAY);
		UIManager.put("TabbedPane.darkShadow", DARK_GRAY);
		UIManager.put("Frame.background", DARK_GRAY);

	}

}
