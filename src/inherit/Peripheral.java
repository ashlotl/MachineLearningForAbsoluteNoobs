package inherit;

import types.GNode;
public class Peripheral extends GNode {
	@Override
	public double out() {
		for (int c=0;c<main.Main.usecase.length();c++) {
			if('~'!=(main.Main.usecase.charAt(c))) {
				if(c==main.Main.usecase.length()-1) {
				output=1/(double)main.Main.usecase.charAt(c);
					if(main.Main.usecase.length()==1) {
						main.Main.usecase="~";
					} else {
						main.Main.usecase=main.Main.usecase.substring(0, c-1)+"~";
					}
				}else {
					if (c==0) {
						main.Main.usecase="~"+main.Main.usecase.substring(c+1);
					} else {
					main.Main.usecase=main.Main.usecase.substring(0, c-1)+"~"+main.Main.usecase.substring(c+1);
					}
				}
				return 1;
			}
		}
		return 0;
	}
	public void backpropagate() {
		return;
	}

}
