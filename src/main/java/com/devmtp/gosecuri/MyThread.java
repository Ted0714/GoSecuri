/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devmtp.gosecuri;

import static com.devmtp.gosecuri.GoSecuri.ParsingFile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Nathan
 */
public class MyThread extends Thread {

        String Agent;
        String value = "checked";
        List listTools = new ArrayList<String>();
        
        MyThread(String Agent, List listTools)
        {
            this.Agent = Agent;
            this.listTools = listTools;
        }
        
        @Override
        public void run() {
            File agentHtml = new File("D:/Users/Nathan/.jenkins/workspace/GoSecuri/publisher/"+Agent+".html");
            String ficheAgentTxt = "D:/Users/Nathan/.jenkins/workspace/GoSecuri/"+Agent+".txt";
            
            List ficheAgent = ParsingFile(ficheAgentTxt);
            
            if(!agentHtml.exists())
            {
                try {
                    agentHtml.createNewFile();
                    run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                List<String> htmlIdent = Arrays.asList(
                        "<html>",
                        "<head>",
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />",
                        "<title>Identification</title>",
                        "</head>",
                        "<body>",
                        "<header>",
                        "<span style=\"margin: 55px 50px; color:#379EC1; font-size: 20px; border: 1px solid black; padding: 5px; \">"+ficheAgent.get(1)+" "+ficheAgent.get(0)+" - "+ficheAgent.get(2)+"</span>",
                        "</header>",
                        "<div>",
                        "<img src=\""+Agent+".jpg\" style=\"margin-left: 70%\">",
                        "</div>",
                        "<div style=\"display: grid; justify-items: baseline; margin-left: 30%\">"
                        );
                
                List<String> headerHtmlLines  = new ArrayList<String>(htmlIdent);
            
                for(int t = 1; t < listTools.size(); t = t + 2)
                {
                    if(ficheAgent.contains(listTools.get(t-1)) == true)
                        headerHtmlLines.add("<p>"+listTools.get(t)+"<input type=\"checkbox\" style=\"margin: 5px\" "+value+" disabled></p>");
                    else
                        headerHtmlLines.add("<p>"+listTools.get(t)+"<input type=\"checkbox\" style=\"margin: 5px\" disabled></p>");
                }

                headerHtmlLines.add("</div>");
                headerHtmlLines.add("</body>");
                headerHtmlLines.add("</html>");
            
            
                try {
                    
                    FileWriter writer = new FileWriter(agentHtml);
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
    }
}
