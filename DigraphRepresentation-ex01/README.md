# Representação de Grafo

**Eduardo Lemos Paschoalini**\
**[Github](https://github.com/EduLemos0)**

## Rode o arquivo em JAVA

## Técnica Utilizada

Diante as técnicas apresentadas durante as aulas, escolhi construir a lista de adjacência por vetores, dado que a implementação de estruturas de dados adicionais e.g. linked lists - nas quais a seriam utilizadas na representação de lista de adjacência tradicional - não são necessárias, pois toda a estruturação é feita via vetores.

Sobre as matrizes, tanto de incidência quanto de adjacência, foram opções que desde o princípio eu desconsiderei, tendo em vista a proposta deste exercício, isto é, trabalhar com grafos densos. As matrizes possuem um tempo de consulta constante, não obstante, o espaço que estas matrizes ocupam não é algo que me agrada.

## Código

O programa executa os algoritmos de foward-star e reverse-star. Como o vetor de vértices já vem ordenado pelo input, o forward-star foi mais simples de fazer, tendo apenas que preencher o vetor `pointer` e buscar o vetor com maior grau de saída.

Já para o reverse-star, tive que ordenar o vetor de vértices-destino, consequentemente reorganizando o de origem. Para isso, utilizei o algoritimo de [selection sort](https://www.geeksforgeeks.org/selection-sort/).

A explicação geral de algumas funções estão descritas no decorrer do [código](./Digraph.java).
