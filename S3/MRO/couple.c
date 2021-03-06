void DFS(int **res,int i,int n,int *visited)
{
	int j;
    for(j=0; j<n; j++)
    {
        if(res[i][j]>0 && visited[j]==0)
        {
        	visited[j]=1;
            DFS(res,j,n,visited);
        }
    }
}

void minimum_cut_algorithm(int n, int** flow, int** capa, int* cut)
{
	int **res = (int**)malloc(n*sizeof(int *));
	int i=0;
	int j=0;
	for(i=0;i<n;i++)
	{
		res[i]=(int*)malloc(n*sizeof(int));
	}
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
		{
		if(capa[i][j]-flow[i][j]>0)
			{
				 res[i][j] = capa[i][j]-flow[i][j];
			}
		if(flow[i][j]>0)
			{
				 res[j][i] = flow[i][j];
			}
		}
	cut[0]=0;
	cut[n-1]=1;

	int *visited = (int*)malloc(n*sizeof(int));
	for(i=0;i<n;i++)
	{
		visited[i]=0;
	}
	DFS(res,0,n,visited);

	for(i=1;i<n-1;i++)
	{
		if(visited[i]==1){
			cut[i]=0;
		}
		else
		{
			cut[i]=1;
		}
	}
		
}
