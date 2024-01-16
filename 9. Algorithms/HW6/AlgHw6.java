package algHw6;

public class AlgHw6 {
	public static void main(String args[]) {
		double[] p = {0.00, 0.15, 0.10, 0.05, 0.10, 0.20};
		double[] q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
		OptimalBST(p, q, q.length);
	}
	
	private static void OptimalBST(double[] p, double[] q, int n) {
		double[][] e = new double[n+1][n];
		double[][] w = new double[n+1][n];
		int[][] root = new int[n][n];
		
		for(int i = 1; i < n+1; i++) {
			e[i][i-1] = q[i-1];
			w[i][i-1] = q[i-1];
		}
		
		for(int l = 1; l <= n; l++) {
			for(int i = 1; i < n-l+1; i++) {
				int j = i+l-1;
				e[i][j] = Integer.MAX_VALUE;
				w[i][j] = w[i][j-1] + p[j] + q[j];
				for(int r = i; r <= j; r++) {
					double t = e[i][r-1] + e[r+1][j] + w[i][j];
					if(t < e[i][j]) {
						e[i][j] = t;
						root[i][j] = r;
					}
				}
			}
		}
		PrintHalfTable(e, "e Table");
		PrintHalfTable(w, "w Table");
		PrintRootHalfTable(root, "root Table");
	}
	
	private static void PrintHalfTable(double[][] table, String tableName){
		System.out.println(tableName + ":");
		for(int i = 1; i <= table.length; i++) {
			for(int j = i - 1; j < table.length - 1; j++) {
				System.out.printf("%.2f ", table[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void PrintRootHalfTable(int[][] table, String tableName){
		System.out.println(tableName + ":");
		for(int i = 1; i <= table.length; i++) {
			for(int j = i; j < table.length; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
	}
}
