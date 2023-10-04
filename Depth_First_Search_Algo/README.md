# Busca em Profundidade - Depth-First Search

## Algoritmo

Por motivos de eficiência e buscando evitar o estouro de pilha (_stack-overflow_), converti o algoritmo para a execução iterativa, utilizando a classe [`<stack>`](https://en.cppreference.com/w/cpp/container/stack) como uma _priority queue_ para a execução dos vértices de forma correta, evitando repetições que poderiam quebrar o código.

## Execução

O código foi feito em C++(_versão 11+_) e é necessário se atentar com o _path_ que é passado para a variável `file_path`, de preferência, passando o **caminho absoluto do arquivo**. Além disso, o usuário deverá passar qual vértice do grafo ele deseja observar.
