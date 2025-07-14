const buscarFilmes = (genero) => {
    const tmdbApiKey = "95d8dce72b5835a6cde9ffa978d242ce";
    const url = `https://api.themoviedb.org/3/discover/movie?api_key=${tmdbApiKey}&with_genres=${genero}&language=pt-BR`;

    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            mostrarFilmes(data.results);
        })
        .catch((error) => console.error("Erro ao buscar filmes:", error));
};

// Função para exibir os filmes no HTML
const mostrarFilmes = (filmes) => {
    const container = document.querySelector(".movie-container");
    container.innerHTML = ""; // Limpar resultados anteriores

    filmes.forEach((filme) => {
        const div = document.createElement("div");
        div.className = "movie";
        div.innerHTML = `
            <h2>${filme.title}</h2>
            <img src="https://image.tmdb.org/t/p/w200${filme.poster_path}" alt="${filme.title}">
        `;
        container.appendChild(div);
    });
};

// Funções de navegação do carrossel
let currentIndex = 0;

const showMovies = () => {
    const container = document.querySelector(".movie-container");
    const totalMovies = container.children.length;
    const offset = -currentIndex * 230; // Ajuste o valor conforme necessário para o espaçamento
    container.style.transform = `translateX(${offset}px)`;
};

document.querySelector(".next").onclick = () => {
    const container = document.querySelector(".movie-container");
    const totalMovies = container.children.length;
    if (currentIndex < totalMovies - 1) {
        currentIndex++;
        showMovies();
    }
};

document.querySelector(".prev").onclick = () => {
    if (currentIndex > 0) {
        currentIndex--;
        showMovies();
    }
};

const generos = {
    acao: 28,
    ficcao: 878,
    drama: 18,
    romance: 10749,
    terror: 27,
};

document.getElementById("acao").onclick = () => buscarFilmes(generos.acao);
document.getElementById("ficcao").onclick = () => buscarFilmes(generos.ficcao);
document.getElementById("drama").onclick = () => buscarFilmes(generos.drama);
document.getElementById("romance").onclick = () => buscarFilmes(generos.romance);
document.getElementById("terror").onclick = () => buscarFilmes(generos.terror);
