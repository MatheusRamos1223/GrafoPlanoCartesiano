## Cartesiano (Plano Cartesiano em Java)

Projeto de estudo em Programação Orientada a Objetos (POO) usando Java.
Permite criar pontos nomeados no plano cartesiano, manipulá-los via menu no console e calcular rotas entre pontos utilizando um grafo com algoritmo de menor caminho.

Funcionalidades
Operações básicas

Criar coordenadas nomeadas

Trocar coordenada atual

Listar coordenadas cadastradas

Deslocar coordenadas

Escalar coordenadas

Calcular distância entre dois pontos

Origem fixa e imutável (0,0)

Remoção de coordenadas (exceto a origem)

Sistema de rotas

Conexão automática entre pontos formando um grafo

Reconstrução automática do grafo ao adicionar ou remover pontos

Cálculo da menor rota entre dois pontos

Retorno da sequência completa de pontos que formam o caminho

Estrutura do sistema

O sistema organiza os pontos do plano em uma estrutura de grafo, onde:

Vértices representam os pontos do plano cartesiano

Arestas representam conexões entre dois pontos

Pesos das arestas são calculados pela distância euclidiana entre os pontos

As conexões entre pontos são geradas automaticamente pelo sistema, sem necessidade de inserção manual de arestas.

Lógica de geração das conexões

Para criar as conexões do grafo, o sistema utiliza uma lógica baseada em proximidade entre pontos.

Média das distâncias das arestas

O sistema calcula uma média dos pesos das arestas existentes para definir um raio inicial de busca.

Quando existem poucas arestas, é utilizada a média simples

Quando existem muitas arestas, o cálculo ignora o menor e o maior valor para reduzir o efeito de outliers

Isso mantém o comportamento do grafo mais estável e evita conexões muito distantes.

Busca pelo ponto mais próximo

Para cada ponto do grafo, o sistema procura o vizinho mais próximo dentro do raio calculado.

Caso nenhum ponto seja encontrado dentro desse raio, o sistema expande progressivamente o raio de busca até encontrar um ponto possível para conexão.

Reconstrução do grafo

Sempre que um ponto é adicionado ou removido:

todas as arestas são recalculadas

o grafo é reconstruído automaticamente

novas conexões são estabelecidas

Isso garante consistência na estrutura das rotas.

Sistema de rotas (Algoritmo de Dijkstra)

O projeto implementa o algoritmo de Dijkstra para calcular a menor rota entre dois pontos do grafo.

A implementação utiliza estruturas da biblioteca padrão do Java:

HashMap para armazenar as menores distâncias conhecidas

HashMap para registrar o ponto anterior no caminho

HashSet para controlar os pontos já visitados

PriorityQueue para sempre processar o ponto com menor custo acumulado

Funcionamento geral

Todas as distâncias são inicializadas como infinito

A origem recebe distância 0

Uma fila de prioridade seleciona sempre o ponto com menor distância conhecida

Para cada vizinho do ponto atual, é calculada uma nova distância acumulada

Se a nova distância for menor que a registrada anteriormente, o caminho é atualizado

O algoritmo termina quando o destino é removido da fila de prioridade

Após a execução, o caminho é reconstruído utilizando o mapa de anteriores, percorrendo os pontos do destino até a origem.

Conceitos de Programação Orientada a Objetos aplicados

Este projeto foi desenvolvido com foco em praticar conceitos fundamentais de POO em Java.

Herança

PontoNomeado herda de Ponto

PontoOrigem herda de PontoNomeado

Polimorfismo

A classe PontoOrigem sobrescreve métodos para impedir alterações na origem do plano cartesiano.

Sobrecarga de métodos

distance()

distance(Ponto pt)

distance(double x, double y)

Encapsulamento

A manipulação dos pontos e das conexões do grafo é controlada pela classe responsável pelo gerenciamento do sistema.

Uso de estruturas de dados

O projeto utiliza diversas estruturas da biblioteca padrão do Java:

ArrayList

HashMap

HashSet

PriorityQueue

Iterator

Como executar

Compile e execute a classe PlanoCartesiano

Utilize o menu no console para criar e manipular coordenadas

Utilize o sistema de rotas para calcular o menor caminho entre dois pontos

Autor

Ramos
