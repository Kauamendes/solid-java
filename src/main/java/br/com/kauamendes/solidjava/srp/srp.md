# Princípio da Responsabilidade Única (SRP)

O Princípio da Responsabilidade Única (SRP) afirma que uma classe deve ter apenas uma razão para mudar, ou seja, ela deve ser responsável por uma única parte do comportamento do sistema. Isso significa que, se uma classe tiver múltiplas responsabilidades, ela se tornará mais difícil de manter, testar e modificar. Cada responsabilidade tem seu próprio ciclo de mudanças, e quando uma classe tem várias responsabilidades, as mudanças em uma parte do sistema podem afetar outras partes que não têm relação com ela.

## 🎯 Por que essa estrutura segue o SRP?

### ✅ Cada classe tem uma responsabilidade bem definida:

    UsuarioController: Só lida com as requisições HTTP relacionadas a usuários.
    UsuarioService: Contém apenas as regras de negócio relacionadas ao usuário.
    UsuarioRepository: Gerencia as operações de banco de dados relacionadas ao usuário.
    Model (Usuario): Representa a entidade de domínio Usuario com seus atributos e comportamentos.
    UsuarioDto: Serve como um objeto de transporte de dados entre camadas, sem lógica de negócios.
    UsuarioMapper: Responsável apenas por converter entre as representações de dados (DTOs) e a entidade Usuario.

## Exemplo de Violação do SRP

A violação do SRP ocorre quando colocamos mais de uma responsabilidade em uma classe. Por exemplo, imagine que, ao invés de usar um Mapper, fizemos as conversões diretamente nas entidades.

## Exemplo de violação com UsuarioEntity e UsuarioDto:

### UsuarioEntity com método de conversão:

    public class UsuarioEntity {
        private Long id;
        private String nome;
        private String email;

    // Método que converte a entidade para o DTO
        public UsuarioDto toDto() {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(this.id);
        dto.setNome(this.nome);
        dto.setEmail(this.email);
        return dto;
        }

    // Outros métodos...
    }

### UsuarioDto com método de conversão:

    public class UsuarioDto {
        private Long id;
        private String nome;
        private String email;

        // Método que converte o DTO para a entidade
        public static UsuarioEntity of(UsuarioDto dto) {
            UsuarioEntity entity = new UsuarioEntity();
            entity.setId(dto.getId());
            entity.setNome(dto.getNome());
            entity.setEmail(dto.getEmail());
            return entity;
        }

        // Outros métodos...
    }

Nesse exemplo, tanto a classe UsuarioEntity quanto a UsuarioDto estão fazendo o trabalho de transformar dados entre representações, o que significa que elas estão assumindo responsabilidades que não deveriam ter.
Por que isso fere o SRP?

### Responsabilidade duplicada:
A classe UsuarioEntity deveria ser responsável apenas por representar os dados da entidade de domínio e não por logicamente transformá-los para outra forma.
Da mesma forma, o UsuarioDto deveria ser apenas um objeto de transporte de dados, e não uma classe que também realiza a conversão.

### Dificuldade de manutenção:
Caso você precise alterar a maneira como os dados são mapeados entre a entidade e o DTO (por exemplo, se você precisar adicionar um novo campo ou transformar o dado de uma maneira diferente), terá que modificar essas duas classes. Isso torna a manutenção mais difícil e aumenta o risco de introduzir erros ao tentar manter ambas as responsabilidades.

### Testabilidade comprometida:
Além disso, testar o comportamento das classes de entidade e DTO se torna mais difícil, pois elas agora têm uma lógica de conversão interna que, em vez de estar em uma classe separada e focada nisso, está misturada com a lógica principal da classe.

## Melhor prática: Utilizando o Mapper (SRP)

Em vez de colocar a responsabilidade de conversão diretamente nas classes de entidade ou DTO, podemos delegar essa tarefa a uma classe dedicada, como o UsuarioMapper. Isso isolará a responsabilidade de mapeamento e manterá as outras classes com uma responsabilidade única.

#### UsuarioMapper com MapStruct (Alinhado com SRP):

    @Mapper(componentModel = "spring")
    public interface UsuarioMapper {

        // Mapeamento de UsuarioEntity para UsuarioDto
        UsuarioDto toDto(UsuarioEntity entity);
    
        // Mapeamento de UsuarioDto para UsuarioEntity
        UsuarioEntity toEntity(UsuarioDto dto);
    }

## Por que essa abordagem segue o SRP?

### Responsabilidade bem definida: 
O UsuarioMapper tem uma única responsabilidade, que é a conversão entre os tipos de dados (entidade e DTO).

### Manutenção simplificada: 
Caso a lógica de mapeamento precise ser alterada, você só precisa modificar a interface UsuarioMapper, sem afetar a lógica de negócios ou a estrutura de dados das classes UsuarioEntity e UsuarioDto.

### Testabilidade aprimorada:
Como a conversão é isolada em um Mapper separado, é muito mais fácil testar essa funcionalidade sem precisar testar diretamente as classes de modelo.

### Escalabilidade: 
Se no futuro você precisar adicionar mais conversões entre outros tipos de entidades ou DTOs, pode fazer isso diretamente no UsuarioMapper, sem sobrecarregar as classes de modelo com responsabilidade extra.

## Conclusão

Embora seguir o SRP seja uma boa prática para garantir a manutenibilidade, testabilidade e organização do código, é importante lembrar que a adoção rigorosa do SOLID ou de qualquer outro padrão deve ser balanceada com a simplicidade e as necessidades do projeto. Às vezes, a simplicidade de implementação pode ser mais importante do que seguir estritamente todos os princípios, mas sempre com o objetivo de garantir código claro, bem estruturado e de fácil manutenção.