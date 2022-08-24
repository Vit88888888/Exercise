
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise {

	static class Line {

		private int a;
		private int b;

		public int getA() {
			return a;
		}

		public void setA(int a) {
			this.a = a;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

	}

	public static void main(String[] args) {

		ArrayList<Line> list = new ArrayList<Line>();
		list = loadLines();
		list = filterLines(list);
		printLines(list);

	}

	public static ArrayList<Line> loadLines() {
		System.out.println("Zadejte usecky");
		ArrayList<Line> list = new ArrayList<Line>();

		String str = "";
		boolean check;
		int space;

		try {
			Scanner scanner = new Scanner(System.in);

			do {
				check = true;
				space = 0;
				str = scanner.nextLine().trim();

				if (!str.isBlank()) {
					if (str.matches("[0-9\\s]+")) {

						for (int i = 0; i < str.length(); i++) {
							if (Character.isWhitespace(str.charAt(i))) {
								if (space == 0) {
									space = i;
								} else
									check = false;

							}

						}

					} else
						check = false;
					
					if (space == 0)
						check = false;
					
					if (check == true) {
						int a = Integer.parseInt(str.substring(0, space));
						int b = Integer.parseInt(str.substring(space + 1, str.length()));

						if (a > 0 && b > 0 && a <= b) {
							Line line = new Line();
							line.setA(a);
							line.setB(b);
							list.add(line);

						} else
							check = false;
					}

					if (check == false) {
						System.out.println("Chybny vstup");
					}

				}
			} while (!str.isBlank());
			scanner.close();
		} catch (Exception e) {
			System.out.println("Chyba");
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<Line> filterLines(ArrayList<Line> list) {

		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getA() >= list.get(j).getA() && list.get(i).getB() <= list.get(j).getB()) {
					list.get(i).setA(0);
					list.get(i).setB(0);
				} else if (list.get(i).getA() <= list.get(j).getA() && list.get(i).getB() >= list.get(j).getB()) {
					list.get(j).setA(0);
					list.get(j).setB(0);
				}
			}
		}

		return list;
	}

	public static void printLines(ArrayList<Line> list) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getA() != 0 && list.get(i).getB() != 0) {
				System.out.println(list.get(i).getA() + " " + list.get(i).getB());
			}
		}

	}

}
