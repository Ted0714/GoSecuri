/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devmtp.gosecuri;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Nathan
 */
public class GoSecuri {
    /**
     * 
     * @param args
     */
    public static void main(String[] args)  {
        
        //URL de récupération des fichiers txt
        String fileAgent = "C:/wamp64/www/GoSecuri/staff.txt";
        String fileTools = "C:/wamp64/www/GoSecuri/liste.txt";
        
        //récupération des données des fichiers txt et converti dans un tableau
        List listAgent = ParsingFile(fileAgent);
        List listTools = ParsingFile(fileTools);
        
        //création du fichier .htpasswd
        File fileHtpasswd = new File("C:/wamp64/www/GoSecuri/.htpasswd");
        
        List<String> Identification = SecurityFile(fileAgent);
        
        try {
                    
            FileWriter writerI = new FileWriter(fileHtpasswd);
            BufferedWriter bwI = new BufferedWriter(writerI);
                    
            for (String lineI : Identification)
            {   
                bwI.write(lineI);
                bwI.newLine();
                System.out.println(lineI);
            }
            bwI.close();
            writerI.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        //création ou update des pages html
        File file = new File("C:/wamp64/www/GoSecuri/Accueil.html");

        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            List<String> htmlLines = Arrays.asList(
                    "<html>",
                    "<head>",
                    "<title>GoSecuri</title>",
                    "<body>",
                    "</head>",
                    "<header>",
                    "<h1 style=\"color:#659224; text-align: center; font-size: 56px; font-family: 'Roboto Light 300',sans-serif\">GoSecuri</h1>",
                    "</header>",
                    "<h4 style=\"margin: 35px; color:#379EC1; font-size: 20px\">Liste des agents</h4>",
                    "<div style=\"display: grid; justify-items: baseline; border: 1px solid black; padding: 5px;\">"
                    );
            
            List<String> headerHtmlLines  = new ArrayList<String>(htmlLines);
            
            for(int a = 0; a < listAgent.size(); a++)
            {
                headerHtmlLines.add("<a href=\""+listAgent.get(a)+".html\"style=\"margin: 20px\">"+listAgent.get(a)+"</a>");
            }
            
            headerHtmlLines.add("</div>");
            headerHtmlLines.add("</body>");
            headerHtmlLines.add("</html>");
            
            try {
                    
                    FileWriter writer = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(writer);
                    
                    for (String line : headerHtmlLines)
                        {   
                            bw.write(line);
                            bw.newLine();
                            System.out.println(line);
                        }
                    bw.close();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        
        for (int i = 0; i < listAgent.size(); ++i)
            new MyThread("" + listAgent.get(i), listTools).start();
    }
    
    public static List ParsingFile(String File)
    {
        List list = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(File))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.indexOf("\\") != -1)
                {
                    String buffer = line.substring(0, line.indexOf("\\"));
                    line = line.substring(line.indexOf("\\") + 2);
                    list.add(buffer);
                }
                     
                list.add(line);
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static List SecurityFile(String ListAgent)
    {
        List listIdentifiant = new ArrayList<String>();
        try(BufferedReader brL = new BufferedReader(new FileReader(ListAgent))) 
        {
            String lineL;
            while ((lineL = brL.readLine()) != null) {
                listIdentifiant.add(lineL);
                //listIdentifiant.add("");
                System.out.println(lineL);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        List ficheAgent = ParsingFile(ListAgent);
        
        for(int f = 0; f < ficheAgent.size(); f++)
        {
            try(BufferedReader brL = new BufferedReader(new FileReader("C:/wamp64/www/GoSecuri/"+ficheAgent.get(f)+".txt"))) 
            {
                String lineL;
                int compteur = 0;
                while ((lineL = brL.readLine()) != null) {
                    if (compteur == 3)
                    {
                        /*if(f != 0)
                            listIdentifiant.set(f*2, lineL);
                        else*/
                            listIdentifiant.set(f, listIdentifiant.get(f)+":"+lineL);
                        System.out.println("---------------------------------------------------------------------"+lineL);
                        break;
                    }
                    compteur++;
                }
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        
        listIdentifiant.add("admin:admin");
        
        return listIdentifiant;
    }
}
