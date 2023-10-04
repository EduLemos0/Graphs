#include <iostream>
#include <fstream>
#include <string>
#include <cstdint>
#include <vector>
#include <stack>
#include <algorithm>
using namespace std;

class Graph {

public:
    uint32_t size;
    vector<vector<uint32_t>> adj;

    explicit Graph(uint32_t s) : size(s), adj(s) {}
};

class Depth_First_Search {
public:
    Graph graph;
    uint32_t selected_vertex;
    vector<uint32_t> discovery_time;
    vector<uint32_t> end_time;
    vector<uint32_t> parent;
    vector<string> tree_edges;
    vector<string> selected_edges;
    vector<string> classification;

    Depth_First_Search(Graph& g, uint32_t r) : graph(g), selected_vertex(r) {
        discovery_time.resize(graph.size);
        end_time.resize(graph.size);
        parent.resize(graph.size);
        Build_DFS();
        Write_Data();
    }

private:
    auto Build_DFS() -> void {
        uint32_t time = 0;
        stack<uint32_t> vertex_stack;
        // Always push 1, since root is 1.
        vertex_stack.push(1);

        while (!vertex_stack.empty()) {
            uint32_t v = vertex_stack.top();
            // Stops redundant data
            if (discovery_time[v] == 0) {
                time += 1;
                discovery_time[v] = time;
            }

            for (const auto& w : graph.adj[v]) {
                string current_edge = "(" + to_string(v) + "-" + to_string(w) + ")";

                if (discovery_time[w] == 0) {
                    tree_edges.push_back(current_edge);
                    parent[w] = v;

                    if (v == selected_vertex) {
                        selected_edges.push_back(current_edge);
                        classification.emplace_back("arvore");
                    }

                    vertex_stack.push(w);
                    break;
                }
                else if (v == selected_vertex && find(selected_edges.begin(), selected_edges.end(), current_edge) == selected_edges.end()) {
                    {
                        selected_edges.push_back(current_edge);
                        classification.emplace_back( end_time[w] == 0 ? "retorno" : discovery_time[v] < discovery_time[w] ? "avanco"
                                                                                                     : "cruzamento");
                    }
                }

                if (w == graph.adj[v][graph.adj[v].size() - 1]) {
                    time += 1;
                    end_time[v] = time;
                    vertex_stack.pop();
                }
            }
        }
    }
    
    auto Write_Data() -> void
    {
        cout << "----------------------------------\nARESTAS ARVORE\n";
        for (const auto & tree_edge : tree_edges) {
            cout << tree_edge << " ";
        }

        cout << "\n----------------------------------\nARESTAS DIVERGENTES DE " + to_string(selected_vertex) + "\n";
        for (int i = 0; i < selected_edges.size(); ++i) {
            cout << selected_edges[i] << "->" << classification[i] << '\n';
        }
    }
};

auto main() -> int {
    bool debug = true;
    // **WARNING** -> Make sure you pass in the correct @file_path.
    string file_path;
    cout << "Please type your file name\n";
    cin >> file_path;

    uint32_t root = 0;
    cout << "Select vertex\n";
    cin >> root;

    ifstream file (file_path);

    if (!file.is_open()) {
        cerr << "@file input expected to be open";
    };

    uint32_t vertice_amt = 0;
    uint32_t edge_amt = 0;
    file >> vertice_amt >> edge_amt;

    if (debug) { cout << vertice_amt << "\t" << edge_amt << "\n";};

    Graph graph((vertice_amt + 1));

    uint32_t e_src = 0;
    uint32_t e_dest = 0;
    while (file >> e_src >> e_dest) {
        graph.adj.at(e_src).push_back(e_dest);
    }

    // Sorting vertices
    for (uint32_t i = 0; i < graph.size; ++i) {
        sort(graph.adj.at(i).begin(), graph.adj.at(i).end());
    }

    Depth_First_Search dfs(graph, root);

    return 0;
}