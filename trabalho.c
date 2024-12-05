#include <stdlib.h>
#include <string.h>
#include <stdio.h>

typedef struct info_animal {
    char codigo[10];
    char nome[50];
    char especie[50];
    float peso;
    float altura;
} Animal;

int MENU() {
    int x;
    printf("1- Inicializacao do sistema\n");
    printf("2- Catalogo de setores\n");
    printf("3- Inserir animais em uma jaula de um setor\n");
    printf("4- Animal mais pesado de um dado setor\n");
    printf("5- Inserir novo setor\n");
    printf("6- Remover setor\n");
    printf("7- Mostrar Zoologico\n");
    printf("8- Sair do programa\n");
    scanf("%d", &x);
    return x;
}

int main() {
    int escolha;
    int Setores, NumeroDeJaulas, AnimaisPorJaula;
    Animal ***matriz;
    char **NomeSetores;

    do {
        escolha = MENU();
        switch (escolha) {
            case 1:
                printf("Inicializacao do sistema\n");

                printf("Numero de setores: ");
                scanf("%d", &Setores);

                printf("Numero de jaulas: ");
                scanf("%d", &NumeroDeJaulas);

                printf("Numero de animais por jaula: ");
                scanf("%d", &AnimaisPorJaula);

                matriz = (Animal ***)malloc(Setores * sizeof(Animal **));
                for (int i = 0; i < Setores; i++) {
                    matriz[i] = (Animal **)malloc(NumeroDeJaulas * sizeof(Animal *));
                    for (int j = 0; j < NumeroDeJaulas; j++) {
                        matriz[i][j] = (Animal *)malloc(AnimaisPorJaula * sizeof(Animal));
                    }
                }
                break;

            case 2:
                printf("Catalogo de setores\n");
                NomeSetores = (char **)malloc(Setores * sizeof(char *));
                for (int i = 0; i < Setores; i++) {
                    char auxNomeSetor[50];
                    printf("Digite o nome do setor %d: ", i + 1);
                    scanf("%s", auxNomeSetor);

                    NomeSetores[i] = (char *)malloc((strlen(auxNomeSetor) + 1) * sizeof(char));
                    strcpy(NomeSetores[i], auxNomeSetor);
                }
                break;

            case 3:
                printf("Inserir animais em uma jaula de um setor\n");

                int NumeroSetor, NumeroJaula;

                printf("Quantos setores deseja inserir animais?: ");
                scanf("%d", &NumeroSetor);

                for (int k = 0; k < NumeroSetor; k++) {
                    printf("Insira o numero do setor para inserir animais (1 a %d): ", Setores);
                    scanf("%d", &NumeroSetor);
                    NumeroSetor--;

                    printf("Insira o numero da jaula do setor %s onde o animal sera inserido (1 a %d): ", NomeSetores[NumeroSetor], NumeroDeJaulas);
                    scanf("%d", &NumeroJaula);
                    NumeroJaula--;

                    while (NumeroJaula >= NumeroDeJaulas){
                        printf("Insira um numero de jaula valido: ");
                        scanf("%d", &NumeroJaula);
                    }

                    // Aloca memória para armazenar os animais na jaula selecionada
                    matriz[NumeroSetor][NumeroJaula] = (Animal *)malloc(AnimaisPorJaula * sizeof(Animal));

                    for (int i = 0; i < AnimaisPorJaula; i++) {
                        // Aloca memória para cada animal na jaula
                        printf("Insira o Codigo do animal %d da jaula %d do setor %s: ", i + 1, NumeroJaula + 1, NomeSetores[NumeroSetor]);
                        scanf("%s", matriz[NumeroSetor][NumeroJaula][i].codigo);

                        printf("Insira a especie do animal %d da jaula %d do setor %s: ", i + 1, NumeroJaula + 1, NomeSetores[NumeroSetor]);
                        scanf("%s", matriz[NumeroSetor][NumeroJaula][i].especie);

                        printf("Insira o nome do animal %d da jaula %d do setor %s: ", i + 1, NumeroJaula + 1, NomeSetores[NumeroSetor]);
                        scanf("%s", matriz[NumeroSetor][NumeroJaula][i].nome);

                        printf("Insira o peso do animal %d da jaula %d do setor %s: ", i + 1, NumeroJaula + 1, NomeSetores[NumeroSetor]);
                        scanf("%f", &matriz[NumeroSetor][NumeroJaula][i].peso);

                        printf("Insira a altura do animal %d da jaula %d do setor %s: ", i + 1, NumeroJaula + 1, NomeSetores[NumeroSetor]);
                        scanf("%f", &matriz[NumeroSetor][NumeroJaula][i].altura);
                    }
                }
                break;

            case 4:
                printf("Animal mais pesado de um dado setor\n");

                int NumeroSetor1;
                float *MaisPesado;
                int Contador = 0;

                MaisPesado = (float *)malloc(sizeof(float) * AnimaisPorJaula);

                printf("Insira o numero do setor desejado: ");

                for (int i = 0; i < Setores; i++) {
                    printf("%d: %s\n", i + 1, NomeSetores[i]);
                }

                scanf("%d", &NumeroSetor1);
                NumeroSetor1--;

                for (int i = 0; i < AnimaisPorJaula; i++) {
                    MaisPesado[i] = 0.0;
                }

                for (int i = 0; i < NumeroDeJaulas; i++) {
                    for (int j = 0; j < AnimaisPorJaula; j++) {
                        if (MaisPesado[j] < matriz[NumeroSetor1][i][j].peso) {
                            MaisPesado[j] = matriz[NumeroSetor1][i][j].peso;
                        }
                    }
                }

                float pesoMaximo = 0.0;
                int indiceAnimalMaisPesado = 0;
                for (int i = 0; i < AnimaisPorJaula; i++) {
                    if (MaisPesado[i] > pesoMaximo) {
                        pesoMaximo = MaisPesado[i];
                        indiceAnimalMaisPesado = i;
                    }
                }

                printf("O animal mais pesado do setor %s: %s\n", NomeSetores[NumeroSetor], matriz[NumeroSetor][indiceAnimalMaisPesado]->nome);

                free(MaisPesado);
                break;

            case 5:
                printf("Inserir novo setor\n");

                Setores++;

                NomeSetores = (char **)realloc(NomeSetores, Setores * sizeof(char *));
                char auxNomeSetor[50];
                printf("Digite o nome do novo setor: ");
                scanf("%s", auxNomeSetor);
                char *PonteiroNomeSetor = (char *)malloc((strlen(auxNomeSetor) + 1) * sizeof(char));
                strcpy(PonteiroNomeSetor, auxNomeSetor);

                NomeSetores[Setores - 1] = PonteiroNomeSetor;

                matriz = (Animal ***)realloc(matriz, Setores * sizeof(Animal **));
                matriz[Setores - 1] = (Animal **)malloc(NumeroDeJaulas * sizeof(Animal *));

                for (int i = 0; i < NumeroDeJaulas; i++) {
                    matriz[Setores - 1][i] = (Animal *)malloc(AnimaisPorJaula * sizeof(Animal));
                }

                for (int i = 0; i < NumeroDeJaulas; i++) {
                    for (int j = 0; j < AnimaisPorJaula; j++) {
                        printf("Insira o Codigo do animal %d da jaula %d no novo setor: ", j + 1, i + 1);
                        scanf("%s", matriz[Setores - 1][i][j].codigo);

                        printf("Insira a especie do animal %d da jaula %d no novo setor: ", j + 1, i + 1);
                        scanf("%s", matriz[Setores - 1][i][j].especie);

                        printf("Insira o nome do animal %d da jaula %d no novo setor: ", j + 1, i + 1);
                        scanf("%s", matriz[Setores - 1][i][j].nome);

                        printf("Insira o peso do animal %d da jaula %d no novo setor: ", j + 1, i + 1);
                        scanf("%f", &matriz[Setores - 1][i][j].peso);

                        printf("Insira a altura do animal %d da jaula %d no novo setor: ", j + 1, i + 1);
                        scanf("%f", &matriz[Setores - 1][i][j].altura);
                    }
                }

                printf("Novo setor inserido com sucesso!\n");
                break;

            case 6:
                printf("Remover setor\n");
                if (Setores > 0) {
                    int setorRemover;
                    printf("Digite o numero do setor a ser removido: ");
                    scanf("%d", &setorRemover);
                    if (setorRemover > 0 && setorRemover <= Setores) {
                        free(NomeSetores[setorRemover - 1]);
                        free(matriz[setorRemover - 1]);
                        for (int i = setorRemover - 1; i < Setores - 1; i++) {
                            NomeSetores[i] = NomeSetores[i + 1];
                            matriz[i] = matriz[i + 1];
                        }
                        Setores--;
                        matriz = realloc(matriz, Setores * sizeof(Animal **));
                        printf("Setor removido com sucesso.\n");
                    } else {
                        printf("Setor invalido.\n");
                    }
                } else {
                    printf("Nenhum setor para remover.\n");
                }
                break;

            case 7:
                printf("Mostrar Zoologico:\n");
                for (int i = 0; i < Setores; i++) {
                    printf("Setor: %s\n", NomeSetores[i]);
                    for (int j = 0; j < NumeroDeJaulas; j++) {
                        printf("  Jaula %d:\n", j + 1);
                        for (int k = 0; k < AnimaisPorJaula; k++) {
                            printf("    Animal %d:\n", k + 1);
                            printf("      Codigo: %s\n", matriz[i][j][k].codigo);
                            printf("      Especie: %s\n", matriz[i][j][k].especie);
                            printf("      Nome: %s\n", matriz[i][j][k].nome);
                            printf("      Peso: %.2f\n", matriz[i][j][k].peso);
                            printf("      Altura: %.2f\n", matriz[i][j][k].altura);
                        }
                    }
                }
                break;

            case 8:
                printf("Saindo do programa\n");
                break;

            default:
                printf("Opcao invalida\n");
                break;
        }
    } while (escolha != 8);
    
    //Liberando memoria
    
    for (int i = 0; i < Setores; i++) {
        for (int j = 0; j < NumeroDeJaulas; j++) {
            free(matriz[i][j]);
        }
            free(matriz[i]);
    }
    free(matriz);
    
    for (int i = 0; i < Setores; i++) {
        free(NomeSetores[i]);
    }
    free(NomeSetores);
    
    for (int i = 0; i < Setores; i++) {
        for (int j = 0; j < NumeroDeJaulas; j++) {
            free(matriz[i][j]);
        }
        free(matriz[i]);
    }
    free(matriz);
    
    for (int i = 0; i < NumeroDeJaulas; i++) {
        free(matriz[Setores - 1][i]);
    }
    free(matriz[Setores - 1]);
    free(matriz);
    free(NomeSetores);
    
    free(NomeSetores[Setores - 1]);
    free(matriz[Setores - 1]);
    Setores--;
    matriz = realloc(matriz, Setores * sizeof(Animal **));

    for (int i = 0; i < Setores; i++) {
        for (int j = 0; j < NumeroDeJaulas; j++) {
            free(matriz[i][j]);
    }
    free(matriz[i]);
    }
    free(matriz);
    
    for (int i = 0; i < Setores; i++) {
        free(NomeSetores[i]);
    }
    free(NomeSetores);
    

    return 0;
}
