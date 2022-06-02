import java.util.Scanner;
import java.util.ArrayList;


public class Main {

	public static ArrayList<Passageiro> passageiros = new ArrayList<>();
	public static ArrayList<Rotas> rotas = new ArrayList<>();

	public static void cadastrarUsuario()
	{
		Passageiro usuario = new Passageiro();
		usuario.dadosPassageiro();
		passageiros.add(usuario);
	}

	public static void removerUsuario() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o CPF de quem deseja remover: ");
		long CPF = sc.nextLong(); 

		boolean encontrado=false;
		for(int i = 0; i < passageiros.size(); i++)
		{
			
			if(passageiros.get(i).getDocumento() == CPF) {
				encontrado = true;
				passageiros.remove(passageiros.get(i));
			}
		}
		if(encontrado)
			System.out.println("Usuário removido com sucesso!");
		else
			System.out.println("Usuário NÃO removido com sucesso!");
	}
	
	public static void reservarPassagem()
	{
		String sn = "n"; //prompt de confirmação da linha
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite seu CPF: ");
		long CPF = sc.nextLong();	
		boolean encontrado=false;

		//Verifica se o CPF digitado está, de fato, cadastrado
		//e reserva a linha pedida
		for(int i = 0; i < passageiros.size(); i++)
			if(passageiros.get(i).getDocumento() == CPF)
			{
				System.out.println("Encontrado!");
				encontrado = true;
				Passageiro pessoa = passageiros.get(i); 
				System.out.println(pessoa.getNome());
				int numlinha;	

				do{  //laço de verificação "deseja reservar MESMO essa linha?"
					System.out.println("Deseja reservar qual ônibus?\nDigite o número da linha:");
					numlinha = sc.nextInt(); //Lê o numero da linha a ser reservada
					for(int j = 0; j < rotas.size(); j++)
						if(rotas.get(j).getIDRota() == numlinha)
						{
							Onibus bus = rotas.get(j).getOnibus();
							System.out.println("Linha encontrada");

							System.out.print("Deseja reservar para " + numlinha + "-" + rotas.get(j).getDestino() + "\n[S/N]: ");
							sc.nextLine();
							sn = sc.nextLine();

							if (sn.contains("s") || sn.contains("S"))  { 
								bus.reservarAssento();
								pessoa.setLinha(numlinha);
							}

							break;
						}
				}  while (sn.contains("n") || sn.contains("N"));  
			}

		if(!encontrado)
			System.out.println("CPF não cadastrado!");


	}

	/*	public void reservarPassagem(long CPF)
		{
		Scanner sc = new Scanner(System.in);
		}
	 */	
	public static void menuUsuario()  {
		int opt; //pra seleções numericas
		String sn; //pra seleções de S/N

		do{
			System.out.println("\n\nMenu Usuário: ");
			System.out.println("1- Cadastrar usuário");
			System.out.println("2- Remover usuário");
			System.out.println("3- Mostrar linhas");
			System.out.println("4- Reservar passagem");
			System.out.println("5- Mostrar rotas disponíveis");
			System.out.println("6- Sair");
			System.out.print("\nSeleção: ");

			Scanner sc = new Scanner(System.in);
			opt = sc.nextInt();
			switch(opt)
			{
				case 1: cadastrarUsuario(); break;
				
				case 2: removerUsuario(); break;
							
				case 3:
					if(rotas.size() == 0)
						System.out.println("Não há rotas disponíveis!");
					else{
						for(int i = 0; i < rotas.size(); i++)
							System.out.println(rotas.get(i).getIDRota() + " - " + rotas.get(i).getDestino());
					}
					break;

				case 4: 
					do{
						sc.nextLine(); //Limpa buffer
						System.out.println("\n\nJá tens cadastro? [S/N]: ");
						sn = sc.nextLine();
						if(sn.contains("S") || sn.contains("s"))
						{
							reservarPassagem();
						}
						else if(sn.contains("n") || sn.contains("N"))
						{
							cadastrarUsuario();
							System.out.println("Tente reservar novamente com sua conta recém-criada");
						}
						else System.out.println("Opção inválida!");
					}while(!sn.contains("S") && !sn.contains("s") && !sn.contains("n") && !sn.contains("N")); break;

				default: break;
			}


		}while(opt != 7);
	}

  
	public static void menuADM()  {
		System.out.println("\n\nMenu Admin: ");
		System.out.println("1- Cadastrar ônibus");
		System.out.println("2- Cadastrar motorista");
		System.out.println("3- Demitir motorista");
		System.out.println("4- Destruir ônibus (self destruct)");
		System.out.println("5- Editar rotas");
		System.out.println("6- Sair");
	}




	//Inicio da main
	public static void main(String[] args) {
		int opt;

		      Rotas SaintDeoclecian = new Rotas(); //instanciada para fins de teste
		      SaintDeoclecian.setIDRota(107);
		      SaintDeoclecian.setDestino("Sao Deocleciano");
		      rotas.add(SaintDeoclecian);


		do  { 
			System.out.println("Digite o tipo de usuário:");
			System.out.println("1- Passageiro");
			System.out.println("2- Administrador");
			System.out.println("3- Sair");
			System.out.print("\nSeleção: ");
			Scanner sc = new Scanner(System.in);
			opt = sc.nextInt();
			switch(opt)
			{
				case 1: menuUsuario(); break;
				case 2: menuADM(); break;
				case 3: break;
			}
			if(opt < 1 || opt > 3)
				System.out.println("Opção inválida!");
		}while(opt < 1 || opt > 3);

		// System.out.print(registro.passageiros);

		  /*    SaintDeoclecian.reservarAssento();

		      SaintDeoclecian.imprimeMatriz();

		      SaintDeoclecian.reservarAssento();

		      SaintDeoclecian.imprimeMatriz();
		 */


	}




}
//teste
