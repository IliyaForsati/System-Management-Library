public class Main{
	private static void doSomething(int[] a) {
		a[0] = 2;
		a[1] = 3;
	}

	public static void main(String[] args) {
		int[] a = new int[5];

		a[0] = 1;
		a[1] = 2;
		doSomething(a);

		System.out.println(a[0]);	
	}
}
