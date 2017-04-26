import java.io.*;
import java.util.*;

class MST
{
	private static String inputFile = "p107_network.txt";
	private static int verticesNumber = 40;
	class Edge
	{
		int start,end,weight;
		public Edge(int start, int end,int weight)
		{
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}

	static class unionRank
	{
		int[] setArray;

	// create n sets
		public unionRank(int n)
		{
			setArray = new int[n];

		// initial value of -1 represents that the set has no parent or it is the root
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

	public LinkedList input()
	{
		Scanner scanner = new Scanner(System.in);
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
					Edge current = new Edge(i,j,Integer.parseInt(vertices[j])); 
					edges.add(current);
				}

			}
		}
		return edges;
	}

	public static void main(String[] args) {
		MST object = new MST();
		LinkedList edges = object.input();
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
		LinkedList addedEdges = new LinkedList<Edge>();
		int sum = 0,total = 0;
		unionRank obj = new unionRank(verticesNumber);
		Iterator it = edges.iterator();
		int addedCount = 0;
		while(it.hasNext() && addedCount <= (verticesNumber-1))
		{
			Edge current = (Edge)it.next();
			System.out.println(current.weight);
			int parent1 = obj.find(current.start);
			int parent2 = obj.find(current.end);
			total+=current.weight;
			if(parent1 != parent2){
				addedEdges.add(current);
				addedCount++;
				obj.union(parent1,parent2);
				sum+=current.weight;
			}
		}

		it = addedEdges.iterator();
		while(it.hasNext())
		{
			Edge current = (Edge)it.next();
			System.out.println(current.weight);
		}
		System.out.println("weight:"+(total-sum));
	}
}