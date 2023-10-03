#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// Função para encontrar a primeira ocorrência do valor de destino no vetor de origem
int getFirstInstance(int target, vector<int>& origin) {
    for (int i = 0; i < origin.size(); i++) {
        if (origin[i] == target) {
            return i + 1; // Adicionar 1 para corresponder à indexação base 1
        }
    }
    return 0; // Retorna 0 se não for encontrado
}

// Função para preencher o vetor de ponteiros
void fillPointer(vector<int>& pointer, vector<int>& origin) {
    pointer[0] = 1;

    for (int i = 1; i <= pointer.size(); i++) {
        if (i < pointer.size()) {
            pointer[i] = getFirstInstance(i, origin);
        } else {
            pointer[i - 1] = origin.size() + 1;
        }
    }
}

// Função para encontrar o vértice com o maior grau e seus sucessores
void getVertex(vector<int>& pointer, vector<int>& origin, vector<int>& destination) {
    int highestDegree = 0;
    int vertex = 0;

    for (int i = 0; i < pointer.size() - 1; i++) {
        int curr = pointer[i];
        int next = pointer[i + 1];
        int diff = next - curr;

        if (diff > highestDegree) {
            highestDegree = diff;
            vertex = i + 1; // Adicionar 1 para corresponder à indexação base 1
        }
    }

    vector<int> successors(highestDegree);
    int pos = pointer[vertex - 1];

    for (int i = 0; i < highestDegree; i++) {
        successors[i] = destination[pos++];
    }

    cout << "------Forward-Star------" << endl;
    cout << "Vertice: " << vertex << endl;
    cout << "Grau de saida: " << highestDegree << endl;
    for (int s : successors) {
        cout << s << " ";
    }
    cout << endl;
    cout << "------------------------" << endl;
}

// Função para classificar o vetor de destino usando o vetor de origem
void sortVectors(vector<int>& origin, vector<int>& destination) {
    int n = destination.size();

    for (int i = 0; i < n; i++) {
        int min_idx = i;

        for (int j = i + 1; j < n; j++) {
            if (destination[j] < destination[min_idx]) {
                min_idx = j;
            }
        }

        swap(destination[i], destination[min_idx]);
        swap(origin[i], origin[min_idx]);
    }
}

// Função para o Reverse-Star
void reverseStar(vector<int>& origin, vector<int>& destination) {
    sortVectors(origin, destination);

    vector<int> rev_pointer(destination.size() + 1);
    fillPointer(rev_pointer, destination);

    int highestDegree = 0;
    int vertex = 0;

    for (int i = 0; i < rev_pointer.size() - 1; i++) {
        int curr = rev_pointer[i];
        int next = rev_pointer[i + 1];
        int diff = next - curr;

        if (diff > highestDegree) {
            highestDegree = diff;
            vertex = i + 1; // Adicionar 1 para corresponder à indexação base 1
        }
    }

    vector<int> predecessors(highestDegree);
    int pos = rev_pointer[vertex - 1];

    for (int i = 0; i < highestDegree; i++) {
        predecessors[i] = origin[pos++];
    }

    cout << "------Reverse-Star------" << endl;
    cout << "Vertice: " << vertex << endl;
    cout << "Grau de entrada: " << highestDegree << endl;
    for (int s : predecessors) {
        cout << s << " ";
    }
    cout << endl;
}

int main() {
    int vertexAmt, arcsAmt;
    cin >> vertexAmt >> arcsAmt;

    vector<int> origin(arcsAmt);
    vector<int> destination(arcsAmt);

    for (int i = 0; i < arcsAmt; i++) {
        cin >> origin[i] >> destination[i];
    }

    // Forward-Star
    vector<int> forward_pointer(vertexAmt + 1);
    fillPointer(forward_pointer, origin);
    getVertex(forward_pointer, origin, destination);

    // Reverse-Star
    reverseStar(origin, destination);

    return 0;
}