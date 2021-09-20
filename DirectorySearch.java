
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class DirectorySearch {

	public DirectorySearch() {
		super();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the Directory");
		String link = br.readLine();
		System.out.println("Enter the number of words to be searched");
		Integer n = Integer.parseInt(br.readLine());
		List<String> words = new ArrayList<String>();
		if(n!=0)
		System.out.println("Enter the words to be searched");
		for (int i = 0; i < n; i++) {
			String st = br.readLine();
			words.add(st);
		}
//	String s="C:\\Users\\HP\\New folder";
//  File directory = new File("C:\\Users\\HP\\New folder");
		DirectorySearch crawler = new DirectorySearch();

		File directory = new File(link);
		if (directory == null || !directory.exists()) {
			System.out.println("Directory doesn't exists!!!");
			return;
		}
//    List<Map<String, Integer>> l=new ArrayList<Map<String, Integer>>();
		if(!words.isEmpty()) {
		System.out.println("{  \n   \"Status\": \"SUCCESS\", \n   \"searchResult\": [");
		int co=0;
		for (String searchWord : words) {
			
			co++;
			Map<String, Integer> map = new HashMap<String, Integer>();
			System.out.println("{ \n \"searchingString\": \""+searchWord + "\" \n \"details\" : [");
			crawler.searchForDirectory(directory, searchWord, map);
			Iterator<Entry<String, Integer>> hmIterator = map.entrySet().iterator();
			while (hmIterator.hasNext()) {
				Map.Entry<String, Integer> mapElement = (Map.Entry<String, Integer>) hmIterator.next();
				int c = (int) mapElement.getValue();
				System.out.print("{ \n \"" + mapElement.getKey() + "\" : \"" + c + "\" \n}");
				if(hmIterator.hasNext()) System.out.println(",");
				else System.out.println();
			}
			System.out.print("] \n}");
			if(words.size()>co) System.out.println(",");
			else System.out.println();

		}
		System.out.println("] \n }");
		}
		else System.out.println("No word to be searched");
	}

	public void searchForDirectory(File directory, String searchWord, Map<String, Integer> count) {
		File[] filesAndDirs = directory.listFiles();
		for (File file : filesAndDirs) {
			if (file.isFile()) {
				searchWord(file, searchWord, count);
//System.out.println(" |-" + file.getName());
			} else {
				searchForDirectory(file, searchWord, count);
			}
		}
	}
	
	private void searchWord(File file, String searchWord, Map<String, Integer> count) {
		try {
			Scanner scanFile = new Scanner(file);
			Integer counter = 0;
			while (scanFile.hasNext()) {
				String search = scanFile.next();
				if (search.equals(searchWord)) {
					counter++;
				}
			}
			if (counter > 0)
				count.put(file.toString(), counter);
			scanFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("Search File Not Found !!!!! ");
			e.printStackTrace();
		}
	}
}