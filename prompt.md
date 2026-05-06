
### 🎯 Prompt melhorado (UX/UI + Swing moderno)

Agora quero que você atue como um **especialista em UX/UI e design de interfaces desktop**.

Com base no projeto já estruturado (Java + Maven + Swing + MDI), quero que você **refatore completamente a interface gráfica**, mantendo a arquitetura existente.

---

## 🎨 Objetivo

Transformar a interface atual em algo:

* Visualmente **moderno e profissional**
* Com **boa usabilidade (UX)**
* Próximo de sistemas atuais (mesmo usando Swing)

---

## 🧩 Restrições importantes

* Manter o padrão **MDI (JFrame + JDesktopPane + JInternalFrame)**
* NÃO quebrar a arquitetura (DDD, camadas, etc)
* NÃO mover lógica de negócio para a UI

---

## 🎯 Melhorias esperadas

### 1. Visual moderno (Swing não padrão)

Você pode (e deve) melhorar o visual usando:

* Bibliotecas como:

  * FlatLaf (preferencial)
  * MigLayout (para layout mais flexível)

* Aplicar:

  * Tema consistente (dark ou light — escolha um)
  * Cores modernas (ex: tons neutros + cor de destaque)
  * Tipografia melhorada (fontes mais agradáveis)

---

### 2. Responsividade (dentro do Swing)

Mesmo sendo desktop:

* Inputs devem:

  * Expandir corretamente
  * Não quebrar layout ao redimensionar
* Usar:

  * Layouts adequados (evitar AbsoluteLayout)
  * Preferência por:

    * `MigLayout`
    * `GridBagLayout` (se bem aplicado)

---

### 3. Padronização de telas

Todas as telas devem seguir um padrão visual:

* Título da tela
* Espaçamento consistente
* Campos alinhados
* Botões com hierarquia clara:

  * Primário (ação principal)
  * Secundário

---

### 4. Componentização

* Criar componentes reutilizáveis:

  * Input padrão
  * Botão padrão
  * Painel base
* Evitar duplicação de código de UI

---

### 5. Feedback ao usuário

* Mensagens claras (erro/sucesso)
* Validação visual:

  * Campo inválido destacado
* Evitar apenas `JOptionPane` — usar também feedback inline

---

### 6. Navegação MDI melhorada

* Melhorar experiência com múltiplas janelas:

  * Evitar abrir duplicadas
  * Focar janela já aberta
* Opcional:

  * Menu com ícones
  * Organização mais limpa

---

### 7. Microinterações (dentro do possível no Swing)

* Hover em botões
* Mudança de cor ao focar input
* Estados visuais (disabled, loading simples)

---

## ⚙️ Liberdade técnica

Você pode:

* Adicionar dependências Maven
* Criar novas classes de UI
* Refatorar completamente a camada `presentation`

---

# ✅ CRITÉRIOS DE ACEITE

### ✔ Visual

* [ ] Interface não parece padrão “Swing antigo”
* [ ] Tema consistente aplicado em todas as telas
* [ ] Cores e fontes harmonizadas

---

### ✔ UX

* [ ] Layout não quebra ao redimensionar
* [ ] Inputs e botões bem alinhados
* [ ] Fluxo de uso claro e intuitivo

---

### ✔ Código

* [ ] Componentes reutilizáveis criados
* [ ] Sem duplicação de UI
* [ ] Código organizado e legível

---

### ✔ MDI

* [ ] Continua funcionando corretamente
* [ ] Janelas internas bem gerenciadas
* [ ] Sem múltiplas instâncias desnecessárias

---

### ✔ Integração

* [ ] UI continua desacoplada da regra de negócio
* [ ] Usa corretamente os services da aplicação

