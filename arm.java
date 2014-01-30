//package FinalArm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

class arm {

    static String FullString1 = "";
    static String FullString2 = "";
    static String FullString3 = "";
    static String item_Name[] = new String[91];

    public static void main(String args[])
            throws SQLException, IOException, InterruptedException {
        // Load the Oracle JDBC driver
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        FileInputStream fis = new FileInputStream("system.in");
        DataInputStream din = new DataInputStream(fis);
        BufferedReader br = new BufferedReader(new InputStreamReader(din));
        String str = "";
        int k = 0;
        //int count = 0;
        //int y[] = new int[4];




        String op[] = new String[8];
        while ((str = br.readLine()) != null && (k < op.length)) {
            op[k] = str;
            k++;
        }
        din.close();

        int i_uname1 = op[0].indexOf("=");
        int i_uname2 = op[0].indexOf(",");
        String username = (op[0].substring(i_uname1 + 1, i_uname2)).trim();
        String password = (op[0].substring(op[0].indexOf("=", i_uname2) + 1, op[0].length())).trim();

        String task11 = op[1].substring(op[1].indexOf("=") + 1, op[1].indexOf("%")).trim();
        int stask1 = Integer.parseInt(task11);
        String task22 = op[2].substring(op[2].indexOf("=") + 1, op[2].indexOf("%")).trim();
        int stask2 = Integer.parseInt(task22);

        String task33_s = op[3].substring(op[3].indexOf("=") + 1, op[3].indexOf("%")).trim();
        int stask3 = Integer.parseInt(task33_s);

        String task33_i = op[3].substring(op[3].lastIndexOf("=") + 1, op[3].length()).trim();
        int size3 = Integer.parseInt(task33_i);

        int imm1 = op[4].indexOf("=") + 1;
        String task44_s = op[4].substring(op[4].indexOf("=") + 1, op[4].indexOf("%")).trim();
        int task4_s = Integer.parseInt(task44_s);
        int imm2 = op[4].indexOf("%") + 1;
        int imm3 = op[4].indexOf("=", imm1) + 1;
        String task44_c = op[4].substring(imm3, op[4].indexOf("%", imm3 + 1)).trim();
        int task4_c = Integer.parseInt(task44_c);
        String task44_i = op[4].substring(op[4].lastIndexOf("=") + 1, op[4].length()).trim();
        int task4_size = Integer.parseInt(task44_i);
System.out.println("Hello Started");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
                username, password);

        Connection conn1 = DriverManager.getConnection("jdbc:oracle:thin:hr/hr@oracle1.cise.ufl.edu:1521:orcl",
                username, password);
        System.out.println("Got connection Started");
        System.out.println("username"+username+" "+password+" Stask1 "+stask1+" stask2:"+"stask3:"+stask3+" size3"+size3+" "+task4_s+" " +task4_c+" "+task4_size);
        //Before Drop Tables..
Statement stmtcreatetable1 = conn.createStatement();
ResultSet rsetstmtcreatetable1=stmtcreatetable1.executeQuery("select count(*) from user_tables where table_name = 'ITEMS' ");
rsetstmtcreatetable1.next();
int cr1=rsetstmtcreatetable1.getInt(1);
rsetstmtcreatetable1.close();
Statement stmtcreatetable12 = conn.createStatement();
//Statement stmtcreatetable13 = conn.createStatement();
if(cr1==1)
{
stmtcreatetable12.executeQuery("drop table ITEMS");
 stmtcreatetable12.executeQuery("create table Items(Itemid Number, Itemname varchar2(50))");
}
else
    stmtcreatetable12.executeQuery("create table Items(Itemid Number, Itemname varchar2(50))");

//Trans
ResultSet rsetstmtcreatetable2=stmtcreatetable1.executeQuery("select count(*) from user_tables where table_name = 'TRANS' ");
rsetstmtcreatetable2.next();
int cr2=rsetstmtcreatetable2.getInt(1);
rsetstmtcreatetable2.close();
if(cr2==1)
{
stmtcreatetable12.executeQuery("drop table TRANS");
 stmtcreatetable12.executeQuery("create table TRANS(transid number,itemid Number)");
}
else
    stmtcreatetable12.executeQuery("create table TRANS(transid number,itemid Number)");

//Ftask1
ResultSet rsetstmtcreatetable3=stmtcreatetable1.executeQuery("select count(*) from user_tables where table_name = 'FTASK1' ");
rsetstmtcreatetable3.next();
int cr3=rsetstmtcreatetable3.getInt(1);
rsetstmtcreatetable3.close();
if(cr3==1)
{
stmtcreatetable12.executeQuery("drop table FTASK1");
 stmtcreatetable12.executeQuery("create table Ftask1(itemid number,support number)");
}
else
   stmtcreatetable12.executeQuery("create table Ftask1(itemid number,support number)");

//F1task2
ResultSet rsetstmtcreatetable4=stmtcreatetable1.executeQuery("select count(*) from user_tables where table_name = 'F1TASK2' ");
rsetstmtcreatetable4.next();
int cr4=rsetstmtcreatetable4.getInt(1);
rsetstmtcreatetable4.close();
if(cr4==1)
{
stmtcreatetable12.executeQuery("drop table F1TASK2");
  stmtcreatetable12.executeQuery(" create table F1task2(transid number,itemid number)");
}
else
    stmtcreatetable12.executeQuery(" create table F1task2(transid number,itemid number)");

//f2task2
ResultSet rsetstmtcreatetable5=stmtcreatetable1.executeQuery("select count(*) from user_tables where table_name = 'F2TASK2' ");
rsetstmtcreatetable5.next();
int cr5=rsetstmtcreatetable5.getInt(1);
rsetstmtcreatetable5.close();
if(cr5==1)
{
stmtcreatetable12.executeQuery("drop table F2TASK2");
  stmtcreatetable12.executeQuery("create table F2task2(itemid1 number,itemid2 number,support number)");
}
else
    stmtcreatetable12.executeQuery("create table F2task2(itemid1 number,itemid2 number,support number)");
stmtcreatetable1.close();
stmtcreatetable12.close();

rsetstmtcreatetable2.close();
rsetstmtcreatetable3.close();
rsetstmtcreatetable4.close();
rsetstmtcreatetable5.close();



String username_padd="sqlplus "+ username+"@orcl/"+ password +" @arm.sql";
System.out.println(username_padd);
//Process p = Runtime.getRuntime().exec("sqlplus "+ username+"@orcl/"+ password +" @arm.sql");
//p.waitFor();
Process p = Runtime.getRuntime().exec("sqlplus " +username+ "@orcl/"+password+ "@arm.sql");
p.waitFor();








        
        
