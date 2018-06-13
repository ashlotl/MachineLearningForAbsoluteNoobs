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
	public static int[] composition= {20,20,50,100,50,20,20,10,5,1};//number of nodes per layer
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
	public static void train() {
		for(int set=0;set<trainingMap.length;set++){
			for(String datapoint:trainingMap.keySet()){

			}
		}
	}
}
