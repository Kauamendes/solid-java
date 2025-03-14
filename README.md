# SOLID com Java e Spring

Este repositório tem como objetivo documentar e demonstrar a aplicação dos princípios SOLID no desenvolvimento com Java e Spring. Cada princípio é abordado em uma estrutura organizada para facilitar o aprendizado e a consulta.

## Estrutura do Projeto

A estrutura do projeto segue a seguinte organização de pastas:

```
solid-java/
├── src/main/br/com/kauamendes/solidjava/
│   ├── common/        # Contém classes reutilizáveis, como entidades, DTOs e exceções
│   │   ├── entities/  # Entidades do domínio
│   │   ├── dto/       # Objetos de Transferência de Dados
│   │   ├── exceptions/# Exceções customizadas
│   ├── srp/           # Single Responsibility Principle (Princípio da Responsabilidade Única)
│   ├── ocp/           # Open/Closed Principle (Princípio do Aberto/Fechado)
│   ├── lsp/           # Liskov Substitution Principle (Princípio da Substituição de Liskov)
│   ├── isp/           # Interface Segregation Principle (Princípio da Segregação de Interfaces)
│   ├── dip/           # Dependency Inversion Principle (Princípio da Inversão de Dependência)
```

## Princípios SOLID

Os princípios SOLID são diretrizes que ajudam a criar um código mais modular, reutilizável e de fácil manutenção. Aqui está um resumo de cada um:

1. **Single Responsibility Principle (SRP)** - Cada classe deve ter uma única responsabilidade.
2. **Open/Closed Principle (OCP)** - As classes devem estar abertas para extensão, mas fechadas para modificação.
3. **Liskov Substitution Principle (LSP)** - Objetos de uma classe derivada devem poder substituir objetos da classe base sem alterar o comportamento esperado.
4. **Interface Segregation Principle (ISP)** - Nenhuma classe deve ser forçada a implementar interfaces que não usa.
5. **Dependency Inversion Principle (DIP)** - Os módulos de alto nível não devem depender de módulos de baixo nível, ambos devem depender de abstrações.

### SOLID e Orientação a Objetos

Os princípios SOLID nada mais são do que a boa utilização dos conceitos da Programação Orientada a Objetos (POO). Seguir esses princípios ajuda a criar código mais coeso, desacoplado e sustentável ao longo do tempo.

Além disso, alguns princípios do SOLID combinam muito bem com o uso de padrões de projeto (Design Patterns). Por exemplo:
- **SRP** pode ser aplicado com padrões como **Factory Method** para separar responsabilidades.
- **OCP** pode ser aplicado em conjunto com o padrão **Strategy** para permitir extensibilidade sem modificar o código existente.
- **LSP** se relaciona com o uso correto de herança e polimorfismo.
- **ISP** se relaciona com o uso de **Interfaces Segregadas**, garantindo que cada interface tenha apenas os métodos necessários para uma determinada funcionalidade.
- **DIP** pode ser implementado com **Dependency Injection** e o padrão **Adapter**, promovendo melhor organização do código.

### Entender quando flexibilizar essas boas práticas é tão importante quanto conhecê-las!

Além do SOLID, é interessante conhecer outros princípios como:
- **KISS (Keep It Simple, Stupid)** - Mantenha o código simples e direto.
- **YAGNI (You Aren't Gonna Need It)** - Não implemente algo que não é necessário no momento.
- **DRY (Don't Repeat Yourself)** - Evite repetição de código e promova reutilização.

Esses princípios ajudam a evitar overengineering e garantem que o código seja eficiente e fácil de manter.

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Maven**
- **JUnit** (para testes)

## Como Executar o Projeto

1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/solid-java.git
   ```

2. Acesse a pasta do projeto:
   ```sh
   cd solid-java
   ```

3. Compile e execute o projeto com Maven:
   ```sh
   mvn spring-boot:run
   ```

4. Para rodar os testes:
   ```sh
   mvn test
   ```