        Statement stmt5 = conn.createStatement();
        Statement stmttask1 = conn.createStatement();
        Statement stmttask2 = conn.createStatement();
        
        Statement stmt = conn.createStatement();
        CallableStatement callst = null;

        create_table(stmt5, conn, size3);
//---------------------------------------------Task1-------------------------------
        String task1_s = "{call task__one(?)}";
        callst = conn.prepareCall(task1_s);
        callst.setInt(1, stask1);
        callst.executeUpdate();

        callst.close();
        // Create a Statement
        Statement stmt1 = conn.createStatement();
        FullString1 = task1_op(stmttask1);
        System.out.println("Task1 Completed");
        Printfile(FullString1, 1);
        stmttask1.close();
        //Printing Task1

        //---------------------------------------------Task2-------------------------------
        String task2_s = "{call task__two(?)}";
        callst = conn.prepareCall(task2_s);
        callst.setInt(1, stask2);
        callst.executeUpdate();
        //System.out.println("-----------------------Task2---------------");
        FullString2 = task1_op(stmt1);

        FullString2 += task2_op(stmttask2);
        callst.close();
        stmttask2.close();
        System.out.println("Task2 Completed");
        Printfile(FullString2, 2);
        System.out.println("-----------Task3----------");


        String task3_1s = "{call task__one(?)}";
        callst = conn.prepareCall(task3_1s);
        callst.setInt(1, stask3);
        callst.executeUpdate();
        callst.close();
        stmttask1 = conn.createStatement();
        stmttask1.execute("delete from fiset3_1");
        stmttask1.close();
        String fset_1_s = "insert into fiset3_1(itemid1,support_val) select * from ftask1";
stmt1.close();
stmt1 = conn.createStatement();
        stmt1.execute(fset_1_s);
        stmt1.close();
        stmt1 = conn.createStatement();
        stmt1.execute("delete from ciset3_1");
        stmt1.close();
        String cset_1 = "insert into ciset3_1(itemid1)select itemid1 from fiset3_1 ";
        stmt.execute(cset_1);
        stmt.close();
//int size=4,i=2;
        for (int i = 2; i <= size3; i++) {
            String task3_s1 = "{call CANDI_SETS(?)}";
//callst = conn.prepareCall(task3_s1);
            CallableStatement cstmt2 = conn.prepareCall(task3_s1);
            cstmt2.setInt(1, i);
            cstmt2.executeUpdate();
            cstmt2.close();
            String task3_s2 = "{call F_ITEM_SETS(?,?)}";
            CallableStatement cstmt3 = conn.prepareCall(task3_s2);
            cstmt3.setInt(1, i);
            cstmt3.setInt(2, stask3);
            cstmt3.executeUpdate();
            cstmt3.close();

        }
        System.out.println("Done");
         stmttask2=conn.createStatement();
        FullString3 = task3_op(stmttask2,size3 );
       // System.out.println("Sixe3:"+size3+"\n"+FullString3);
       // System.out.println("Task3 Completed");
stmttask1=conn.createStatement();
        Printfile(FullString3, 3);
        task4(stmttask1, conn, task4_s, task4_c, size3, task4_size);
        stmttask1.close();
        Statement stmt2del = conn.createStatement();
        del_table(stmt2del, conn1, task4_size);
        stmt2del.close();
        conn.close(); 
        conn1.close(); 
        System.out.println("Finished");
        
