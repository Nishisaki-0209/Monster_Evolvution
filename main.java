import java.util.*;

class Status {
    int[] stat;
}

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//区別すべき鉱石の種類数を示す整数 N
        int L = sc.nextInt();//各鉱石にある特徴数 L 
        
        Status[] status = new Status[N];
        
        for (int i = 0; i < N ; i++) {
            status[i] = new Status();
            status[i].stat = new int[L];
            //System.out.print((i+1) + "番目の鉱石の特徴を入力してください : ");
            for (int j = 0; j < L ; j++) {
                status[i].stat[j] = sc.nextInt();
            }
            //System.out.println( Arrays.toString(status[i].stat));
        }
        
        
        
        int minFeatures = L;

        // 特徴の組み合わせを全て試す
        for(int i = 0; i < (1 << L); i++) {
            int featuresCount = 0;
            boolean isPossible = true;

            // ある特徴の組み合わせが鉱石を区別できるかどうかを判定する
            for(int j = 0; j < N - 1; j++) {
                for(int k = j + 1; k < N; k++) {
                    boolean isSame = true;
                    for(int m = 0; m < L; m++) {
                        // ある特徴が異なる場合
                        if(((i >> m) & 1) == 1 && status[j].stat[m] != status[k].stat[m]) {
                            isSame = false;
                            break;
                        }
                    }
                    if(isSame == true) {
                        isPossible = false;
                        break;
                    }
                }
                if(isPossible == false) {
                    break;
                }
            }

            // 鉱石を区別できる場合
            if(isPossible == true) {
                for(int j = 0; j < L; j++) {
                    if(((i >> j) & 1) == 1) {
                        featuresCount++;
                    }
                }
                if(featuresCount < minFeatures) {
                    minFeatures = featuresCount;
                }
            }
        }
        
        //System.out.println("覚えるべき特徴の数の最小値は" + minFeatures + "です");
        System.out.println(minFeatures);
    }  
}
