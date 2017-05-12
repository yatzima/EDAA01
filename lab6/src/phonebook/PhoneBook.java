package phonebook;

import java.io.Serializable;
import java.util.*;

public class PhoneBook implements Serializable {
	private Map<String, Set<String>> pb;
	private static final long serialVersionUID = 1L;
	
	public PhoneBook() {
		pb = new TreeMap<String, Set<String>>();
	}

	/**
	 * Associates the specified number with the specified name in this phone
	 * book. post: If the specified name is not present in this phone book, the
	 * specified name is added and associated with the specified number.
	 * Otherwise the specified number is added to the set of number associated
	 * with name.
	 * 
	 * @param name
	 *            The name for which a phone number is to be added
	 * @param number
	 *            The number associated with the specified name
	 * @return true if the specified name and number was inserted
	 */
	public boolean put(String name, String number) {
		if (pb.containsKey(name)) {
			pb.get(name).add(number);
			return false;
		} else {
			HashSet<String> temp = new HashSet<String>();
			temp.add(number);
			pb.put(name, temp);

			return true;
		}
	}

	/**
	 * Removes the the specified name from this phone book. post: If the
	 * specified name is present in this phone book, it is removed. Otherwise
	 * this phone book is unchanged.
	 * 
	 * @param name
	 *            The name to be removed
	 * @return true if the specified name was present
	 */
	public boolean remove(String name) {
		if (pb.containsKey(name)) {
			pb.remove(name);
			return true;
		}
		return false;
	}

	/**
	 * Removes the specified number from name in this phone book. post: If the
	 * specified name and number is present in this phone book, the number is
	 * removed. Otherwise this phone book is unchanged.
	 * 
	 * @param number
	 *            The number to be removed
	 * @param name
	 *            The name from which to remove the number
	 * @return true if the specified name and number was present
	 */
	public boolean removeNumber(String name, String number) {
		if (pb.containsKey(name) && pb.get(name).contains(number)) {
			pb.get(name).remove(number);
			return true;
		}
		return false;
	}

	/**
	 * Retrieves a set of phone numbers for the specified name. If the specified
	 * name is not present in this phone book an empty set is returned.
	 * 
	 * @param name
	 *            The name whose associated phone numbers are to be returned
	 * @return The phone numbers associated with the specified name
	 */
	public Set<String> findNumber(String name) {
		HashSet<String> fail = new HashSet<String>();
		fail.add("Inga telefonnummer hittades");
		return !(pb.get(name) == null) ? pb.get(name) : fail;
	}

	/**
	 * Retrieves a set of names associated with the specified phone number. If
	 * the specified number is not present in this phone book an empty set is
	 * returned.
	 * 
	 * @param number
	 *            The number for which the set of associated names is to be
	 *            returned.
	 * @return The names associated with the specified number
	 */
	public Set<String> findNames(String number) {
		HashSet<String> temp = new HashSet<String>();
		for (String name : pb.keySet()) {
			for (String num : pb.get(name)) {
				if (num.equals(number)) {
					temp.add(name);
					break;
				}
			}

		}
		HashSet<String> fail = new HashSet<String>();
		fail.add("Inga namn hittades");
		return temp == null ? fail : temp;
	}

	/**
	 * Retrieves the set of all names present in this phone book. The set's
	 * iterator will return the names in ascending order
	 * 
	 * @return The set of all names present in this phone book
	 */
	public Set<String> names() {
		return pb.keySet();
	}

	/**
	 * Returns true if this phone book is empty.
	 * 
	 * @return true if this phone book is empty
	 */
	public boolean isEmpty() {
		return pb.isEmpty();
	}

	/**
	 * Returns the number of names in this phone book.
	 * 
	 * @return The number of names in this phone book
	 */
	public int size() {
		return pb.size();
	}

	/**
	 * Returns a string containing all names and numbers (one entry per row) in
	 * the the phonebook.
	 * 
	 * @return A string containing all names and numbers (one entry per row) in
	 *         the the phonebook
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String name : pb.keySet()) {
			sb.append("Namn: " + name + "\t Nummer: ");
			for (String num : pb.get(name)) {
				sb.append(num + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
