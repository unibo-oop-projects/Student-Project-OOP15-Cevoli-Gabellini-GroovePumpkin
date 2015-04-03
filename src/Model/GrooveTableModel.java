package Model;

import java.awt.Color;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * This class is a specialized Table Model for the groove box
 * 
 * @author Alessandro
 *
 * @param <X>
 */
public class GrooveTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -8015327194349106508L;
	
	/**
	 * 
	 * The names of all the columns, i've used a static array instead of an
	 * ArrayList because i was already sure it won't have changed
	 * 
	 * @GrooveTimeValues
	 */
	public static final String[] GROOVE_TIME_VALUES = new String[] {
			"-Instruments-", "#0", "A", "B", "C", "#1", "A", "B", "C", "#2",
			"A", "B", "C", "#3", "A", "B", "C", "#4", "A", "B", "C", "#5", "A",
			"B", "C", "#6", "A", "B", "C", "#7", "A", "B", "C", "#8", "A", "B",
			"C", "#9", "A", "B", "C" };

	private final List<GrooveValues> grooveValues;
	
	/**
	 * 
	 * @param list
	 */
	public GrooveTableModel(final List<GrooveValues> list) {
		grooveValues=list;
	}
	
	@Override
	public int getRowCount() {

		return DefaultValues.values().length;
	}

	@Override
	public int getColumnCount() {

		return GROOVE_TIME_VALUES.length;
	}

	@Override
	public String getColumnName(final int column) {

		return GROOVE_TIME_VALUES[column];
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {

		if (columnIndex == 0) {
			return DefaultValues.values()[rowIndex].getInstrument();
		} else{
			return grooveValues.get(rowIndex).getColorAtIndex(columnIndex-1);
		}
	}

	@Override
	public boolean isCellEditable(final int rowIndex, final int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
		if (aValue instanceof Color) {
			grooveValues.get(rowIndex).setColorAtIndex((Color) aValue, columnIndex-1);
		}
	}
	
	/**
	 * 
	 * @return the groovebox associated to this table model
	 */
	public List<GrooveValues> getList(){
		return grooveValues;
	}
}