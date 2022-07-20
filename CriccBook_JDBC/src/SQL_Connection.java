import java.sql.*;

public class SQL_Connection {
	
	Connection conn = null;
	Statement stmt=null;
	ResultSet rs;
	
	SQL_Connection()
	{
		try 
		{
			//Loading Driver
			Class.forName("com.mysql.jdbc.Driver");
			String DB_URL = "jdbc:mysql://localhost:3306/CriccBook";
          
			// Open a connection
			conn = DriverManager.getConnection(DB_URL,"root","root");
	        stmt = conn.createStatement();
	        
		}
		catch(Exception e){ System.out.println(e);}
	}
	
	public int CID_Validation(String cid)
	{
		try
		{
			rs = stmt.executeQuery("select cid from Customer;");
			while(rs.next()){
				if(rs.getString(1).equals(cid))
				{
					return 0;
				}
			}			
		}
		catch(Exception e){
			return 0;
		}
		
		return 1;
	}
	
	public int ValidPassword(String cpass)
    {
        if(cpass.length()>=8)return 1;
        else return 0;
    }

	
	public void registration(String cid,String cname,String cpass)
	{
		try
		{
			String reg="insert into Customer values('"+cid+"','"+cname+"','"+cpass+"');";
			stmt.executeUpdate(reg);			
		}
		catch(Exception e){ System.out.println(e);}
	}
	
	public boolean login(String id,String pass)
	{
		int flag=0;
		
		String log="";
		if(id.equals("ADMIN"))
		{
			log="select password from Admin where admin_id='"+id+"';";
		}
		else
		{
			log="select password from Customer where cid='"+id+"';";			
		}
		
		try
		{
			rs = stmt.executeQuery(log);
			while(rs.next()){
				if(rs.getString(1).equals(pass)){
					flag=1;
				}			
			}
		}
		catch(Exception e){ System.out.println(e);}
		
		if(flag==1) return true;
		else return false;
	}
}


