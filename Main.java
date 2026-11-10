package padillo;

import java.util.Queue;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	static Scanner input = new Scanner(System.in);

	// Queue implementation
	static Queue<String> queue = new LinkedList<>();

	static int permutationCount = 0;
	static int combinationCount = 0;

	public static void main(String[] args) {

		int choice;

		do {
			System.out.println("\n===== PERMUTATION AND COMBINATION =====");
			System.out.println("[1] Add Elements to Queue");
			System.out.println("[2] Display Queue");
			System.out.println("[3] Generate Permutations");
			System.out.println("[4] Generate Combinations");
			System.out.println("[5] Exit");
			System.out.print("Enter choice: ");

			choice = input.nextInt();

			switch (choice) {

			case 1:
				addElements();
				break;

			case 2:
				displayQueue();
				break;

			case 3:
				generatePermutations();
				break;

			case 4:
				generateCombinations();
				break;

			case 5:
				System.out.println("Program terminated.");
				break;

			default:
				System.out.println("[Invalid choice!]");
			}

		} while (choice != 5);
	}

	// TODO 1: Accept elements and add them to the Queue
	static void addElements() {
		int num = isValid("How many elements to add? ");

		for (int i = 0; i < num; i++) {
			String element;
			while (true) {
				System.out.print("Enter element " + (i + 1) + ": ");
				element = input.next();

				if (element.length() == 1) {
					break;
				} else {
					System.err.println("[Invalid input! Please enter exactly ONE character.]");
				}
			}
			queue.add(element);
		}

		System.out.println("\n[Elements successfully added!]");
		System.out.println("Queue: " + queue);
		System.out.println("Front element: " + queue.peek());

		System.out.println("\nDequeue two elements:");
		for (int i = 0; i < 2; i++) {
			if (!queue.isEmpty()) {
				System.out.println("Removed: " + queue.poll());
			}
		}

		System.out.println("Current Queue: " + queue);
	}

	// TODO 2: Display all elements in the Queue
	static void displayQueue() {
		if (queue.isEmpty()) {
			System.out.println("\n[Queue is empty! Add elements to the queue first.]");
			return;
		}
		System.out.println("\nQueue: " + queue);
		;
	}

	// TODO 3: Generate all possible permutations
	static void generatePermutations() {
		if (queue.isEmpty()) {
			System.out.println("\n[Queue is empty! Add elements first.]");
			return;
		}
		
		int r;
		while (true) {
			r = isValid("Enter number of elements to arrange: ");

			if (r > queue.size() || r <= 0) {
				System.err.println("[Invalid number of elements. Queue only has " + queue.size() + " element(s).]");
				continue;
			} else {break;}
		}
		
		String[] elements = queue.toArray(new String[0]);
		Queue<String> permQueue = new LinkedList<>();
		
		System.out.println("\nPermutations:");

		for (String elem : elements) {
			permQueue.add(elem);
		}

		int totalPermutations = 0;
		while (!permQueue.isEmpty()) {
			String current = permQueue.poll();

			if (current.length() == r) {
				System.out.println(current);
				totalPermutations++;
			} else {
				for (String elem : elements) {
					if (!current.contains(elem)) {
						permQueue.add(current + elem);
					}
				}
			}
		}

		System.out.println("\nTotal Permutations: " + totalPermutations);
	}

	// TODO 4: Generate all possible combinations
	static void generateCombinations() {
		if (queue.isEmpty()) {
			System.out.println("\n[Queue is empty! Add elements first.]");
			return;
		}
		
		int r;
		while (true) {
			r = isValid("Enter number of elements to select: ");

			if (r > queue.size() || r <= 0) {
				System.err.println("[Invalid number of elements. Queue only has " + queue.size() + " element(s).]");
				continue;
			} else {break;}
		}

		LinkedList<String> elements = new LinkedList<>(queue);
        combinationCount = 0;

        System.out.println("\nCombinations:");
        generateCombinations(elements, r, 0, new LinkedList<>());
        System.out.println("\nTotal Combinations: " + combinationCount);
    }

    // Combination algorithm
    static void generateCombinations(LinkedList<String> elements, int r, int start, LinkedList<String> current) {
        if (current.size() == r) {
            for (String element : current) {
                System.out.print(element);
            }
            System.out.println();
            combinationCount++;
            return;
        }

        for (int i = start; i < elements.size(); i++) {
            current.add(elements.get(i));
            generateCombinations(elements, r, i + 1, current);
            current.removeLast();
        }
	}

	// Integer input validation
	private static int isValid(String prompt) {
		int num = 0;
		while (true) {
			System.out.print(prompt);
			try {
				num = input.nextInt();
				input.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.err.println("[Error: Please enter a valid integer.]");
				input.next();
			}
		}
		return num;
	}
}
