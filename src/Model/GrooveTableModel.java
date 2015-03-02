package Model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 * This class is a specialized Table Model for the groove box
 * 
 * @author Alessandro
 *
 * @param <X>
 */
@SuppressWarnings("unused")
public class GrooveTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -8015327194349106508L;

	private List<GrooveValues> grooveValues;
	
	public GrooveTableModel(final List<GrooveValues> list) {
		grooveValues= list;
	}
	
	@Override
	public int getRowCount() {

		return GrooveValues.DefaultValues.values().length;
	}

	@Override
	public int getColumnCount() {

		return GrooveValues.GROOVE_TIME_VALUES.length;
	}

	@Override
	public String getColumnName(int column) {

		return GrooveValues.GROOVE_TIME_VALUES[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (columnIndex == 0) {
			return GrooveValues.DefaultValues.values()[rowIndex].getInstrument();
		}

		return grooveValues.get(rowIndex).getColor(columnIndex-1);
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return columnIndex == 0 ? false : true;

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex != 0) {
			if (columnIndex == 0) {
				grooveValues.get(rowIndex).setName((String) aValue);
			} else {
				grooveValues.get(rowIndex).setColor((Color) aValue, columnIndex-1);
			}
		}
	}
	
	public List<GrooveValues> getList(){
		return grooveValues;
	}

}