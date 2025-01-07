# StartDB Grupo 08 - Conecta Gerações
> Gabriela de Castro Laurindo
## Sobre o Desafio 
O desafio que nos foi apresentado trouxe à tona um problema relevante: a solidão e o isolamento social entre idosos. Observamos que isso é uma questão crescente, afetando tanto o bem-estar emocional quanto a qualidade de vida dessa geração. Para responder ao desafio, idealizamos a Conecta Gerações, uma aplicação web que promove conexões intergeracionais por meio de atividades e suporte entre jovens e idosos.
## Mas como funciona a solução?
A Conecta Gerações facilita o encontro e a criação de atividades (que chamamos de conexões), que podem ser encontros recreativos, suporte em tarefas cotidianas ou até mesmo aulas de tecnologia. Ela funciona como uma ponte para unir essas gerações.
## Técnicas implementadas
Nossa aplicação utiliza React-Typescript no frontend e Spring Boot no backend, com uma API REST para comunicação.
# Requisitos 
**Backend (Spring Boot):**

- **Requisição GET (✅):** Implementamos com sucesso requisições GET em diversos pontos, como para listar atividades (/atividades), buscar atividade por ID (/atividades/{id}), e buscar perfil do usuário (/pessoas/{id}). 
- **Requisição POST (✅):** Também implementamos requisições POST, por exemplo, para criar atividades (/atividades), cadastrar usuário (/cadastro/formulario) e realizar login (/login).
- **Requisição com variável/parâmetro (✅):** Utilizamos tanto @RequestParam (para parâmetros na query string, como em /busca/atividades) quanto @PathVariable(para variáveis na URL, como em /atividades/{id}) para lidar com parâmetros em diferentes tipos de requisições.
- **Repositório (✅):** Criamos os repositórios PessoaRepositorio e AtividadeRepositorio, estendendo JpaRepository para o acesso aos dados no banco.
- **Duas entidades com relacionamento (✅):** Definimos as entidades Pessoa e Atividade, com um relacionamento ManyToOne entre elas, representando a ligação entre usuários e as atividades que criam (ou se voluntariam - parte ainda não implementada).
- **Teste de controlador com isolamento (MockMvc) (✅):** Implementamos testes de controlador com isolamento usando @WebMvcTest, MockMvc e @MockBean, permitindo testar a lógica dos controllers independentemente de outros componentes.
- **Teste de controlador sem isolamento (@SpringBootTest) (❌):** Ainda não implementamos testes de controller sem isolamento. 
- **Banco de dados com dados de testes (✅):** Utilizamos o banco de dados H2 em memória.

**Frontend (React):**

- **Página para listar uma coleção de objetos (✅):** Temos páginas que listam coleções de objetos, como a página `Atividades.tsx` que lista as atividades e `UserPage.tsx`, que lista informações do usuário.
- **Página para inserir um objeto novo (✅):** A página `CriarAtividade.tsx` e `Cadastro.tsx` permitem a criação de novas atividades e usuários.
- **Página para editar um objeto (✅):** A página `UserPage.tsx` permite a edição do perfil do usuário.
- **Transição entre duas páginas (✅):** Utilizamos `react-router-dom` e `useNavigate` para implementar a navegação e transição entre as diferentes páginas da aplicação.
  
**Em aberto**

O ponto principal que ainda precisa ser implementado são os testes de controller sem isolamento.  Além disso, seria bom aprimorar os testes de integração com dados reais no banco de dados H2. 

# Explicando o código por ordem de páginas
## Funcionamento técnico do login e cadastro
1. Cadastro (/cadastro/formulario)
- Cadastro.tsx:
  O formulário coleta nome, email, senha, tipo de usuário e localização, faz validações básicas no frontend (como campos obrigatórios e o formato correto de email) e envia os dados para o backend via api.post("/cadastro/formulario", novoUsuario).
