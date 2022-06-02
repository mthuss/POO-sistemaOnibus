public class Funcoes{
public void cadastrarUsuario()
	{
		Passageiro usuario = new Passageiro();
		usuario.dadosPassageiro();
		this.passageiros.add(usuario);
	}

	public void reservarPassagem()
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
	public void menuUsuario()  {
		int opt; //pra seleções numericas
		String yn; //pra seleções de S/N

		do{
			System.out.println("\n\nMenu Usuário: ");
			System.out.println("1- Cadastrar usuário");
			System.out.println("2- Remover usuário");
			System.out.println("3- Mostrar linhas");
			System.out.println("4- Reservar passagem");
			System.out.println("5- Remover cadastro");
			System.out.println("6- Mostrar rotas disponíveis");
			System.out.println("7- Sair");
			System.out.print("\nSeleção: ");

			Scanner sc = new Scanner(System.in);
			opt = sc.nextInt();
			switch(opt)
			{
				case 1: this.cadastrarUsuario(); break;
				case 4: 
					do{
						sc.nextLine(); //Limpa buffer
						System.out.println("\n\nJá tens cadastro? [S/N]: ");
						yn = sc.nextLine();
						if(yn.contains("S") || yn.contains("s"))
						{
							this.reservarPassagem();
						}
						else if(yn.contains("n") || yn.contains("N"))
						{
							this.cadastrarUsuario();
              System.out.println("Tente reservar novamente com sua conta recém-criada");
						}
						else System.out.println("Opção inválida!");
					}while(!yn.contains("S") && !yn.contains("s") && !yn.contains("n") && !yn.contains("N")); break;

				default: break;
			}

      
		}while(opt != 7);
	}

  
	public void menuADM()  {
		System.out.println("\n\nMenu Admin: ");
		System.out.println("1- Cadastrar ônibus");
		System.out.println("2- Cadastrar motorista");
		System.out.println("3- Demitir motorista");
		System.out.println("4- Destruir ônibus (self destruct)");
		System.out.println("5- Editar rotas");
		System.out.println("6- Sair");
	}
}