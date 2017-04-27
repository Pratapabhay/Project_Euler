/* Project Euler: Minimum Spanning Tree

Problem Statement : Find out maximum saving that can be achieved in a given graph with weighted edges.

	Author : Abhay Singh
	Designation : Student, IIT Mandi
	Time Complexity : O(n2)
	Space Complexity : O(n2)


*/

// Importing required libraries
package minimalNetworks
import java.io.*;
import java.util.*;
import java.math.*;

class MST
{
	private static String inputFile = "input.txt";
	private static int verticesNumber = 40;
	static long total = 0;

	static class Edge
	{
		int start,end;
		long weight;
		public Edge(int start, int end,long weight)
		{
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}



	// Union Rank Data-Structure Class 
	static class UnionRank
	{
		int[] setArray;

	// Create n sets
		public UnionRank(int n)
		{
			setArray = new int[n];

		// Initial value of -1 represents that the set has no parent or it is the root.
			for (int i = 0; i < n ; i++) {
				setArray[i] = -1;
			}
		}

		int find(int n)
		{
			if(this.setArray[n] == -1)
				return n;
			else return(find(this.setArray[n]));

		}


		// This union function searches for the parent of vertices in consideration and if set parent of one node to another, if they do not have same parent.
		void union(int set1, int set2)
		{
			int parent1 = this.find(set1);
			int parent2 = this.find(set2);

			if(parent1 != parent2)
			{
				this.setArray[parent1] = parent2;
			}

		}
	}

	// Take input from the given file and return a list of edges
	public LinkedList input(String fileName)
{	
		Scanner scanner = null;
		try{
			File file = new File(fileName);
			scanner = new Scanner(file);
}
	catch(Exception e)
{
	System.err.println("Please check that the input file exixts in the current directory");
	System.exit(-1);
}
		String temp;
		String[] vertices;
		LinkedList edges = new LinkedList<Edge>(); 
		for(int i = 0 ; i < verticesNumber ; i++)
		{
			temp = scanner.nextLine();
			vertices = temp.split(",");	

			for(int j = 0 ; j < verticesNumber ; j++)
			{
				if(!vertices[j].equals("-"))
				{
					Edge current = new Edge(i,j,Long.parseLong(vertices[j])); 
					edges.add(current);
					

					total = total + current.weight;

				}

			}
		}
		return edges;
	}

	public static void main(String[] args)throws Exception {
		MST object = new MST();
		if(args.length != 1)
		{
			System.err.println("Run the program via following command : \njava MST <input_file>");
			System.exit(1);
		}
		String fileName = args[0];
		LinkedList edges = object.input(fileName);
		
		
		

		// Sort the input edges according to their weights

		Collections.sort(edges, new Comparator<Edge>()
		{
			public int compare(Edge e1,Edge e2)
			{
				if(e1.weight >= e2.weight)
					return 1;
				else 
					return -1;
			}
				});



		// addedEdges will contain all the edges that are included in the Minimum Spanning Tree
		LinkedList addedEdges = new LinkedList<Edge>();
		long sum = 0;
		UnionRank obj = new UnionRank(verticesNumber);
		Iterator it = edges.iterator();
		int addedCount = 0;
		
		while(it.hasNext() && addedCount <= (verticesNumber-1))
		{
			Edge current = (Edge)it.next();
			int parent1 = obj.find(current.start);
			int parent2 = obj.find(current.end);
			
			// cycle detection, if parent1 == parent2, then there is a cycle
			if(parent1 != parent2){
				addedEdges.add(current);
				addedCount++;
				obj.union(parent1,parent2);
				sum = sum+current.weight;
			}
		}

		System.out.println("Maximum saving achieved:"+(total/2-sum));
	}
}
