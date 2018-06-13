package inherit;

import types.GNode;

public class Neuron extends GNode {
	@Override
	public double out() {
		double sum=0;
		for(int chi=0;chi<main.Main.composition[layer-1];chi++) {
			double w=weight[chi];
			sum+=w*input[chi].output;
		}
		//System.out.println("sum"+sum);
		output=1/(1+Math.pow(Math.E, (sum)));
		return output;
	}
	public void backpropagate(boolean decreaseOthers) {
		int[] largest=new int[numberofpropagations];
		int[] careabout=new int[numberofpropagations];
		for(int i=0;i<composition[layer-1];i++){
			for(int subi=numberofpropagations;subi>0;subi--){
				if(nodegraph[layer-1][i].output>largest[subi]){
					util.Util.insert(largest,subi,nodegraph[layer-1][i].output);
					careabout[subi]=i;
				}
			}
		}
		double multiplier=1;
		if(decreaseOthers){
			for(int i3=0;i2<composition[layer-1];i2++){
				weight[i3]-=maxmutationincrement;
				multiplier=2;
			}
		}
		for(int i2=0;i<numberofpropagations;i++){
			weight[careabout[i2]]+=multiplier*maxmutationincrement;
			nodegraph[layer-1][careabout[i2]].backpropagate();//TODO multiple threads/processes
		}
	}
}
