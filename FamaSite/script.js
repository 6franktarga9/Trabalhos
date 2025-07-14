document.addEventListener("DOMContentLoaded", () => {
    // Código do dropdown
    const dropdownButtons = document.querySelectorAll(".dropdown-btn");

    dropdownButtons.forEach((button) => {
        button.addEventListener("click", (event) => {
            event.stopPropagation(); // Impede que o clique no botão feche o menu imediatamente

            const dropdownContent = button.nextElementSibling;

            // Fecha todos os menus suspensos, exceto o atual
            document.querySelectorAll(".dropdown-content").forEach((menu) => {
                if (menu !== dropdownContent) {
                    menu.classList.remove("show"); // Fecha o menu
                    const arrow = menu.previousElementSibling.querySelector("span");
                    arrow.classList.remove("rotate"); // Remove a rotação da seta
                }
            });

            // Alterna a visibilidade do menu atual
            dropdownContent.classList.toggle("show");

            // Alterna a rotação da seta
            const arrow = button.querySelector("span");
            arrow.classList.toggle("rotate");
        });
    });

    // Fecha os menus suspensos ao clicar fora
    document.addEventListener("click", () => {
        document.querySelectorAll(".dropdown-content").forEach((menu) => {
            menu.classList.remove("show"); // Fecha todos os menus
        });

        // Remove a rotação das setas
        dropdownButtons.forEach((button) => {
            const arrow = button.querySelector("span");
            arrow.classList.remove("rotate");
        });
    });
    
    // Código do Carrossel
    const prevButton = document.querySelector(".carousel-btn.prev");
    const nextButton = document.querySelector(".carousel-btn.next");
    const slides = document.querySelectorAll(".carousel-slide");
    let currentIndex = 0;

    // Função para mostrar o slide atual
    function showSlide(index) {
        // Certifica-se de que o índice está dentro do intervalo de slides
        if (index >= slides.length) {
            currentIndex = 0;
        } else if (index < 0) {
            currentIndex = slides.length - 1;
        } else {
            currentIndex = index;
        }

        // Faz todos os slides invisíveis
        slides.forEach((slide) => {
            slide.style.opacity = "0";
        });

        // Torna o slide atual visível
        slides[currentIndex].style.opacity = "1";
    }

    // Evento para o botão "Anterior"
    prevButton.addEventListener("click", () => {
        showSlide(currentIndex - 1);
    });

    // Evento para o botão "Próximo"
    nextButton.addEventListener("click", () => {
        showSlide(currentIndex + 1);
    });

    // Inicializa o carrossel mostrando o primeiro slide
    showSlide(currentIndex);
});
