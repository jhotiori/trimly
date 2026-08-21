<h3 align="center">
  <img src=".github/assets/banner.png" alt="Trimly">
</h3>

<h4 align="center">
  <a href="https://github.com/jhotiori/trimly/tree/main/" rel="noopener noreferrer">Source</a>
  ·
  <a href="https://github.com/jhotiori/trimly/commits/main/" rel="noopener noreferrer">Commits</a>
  ·
  <a href="https://github.com/jhotiori/trimly/pulls" rel="noopener noreferrer">PRs</a>
  ·
  <a href="https://github.com/jhotiori/trimly/tree/main/LICENSE.md" rel="noopener noreferrer">License</a>
</h4>

<p align="center">
  <a href="https://github.com/jhotiori/trimly/stargazers">
    <img src="https://img.shields.io/github/stars/jhotiori/trimly?style=for-the-badge&labelColor=%231a1a1a&color=%23000000&logo=github&logoColor=ffffff" alt="GitHub Stars">
  </a>
  <a href="https://github.com/jhotiori/trimly/network/members">
    <img src="https://img.shields.io/github/forks/jhotiori/trimly?style=for-the-badge&labelColor=%231a1a1a&color=%23000000&logo=git&logoColor=ffffff" alt="GitHub Forks">
  </a>
  <a href="https://github.com/jhotiori/trimly/issues">
    <img src="https://img.shields.io/github/issues/jhotiori/trimly?style=for-the-badge&labelColor=%231a1a1a&color=%23000000&logo=github&logoColor=ffffff" alt="GitHub Issues">
  </a>
</p>

<blockquote>
    <strong>
    Projeto acadêmico de uma aplicação voltada ao gerenciamento de agendamentos em barbearias, desenvolvida a partir de uma demanda real e focada na modernização do processo de contratação de serviços de cuidados masculinos.
    </strong>
</blockquote>

<hr>

## 🛠️ Técnologias
| Área                   | Tecnologias                                               |
| ---------------------- | --------------------------------------------------------- |
| **Backend**            | Java (17) · SpringBoot (JPA, Security, Validation)   |
| **Frontend**           | Angular (19) · Bootstrap · Material Design Bootstrap      |
| **Banco de dados**     | PostgreSQL                                                |
| **Infraestrutura**     | AWS Cloud                                                 |
| **Controle de versão** | Git · GitHub                                              |

## 👥 Equipe
| Integrante             | Cargo           | Responsabilidade                |
| ---------------------- | --------------- | ------------------------------- |
| **João**               | Líder           | Frontend + Backend              |
| **Nicholas**           | Desenvolvedor   | Frontend                        |
| **Alexandre**          | Desenvolvedor   | Backend                         |
| **Evandro**            | Desenvolvedor   | Backend                         |

## 🧪 Desenvolvimento
Clone o repositório localmente e navegue até a raiz dele:
```bash
git clone https://github.com/jhotiori/trimly
cd trimly
```

### 🎨 Frontend
> _Trimly utiliza [**Bun**](https://bun.sh/) como manuseador de pacotes Node, de preferência, é recomendado utilizar ele para o Frontend!_

Navegue até o diretório do Frontend:
```bash
cd frontend
```

Instale as dependencias necessárias com seu manuseador de pacotes Node:
```bash
bun install
```

#### Live
Para rodar a aplicação do front em ambiente de desenvolvimento com auto-carregamento:
```bash
bun start
```

#### Build
Para construir o projeto em modo de produção (otimizado para tamanho e velocidade):
```bash
bun build
```

### 🚀 Backend
Navegue até o diretório do Backend:
```bash
cd backend
```

Depois, rode o projeto usando o executavel do Maven `mvnw`

#### Linux/macOS
```bash
./mvnw spring-boot:run
```

#### Windows
```bash
mvnw.cmd spring-boot:run
```
