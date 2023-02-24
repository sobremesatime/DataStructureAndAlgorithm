package datastructure.mygraph;

import java.util.*;

//邻接矩阵
public class AMGraph {
    public static final int MAX_INT = 32767;
    //点的名称v0, v1, v2.....
    List<String> vexs;
    //邻接矩阵
    int[][] arcs;
    //点的数量， 和边的数量
    int vexnum, arcnum;
    //是否访问过该点
    boolean[] visited;

    //图的初始化
    public AMGraph(int vexnum) {
        this.vexs = new ArrayList<>(vexnum);
        this.arcs = new int[vexnum][vexnum];
        this.visited = new boolean[vexnum];
        this.vexnum = vexnum;
        this.arcnum = 0;
    }


    //创建无向图
    public void creatAMGraph_undirected(AMGraph G) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        G = new AMGraph(n);
        //输入每个顶点的名称
        for (int i = 0; i < G.vexnum; i++) {
            String s = scanner.nextLine();
            G.vexs.add(s);
        }
        //初始化矩阵的边权值
        for (int i = 0; i < G.vexnum; i++) {
            for (int j = 0; j < G.vexnum; j++) {
                G.arcs[i][j] = MAX_INT;
            }
        }

        for (int i = 0; i < G.vexnum; i++) {
            String v1 = scanner.nextLine();
            String v2 = scanner.nextLine();
            int weight = scanner.nextInt();
            insertArc_undirected(locateVex(G, v1), locateVex(G, v2), weight);
        }
    }

    //图的显示
    public void showAMGraph() {
        for (int[] ints : arcs) {
            System.out.println(ints);
        }
    }

    //向图中插入新结点
    public void insertVertex(String vertex) {
        this.vexs.add(vertex);
        this.vexnum++;
    }

    //无向图的插入
    public void insertArc_undirected(int v1, int v2, int weight) {
        this.arcs[v1][v2] = weight;
        this.arcs[v2][v1] = weight;
        this.arcnum++;
    }

    //有向图插入边
    public void insertArc_directed(int v1, int v2, int weight) {
        this.arcs[v1][v2] = weight;
        this.arcnum++;
    }

    //顶点u的位置
    public int locateVex(AMGraph G, String u) {
        if (G.vexs.contains(u))
            return G.vexs.indexOf(u);
        else
            return -1;
    }

    //得到第一个邻接点的小标
    public int getFirstNeighbor(AMGraph G, int v) {
        for (int j = 0; j < G.vexnum; j++) {
            if (G.arcs[v][j] != MAX_INT)
                return j;
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(AMGraph G, int v, int w) {
        for (int i = w + 1; i < G.vexnum; i++) {
            if (G.arcs[v][i] != MAX_INT)
                return i;
        }
        return -1;
    }

    //DFS遍历
    public void dfs(AMGraph G, int v) {
        System.out.printf(G.vexs.get(v));
        G.visited[v] = true;
        for (int w = G.getFirstNeighbor(G, v); w >= 0; w = G.getNextNeighbor(G, v, w)) {
            if (!G.visited[w])
                dfs(G, w);
        }
    }

    //BFS遍历
    public void bfs(AMGraph G, int v) {
        System.out.printf(G.vexs.get(v));
        G.visited[v] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int w = G.getFirstNeighbor(G, v); w >= 0; w = G.getNextNeighbor(G, v, w)) {
                if (!visited[w]) {
                    System.out.printf(G.vexs.get(w));
                    G.visited[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    //拓扑排序_有向无环图
    public void ToPologicalSort(AMGraph G, int[] topo) {
        int[] indegree = new int[G.vexnum];
        G.findInDegree(G, indegree);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0)
                stack.push(i);
        }
        int count = 0;
        while (!stack.isEmpty()){
            int vertex = stack.pop();
            for (int w = G.getFirstNeighbor(G, vertex); w >= 0; w = G.getNextNeighbor(G, vertex, w)){
                indegree[w]--;
                if (indegree[w] == 0)
                    stack.push(w);;
            }
        }

    }

    //求出各顶点的入度
    public void findInDegree(AMGraph G, int[] indegree){
        for (int i = 0; i < G.vexnum; i++) {
            for (int j = 0; j < G.vexnum; j++) {
                if (G.arcs[i][j] != MAX_INT){
                    indegree[j]++;
                }
            }
        }
    }

    //最小生成树_普里姆算法
    public void miniSpanTree_Prim(AMGraph G, String u){
        //location为顶点u的下标
        int location = G.locateVex(G, u);
        CloseEdge[] closeEdges = new CloseEdge[G.vexnum];
        closeEdges[location] = new CloseEdge(null, 0);
        //初始化closeEdges数组
        for (int i = 0; i < G.vexnum; i++) {
            if (i != location){
                closeEdges[i] = new CloseEdge(u, G.arcs[location][i]);
            }
        }
        //选择其他n-1的顶点， 生产n-1条边
        for (int i = 1; i < G.vexnum; i++) {
            int k = minPrim(closeEdges);
            //V-U中权值最小边的父结点名称和自己名称
            String v0 = closeEdges[k].adjvex;
            String u0 = G.vexs.get(k);
            System.out.println(v0 + " " + u0);
            closeEdges[k].adjvex = null;
            closeEdges[k].lowcost = 0;
            //更新closeEdges集合
            for (int j = 0; j < G.vexnum; j++) {
                if (closeEdges[j].adjvex != null && G.arcs[k][j] < closeEdges[j].lowcost){
                    closeEdges[j].adjvex =G.vexs.get(k);
                    closeEdges[j].lowcost = G.arcs[k][j];
                }
            }
        }
    }

    //求出V-U集合中最小边的顶点
    public int minPrim(CloseEdge[] closeEdges){
        int k = 0;
        //找到根顶点
        while (closeEdges[k].adjvex == null)
            k++;
        //V-U集合中最小边的顶点
        for (int i = 0; i < closeEdges.length; i++) {
            if (closeEdges[i].adjvex != null && closeEdges[i].compareTo(closeEdges[k]) == -1){
                k = i;
            }
        }
        return k;
    }

    //最短路径，迪杰斯特拉算法
    public void shortestPath_DIJ(AMGraph G, int v0){
        int n =G.vexnum;
        //记录源点v0到终点vi是否已经被确定为最短路径
        boolean[] S = new boolean[n];
        //从源点v0到终点vi的当前最短路径上vi的直接前驱
        int[] Path = new int[n];
        //记录从源点v0到终点vi得当前最短路径得长度
        int[] D = new int[n];
        //初始化
        for (int i = 0; i < n; i++) {
            S[i] = false;
            D[i] = G.arcs[v0][i];
            if (G.arcs[v0][i] < G.MAX_INT){
                Path[i] = v0;
            }else {
                //表示v0->vi不可达
                Path[i] = -1;
            }
        }
        S[v0] = true;
        D[v0] = 0;

        for (int i = 0; i < n; i++) {
            //最小权值
            int min = G.MAX_INT;
            //当前点
            int v = v0;
            //v0->vi的最小边
            for (int w = 0; w < n; w++) {
                if (!S[w] && D[w] < G.MAX_INT){
                    v = w;
                    min = D[w];
                }
            }
            //更新S，Path，D集合状态
            S[v] = true;
            for (int w = 0; w < n; w++) {
                if (!S[w] && (D[v] + G.arcs[v][w] < D[w])){
                    D[w] = D[v] + G.arcs[v][w];
                    Path[w] = v;
                }
            }
        }
    }
}
