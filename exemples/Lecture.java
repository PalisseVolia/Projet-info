package exemples;

import java.io.*;
class  Lecture
{
public static void main(String[]args)
{
try
{
BufferedReader etudiants=new BufferedReader(new FileReader("Etudiants.txt"));
String lignelue;// Ligne lue depuis le fichier
String[] mot;
   while((lignelue=etudiants.readLine())!=null)
   {
   mot = lignelue.split(" ");

   for (int i = 0; i < 5; i++) {
      if (mot[3].equals("Lille")) {
         System.out.println(lignelue);
      }
   }
   System.out.println(lignelue);
   }
etudiants.close();
}
catch(FileNotFoundException err){
System.out.println( "Erreur :le fichier n’existe pas!\n "+err);}
catch (IOException err){
System.out.println(" Erreur :\n "+err);}

}  // Fin main()
}  // Fin classe
