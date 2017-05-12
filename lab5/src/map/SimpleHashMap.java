package map;

import map.Map.Entry;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private double lf;
	private int size;

	public SimpleHashMap() {
		this.table = (Entry<K, V>[]) new Entry[16];
		this.lf = 0.75;
		this.size = 0;
	}

	public SimpleHashMap(int capacity) {
		this.table = (Entry<K, V>[]) new Entry[capacity];
		this.lf = 0.75;
		this.size = 0;
	}

	@Override
	public V get(Object arg0) {
		K key = (K) arg0;
		Entry<K, V> temp = find(index(key), key);
		return temp != null ? temp.getValue() : null;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public V put(K arg0, V arg1) {
		int index = index(arg0);
		Entry<K, V> temp = table[index];

		if (temp != null) {
			if (temp.key.equals(arg0)) {
				V old = temp.value;
				temp.setValue(arg1);
				return old;
			} else {
				while (temp.next != null) {
					if (temp.key.equals(arg0)) {
						V tempo = temp.value;
						temp.setValue(arg1);
						return tempo;
					}
					temp = temp.next;
				}
				if (temp.key.equals(arg0)) {
					V tempo = temp.value;
					temp.setValue(arg1);
					return tempo;
				}
			}
			temp.next = new Entry<K, V>(arg0, arg1);
			size++;
			if ((double) size / (double) table.length >= lf) {
				reHash();
			}
			return null;
		} else {

			table[index] = new Entry<K, V>(arg0, arg1);
			size++;
			if ((double) size / (double) table.length >= lf) {
				reHash();
			}
			return null;

		}

	}

	private void reHash() {
		Entry<K, V>[] old = table;
		Entry<K, V>[] big = (Entry<K, V>[]) new Entry[(2 * table.length) + 1];
		this.table = big;
		size = 0;
		for (int i = 0; i < old.length; i++) {
			Entry<K, V> temp = old[i];
			if (temp != null) {
				while (temp != null) {
					put(temp.getKey(), temp.getValue());
					temp = temp.next;
				}
			}
		}

	}

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		int index = index(key);
		Entry<K, V> temp = table[index];

		if (temp != null) {

			if (temp.getKey().equals(key)) {
				V old = temp.getValue();
				table[index] = temp.next;
				size--;
				return old;

			} else {

				while (temp.next != null) {
					Entry<K, V> prev = temp;
					if (temp.next.getKey().equals(key)) {
						V old = temp.next.getValue();
						prev = temp.next;
						size--;
						return old;

					} else {
						temp = temp.next;
					}
				}
			}
		}

		return null;
	}

	@Override
	public int size() {

		return size;

	}

	public String show() {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < table.length; i++) {
			Entry<K, V> temp = table[i];
			if (temp != null) {
				sb.append(i + "\t");
				sb.append(temp.toString() + "\t" + "\t");
				while (temp.next != null) {
					sb.append(" " + temp.next.toString());
					temp = temp.next;
				}
				sb.append("\n");

			}

		}
		return sb.toString();

	}

	private int index(K key) {
		int index = key.hashCode() % table.length;
		if (index < 0) {
			index = index + table.length;
		}
		return index;
	}

	private Entry<K, V> find(int index, K key) {
		Entry<K, V> temp = table[index];
		while (temp != null) {
			if (temp.getKey().equals(key)) {
				return temp;

			}
			temp = temp.next;

		}
		return null;

	}

	public static class Entry<K, V> implements Map.Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}

		@Override
		public K getKey() {

			return key;
		}

		@Override
		public V getValue() {

			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}

		public String toString() {
			return key + " = " + value;
		}
	}
}