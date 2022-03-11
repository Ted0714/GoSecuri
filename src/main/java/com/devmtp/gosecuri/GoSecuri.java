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
        String fileAgent = "D:/Users/Nathan/.jenkins/workspace/GoSecuri/staff.txt";
        String fileTools = "D:/Users/Nathan/.jenkins/workspace/GoSecuri/liste.txt";
        
        //récupération des données des fichiers txt et converti dans un tableau
        List listAgent = ParsingFile(fileAgent);
        List listTools = ParsingFile(fileTools);
        
        //création du fichier .htpasswd
        File fileHtpasswd = new File("D:/Users/Nathan/.jenkins/workspace/GoSecuri/publisher/.htpasswd");
        
        List<String> Identification = SecurityFile(fileAgent);
        
        try {
                    
            FileWriter writerI = new FileWriter(fileHtpasswd);
            BufferedWriter bwI = new BufferedWriter(writerI);
                    
            for (String lineI : Identification)
            {   
                bwI.write(lineI);
                bwI.newLine();
            }
            bwI.close();
            writerI.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        //création ou update des pages html
        File file = new File("D:/Users/Nathan/.jenkins/workspace/GoSecuri/publisher/index.html");

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
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />",
                    "<title>GoSecuri</title>",
                    "</head>",
                    "<body>",
                    "<div class=\"header\">",
                    "<div class=\"container\">",
                    "<div class=\"header-navbar\">",
                    "<div class=\"header-navbar-logo\">",
                    "<h1>GoSecuri</h1>",
                    "</div>",
                    "<div class=\"header-navbar-menu\">",
                   "<a href=\"#\" class=\"header-navbar-menu-link\"><i class=\"fas fa-adjust fa-xs\"></i>Acceuil</a>",
                    "<a href=\"#pre\" class=\"header-navbar-menu-link\"><i class=\"fas fa-align-justify fa-xs\"></i>Contact</a>",
                    "</div>",
                    "</div>",
                    "<div class=\"box_discord\">",
                    "<div class=\"container px-lg-5\">",
                    "<div class=\"row mx-lg-n5\">",
                    "<div class=\"col py-3 px-lg-5\"><h2 class=\"textH1\"></h2><p class=\"para\">GoSecuri</p> <p class=\"bvn\">Agence de s&eacute;curit&eacute;</p></div>",
                    "</div>",
                    "</div>",
                    "</div>",
                    "</div>",
                    "<div class=\"header-2\">",
                    "<div class=\"container\">",
                    "<div class=\"text_2\">",
                    "<h2 id=\"video\">Nos Agents</h2>",
                    "<div class=\"video\">",
                    "<div class=\"ListWrapper\">",
                    "<div class=\"ListArea\">"
                    );
            
            List<String> headerHtmlLines  = new ArrayList<String>(htmlLines);
            
            
            for(int a = 0; a < listAgent.size(); a++)
            {
                headerHtmlLines.add("<div><a href=\""+listAgent.get(a)+".html\"style=\"margin: 20px\">"+listAgent.get(a)+"</a></div>");
            }
            
            headerHtmlLines.add("</div>");
            headerHtmlLines.add("</div>");
            headerHtmlLines.add("</div>");
            headerHtmlLines.add("</div>");
            headerHtmlLines.add("</div>");
            headerHtmlLines.add("</body>");
            headerHtmlLines.add("</html>");
            headerHtmlLines.add("<style type=\"text/css\">\n" +
                            "    .header {\n" +
                            "        height: 500px;\n" +
                            "        width: 100%;\n" +
                            "        background: white;\n" +
                            "        position: relative;\n" +
                            "    }\n" +
                            "    .header h1 {\n" +
                            "        color: #000000;\n" +
                            "    }\n" +
                            "\n" +
                            "    .header-texture {\n" +
                            "        width: 100%;\n" +
                            "        height: 100%;\n" +
                            "        position: absolute;\n" +
                            "        top: 0;\n" +
                            "        left: 0;\n" +
                            "        background-size: cover;\n" +
                            "        z-index: 1;\n" +
                            "        opacity: 0.5;\n" +
                            "    }\n" +
                            "\n" +
                            "    .container {\n" +
                            "        width: 90%;\n" +
                            "        margin: auto;\n" +
                            "        position: relative;\n" +
                            "        z-index: 999;\n" +
                            "    }\n" +
                            "\n" +
                            "    .header-navbar {\n" +
                            "        width: 100%;\n" +
                            "        height: 80px;\n" +
                            "        display: flex;\n" +
                            "        flex-direction: row;\n" +
                            "        justify-content: space-between;\n" +
                            "        align-items: center;\n" +
                            "    }\n" +
                            "\n" +
                            "    .header-navbar-menu-link {\n" +
                            "        margin-left: 40px;\n" +
                            "        color: #000000;\n" +
                            "    }\n" +
                            "\n" +
                            "    body {\n" +
                            "        font-family: 'Roboto Light', sans-serif;\n" +
                            "    }\n" +
                            "\n" +
                            "    i {\n" +
                            "        padding: 10px;\n" +
                            "    }\n" +
                            "\n" +
                            "    a:hover {\n" +
                            "        text-decoration: none;\n" +
                            "        list-style: none;\n" +
                            "    }\n" +
                            "\n" +
                            "    .para {\n" +
                            "        color: white;\n" +
                            "        padding-top: 100px;\n" +
                            "        font-size: 35px;\n" +
                            "\n" +
                            "        position: relative;\n" +
                            "        text-transform: uppercase;\n" +
                            "        letter-spacing: 4px;\n" +
                            "        overflow: hidden;\n" +
                            "        background: linear-gradient(90deg, #379EC1, #fff, #379EC1);\n" +
                            "        background-repeat: no-repeat;\n" +
                            "        background-size: 80%;\n" +
                            "        animation: animate 5s linear infinite;\n" +
                            "        -webkit-background-clip: text;\n" +
                            "        -webkit-text-fill-color: rgba(255, 255, 255, 0);\n" +
                            "    }\n" +
                            "\n" +
                            "    @keyframes animate\n" +
                            "    {\n" +
                            "        0%\n" +
                            "        {\n" +
                            "            background-position: -500%;\n" +
                            "        }\n" +
                            "        100%\n" +
                            "        {\n" +
                            "            background-position: 500%;\n" +
                            "        }\n" +
                            "    }\n" +
                            "\n" +
                            "    .bvn {\n" +
                            "        color: black;\n" +
                            "        padding-bottom: 20px;\n" +
                            "    }\n" +
                            "\n" +
                            "    .header-2 {\n" +
                            "        height: 800px;\n" +
                            "        width: 100%;\n" +
                            "        position: relative;\n" +
                            "        background: #000000;\n" +
                            "    }\n" +
                            "\n" +
                            "    .text_2 {\n" +
                            "        color: #659224;\n" +
                            "        text-align: center;\n" +
                            "        padding-top: 30px;\n" +
                            "    }\n" +
                            "\n" +
                            "    .video {\n" +
                            "        padding-top: 30px;\n" +
                            "    }\n" +
                            "\n" +
                            "    body {\n" +
                            "        background-color: #ffffff;\n" +
                            "        padding: 0%;\n" +
                            "        margin: 0%;\n" +
                            "    }\n" +
                            "\n" +
                            "    .ListWrapper{\n" +
                            "        width: 350px;\n" +
                            "        height: 350px;\n" +
                            "        margin: 100px auto;\n" +
                            "        overflow: hidden;\n" +
                            "        overflow-y: auto;\n" +
                            "\n" +
                            "        font-family: Roboto, sans-serif;\n" +
                            "        border-radius: 3px;\n" +
                            "        box-shadow: 0 0 15px  #659224;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListHeader{\n" +
                            "        border-bottom: 1px solid rgb(220,220,220);\n" +
                            "        display: flex;\n" +
                            "        justify-content: space-between;\n" +
                            "        line-height: 50px;\n" +
                            "        padding: 0 20px;\n" +
                            "    }\n" +
                            "    .ListWrapper h3{\n" +
                            "        font-weight: 400;\n" +
                            "        margin: 0;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListHeader button{\n" +
                            "        background: none;\n" +
                            "        border: none;\n" +
                            "        color: #2196F3;\n" +
                            "        vertical-align: -6px;\n" +
                            "        line-height: 1em;\n" +
                            "        cursor: pointer;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListHeader button{\n" +
                            "        opacity: 0;\n" +
                            "        visibility: hidden;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListHeader button.Visible{\n" +
                            "        opacity: 1;\n" +
                            "        visibility: visible;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListArea{\n" +
                            "        overflow-x: auto;\n" +
                            "        height: calc(100% - 51px);\n" +
                            "    }\n" +
                            "    .ListWrapper .ListArea > div{\n" +
                            "        line-height: 40px;\n" +
                            "        position: relative;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListArea > div + div{\n" +
                            "        border-top: 1px solid rgb(220,220,220);\n" +
                            "    }\n" +
                            "\n" +
                            "    .ListWrapper .ListArea > div a{\n" +
                            "        vertical-align: -5px;\n" +
                            "        color: white;\n" +
                            "        padding: 0 10px 0 20px;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListArea .ListCheckbox{\n" +
                            "        opacity: 0;\n" +
                            "        visibility: hidden;\n" +
                            "        float: right;\n" +
                            "        padding: 10px 20px 0 0;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListArea .ListCheckbox.Visible{\n" +
                            "        opacity: 1;\n" +
                            "        visibility: visible;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListArea .ListCheckbox input{\n" +
                            "        display: none;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListArea .ListCheckbox label{\n" +
                            "        display: block;\n" +
                            "        width: 20px;\n" +
                            "        height: 20px;\n" +
                            "        background-color: rgba(0,0,0, .2);\n" +
                            "        border-radius: 3px;\n" +
                            "        cursor: pointer;\n" +
                            "        position: relative;\n" +
                            "    }\n" +
                            "    .ListWrapper .ListArea .ListCheckbox :checked ~ label:after{\n" +
                            "        display: block;\n" +
                            "        content: '✔';\n" +
                            "        position: absolute;\n" +
                            "        top: 0;\n" +
                            "        left: 0;\n" +
                            "        width: 100%;\n" +
                            "        height: 100%;\n" +
                            "        text-align: center;\n" +
                            "        line-height: 20px;\n" +
                            "        color: #2196F3;\n" +
                            "    }\n" +
                            "</style>");
            
            try {
                    
                    FileWriter writer = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(writer);
                    
                    for (String line : headerHtmlLines)
                        {   
                            bw.write(line);
                            bw.newLine();
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
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        List ficheAgent = ParsingFile(ListAgent);
        
        for(int f = 0; f < ficheAgent.size(); f++)
        {
            try(BufferedReader brL = new BufferedReader(new FileReader("D:/Users/Nathan/.jenkins/workspace/GoSecuri/"+ficheAgent.get(f)+".txt"))) 
            {
                String lineL;
                int compteur = 0;
                while ((lineL = brL.readLine()) != null) {
                    if (compteur == 3)
                    {
                        listIdentifiant.set(f, listIdentifiant.get(f)+":"+lineL);
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
