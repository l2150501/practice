package practice5;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel {

  private static final long serialVersionUID = 1L;
  private Vector<Vector<Object>> data;
  private Vector<Object> columnNames;

  public ResultSetTableModel(ResultSet rs) {
    try {
      ResultSetMetaData md = rs.getMetaData();
      int columns = md.getColumnCount();
      columnNames = new Vector<Object>(columns);

      // Get column names
      for (int i = 1; i <= columns; i++) {
        columnNames.add(md.getColumnName(i));
      }

      // Get data
      data = new Vector<Vector<Object>>();
      while (rs.next()) {
        Vector<Object> row = new Vector<Object>(columns);
        for (int i = 1; i <= columns; i++) {
          row.add(rs.getObject(i));
        }
        data.add(row);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public int getColumnCount() {
    return columnNames.size();
  }

  public int getRowCount() {
    return data.size();
  }

  public Object getValueAt(int row, int column) {
    return data.get(row).get(column);
  }

  public String getColumnName(int column) {
    return columnNames.get(column).toString();
  }
}
