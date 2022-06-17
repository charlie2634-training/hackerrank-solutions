package strings.two_strings_game;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class Node implements Comparable<Node> {
	private static BigInteger winningPositionCounter;
	private int count;
	private String value;
	private Set<Node> children;
	private boolean grundyNumberCalculated;
	private Integer grundyNumber;
	private Integer remainingCharacters;
	private BigInteger initialWinningPosition;
	private BigInteger finalWinningPosition;

	public Node(String value, Integer grundyNumber, Integer remainingCharacters) {
		this.count = 1;
		this.value = value;
		this.children = new TreeSet<>();
		this.grundyNumber = grundyNumber;
		this.remainingCharacters = remainingCharacters;
		this.grundyNumberCalculated = false;
	}

	public int getCount() {
		return count;
	}

	public String getValue() {
		return value;
	}

	public Set<Node> getChildren() {
		return children;
	}

	private boolean isExpanded() {
		return this.children != null && !this.children.isEmpty();
	}

	public BigInteger getInitialWinningPosition() {
		return initialWinningPosition;
	}

	public BigInteger getFinalWinningPosition() {
		return finalWinningPosition;
	}

	public Integer getRemainingCharacters() {
		if (children != null && !children.isEmpty()) {
			return null;
		} else {
			return remainingCharacters - value.length();
		}
	}

	public Node getVictoryNode(BigInteger index) {
		Node returnable = null;

		if (this.initialWinningPosition.compareTo(index) < 0 && this.finalWinningPosition.compareTo(index) >= 0) {
			returnable = this;
		}

		if (!this.children.isEmpty()) {
			for (Node child : children) {
				Node victoryNode = child.getVictoryNode(index);
				if (victoryNode != null) {
					returnable = victoryNode;
					break;
				}
			}
		}

		return returnable;
	}

	public Integer getGrundyNumber() {
		fixVoidBetweenNodes();
		if (children != null && !children.isEmpty() && !this.grundyNumberCalculated) {
			Set<Integer> childGrundyNumbers = children.stream().map(x -> x.getGrundyNumber())
					.collect(Collectors.toSet());
			this.grundyNumber = getMex(childGrundyNumbers);
			this.grundyNumberCalculated = true;
		}

		return grundyNumber;
	}

	private static int getMex(Set<Integer> grundyNumbers) {
		int mex = 0;

		while (grundyNumbers.contains(mex)) {
			mex++;
		}

		return mex;
	}

	private static Map<Integer, BigInteger> grundyNumbers;

	public Map<Integer, BigInteger> getGrundyNumbers() {
		grundyNumbers = new HashMap<>();

		addGrundyNumbers();

		return grundyNumbers;
	}

	private void fixVoidBetweenNodes() {
		for (Node child : children) {
			if (child.value.length() - this.value.length() > 1) {
				Node childNode1 = new Node(child.value, child.grundyNumber, child.remainingCharacters);
				childNode1.children.addAll(child.children);
				child.value = child.value.substring(0, this.value.length() + 1);
				child.children = new TreeSet<>();
				child.children.add(childNode1);
				child.fixVoidBetweenNodes();
			}
		}
	}

	private void addGrundyNumbers() {
		grundyNumbers.putIfAbsent(this.getGrundyNumber(), BigInteger.valueOf(0));
		grundyNumbers.put(this.getGrundyNumber(), grundyNumbers.get(this.getGrundyNumber()).add(BigInteger.valueOf(1)));

		if (!this.children.isEmpty()) {
			for (Node child : children) {
				child.addGrundyNumbers();
			}

		} else if (getRemainingCharacters() != null) {
			grundyNumbers.putIfAbsent(1, BigInteger.valueOf(0));
			grundyNumbers.putIfAbsent(0, BigInteger.valueOf(0));
			grundyNumbers.put(1, grundyNumbers.get(1).add(BigInteger.valueOf(getRemainingCharacters() / 2)));
			grundyNumbers.put(0, grundyNumbers.get(0).add(BigInteger.valueOf(getRemainingCharacters() / 2)));

			if (getRemainingCharacters() % 2 == 1) {
				if (this.getGrundyNumber() == 0) {
					grundyNumbers.put(1, grundyNumbers.get(1).add(BigInteger.valueOf(1)));
				} else {
					grundyNumbers.put(0, grundyNumbers.get(0).add(BigInteger.valueOf(1)));
				}
			}

		}
	}

	public void calculateWinningPositions(Map<Integer, BigInteger> values, BigInteger stopOn) {
		winningPositionCounter = BigInteger.valueOf(0);
		this.calculateTreeWinningPositions(values, stopOn);
	}

	private void calculateTreeWinningPositions(Map<Integer, BigInteger> values, BigInteger stopOn) {
		if (winningPositionCounter.compareTo(winningPositionCounter) > 0)
			return;

		this.initialWinningPosition = winningPositionCounter;
		winningPositionCounter = winningPositionCounter.add(values.get(getGrundyNumber()));
		this.finalWinningPosition = winningPositionCounter;

		if (this.children.isEmpty()) {
			winningPositionCounter = winningPositionCounter
					.add(BigInteger.valueOf(getRemainingCharacters() / 2).multiply(values.get(0)));
			winningPositionCounter = winningPositionCounter
					.add(BigInteger.valueOf(getRemainingCharacters() / 2).multiply(values.get(1)));

			if (getRemainingCharacters() % 2 == 1) {
				if (this.getGrundyNumber() == 0) {
					winningPositionCounter = winningPositionCounter.add(values.get(1));
				} else {
					winningPositionCounter = winningPositionCounter.add(values.get(0));
				}
			}
			this.finalWinningPosition = winningPositionCounter;
		}

		if (!this.children.isEmpty()) {
			for (Node child : this.children) {
				child.calculateTreeWinningPositions(values, stopOn);
			}
		}
	}

	public void prune() {
		for (Node child : children) {
			if (child.children.isEmpty()) {
				int removedCharacters = child.value.length() - Math.min(value.length() + 1, child.value.length());
				if (removedCharacters % 2 == 1) {
					if (child.grundyNumber == 1)
						child.grundyNumber = 0;
					else if (child.grundyNumber == 0)
						child.grundyNumber = 1;
				}
				child.value = child.value.substring(0, Math.min(value.length() + 1, child.value.length()));
			} else {
				child.prune();
			}
		}
	}

	public void push(Node newNode) {
		// System.out.printf("Evaluating %s\n", newNode.value);
		if (newNode.value.equals(value)) {
			if (newNode.children.isEmpty()) {
				count++;
			} else {
				for (Node child : newNode.children) {
					this.push(child);
				}
			}
		} else if (!newNode.value.equals(value) && newNode.value.startsWith(value)) {
			Node nextNode = null;

			for (Node child : children) {
				if (child.value.charAt(value.length()) == newNode.value.charAt(value.length())) {
					nextNode = child;
					break;
				}
			}

			if (nextNode != null) {
				// System.out.printf("%s: Pushing %s\n", value, newNode.value);
				nextNode.push(newNode);
			} else {
				// System.out.printf("-----------------------------\n%s: Adding
				// %s\n", value, newNode.value);
				children.add(newNode);
			}

		} else if (!newNode.value.equals(value) && value.startsWith(newNode.value)) {

			Node childNode1 = new Node(this.value, this.grundyNumber, this.remainingCharacters);
			childNode1.children.addAll(this.children);

			this.value = newNode.value;
			children = new TreeSet<>();
			children.add(childNode1);

		} else if (newNode.value.charAt(0) == value.charAt(0)) {
			String newCurrentValue = "";

			for (int i = 0; i < Math.min(newNode.value.length(), value.length()); i++) {
				if (newNode.value.charAt(i) == value.charAt(i)) {
					newCurrentValue += newNode.value.charAt(i);
				} else {
					break;
				}
			}

			Node childNode1 = new Node(this.value, this.grundyNumber, this.remainingCharacters);
			childNode1.children.addAll(this.children);

			Node childNode2 = new Node(newNode.value, newNode.grundyNumber, newNode.remainingCharacters);
			childNode2.children.addAll(newNode.children);

			// System.out.printf("-----------------------------\n%s: Splitting
			// \n\t%s\n\t%s\n", newCurrentValue, value, newNode.value);
			this.value = newCurrentValue;
			children = new TreeSet<>();
			children.add(childNode1);
			children.add(childNode2);
		}
	}

	public List<String> getUnexploredNodes() {
		if (!isExpanded() && count > 1) {
			return Arrays.asList(value);
		}

		List<String> values = new ArrayList<>();

		for (Node child : children) {
			values.addAll(child.getUnexploredNodes());
		}

		return values;
	}

	public void printTree() {
		printTree(0);
	}

	private void printTree(int level) {
		String levelPrefix = new String(new char[level]).replace("\0", "----");
		System.out.println(String.format("%s [level: %d] %s", levelPrefix, level, toString()));

		for (Node child : children) {
			child.printTree(level + 1);
		}
	}

	@Override
	public String toString() {
		return String.format(
				"grundyNumber: %d, count: %d, remainingCharacters: %d winningPositions: [from %d to %d] value: %s",
				getGrundyNumber(), count, getRemainingCharacters(), this.initialWinningPosition,
				this.finalWinningPosition, value);
	}

	@Override
	public int compareTo(Node o) {
		return this.value.compareTo(o.value);
	}
}

