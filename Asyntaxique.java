/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package COMPILATION;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author h
 */
public class Asyntaxique {
   private static boolean  condIf=false,condStart=false,condFinish=false,start=false,finish=false;
  static String total;
    public Asyntaxique() {
    }
    
    public void syntaxique()
    {

    /**
     *
     */
   // ArrayList <String> Entiers=new ArrayList<String>();
	//ArrayList <String> Réels=new ArrayList<String>();
	
      String id=" ?[a-zA-Z](_?[a-zA-Z0-9]+)* ?";
      String i="[0-9]+";
      String f="";
      String s=miniprojet.contenu;
      
     
          StringTokenizer k= new StringTokenizer(s,"[\n]");
          int tour=0;
          int knb=k.countTokens();
         
           total="";
          while(k.hasMoreTokens()) {
        	 // System.out.println("in while");
        	String c=(String) k.nextElement();
        	String j=c.trim(); 
        	String res="";
        	Boolean show=true;
        	//check le debut 
        	//System.out.println(j);
        	if(j.matches("Start_Program")) { System.out.println("affiche");if (tour==0) {Asyntaxique.start=true; res=j+": Début de programme";System.out.println("affiche");}else{res=j+":Erreur  Début de programme mal placée";}}
        	//check la dÃ©claration entiere et rÃ©eles
        	else if(j.matches("Int_Number.+") || j.matches("Real_Number.+")) { int x=0;
        		                                boolean iden=false,virgule=false,end=false,start=false,dots=false;
        	                                    StringTokenizer st=new StringTokenizer(j,"[,|:|;;]",true);
        	                                    int nb=st.countTokens();
        	                                    //System.out.println("there are tokens"+nb);
        	                                    while(st.hasMoreTokens()) {
        	                                     String st1=st.nextToken();
                                            	//	System.out.println("its the token"+st1);
        	                                    if(x==0) { if(st1.equals("Int_Number ")) {start=true;} 
        	                                    	       else if(st1.equals("Real_Number ")) {start=true;}/*System.out.println("start");*/}
        	                                    else if(x==1 && st1.equals(":")) {dots=true; /*System.out.println("dots");*/ }
        	                                    else if(x%2==0 && st1.matches(id)) {iden=true;}
        	                                    else if(x%2 !=0 && st1.equals(",")) { virgule=true; /*System.out.println("virgule");*/}
        	                                    else if(st1.matches(";") && x+2==nb) {/*System.out.println("end");*/end=true;}
        	                                    else if(st1.matches(";") && x+1==nb && end) {/*System.out.println("end");*/end=true;}
        	                                    //else {iden=false;}
        	                                    x++;
        	 									}
        	                                    if(nb>5) { 
        	                                    if(start) {
        	                                        if(iden && virgule && start && dots && end) {res=j+":Instruction de  Declaration Correcte";}
        	                                        else {res=j+"Instruction de Declaration fausse ";}}
        	                                    
        	                                                }
        	                                     if(nb==5) { 
            	                                  if(start) {
            	                                        if(iden  && start && dots && end) {res=j+": Instruction de Declaration Correcte ";}
            	                                        else {res=j+" Instruction de Declaration fausse ";}}
            	                                   
            	                                    }
        	                                  
        	                                   
        	                                  }
        	//check le give
        	else if(j.matches("\\s*Give.+")) {// System.out.println(j+"c'est une aff");
        		                         boolean give=false,dots2=false,iden2=false,num=false,end2=false;
        		                         StringTokenizer st2=new StringTokenizer(j);
        		                         int nb2=st2.countTokens();
        		                         int x1=0;
        	                        	 //System.out.println("there are tokens"+nb2);
        		                          while(st2.hasMoreTokens()) {
        		                           String st21=st2.nextToken();
        		                         //System.out.println(st21);
                                   	     if(x1==0 && st21.equals("Give")) {give=true;/* System.out.println("give");*/}
                                   	     else if(x1==1) {if(st21.matches(id)) {iden2=true;}
                                   		                 }
                                   	     else if(x1==2 && st21.matches(":")) {dots2=true;}
                                   	     else if (x1==3 && st21.matches("[0-9]+") || st21.matches("[0-9]+.[0-9]+")) {num=true;/* System.out.println("num");*/}
                                   	     else if(st21.matches(";;") && x1==4) {/*System.out.println("end");*/end2=true;}
                                   	    x1++;
        		                         }
        		                        if(iden2  && give && dots2 && end2 && num) {res=j+": Affectation correcte d'une valeur a une variable;";}
                                         else {res=j+":Erreur Affectation fausse d'une valeur a une variable;";}  
        								}
        	//check la condition
        	else if(j.matches("If.+")) { 
        							String givee="Give [a-zA-Z](_?[a-zA-Z0-9]+)* : [0-9]+ ;;";
        							String giver="Give [a-zA-Z](_?[a-zA-Z0-9]+)* : [0-9]+.[0-9]+ ;;";
        							String showm="ShowMes : \".+\" ;;";
        							String affect="Affect [a-zA-Z](_?[a-zA-Z0-9]+)* to [a-zA-Z](_?[a-zA-Z0-9]+)* ;;";
        							String showv="ShowVal : [a-zA-Z](_?[a-zA-Z0-9]+)* ;;";
        						if(j.matches("If -- [a-zA-Z](_?[a-zA-Z0-9]+)*(<|>|=)[a-zA-Z](_?[a-zA-Z0-9]+)* -- "+"("+givee+"|"+giver+"|"+showm+"|"+affect+"|"+showv+")"))
        												{ Asyntaxique.condIf=true;res=j+"condition alors action";}
        												else {res=j+"Vérifier la syntaxe de la condition";}
        											 }
        	//check le else
        	else if(j.matches("\\s*Else")) {if(Asyntaxique.condIf) {res=j+" Sinon";}else if(!Asyntaxique.condIf) {res=j+" Erreur sinon non permis";}
        	  
        	                            }
        	//check le Start
        	else if(j.matches("\\s*Start")) { res=j+" :Debut d'un bloc"; Asyntaxique.condStart=true;}
           //check le finish
        	else if(j.matches("\\s*Finish")) { if(Asyntaxique.condStart) {res=j+" :Fermeture d'un bloc"; Asyntaxique.condFinish=true;} else res="Erreur fermeture non permise";  }
        	   //check le Affect
        	else if(j.matches("\\s*Affect.+")) {
        								 boolean aff=false,dots2=false,var1=false,var2=false,end2=false,affE=false,affF=false;
        		                         StringTokenizer st2=new StringTokenizer(j);
        		                         int nb2=st2.countTokens();
        		                         int x1=0;
        	                        	 //System.out.println("there are tokens"+nb2);
        		                          while(st2.hasMoreTokens()) {
        		                          String st21=st2.nextToken();
        		                         //System.out.println(st21);
                                   	     if(x1==0 && st21.equals("Affect")) {aff=true; /*System.out.println("aff");*/}
                                   	     else if(x1==1) {if(st21.matches(id)) {var1=true;  /*System.out.println("id1");*/}
                                   		                 }
                                   	     else if(x1==2 && st21.matches("to")) {dots2=true;  /*System.out.println("toto");*/}
                                   	     else if (x1==3) { if(st21.matches(id))var2=true;
                                   	  System.out.println("id2");
                                   	    	             }
                                   	     else if(st21.matches(";;") && x1==4) {/*System.out.println("end");*/end2=true; /*System.out.println("endyendy");*/}
                                   	    x1++;
        		                         }
        		                        if(aff  && var1 && dots2 && end2 && var2) {res=j+": Affectation correcte d'une variable a une autre variable;";}
                                         else {res=j+":Erreur Affectation fausse d'une variable a une variable;";}  
        								}
        //check le show message
        	if(j.matches("\\s*ShowMes : \".+[^\"]\" ;;")) {res=j+": Affichage d'un message a l'écran";}
         //check le show value
        	if(j.matches("\\s*ShowVal.+")) { boolean showV=false,var=false,dots=false,end=false;String value = null;
        								StringTokenizer st2=new StringTokenizer(j);
        								int x1=0;
        								  while(st2.hasMoreTokens()) {
            		                          String st21=st2.nextToken();
            		                          System.out.println(st21);
                                  	     if(x1==0 && st21.equals("ShowVal")) {showV=true; }
                                  	     else if(x1==1 && st21.matches(":")) {dots=true; }
                                  	     else if(x1==2) { 
                                  	    	if(st21.matches(id))var=true;
                                  	                    }
                                  	   else if(st21.matches(";;") && x1==3) {end=true;}
                                  	   
        								                  x1++;           }
        							if(showV && dots && end && var) {res=j+":affichage de la valeur d'une variable: ";}
                                         else {res=j+":Erreur d'affichage de la valeur d'une  variable;";}  
        								  
        								}
        	
        	//les commentaires
        	if(j.matches("//. "+".+")) {res=j+" :Un Commentaire";}
         //la fin du pgme
        	if(j.matches("End_Program")) {if(Asyntaxique.start && !Asyntaxique.finish) { Asyntaxique.finish=true; res=j+": Fin du programme ";}
        								else {res="erreur Verifier l'emplacement ou le nombre d'occurnce de l'instruction Start/Fin Program";} }
        	 String Phrases=res;                                 
             total+=Phrases+System.getProperty("line.separator");;
          }
         
       
                
    }
    }


  
