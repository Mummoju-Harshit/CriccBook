import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class Stadium extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Connection conn = null;
	private Statement stmt=null;
	private ResultSet rs;
	
	private JTable ct;
	private JLabel label1, label2;
	
	Stadium()
	{
		try 
		{
			//Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost:3306/CriccBook";
          
			// Open a connection
			conn = DriverManager.getConnection(DB_URL,"root","root");
	        stmt = conn.createStatement();
	        
	        Object[][] rows = new Object[3][4];
	        
	        rs = stmt.executeQuery("select * from Stadium;");
	        
	        int i=0;
			while(rs.next()){
				rows[i][0] = rs.getString(1);
				rows[i][1] = rs.getString(2);
				rows[i][2] = rs.getString(3);
				rows[i][3] = rs.getString(4);
				i++;				
			}
			
			label1 = new JLabel("List Of Stadiums");
	        
			String[] cols = new String[]{"Stadium ID","Stadium Name","Location","Tiers"};

			ct = new JTable(rows,cols);
			
			JScrollPane scrollP = new JScrollPane(ct);
			
			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
			tableRenderer.setHorizontalAlignment(JLabel.CENTER); //Aligning the table data centrally.
			ct.setDefaultRenderer(Object.class, tableRenderer);
			
			
			scrollP.setBorder(BorderFactory.createEmptyBorder()); //How to remove the border of JScrollPane.
			scrollP.setPreferredSize(new Dimension(420, 100));
			
			label2 = new JLabel("Rows : " + ct.getRowCount() + ", Columns : "+ ct.getColumnCount() );
			
			add(label1);
			add(scrollP);
			add(label2);
			
			
			setTitle("Stadiums");     
			setSize(530,200);
			setLayout(new FlowLayout());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
		}
		catch(Exception e){ System.out.println(e);}  	
	}
}

