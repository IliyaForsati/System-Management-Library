public class Test {
    public static void main(String[] args) {
        List<Integer> numbers = new List();

        numbers.append(5);
        numbers.append(4);

        System.out.println(numbers.getLength());
        System.out.println(numbers.get(0));
        System.out.println(numbers.get(1));

        numbers.remove(5);

        System.out.println(numbers.getLength());
        System.out.println(numbers.get(0));
        System.out.println(numbers.get(1));
    }
}