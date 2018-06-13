package util;

public class Util {
  Main refMain;

  int charLim;
  int setLim;

  Map[] setMap;
  char[] alphabet;

  int charValue;
  int randValue;

  Util(int lim, int setLim) {
    refMain = new Main();

    this.charLim = lim;
    this.setLim = setLim;

    setMap = refMain.trainingMap;
    alphabet = new char[62]; {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", a, A, b, B, c, C, d, D, e, E, f, F, g, G, h, H, i, I, j, J, k, K, l, L, m, M, n, N, o, O, p, P, q, Q, r, R, s, S, t, T, u, U, v, V, w, W, x, X, y, Y, z, Z}
  }

  public void setGenerator() {
    for(int i = 0; i < setMap.length(); i++) {
      for(int j = 1; j < setLim; j++) {

        charValue = (int )((Math.random()*charLim)+1);
        String newString;

        for(int n = 1; n < charValue; n++) {
          randValue = (int )(Math.random()*62);
          newString = newString + alphabet[randValue];
        }

        System.out.println(newString);
        setMap[i].put(newString, charValue);

      }
    }
  }
  public int[] insert(int[] old,int index, int value) {
       int[] n=old;
       int previous=old[index];
       for(int i=1;i<=index;i++){
            previous=n[i];
            n[i-1]=previous;
            if(i==index){
                 n[i]=value
                 return n;
            }
       }
       if(index==0){
            n[0]=value;
            return n;
       }
 }
 public int[] deletion(int[] indicesfordeletion,int[] deletefrom){
      int[] fin=new int[deletefrom.length-indicesfordeletion.length];
      boolean no=false;
      int omitted=0;
      for(int i=0;i<deletefrom.length;i++){
           for(int i2=0;i2<indicesfordeletion;i2++){
                if(i==indicesfordeletion[i2]){
                     no=true;
                }
           }
           if(!no){
                fin[i-omitted]=deletfrom[i];
                no=false;
           } else {
                omitted++;
           }
      }
      return fin;
}

}
