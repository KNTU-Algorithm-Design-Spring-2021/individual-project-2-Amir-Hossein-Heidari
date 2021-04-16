import java.util.Scanner;

public class WordWrap {
    public static void main(String[] args) {
		String[] words = detect();
		int[] size = size(words);
		System.out.println("\nmax = " + 50);
		show(words,wordWrap(size,50),size.length-1);
    }

	private static int[] size(String[] words){
		int[] size = new int[words.length];
		for(int i=0;i<words.length;i++){
			size[i] = words[i].length();
		}
		return size;
	}

	private static String[] detect(){
		Scanner in = new Scanner(System.in);
		String input = "";
		while(in.hasNext()){
			input += in.nextLine();
		}
		in.close();
		return input.split(" ");
	}
	
	private static void show(String[] words,int[] track,int n){
        if (track[n] != 0)
        	show(words,track, track[n]-1);
		System.out.print("| ");
			for(int i=track[n];i<=n;i++){
			System.out.print(words[i] + " ");
		}
		System.out.println();
	}

	private static int[] wordWrap (int[] input, int M){
        
        int n = input.length;
		int[][] extraSpace = new int[n][n];
		int[][] lineCost = new int[n][n];
		int[] cost = new int[n+1];	
		int[] track = new int[n];
	
		for(int i=0;i<n;i++){
			extraSpace[i][i] = M - input[i];
			for(int j=i+1;j<n;j++)
			    extraSpace[i][j] = extraSpace[i][j-1] - input[j] - 1;
		}
		
		for(int i=0;i<n;i++){
			for(int j=i;j<n;j++){
				if(extraSpace[i][j]<0)
					lineCost[i][j] = Integer.MAX_VALUE;
				else if(j==n-1)
					lineCost[i][j] = 0;
				else
					lineCost[i][j] = extraSpace[i][j]*extraSpace[i][j]*extraSpace[i][j];
			}
		}
		
		cost[0] = 0;
		for(int j=0;j<n;j++){
			cost[j+1] = Integer.MAX_VALUE;
			for(int i=0;i<=j;i++){
				if(cost[i]!=Integer.MAX_VALUE && lineCost[i][j]!=Integer.MAX_VALUE &&
				(cost[i] + lineCost[i][j] < cost[j+1])){
					cost[j+1] = cost[i] + lineCost[i][j];
					track[j] = i;
				}
			}
		}
	
		return track;
	}
}