package lesson5;


import kotlin.NotImplementedError;
import lesson5.impl.GraphBuilder;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     * <p>
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     * <p>
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        List<Graph.Edge> ans = new ArrayList<>();
        Set<Graph.Vertex> ver = graph.getVertices();
        List<Graph.Vertex> EulerCicle = new ArrayList<>();
        Iterator it = ver.iterator();
        Set<Graph.Edge> edges = graph.getEdges();
        if (!isEulerian(graph))
            return ans;
        Stack<Graph.Vertex> st = new Stack<>();
        Graph.Vertex first = (Graph.Vertex) it.next();
        st.push(first);
        it = edges.iterator();
        while (it.hasNext()) {
            ans.add((Graph.Edge) it.next());
        }
        while (!st.empty()) {
            int n = 0;
            Graph.Vertex v = st.peek();
            for (int i = 0; i <= ans.size() - 1; i++) {
                if (ans.get(i).getBegin() == v || ans.get(i).getEnd() == v)
                    n++;
            }
            if (n == 0) {
                EulerCicle.add(st.pop());
            } else {
                for (int i = 0; i <= ans.size() - 1; i++) {
                    if (ans.get(i).getBegin() == v) {
                        st.push(ans.get(i).getEnd());
                        ans.remove(i);
                        break;
                    }
                    if (ans.get(i).getEnd() == v) {
                        st.push(ans.get(i).getBegin());
                        ans.remove(i);
                        break;
                    }
                }
            }
        }
        ans.clear();
        Iterator it_2;
        for (int i = 0; i <= EulerCicle.size() - 2; i++) {
            it_2 = edges.iterator();
            while (it_2.hasNext()) {
                Graph.Edge ed = (Graph.Edge) it_2.next();
                if ((ed.getBegin().equals(EulerCicle.get(i)) && ed.getEnd().equals(EulerCicle.get(i + 1)))
                        || (ed.getEnd().equals(EulerCicle.get(i)) && ed.getBegin().equals(EulerCicle.get(i + 1))))
                    ans.add(ed);
            }
        }
        return ans;
    }

    private static boolean isEulerian(Graph graph) {
        Set<Graph.Edge> edges = graph.getEdges();
        Map<Graph.Vertex, Integer> verticesCount = new HashMap<>();
        Set<Graph.Vertex> keys = new HashSet<>();
        for (Graph.Edge e : edges) {
            if (!verticesCount.containsKey(e.getBegin())) {
                verticesCount.put(e.getBegin(), 1);
                keys.add(e.getBegin());
            } else verticesCount.put(e.getBegin(), verticesCount.get(e.getBegin()) + 1);
            if (!verticesCount.containsKey(e.getEnd())) {
                verticesCount.put(e.getEnd(), 1);
                keys.add(e.getEnd());
            } else verticesCount.put(e.getEnd(), verticesCount.get(e.getEnd()) + 1);
        }
        for (Graph.Vertex key : keys) {
            if (verticesCount.get(key) % 2 != 0)
                return false;
        }
        return true;
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     * <p>
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     * <p>
     * Пример:
     * <p>
     * G -- H
     * |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     * <p>
     * Ответ:
     * <p>
     * G    H
     * |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        GraphBuilder ansBuilder = new GraphBuilder();
        Set e = graph.getEdges();
        Set v = graph.getVertices();
        List<Graph.Edge> edges = new ArrayList<>();
        for (Object anE : e) {
            Graph.Edge cur = (Graph.Edge) anE;
            edges.add(cur);
        }
        List<Graph.Vertex> vertices = new ArrayList<>();
        for (Object aV : v) {
            Graph.Vertex cur = (Graph.Vertex) aV;
            vertices.add(cur);
        }
        for (int i = 0; i <= vertices.size() - 1; i++) {
            ansBuilder.addVertex(vertices.get(i).getName());
        }
        Set<Graph.Vertex> used = new HashSet<>();
        used.add(vertices.get(0));
        Stack<Graph.Vertex> stack = new Stack<>();
        stack.push(vertices.get(0));
        while (!stack.isEmpty()) {
            Graph.Vertex upper = stack.peek();
            int c = 0;
            for (int i = 0; i <= edges.size() - 1; i++) {
                if (edges.get(i).getBegin() == upper && !used.contains(edges.get(i).getEnd())) {
                    ansBuilder.addConnection(edges.get(i).getBegin(), edges.get(i).getEnd(), 1);
                    used.add(edges.get(i).getEnd());
                    c++;
                    stack.push(edges.get(i).getEnd());
                    edges.remove(i);
                    break;
                }
                if (edges.get(i).getEnd() == upper && !used.contains(edges.get(i).getBegin())) {
                    ansBuilder.addConnection(edges.get(i).getBegin(), edges.get(i).getEnd(), 1);
                    used.add(edges.get(i).getBegin());
                    c++;
                    stack.push(edges.get(i).getBegin());
                    edges.remove(i);
                    break;
                }
            }
            if (c == 0) {
                stack.pop();
            }
        }
        Graph ans = ansBuilder.build();
        return ans;
    }



        /**
         * Максимальное независимое множество вершин в графе без циклов.
         * Сложная
         *
         * Дан граф без циклов (получатель), например
         *
         *      G -- H -- J
         *      |
         * A -- B -- D
         * |         |
         * C -- F    I
         * |
         * E
         *
         * Найти в нём самое большое независимое множество вершин и вернуть его.
         * Никакая пара вершин в независимом множестве не должна быть связана ребром.
         *
         * Если самых больших множеств несколько, приоритет имеет то из них,
         * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
         *
         * В данном случае ответ (A, E, F, D, G, J)
         *
         * Эта задача может быть зачтена за пятый и шестой урок одновременно
         */
        public static Set<Graph.Vertex> largestIndependentVertexSet (Graph graph) {
            throw new NotImplementedError();
        }

        /**
         * Наидлиннейший простой путь.
         * Сложная
         *
         * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
         * Простым считается путь, вершины в котором не повторяются.
         * Если таких путей несколько, вернуть любой из них.
         *
         * Пример:
         *
         *      G -- H
         *      |    |
         * A -- B -- C -- D
         * |    |    |    |
         * E    F -- I    |
         * |              |
         * J ------------ K
         *
         * Ответ: A, E, J, K, D, C, H, G, B, F, I
         */

        public static Path longestSimplePath (Graph graph) {
            throw new NotImplementedError();
        }
    }
