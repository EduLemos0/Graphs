//  @author - Eduardo Lemos
//  @lemosep on GitHub
#include <iostream>
#include <fstream>
#include <string>
#include <cstdint>
#include <vector>
using namespace std;

class Graph {
public:
    uint32_t size{};
    vector<vector<uint32_t>> adj;

    Graph() = default;

    explicit Graph(uint32_t s) {
        size = s;
        adj.resize(s);
    }
};

class Depth_First_Search {
public:
    static Graph graph;
    static int32_t time;
    static vector<uint32_t> discovery_time;
    static vector<uint32_t> end_time;
    static vector<uint32_t> parent;

    Depth_First_Search(Graph& g, uint32_t root)
    {
        graph = g;
        discovery_time.resize(graph.size);
        end_time.resize(graph.size);
        parent.resize(graph.size);
        init(root);
    };

private:
    static auto init (uint32_t root) -> void
    {
        for (uint32_t i = 1; i < graph.size; ++i) {
            discovery_time.at(i) = 0;
            end_time.at(i) = 0;
            parent.at(i) = 0;
        };

        for (const auto& v : graph.adj.at(root)) {
            if (discovery_time.at(v) == 0) { DepthFirstSearch(v); };
        }

    }

    static auto DepthFirstSearch(uint32_t v) -> void {
        time++;
        discovery_time.at(v) = time;

        for (const auto& w : graph.adj.at(v)) {
            if (discovery_time.at(w) == 0) {
                //write source & destination edge in string separated by '\n'
            }
        }
    }


};

auto main() -> int
{
    bool debug = true;
    // **WARNING** -> Make sure you pass in the correct @file_path.
    string file_path;
    cout << "Please type your file name\n";
    cin >> file_path;

    uint32_t root = 0;
    cout << "Select a root node\n";
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

    Depth_First_Search dfs(graph, root);
}

//C:\Users\user\CLionProjects\untitled\graph.txt

