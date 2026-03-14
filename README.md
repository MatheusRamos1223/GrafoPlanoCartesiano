# Cartesiano (Plano Cartesiano em Java)

Projeto de estudo em Programação Orientada a Objetos (POO) usando Java.  
Permite criar pontos nomeados no plano cartesiano, operar sobre eles via menu no console e calcular rotas entre pontos utilizando um grafo com o algoritmo de Dijkstra.

## Funcionalidades

### Operações básicas
- Criar coordenadas nomeadas
- Trocar coordenada atual
- Listar coordenadas cadastradas
- Deslocar e escalar coordenadas
- Calcular distância entre dois pontos
- Origem fixa e imutável (0,0)
- Remoção de coordenadas (exceto a origem)

### Sistema de rotas
- Conexão automática entre pontos formando um grafo
- Reconstrução automática do grafo ao adicionar ou remover pontos
- Cálculo da menor rota entre dois pontos
- Retorno da sequência de pontos que compõem o caminho

## Estrutura do sistema

O sistema organiza os pontos do plano em uma estrutura de grafo, onde:

- Vértices representam os pontos do plano cartesiano
- Arestas representam conexões entre dois pontos
- Pesos das arestas são calculados pela distância euclidiana entre os pontos

As conexões entre pontos são geradas automaticamente pelo sistema.

## Lógica de geração das conexões

Para criar as conexões do grafo, o sistema utiliza uma lógica baseada na proximidade entre os pontos.

### Média das distâncias das arestas
O sistema calcula uma média das distâncias das arestas existentes para definir um raio inicial de busca.

- Quando existem poucas arestas, é utilizada a média simples
- Quando existem muitas arestas, o cálculo remove o menor e o maior valor para reduzir o impacto de outliers

Isso ajuda a manter o comportamento das conexões mais estável.

### Busca pelo ponto mais próximo
Para cada ponto do grafo, o sistema procura o vizinho mais próximo dentro do raio calculado.

Caso nenhum ponto seja encontrado dentro desse raio, o sistema expande progressivamente o raio até encontrar um ponto possível para conexão.

### Reconstrução do grafo
Sempre que um ponto é adicionado ou removido:

- todas as arestas são recalculadas
- o grafo é reconstruído automaticamente
- novas conexões são geradas

Isso mantém a estrutura das rotas consistente.

## Sistema de rotas (Algoritmo de Dijkstra)

O projeto implementa o algoritmo de Dijkstra para calcular o menor caminho entre dois pontos do grafo.

### Estruturas de dados utilizadas

- HashMap para armazenar as menores distâncias conhecidas
- HashMap para registrar o ponto anterior no caminho
- HashSet para controlar os pontos visitados
- PriorityQueue para sempre selecionar o ponto com menor custo acumulado

### Funcionamento geral

1. Todas as distâncias são inicializadas como infinito  
2. A origem recebe distância 0  
3. Uma fila de prioridade seleciona sempre o ponto com menor distância conhecida  
4. Para cada vizinho é calculada uma nova distância acumulada  
5. Se essa distância for menor que a atual, o caminho é atualizado  
6. O algoritmo termina quando o destino é removido da fila de prioridade  

Após a execução, o caminho é reconstruído utilizando o mapa de anteriores, percorrendo os pontos do destino até a origem.

## Arquitetura do projeto

### Ponto
Classe base que representa um ponto no plano cartesiano.

Responsável por:
- armazenar coordenadas x e y
- calcular distâncias
- realizar deslocamento e escala

### PontoNomeado
Extende Ponto e adiciona:

- nome do ponto
- identificação no sistema
- exibição formatada

Essa classe representa os vértices do grafo.

### PontoOrigem
Classe especial que representa a origem fixa (0,0).

Características:

- não pode ser alterada
- não pode ser removida
- métodos sobrescritos para impedir modificações

### Aresta
Representa uma conexão entre dois pontos do grafo.

Cada aresta armazena:

- os dois pontos conectados
- o peso da conexão (distância entre os pontos)

### GrafoPlano
Classe responsável por gerenciar o grafo.

Responsabilidades:

- armazenar pontos
- armazenar arestas
- gerar conexões automaticamente
- reconstruir o grafo
- executar o algoritmo de Dijkstra

### PlanoCartesiano
Classe principal do sistema.

Responsável por:

- executar o menu interativo
- receber entrada do usuário
- chamar operações do grafo e dos pontos

## Conceitos de Programação Orientada a Objetos aplicados

Este projeto foi desenvolvido com foco em praticar conceitos fundamentais de POO em Java:

- **Herança**
  - PontoNomeado herda de Ponto
  - PontoOrigem herda de PontoNomeado

- **Polimorfismo**
  - A classe PontoOrigem sobrescreve métodos para impedir alteração da origem.

- **Sobrecarga de métodos**
  - distance()
  - distance(Ponto pt)
  - distance(double x, double y)

- **Encapsulamento**
  - A manipulação dos pontos e das conexões do grafo é controlada pelas classes responsáveis pelo gerenciamento do sistema.

- **Uso de Iterator**
  - Utilizado para remover elementos da lista com segurança.

- **Objeto especial imutável**
  - A origem (0,0) é representada por um objeto fixo (PontoOrigem) que não pode ser modificado ou removido.

- **Estruturas de dados da biblioteca Java**
  - ArrayList
  - HashMap
  - HashSet
  - PriorityQueue

## Complexidade do algoritmo

Considerando:

- V = número de pontos (vértices)
- E = número de conexões (arestas)

A complexidade do algoritmo de Dijkstra utilizado é aproximadamente:

O((V + E) log V)

Isso ocorre devido ao uso da PriorityQueue, que permite selecionar o próximo ponto com menor custo de forma eficiente.

## Como executar

1. Compile e execute a classe PlanoCartesiano
2. Use o menu no console para criar e manipular coordenadas
3. Utilize o sistema de rotas para calcular o menor caminho entre dois pontos

## Próximos passos

- Possível implementação de visualização gráfica do plano cartesiano

## Autor

Ramos
