
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DirectorySearch {

public DirectorySearch() {
super();
}

public static void main(String[] args) throws IOException {
	String searchWord="java";
	String s="C:\\Users\\HP\\New folder";
   DirectorySearch crawler = new DirectorySearch();
//    File directory = new File("C:\\Users\\HP\\New folder");
   File directory = new File(s);
    if (directory == null || !directory.exists()) {
           System.out.println("Directory doesn't exists!!!");
           return;
    }
    crawler.searchForDirectory(directory, searchWord);
}

public void searchForDirectory(File directory, String searchWord){

File[] filesAndDirs = directory.listFiles();
for (File file : filesAndDirs) {
if (file.isFile()) {
searchWord(file, searchWord);
//System.out.println(" |-" + file.getName());
} else {
searchForDirectory(file, searchWord);
}
}
}

private void searchWord(File file, String searchWord){
try {
	Scanner scanFile = new Scanner(file);
while(scanFile.hasNext()) {
	String search = scanFile.next();
	if(search.equals(searchWord)) 
		System.out.println(file+""+search);
}
scanFile.close();
} 

	catch (FileNotFoundException e) {
System.out.println("Search File Not Found !!!!! ");
e.printStackTrace();
}
}
}