package set;

public class RemoveDuplicates {

	public static int[] uniqueElements(int[] ints) {
		MaxSet<Integer> max = new MaxSet<Integer>();

		if (ints != null) {
			for (int i = 0; i < ints.length; i++) {
				max.add(ints[i]);
			}
		} else {
			throw new NullPointerException();
		}

		int[] ints2 = new int[max.size()];
		for (int i = (max.size() - 1); i >= 0; i--) {
			ints2[i] = max.getMax();
			max.remove(max.getMax());
		}

		return ints2;
	}

	public static void main(String[] args) {
		int[] v = new int[7];
		v[0] = 7;
		v[1] = 5;
		v[2] = 3;
		v[3] = 5;
		v[4] = 2;
		v[5] = 2;
		v[6] = 7;
		v = uniqueElements(v);
		for (int i = 0; i < v.length; i++) {
			System.out.println(v[i]);
		}

	}
}