class Result {

	private static final int ADDED_AMMOUNT = 100;
	private static final List<Character> ALPHABET = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
	private static List<Character> stringACharacters;
	private static List<Character> stringBCharacters;

	public static List<String> twoStrings(long k, String a, String b) {
		stringACharacters = ALPHABET.stream().filter(x -> a.indexOf(x) >= 0).collect(Collectors.toList());
		stringBCharacters = ALPHABET.stream().filter(x -> b.indexOf(x) >= 0).collect(Collectors.toList());

		Node firstNode = getGrundyNumber(a, "", stringACharacters);
		Node secondNode = getGrundyNumber(b, "", stringBCharacters);

		Map<Integer, BigInteger> firstStringGrundyNumbers = firstNode.getGrundyNumbers();
		Map<Integer, BigInteger> secondStringGrundyNumbers = secondNode.getGrundyNumbers();

		BigInteger totalSumOfPossibleWinningPositions = BigInteger.valueOf(0);
		Map<Integer, BigInteger> positionsPerIteration = new HashMap<>();

		for (int grundyNumberA : firstStringGrundyNumbers.keySet()) {
			positionsPerIteration.putIfAbsent(grundyNumberA, BigInteger.valueOf(0));
			for (int grundyNumberB : secondStringGrundyNumbers.keySet()) {
				int result = grundyNumberA ^ grundyNumberB;
				if (result > 0) {
					BigInteger multiplication = firstStringGrundyNumbers.get(grundyNumberA)
							.multiply(secondStringGrundyNumbers.get(grundyNumberB));

					totalSumOfPossibleWinningPositions = totalSumOfPossibleWinningPositions.add(multiplication);
					positionsPerIteration.put(grundyNumberA,
							positionsPerIteration.get(grundyNumberA).add(secondStringGrundyNumbers.get(grundyNumberB)));
				}
			}
		}

		if (totalSumOfPossibleWinningPositions.compareTo(BigInteger.valueOf(k)) < 0) {
			return Arrays.asList("no solution");
		}

		firstNode.calculateWinningPositions(positionsPerIteration, BigInteger.valueOf(k));

		Node victoriousNode = firstNode.getVictoryNode(BigInteger.valueOf(k));
		String victoriousString = victoriousNode.getValue();
		int victoriousGrundyNumber = victoriousNode.getGrundyNumber();
		BigInteger initialWinningPosition = victoriousNode.getInitialWinningPosition();

		if (victoriousNode.getRemainingCharacters() != null && victoriousNode.getRemainingCharacters() > 0) {
			BigInteger finalWinningPosition = victoriousNode.getInitialWinningPosition()
					.add(positionsPerIteration.get(victoriousNode.getGrundyNumber()));

			for (int i = 1; i < victoriousNode.getRemainingCharacters(); i++) {
				victoriousGrundyNumber = victoriousNode.getGrundyNumber();

				if (i % 2 == 1) {
					if (victoriousNode.getGrundyNumber() == 1) {
						victoriousGrundyNumber = 0;
					} else if (victoriousNode.getGrundyNumber() == 0) {
						victoriousGrundyNumber = 1;
					}
				}

				finalWinningPosition = finalWinningPosition.add(positionsPerIteration.get(victoriousGrundyNumber));

				if (finalWinningPosition.compareTo(BigInteger.valueOf(k)) >= 0) {
					victoriousString = a.substring(a.indexOf(victoriousString),
							a.indexOf(victoriousString) + victoriousString.length() + i);
					break;
				}
				initialWinningPosition = finalWinningPosition;
				// System.out.println(finalWinningPosition);
			}
		}

		Map<Integer, BigInteger> positionsPerIteration2 = new HashMap<>();

		for (int grundyNumberB : secondStringGrundyNumbers.keySet()) {
			positionsPerIteration2.putIfAbsent(grundyNumberB, BigInteger.valueOf(0));
			int result = victoriousGrundyNumber ^ grundyNumberB;
			if (result > 0) {
				positionsPerIteration2.put(grundyNumberB, BigInteger.valueOf(1));
			}
		}

		secondNode.calculateWinningPositions(positionsPerIteration2,
				BigInteger.valueOf(k).subtract(initialWinningPosition));
		// System.out.println("totalSumOfPossibleWinningPositions:" +
		// totalSumOfPossibleWinningPositions);
		// System.out.println("positionsPerIteration:" + positionsPerIteration);
		// firstNode.printTree();
		// secondNode.printTree();
		// System.out.println("firstStringGrundyNumbers:" +
		// firstStringGrundyNumbers);
		// System.out.println("secondStringGrundyNumbers:" +
		// secondStringGrundyNumbers);
		// System.out.println("totalSumOfPossibleWinningPositions:" +
		// totalSumOfPossibleWinningPositions);
		// System.out.println("positionsPerIteration:" + positionsPerIteration);
		// System.out.println(victoriousNode);
		// System.out.println(victoriousString);

		Node victoriousNode2 = secondNode.getVictoryNode(BigInteger.valueOf(k).subtract(initialWinningPosition));

		String victoriousString2 = victoriousNode2.getValue();

		if (victoriousNode2.getRemainingCharacters() != null && victoriousNode2.getRemainingCharacters() > 0) {
			BigInteger finalWinningPosition = victoriousNode2.getInitialWinningPosition()
					.add(positionsPerIteration2.get(victoriousNode2.getGrundyNumber()));

			for (int i = 1; i < victoriousNode2.getRemainingCharacters(); i++) {
				int grundyNumber = victoriousNode2.getGrundyNumber();

				if (i % 2 == 1) {
					if (victoriousNode2.getGrundyNumber() == 1) {
						grundyNumber = 0;
					} else if (victoriousNode2.getGrundyNumber() == 0) {
						grundyNumber = 1;
					}
				}

				finalWinningPosition = finalWinningPosition.add(positionsPerIteration2.get(grundyNumber));

				if (finalWinningPosition.compareTo((BigInteger.valueOf(k).subtract(initialWinningPosition))) >= 0) {
					victoriousString2 = b.substring(b.indexOf(victoriousString2),
							b.indexOf(victoriousString2) + victoriousString2.length() + i);
					break;
				}
			}
		}

		return Arrays.asList(victoriousString, victoriousString2);
	}

