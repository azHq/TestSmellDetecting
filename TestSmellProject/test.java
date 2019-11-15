package atiq;

public class Minimax {
	
	int value;
	int MAX=10000;
	int MIN=-10000;
	int values[] = { 3, 5, 6, 9, 1, 2, 0, -1 }; 
	public Minimax() {
		
	}
	
	public void findBestValue(int[] arr) {
		
		int alpha=-10000,index=-10;
		
			
			//int max=minimax(arr,0,-1000,1000,0,false);
			
			int max=minimax(0, 0, true, values, MIN, MAX);
			
			
			
			
		
		
		
		System.out.println(max);
		
		
		
	}
	
	
	/*public int minimax(int [] arr,int index,int alpha,int beta,int level,boolean team) {
		
		
		if(level==3) {
			
			
			return arr[index];
		}
		
		
		if(team) {
			
			
			int newBeta=alpha;
				
			newBeta=Math.min(minimax(arr,index*2,-1000,1000,level+1,false),minimax(arr,index*2+1,-1000,1000,level+1,false));
			
			return newBeta;
			
		}
		else {
			
			int newAlpha=beta;
			
			
				
				newAlpha=Math.max(minimax(arr,index*2,-1000,1000,level+1,true),minimax(arr,index*2+1,-1000,1000,level+1,true));
			
			
			System.out.println(newAlpha);
			return newAlpha;
		}
		
		
		
		
	}*/
	
	public int minimax(int depth, int nodeIndex, 
            boolean maximizingPlayer, 
            int values[], int alpha,  
            int beta) { 
      
   
    if (depth == 3) 
        return values[nodeIndex]; 
  
    if (maximizingPlayer) 
    { 
        int best = MIN; 
  
        // Recur for left and  
        // right children 
        for (int i = 0; i < 2; i++) 
        { 
              
            int val = minimax(depth + 1, nodeIndex * 2 + i,  
                              false, values, alpha, beta); 
            best = Math.max(best, val); 
            alpha = Math.max(alpha, best); 
  
            // Alpha Beta Pruning 
            if (beta <= alpha) 
                break; 
        } 
        return best; 
    } 
    else
    { 
        int best = MAX; 
  
        // Recur for left and 
        // right children 
        for (int i = 0; i < 2; i++) 
        { 
            int val = minimax(depth + 1, nodeIndex * 2 + i, 
                              true, values, alpha, beta); 
            best = Math.min(best, val); 
            beta = Math.min(beta, best); 
  
            // Alpha Beta Pruning 
            if (beta <= alpha) 
                break; 
        } 
        return best; 
    } 
} 

}
