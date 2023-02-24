package datastructure.mygraph;

//记录从U集合到V-U集合的最小权值边
public class CloseEdge implements Comparable<CloseEdge>{
    //最小边在U集合中的那个顶点
    String adjvex;
    //最小边上的权值
    int lowcost;

    public CloseEdge(String adjvex, int lowcost) {
        this.adjvex = adjvex;
        this.lowcost = lowcost;
    }

    @Override
    public int compareTo(CloseEdge o) {
        if (this.lowcost > o.lowcost)
            return 1;
        else if (this.lowcost == o.lowcost) {
            return 0;
        }
        else
            return -1;
    }
}
