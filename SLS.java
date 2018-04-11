// John M. Singleton
// M 2/19/18
// HW 2
// the segmented least squares algorithm

import java.io.*;
import java.util.*;

public class SLS {

	public static void main( String [] args ) {
	
	    // the following code reads n points from a text file and stores their x-values and y-values in separate arrays
	    String fileName = "HW2_InputFile.txt";
		int n;
		double [] x, y;
		try { Scanner sc = new Scanner( new File( "HW2_InputFile.txt" ) ); 
		n = sc.nextInt();
		x = new double [ n ];
		y = new double [ n ];
		for ( int i = 0; i < n; i++ ) {
			x [ i ] = sc.nextDouble();
			y [ i ] = sc.nextDouble();			
		}
		//System.out.println( "# of points:  " + n );
		//System.out.println( "x-values:  " + Arrays.toString( x ) );
		//System.out.println( "y-values:  " + Arrays.toString( y ) );

        // the following code calculates the least squares error eij for the seqment pi, ..., pj for all pairs i <= j
		double [] [] eij = new double [ n ] [ n ];
		for ( int i = 0; i < n; i++ ) {
			for ( int j = i; j < n; j++ ) {
			    if ( i == j) {
				    eij[i][j] = 0;
				}
				else {
				    double [] line;
		            line = leastSquaresError( i, j, x, y);
		            double a = line [ 0 ];
		            double b = line [ 1 ];
				    eij[i][j] = line [ 2 ];
				}
				//System.out.println( "i:  " + i + "\nj:  " + j + "\neij:  " + eij[i][j] );
			}
		}
		
		// c is tunable and allows us to penalize the use of additional lines to a greater or lesser extent
		// One segment costs c.  (See p. 263.)
		// m[n] is the minimum penalty as defined on p. 263.
		double c = 1;
		double m [] = new double [ n ];
		m [ 0 ] = c;
		for ( int j = 1; j < n; j++ ) {
		    m [ j ] = eij[0][j] + c;
			for ( int i = 1; i <= j; i++ ) {
				if ( eij[i][j] + c + m[i-1] < m[j] ) {
					m [ j ] = eij[i][j] + c + m[i-1];
					//System.out.println( "i:  " + i + "\nj:  " + j );
				}
			}
		}
		System.out.println( "m:  " + Arrays.toString( m ) );
		
		int j = n - 1;
		while ( j > 0) {
			int min_index = 0;
			double min = eij[0][j] + c;
			for ( int i = 1; i <= j; i++ ) {
			    if ( eij[i][j] + c + m[i-1] < min ) {
					min_index = i;
					min = eij[i][j] + c + m[i-1];
			    }
			}
			System.out.println( "A segment goes from " + min_index + " to " + j + "." );
			j = min_index - 1;
		}
		
	    double [] line = leastSquaresError( 0, n-1, x, y);
		double a = line [ 0 ];
		double b = line [ 1 ];
		System.out.println( "a:  " + a + ", b:  " + b );
		
		}
		catch( IOException e ) { System.err.println( e ); }
		
	}
	 	
	public static double [] leastSquaresError( int first, int last, double [] x, double [] y ) {

        int n = last - first + 1;
        double [] line = new double [ 3 ];
		double s1 = 0;
		double s2 = 0;
		double s3 = 0;
		double s4 = 0;
		for (int i = first; i <= last; i++ ) {
			s1 = s1 + x[i];
			s2 = s2 + y[i];
			s3 = s3 + x[i]*y[i];
			s4 = s4 + x[i]*x[i];
		}
		line [ 0 ] = (n*s3 - s1*s2)/(n*s4 - s1*s1);
		line [ 1 ] = (s2 - line[0]*s1)/n;
		for ( int i = first; i <= last; i++) {
			line [ 2 ] = line[2] + (y[i] - line[0]*x[i] - line[1])*(y[i] - line[0]*x[i] - line[1]);
		}
		return line;
	}

}

