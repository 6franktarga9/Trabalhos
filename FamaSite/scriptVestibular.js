document.addEventListener("DOMContentLoaded", () => {
    // Código para mostrar ou esconder a área de horário para ligação
    const podeLigarSelect = document.getElementById("podeLigar");
    const horaLigacaoDiv = document.getElementById("horaLigacao");

    podeLigarSelect.addEventListener("change", () => {
        if (podeLigarSelect.value === "sim") {
            horaLigacaoDiv.style.display = "block"; // Exibe a área para escolher horário
        } else {
            horaLigacaoDiv.style.display = "none"; // Esconde a área
        }
    });

    // Validação básica do e-mail
    function validarEmail(email) {
        const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return regex.test(email);
    }

    // Evento de envio do formulário (pode ser adaptado para enviar para um servidor)
    const formVestibular = document.getElementById("formVestibular");
    formVestibular.addEventListener("submit", (event) => {
        event.preventDefault(); // Impede o envio do formulário (pode ser removido em produção)

        const emailInput = document.getElementById('email');
        if (!validarEmail(emailInput.value)) {
            emailInput.style.border = '1px solid red'; // Altera a borda para vermelho
            emailInput.placeholder = 'E-mail inválido.'; // Altera o placeholder
            emailInput.value = ''; // Limpa o valor atual
            return;
        }

        // Restaura o estilo e placeholder originais se o e-mail for válido
        emailInput.style.border = '1px solid #ccc'; // Restaura a borda
        emailInput.placeholder = 'E-mail'; // Restaura o placeholder

        // Mostra a mensagem de sucesso
        const mensagemSucesso = document.getElementById('mensagemSucesso');
        mensagemSucesso.style.display = 'block'; // Mostra a mensagem
        setTimeout(() => {
            mensagemSucesso.style.display = 'none'; // Esconde a mensagem após alguns segundos
        }, 3000);

        // Aqui, você pode adicionar a lógica para processar os dados (por exemplo, enviar para um servidor)
    });

    // Função para formatar o telefone
    function formatarTelefone(input) {
        let valor = input.value.replace(/\D/g, ''); // Remove qualquer caractere não numérico
        if (valor.length <= 2) {
            input.value = `(${valor}`;
        } else if (valor.length <= 6) {
            input.value = `(${valor.substring(0, 2)}) ${valor.substring(2)}`;
        } else {
            input.value = `(${valor.substring(0, 2)}) ${valor.substring(2, 7)}-${valor.substring(7, 11)}`;
        }
    }

    // Adiciona o evento de digitação no campo de telefone
    const telefoneInput = document.getElementById('telefone');
    if (telefoneInput) {
        telefoneInput.addEventListener('input', function() {
            formatarTelefone(this);
        });
    }
});
