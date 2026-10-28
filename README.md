# SIGA — Padrões comportamentais: Strategy e Observer

**Técnicas de Programação II · Aula 12** — Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

## 1. Localizar a divergência

No código inicial, `Matricula.calcularValor` e `GeradorBoleto.calcularValorDoBoleto` tinham cadeias de condicionais duplicadas. A matrícula reconhecia o desconto de egresso, enquanto o boleto verificava somente bolsista e servidor. Ana Pereira, egressa, recebia o valor de R$ 720,00 na matrícula e R$ 800,00 no boleto, considerando uma base de R$ 800,00.

As reações também estavam concentradas em `FachadaMatricula`. O aviso à coordenação lançava uma exceção antes da chamada da auditoria, impedindo que ela fosse executada.

## 2. Criar as estratégias

A interface `PoliticaDesconto` define `aplicar(double valorBase)` e `getDescricao()`. Cada política concreta concentra seu cálculo em um único método:

| Classe | Política | Valor com base de R$ 800,00 |
|---|---|---|
| `SemDesconto` | Valor integral | R$ 800,00 |
| `DescontoBolsista` | Desconto de 50% | R$ 400,00 |
| `DescontoServidor` | Desconto de 20% | R$ 640,00 |
| `DescontoEgresso` | Desconto de 10% | R$ 720,00 |

Cada fator de desconto aparece somente na respectiva classe. Uma nova política pode implementar a interface sem alterar as classes que calculam a matrícula e o boleto.

## 3. Injetar a estratégia

O `Main` escolhe a política e a fornece ao cadastrar a matrícula. A fachada a repassa ao construtor de `Matricula`, que armazena a referência e delega o cálculo ao método `aplicar`. A matrícula não escolhe a política com base nos atributos do aluno.

O `GeradorBoleto` recebe a própria matrícula e utiliza `calcularValor`. Assim, os dois valores vêm da mesma estratégia. Não há condicionais de desconto em nenhuma das duas classes.

Na execução, Ana Pereira recebe R$ 720,00 nos dois pontos. Maria Silva recebe R$ 400,00, Joao Souza recebe R$ 640,00 e Pedro Santos recebe R$ 800,00, também com igualdade entre matrícula e boleto.

## 4. Criar o Observer

A interface `OuvinteMatricula` define `aoMatricular(Matricula)`. As classes `NotificadorEmail`, `PainelVagas`, `AvisoCoordenacao` e `RegistroAuditoria` implementam essa interface e são registradas em `PublicadorMatricula` pelo `Main`.

O publicador oferece `registrar`, `remover` e `publicar`. A publicação percorre uma cópia da lista de ouvintes. Caso um ouvinte seja registrado ou removido durante uma notificação, a alteração passa a valer na publicação seguinte.

Cada chamada fica em um bloco `try/catch`. Uma falha de execução é registrada com o nome do ouvinte e a mensagem do erro, e os próximos ouvintes continuam sendo chamados. O aviso à coordenação mantém a falha proposital quando a turma está lotada. A auditoria, registrada depois dele, continua acontecendo.

A fachada continua responsável por validar o código da turma, criar e armazenar a matrícula e publicar o acontecimento. As reações ficam nos ouvintes. A condição de turma lotada é transportada na matrícula e consultada pelo aviso à coordenação.

## 5. Acrescentar a quinta reação

A classe `EstatisticaOcupacao` implementa `OuvinteMatricula` e contabiliza as matrículas por turma. O total da turma começa em zero e aumenta a cada evento recebido. Essa contagem representa as matrículas registradas nesta execução; não calcula um percentual, pois o código inicial não informa a capacidade das turmas.

Sua inclusão exige a classe nova e uma linha de registro no `Main`:

```java
publicador.registrar(new EstatisticaOcupacao());
```

A fachada depende apenas do publicador e não conhece essa reação. Nenhuma mudança adicional nela é necessária para acrescentar a estatística. Na demonstração, a turma DSM-2A termina com quatro matrículas, incluindo a matrícula em que o aviso à coordenação falha.

## Como compilar e executar

Pré-requisito: JDK 17 ou superior.

No terminal, a partir da pasta do projeto:

```bash
javac -encoding UTF-8 -d bin src/siga/*.java
java -cp bin siga.Main
```

No VS Code, abra a pasta do projeto e o arquivo `src/siga/Main.java`. Com o suporte a Java instalado, use **Run Java** sobre o método `main`.

## Evidências e verificação

A saída completa está em [docs/execucao.txt](docs/execucao.txt). Ela demonstra as quatro políticas, a igualdade dos valores, o registro da falha da coordenação seguido da auditoria e a contagem de ocupação.

Também foram verificadas a injeção de uma política adicional, a independência entre ouvintes, a inclusão e remoção durante a publicação, a contagem por turma, a proteção da lista armazenada pela fachada e a rejeição de código de turma vazio. A compilação foi feita com compatibilidade com Java 17 e sem avisos do compilador.

## Organização

- `src/siga/`: entidades, políticas de desconto, fachada, publicador, ouvintes e demonstração.
- `docs/execucao.txt`: evidência da execução.
- `bin/`: classes compiladas, ignoradas pelo Git.