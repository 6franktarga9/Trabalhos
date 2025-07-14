const buscarSeries = (genero) => {
    const tmdbApiKey = "95d8dce72b5835a6cde9ffa978d242ce";
    const url = `https://api.themoviedb.org/3/discover/tv?api_key=${tmdbApiKey}&with_genres=${genero}&language=pt-BR`;

    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            mostrarSeries(data.results);
        })
        .catch((error) => console.error("Erro ao buscar séries:", error));
};

// Função para exibir as séries no HTML
const mostrarSeries = (series) => {
    const output = document.getElementById("output");
    output.innerHTML = ""; // Limpar resultados anteriores

    series.forEach((serie) => {
        const div = document.createElement("div");
        div.className = "serie";
        div.innerHTML = `
            <h2>${serie.name}</h2>
            <img src="https://image.tmdb.org/t/p/w200${serie.poster_path}" alt="${serie.name}">
        `;
        output.appendChild(div);
    });
};

// Mapeamento de gêneros para séries
const generosSeries = {
    acao: 10759, // Ação e Aventura
    ficcao: 10765, // Ficção Científica e Fantasia
    drama: 18, // Drama
    romance: 10749, // Romance
    terror: 9648, // Mistério e Suspense
};

// Associando os botões a gêneros de séries
document.getElementById("acao").onclick = () => buscarSeries(generosSeries.acao);
document.getElementById("ficcao").onclick = () => buscarSeries(generosSeries.ficcao);
document.getElementById("drama").onclick = () => buscarSeries(generosSeries.drama);
document.getElementById("romance").onclick = () => buscarSeries(generosSeries.romance);
document.getElementById("terror").onclick = () => buscarSeries(generosSeries.terror);
