import java.io.File;
import java.io.IOException;
public class ReadAllFile {
   public static void main(String args[]) throws IOException {
	   //File download = new File(System.getProperty("user.home"), "Downloads");
	   //Creating a File object for directory
	   //File directoryPath = new File(download,"/out-3/");
	   File directoryPath = new File("out-5/");   
      //File directoryPath = new File("/Users/chenzhuo/Downloads/out-3/");
      
      //List of all files and directories
      
      File filesList[] = directoryPath.listFiles();
      for(File file : filesList) {
    	  System.out.println(file.getName());
    	  //System.out.println(file.exists());//true
    	  //System.out.println(file.isDirectory());//false
    	  //System.out.println(file.canRead());//true
    	  double[][] array = ReadFileDe.generate("out-5/"+file.getName());
          ReadFileDe.read(array);
          System.out.println(" ");
          System.out.println(" ");
      }
   }
}