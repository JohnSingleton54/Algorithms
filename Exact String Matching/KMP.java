// John M. Singleton
// started:  W 4/11/18, last revision:  Sa 4/21/18
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
		ArrayList<Character> text = new ArrayList<Character>();
		try { Scanner sc = new Scanner( new File( fileName ) );
		String characters = sc.next();
		for( int i = 0; i < characters.length(); i++) {
			text.add( i, characters.charAt( i ) );		
		}
		
		/*int n = text.size();
		for( int i = 0; i < n; i++ ) {
			System.out.println( text.get( i ) );
		}*/
		
		int match = kmpScan( pattern, text, m, fail );
		
		if( match == -1 ) {
			System.out.println( "No match was found." );
		}
		else {
			System.out.println( "A match was found at index " + match + " ." );
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
	
	public static int kmpScan( char [] pattern, ArrayList<Character> text, int m, int [] fail ) {
	    int match = -1;
		int j = 0;  // j indexes text characters
		int k = 0;  // k indexes the pattern and fail array
		while( j < text.size() ) {
			System.out.println( "Noomi Rapace is hot!" + j + k );
			if( k == -1 ) {
				j++;
				k = 0;  // Start at the beginning of the pattern.
			}
			else if( text.get( j ) == pattern[ k ] ) {
				j++;
				k++;
				// I moved the following four lines of code from the beginning of the while loop to
				// here in order to fix this bug:  No match is being found when the pattern is at
				// the very end of the text.  (cf. Algorithm 3 on p. 494 of Baase and Gelder.)
				if( k == m ) {
				    match = j - m;  // If we get here, then a match has been found!
				    break;
			    }
			}
			else {
				k = fail[ k ];  // Follow the fail arrow.
			}
		}
        return match;
	}
}

