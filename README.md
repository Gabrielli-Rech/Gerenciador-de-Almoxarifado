# 📦 Gerenciador de Almoxarifado

O **Gerenciador de Almoxarifado** é uma aplicação desktop desenvolvida em Java, focada na eficiência do controle de estoque. O sistema permite o gerenciamento completo de materiais (CRUD), com um dashboard dinâmico que sinaliza níveis críticos de armazenamento em tempo real.



---

## ✨ Funcionalidades Principais

* **Dashboard Inteligente**: Visualização rápida do total de itens e quantidade de alertas críticos.
* **CRUD Completo**: Adição, listagem, edição e exclusão de produtos de forma intuitiva.
* **Controle de Saída**: Registro de retiradas com atualização instantânea do saldo.
* **Alertas de Estoque**: Identificação visual (cor vermelha) para itens que atingiram o limite mínimo configurado.
* **Persistência de Dados**: Salvamento automático em arquivo CSV, garantindo que os dados não sejam perdidos ao fechar a aplicação.
* **Interface Premium**: Design moderno utilizando componentes Swing personalizados e separação lógica de ações por cores.

---

## 🏗️ Arquitetura do Projeto (MVC)

O projeto segue o padrão de arquitetura **Model-View-Controller**, garantindo separação de responsabilidades e facilidade de manutenção:

* **`model`**: Classes que representam os dados (Produto).
* **`view`**: Interface gráfica moderna (AlmoxarifadoGrafico).
* **`controller`**: Lógica de negócio e mediação entre dados e interface.
* **`repository`**: Gerenciamento de persistência de dados no arquivo `estoque.csv`.

---
## Estrutura 
src/
├── controller/        # Lógica de controle e eventos
├── model/             # Entidades de dados
├── repository/        # Persistência em CSV
├── view/              # Interfaces gráficas (Swing)
└── App.java           # Classe principal (Main)


## 🛠️ Tecnologias Utilizadas
Linguagem: Java

Interface Gráfica: Java Swing (AWT)

Armazenamento: Arquivo de texto (CSV)

Paradigma: Programação Orientada a Objetos (POO)
