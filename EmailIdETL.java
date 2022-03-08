import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.PreparedStatement;

//ETL = Extract transfer load

public class EmailIdETL {

       
  static List<String> emailfinder(String input) 
	{
          final String emailpattern = "[a-zA-Z]+[.]?+[$]?[a-zA-Z0-9]+[@][a-z]+[.][a-z]{2,4}"; 
          Pattern patten = Pattern.compile(emailpattern);
          Matcher matcher = patten.matcher(input);
          List<String> list = new ArrayList<String>();
          
           while (matcher.find()) 
             {
               list.add(matcher.group());    
             }
          return list;  
    }
  
//  public static List<String> failemailfinder(String input1) 
//	 
//    {
//
//    	final String emailpatternfail = "[0-9a-z]*[a-z]{3}[.][a-z]*"; // 
//	      Pattern patten1 = Pattern.compile(emailpatternfail);
//	      Matcher matcher1 = patten1.matcher(input1);
//	      List<String> list1 = new ArrayList<String>();
//	        
//	     while (matcher1.find()) 
//	       {        
//	         list1.add(matcher1.group());		
//	       }
//	      return list1;
//    }
//	    
	    

  static List<String> phonfinder(String input2)
    {
        final String phonpattern = "[6-9]{1}[0-9]{9}";
        Pattern patten2 = Pattern.compile(phonpattern);
        Matcher matcher2 = patten2.matcher(input2);
        List<String> list2 = new ArrayList<String>();

          while (matcher2.find())
           {
              list2.add(matcher2.group());
           }

        return list2 ;
    }

    static List<String> failphonfinder(String input3)
  
    {
        final String Failphonpattern = "[0-5]{1}[0-9]{4}[0-9]*";
        Pattern patten3 = Pattern.compile(Failphonpattern);
        Matcher matcher3 = patten3.matcher(input3);
        List<String> list3 = new ArrayList<String>();
        while (matcher3.find()) 
           {
             list3.add(matcher3.group());
           }
        return list3 ;
     }
    
    

    public static void main(String[] args) throws IOException 

      {

    
        List<String> finallist = new ArrayList<String>();	
        List<String> finallist2 = new ArrayList<String>();
//        List<String> Failfinallist = new ArrayList<String>();
        List<String> Failfinallist2 = new ArrayList<String>();
     
    
        try
        
        {
            FileReader fr = new FileReader("C:\\Users\\Admin\\Desktop\\CJ project\\phno.txt");
            FileWriter fw = new FileWriter("C:\\Users\\Admin\\Desktop\\CJ project\\outputfiles\\outallemail.txt");
            BufferedReader br = new BufferedReader(fr);
                  
            while (br.ready())
            {
                String input = br.readLine();
                List<String> emailid = EmailIdETL.emailfinder(input);
                finallist.addAll(emailid);
               
                
            }
          
            
            for(int i =0 ; i < finallist.size(); ++i)
            {
            	
            	fw.write(i +") " +finallist.get(i) +"\n");
            }
          
            fw.close();
            br.close();
            
//            FileReader frfail = new FileReader("C:\\Users\\Admin\\Desktop\\CJ project\\.txt");
//            BufferedReader brfail = new BufferedReader(frfail);
//            while(brfail.ready())
//            {
//            	String input1 = brfail.readLine();
//            	List<String> Failemailid = EmailIdETL.failemailfinder(input1);
//            	Failfinallist.addAll(Failemailid);
//            }
//            brfail.close();
//         
            FileReader fr1 = new FileReader("C:\\Users\\Admin\\Desktop\\CJ project\\phno.txt");
            FileWriter fw1 = new FileWriter("C:\\Users\\Admin\\Desktop\\CJ project\\outputfiles\\out_allPhNo.txt");
            BufferedReader brph1 = new BufferedReader(fr1);
  
            while (brph1.ready()) 
            {
                String input2 = brph1.readLine();
                List<String> phonno = phonfinder(input2);  
                finallist2.addAll(phonno);                    
            }
            
            for(int i = 1; i < finallist2.size() ;++i)
            {
            	fw1.write(i +") " + finallist2.get(i) +"\n");
            }
            
            fw1.close();
            brph1.close();
            
            FileReader fr1failphon = new FileReader("C:\\Users\\Admin\\Desktop\\CJ project\\phno.txt");
            BufferedReader br1failph = new BufferedReader(fr1failphon);
              while(br1failph.ready())
            {
            	String input3 = br1failph.readLine();
            	List<String> phonnof = failphonfinder(input3);
            	 Failfinallist2.addAll(phonnof);
            }

            
            br1failph.close();
           
       	 System.out.println( " ***************************Welcome Ketan Extraction is done ***************************************");
         System.out.println("       ");  
         System.out.println( "***************   "+ finallist.size() + "  TOTAL Records OF EmailId are Extract From file ***********************************");
         finallist.forEach(recordall -> System.out.println(recordall));
         System.out.println("       ");  
         System.out.println("       ");       
         Set<String> newset = new  LinkedHashSet<String>();
         newset.addAll(finallist);
         int filtercnt = newset.size();
         System.out.println( "**************************  " + filtercnt  + "  Records of UNIQUE emailID are found   ***************************************");
         System.out.println("   Unique email ids are : ");
         System.out.println( " ");
         newset.forEach(filter -> System.out.println(filter));
//         System.out.println("  FailEmail id found are  ");
//         Failfinallist.forEach(record1 -> System.out.println( record1));
         System.out.println( " ");
         System.out.println("=====================================================================================================================================================================================================       ");
         System.out.println("       ");
         System.out.println( "***********************   "+ finallist2.size() +"  Records of valid PHON number are found *************************************** ");
         finallist2.forEach(record2 -> System.out.println(record2));
         
         Set<String> newset1 = new  LinkedHashSet<String>();
         newset1.addAll(finallist2);
         System.out.println(  "***********************   "+ newset1.size()  + " Records of UNIQUE Phone Number are found *************************************** ");
         newset1.forEach(filternum -> System.out.println(filternum));  
         System.out.println("       ");
         System.out.println("       ");
         System.out.println(  "***********************   "+  Failfinallist2.size() + "  Records  of failphon Number are found *************************************** ");
         Failfinallist2.forEach(record3 -> System.out.println(record3));
          System.out.println("       ");
           
//         try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql:localhost:3306/mysql" , "root" , "");
//			PreparedStatement ps = (PreparedStatement) con.prepareStatement();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//          
            
          
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
