package project2;

/**
 * This class represents a Date. It uses the representation of a date
 * --Date(year, month, day) and it intakes each components to a combine Date
 * Method This class also includes check isLeapYear method and
 * toString(override) to convert Date in the form of YYYY-MM-DD and
 * compareTo(override) and equals(override) method.
 * 
 * @author Vicky Lin
 */

public class Date implements Comparable<Date> {
	// create members
	private int year;
	private int month;
	private int day;

	/**
	 * Constructs a new Date object with specified year, month and day components.
	 * 
	 * @param year  year component of this Date object; should be in the range of
	 *              positive integers
	 * @param month month component of this Date object; should be in the range of 1
	 *              to 12
	 * @param day   day component of this Date object; should be in the range of 1
	 *              to 31 if it;s a big month 30 of it's a small month; 29 if it's
	 *              Feb in leap year and 28 if it's Feb in common year.
	 * @throws IllegalArgumentException if year, month, or day parameters are
	 *                                  invalid
	 */
	public Date(int year, int month, int day) throws IllegalArgumentException {

		if (year <= 0)
			throw new IllegalArgumentException("Invalid year, valid range is positive integer");
		if (month < 1 || month > 12)
			throw new IllegalArgumentException("Invalid month, valid range is 1-12");

		if (day > 31 || day < 1)
			throw new IllegalArgumentException("Invalid day, valid range is 1-31");

		if (month == 4 || month == 6 || month == 9 || month == 11)
			if (day > 30 || day < 1)
				throw new IllegalArgumentException("Invalid day, valid range is 1-30");

		if (month == 2) {
			if (isLeapYear(year)) {
				if (day > 29 || day < 1)
					throw new IllegalArgumentException("Invalid day, this is a leap year, valid range is 1-29");
			}

			else if (day > 28 || day < 1) {
				throw new IllegalArgumentException("Invalid day, this is a leap year, valid range is 1-28");
			}
		}

		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * identified the year is leap year or not
	 * 
	 * @param year year, a positive integer
	 * @return true for year that is leap year and false for year that is a common
	 *         year
	 */
	private static boolean isLeapYear(int year) {
		if (year % 4 != 0)
			return false;
		if (year % 100 != 0)
			return true;
		if (year % 400 != 0)
			return false;
		return true;
	}

	/**
	 * Compares this object with the specified object for order.
	 * 
	 * @param other the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Date other) {
		if (this.year != other.getYear()) {
			return this.year - other.getYear();
		} else if (this.month != other.getMonth()) {
			return this.month - other.getMonth();

		} else {
			return this.day - other.getDay();
		}
	}

	/**
	 * Returns the year value representing this Date object.
	 *
	 * @return the year value of this Date object
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Returns the month value representing this Date object.
	 * 
	 * @return the month value of this Date object
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Returns the day value representing this Date object.
	 * 
	 * @return the day value of this Date object
	 */
	public int getDay() {
		return day;
	}

	/**
	 * set year to represent the year value of this Date object.
	 */

	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * set month to represent the month value of this Date object.
	 */

	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * set day to represent the day value of this Date object.
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Indicates whether some Object object is "equal to" this one. To Date objects
	 * are considered equal if their year,month,day components are the same
	 * 
	 * @return true if this object is the same as the object argument; false
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (!(object instanceof Date))
			return false;
		Date other = (Date) object;
		if (this.year != other.getYear())
			return false;
		if (this.month != other.getMonth())
			return false;
		if (this.day != other.getDay())
			return false;
		return true;
	}

	/**
	 * Returns the string representation of this Date.
	 * 
	 * @returns the string representation of this Date object
	 */
	public String toString() {
		if (month < 10) {
			if (day < 10) {
				return String.format("%d-%02d-%02d", year, month, day);
			}
			return String.format("%d-%02d-%d", year, month, day);
		}
		if (day < 10) {
			return String.format("%d-%d-%02d", year, month, day);
		}
		return String.format("%d-%d-%d", year, month, day);
	}

}
