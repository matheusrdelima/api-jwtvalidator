# JWT Validator API  

API desenvolvida para **validação de tokens JWT**, permitindo aplicar diferentes regras de negócio sobre as *claims*.  

A aplicação foi projetada com foco em **qualidade de código, métricas observáveis e arquitetura flexível**, garantindo fácil evolução e manutenção.  


## ⚙️ Tecnologias e Dependências  

A API foi construída em **Java 17** com **Spring Boot 3.5.4**, utilizando **Maven** como ferramenta de build, gerenciamento de dependências e execução de testes.  

Principais dependências e ferramentas utilizadas:  

- **Spring Boot Starter Web** → Criação da API REST.  
- **Spring Boot Starter Validation** → Validação de dados de entrada.  
- **Spring Boot Actuator** → Endpoints de monitoramento e saúde da aplicação.  
- **Micrometer + Prometheus** → Coleta de métricas para observabilidade.  
- **Auth0 Java JWT** → Decodificação e manipulação de tokens JWT.  
- **Spring Boot DevTools** → Hot reload em ambiente de desenvolvimento.  
- **Spring Boot Starter Test** → Testes unitários e de integração.  
- **Mockito** → Criação de mocks em testes.  
- **REST Assured** → Testes de integração e validação de APIs REST.  
- **PIT Mutation Testing** → Testes de mutantes para validar a efetividade dos testes unitários.  


## 🚀 Como executar a aplicação com Docker Compose  

A aplicação já está configurada para subir com **Prometheus** e **Grafana** integrados.  

### 1. Build e subida dos containers  
```bash
docker compose up --build
```

### 2. Testes Unitários
Executa todos os testes unitários da aplicação.
```bash
mvn test
```

### 3. Testes Integração
Executa testes que verificam os endpoints da API utilizando REST Assured.
```bash
mvn verify
```

### 4. Testes de Mutantes (PIT)
Executa o plugin PIT para validação da efetividade dos testes unitários.
```bash
mvn pitest:mutationCoverage
```

## 🌐 Serviços Disponíveis  

Após subir os containers com Docker Compose, os seguintes serviços estarão disponíveis:  

- **API** → [http://localhost:8080](http://localhost:8080)  
- **Endpoint de Métricas (Prometheus)** → [http://localhost:8080/actuator/prometheus](http://localhost:8080/actuator/prometheus)  
- **Grafana** → [http://localhost:3000](http://localhost:3000)

### Acesso ao Grafana  
- Usuário: **admin**  
- Senha: **admin**  

### Dashboard  
O **dashboard "API JWT Validator"** será importado automaticamente. 

Para utilizá-lo:  
1. Acesse o Grafana.
2. Selecione o Dashboard: **API JWT Validator**
3. Selecione a instância **`api-jwt-validator:8080`**.  

O painel será exibido conforme a imagem abaixo:

![Dashboard-api-jwt](./docs/dashboard.png)


## 📂 Collection de Testes  

Uma collection do **Insomnia** está disponível para facilitar os testes da API.  

Você pode importá-la clicando no botão abaixo:  

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=API%20JWT%20Validator&uri=https%3A%2F%2Fgithub.com%2Fmatheusrdelima%2Fapi-jwtvalidator%2Fblob%2Fmain%2Fcollection.yaml) 

Caso opte pela importação manual, utilize o arquivo [**collection.yaml**](https://github.com/matheusrdelima/api-jwtvalidator/blob/main/collection.yaml), disponível na raiz do projeto.

## 🧪 Massa de teste

### Caso 1:
Entrada:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg
```
Saida:
```
verdadeiro
```
Justificativa:
Abrindo o JWT, as informações contidas atendem a descrição:
```json
{
  "Role": "Admin",
  "Seed": "7841",
  "Name": "Toninho Araujo"
}
```

### Caso 2:
Entrada:
```
eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg
```
Saida:
```
falso
```
Justificativa:
JWT invalido

### Caso 3:
Entrada:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs
```
Saida:
```
falso
```
Justificativa:
Abrindo o JWT, a Claim Name possui caracter de números
```json
{
  "Role": "External",
  "Seed": "72341",
  "Name": "M4ria Olivia"
}
```

### Caso 4:
Entrada:
```
eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY
```
Saida:
```
falso
```

---

## 🛠 Histórico de Construção da API  

Durante o desenvolvimento da API, foram aplicadas diversas boas práticas e técnicas para garantir **qualidade, escalabilidade e manutenibilidade**:  

- Implementação de **testes unitários** com ampla cobertura.  
- Inclusão de **testes de mutantes**, integrados em um workflow no GitHub para simular uma pipeline de CI.  
- Instrumentação de **métricas de negócio** para monitoramento via Grafana (tokens validados com sucesso ou falha).  
- Uso de **Docker Compose** para orquestrar os containers da aplicação, Prometheus e Grafana.  
- Criação de **Dockerfile com multi-stage build**, garantindo imagens finais mais leves e otimizadas.  
- Isolamento dos **contratos da API** na pasta `core`, com implementações na pasta `infrastructure`, seguindo o **princípio da segregação de interfaces (SOLID)**.  
- Realização de **commits pequenos e incrementais**, facilitando reversões rápidas em caso de necessidade.  
- Centralização das **regras de negócio** na pasta `domain`, mantendo-as independentes de frameworks.  
- Aplicação do **Strategy Pattern** para permitir a inclusão de novos validadores de claims sem impactar o serviço principal.  
- Estruturação baseada no **princípio da responsabilidade única (SRP)**, garantindo que cada classe possua apenas uma responsabilidade.  
- Arquitetura projetada para possibilitar a **expansão das validações de claims JWT** e integração de novas bibliotecas sem afetar o código central da API.  


