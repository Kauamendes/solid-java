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

## Contribuição

Caso queira contribuir, siga estes passos:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature (`git checkout -b minha-feature`).
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova funcionalidade'`).
4. Faça push para a branch (`git push origin minha-feature`).
5. Abra um Pull Request.

## Autor

Projeto criado por [Kaua Mendes](https://github.com/seu-usuario) para documentar e praticar os conceitos SOLID com Java e Spring.

---

Se tiver dúvidas ou sugestões, fique à vontade para abrir uma issue ou contribuir! 🚀

