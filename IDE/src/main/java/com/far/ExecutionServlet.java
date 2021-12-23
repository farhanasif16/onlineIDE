package com.far;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
public class ExecutionServlet extends HttpServlet{
		String readUsingBuffered(String fileName) throws IOException{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		try {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}catch(Exception e) {
			
		}
		reader.close();
		return stringBuilder.toString();
	}
		public void service(HttpServletRequest req,HttpServletResponse res) throws IOException{
			 String extention=req.getParameter("language");
			 String code=req.getParameter("code");
			 System.out.println(code);
			 try {
			 extention=extention.toLowerCase();
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }
			 File source=new File("source."+extention);
			 File log=new File("log.txt");
			 try(FileWriter writer=new FileWriter(log)){
				for(int i=0;i<code.length();i++){
					writer.write(code.charAt(i));
				}
			 }catch(Exception e){
			 	e.printStackTrace();
			 }
			 Executer.setExecuter("", extention);
			 File result=Executer.execute(source,log);
			 String output=readUsingBuffered(result.getName());
			 res.setContentType("text/html;charset=UTF-8");
		     res.getWriter().write(output);
		}
}