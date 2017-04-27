import java.io.*;
import java.util.*;
import java.math.*;
import java.awt.*;
import static java.lang.Math.*;
import static java.lang.Character.*;
import static java.lang.System.*;
import static java.math.BigInteger.*;
import static java.lang.Integer.*;


class edge implements Comparable<edge>
{
	int f, t, wt;
	public edge(int F, int T, int W)
	{
		f=F; t=T; wt=W;
	}
	public int compareTo( edge e )
	{
		return wt-e.wt;
	}
}
class Main
{
	final static int oo = (int)13e7;
	static int id[];
	
	static int parent(int p)
	{
		if( id[p] == p )
		return p;
		return id[p] = parent( id[p] );
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		int N = 40;
		int a[][] = new int[N+1][N+1];
		id = new int[N+1];
		Vector<edge> v = new Vector();
		long tot = 0;
		for(int i=0; i < N; ++i)
		{
			String t[] = in.readLine().split(",");
			for(int j=0; j< t.length; ++j)
				if( t[j].equals("-") ) a[i][j] = oo;
				else
				{
					 a[i][j] = Integer.parseInt( t[j] );
					 tot += a[i][j];
					 v.add( new edge(i,j,a[i][j] ) );
				}
		}
		Collections.sort( v );
		for(int i=0; i < N; ++i) id[i] = i;
		
		int mst = 0;
		for(int i=0; i < v.size(); ++i)
		{
			edge c = v.elementAt( i );
			int pcf = parent(id[c.f]) ;
			int pct = parent(id[c.t]) ;
			if( pcf == pct ) continue;
			mst += c.wt;
			id[pcf] = id[pct] ;
		}
		System.out.println( tot/2 - mst );
	}
	
}


