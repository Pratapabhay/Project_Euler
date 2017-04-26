class unionRank
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