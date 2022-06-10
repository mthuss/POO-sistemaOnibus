//Feito por Bruno Silveira e Matheus Moriel 👍
import java.util.Scanner;
import java.util.ArrayList;


public class Main {


	//Inicio da main
	public static void main(String[] args) {
		int opt;

		Sistema registro = new Sistema();

		do  { 
			System.out.println("\nDigite o tipo de usuário:");
			System.out.println("1- Passageiro");
			System.out.println("2- Administrador");
			System.out.println("3- Sair");
			System.out.print("\nSeleção: ");
			Scanner sc = new Scanner(System.in);
			opt = sc.nextInt();
			switch(opt)
			{
				case 1: Sistema.menuUsuario(); break;
				case 2: Sistema.menuADM(); break;
				case 3: break;
			}
			if(opt < 1 || opt > 3)
				System.out.println("Opção inválida!");
		}while(opt != 3);


		System.out.println("Obrigado por utilizar :)");
	}
}
