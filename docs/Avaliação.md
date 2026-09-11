Cada aluno deverá desenvolver **individualmente** um **aplicativo Android** com uma proposta coerente, cujas funcionalidades estejam relacionadas a um mesmo problema ou tema.

## Parcial: Android Views (XML) e navegação + intent

### Requisitos obrigatórios
O aplicativo deve:
- Implementar duas telas em Android Views com layouts XML, usando Views (i.e. `TextView`, `ImageView`, `Space`) e ViewGroups (i.e. `LinearLayout`, `FrameLayout`, `RecyclerView`) adequados ao fluxo do aplicativo.
- Implementar a navegação entre as duas telas por meio de `Intent` explícita, com passagem de dados quando aplicável.
- Conectar as Views ao Kotlin por `ViewBinding` ou `findViewById`, tratando pelo menos uma interação que atualize a interface ou avance o fluxo.
- Representar os dados exibidos com modelos imutáveis (`data class`) e tratar corretamente valores opcionais.
- Utilizar dados simulados (_mocks_), sem necessidade de API ou banco de dados nesta etapa.
### Itens opcionais
- Utilizar apenas ViewBinding ao invés de `findViewById`
- Criar componentes XML reutilizáveis, inflá-los quando necessário e tratar eventos que atualizem a interface
- Implementar uma interface funcional em `Fragment`, utilizando seu ciclo de vida e ViewBinding

## Etapa 2: Jetpack Compose

### Requisitos obrigatórios
- Implementar telas e componentes que sejam coerentes com o contexto do aplicativo, sem criar telas artificiais apenas para cumprir requisitos
- Aplicar requisitos básicos de acessibilidade, como contraste, descrição de elementos, tamanho das áreas de toque e suporte a textos maiores
- Utilizar Navigation 3, com rotas, entries, back stack e passagem de argumentos quando necessário
- Possuir pelo menos uma lista implementada com `LazyColumn` ou `LazyRow`
- Possuir pelo menos um formulário com validação e feedback adequado ao usuário
- Utilizar `ViewModel`, `UiState`/`StateFlow`, `collectAsStateWithLifecycle` e fluxo unidirecional de dados
- Apresentar estados visualmente distintos de carregamento, conteúdo, lista vazia e erro, preservando adequadamente o estado relevante após rotação ou recriação da tela
- Utilizar `Repository` para a camada de dados
- Executar pelo menos uma operação assíncrona relevante ao fluxo do aplicativo, usando coroutines, `scope` apropriado e cancelamento adequado
- Persistir dados relevantes do aplicativo usando pelo menos uma solução local adequada — como Room, DataStore, ObjectBox ou arquivos — com operações coerentes com o fluxo do aplicativo e exposição reativa dos dados quando aplicável
- Nenhum dado sensível (`secrets`), como chave de API ou senha de banco de dados, deve estar no código versionado e enviado ao GitHub

As funcionalidades devem compor pelo menos um fluxo completo. Exemplos de fluxo completo: consultar dados remotos -> apresentar o resultado -> acessar os detalhes de um item -> registrar ou alterar informações locais -> manter essas informações após reiniciar o aplicativo.

### Itens opcionais
- Mapear os dados remotos entre DTOs, modelos de domínio e modelos apresentados pela interface quando essa separação contribuir para a clareza e a manutenção do aplicativo
- Implementar testes automatizados relevantes, como testes de regra de negócio, `ViewModel` e de interface em Compose, contemplando componentes reutilizáveis e os fluxos principais do aplicativo
- Adotar boas práticas de código seguro, como validar entradas e não registrar dados sensíveis em logs (i.e. Logcat)
- Estratégia de cache de dados remotos combinando persistência local e API externa, com uma política definida de atualização ou expiração dos dados
- Funcionamento offline-first, permitindo utilizar funcionalidades centrais com os dados locais enquanto a conexão estiver indisponível
- Animações implícitas que contribuam para a compreensão das mudanças de estado
- Adaptar-se adequadamente a diferentes tamanhos de tela, sem cortes ou sobreposições de conteúdo
- Aplicar injeção manual de dependências, interfaces de `Repository` e fakes para fontes locais, remotas ou repositories em testes
- Sincronizar alterações locais com a fonte remota em segundo plano usando WorkManager, registrando operações pendentes e tratando restrição de rede, retry ou backoff, conflitos e idempotência quando aplicável
- Integração com recurso do celular como: Photo Picker, câmera ou compartilhamento; deep link; notificação com ação

# Condições de entrega e execução
- O projeto deve compilar e executar sem alterações no código por parte do professor.
- Apenas esses itens serão aceitos na entrega do projeto:
	- O link do repositório no GitHub.
	- Se o projeto usar chaves/senhas, o arquivo `.env` (ou equivalente) enviado separadamente — nunca publicado no GitHub.
- Recomenda-se que a entrega seja feita até uma aula antes do prazo final. Assim, caso eu não consiga rodar o projeto localmente, conseguiremos ver juntos durante a(s) aula(s)
- Caso eu não consiga rodar o projeto, será atribuída nota zero ao projeto
- Por isso, o README.md desse repositório deve conter: 
	- Instruções de como rodar o projeto localmente
	- Uma breve descrição para cada biblioteca externa utilizada (se for o caso)
	- Breve descrição do objetivo do aplicativo
- O estudante deverá ser capaz de explicar as decisões técnicas, a arquitetura e o funcionamento do código entregue.
