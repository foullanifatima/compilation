package COMPILATION;


import java.util.ArrayList;
//import .Le;
//import compilation.NewJFrame;
//import static compilation.miniprojet.*;

import java.util.StringTokenizer;
import javax.swing.JTextPane;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author h
 */
public class Alexicale {

    public Alexicale() {
    }
  
    public void lexique() {
      ArrayList <Le> chaineReservée=new ArrayList <Le>();
	

	 //ajouter les mot reserver dans la liste Le
	chaineReservée.add(new Le("mot_Reserver debut du programme","Start_Program"));
	chaineReservée.add(new Le("mot Reserver déclaration des entiers","Int_Number"));
	chaineReservée.add(new Le("charachtere réservé",":"));
     chaineReservée.add(new Le("charachtere réservé","\""));
	chaineReservée.add(new Le("mot Reserver  d'affectation","Give"));
	chaineReservée.add(new Le("mot Reserver if de condition","If"));
	chaineReservée.add(new Le("mot Reserver Declaration de réel","Real_Number"));
	chaineReservée.add(new Le("mot Reserver pour condition","--"));
	chaineReservée.add(new Le("mot Reserver ",";;"));
	chaineReservée.add(new Le("mot Reserver else de condition","Else"));
	chaineReservée.add(new Le("mot Reserver debut d'un bloc","Start"));
	chaineReservée.add(new Le("mot Reserver pour affectation","Affect"));
	chaineReservée.add(new Le("mot Reserver pour affectation ","to"));
	chaineReservée.add(new Le("mot Reserver fin d'un bloc","Finish"));
	chaineReservée.add(new Le("mot Reserver au programme pour afficher","ShowMes"));
	chaineReservée.add(new Le("mot Reserver au programme pour afficher une valeur","ShowVal"));
	chaineReservée.add(new Le("mot Reserver au programme pour fin du programme","End_Program"));
	chaineReservée.add(new Le("symbole inférieur ","<"));
	chaineReservée.add(new Le(" symbole égal","="));
	chaineReservée.add(new Le("symbole supérieur ",">"));
        String id=" ?[a-zA-Z](_?[a-zA-Z0-9]+)* ?";
        String en="[0-9]+";
        String re="[0-9]+.[0-9]+";
         String s=miniprojet.contenu;
         String total = "";
         //pour séparer le contenu en ligne
      StringTokenizer f=new StringTokenizer(s,"[\n]");
      while(f.hasMoreElements()) {
    	  String jj=f.nextToken();
    	  //pour éviter les commentaires
    	  
    	  if(!jj.matches("//..+")) {
      StringTokenizer e=new StringTokenizer(jj);
       
		 while (e.hasMoreTokens()) {
			 
			 boolean show=true;
			 String h=e.nextToken();
			String res="";
			if(h.matches(".+(<|>|=).+")) {
				String a,b,c;
				StringTokenizer ss=new StringTokenizer(h,"[<|>|=]",true);
				while(ss.hasMoreTokens()) {
					String ss1=ss.nextToken();
					
					if(ss1.matches(id)) {res=res+"<span style=\"color:#22427C;\">"+ss1+"</span>"+": c'est un Identificateur <br>";}
					if(ss1.matches("[<|>|=]")) { res=res+"<span style=\"color:#22427C;\">"+ss1+": c'est un Mot réservé <br>";}
				}   }      
			  for(Le l:chaineReservée) { if(h.equals(l.getToken())) {res="<span style=\"color:#22427C;\">"+h+"</span>"+":"+l.getMeaning();show=false;continue; }}
				if(h.contains(",")){ String[] v= h.split(",");
                                                     int n=v.length;
                                                     for(int ii=0;ii<n;ii++)
                                                     {
                                                       
                                                       
                                                       if(v[ii].matches(id)&&show ) {res=res+"<span style=\"color:#22427C;\">"+v[ii]+"</span>"+"   : c'est un Identificateur <br><br>";}
                                                       if(ii+1<n)                    res=res+"<span style=\"color:#22427C;\">,</span>"+"   : charactere reserver <br><br>";
                                                     }
                                                     }
				else           if(h.matches(id)&&show ) {res="<span style=\"color:#22427C;\">"+h+"</span>"+"   : c'est un Identificateur";}
				else           if(h.matches(en)) {res="<span style=\"color:#22427C;\">"+h+"</span>"+"   : c'est un Entier";}
				else             if(h.matches(re)) {res="<span style=\"color:#22427C;\">"+h+"</span>"+"   : c'est un Float";}
				else           if(h.matches("//.+")) {/*ignorer les commentaires*/}
			//	else if(show)res="<span style=\"color:#22427C;\">"+h+"</span>"+"erreur";
                               
                                  String Phrases=res+"<br><br>";                                 
                                  total+=Phrases;
                        }}}
                      
                        total="<html style=\"text-size:large;\">"+total+"</html>";
                         miniprojet.jTextPane1.setContentType("text/html");
                         miniprojet.jTextPane1.setText(total);
			 
	     } 
        
        
        
        
    }

    
    
    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
     //for(String h:Entiers) {System.out.println(h);}
     	// for(String h:RÃ©els) {System.out.println(h);}
		
    

