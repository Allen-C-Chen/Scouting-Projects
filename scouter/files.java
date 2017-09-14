package scouter;

	import java.io.*;
	/**
	 * basic class used to handle file iO
	 * @author 
	 *
	 */
	public class files{
	  public String fileData;
	  /**
	   * create file with name
	   * @param name name of file
	   * @return
	   */
	  public boolean createFile(String name){
	    try{
	      File file=new File(name);
	      boolean success= file.createNewFile();
	      if(success){
	        return true;
	      }
	      else {
	        System.out.println("File Creation failed");
	        return false;
	      }
	    }
	    catch(IOException e){
	      return false;
	    }
	}
	  /**
	   * delete file with name
	   * @param filename
	   * @return
	   */
	  public boolean deleteFile(String filename){
	    File file= new File(filename);
	    boolean success=file.delete();
	    if(!success){
	      System.out.println("Deletion failed");
	      return false;
	    }
	    return true;
	  }
	  /**
	   * rename file
	   * @param oldName
	   * @param newName
	   * @return
	   */
	  public boolean renameFile(String oldName,String newName){
	    File file=new File(oldName);
	    File file2= new File(newName);
	    boolean success= file.renameTo(file2);
	    if(!success){
	      return false;
	    }
	    return true;
	  }
	  /**
	   * write string to file using buffered file writer
	   * @param file
	   * @param msg
	   */
	  public void writeToFile(String file,String msg){
	    try{
	      BufferedWriter out = new BufferedWriter(new FileWriter(file));
	      out.write(msg);
	      out.close();
	    }
	    catch(IOException e){
	      System.out.println("Write in file error");
	    }
	  }
	  /**
	   * read data from file in lines
	   * @param file
	   */
	  public void readFile(String file){
	    try{
	      BufferedReader in = new BufferedReader(new FileReader(file));
	      String str;
	      while((str=in.readLine())!=null){
	        fileData=str;
	      }
	      in.close();
	    }
	    catch(IOException e){
	      System.out.println("Reading file error");
	    }
	  }
	  /**
	   * get the strings read using {@link #readFile(String)}
	   * @return
	   */
	  public String getFileData(){
	    return fileData;
	  }
	  public void setFileData(String fileData){
		  this.fileData=fileData;
	  }
	
	  
}
