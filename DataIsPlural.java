package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the program performing Data is Plural. The program is
 * interactive. When the program is executed the name of the input file
 * containing the list of DataSet(DataSetList) is provided as the program's
 * single command line argument. The data in this file serves as a database of
 * all the DataSet. In the interactive part, the user enters with one or at most
 * two queries with keywords. The program responds by printing the DataSet that
 * have matching keywords (if one exists in DataSetList).
 * 
 * @author Vicky Lin
 *
 */
public class DataIsPlural {
	/**
	 * The main() method of this program.
	 * 
	 * @param args array of Strings provided on the command line when the program is
	 *             started; the first string should be the name of the input file
	 *             containing the DataSetList.
	 */
	private static DataSetList datasetList = new DataSetList();

	public static void main(String[] args) throws Exception {
		// verify that the command line argument exists
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

		File csvFile = new File(args[0]);

		if (!csvFile.exists()) {
			System.err.println("Error: the file " + csvFile.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}
		if (!csvFile.canRead()) {
			System.err.println("Error: the file " + csvFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}
		// open file to read
		// throw exception if file is not found
		Scanner myScanner;
		CSV csv = null;
		try {
			myScanner = new Scanner(csvFile);
			csv = new CSV(myScanner);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found!");
			System.exit(1);
		}

		// Get the first line of the file as the headers
		ArrayList<String> csvHeaders = csv.getNextRow();

		// Start from the second line of the DataSetList file so that it only contains
		// contents(no headers)
		int counter = csv.getNumOfRows() - 1;

		// read the file row by row
		//make variables to store the values
		ArrayList<String> data=null;
		String[] links =null;
		ArrayList<URL>linkList=null;
		DataSet dataset=null;
		String[] dates = null;

		while (counter > 0) {
			data=csv.getNextRow();
			if(data!=null) {
				links = data.get(4).split("\n");
				//get url
				if(links.length==0 && links==null)
					continue;
				linkList=new ArrayList<URL>();
				for (String link : links) {
					if(link==null || link.isEmpty()==true)
						continue;
					try {
						URL url = new URL(link);
						linkList.add(url);
					}
					catch(MalformedURLException e) {
						continue;
					}		
				}
			}
			// get every date so that the format can match one per line
			//checking the title, description and url list validation.
			//title
			if(data.get(2)==null || data.get(2).isEmpty())
				continue;
			//description
			if(data.get(3)==null || data.get(3).isEmpty())
				continue;
			//url list
			if(linkList==null || linkList.isEmpty())
				continue;		
			dataset = new DataSet(data.get(2), data.get(3), linkList);
			try {
				dates = data.get(0).split("\\.");
				Date date = new Date(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]),
						Integer.parseInt(dates[2]));
				dataset.setDate(date);
			}
			catch(IllegalArgumentException e){
				//ignore the exception
			}

			//get hattips
			try {
				if(data!=null && dataset!=null) {
					if (data.size()==6) {
						dataset.setHatTips(data.get(5));
					}
				}
			}
			catch(IllegalArgumentException e) {
				//ignore the exception
			}

			boolean notInDataSet=true;
			for(DataSet d :datasetList) {
				if (d.equals(dataset))
					notInDataSet=false;
				break;	
			}
			//Incorrect output, print Covid without date two times
			if (notInDataSet) {
				datasetList.add(dataset);
			}
			counter--;
		}

		// interactive form
		System.out.println("Welcome the Data Is Plural data explorer!\n");
		System.out.println("You can use the following queries to search through the data: ");
		System.out.println(" title KEYWORD");
		System.out.println(" description KEYWORD");
		System.out.println(" url KEYWORD");
		System.out.println("You can combine up to two queries to narrow down the results, for example: ");
		System.out.println(" title KEYWORD1  url KEYWORD2:\n");

		Scanner input = new Scanner(System.in);
		String query = "";

		// ask the users for queries until they enter "quit" to exit the program
		while (!query.equalsIgnoreCase("quit" )) {
			System.out.println("Enter query or \"quit\" to stop: ");
			query = input.nextLine();
			if (query.equals("quit"))
				break;

			String[] queries = query.split(" ");
			// check the query appears in pair(title xxx)
			if (queries.length % 2 != 0) {
				System.out.println("This is not a valid query. Try again.\n");
				continue;
			}
			// check the queries are two pairs at most
			if (queries.length > 4) {
				System.out.println("This is not a valid query. Try again.\n");
				continue;
			}

			DataSetList result = datasetList;
			// Start the comparison
			// if it has second queries, narrow down the result from the first one
			if(queries[0].equalsIgnoreCase("title"))
				result = result.getByTitle(queries[1]);
			else if(queries[0].equalsIgnoreCase("description"))
				result = result.getByDescription(queries[1]);
			else if(queries[0].equalsIgnoreCase("url"))
				result = result.getByURL(queries[1]);
			else {
				System.out.println("This is not a valid query. Try again.\n");
				continue;
			}
			if (result == null) {
				System.out.println("No matches found. Try again.\n");
				continue;
			}
			if(queries.length==4) {
				if(queries[2].equalsIgnoreCase("title"))
					result = result.getByTitle(queries[3]);
				else if(queries[2].equalsIgnoreCase("description"))
					result = result.getByDescription(queries[3]);
				else if(queries[2].equalsIgnoreCase("url"))
					result = result.getByURL(queries[3]);
				else {
					System.out.println("This is not a valid query. Try again.\n");
					continue;
				}
				if (result == null) {
					System.out.println("No matches found. Try again.\n");
					continue;
				}
			}

			// print out the result of the matching DataSet
			for (DataSet d:result) {
				System.out.println(d);
				System.out.println("-----");
			}

		}
	}
}

