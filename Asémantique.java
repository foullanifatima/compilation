package COMPILATION;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Asémantique {
 private static boolean condIf=false,condStart=false,condFinish=false,start=false,finish=false;
static String total=" "; 
 
 
 public Asémantique() {}
public void ouvre() {
	
	ArrayList <String> Entiers=new ArrayList<String>();
	ArrayList <String> Réels=new ArrayList<String>();
   
    String id=" ?[a-zA-Z](_?[a-zA-Z0-9]+)* ?";
    String i="[0-9]+";
    String f="";
      String s=miniprojet.contenu;
      //System.out.println(s);
         //the tkenizer by statement or by new line
          StringTokenizer k= new StringTokenizer(s,"[\n]");
          int tour=0;
          int knb=k.countTokens();
         // String total="";
          while(k.hasMoreTokens()) {
        	  String res=" ";
        	String c=(String) k.nextElement();
        	String j=c.trim();
        	///System.out.println(j);
        	//System.out.println("in while");
        	Boolean show=true;
        	//check le debut 
        	if(j.matches("Start_Program")) { if (tour==0) {Asémantique.start=true; res=j+": Début de programme";}else{res=j+":Erreur  Début de programme mal placé";}}
        	//check la déclaration entiere et réeles
        	else if(j.matches("Int_Number.+") || j.matches("Real_Number.+")) { int x=0;
        		                                boolean iden=false,virgule=false,end=false,startI=false,dots=false,startR=false;
        	                                    StringTokenizer st=new StringTokenizer(j,"[,|:|;;]",true);
        	                                    int nb=st.countTokens();
        	                                   
        	                                    while(st.hasMoreTokens()) {
        	                                     String st1=st.nextToken();
    	                                    	
        	                                    if(x==0) { if(st1.equals("Int_Number ")) {startI=true;} 
        	                                    	       else if(st1.equals("Real_Number ")) {startR=true;}/*System.out.println("start");*/}
        	                                    //le x veut dire ou je suis 
        	                                    else if(x==1 && st1.equals(":")) {dots=true; /*System.out.println("dots");*/ }
        	                                    else if(x%2==0 && st1.matches(id)) { if(!Entiers.contains(st1) && startI) 
        	                                    	                                     {Entiers.add(st1); /*System.out.println("iden ");*/ iden=true;}
        	                                    										else if(!Réels.contains(st1) && startR) 
        	                                    										{Réels.add(st1); iden=true; /*System.out.println("iden ");*/}
        	                                    										}
        	                                    	
        	                                    else if(x%2 !=0 && st1.equals(",")) { virgule=true; /*System.out.println("virgule");*/}
        	                                    else if(st1.matches(";") && x+2==nb) {/*System.out.println("end");*/end=true;}
        	                                    else if(st1.matches(";") && x+1==nb && end) {/*System.out.println("end");*/end=true;}
        	                                    else {iden=false;}
        	                                    x++;
        	 									}
        	                                    if(nb>5) { 
        	                                    if(startI) {
        	                                        if(iden && virgule && startI && dots && end) {res=j+": Declaration Correcte de nombres entier;";}
        	                                        else {res=j+"Declaration fausse de variables entieres ";}}
        	                                    else  if(startR) {
        	                                    	if(iden && virgule && startR && dots && end) {res=j+": Declaration Correcte de variables Réels;";}
            	                                    else {res=j+"Declaration fausse de variables Réeles ";}
        	                                                     }
        	                                                }
        	                                    else  if(nb==5) { 
            	                                  if(startI) {
            	                                        if(iden  && startI && dots && end) {res=j+": Declaration Correcte de nombres entier;";}
            	                                        else {res=j+"Declaration fausse de variables entieres ";}}
            	                                   else  if(startR) {
            	                                    	if(iden  && startR && dots && end) {res=j+": Declaration Correcte de variables Réels;";}
                	                                    else {res=j+"Declaration fausse de variables Réeles ";}
            	                                    }
            	                                    }
        	                                    else {res=j+"la declaration est fause";}
        	                                     
        	                                  
        	                                   
        	                                  }
        	//check le give
        	else if(j.matches("\\s*Give.+")) {// System.out.println(j+"c'est une aff");
        		                         boolean give=false,dots2=false,iden2=false,num=false,end2=false,In=false,Re=false;
        		                         StringTokenizer st2=new StringTokenizer(j);
        		                         int nb2=st2.countTokens();
        		                         int x1=0;
    		                        	 //System.out.println("there are tokens"+nb2);
        		                          while(st2.hasMoreTokens()) {
        		                           String st21=st2.nextToken();
        		                         //System.out.println(st21);
                                   	     if(x1==0 && st21.equals("Give")) {give=true; System.out.println("give");}
                                   	     else if(x1==1) {for(String str:Entiers) {str=str.trim(); if(str.equals(st21)) {iden2=true;  In=true;}}
                                   	     				for(String str:Réels) {System.out.println("fetch in reals");str=str.trim();if(str.equals(st21)) { iden2=true; Re=true;}}
                                   		                 }
                                   	     else if(x1==2 && st21.matches(":")) {dots2=true;  }
                                   	     else if (x1==3) {if(In) {System.out.println("in In");if(st21.matches("[0-9]+")) {num=true; }}
                                   	     				 if(Re) {System.out.println("in Re");if(st21.matches("[0-9]+.[0-9]+")) {num=true;}}			}
                                   	     else if(st21.matches(";;") && x1==4) {end2=true;}
                                   	    x1++;
        		                         }
        		                        if(iden2  && give && dots2 && end2 && num) {res=j+": Affectation correcte d'une valeur a une variable;";}
 	                                    else {res=j+":Erreur Affectation fausse d'une valeur a une variable;";}  
        								}
        	//check la condition
        	else if(j.matches("\\s*If.+")) { 
        												
        String givee="Give [a-zA-Z](_?[a-zA-Z0-9]+)* : [0-9]+ ;;";
				String giver="Give [a-zA-Z](_?[a-zA-Z0-9]+)* : [0-9]+.[0-9]+ ;;";
				String showm="ShowMes : \".+\" ;;";
				String affect="Affect [a-zA-Z](_?[a-zA-Z0-9]+)* to [a-zA-Z](_?[a-zA-Z0-9]+)* ;;";
				String showv="ShowVal : [a-zA-Z](_?[a-zA-Z0-9]+)* ;;";
			if(j.matches("If -- [a-zA-Z](_?[a-zA-Z0-9]+)*(<|>|=)[a-zA-Z](_?[a-zA-Z0-9]+)* -- "+"("+givee+"|"+giver+"|"+showm+"|"+affect+"|"+showv+")"))
									         {    Asémantique.condIf=true;res=j+"condition alors action";}
        												else {res=j+"Vérifier la syntaxe de la condition";}
        											 }
        	//check le else
        	else if(j.matches("\\s*Else")) {if(Asémantique.condIf) {res=j+" Sinon";}else if(!Asémantique.condIf) {res=j+" Erreur sinon non permis";}
        	  
        	                            }
        	//check le Start
        	else if(j.matches("\\s*Start")) { res=j+" :Debut d'un bloc"; Asémantique.condStart=true;}
           //check le finish
        	else if(j.matches("\\s*Finish")) { if(Asémantique.condStart) {res=j+" :Fermeture d'un bloc"; Asémantique.condFinish=true;} else {res="Erreur fermeture non permise";  }}
     	   //check le Affect
        	else if(j.matches("\\s*Affect.+")) {
        								 boolean aff=false,dots2=false,var1=false,var2=false,end2=false,affE=false,affF=false;
        		                         StringTokenizer st2=new StringTokenizer(j);
        		                         int nb2=st2.countTokens();
        		                         int x1=0;
    		                        	 
        		                          while(st2.hasMoreTokens()) {
        		                          String st21=st2.nextToken();
        		                         
                                   	     if(x1==0 && st21.equals("Affect")) {aff=true;}
                                   	     else if(x1==1) {for(String str:Entiers) {str=str.trim(); if(str.equals(st21)) {var1=true;affE=true; }}
                                   	     				for(String str:Réels) {str=str.trim(); if(str.equals(st21)) {var1=true;affF=true; }}
                                   		                 }
                                   	     else if(x1==2 && st21.matches("to")) {dots2=true;}
                                   	     else if (x1==3) { if(affE) {for(String str:Entiers) {str=str.trim(); if(str.equals(st21)) {var2=true;}}
                                   	     								for(String str:Réels) {str=str.trim(); if(str.equals(st21)) {var2=true;}}}
                                   	                       if(affF) {
                                      	     				       for(String str:Réels) {str=str.trim(); if(str.equals(st21)) {var2=true; }}}
                                   	    	             }
                                   	     else if(st21.matches(";;") && x1==4) {end2=true;}
                                   	    x1++;
        		                         }
        		                        if(aff  && var1 && dots2 && end2 && var2) {res=j+": Affectation correcte d'une variable a une autre variable;";}
 	                                    else {res=j+":Erreur Affectation fausse d'une variable a une variable;";}  
        								}
        //check le show message
        	else if(j.matches("\\s*ShowMes : \".+[^\"]\" ;;")) {res=j+": Affichage d'un message a l'écran";}
         //check le show value
        	else if(j.matches("\\s*ShowVal.+")) { boolean showV=false,var=false,dots=false,end=false;String value = null;
        								StringTokenizer st2=new StringTokenizer(j);
        								int x1=0;
        								  while(st2.hasMoreTokens()) {
            		                          String st21=st2.nextToken();
                                  	     if(x1==0 && st21.equals("ShowVal")) {showV=true; }
                                  	     else if(x1==1 && st21.matches(":")) {dots=true;}
                                  	     else if(x1==2) { 
                                  	    	for(String str:Entiers) {str=str.trim(); if(str.equals(st21)) {var=true; value=str; /*System.out.println("iden2");*/}}
                       	     				for(String str:Réels) {str=str.trim(); if(str.equals(st21)) {var=true; /*System.out.println("iden2");*/}}
                                  	                    }
                                  	   else if(st21.matches(";;") && x1==3) {end=true;}
                                  	   
       								                  x1++;           }
        							if(showV && dots && end && var) {res=j+":affichage de la valeur de la variable"+value;}
  	                                else {res=j+":Erreur d'affichage de la valeur d'une  variable;";}  
        								  
        								}
        	
        	//les commentaires
        	else if(j.matches("//. "+".+")) {res=j+" :Un Commentaire";}
         //la fin du pgme
        	else if(j.matches("End_Program")) {if(Asémantique.start && !Asémantique.finish) { Asémantique.finish=true; res=j+": Fin du programme ";}
        								else {res="erreur Verifier l'emplacement ou le nombre d'occurnce de l'instruction Start/Fin Program";} }
        	//traiter le cas d'autre instructions inconnues
        	else {res=j+"Cette instruction est invalide Vérifier votre syntaxe";}
        	
        	String Phrases=res;
        	System.out.println(res);
            total+=Phrases+System.getProperty("line.separator");;
           
          }
          miniprojet.jTextPane1.setContentType("text");
          miniprojet.jTextPane1.setText(total);

          if(!Asémantique.start) {System.out.println("Ajouter l'instruction Start Program");}
          else if(!Asémantique.condStart && Asémantique.condFinish)  {System.out.println("Ajouter l'instruction Start ");}
          else if(!Asémantique.finish) {System.out.println("Ajoutez l'instruction End_Program");}
          
          
          
          
       
	 }}	


