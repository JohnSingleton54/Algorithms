// John M. Singleton
// started:  W 4/11/18, last revision:  Sa 4/14/18
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
		
		
		String fileName = "KMP_InputFile.txt";
		// ArrayList - See pp. 229-232 of Lewis and Loftus 9e.  An ArrayList stores references to
		// objects, not primitive values.  So I had to use the wrapper class Character instead of
		// the primitive type char.
		ArrayList<Character> text = new ArrayList<Character>();
		try { Scanner sc = new Scanner( new File( fileName ) );
		String characters = sc.next();
		for( int i = 0; i < characters.length(); i++) {
			text.add( i, characters.charAt( i ) );		
		}
		int n = text.size();
		for( int i = 0; i < n; i++ ) {
			System.out.println( text.get( i ) );
		}
		}
		catch( IOException e ) { System.err.println( e ); }
	}
 	
	// The following method constructs the KMP flowchart.  (See Algorithm 11.2 on p. 493.)
	// It is correctly constructing the flowchart for pattern ABABABCB.  (See Figure 11.6 on p.
	// 492.)
	public static void constructFlowchart( char [] pattern, int m, int [] fail ) {
		int k, s;
		fail[0] = -1;
		for( k = 1; k < m; k++ ) {
			s = fail[k-1];
			while( s >= 0 ) {
				if( pattern[s] == pattern[k-1] )
				    break;
				s = fail[s];
			}
			fail[k] = s + 1;
		}	
	}
}