- CadastroController.java
O @RestController indica que a classe lida com requisções REST, @RequestMapping("/cadastro") define o endpoint base, @PostMapping("/formulario") mapeia requisições POST para /cadastro/formulario, @RequestBody indica que os dados da requisição estão no corpo (formato JSON) e, por fim,  método postMethodName recebe os dados, cria um objeto Pessoa e o salva no banco de dados usando o PessoaRepositorio.
- PessoaRequestDTO, Pessoa e PessoaRepositorio
PessoaRequestDTO: um record (classe imutável) que representa os dados recebidos na requisição,  Pessoa: a entidade que representa um usuário no banco de dados, PessoaRepositorio: interface que estende JpaRepository e fornece métodos para acessar o banco de dados (ex: save, findByemail).
2. Login (/login)
- Login.tsx:
O formulário coleta email e senha, realiza validações básicas no frontend (campos obrigatórios, formato de email) e envia os dados para o backend via api.post("/login", { email, senha }).
- LoginController.java
O `@PostMapping` mapeia requisições POST para `/login`, método `login` recebe email e senha, busca o usuário no banco de dados pelo email usando `repositorio.findByemail(log.getEmail())`, verifica se o usuário existe e se a senha informada corresponde à senha armazenada no banco, e se a autenticação for bem-sucedida, retorna o ID do usuário. Caso contrário, lança uma exceção.
**Fruxo de autenticação**: o front recebe o ID do usuário após login bem-sucedido e este ID pode ser armazenado no localStorage (como no código fornecido) para manter a sessão usuário.
3. Busca de Atividades (/busca/atividades)
- Atividades.tsx:
Usa useState para gerenciar os filtros (tag, tipo de encontro, localização), formulário de busca chama handleAtividades ao ser submetido, handleAtividades atualiza o estado filtros com os valores selecionados, o componente ListaDeAtividades recebe os filtros como props e o  botão "Criar uma conexão" redireciona para /criar.
- ListaDeAtividades.tsx:
Usa useEffect para buscar as atividades da API quando o componente monta ou quando os filtros mudam, constrói a query string com base nos filtros usando criarQuery, faz a requisição GET para /busca/atividades recebendo todas as atividades do banco de dados e depois exibindo as que corresponderem aos filtros selecionados (caso algum).
- BuscaController.java:
@GetMapping("/atividades") mapeia requisições GET para /busca/atividades, filtrarAtividades recebe os parâmetros de filtro como @RequestParam e usa o `AtividadeService` para filtrar as atividades.
- AtividadeService.java:
filtrarAtividades filtra a lista de todas as atividades com base nos parâmetros fornecidos. Usa stream e filter para uma abordagem funcional.
4. Criação de atividades (/atividades)
  - CriarAtividade.tsx:
  Usa useState para gerenciar o estado da atividade, handleChange atualiza o estado com os valores dos inputs, handleTagChange converte a string de tags em um array, handleSubmit envia os dados para a API via POST e converte a data para o formato correto antes de enviar.
- AtividadeController.java:
@PostMapping mapeia requisições POST para /atividades, `criar` recebe os dados da atividade como @RequestBody e usa o AtividadeService para criar a atividade.
- AtividadeService.java:
"criar" salva a nova atividade no banco de dados AtividadeRepositorio.
5. Página de Perfil
- User.tsx
useEffect para buscar dados do usuário: Quando o componente monta, o useEffect busca o ID do usuário do localStorage. Se o ID existir, ele faz uma requisição GET para /pessoas/{id} para obter os dados do usuário.
**Estado com useState:**
    - user: Armazena os dados do usuário (nulo inicialmente).
    - isEditing: Controla se o formulário de edição está visível.
    - endereco: Valor do input de endereço (sincronizado com o estado do usuário).
    - papel: Valor do select de papel (sincronizado com o estado do usuário).
- A página exibe diferentes conteúdos dependendo do estado:
"Carregando..." se user for nulo; Formulário de edição se isEditing for verdadeiro; Informações do usuário e botão "Editar Perfil" se isEditing for falso.
- **handleEdit para salvar alterações:** Envia uma requisição PUT para /pessoas/{id} com os dados editados.
- **Botões "Sair" e "Voltar":** O botão "Sair" remove o ID do usuário do `localStorage` e redireciona para /login. O botão "Voltar" redireciona para /busca.
- PessoaController.java:
- **@GetMapping("{id}"):** Mapeia requisições GET para /pessoas/{id}. Retorna os dados do usuário correspondente ao ID.
- **@PutMapping("{id}"):** Mapeia requisições PUT para /pessoas/{id}. Recebe os dados editados no corpo da requisição (@RequestBody). Atualiza o endereço e o papel do usuário e salva as alterações no banco de dados.
- Pessoa.java (Entidade): Representa a entidade Pessoa com atributos como id, nome, email, senha, endereco e papel, e os métodos getters e setters permitem acessar e modificar os atributos.
