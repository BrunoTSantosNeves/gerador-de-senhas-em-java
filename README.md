# Gerador de Senhas Criptografadas

Este projeto é um gerador de senhas seguras que utiliza diversos algoritmos de hashing para garantir a segurança das senhas geradas. Ele permite a criação de senhas aleatórias, sua criptografia e armazenamento seguro em um banco de dados MySQL.

## Tecnologias Utilizadas
- **Java** (JDK 17+)
- **Maven** (para gerenciamento de dependências)
- **MySQL** (para armazenamento de senhas criptografadas)
- **JUnit 5** (para testes unitários)

## Estrutura do Projeto

O projeto está organizado de forma modular para facilitar a manutenção e escalabilidade. Abaixo está a estrutura de diretórios e a explicação de cada componente:

```
gerador-de-senhas/
│── src/
│   ├── main/java/com/example/
│   │   ├── database/
│   │   │   ├── DatabaseManager.java
│   │   ├── generator/
│   │   │   ├── config/
│   │   │   │   ├── PasswordPolicy.java
│   │   │   ├── hashing/
│   │   │   │   ├── HashAlgorithm.java
│   │   │   │   ├── HashingUtils.java
│   │   │   │   ├── PasswordGenerator.java
│   │   │   │   ├── SecurePasswordGenerator.java
│   │   ├── App.java
│   ├── test/java/com/example/
│   │   ├── database/
│   │   │   ├── DatabaseConnectionTest.java
│   │   ├── generator/
│   │   │   ├── hashing/
│   │   │   │   ├── HashingUtilsTest.java
│   │   │   │   ├── TestHashing.java
│   │   │   ├── DatabaseManagerTest.java
│   │   │   ├── SecurePasswordGeneratorTest.java
│── pom.xml
│── README.md
│── .gitignore
│── LICENSE
```

### Explicação das Pastas e Arquivos

#### `main/java/com/example/`
- **`database/`**: Contém classes responsáveis por gerenciar a conexão com o banco de dados e salvar as senhas criptografadas.
  - `DatabaseManager.java`: Classe singleton para gerenciar conexões com o MySQL.

- **`generator/`**: Contém a lógica de geração e criptografia das senhas.
  - **`config/`**: Define regras e políticas para senhas.
    - `PasswordPolicy.java`: Define regras mínimas de segurança para senhas.
  - **`hashing/`**: Implementação dos algoritmos de hash suportados.
    - `HashAlgorithm.java`: Enum que define os algoritmos de hashing disponíveis (PBKDF2, BCrypt, SHA-256).
    - `HashingUtils.java`: Classe utilitária para aplicar e verificar hashes.
    - `PasswordGenerator.java`: Responsável por gerar senhas aleatórias.
    - `SecurePasswordGenerator.java`: Implementa um gerador de senhas que respeita políticas de segurança definidas.
  - `App.java`: Classe principal que inicia a aplicação.

#### `test/java/com/example/`
- **`database/`**: Testes para a camada de persistência de dados.
  - `DatabaseConnectionTest.java`: Testa a conexão com o banco de dados.
- **`generator/`**: Testes para a lógica de geração e hash de senhas.
  - **`hashing/`**: Testes específicos para os algoritmos de hash.
    - `HashingUtilsTest.java`: Testes unitários para garantir que os hashes sejam gerados corretamente.
    - `TestHashing.java`: Testes adicionais de hashing.
  - `DatabaseManagerTest.java`: Testa a inserção de dados no banco de dados.
  - `SecurePasswordGeneratorTest.java`: Testa a geração de senhas seguras.

## Instalação e Configuração

### Pré-requisitos
Antes de começar, certifique-se de ter instalado:
- Java JDK 17+
- Apache Maven
- MySQL Server

### Configuração do Banco de Dados
1. Crie um banco de dados MySQL chamado `gerador_senhas`.
2. No MySQL, execute o seguinte comando para criar a tabela necessária:

```sql
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(255) NOT NULL,
    senha_hash TEXT NOT NULL,
    algoritmo VARCHAR(50) NOT NULL
);
```
3. Atualize `DatabaseManager.java` com as credenciais corretas do seu banco de dados.

### Executando o Projeto
1. Clone este repositório:
   ```sh
   git clone https://github.com/seu-usuario/gerador-de-senhas.git
   ```
2. Acesse o diretório do projeto:
   ```sh
   cd gerador-de-senhas
   ```
3. Compile o projeto com Maven:
   ```sh
   mvn clean install
   ```
4. Execute a aplicação:
   ```sh
   mvn exec:java -Dexec.mainClass="com.example.App"
   ```

## Testes
Para executar os testes unitários:
```sh
mvn test
```

# Guia de Contribuição

Obrigado por considerar contribuir para este projeto! Siga as diretrizes abaixo para garantir um fluxo de trabalho organizado e eficiente.

## Como Contribuir

1. **Fork o repositório**: Clique no botão "Fork" no GitHub.
2. **Clone o repositório**: No seu terminal, execute:
   ```sh
   git clone https://github.com/seu-usuario/gerador-de-senhas.git
   ```
3. **Crie uma branch para sua contribuição**:
   ```sh
   git checkout -b minha-nova-feature
   ```
4. **Faça suas modificações** e garanta que elas estejam bem documentadas.
5. **Teste suas mudanças** antes de abrir um PR (Pull Request).
6. **Commit suas alterações**:
   ```sh
   git commit -m "Descrição clara da alteração"
   ```
7. **Envie para o seu fork**:
   ```sh
   git push origin minha-nova-feature
   ```
8. **Abra um Pull Request**: Vá até o repositório original no GitHub e crie um PR a partir da sua branch.

## Convenções de Código

- Siga as boas práticas do Java.
- Utilize **nomes descritivos** para variáveis, métodos e classes.
- Documente seu código com **Javadoc**.
- Formate o código utilizando **o padrão do projeto**.

## Revisão de Código

Todos os Pull Requests passarão por revisão antes de serem mesclados ao branch principal. Fique atento aos comentários e sugestões dos revisores.

## Relatando Problemas

Se encontrar um bug ou tiver uma sugestão de melhoria, abra uma [issue](https://github.com/seu-usuario/gerador-de-senhas/issues) e descreva o problema de forma clara.

## Futuras Melhorias

Aqui estão algumas sugestões de melhorias para o projeto:
- **Adição de suporte a mais algoritmos de hashing** (exemplo: Argon2).
- **Implementação de uma interface gráfica** para facilitar o uso.
- **Internacionalização (i18n)** para suportar múltiplos idiomas.
- **Melhoria nos testes unitários e cobertura de código**.
- **Criação de um sistema de recuperação de senhas criptografadas**.
- **Integração com serviços externos de autenticação** (OAuth, JWT, etc.).

Fique à vontade para contribuir com essas melhorias ou sugerir novas ideias!

---
Siga estas diretrizes para manter a qualidade e organização do projeto. Boa contribuição! 🚀



## Licença
Este projeto está licenciado sob a MIT License - consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

