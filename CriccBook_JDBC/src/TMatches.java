import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TMatches extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Connection conn = null;
	private Statement stmt=null;
	private ResultSet rs;
	
	private JTable ct;
	private JLabel label1, label2;
	private JTextField match_no;
	private JButton next;
	private int n=0;
	
	private String CID;
	private String[] MID;
	private String[] SID;
	
	TMatches(String CID,String Team)
	{
		try 
		{
			
			this.CID = CID;
			
			//Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost:3306/CriccBook";
          
			// Open a connection
			conn = DriverManager.getConnection(DB_URL,"root","root");
	        stmt = conn.createStatement();
	        
	        
	        rs = stmt.executeQuery("select mid from Matches where team1='"+Team+"' or team2='"+Team+"';");
	        int i=0;
	        while(rs.next()){
				n++;		
			}
	        
	        MID = new String[n];
	        SID = new String[n];
	        
	        rs = stmt.executeQuery("select S.mid,S.sid from Schedule S,Matches M where "
	        		+ "M.mid in (select mid from Matches where team1='"+Team+"' || team2='"+Team+"') and S.mid=M.mid;");
	        i=0;
	        while(rs.next()){
	        	MID[i]=rs.getString(1);
	        	SID[i]=rs.getString(2);
				i++;		
			}
	        	        
	        Object[][] rows = new Object[n][6];
	        
	        rs = stmt.executeQuery("select M.team1,M.team2,St.sname,S.mdate,S.day,S.mtime from Schedule S,Matches M,Stadium St where "
	        		+ "M.mid in (select mid from Matches where team1='"+Team+"' || team2='"+Team+"') and S.mid=M.mid and St.sid=S.sid order by S.mdate;");
	        
	        i=0;
			while(rs.next()){
				rows[i][0] = rs.getString(1);rows[i][1] = rs.getString(2);
				rows[i][2] = rs.getString(3);rows[i][3] = rs.getString(4);
				rows[i][4] = rs.getString(5);rows[i][5] = rs.getString(6);
				i++;				
			}
			
			label1 = new JLabel(Team+" Team Schedule");
	        
			String[] cols = new String[]{"Team1","Team2","Stadium","Date","Day","Time"};

			ct = new JTable(rows,cols);
			
			JScrollPane scrollP = new JScrollPane(ct);
			
			DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
			tableRenderer.setHorizontalAlignment(JLabel.CENTER); //Aligning the table data centrally.
			ct.setDefaultRenderer(Object.class, tableRenderer);
			
			
			scrollP.setBorder(BorderFactory.createEmptyBorder()); //How to remove the border of JScrollPane.
			scrollP.setPreferredSize(new Dimension(490, 100));
			
			label2 = new JLabel("Match No:");
			match_no = new JTextField(5);
			next = new JButton("Next");
			
			add(label1);
			add(scrollP);
			add(label2);
			add(match_no);
			add(next);
			
			registerListeners();
			
			setTitle("Schedule");     
			setSize(530,200);
			setLayout(new FlowLayout());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setVisible(true);
		}
		catch(Exception e){ System.out.println(e);}  	
	}
	
	public void registerListeners()
	{
		next.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					int m_no = Integer.parseInt(match_no.getText());
					if(m_no <=0 || m_no>n){
						JOptionPane.showMessageDialog(TMatches.this, "Invalid Match Number!!", "WARNING", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						new Tiers(CID,MID[m_no-1],SID[m_no-1]);
						dispose();
					}
				}
				catch(Exception E){ 
					JOptionPane.showMessageDialog(TMatches.this, "Invalid Match Number!!", "WARNING", JOptionPane.WARNING_MESSAGE);
					System.out.println(E);
				}
	        }  
	    });
	}
}




