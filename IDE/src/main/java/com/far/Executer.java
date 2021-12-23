package com.far;
import java.io.*;
class Executer{
	private static String path;
	private static String extention;
	public static void setExecuter(String path,String name){
		Executer.path=path;
		Executer.extention=name;
	}
	public static String fetch(String name){
                StringBuffer sb=new StringBuffer(name);
                int i;
                for(i=name.length()-1;i>=0&&sb.charAt(i)!='.';i--){
                        sb.deleteCharAt(i);
                }
                sb.deleteCharAt(i);
                return sb.toString();
        }
	public static File execute(File source,File log){
		File output=compileAndRun(source,log);
		return output;
	}
	public static File compileAndRun(File source,File log){
		String name=source.getName();
		String command="compile.sh "+source.getName()+" "+log.getName();
		String command2="run.sh "+fetch(source.getName())+" "+log.getName();
		File compiled=new File(path,name);//check it later
		try{
			Process compile=Runtime.getRuntime().exec(command);
			compile.destroy();
		}catch(Exception E){
			E.printStackTrace();
		}
		try{
			Process run=Runtime.getRuntime().exec(command2);
			run.destroy();
		}catch(Exception E){
			E.printStackTrace();
		}
		return log;
		/*BufferedReader reader=new BufferedReader(new InputStreamReader(p2.getInputStream()));
		String line="";
		while((line=reader.nextLine())!=null){
			System.out.println(line);
		}*/
	}
}
