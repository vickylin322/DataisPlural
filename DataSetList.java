package project2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * DataSetList class is used to store a collection of DataSet objects. This
 * class inherits all of its properties from an ArrayList<DataSet>. It adds
 * DataSet-specific functions that allow search by title, description,and links
 * This class does store DataSet objects in an particular order. The return of
 * should be sorted according to the natural ordering of the elements
 * 
 * @author Vicky Lin
 */

@SuppressWarnings("serial")
public class DataSetList extends ArrayList<DataSet> {
	// create a default constructor
	public DataSetList() {
	}

	/**
	 * Search through the list of DataSet for an object matching the given keyword
	 * in title.
	 * 
	 * @param keyword the keyword that contains in the title for which to search 
	 * 		  the compare should be case insensitive
	 * @return the matching DataSet object in the list, or null if the matching
	 *         DataSet is not found
	 */

	public DataSetList getByTitle(String keyword) throws IllegalArgumentException {
		DataSetList list = new DataSetList();

		if (keyword == null || keyword.isEmpty())
			throw new IllegalArgumentException("Invalid Keywords");

		for (DataSet d : this) {
			String title = d.getTitle();
			if (title == null)
				continue;
			if (title.toLowerCase().contains(keyword.toLowerCase())) {
				list.add(d);
				continue;
			}

		}
		Collections.sort(list);

		if (list.size() == 0)
			return null;
		return list;
	}

	/**
	 * Search through the list of DataSet for an object matching the given keyword
	 * in description.
	 * 
	 * @param keyword the keyword that contains in the description for which to
	 *        search the compare should be case insensitive
	 * @return the matching DataSet object in the list, or null if the matching
	 *         DataSet is not found
	 */

	public DataSetList getByDescription(String keyword) throws IllegalArgumentException {
		DataSetList list = new DataSetList();

		if (keyword == null || keyword.isEmpty())
			throw new IllegalArgumentException("Invalid Keyword");

		for (DataSet d : this) {
			String description = d.getDescription();
			if (description == null)
				continue;
			if (description.toLowerCase().contains(keyword.toLowerCase())) {
				list.add(d);

			}
		}
		Collections.sort(list);

		if (list.size() == 0)
			return null;
		return list;
	}

	/**
	 * Search through the list of DataSet for an object matching the given keyword
	 * in URL.
	 * 
	 * @param keyword the keyword that contains in the URL link for which to search,
	 *        the compare should be case insensitive
	 * @return the matching DataSet object in the list, or null if the matching
	 *         DataSet is not found
	 */
	public DataSetList getByURL(String keyword) throws IllegalArgumentException {
		DataSetList list = new DataSetList();
		if (keyword == null || keyword.isEmpty())
			throw new IllegalArgumentException("Invalid Keyword");
		for (DataSet d : this) {
			ArrayList<URL> links = d.getURL();
			if (links == null)
				continue;
			for (URL item : links) {
				if (item.toString().toLowerCase().contains(keyword.toLowerCase())) {
					list.add(d);
					break;
				}

			}
		}
		Collections.sort(list);
		if (list.size() == 0)
			return null;
		return list;

	}

	/**
	 * Returns the string representation of this DataSetList.
	 * 
	 * @returns the string representation of this DataSetList object
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (DataSet i : this) {
			result.append(i.toString() + "\n");
		}
		result.append("----");
		return result.toString();
	}

}
