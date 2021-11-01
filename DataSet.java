package project2;

import java.net.URL;

/**
 * This class represents a DataSet. It uses the representation of a set of values that stored in the dataset
 * --DataSet(title,description,links)
 * Methods in this class also includes getDate, SetDate, GetHatTips,SetHatTips
 * toString(override) to convert DataSet in the form of each component per line and link per line,
 * compareTo(override), and equals(override) method.
 * 
 * @author Vicky Lin
 */

import java.util.ArrayList;

public class DataSet implements Comparable<DataSet> {
	// identified the private members
	private String title;
	private String description;
	private ArrayList<URL> links;
	private Date date;
	private String hatTips;

	// constructor
	/**
	 * Constructs a new DateSet object with specified title, description and links
	 * components.
	 * 
	 * @param title       title component of this DataSet object; should be not
	 *                    empty and not null
	 * @param description description component of this DataSet object; should not
	 *                    empty and not null
	 * @param links       links component of this DateSet object; should be not
	 *                    empty and not null
	 * @throws IllegalArgumentException if title, description, or links parameters
	 *                                  are invalid
	 */
	public DataSet(String title, String description, ArrayList<URL> links) throws IllegalArgumentException {
		// check condition
		if (title == null || title.isEmpty() == true)
			throw new IllegalArgumentException("Invalid title, valid format: non-empty string");
		if (description == null || description.isEmpty() == true)
			throw new IllegalArgumentException("Invalid description, valid format: non-empty string");
		if (links == null || links.isEmpty() == true)
			throw new IllegalArgumentException("Invalid URL, valid format: non-empty list of URL objects");

		this.title = title;
		this.description = description;
		this.links = links;
	}

	// setDate and GetDate
	/**
	 * set date to represent the date value of this DateSet object.
	 * 
	 * @param date date should be not null and year component of the date should not
	 *             be less than 2000
	 * @throws IllegalArgumentException if date parameter is invalid
	 */
	public void setDate(Date date) throws IllegalArgumentException {
		if (date == null)
			throw new IllegalArgumentException("Invalid date, valid format: non-empty Date obeject");
		if (date.getYear() < 2000)
			throw new IllegalArgumentException("Invalid year, valid format: year >=2000");
		this.date = date;
	}

	/**
	 * Returns the date value representing this DataSet object.
	 * 
	 * @return the date value of this DataSet object
	 */
	public Date getDate() {
		return date;
	}

	// setHatTips and GetHatTips
	/**
	 * set hatTips to represent the hatTips value of this DateSet object.
	 * 
	 * @param hatTips hatTips should be not a null string
	 * @throws IllegalArgumentException if hatTips parameter is invalid
	 */
	public void setHatTips(String hatTips) throws IllegalArgumentException {
		if (hatTips == null)
			throw new IllegalArgumentException("Invalid hat-tips, valid format:not null string");
		this.hatTips = hatTips;
	}

	/**
	 * Returns the hatTips value representing this DataSet object.
	 * 
	 * @return the hatTips value of this DataSet object
	 */
	public String getHatTips() {
		return hatTips;
	}

	/**
	 * Returns the title value representing this DataSet object.
	 * 
	 * @return the title value of this DataSet object
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the description value representing this DataSet object.
	 * 
	 * @return the description value of this DataSet object
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the links value representing this DataSet object.
	 * 
	 * @return the links value of this DataSet object
	 */
	public ArrayList<URL> getURL() {
		return links;
	}

	/**
	 * Compares this object with the specified object for order.
	 * 
	 * @param other the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(DataSet other) {
		if (!(this.date == null) && !(other.getDate() == null) && !(this.date.equals(other.getDate()))) {
			return this.date.compareTo(other.getDate());
		}
		return this.title.compareToIgnoreCase(other.getTitle());
	}

	/**
	 * Indicates whether some Object object is "equal to" this one. To Date objects
	 * are considered equal if their date,title components are the same
	 * 
	 * @return true if this object is the same as the object argument; false
	 *         otherwise.
	 */
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (!(object instanceof DataSet))
			return false;
		DataSet dataset = (DataSet) object;
		if (!this.title.equalsIgnoreCase(dataset.getTitle()))
			return false;
		if (!this.date.equals(dataset.getDate()))
			return false;
		return true;

	}

	/**
	 * Returns the string representation of this DataSet. in form of each component
	 * per line and link per line,
	 * 
	 * @returns the string representation of this Date object
	 */
	public String toString() {
		StringBuilder result;
		if (this.date == null) {
			result = new StringBuilder(String.format("%s\n%s\n", title, description));
		} else {
			result = new StringBuilder(String.format("%s\n%s\n%s\n", date, title, description));
		}
		for (URL link : links)
			result.append(link + "\n");
		return result.toString();
	}
}
