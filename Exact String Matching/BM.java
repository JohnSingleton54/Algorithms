// John M. Singleton
// started:  Sa 4/21/18, last revision:  R 4/26/18
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
		char [] pattern = input_pattern.toCharArray();
		int m = pattern.length;
		int [] charJump = new int [ alpha ];
		
		int count4a = computeJumps( pattern, m, alpha, charJump );
		
		/*for( int i = 0; i < alpha; i++ ) {
		    System.out.print( charJump[i] + " ");
		}
		System.out.print( "\n" );*/
		
		int [] matchJump = new int [ m ];
		
		int count4b = computeMatchJumps( pattern, m, matchJump );
		
		/*for( int i = 0; i < m; i++ ) {
		    System.out.print( matchJump[i] + " ");
		}
		System.out.print( "\n" );*/		
		
		String fileName = "InputFile.txt";
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
		
		int [] match = boyerMooreScan( pattern, text, m, charJump, matchJump );
		int count45 = count4a + count4b + match[1];
		if( match[0] == -1 ) {
			System.out.println( "No match was found.  count45:  " + count45 );
		}
		else {
			System.out.println( "A match was found at index " + match[0] + ".  count45:  " + count45 );
		}
		
		}
		catch( IOException e ) { System.err.println( e ); }
	}
	
	public static int computeJumps( char [] pattern, int m, int alpha, int [] charJump ) {
		int count4a = 0;
		for( int i = 0; i < alpha; i++) {
			charJump[i] = m;
			count4a++;
		}
		for( int k = 0; k < m; k++ ) {
			charJump[ (int)pattern[k] - 65 ] =  m - k - 1;
			count4a++;
		}
		return count4a;
	}
 
	public static int computeMatchJumps( char [] pattern, int m, int [] matchJump ) {
		int count4b = 0;
		for( int k = 0; k < m; k++ ) {
			matchJump[k] = m + 1;  // This represents an impossibly large slide.
			count4b++;
		}
		 
		int [] suffix = new int [ m + 1 ];
	 	suffix[m] = m + 1;
		for( int k = m - 1; k >= 0; k-- ) {
		 	int s = suffix[k+1];
		    //System.out.println( "in for loop" );			
			while( s <= m ) {
				//System.out.println( "in while loop" );
				count4b++;
				if( pattern[k] == pattern[s-1] ) {
					break;
				}
				matchJump[s-1] = Math.min( matchJump[s-1], s - (k + 1) );
				count4b++;
				s = suffix[s];
			}
			suffix[k] = s - 1;
		}
	 	/*for( int i = 0; i < m + 1; i++) {
		 	System.out.print( suffix[i] );
		}
		System.out.print( "\n" );
		 
		System.out.println( "slide values prior to final while loop:" );
		for( int i = 0; i < m; i++) {
		 	System.out.print( matchJump[i] );
		}
		System.out.print( "\n" );*/
	 
		int low = 1;
		int shift = suffix[0];
		while( shift <= m ) {
		 	for( int k = low; k <= shift; k++ ) {
				matchJump[k-1] = Math.min( matchJump[k-1], shift );
				count4b++;
            }
			low = shift + 1;
			shift = suffix[shift];
			//System.out.println( "shift:  " + shift );
		} 
		/*System.out.println( "slide values:" );
		for( int i = 0; i < m; i++) {
		 	System.out.print( matchJump[i] );
		}
		System.out.print( "\n" );*/
		 
		for( int k = 0; k < m; k++) {
		 	matchJump[k] += (m - k - 1);
			count4b++;
		}
		return count4b;
	}
	
	public static int [] boyerMooreScan( char [] pattern, ArrayList<Character> text, int m, int [] charJump, int [] matchJump ) {
	    int [] match = new int[2];
		match[0] = -1;
		int count5 = 0;
		int j = m - 1;  // j indexes text characters
		int k = m - 1;  // k indexes the pattern
		while( j < text.size() ) {
			if( k < 0 ) {
				match[0] = j + 1;  // A match was found!
				break;
			}
			count5++;
			if( text.get( j ) == pattern[ k ] ) {
				j--;
				k--;
			}
			else {
			    count5++;
				j += Math.max( charJump[(int)text.get(j) - 65], matchJump[k] );
				k = m - 1;
			}
		}
		match[1] = count5;
        return match;
	}
	
}

