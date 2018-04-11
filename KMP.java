// John M. Singleton
// W 4/11/18
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
		String pattern = reader.readLine();
		System.out.println( pattern );
		
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
		catch( IOException e ) { System.err.println( e ); }
	}
	
	// The following method constructs the KMP flowchart.
	public static void flowchart() {
*/		
	}
}

