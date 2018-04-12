// John M. Singleton
// started:  W 4/11/18, last revision:  R 4/12/18
// CSCI 532
// Project
// the Knuth-Morris-Pratt (KMP) Algorithm
// References:  Computer Algorithms by Baase and Gelder 3e C11S3 pp. 487-495

import java.io.*;
import java.util.*;

public class KMP {

	public static void main( String [] args ) throws IOException {
	
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		System.out.print( "Enter a pattern to search for:  ");
		String input_pattern = reader.readLine();
		// The flowchart representation uses two arrays.  One contains the characters of the
		// pattern (pattern), and the other contains the failure links (fail).
		char [] pattern = input_pattern.toCharArray();
		int m = pattern.length;
		int [] fail = new int [ m ];
		
		constructFlowchart( pattern, m, fail );
		for( int i = 0; i < m; i++) {
			System.out.println( fail[i] );
		}
		
		
		// The following code is from another program, but it may help me input the text.
/*		String fileName = "KMP_InputFile.txt";
		int degree;
		double [] poly1, poly2;
		try { Scanner sc = new Scanner( new File( "HW1_InputFile.txt" ) ); 
		degree = sc.nextInt();
		poly1 = new double [ degree + 1 ];
		poly2 = new double [ degree + 1 ];
		for ( int i = 0; i <= degree; i++ ) {
			poly1 [ degree - i ] = sc.nextDouble();
		}
		for ( int i = 0; i <= degree; i++ ) {
			poly2 [ degree - i ] = sc.nextDouble();
		}
		System.out.println( "degree = " + degree );
		System.out.println( Arrays.toString( poly1 ) );
		System.out.println( Arrays.toString( poly2 ) );
		}
		catch( IOException e ) { System.err.println( e ); }*/
	}
	
	// The following method constructs the KMP flowchart.
	// *** The following method has a bug.  It is not correctly constructing the flowchart for
	// pattern ABABABCB.  (See Figure 11.6 on p. 492.)
	public static void constructFlowchart( char [] pattern, int m, int [] fail ) {
		int k, s;
		fail[0] = -1;
		for( k = 1; k < m; k++ ) {
			s = fail[k-1];
			while( s >= 1 ) {
				if( pattern[s] == pattern[k-1] )
				    break;
				s = fail[s];
			}
			fail[k] = s + 1;
		}	
	}
}

