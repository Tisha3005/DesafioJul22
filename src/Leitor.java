import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Leitor {

	public static void main(String[] args) throws FileNotFoundException {
		lerTexto("alunos.txt");
		System.out.println();
		System.out.println("FINAL");

	}// FECHA O MAIN

	public static void lerTexto(String nomeArquivo) throws FileNotFoundException {
		//TRATAMENTO DE ERROS + BUSCA PELO ARQUIVO ALUNOS.TXT + LEITURA ARQUIVO
		try {
			File arquivo = new File(nomeArquivo);
			Scanner linhaScanner = new Scanner(new FileInputStream(arquivo));// funcao de ler linha a linha

			//DECLARANDO VARIAVEIS
			// LINHA
			int qtdeLinhas = 0;
			int maiorLinha = 0;

			// PALAVRA
			int qtdePalavras = 0;
			String maiorPalavra = "";
			int qtdePalavrasMaiorLinha = 0;
			int qtdeCaracteresMaiorLinha = 0;

			ArrayList<String> novaPalvra = new ArrayList<>();
			ArrayList<String> novoArquivoSaida = new ArrayList<>();

			PrintStream arquivoOut;
			boolean primeiraLinha = true;

			//COMANDO DE REPETICAO + CONDICAO
			while (linhaScanner.hasNextLine()) {// ENQUANTO FOR VERDADE REPETE - ainda tem uma proxima linha a ser lida?
				String linhaNova = "";
				String linha = linhaScanner.nextLine();// proxima linha
				String[] newStr = linha.split("\\s+");

				//ORGANIZAR AS LETRAS + EXCECAO 1 DO DESAFIO
				for (int i = 0; i < newStr.length; i++) {
					if(!newStr[i].isEmpty()){//AQUI EU NEGO O RETORNO QDO ESTIVER VAZIO ENTRE NOME SOBRENOME
						System.out.println("Essa palavra: " + newStr[i] + " deve ficar em MAIÚSCULO? ");
						Scanner recebe = new Scanner(System.in);
						String armazena = recebe.nextLine().toLowerCase();

						if ("sim".equals(armazena)) {
							linhaNova = linhaNova + " " + newStr[i].toUpperCase();
						} else{
							linhaNova = linhaNova + " " + newStr[i].toString().substring(0, 1).toUpperCase()
									+ newStr[i].toString().substring(1).toLowerCase();
						}
						novaPalvra.add(newStr[i]);
					}
				}

				//RETIRAR A PRIMEIRA LINHA VAZIA DO ARQUIVO DE SAIDA
				if (!primeiraLinha) {
					novoArquivoSaida.add("\n");
				} else {
					primeiraLinha = false;
				}

				if (maiorLinha <= newStr.length) {
					maiorLinha = qtdeLinhas;
					qtdePalavrasMaiorLinha = newStr.length;
					qtdeCaracteresMaiorLinha = linha.length();
				}

				//NA SAIDA DE INFORMACOES ESTAVA IMPRIMINDO UMA SOH LINHA
				novoArquivoSaida.add(linhaNova);
				qtdeLinhas = qtdeLinhas + 1;
				qtdePalavras = qtdePalavras + newStr.length;


			} // FIM DO WHILE

			//ENCONTRANDO A MAIOR PALAVRA
			for (int i = 0; i < novaPalvra.size(); i++) {
				if (maiorPalavra.length() < novaPalvra.get(i).length()) {
					maiorPalavra = novaPalvra.get(i).toString();// PODERIA USAR Math.max(maiorPalvra, novaPalavra)
				}
			}

			// SAIDA DE DADOS PARA ARQUIVO
			arquivoOut = new PrintStream("alunossaida.txt");
			arquivoOut.print(novoArquivoSaida.toString().replace("[", "").replace("]", "").replace(",", "").trim());

			//SAIDA DE INFORMACAO CONSOLE
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("** DESAFIO DE JULHO - VAI NA WEB / PAGSEGURO**");
			System.out.println();
			System.out.println();
			System.out.println("Fa�a um programa que leia um arquivo texto e imprima no terminal os seguintes dados:"
					.toUpperCase());
			System.out.println();
			System.out.println();
			System.out.println("1) Quantas linhas o arquivo tem: " + qtdeLinhas + " ln");
			System.out.println();
			System.out.println("2) Quantas palavras o arquivo tem: " + qtdePalavras + " plv");
			System.out.println();
			System.out.println("3A) Qual a maior linha do arquivo: " + maiorLinha);
			System.out.println();
			System.out.println("3B) Quantas palavras a maior linha do arquivo tem: " + qtdePalavrasMaiorLinha);
			System.out.println();
			System.out.println("4) Quantos caracteres a maior linha do arquivo tem: " + qtdeCaracteresMaiorLinha);
			System.out.println();
			System.out.println("5A) Qual a maior palavra: " + maiorPalavra.toUpperCase());
			System.out.println();
			System.out.println("5B) Quantos caracteres a maior palavra do arquivo tem: " + maiorPalavra.length());
			System.out.println();
			System.out.println("-------------------------------------------------------------------------------");

			linhaScanner.close();// FINALIZANDO DE LER ARQUIVO

			System.out.println("FINALIZANDO DE LER ARQUIVO");

		} // FECHA TRY



		//TRATAMENTO DE ERROS - CASO NAO ENCONTRE O ARQUIVO
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ARQUIVO NAO ENCONTRADO");
		}
	}// FECHA VOID
}// FECHA A CLASSE