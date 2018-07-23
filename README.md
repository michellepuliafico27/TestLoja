Observações do desafio proposto:

O principal percalço na criação dos testes foi o captcha, que aparece eventualmente e impede de prosseguir com a busca após a criação do cliente.
Para resolver essa questão dividi o desafio em testes de funcionalidade e verificação de campos.

Testes criados.
Positivos:
successfullRegister
Testa se é possível criar um cliente para primeira compra com sucesso.

checkValueAndConditions
Testa se a soma de dois produtos específicos é menor do que R$5000,00 e se as condições preveem 10x sem juros.

invalidCPF
Verifica se a mensagem de erro é exibida ao inserir um CPF inválido.

emailAlreadyRegistered
Verifica se a mensagem de erro é exibida ao inserir um email existente.

shortPassword
Verifica se a mensagem de erro é exibida ao inserir uma senha muito curta.

Requisitos para execução dos testes.
É necessária a plataforma Maven. Certifique-se se possui o gecko corretamente instalado na sua máquina.


Passo a passo para execução dos testes:

    1-) Troque o email e o cpf por novos nas linhas 85 e 87 da classe UserRegisterTests.java;
    2-) Execute o comando 'mvn clean surefire-report:report' no diretório raíz do projeto.

O relatório estará disponível em ./target/site/surefire-report.html