        stmttask2.close();
        
        
    }

    public static void create_table(Statement stmtcreate, Connection conn, int size3) throws SQLException, FileNotFoundException, IOException {

        
       
       create_tables( size3, conn);

        stmtcreate = conn.createStatement();
        stmtcreate = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        conn.setAutoCommit(false);
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        br1 = new BufferedReader(new FileReader("items.dat"));
        br2 = new BufferedReader(new FileReader("trans.dat"));
        String line1 = "";
        String line2 = "";
        String a[] = new String[2];
        int indx = 0;
        while ((line1 = br1.readLine()) != null) {
            a = line1.split(",");
            item_Name[indx] = a[1].substring(1, a[1].length() - 1).trim();
            indx++;
            stmtcreate.addBatch("insert into ITEMS (itemid,itemname) values( " + line1 + ")");

        }


        stmtcreate.executeBatch();
        stmtcreate.close();
        System.out.println("Items Inserted");
        conn.commit();
        stmtcreate = conn.createStatement();
        while ((line2 = br2.readLine()) != null) {

            stmtcreate.addBatch("insert into TRANS (transid,itemid) values(  " + line2 + ")");


        }
        stmtcreate.executeBatch();
        System.out.println("Trans Inserted");
        conn.commit();
        //stmtcreate.close();

    }

    public static String task1_op(Statement stmttaskone) throws SQLException {

        String task1_S = "select (select i.itemname from items i where i.itemid=f.itemid ),f.support from ftask1 f";
        ResultSet rset11 = stmttaskone.executeQuery(task1_S);
        String FullString = "";
        String FullString1 = "";
        while (rset11.next()) {
            String itemname_t1 = rset11.getString(1);
            
            float sup_t1 = rset11.getFloat(2);
            FullString = "{" + itemname_t1 + "},s=" + sup_t1 + "%" + "\n";

            FullString1 += FullString;
        }
        
rset11.close();

        return (FullString1);
    }

    public static String task2_op(Statement stmttasktwo) throws SQLException {
       
        String FullString = "";
        String FullString2 = "";
        String task2 = "select i1.itemname,i2.itemname,f.support from items i1,items i2,F2Task2 f where i1.itemid=f.itemid1 and i2.itemid=f.itemid2 ";
        ResultSet rsettask2 = stmttasktwo.executeQuery(task2);
        while (rsettask2.next()) {
            String itemname1_2 = rsettask2.getString(1);
            String itemname2_2 = rsettask2.getString(2);
            float t2_count = rsettask2.getFloat(3);

            FullString = "{" + itemname1_2 + "," + itemname2_2 + "},s=" + t2_count + "%" + "\n";
            FullString2 += FullString;

        }
        
        rsettask2.close();
       // rset1.close();
        return (FullString2);
        
    }

    public static String task3_op(Statement stmttaskthree, int t3_size) throws SQLException {

        int j = 1, actualsize = t3_size;
        String FullString1 = "";

        String itemname_3[] = new String[actualsize + 1];
        String Final_string = "";
        for (; j <= actualsize; j++) {

            int size = j;
            int i = 1;
            String select = "select ";
            String from = "from ";
            String where = "where ";
            String Actual_string = "";
            for (; i <= size; i++) {

                if (i <= size - 1) {
                    select += "i" + String.valueOf(i) + ".itemname ,";
                    from += "items i" + String.valueOf(i) + ",";
                    where += "f.itemid" + String.valueOf(i) + "=i" + String.valueOf(i) + ".itemid and ";
                } else {
                    select += "i" + String.valueOf(i) + ".itemname ," + "f.support_val";
                    from += "items i" + String.valueOf(i) + "," + "fiset3_" + String.valueOf(i) + " f";
                    where += "f.itemid" + String.valueOf(i) + "=i" + String.valueOf(i) + ".itemid ";
                }

            }
            FullString1 = select + " " + from + " " + where;
            //System.out.println(FullString);
            ResultSet rsettaskthree = stmttaskthree.executeQuery(FullString1);


            while (rsettaskthree.next()) {
                Actual_string = "{";
                for (int z = 1; z <= size; z++) {
                    itemname_3[z] = rsettaskthree.getString(z);
                    if (z != size) {
                        Actual_string += itemname_3[z] + ",";
                    } else {
                        Actual_string += itemname_3[z];
                    }

                }
                float t3_count = rsettaskthree.getFloat(size + 1);
                Actual_string += "},s=" + t3_count + "% \n";
                Final_string += Actual_string;
            }
            
        rsettaskthree.close();  

        }
        // System.out.println(Final_string);
        
        
        
        return (Final_string);


    }

    public static void Printfile(String s1, int i) {
        try {
            FileWriter fw1 = new FileWriter("system.out." + String.valueOf(i));
            BufferedWriter out = new BufferedWriter(fw1);
            out.write(s1);
            out.close();
            fw1.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static void del_table(Statement stmtdel, Connection conn1, int size4) throws SQLException {
        CallableStatement call2d = conn1.prepareCall("{call bef_drop()}");
        
        call2d.executeUpdate();
        call2d.close();
        Statement stmtdel5= conn1.createStatement();
        CallableStatement call2 = conn1.prepareCall("{call drp_table(?)}");
        call2.setInt(1, size4);
        call2.executeUpdate();
        call2.close();
        stmtdel5.close();

    }

    public static void task4(Statement stmt, Connection conn, int support4, int confi4, int size3, int size4) throws SQLException {
        Statement stmt1 = conn.createStatement();
       // Statement stmt31 = conn.createStatement();
        //System.out.println("Confidence:" + confi4);
        String FullString_task4 = "";
        CallableStatement call2 = conn.prepareCall("{call drp_table(?)}");
        call2.setInt(1, size3);
        call2.executeUpdate();
       create_tables( size4, conn);
        String task4_1s = "{call task__one(?)}";

        CallableStatement callst = conn.prepareCall(task4_1s);
        callst.setInt(1, support4);
        callst.executeUpdate();
        callst.close();
        stmt1.execute("delete from fiset3_1");
        stmt1.close();
        stmt1 = conn.createStatement();
        String fset_1_s = "insert into fiset3_1(itemid1,support_val) select * from ftask1";
        stmt1.execute(fset_1_s);
        stmt1.close();
        stmt1 = conn.createStatement();
        stmt1.execute("delete from ciset3_1");
        stmt1.close();
        stmt1 = conn.createStatement();
        String cset_1 = "insert into ciset3_1(itemid1)select itemid1 from fiset3_1 ";
        stmt1.execute(cset_1);

        for (int i = 2; i <= size4; i++) {
            String task3_s1 = "{call CANDI_SETS(?)}";
            CallableStatement cstmt2 = conn.prepareCall(task3_s1);
            cstmt2.setInt(1, i);
            cstmt2.executeUpdate();
            cstmt2.close();
            String task3_s2 = "{call F_ITEM_SETS(?,?)}";
            CallableStatement cstmt3 = conn.prepareCall(task3_s2);
            cstmt3.setInt(1, i);
            cstmt3.setInt(2, support4);
            cstmt3.executeUpdate();
            cstmt3.close();

        }
        int task4_finalcount = 0;
        String FinallyTask4 = "";
        // System.out.println("Size4:"+size4);
        int finalsize = size4;
        for (int q = 2; q <= finalsize; q++) {
            size4 = q;
            String task4_fsupp = "Select * from fiset3_" + String.valueOf(size4);
            // System.out.println(task4_fsupp);
            Statement stmt2 = conn.createStatement();
            ResultSet rsetfirst = stmt2.executeQuery(task4_fsupp);
            while (rsetfirst.next()) {
                int numbr[] = new int[size4];
                float sprcount = 0;
                for (int z = 0; z < size4; z++) {
                    numbr[z] = rsetfirst.getInt(z + 1);
                }

                sprcount = rsetfirst.getFloat(size4 + 1);
                ArrayList<Integer> Supset = new ArrayList<Integer>();
                for (int x : numbr) {
                    if (!Supset.contains(x)) {
                        Supset.add(x);
                    }
                }
                ArrayList<ArrayList<Integer>> subSets = GenerateSubSets(Supset);
                subSets.remove(0);

                subSets.remove(subSets.size() - 1);
                // System.out.println("SuSet:"+Supset);
//System.out.println("Set:"+subSets);
                // dump the subsets one by one
                for (int i = 0; i < subSets.size(); i++) {
                    String Sel = "Select Support_val";

                    Double subcount = 0.0;
                    String from = "";
                    String where = "";
                    from = " from Fiset3_" + subSets.get(i).size() + " f ";
                    where = " where ";
                    for (int j = 0; j < subSets.get(i).size(); j++) {
                        if (j < subSets.get(i).size() - 1) {

                            where += "F.itemid" + String.valueOf(j + 1) + "=" + subSets.get(i).get(j) + " and ";
                        } else {
                            where += "F.itemid" + String.valueOf(j + 1) + "=" + subSets.get(i).get(j);
                        }


                    }

                    String sel_count1 = Sel + from + where;
                    //Statement stmt3 = conn.createStatement();
                    ResultSet rset3 = stmt.executeQuery(sel_count1);

                    while (rset3.next()) {
                        subcount = rset3.getDouble(1);
                    }
                    double task4_confi = sprcount * 100 / subcount;
                    //Confidence Comparsion
                    if (task4_confi >= confi4) {
                        task4_finalcount++;
                        //System.out.print(sel_count1);
                        ArrayList<Integer> leftlist = subSets.get(i);
                        ArrayList<Integer> rightlist = new ArrayList<Integer>();
                        for (int p = 0; p < Supset.size(); p++) {
                            if (leftlist.contains(Supset.get(p))) {
                            } else {
                                rightlist.add(Supset.get(p));
                            }

                        }
                        String leftString = "{{";
                        String Name1 = "";
                        for (int p = 0; p < leftlist.size(); p++) {
                            if (p < leftlist.size() - 1) {
                                Name1 = item_Name[leftlist.get(p)] + ",";
                            } else {
                                Name1 = item_Name[leftlist.get(p)];
                            }
                            leftString += Name1;
                        }
                        leftString += "} - >{";
                        String rightString = "";
                        String Name2 = "";
                        for (int p = 0; p < rightlist.size(); p++) {
                            if (p < rightlist.size() - 1) {
                                Name2 = item_Name[rightlist.get(p)] + ",";
                            } else {
                                Name2 = item_Name[rightlist.get(p)];
                            }
                            rightString += Name2;
                        }
                        rightString += "},s=";
                        FullString_task4 = leftString + rightString + sprcount + "%, c=" + (task4_confi) + "% \n";
                        //System.out.print(FullString_task4);
                        FinallyTask4 += FullString_task4;
                    }
rset3.close();
                }

            }
            rsetfirst.close();
        }
       // System.out.println("Final Count:" + task4_finalcount);
        Printfile(FinallyTask4, 4);
    }

   private static ArrayList<ArrayList<Integer>> GenerateSubSets(
            ArrayList<Integer> set) {

        ArrayList<ArrayList<Integer>> setcollectn = new ArrayList<ArrayList<Integer>>();

        if (set.size() == 0) {
            setcollectn.add(new ArrayList<Integer>());
        } else {
            ArrayList<Integer> ShortSets = new ArrayList<Integer>();

            ShortSets.addAll(set);

            int first = ShortSets.remove(0);
            ArrayList<ArrayList<Integer>> SubSets = GenerateSubSets(ShortSets);
            setcollectn.addAll(SubSets);

            SubSets = GenerateSubSets(ShortSets);

            for (ArrayList<Integer> subset : SubSets) {
                subset.add(0, first);
            }

            setcollectn.addAll(SubSets);
        }

        return setcollectn;
    }
   
 public static void create_tables(int size,Connection conn) throws SQLException
  {
      
  
    Statement stmt=conn.createStatement();
        
        String create_1="";
        String create_2="";
        //int i=0;
        for(int i=1;i<=size;i++)
        {
            create_1="create table fiset3_"+String.valueOf(i)+"(";
            create_2="create table ciset3_"+String.valueOf(i)+"(";
            
            for(int j=1;j<=i;j++)
            {
                if(j!=i)
                {
                    create_1+="itemid"+String.valueOf(j)+" NUMBER"+",";
                    create_2+="itemid"+String.valueOf(j)+" NUMBER"+",";
                }
                else
                {
                    create_2+="itemid"+String.valueOf(j)+" NUMBER"+")";
                    create_1+="itemid"+String.valueOf(j)+" NUMBER"+","+"support_val"+" NUMBER"+")";
                }
            }
            //System.out.println(create_1);
            //System.out.println(create_2);
            stmt.executeUpdate(create_1);
            stmt.executeUpdate(create_2);
           //System.out.println(create_1);
            //System.out.println(create_2);
        }
  }
}
