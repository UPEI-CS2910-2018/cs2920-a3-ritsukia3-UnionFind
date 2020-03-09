public class QuickUnionFindUF {

    private int id[];

    public QuickUnionFindUF(int n)
    {
        id = new int[n];
        for (int i = 0; i < n; i++)
        {
            id[i] = i;
        }
    }

    /**
     * Chase parent pointers until reaching root
     *
     * @param i an index of id[]
     * @return the parent index of a given index i
     */
    public int find(int i)
    {
        while(i != id[i])
        {
            i = id[i];
        }

        return i;
    }

    /**
     * Change root
     *
     * @param p
     * @param q
     */
    public void union(int p, int q)
    {
        int i = find(p);
        int j = find(q);
        if (i==j) return;
        if (id[i] < id[j])
    }

}
