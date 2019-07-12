import java.sql.*;
import java.util.Scanner;
public class Shopping {
    public static void main(String[] args)throws SQLException, ClassNotFoundException  { 
        int ret_code;
		try{  
		Class.forName("oracle.jdbc.driver.OracleDriver");   
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@PADMAPRIYA-98:1521:XE","SYSTEM","chitra");  
                Statement stmt = con.createStatement();
                 Scanner sc = new Scanner(System.in); 
                 System.out.println("WELCOME TO WOMEN SHOPPING CENTER");
                 int ch;
                  do
                 {
                System.out.println("Enter your choice:");
                System.out.println("1.Signup");
                System.out.println("2.Login");
                System.out.println("3.Product display");
                System.out.println("4.Add to cart");
                System.out.println("5.Discount Eligibility");
                System.out.println("6.Total amount");
                System.out.println("7.Payment");
                System.out.println("8.Card updation");
                System.out.println("9.Check your luck");
                
                int choice=sc.nextInt();
                switch(choice)
                {
                    case 1:    //Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@PADMAPRIYA-98:1521:XE","SYSTEM","chitra");  
                 CallableStatement pstmt = con.prepareCall("{call signup(?,?,?,?,?)}");
                 System.out.println("Enter your name,id,email,address,credit points");
 String name = sc.next();
 int id = sc.nextInt();
 String em = sc.next();
 String ad = sc.next();
 int c = sc.nextInt();
 
 pstmt.setString(1, name);
 pstmt.setInt(2, id);
 pstmt.setString(3, em);
 pstmt.setString(4, ad);
 pstmt.setInt(5, c);
 pstmt.executeUpdate();
System.out.println("You have signed in successfully");
 pstmt.close();
 break;
                    case 2:
                        CallableStatement p1stmt = con.prepareCall("{call login(?)}");
                        System.out.println("Enter your id");
                        int id1=sc.nextInt();
                        p1stmt.setInt(1,id1);
                        p1stmt.executeUpdate();
                        System.out.println("You have signed in successfully");
                        p1stmt.close();
                        break;
                    case 3:
                        int i=12;
                         System.out.println("The products are as follows");
                         while(i!=0)
                         {
                            CallableStatement p4stmt = con.prepareCall("{call pro_dis(?,?,?,?,?,?,?)}");
                            p4stmt.setInt(7,i);
                            p4stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
                            p4stmt.registerOutParameter(2,java.sql.Types.INTEGER);
                            p4stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
                            p4stmt.registerOutParameter(4,java.sql.Types.VARCHAR);
                            p4stmt.registerOutParameter(5,java.sql.Types.VARCHAR);
                            p4stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
                            p4stmt.executeUpdate(); 
                             String f9 = p4stmt.getString(1);
                             int f10 = p4stmt.getInt(2);
                             String f11 = p4stmt.getString(3);
                             String f12 = p4stmt.getString(4);
                             String f13 = p4stmt.getString(5);
                             String f14 = p4stmt.getString(6);
                             System.out.println(f9+" "+f10+" "+f11+" "+f12+" "+f13+" "+f14);
                            i--;
                            p4stmt.close();
                         }
                        
                        break;
                    case 4:
                         CallableStatement p7stmt = con.prepareCall("{call add_to_cart(?,?,?,?)}");
                 System.out.println("Enter your product id,quatity,cost,customer_id");
 
 int id7 = sc.nextInt();
 int qty = sc.nextInt();
 String cost = sc.next();
 int cs = sc.nextInt();
 
 p7stmt.setInt(1,id7);
 p7stmt.setInt(2,qty);
 p7stmt.setString(3,cost);
 p7stmt.setInt(4,cs);
 p7stmt.executeUpdate();
                        System.out.println("your product has been added to your cart");
                        p7stmt.close();
                        break;
                    case 5:
                         CallableStatement p2stmt = con.prepareCall("{?=call discount(?,?)}");
                        System.out.println("Enter your cost and id");
                        String s=sc.next();
                        int id2=sc.nextInt();
                        
                        p2stmt.setString(2,s);
                        p2stmt.setInt(3,id2);
                        
                        p2stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
                        p2stmt.executeUpdate();
                        String fc = p2stmt.getString(1);
                        System.out.println("Your final amount is "+fc);
                        p2stmt.close();
                        break;
                    case 6:
                        CallableStatement p3stmt = con.prepareCall("{?=call pay_result(?)}");
                        System.out.println("Enter your id");
                        int id3=sc.nextInt();
                        p3stmt.setInt(2,id3);
                        p3stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
                        p3stmt.executeUpdate();
                        String f=p3stmt.getString(1);
                        System.out.println("Your final amount is"+f);
                        p3stmt.close();
                        break;
                    case 7:
                        CallableStatement p8stmt = con.prepareCall("{call make_payment(?,?,?,?)}");
                        System.out.println("Enter your ccv,type of payment,customer_id,cart_id");
                        int f15=sc.nextInt();
                        String type = sc.next();
                        int f16=sc.nextInt();
                        int f17=sc.nextInt();
                        p8stmt.setInt(1,f15);
                        p8stmt.setString(2,type);
                        p8stmt.setInt(3,f16);
                        p8stmt.setInt(4,f17);
                        p8stmt.registerOutParameter(1,java.sql.Types.INTEGER);
                        p8stmt.executeUpdate();
                        System.out.println("Proceed to your payment");
                        p8stmt.close();
                        break;
                    case 8:
                        CallableStatement p6stmt = con.prepareCall("{?=call card_updation(?,?)}");
                        System.out.println("Enter your id and amount");
                        int id5=sc.nextInt();
                        int am = sc.nextInt();
                        p6stmt.setInt(2,id5);
                        p6stmt.setInt(3,am);
                        p6stmt.registerOutParameter(1,java.sql.Types.INTEGER);
                        p6stmt.executeUpdate();
                        int f2=p6stmt.getInt(1);
                        System.out.println("The points added to your credit is"+f2);
                        p6stmt.close();
                        break;
                    case 9:
                        CallableStatement p10stmt = con.prepareCall("{?=call lucky(?)}");
                        System.out.println("Enter your id");
                        int f18=sc.nextInt();
                        p10stmt.setInt(2,f18);
                        p10stmt.registerOutParameter(1,java.sql.Types.INTEGER);
                        p10stmt.executeUpdate();
                        int var = p10stmt.getInt(1);
                        if(var==1)
                            System.out.println("Congratulations!!You are the lucky winner");
                        else if(var==2)
                            System.out.println("Sorry!!Better luck next time");
                        p10stmt.close();
                        break;
                        
                }
                System.out.println("press any key to continue and zero to exit");
                ch = sc.nextInt();
                 }  while ch != 0;    
                        
 con.close();
  } catch (SQLException e) {ret_code = e.getErrorCode();
   System.err.println(ret_code + e.getMessage());}
 
		
                catch(Exception e){ System.out.println(e);}  
		} 
}
