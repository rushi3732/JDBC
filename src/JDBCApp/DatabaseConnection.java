package JDBCApp;

import java.sql.*;
import java.util.*;

public class DatabaseConnection {
	public static void main(String[] args) throws Exception {
		com.mysql.cj.jdbc.Driver d = new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(d);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcapp", "root", "Rushi@3732");
		Statement stmt = conn.createStatement();
		if (conn != null) {
			System.out.println("Database is connected");
			int value = stmt.executeUpdate(
					"create table if not exists register(rid int(5)primary key auto_increment,name varchar(20)not null,email varchar(20)not null unique,contact varchar(20)not null unique,username varchar(20)not null,password varchar(20) not null unique)");
			if (value >= 0) {
				ResultSet temp = stmt.executeQuery(" select * from register");
				if (temp.getRow() > 0) {
					System.out.println("table already Exists");
				}
			} else {
				if (value == 0) {
					System.out.println("Table   create sucessfully.................");
				} else {
					System.out.println("Table  not create Successfully......................");
				}

			}

		}

		else 
		{
			System.out.println("Database not connected \n");
		}
		Scanner ad = new Scanner(System.in);
		System.out.println("1. add new student\n");
		System.out.println("2. view all students \n");
		System.out.println("3. search student by its name \n");
		System.out.println("4. search student whose name contain at 2 characters\n");
		System.out.println("5. search student whose name start with r and end with sh \n");
		System.out.println("6. search student whose having a same name and its count \n");
		System.out.println("7 delete students by using its name or email \n");
		System.out.println("8. update student record by using its id.");
		do {
			System.out.println("Enter Your Choice\n");
			int ch = ad.nextInt();
			switch (ch) {
			case 1:

				System.out.println("Enter Id Name Email Contact UserName Password \n");
				int rid = ad.nextInt();
				String name = ad.next();
				String email = ad.next();
				String contact = ad.next();
				String username = ad.next();
				String password = ad.next();
				int value = stmt.executeUpdate("insert into register values(" + rid + ",'" + name + "','" + email
						+ "','" + contact + "','" + username + "','" + password + "')");
				if (value > 0) {
					System.out.println("Record insert Succesfully");
				} else {
					System.out.println("Record  not insert Succesfully");
				}

				break;

			case 2:
				ResultSet temp = stmt.executeQuery(" select * from register");

				while (temp.next()) {
					System.out.println("Id: \t" + temp.getString(1) + "\t");
					System.out.println("Name:\t" + temp.getString(2) + "\t");
					System.out.println("Email:\t" + temp.getString(3) + "\t");
					System.out.println("Contact:\t" + temp.getString(4) + "\t");
					System.out.println("UserName:\t" + temp.getString(5) + "\t");
					System.out.println("Password:\t" + temp.getString(6) + "\t");

				}
				break;

			case 3:
				System.out.println("enter a name You want to search");
				String Name = ad.next();
				ResultSet rs = stmt.executeQuery(" select * from register where name='" + Name + "'");
				if (rs.next()) {
					System.out.println("Id: \t" + rs.getString(1) + "\t");
					System.out.println("Name:\t" + rs.getString(2) + "\t");
					System.out.println("Email:\t" + rs.getString(3) + "\t");
					System.out.println("Contact:\t" + rs.getString(4) + "\t");
					System.out.println("UserName:\t" + rs.getString(5) + "\t");
					System.out.println("Password:\t" + rs.getString(6) + "\t");
				} else {
					System.out.println("Name Not present in table");
				}

				break;
			case 4:

				ResultSet result = stmt.executeQuery(" select * from register where name like '__%'");
				if (result.next()) {
					System.out.println("Id: \t" + result.getString(1) + "\t");
					System.out.println("Name:\t" + result.getString(2) + "\t");
					System.out.println("Email:\t" + result.getString(3) + "\t");
					System.out.println("Contact:\t" + result.getString(4) + "\t");
					System.out.println("UserName:\t" + result.getString(5) + "\t");
					System.out.println("Password:\t" + result.getString(6) + "\t");
				}

				break;
			case 5:
				ResultSet resul = stmt.executeQuery(" select * from register where name like 'r%sh'");
				if (resul.next()) {
					System.out.println("Id: \t" + resul.getString(1) + "\t");
					System.out.println("Name:\t" + resul.getString(2) + "\t");
					System.out.println("Email:\t" + resul.getString(3) + "\t");
					System.out.println("Contact:\t" + resul.getString(4) + "\t");
					System.out.println("UserName:\t" + resul.getString(5) + "\t");
					System.out.println("Password:\t" + resul.getString(6) + "\t");
				}

				break;
			case 6:
				System.out.println("Enter the name having count");
				String na = ad.next();
				ResultSet rs1 = stmt
						.executeQuery(" select count(name),name from  register group by name having name='" + na + "'");
				while (rs1.next() == true) {
					System.out.println("Count: \t" + rs1.getString(1) + "\t");
					System.out.println("Name:\t" + rs1.getString(2) + "\t");

				}
				break;

			case 7:
				System.out.println("delete students by using its name ");
				String em = ad.next();
				
				int vale = stmt.executeUpdate("delete from register where (email='" + em +"')");
				if (vale > 0) {
					System.out.println("Record delete sucessfully");
				} else {

					System.out.println("Record not  delete sucessfully");
				}
				break;
			case 8:
				System.out.println("update record");
				System.out.println("enter a id for update record");
				int id = ad.nextInt();
				String name1 = ad.next();
				String email1 = ad.next();
				String contact1 = ad.next();
				String username1 = ad.next();
				String password1 = ad.next();
				int che = stmt.executeUpdate(" update register set name='" +name1+"',email='" +email1+ "',contact='"
						+contact1 +"',username='" + username1 + "',password='" + password1 + "' where rid='" + id + "'");
				if (che > 0) {
					System.out.println("record update successfully");
				} else {
					System.out.println("record not update successfully");
				}
				break;
			default:
			}

		} while (true);

	}
}
