# SIGA — Padrões comportamentais

Atividade da Aula 12 de Técnicas de Programação II do curso de Desenvolvimento de Software Multiplataforma da Fatec de Porto Ferreira.

## Versão inicial

O projeto simula matrículas, emissão de boletos e reações à confirmação de uma matrícula. Esta versão mantém os problemas do código inicial para as próximas etapas de aplicação dos padrões Strategy e Observer.

- O desconto de egresso é aplicado na matrícula, mas não no boleto. Ana Pereira recebe os valores de R$ 720,00 e R$ 800,00, respectivamente.
- As reações estão concentradas na fachada. Quando o aviso à coordenação falha, o registro de auditoria seguinte deixa de acontecer.

## Como executar

Requisito: JDK 17 ou superior.

```bash
javac -encoding UTF-8 -d bin src/siga/*.java
java -cp bin siga.Main
```

## Organização

Os arquivos Java estão em `src/siga`, com uma classe pública por arquivo.