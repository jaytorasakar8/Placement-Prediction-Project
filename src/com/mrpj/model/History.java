package com.mrpj.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.aliasi.matrix.DenseVector;
import com.aliasi.matrix.Vector;
import com.aliasi.stats.AnnealingSchedule;
import com.aliasi.stats.LogisticRegression;
import com.aliasi.stats.RegressionPrior;

public class History 
{
	////All initializations
	// public  double ssc=9.5, hsc=8.5, sem1=9, sem2=9.29, sem3=9.75, sem4=10, sem5=9.75, sem6=9.92, 
			// ctest=8,cpp=8, java=8.5, aptitude=9.8,basiccomputer=8,returnvalue;
	 
		public  double  ssc,hsc,sem1,sem2,sem3, sem4,sem5,sem6,ctest,cpp,java,aptitude,basiccomputer,returnvalue;
	 
	  	 

    public   Double getPrediction(String a, String b, String c,String d,String e,String f,String g,String h,String p, String q, String r, String s, String t ) throws Exception
    {
    	Connection con;
      String user = "root";
      String password = "";
      String url = "jdbc:mysql://localhost:3306/";
      String dbname = "placement_db";
      String driver = "com.mysql.jdbc.Driver";
      
      Class.forName(driver);
      con = DriverManager.getConnection(url + dbname, user, password);
      con.setAutoCommit(false);
      
  ////////Initialization of static variables
      ssc=Double.parseDouble(a);
      hsc=Double.parseDouble(b);
      sem1=Double.parseDouble(c);
      sem2=Double.parseDouble(d);
      sem3=Double.parseDouble(e);
      sem4=Double.parseDouble(f);
      sem5=Double.parseDouble(g);
      sem6=Double.parseDouble(h);
      ctest=Double.parseDouble(p);
      cpp=Double.parseDouble(q);
      java=Double.parseDouble(r);
      aptitude=Double.parseDouble(s);
      basiccomputer=Double.parseDouble(t);
      
      ssc=ssc/10.0;
      hsc=hsc/10.0;
      ctest=ctest/2.0;
      cpp=cpp/2.0;
      java=java/2.0;
      aptitude=aptitude/2.0;
      basiccomputer=basiccomputer/2.0;
      

  	final Vector[] TEST_INPUTS = new Vector[] 
			    {
			    /* new DenseVector(new double[] { 1, 6.5, 5, 6, 6.5, 6.2, 7, 7.5, 8, 5, 4, 4, 3.5, 4.5}),//1
			        new DenseVector(new double[] { 1,6.5,5.5,6.5,6,6.7,7,7.8,8,5,5.5,4,5,5 }),//1
			        new DenseVector(new double[] { 1,6.8,6,6.5,6.5,7,6.8,7.8,8.7,5.5,6,5.5,6,6 }),//1
			        new DenseVector(new double[] { 1,7,6.5,6.8,6.1,6.4,6.7,7,7.5,5.5,6.5,6,5.5,7}),//0
			        new DenseVector(new double[] { 1,7.2,6.2,7,7.2,7.0,6.8,6.5,6.7,6,7,7.5,7,6.5 }),//1
			        new DenseVector(new double[] { 1,7.8,7.2,7.5,7.8,7.2,7.6,7.1,7.5,6.5,7.5,7,6.5,7 }),
			        new DenseVector(new double[] { 1,8,7.6,8,7.5,7.4,7.2,7.6,7.9,7,7,8,8,7.5 }),
			        new DenseVector(new double[] { 1,8.5,8,8,7.5,7.8,8,8.2,8,7.5,8,8.5,8,7}),
			        new DenseVector(new double[] { 1,9,8.5,8,8.2,7.8,8.5,8.75,8.4,8,8,8,7,8}),
			        new DenseVector(new double[] { 1,9.4,9,8.5,8,9.2,8.9,9.4,9,8.5,8.5,8,7.5,8}),
			        new DenseVector(new double[] { 1,9.4,8.6,9.19,9.37,9.71,9.71,9,9.67,9,8,8,9,7.5}),
			        new DenseVector(new double[] { 1,9.2,7.6,9.07,9.07,9.29,9.46,9.17,9.33,8.5,8,7.5,9.5,8}),
			        new DenseVector(new double[] { 1,9.5,8.5,9,9.29,9.75,10,9.75,9.92,8,8,8.5,9.8,8}),*/
			    		
			        new DenseVector(new double[] { 1,ssc,hsc,sem1,sem2,sem3, sem4,sem5,sem6,ctest,cpp,java,aptitude,basiccomputer }),
			        
			    };
      
        int count=0;
        System.out.println("Student placement prediction using Logistic Regression");
        
        //output array start
        ArrayList<Integer> stores = new ArrayList<Integer>();//for beta
        ArrayList<Double> input1 = new ArrayList<Double>();//for ssc
        ArrayList<Double> input2 = new ArrayList<Double>();//for hsc
        ArrayList<Double> input3 = new ArrayList<Double>();//for sem1
        ArrayList<Double> input4 = new ArrayList<Double>();//for sem1
        ArrayList<Double> input5 = new ArrayList<Double>();//for sem1
        ArrayList<Double> input6 = new ArrayList<Double>();//for sem1
        ArrayList<Double> input7 = new ArrayList<Double>();//for sem1
        ArrayList<Double> input8 = new ArrayList<Double>();//for sem1
        ArrayList<Double> input9 = new ArrayList<Double>();//for c
        ArrayList<Double> input10 = new ArrayList<Double>();//for cpp
        ArrayList<Double> input11 = new ArrayList<Double>();//for java
        ArrayList<Double> input12 = new ArrayList<Double>();//for aptitude
        ArrayList<Double> input13 = new ArrayList<Double>();//for basiccomputer
        ArrayList<Double> input14 = new ArrayList<Double>();//for status
    
        
        String sql = "select * from history_data";
      PreparedStatement pstmt = con.prepareStatement(sql);
      ResultSet rst = pstmt.executeQuery();
      String[] entries =new String[1000];
    while(rst.next())
    {
      //count = rst.getInt("count"); //1
      //id = rst.getInt("id"); //1000 onwards
      entries[0] = rst.getString("beta");
      entries[1] = rst.getString("ssc");
      entries[2] = rst.getString("hsc");
      entries[3] = rst.getString("sem1");
      entries[4] = rst.getString("sem2");
      entries[5] = rst.getString("sem3");
      entries[6] = rst.getString("sem4");
      entries[7] = rst.getString("sem5");
      entries[8] = rst.getString("sem6");
      entries[9] = rst.getString("c");
      entries[10] = rst.getString("cpp");
      entries[11] = rst.getString("java");
      entries[12] = rst.getString("aptitude");
      entries[13] = rst.getString("basiccomputer");
      entries[14] = rst.getString("status");
      
        input1.add(Double.parseDouble(entries[0]));
            input2.add(Double.parseDouble(entries[1]));
            input3.add(Double.parseDouble(entries[2]));
            input4.add(Double.parseDouble(entries[3]));
            input5.add(Double.parseDouble(entries[4]));
            input6.add(Double.parseDouble(entries[5]));
            input7.add(Double.parseDouble(entries[6]));
            input8.add(Double.parseDouble(entries[7]));
            input9.add(Double.parseDouble(entries[8]));
            input10.add(Double.parseDouble(entries[9]));
            input11.add(Double.parseDouble(entries[10]));
            input12.add(Double.parseDouble(entries[11]));
            input13.add(Double.parseDouble(entries[12]));
            input14.add(Double.parseDouble(entries[13]));
            stores.add(Integer.parseInt(entries[14]));
           
    }//end of while loop for entering the data for trainning
    
        
        int[] OUTPUTS = new int[stores.size()];
        for(int i = 0;i < OUTPUTS.length;i++)
        {
        OUTPUTS[i] = stores.get(i);
        }
        
        Double[] db1 = new Double[input1.size()];
        for(int i = 0;i < db1.length;i++)
        {
        db1[i] = input1.get(i);
        }
        
        Double[] db2 = new Double[input2.size()];
        for(int i = 0;i < db2.length;i++)
        {
        db2[i] = input2.get(i);
        }
        
        Double[] db3 = new Double[input3.size()];
        for(int i = 0;i < db3.length;i++)
        {
        db3[i] = input3.get(i);
        }
        
        Double[] db4 = new Double[input4.size()];
        for(int i = 0;i < db4.length;i++)
        {
        db4[i] = input4.get(i);
        }
        
        Double[] db5 = new Double[input5.size()];
        for(int i = 0;i < db5.length;i++)
        {
        db5[i] = input5.get(i);
        }
        
        Double[] db6 = new Double[input6.size()];
        for(int i = 0;i < db6.length;i++)
        {
        db6[i] = input6.get(i);
        }
        
        Double[] db7 = new Double[input7.size()];
        for(int i = 0;i < db7.length;i++)
        {
        db7[i] = input7.get(i);
        }
        
        Double[] db8 = new Double[input8.size()];
        for(int i = 0;i < db8.length;i++)
        {
        db8[i] = input8.get(i);
        }
        
        Double[] db9 = new Double[input9.size()];
        for(int i = 0;i < db9.length;i++)
        {
        db9[i] = input9.get(i);
        }
        
        Double[] db10 = new Double[input10.size()];
        for(int i = 0;i < db10.length;i++)
        {
        db10[i] = input10.get(i);
        }
        
        Double[] db11 = new Double[input11.size()];
        for(int i = 0;i < db11.length;i++)
        {
        db11[i] = input11.get(i);
        }
        
        Double[] db12 = new Double[input12.size()];
        for(int i = 0;i < db12.length;i++)
        {
        db12[i] = input12.get(i);
        }
        
        Double[] db13 = new Double[input13.size()];
        for(int i = 0;i < db13.length;i++)
        {
        db13[i] = input13.get(i);
        }
        
        Double[] db14 = new Double[input14.size()];
        for(int i = 0;i < db14.length;i++)
        {
        db14[i] = input14.get(i);
        }
        
        
        Vector[] INPUTS = new Vector[OUTPUTS.length];
        for(int i=0 ; i< db1.length;i++)
        {
            
            INPUTS[i]=new DenseVector(new double[]
            		{db1[i],db2[i],db3[i],db4[i],db5[i],db6[i],db7[i],db8[i],db9[i],db10[i],db11[i],db12[i],db13[i],db14[i]});
            
          System.out.println("Training Data:-"+" "+INPUTS[i]);
            
            count++;
            
        }
        System.out.println("No. of tuples:-"+" "+count);
        
       //output array end
        
        //start of training data
     
        
        //end of trainig data
        
        
        LogisticRegression regression
            = LogisticRegression.estimate(INPUTS,
                                          OUTPUTS,
                                          RegressionPrior.noninformative(),
                                          AnnealingSchedule.inverse(.000003,1000),
                                          null, // reporter with no feedback
                                          0.000000001, // min improve
                                          1, // min epochs
                                          5000); // max epochs
        
        Vector[] betas = regression.weightVectors();
        
        for (int outcome = 0; outcome < betas.length; ++outcome)
        {
             //logistic regression coefficients
             System.out.println("\nNumber of Independent Variables: "+betas[outcome].numDimensions());
            for (int i = 0; i < betas[outcome].numDimensions(); ++i)
               System.out.printf(" %6.2f",betas[outcome].value(i));
            System.out.println();
        }
        
 System.out.println("\nInput Vector Outcome Conditional Probabilities");
        for (Vector testCase : TEST_INPUTS) 
        {
            
            double[] input_case=new double[13];
            double[] conditionalProbs = regression.classify(testCase);
           
           System.out.print("\nTestCase=" + testCase.numDimensions()+"\n");
            
           System.out.println("Real-time DB Input");
            
            for (int i = 1; i < testCase.numDimensions(); ++i) {
               System.out.printf("%3.1f ",testCase.value(i));
                input_case[i-1]= testCase.value(i);
            }
            //DB comparision
            History spp=new History();
            spp.Decision(input_case);
            //input_case[]=null;
            
            
            System.out.println("\nProbability: "+conditionalProbs.length+"\n");
            for (int k = 0; k < conditionalProbs.length; k++) 
            {
                System.out.printf(" p(%d|input)=%4.2f ",k,conditionalProbs[k]);
                 returnvalue=conditionalProbs[k];
                 System.out.println("Return value is: "+ (returnvalue*10));
                       
            }
            System.out.println();
            
        }
        
        con.close();
        con=null;
        
        return  returnvalue*100.0;

    }
    //end of main method
    
        
    public String grading(double avg)
    {
        
     if(avg<=5.0)
     {
       return "1";  
     }
     if(avg > 5 && avg <= 6.5)
     {
       return "2";  
     }
     if(avg > 6.5 && avg <= 7.5)
     {
       return "3";  
     }
     if(avg > 7.5 && avg <= 8.5)
     {
       return "4";  
     }
     if(avg > 8.5)
     {
       return "5";  
     } 
      return null;
    }
    //end of grading
    
  
    public void Decision(double INPUT_CASE[] )
    {
     String result; 
     double cgpi_avg= (INPUT_CASE[2]+INPUT_CASE[3]+INPUT_CASE[4]+INPUT_CASE[5]+INPUT_CASE[6]+INPUT_CASE[7])/6 ;
     result=grading(cgpi_avg);   
     System.out.println("\n"+"Your Academic Status:- "+result);
     result=null;
     
     result=grading(INPUT_CASE[8]);
     System.out.println("Your Aptitude Status:- "+result);
     result=null;
     
     result=grading(INPUT_CASE[9]);
     System.out.println("Your C Programming Status:- "+result);
     result=null;
     
     result=grading(INPUT_CASE[10]);
     System.out.println("Your C++ Programming Status:- "+result);
     result=null;
     
     result=grading(INPUT_CASE[11]);
     System.out.println("Your Java Programmig Status:- "+result);
     result=null;
     
     result=grading(INPUT_CASE[12]);
     System.out.println("Your Communication Skills:- "+result);
     result=null;
     
     
    }///end of Decision

}/////end of history class
