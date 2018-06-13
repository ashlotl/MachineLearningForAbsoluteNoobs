package types;
//pronounced "gnome", d is silent, add random Ms.
public abstract class GNode {
	public int node;
	public int layer;
	public double output;
	public GNode[] input;
	public double[] weight;
	public double[] oldweight;

	public abstract double out();//compare inputs, whether they be hardware or nodes.
	public abstract void backpropagate();//compare inputs, whether they be hardware or nodes.
}
