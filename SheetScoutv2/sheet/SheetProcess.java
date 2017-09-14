package sheet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * main class to call from main to process sheet data
 * @author Allen
 *
 */
public class SheetProcess {
	SheetColumns template;
	SheetSequencer sequence;
	public void setTemplate(SheetColumns template){
		this.template=template;
	}
	public void setSequence(SheetSequencer sequence){
		this.sequence=sequence;
	}
	public String processFileData(String file) throws IOException{
		return processFileData(new File(file));
	}
	public String processFileData(File file) throws IOException{
		if(sequence==null||template==null){
			throw new IOException("sequence or template has not been set");
		}
		String out="";
		BufferedReader in =new BufferedReader(new FileReader(file));
		Vector<String> rows=new Vector<String>();
		while(in.ready()){
			rows.add(in.readLine());
		}
		in.close();
		for(String s:rows){	
			try{
				template.parse(s);
			}catch(Exception e){System.out.println("error parsing "+s);}
			
		}
		for(String s:template.getTeams()){
			System.out.println(template.getData(s));
			out+="\n"+sequence.sequence(template.getData(s));
		}
		return out.substring(1);
	}
	public static void main(String[] args)throws IOException{
		SheetProcess sheet=new SheetProcess();
		sheet.setSequence(SheetSequencer.read(new File("/Users/Allen/Desktop/sequence.txt")));
		sheet.setTemplate(SheetColumns.read(new File("/Users/Allen/Desktop/template.txt")));
		System.out.println(sheet.processFileData("/Users/Allen/Desktop/data.txt"));
	}
}
