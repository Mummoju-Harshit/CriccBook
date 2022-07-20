import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class CTable extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Connection conn = null;
	private Statement stmt=null;
	private ResultSet rs;
	
	private JTable ct;
	private JLabel label1, label2;
	
	CTable()
	{
		try 
		{
			//Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost:3306/CriccBook";
          
			// Open a connection
			conn = DriverManager.getConnection(DB_URL,"root","root");
	        stmt = conn.createStatement();
	        
	        rs = stmt.executeQuery("select * from Customer;");
	        
	        int n=0;
			while(rs.next()){
				n++;				
			}
	                
	        Object[][] rows = new Object[n][3];
	        
	        rs = stmt.executeQuery("select * from Customer;");
	        
	        int i=0;
			while(rs.next()){
				rows[i][0] = rs.getString(2);
				rows[i][1] = rs.getString(1);
				rows[i][2] = rs.getString(3);
				i++;				
			}
			
			label1 = new JLabel("List Of Customers");
	        
			String[] cols = new String[]{"Custmoer Name","Customer ID","Password"};

			ct = new JTable(rows,cols);
			
			JScrollPane scrollP = new JScrollPane(ct);
			
			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
			tableRenderer.setHorizontalAlignment(JLabel.CENTER); //Aligning the table data centrally.
			ct.setDefaultRenderer(Object.class, tableRenderer);
			
			
			scrollP.setBorder(BorderFactory.createEmptyBorder()); //How to remove the border of JScrollPane.
			scrollP.setPreferredSize(new Dimension(400, 100));
			
			label2 = new JLabel("Rows : " + ct.getRowCount() + ", Columns : "+ ct.getColumnCount() );
			
			add(label1);
			add(scrollP);
			add(label2);
			
			
			setTitle("Customers");     
			setSize(500,200);
			setLayout(new FlowLayout());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
		}
		catch(Exception e){ System.out.println(e);}
    	
	}
}
