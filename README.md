# 🎵 ScreenMusic

**ScreenMusic** é uma aplicação em Java com Spring Boot que roda no terminal e permite interagir com um banco de dados PostgreSQL para cadastrar e consultar **músicas, artistas e álbuns**. A interface é simples, baseada em linha de comando, e o projeto utiliza a API da OpenAI para gerar biografias automáticas de artistas.

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- CommandLineRunner
- Spring Data JPA
- PostgreSQL
- OpenAI API (para biografias)


## 📦 Instalação

1. Clone este repositório:

```bash
git clone https://github.com/KlaytonFacre/screenmusic.git
cd screenmusic
```

2. Crie os arquivos de configuração no diretório src/main/resources/:
- `api-keys.properties`:
```
openai.api.key=SUA_CHAVE_DA_OPENAI
```

- `database-credentials.properties`:
```
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

3. Certifique-se de que o PostgreSQL esteja em execução e com o banco de dados ```alura_music``` criado.

4. Execute a aplicação:
```
./mvnw spring-boot:run
```

## 💻 Uso
A aplicação inicia automaticamente e apresenta um menu interativo no terminal. Você poderá:

Cadastrar novos artistas

Cadastrar músicas e álbuns

Consultar dados armazenados

Gerar biografias de artistas com auxílio da OpenAI

## 📄 Licença
Este projeto está licenciado sob a licença MIT. Sinta-se à vontade para usar, modificar e distribuir.

## 👤 Autor
Desenvolvido por Klayton Facre.

📫 Para feedbacks, sugestões ou contribuições, fique à vontade para abrir uma issue ou enviar um pull request!