	private static Node getGrundyNumber(String currentString, String currentState, List<Character> currentCharacters) {
		Node node = new Node("", 0, null);

		for (Character character : currentCharacters) {
			String newState = currentState + character;
			node.push(getBranches(currentString, newState));
		}
		node.prune();
		return node;
	}

	private static Node getBranches(String currentString, String currentState) {
		// System.out.println("Evaluating " + currentState);
		int beginIndex = currentString.indexOf(currentState);
		Node node = new Node(currentState,
				calculateGrundyNumber(beginIndex + currentState.length(), currentString.length()),
				currentString.length() - beginIndex);
		while (beginIndex >= 0) {
			int endIndex = Math.min(beginIndex + currentState.length() + ADDED_AMMOUNT, currentString.length());
			String currentValue = currentString.substring(beginIndex, endIndex);
			if (!currentValue.equals(currentState)) {
				node.push(new Node(currentValue, calculateGrundyNumber(endIndex, currentString.length()),
						currentString.length() - beginIndex));
			}
			beginIndex = currentString.indexOf(currentState, beginIndex + 1);
		}

		for (String value : node.getUnexploredNodes()) {
			node.push(getBranches(currentString, value));
		}

		return node;
	}

	private static int calculateGrundyNumber(int endIndex, int stringLength) {
		int remainingChars = stringLength - endIndex;

		if (remainingChars % 2 == 1) {
			return 1;
		} else {
			return 0;
		}
	}
}

public class Solution {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int m = Integer.parseInt(firstMultipleInput[1]);

		long k = Long.parseLong(firstMultipleInput[2]);

		String a = bufferedReader.readLine();

		String b = bufferedReader.readLine();

		List<String> result = Result.twoStrings(k, a, b);

		bufferedWriter.write(result.stream().collect(joining("\n")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();
	}
}
