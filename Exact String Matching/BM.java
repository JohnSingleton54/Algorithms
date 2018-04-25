// John M. Singleton
// started:  Sa 4/21/18, last revision:  T 4/24/18
// CSCI 532
// Project
// the Boyer-Moore (BM) Algorithm
// References:  Computer Algorithms by Baase and Gelder 3e C11S4 pp. 495-504

import java.io.*;
import java.util.*;

public class BM {

	public static void main( String [] args ) throws IOException {
	    // The alphabet consists of the 26 uppercase letters.
		int alpha = 26;
		/*System.out.println( (int)'A' ); output 65
		System.out.println( (int)'B' ); output 66
		System.out.println( (int)'Z' ); output 90
		System.out.println( (char)65 ); output A*/
		
	
		BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
		System.out.print( "Enter a pattern to search for:  ");
		String input_pattern = reader.readLine();
		// The flowchart representation uses two arrays.  One contains the characters of the
		// pattern (pattern), and the other contains the failure links (fail).
		char [] pattern = input_pattern.toCharArray();
		int m = pattern.length;
		int [] charJump = new int [ alpha ];
		
		computeJumps( pattern, m, alpha, charJump );
		
		for( int i = 0; i < alpha; i++ ) {
		    System.out.print( charJump[i] + " ");
		}
		System.out.print( "\n" );
		
		int [] matchJump = new int [ m ];
		
		computeMatchJumps( pattern, m, matchJump );
		for( int i = 0; i < m; i++ ) {
		    System.out.print( matchJump[i] + " ");
		}
		System.out.print( "\n" );		
		
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
		
		
		}
		catch( IOException e ) { System.err.println( e ); }
	}
	
	public static void computeJumps( char [] pattern, int m, int alpha, int [] charJump ) {
		for( int i = 0; i < alpha; i++) {
			charJump[i] = m;
		}
		for( int k = 0; k < m; k++ ) {
			charJump[ (int)pattern[k] - 65 ]= m - k - 1;
		}
	}
 
	public static void computeMatchJumps( char [] pattern, int m, int [] matchJump ) {
		 for( int k = 0; k < m; k++ ) {
		 	matchJump[k] = m + 1;  // This represents an impossibly large slide.
		 }
		 
		 int [] suffix = new int [ m + 1 ];
		 suffix[m] = m + 1;
		 for( int k = m - 1; k >= 0; k-- ) {
		 	int s = suffix[k+1];
		    //System.out.println( "in for loop" );			
			while( s <= m ) {
				//System.out.println( "in while loop" );
				if( pattern[k] == pattern[s-1] ) {
					break;
				}
				matchJump[s-1] = Math.min( matchJump[s-1], s - (k + 1) );
				s = suffix[s];
			}
			suffix[k] = s - 1;
		 }
		 for( int i = 0; i < m + 1; i++) {
		 	System.out.print( suffix[i] );
		 }
		 System.out.print( "\n" );
		 
		 System.out.println( "slide values prior to final while loop:" );
		 for( int i = 0; i < m; i++) {
		 	System.out.print( matchJump[i] );
		 }
		 System.out.print( "\n" );
		 
		 int low = 1;
		 int shift = suffix[0];
		 while( shift < m ) {
		 	for( int k = low; k <= shift; k++ ) {
				if( k == 3) {
					System.out.println( "k = 3:  matchJump[3] = " + matchJump[3] + ", shift = " + shift );
				}
				matchJump[k-1] = Math.min( matchJump[k-1], shift );
			}
			low = shift + 1;
			shift = suffix[shift];
			System.out.println( "shift:  " + shift );
		 }
		 System.out.println( "slide values:" );
		 for( int i = 0; i < m; i++) {
		 	System.out.print( matchJump[i] );
		 }
		 System.out.print( "\n" );
		 
		 for( int k = 0; k < m; k++) {
		 	matchJump[k] += (m - k - 1);
		 }
		 
		 return;
	}
 
}

