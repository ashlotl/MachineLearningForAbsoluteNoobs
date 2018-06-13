package main;

import types.*;

import java.util.HashMap;
import java.util.Map;

import inherit.*;

public class Main {
	public static Map<String, Integer>[] trainingMap;
    // static//this runs immediately upon class creation, so I think it may happen even before the main() function. Should function as init for training table.
    // {
    // 	trainingMap = new HashMap<String, Integer>();
    // 	trainingMap.put("abs", 3);//all lower-case
    // 	trainingMap.put("bob", 3);
    // 	trainingMap.put("voyage", 6);
    // 	trainingMap.put("pajamas", 7);
    // 	trainingMap.put("dimensional", 11);
    // 	trainingMap.put("processing", 10);
    // 	trainingMap.put("finger", 6);
    // 	trainingMap.put("i", 1);
    // 	trainingMap.put("eye", 3);
    // 	trainingMap.put("buffalo", 7);
    // 	trainingMap.put("mississippi", 11);
    // 	trainingMap.put("awkward", 7);
    // 	trainingMap.put("xylophone", 9);
    // 	trainingMap.put("zebra", 5);
    // 	trainingMap.put("chicken", 7);
    // 	trainingMap.put("boi", 3);
    // 	trainingMap.put("vader", 5);
    // 	trainingMap.put("whoopie", 7);
    // 	trainingMap.put("blender", 7);
    // 	trainingMap.put("insomnia", 8);//making this map.
    // 	trainingMap.put("hilarious", 9);
    // 	trainingMap.put("cake", 4);
    // 	trainingMap.put("query", 5);
    // 	trainingMap.put("tree", 4);
    // 	trainingMap.put("under", 5);
    // 	trainingMap.put("yak", 3);
    // 	trainingMap.put("training", 8);
    // 	trainingMap.put("capricious", 10);
    // 	trainingMap.put("omelette", 8);
    // }
	static GNode[][] nodegraph;
	public static int[] composition= {20,20,50,100,50,35,30,25,20};//number of nodes per layer
	public static String usecase="";
	static double desiredsuccess=0.3;
	static double maxmutationincrement=.001;
	static int mutationsperiteration=500;
	static int numtimesthrough=50;
	static int nodesedited=100;
	static int numberofpropagations=1;
	static int casesperset=100;
	public static void main(String[] args) {
		init();
		train();
		usecase="hi";
		System.out.println("hi: "+(run()));
	}
	public static void init() {
		int lastnum=0;
		for (int num:composition) {
			if(num>lastnum) {
				lastnum=num;
			}
		}
		nodegraph=new GNode[composition.length][lastnum];
		for(int layer=0;layer<composition.length;layer++) {
			for(int node=0;node<composition[layer];node++) {
				if(layer==0){
					nodegraph[layer][node]=new Peripheral();
					nodegraph[layer][node].layer=0;
					nodegraph[layer][node].node=node;
					nodegraph[layer][node].weight=new double[1];
					nodegraph[layer][node].weight[0]=1;
				} else {
					nodegraph[layer][node]=new Neuron();
					nodegraph[layer][node].layer=layer;
					nodegraph[layer][node].node=node;
					nodegraph[layer][node].input=nodegraph[layer-1];
					nodegraph[layer][node].weight=new double[composition[layer-1]];//TODO read from save file.
					for(int i=0;i<composition[layer-1];i++) {
						nodegraph[layer][node].weight[i]=1;
					}
				}
				nodegraph[layer][node].oldweight=weight;
			}
		}
	}

	public static double run() {
		double f=0;
		for(int layer=0;layer<nodegraph.length;layer++) {
			for(int node=0;node<composition[layer];node++) {
				f=nodegraph[layer][node].out();


		}
		return f;
	}
	public static void train() {//sufficient room for multi-processing.
		double loss=0;
		boolean firsttime=true;
		for(int set=0;set<trainingMap.length;set++){
			for(String datapoint:trainingMap.keySet()){
				int num=trainingMap.get(datapoint);
				usecase=datapoint;
				if(firsttime){
					run();//initial to prepare backprop
					firsttime=false;
					for(int node;node<composition[composition.length-1];node++){
						if(node!=num){
							loss+=Math.pow(nodegraph[composition.length-1][node].output,2);
						} else {
							loss+=Math.pow(1-nodegraph[composition.length-1][node].output,2);
						}
					}
					loss=loss/composition[composition.length-1];
				}
				nodegraph[nodegraph.length-1][num].backpropagate();//now the graph is only slightly more efficient, now we must take derivatives from backpropagation to determine our stochastic gradient descent.
				//let's make a new success calculation
				run();
				double nextloss=0;
				for(int node;node<composition[composition.length-1];node++){
					if(node!=num){
						nextloss+=Math.pow(nodegraph[composition.length-1][node].output,2);
					} else {
						nextloss+=Math.pow(1-nodegraph[composition.length-1][node].output,2);
					}
				}
				loss=loss/composition[composition.length-1];
				//Congrats, we are losers. Let's revel in loss and calculate derivatives for stochastic descent.
				double numeratorsum1=0;
				double numeratorsum2=0;
				for(int layer=0; layer<composition.length; layer++){
					for(int node2=0; node2<composition[layer]; node2++){
						for(int it=0;it<nodegraph[layer][node2].weight.length;it++){
							numeratorsum2+=nodegraph[layer][node2].weight[it];
							numeratorsum1+=nodegraph[layer][node2].oldweight[it];
						}

					}
				}
				double derivative=(numeratorsum2-numeratorsum1)/(nextloss-loss);
				//TODO edit weights accordingly. (the hard part?)
				loss=nextloss;
			}
		}
	}
}
