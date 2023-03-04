package datastructure.mygraph;

import org.junit.Test;

import java.util.Arrays;

public class AMGraphTest {
    public static void main(String[] args) {
        int sum = 5;
        String[] vexs = {"A", "B", "C", "D", "E"};
        AMGraph graph = new AMGraph(sum);
        for (String str: vexs) {
            graph.insertVertex(str);
        }
        graph.insertArc_undirected(0, 1, 1);
        graph.insertArc_undirected(0, 2, 2);
        graph.insertArc_undirected(0, 3, 3);
        graph.insertArc_undirected(1, 2, 1);
        graph.insertArc_undirected(1, 4, 4);

        //显示邻接矩阵
        System.out.println("邻接矩阵");
        graph.showAMGraph();
        //深度优先遍历
        System.out.println("进行深度优先遍历");
        graph.dfs(graph, 0);
        //广度优先遍历
        for (int i = 0; i < sum; i++) {
            graph.visited[i] = false;
        }
        System.out.println("\n进行广度优先遍历");
        graph.bfs(graph, 0);

    }

    @Test
    public void topoTest(){
        int sum = 5;
        String[] vexs = {"A", "B", "C", "D", "E"};
        AMGraph graph = new AMGraph(sum);
        for (String str: vexs) {
            graph.insertVertex(str);
        }

        graph.insertArc_directed(0, 1, 1);
        graph.insertArc_directed(0, 2, 1);
        graph.insertArc_directed(0, 3, 1);
        graph.insertArc_directed(1, 2, 1);
        graph.insertArc_directed(1, 4, 1);

        //拓扑排序
        System.out.println("拓扑排序");
        int[] result = new int[graph.vexnum];
        graph.ToPologicalSort(graph, result);
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void primTest(){
        int sum = 5;
        String[] vexs = {"A", "B", "C", "D", "E"};
        AMGraph graph = new AMGraph(sum);
        for (String str: vexs) {
            graph.insertVertex(str);
        }
        for (int i = 0; i < sum; i++) {
            for (int j = 0; j < sum; j++) {
                graph.arcs[i][j] = 10000;
            }
        }

        graph.insertArc_undirected(0, 1, 1);
        graph.insertArc_undirected(0, 2, 2);
        graph.insertArc_undirected(0, 3, 3);
        graph.insertArc_undirected(1, 2, 1);
        graph.insertArc_undirected(1, 4, 4);
        //最小生成树
        //初始顶点
        String v0 = "B";
        graph.miniSpanTree_Prim(graph, v0);
    }
}
