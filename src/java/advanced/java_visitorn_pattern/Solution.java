package java.advanced.java_visitorn_pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

enum Color {
	RED, GREEN
}

abstract class Tree {

	private int value;
	private Color color;
	private int depth;

	public Tree(int value, Color color, int depth) {
		this.value = value;
		this.color = color;
		this.depth = depth;
	}

	public int getValue() {
		return value;
	}

	public Color getColor() {
		return color;
	}

	public int getDepth() {
		return depth;
	}

	public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

	private ArrayList<Tree> children = new ArrayList<>();

	public TreeNode(int value, Color color, int depth) {
		super(value, color, depth);
	}

	public void accept(TreeVis visitor) {
		visitor.visitNode(this);

		for (Tree child : children) {
			child.accept(visitor);
		}
	}

	public void addChild(Tree child) {
		children.add(child);
	}
}

class TreeLeaf extends Tree {

	public TreeLeaf(int value, Color color, int depth) {
		super(value, color, depth);
	}

	public void accept(TreeVis visitor) {
		visitor.visitLeaf(this);
	}
}

abstract class TreeVis {
	public abstract int getResult();

	public abstract void visitNode(TreeNode node);

	public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
	private int result = 0;

	public int getResult() {
		return result;
	}

	public void visitNode(TreeNode node) {
		// implement this
	}

	public void visitLeaf(TreeLeaf leaf) {
		result += leaf.getValue();
	}
}

class ProductOfRedNodesVisitor extends TreeVis {
	private long result = 1;
	private final int M = 1000000007;

	public int getResult() {
		return (int) result;
	}

	public void visitNode(TreeNode node) {
		if (node.getColor().equals(Color.RED)) {
			result = (result * node.getValue()) % M;
		}
	}

	public void visitLeaf(TreeLeaf leaf) {
		if (leaf.getColor().equals(Color.RED)) {
			result = (result * leaf.getValue()) % M;
		}
	}
}

class FancyVisitor extends TreeVis {
	private int sumOfNonLeafNodesAtEvenDepth = 0;
	private int sumOfGreenLeafNodes = 0;

	public int getResult() {
		return Math.abs(sumOfNonLeafNodesAtEvenDepth - sumOfGreenLeafNodes);
	}

	public void visitNode(TreeNode node) {
		if (node.getDepth() % 2 == 0) {
			sumOfNonLeafNodesAtEvenDepth += node.getValue();
		}
	}

	public void visitLeaf(TreeLeaf leaf) {
		if (leaf.getColor().equals(Color.GREEN)) {
			sumOfGreenLeafNodes += leaf.getValue();
		}
	}
}

class NodeIndicator {
	private int nodeIndex;
	private int childNodeIndex;

	public NodeIndicator(int nodeIndex, int childNodeIndex) {
		this.nodeIndex = nodeIndex;
		this.childNodeIndex = childNodeIndex;
	}

	public int getNodeIndex() {
		return this.nodeIndex;
	}

	public int getChildNodeIndex() {
		return this.childNodeIndex;
	}
}

public class Solution {

	private static List<Integer> nodeValues = new ArrayList<>();
	private static List<Color> nodeColors = new ArrayList<>();
	private static Map<Integer, Tree> nodes = new HashMap<>();
	private static Map<Integer, List<Integer>> neighbours = new HashMap<>();

	public static Tree solve() {
		Scanner scanner = new Scanner(System.in);

		int numberOfNodes = scanner.nextInt();

		for (int i = 0; i < numberOfNodes; i++) {
			int nodeValue = scanner.nextInt();
			nodeValues.add(nodeValue);
		}

		for (int i = 0; i < numberOfNodes; i++) {
			int nodeColorIndicator = scanner.nextInt();
			nodeColors.add(nodeColorIndicator == 0 ? Color.RED : Color.GREEN);
		}

		for (int i = 0; i < numberOfNodes - 1; i++) {
			Integer nodeIndex = scanner.nextInt() - 1;
			Integer childNodeIndex = scanner.nextInt() - 1;

			if (neighbours.get(nodeIndex) == null)
				neighbours.put(nodeIndex, new ArrayList<Integer>());
			if (neighbours.get(childNodeIndex) == null)
				neighbours.put(childNodeIndex, new ArrayList<Integer>());

			neighbours.get(nodeIndex).add(childNodeIndex);
			neighbours.get(childNodeIndex).add(nodeIndex);
		}

		scanner.close();
		return getNode(0, 0, 0);
	}

	private static Tree getNode(Integer index, Integer depth, Integer parentIndex) {
		if (nodes.keySet().contains(index))
			return nodes.get(index);

		List<Integer> childrenNodes = neighbours.get(index);
		childrenNodes.remove(parentIndex);

		Tree node;

		if (childrenNodes.size() == 0) {
			node = new TreeLeaf(nodeValues.get(index), nodeColors.get(index), depth);
		} else {
			node = new TreeNode(nodeValues.get(index), nodeColors.get(index), depth);

			for (Integer childNode : childrenNodes) {
				((TreeNode) node).addChild(getNode(childNode, depth + 1, index));
			}
		}

		nodes.put(index, node);
		return node;
	}
}