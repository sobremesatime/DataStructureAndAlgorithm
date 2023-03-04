import datastructure.mygraph.AMGraph;
import datastructure.mygraph.CloseEdge;

import java.util.*;

/**
 * @Title: GraphTest
 * @Description: 测试图
 * @author: LYL
 * @date: 2023/2/25 11:37
 */
public class GraphTest {

    //创建无向图
    public void creatGraph_undirected(AMGraph G){
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        G = new AMGraph(n);
        for (int i = 0; i < G.vexnum; i++){
            String s = scanner.nextLine();
            G.vexs.add(s);
        }
        for (int i = 0; i < G.vexnum; i++){
            for (int j = 0; j < n; j++){
                G.arcs[i][j] = G.MAX_INT;
            }
        }
        for (int i = 0; i < G.arcnum; i++) {
            String v1 = scanner.nextLine();
            String v2 = scanner.nextLine();
            int weight = scanner.nextInt();
            G.insertArc_undirected(G.locateVex(G, v1), G.locateVex(G, v2), weight);
        }
    }

    //DFS
    public void dfs(AMGraph G, int v){
        System.out.println(G.vexs.get(v));
        G.visited[v] = true;
        for (int w = G.getFirstNeighbor(G, v); w >= 0; w = G.getNextNeighbor(G, v, w)){
            if (!G.visited[w]){
                dfs(G, w);
            }
        }
    }

    //BFS
    public void bfs(AMGraph G, int v){
        System.out.println(G.vexs.get(v));
        G.visited[v] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        while (!queue.isEmpty()){
            int u = queue.poll();
            for (int w = G.getFirstNeighbor(G, u); w >= 0; w = G.getNextNeighbor(G, u, w)){
                if (!G.visited[w]){
                    System.out.println(G.vexs.get(w));
                    G.visited[w] = true;
                    queue.add(w);
                }
            }
        }
    }

    //最小生成树_普里姆算法
    public void miniSpanTree_Prim(AMGraph G, String u){
        int location = G.locateVex(G, u);
        CloseEdge[] closeEdges = new CloseEdge[G.vexnum];
        closeEdges[location] = new CloseEdge(null, 0);
        for (int i = 0; i < closeEdges.length; i++){
            if (i != location){
                closeEdges[i] = new CloseEdge(u, G.arcs[location][i]);
            }
        }
        for (int i = 0; i < G.vexnum; i++){
            int k = minPrim(closeEdges);
            //V-U中权值最小边的父结点名称和自己名称
            String v0 = closeEdges[k].adjvex;
            String u0 = G.vexs.get(k);
            System.out.println(v0 + " " + u0);
            closeEdges[k].adjvex = null;
            closeEdges[k].lowcost = 0;
            //更新
            for (int j = 0; j < G.vexnum; j++){
                if (closeEdges[j].adjvex != null && G.arcs[k][j] < closeEdges[j].lowcost){
                    closeEdges[j].adjvex = G.vexs.get(k);
                    closeEdges[j].lowcost = G.arcs[k][j];
                }
            }
        }


    }

    public int minPrim(CloseEdge[] closeEdges){
        int k = 0;
        while (closeEdges[k].adjvex == null)
            k++;
        for (int i = 0; i < closeEdges.length; i++) {
            if (closeEdges[i].adjvex != null && closeEdges[i].compareTo(closeEdges[k]) == -1)
                k = i;
        }
        return k;
    }

    public void shortestPath_DIJ(AMGraph G, int v0){
        int n = G.vexnum;
        boolean[] S = new boolean[n];
        int[] Path = new int[n];
        int[] shortestLen = new int[n];
        //初始化
        for (int i = 0; i < n; i++) {
            S[i] = false;
            shortestLen[i] = G.arcs[v0][i];
            if (G.arcs[v0][i] < G.MAX_INT){
                Path[i] = v0;
            }else {
                Path[i] = -1;
            }
        }
        S[v0] = true;
        shortestLen[v0] = 0;

        for (int i = 0; i < n; i++) {
            int min = G.MAX_INT;
            int v = v0;
            for (int w = 0; w < n; w++){
                if (!S[w] && shortestLen[w] < min){
                    v = w;
                    min = shortestLen[w];
                }
            }
            S[v] = true;
            for (int w = 0; w < n; w++){
                if (!S[w] && (shortestLen[v] + G.arcs[v][w]) < shortestLen[w]){
                    shortestLen[w] = shortestLen[v] + G.arcs[v][w];
                    Path[w] = v;
                }
            }
        }
    }

    public void ToPologicalSort(AMGraph G, int[] topo){
        int[] indegree = new int[G.vexnum];
        findInDegree(G, indegree);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < G.vexnum; i++){
            if (indegree[i] == 0)
                stack.push(i);
        }
        int  count = 0;
        while (!stack.isEmpty()){
            int vertex = stack.pop();
            topo[count++] = vertex;
            for (int w = G.getFirstNeighbor(G, vertex); w >= 0; w = G.getNextNeighbor(G, vertex, w)){
                indegree[w]--;
                if (indegree[w] == 0)
                    stack.push(w);
            }
        }
    }
    public void findInDegree(AMGraph G, int[] indegree){
        for (int i = 0; i < G.vexnum; i++) {
            for (int j = 0; j < G.vexnum; j++) {
                if (G.arcs[i][j] != G.MAX_INT){
                    indegree[j]++;
                }
            }
        }
    }
}
