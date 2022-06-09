import java.util.Scanner;
import java.util.ArrayList;

public class Sistema
{
	public static ArrayList<Passageiro> passageiros = new ArrayList<>();
	public static ArrayList<Rotas> rotas = new ArrayList<>();
	public static ArrayList<Motorista> motoristas = new ArrayList<>();
	public static ArrayList<Onibus> onibuses = new ArrayList<>();


	public static void cadastrarUsuario()
	{
		System.out.println("\nRealize seu cadastro: ");

	    boolean repetido = false;

	    System.out.print("CPF: ");
 	    Scanner sc = new Scanner(System.in);
	    long documento = sc.nextLong();

		sc.nextLine(); //Limpa buffer

		//Verifica se já existe algum usuário com este CPF cadastrado
		for(int i = 0; i < passageiros.size(); i++)
			if(passageiros.get(i).getDocumento() == documento)
			{
				System.out.println("Este CPF já está cadastrado!");
				return;
			}

	    System.out.print("Nome: ");
	    String nome = sc.nextLine();

	    System.out.print("\nData de nascimento: \nDia: ");
	    int D,M,A;
	    D = sc.nextInt();
	    System.out.print("Mês: ");
	    M = sc.nextInt();
	    System.out.print("Ano: ");
	    A = sc.nextInt();
	    Data nascData = new Data(D,M,A);
	    sc.nextLine();

	    System.out.print("Endereço: ");
	    String endereco = sc.nextLine();

//		usuario.dadosPassageiro();
		Passageiro usuario = new Passageiro(documento,nome,nascData,endereco);
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
				cancelarPassagem(passageiros.get(i).getDocumento());
				passageiros.remove(passageiros.get(i));
				break;
			}
		}
		if(encontrado)
			System.out.println("Usuário removido com sucesso!");
		else
			System.out.println("Usuário não encontrado.");
	}
	
	public static void reservarPassagem()
	{
		int i,j;
		String sn = "n"; //prompt de confirmação da linha
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite seu CPF: ");
		long CPF = sc.nextLong();	
		boolean encontrado=false;

		//Verifica se o CPF digitado está, de fato, cadastrado
		//e reserva a linha pedida
		for(i = 0; i < passageiros.size(); i++)
			if(passageiros.get(i).getDocumento() == CPF)
			{
				System.out.println("Encontrado!");
				encontrado = true;
				Passageiro pessoa = passageiros.get(i); 
				if(pessoa.getReservado())
				{
					System.out.println("Já tens uma passagem reservada !!!");
					return ;
				}
				
				System.out.println(pessoa.getNome());
				int numlinha;	

				do{  //laço de verificação "deseja reservar MESMO essa linha?"
					System.out.print("Deseja reservar qual ônibus?\nDigite o número da linha\n[0 para cancelar]\n\nLinha: ");
					numlinha = sc.nextInt(); //Lê o numero da linha a ser reservada
					if(numlinha == 0)
						break;
					for(j = 0; j < rotas.size(); j++)
						if(rotas.get(j).getIDRota() == numlinha)
						{
							Onibus bus = rotas.get(j).getOnibus();
							System.out.println("\nLinha encontrada\n");

							if(rotas.get(j).getAtribBus() == false)
							{
								System.out.println("Esta rota ainda não possui um onibus definido!");
								break;
							}
							System.out.print("Deseja reservar para " + numlinha + " - " + rotas.get(j).getDestino() + "\n[S/N]: ");
							sc.nextLine();
							sn = sc.nextLine();

							if (sn.contains("s") || sn.contains("S"))  
							{
								bus.reservarAssento(pessoa);
								pessoa.setReservado(true);
								System.out.println("Reservado");
								pessoa.setLinha(numlinha);

								System.out.println("Linha definida");
								break;
							}

						}
				}  while (sn.contains("n") || sn.contains("N"));  
			}

		if(!encontrado)
			System.out.println("CPF não cadastrado!");


	}

	public static void cancelarPassagem(long CPF)
	{
		boolean encontrado = false;
//		boolean rotaExiste = false; //Pra caso a rota a qual a pessoa estava atrelada tiver sido deletada

		for(int i = 0; i < passageiros.size(); i++) //Encontrar o passageiro que quer cancelar a passagem
			if(passageiros.get(i).getDocumento() == CPF)
			{
				encontrado = true;
				Passageiro pessoa = passageiros.get(i);
				
				if(pessoa.getReservado()) //Só cancela se a pessoa tiver, de fato, alguma passagem reservada
				{
					int ID = pessoa.getLinha();
					int pos[] = pessoa.getAssento();
					pessoa.setLinha(0); //Desvincula linha da pessoa
					//System.out.println(pos[0] + "\n" + pos[1]);
					for(int j = 0; j < rotas.size(); j++)	//Encontra o onibus da rota do passageiro
						if(rotas.get(j).getIDRota() == ID)
						{
							//rotaExiste = true;
							rotas.get(j).getOnibus().cancelarAssento(pos[0],pos[1]);
							pessoa.setReservado(false);
							break;
						}
				}
				else System.out.println("Este usuário não possui nenhuma passagem reservada");
				break;	
			}

		if(!encontrado)
			System.out.println("Não existe usuário registrado com esse CPF");


	}

    public static void imprimirRotas()
    {
		Rotas rota;
		boolean temRota = false;
        if(rotas.size() == 0)
            System.out.println("Não há rotas disponíveis!");
        else
        {
            for(int i = 0; i < rotas.size(); i++)
			{
				if(rotas.get(i).getAtribBus()) //só vai mostrar a rota se tiver algum onibus atribuido a ela
				{
					temRota = true;
					rota = rotas.get(i);
	                System.out.println(rota.getIDRota() + ": " + rota.getOrigem() + " - " + rota.getDestino());
					System.out.println("Saída: " + rota.getHoraSaida().imprimirHorario() + "\nChegada: " + rota.getHoraChegada().imprimirHorario());
					System.out.println("Preço: " + rota.getValor());
				}
			}
			if(!temRota)
				System.out.println("Não há rotas disponíveis no momento");
			
        }
    }

	public static void alterarCadastro()	{
		System.out.print("Digite o CPF de quem deseja alterar: ");
		Scanner sc = new Scanner(System.in);
		boolean encontrado = false;
		long CPF = sc.nextLong();
		int opt;

		for(int i = 0; i < passageiros.size(); i++)	{
			if(passageiros.get(i).getDocumento() == CPF) {
				Passageiro pessoa = passageiros.get(i);
				encontrado = true;
				System.out.println("Cadastro encontrado!!\n");

				do	{
					System.out.println("\nQue dado deseja alterar?");
					System.out.println("1- Alterar nome");
					System.out.println("2- Alterar data de nascimento");
					System.out.println("3- Alterar endereço");
					System.out.print("Comando: ");
					opt = sc.nextInt();
				
					switch(opt)	{
						case 1: System.out.print("Digite o nome: ");
								sc.nextLine();
								String nome = sc.nextLine();
								pessoa.setNome(nome);
								break;

						case 2: System.out.print("\nDigite a nova data de nascimento: \nDia: ");
								int D,M,A;
								D = sc.nextInt();
								System.out.print("Mês: ");
								M = sc.nextInt();
								System.out.print("Ano: ");
								A = sc.nextInt();
								Data nascData = new Data(D,M,A);
								sc.nextLine();
								pessoa.setData(D, M, A);
								break;

						case 3:	System.out.print("Digite o novo endereço: ");
								sc.nextLine();
	    						String endereco = sc.nextLine();
								pessoa.setEndereco(endereco);
								break;


						default: System.out.println("Valor invalido!\n"); break; 
					}
				} while (opt < 1 || opt > 3);
			}
		}
	
		if(!encontrado) {
			
			System.out.println("Cadastro não encontrado!");
		}
	}

//----------------------------------------------------------------------------
	public static void menuUsuario()  {
		int opt; //pra seleções numericas
		String sn; //pra seleções de S/N

		do{
			System.out.println("\n\nMenu Usuário: ");
			System.out.println("1- Cadastrar usuário"); //Feita
			System.out.println("2- Remover usuário"); //Feita
			System.out.println("3- Mostrar linhas"); //Feita*; falta botar horarios (problema!!!)
			System.out.println("4- Reservar passagem"); //Feita
			System.out.println("5- Cancelar passagem"); //Feita
			System.out.println("6- Alterar cadastro"); //Feita
			System.out.println("7- Mostrar Cadastro"); //Feita
			System.out.println("0- Voltar");
			System.out.print("\nSeleção: ");

			Scanner sc = new Scanner(System.in);
			opt = sc.nextInt();
			long CPF;
			switch(opt)
			{
				case 1: cadastrarUsuario(); break;
				
				case 2: removerUsuario(); break;
							
				case 3:	imprimirRotas(); break;

				case 4: 
					do{
						sc.nextLine(); //Limpa buffer
						System.out.println("\n\nJá tens cadastro? [S/N]: ");
						sn = sc.nextLine();
						if(sn.contains("S") || sn.contains("s"))
						{
							reservarPassagem(); break;
						}
						else if(sn.contains("n") || sn.contains("N"))
						{
							cadastrarUsuario();
							System.out.println("Tente reservar novamente com sua conta recém-criada");
						}
						else System.out.println("Opção inválida!");
					}while(!sn.contains("S") && !sn.contains("s") && !sn.contains("n") && !sn.contains("N")); 
					break;
				
				case 5: 
						System.out.print("Cancelar passagem\n\nDigite seu CPF: ");
						CPF = sc.nextLong();
						cancelarPassagem(CPF); 
						break;

				case 6: alterarCadastro(); break;

				case 7:
						System.out.print("Digite seu CPF: ");
						boolean encontrado = false;
						CPF = sc.nextLong();
						for(int i = 0; i < passageiros.size(); i++)	{
							if(passageiros.get(i).getDocumento() == CPF) {
								passageiros.get(i).imprimirDados();
								encontrado = true;
							}
						}
						if(!encontrado)
							System.out.println("Usuario não encontrado !!!");
						break;

				default: System.out.println(""); break;
			}


		}while(opt != 0);
//		passageiros.get(0).imprimirDados();
	}

//----------------------------------------------------------------------------
    //Funções do menuADM
    public static void cadastrarOnibus()
    {
        Onibus bus = new Onibus();
        bus.dadosOnibus();
		onibuses.add(bus);
        System.out.print("Deseja associá-lo a alguma rota? (S/N): "); //Dar uma revisada nisso depois, pra ver se ta linkando os dois do jeito certo
        Scanner sc = new Scanner(System.in);
        String sn = sc.nextLine();

        boolean encontrado = false;
 
        if(sn.contains("S") || sn.contains("s"))
        {
          System.out.print("Digite o número da rota à qual deseja associar o ônibus: ");
          int ID = sc.nextInt();
          for(int i = 0; i < rotas.size(); i++) 
          {
            if(rotas.get(i).getIDRota() == ID)
            {
              encontrado = true;
			  Rotas rota = rotas.get(i);
			  if(rota.getAtribBus())
			  {
				  System.out.println("Esta rota já possui um onibus atribuido"); 
				  return;
			  }
              rotas.get(i).setOnibus(bus);
              bus.setIDRota(ID);
//			  bus.setRota(rota);
			  rota.setAtribBus(true);
			  bus.setAtribuido(true);
              if(rotas.get(i).getOnibus() == bus) //confirma que o onibus foi atribuido corretamente
                System.out.println("Ônibus associado com sucesso à rota " + rotas.get(i).getOrigem() + " - " + rotas.get(i).getDestino() + "!");
              break;
            }
          } 
          if(!encontrado)
            System.out.println("Rota não encontrada!");
        }
    }

	public static void atribuirOnibus()	{
        Scanner sc = new Scanner(System.in);
		boolean encontradoOnibus = false;

		System.out.print("Digite a placa do onibus que deseja atrelar: ");
		String auxPlaca = sc.nextLine();

		for (int i=0; i<onibuses.size(); i++)
			if (auxPlaca == onibuses.get(i).getPlaca())	{
				encontradoOnibus = true;
				Onibus bus = onibuses.get(i);

				System.out.print("Digite o número da rota à qual deseja associar o ônibus: ");
				sc.nextInt();
				int ID = sc.nextInt();

				boolean encontradoRotas = false;
				for(int j = 0; j < rotas.size(); j++) 
				{
					if(rotas.get(j).getIDRota() == ID)
					{
					encontradoRotas = true;
					Rotas rota = rotas.get(j);
					if(rota.getAtribBus()) //fazer um prompt pra perguntar se o cara quer sobrescrever
					{
						System.out.println("Esta rota já possui um onibus atribuido"); 
						return;
					}
					rota.setOnibus(bus);
					bus.setIDRota(ID);
					rota.setAtribBus(true);
					bus.setAtribuido(true);
					if(rotas.get(i).getOnibus() == bus) //confirma que o onibus foi atribuido corretamente
						System.out.println("Ônibus associado com sucesso à rota " + rotas.get(i).getOrigem() + " - " + rotas.get(i).getDestino() + "!");
					}
			
				}
			if (!encontradoRotas)
				System.out.println("Não foi possivel encontrar a rota");
			}
		if (!encontradoOnibus)
			System.out.println("Não foi possivel encontrar onibus");

	}

	public static void cadastrarMotorista()
	{
		System.out.println("\nRealize o cadastro do motorista: ");

	    boolean repetido = false;

	    System.out.print("CNH: ");
 	    Scanner sc = new Scanner(System.in);
	    long CNH = sc.nextLong();

		sc.nextLine(); //Limpa buffer

		//Verifica se já existe algum motorista com este CNH cadastrado
		for(int i = 0; i < motoristas.size(); i++)
			if(motoristas.get(i).getCNH() == CNH)
			{
				System.out.println("Esta CNH já está cadastrada!");
				return;
			}

	    System.out.print("Nome: ");
	    String nome = sc.nextLine();

	    System.out.print("\nData de admissão: \nDia: ");
	    int D,M,A;
	    D = sc.nextInt();
	    System.out.print("Mês: ");
	    M = sc.nextInt();
	    System.out.print("Ano: ");
	    A = sc.nextInt();
	    Data admissao = new Data(D,M,A);
	    sc.nextLine();

//		usuario.dadosPassageiro();
		Motorista motorista = new Motorista(CNH,admissao,nome);
		motoristas.add(motorista);

	}

	public static void atribuirMotorista()
	{
		System.out.print("A qual onibus deseja atribuir o motorista?\nPlaca do onibus: ");
		Scanner sc = new Scanner(System.in);
		String placa = sc.nextLine();
		boolean onibusencontrado=false;	

		for(int i = 0; i < onibuses.size(); i++) //Encontra a rota em questão
			if(onibuses.get(i).getPlaca().equals(placa))
			{	System.out.println("Encontrado!!");
				onibusencontrado = true;

				boolean motoristaencontrado = false;

				if(onibuses.get(i).temMotorista())
					System.out.println("Este ônibus já tem um motorista atribuido");
		
				else
				{
					System.out.print("Digite o CNH do motorista: ");
					long CNH = sc.nextLong();
					for(int j = 0; j < motoristas.size(); j++) //Encontra o motorista a ser atrelado
					{
						if(motoristas.get(j).getCNH() == CNH)
						{
							motoristaencontrado = true;
							if(motoristas.get(j).estaAtribuido())
								System.out.println("Este motorista já está atribuido a um onibus");
							else
							{
								onibuses.get(i).setDriver(motoristas.get(j));
								motoristas.get(j).setOnibus(onibuses.get(i));
								onibuses.get(i).getDriver().setAtribuicao(true);
								onibuses.get(i).setTemMotorista(true); //LEMBRAR DE METER O FALSE QUANDO REMOVER
								System.out.println("Motorista atribuido com sucesso!");
							}
						}
					}
					if(!motoristaencontrado)
						System.out.println("Motorista não encontrado");
				}
				break;
			}
			if(!onibusencontrado)
				System.out.println("Onibus não encontrado");
	} 
	public static void apagarMotorista()
	{
		System.out.print("Digite o CNH do motorista que deseja remover: ");
		Scanner sc = new Scanner(System.in);
		long CNH = sc.nextLong();
		boolean encontrado = false;
		for(int i = 0; i < motoristas.size(); i++)
		{
			if(motoristas.get(i).getCNH() == CNH)
			{ 
				System.out.println("Motorista encontrado!");
				 encontrado = true;
				Motorista driver = motoristas.get(i);
				driver.setAtribuicao(false);
				driver.getOnibus().setTemMotorista(false);
				driver.getOnibus().setDriver(null);
				driver.setOnibus(null);
			 	motoristas.remove(driver);
			}
		}
		if(!encontrado)
			System.out.println("Motorista não encontrado");
	}
		//função que desatribui um motorista de um onibus
	public static void desatribuirMotorista()	{
		System.out.print("Digite a CNH do motorista que deseja desatrelar: ");
		Scanner sc = new Scanner(System.in);
		long CNH = sc.nextLong();
		boolean encontrado = false;
		for(int i = 0; i < motoristas.size(); i++)
		{
			if(motoristas.get(i).getCNH() == CNH)
			{
				System.out.println("Motorista encontrado!");
				encontrado = true;
				if(motoristas.get(i).estaAtribuido())
				{
					motoristas.get(i).getOnibus().setTemMotorista(false);
					motoristas.get(i).getOnibus().setDriver(null);
					motoristas.get(i).setOnibus(null);
					motoristas.get(i).setAtribuicao(false);
				}
				else System.out.println("Motorista não está atribuido a nenhum onibus");
			}
		}
		if(!encontrado)
			System.out.println("Motorista não encontrado");

		return;
	}

	public static void criarRota()	{
		Scanner sc = new Scanner(System.in);
//		System.out.print("Digite o ID da rota: ");
//		int auxID = sc.nextInt();
		int auxID = rotas.size();
//		for(int i = 0; i < rotas.size(); i++)	{
//			if(rotas.get(i).getIDRota() == auxID) {
//				System.out.println("Esse ID já foi cadastrado!");
//				return;
//			}
//		}

		System.out.print("Digite a cidade origem: ");
//		sc.nextLine();
		String auxOrigem = 	sc.nextLine();

		System.out.print("Digite a parada: ");
		String auxParada = sc.nextLine();

		System.out.print("Digite a cidade destino: ");
		String auxDestino = sc.nextLine();

		int PHour, PMinutes;
		do {
			System.out.println("Horario de partida");
			System.out.print("Digite o horario de saida: ");
			PHour = sc.nextInt();
			if (PHour < 0 || PHour > 24)
				System.out.println("Horário de saida inválido");
		} while (PHour < 0 || PHour > 24);

		do {
			System.out.print("Digite os minutos de saida: ");
			PMinutes = sc.nextInt();
			if (PMinutes < 0 || PMinutes > 60)
				System.out.println("Horário de saida inválido");
		} while (PMinutes < 0 || PMinutes > 60);

		Horario Hpartida = new Horario(PHour, PMinutes);

		int CHour, CMinutes;
		do {
			System.out.println("Horarios de chegada");
			System.out.println("[Caso o horário de chegada seja menor ou igual ao de saída, entende-se que será do dia seguinte]");
			System.out.print("Digite o horario de chegada: ");
			CHour = sc.nextInt();
			if (CHour < 0 || CHour > 24)
				System.out.println("Horário de chegada inválido");
		} while (CHour < 0 || CHour > 24);

		do {
			System.out.print("Digite os minutos de chegada: ");
			CMinutes = sc.nextInt();
			if (CMinutes < 0 || CMinutes > 60)
					System.out.println("Horário de chegada inválido");
		} while (CMinutes < 0 || CMinutes > 60);

		Horario Hchegada = new Horario (CHour, CMinutes);

		System.out.print("Digite o preço dessa passagem: ");
		float auxPreco = sc.nextFloat();

		Rotas rota = new Rotas(auxOrigem, auxParada, auxDestino, Hpartida, Hchegada, auxPreco, auxID);
		rotas.add(rota);
	}
	
	public static void imprimirRotasADM() //Imprime rotas com info a mais para o ADM
    {
		Rotas rota;
        if(rotas.size() == 0)
            System.out.println("Não há rotas disponíveis!");
        else
        {
            for(int i = 0; i < rotas.size(); i++)
			{
				rota = rotas.get(i);
                System.out.println("Rota " + rota.getIDRota() + ": " + rota.getOrigem() + " - " + rota.getDestino());
				System.out.println("Parada: " + rota.getParada());
				System.out.println("Saída: " + rota.getHoraSaida().imprimirHorario() + "\nChegada: " + rota.getHoraChegada().imprimirHorario());
				System.out.printf("Preço: R$%.2f\n", rota.getValor());
				if(rota.getAtribBus())
				{
					rota.getOnibus().imprimirDados();
					
					if(motoristas.size() != 0 && rota.getOnibus().temMotorista()) { //teoricamente não precisa do motoristas.size(). Só pra garantir kk
						System.out.println("Motorista: " + rota.getOnibus().getDriver().getNome() + " [CNH: " + rota.getOnibus().getDriver().getCNH() + "]");
						Data data = rota.getOnibus().getDriver().getDataAdmissao(); //abreviação do texto ENORME
						System.out.println("  Data de admissão: " + data.imprimirData()); 
					}
				}
				else System.out.println("Ônibus: N/A");
			}
			
        }
    }

	public static void apagarRota() 
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Deseja apagar qual rota?\n\nRota: ");
		int ID = sc.nextInt();
		if(ID < 0 || ID > rotas.size())
		{
			System.out.println("ID inválido");
			return;
		}








	}

	public static void desatribuirOnibus()	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite a placa do onibus que quer desatribuir: ");
		String auxPlaca = sc.nextLine();
		boolean encontradoOnibus = false;

		for (int j=0; j<onibuses.size(); j++){
			if (auxPlaca == onibuses.get(j).getPlaca())	{
				encontradoOnibus = true;
				System.out.print("Digite o ID da rota que deseja desatribuir: ");
				sc.nextInt();
				int auxID = sc.nextInt();
				if (auxID > 0 && auxID < rotas.size())	{
					Rotas rota = rotas.get(auxID);
					rota.getOnibus().setIDRota(-1);
					rota.getOnibus().setAtribuido(false);
					rota.setOnibus(null);
					rota.setAtribBus(false);
				}
				else System.out.println("ID inválido!!!");
			}
		}
	}


//----------------------------------------------------------------------------
	public static void menuADM()  {
        int opt;
        do{	//falta remover rotas E SÓ
	    	System.out.println("\n\nMenu Admin: ");
    		System.out.println("1- Criar rotas"); //Feita
    		System.out.println("2- Imprimir Rotas"); //Feita
    		System.out.println("3- Cadastrar ônibus"); //Feita
			System.out.println("4- Atribuir ônibus a rota"); //Feita
    		System.out.println("5- Cadastrar motorista"); //Feita
			System.out.println("6- Atribuir motorista ao onibus"); //Feita
			System.out.println("7- Remover motorista de onibus"); //Feita
    		System.out.println("8- Excluir motorista"); //Feita, nao testada
    		System.out.println("9- Destruir ônibus (self destruct)");
			System.out.println("10- Remover onibus da rota"); //Feito
			System.out.println("11- Imprimir todos os passageiros"); //Feita
			System.out.println("12- Imprimir todos os motoristas"); //Feita
			System.out.println("13- Imprimir todos os onibus"); //Feita
			System.out.println("0- Voltar");

			//Fazer uma função pra associar um onibus a uma rota
            System.out.print("Seleção: ");
            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();

            switch(opt)
            {
                case 1:
					criarRota();
					break;

				case 2:
					imprimirRotasADM();
					break;

				case 3:
                    cadastrarOnibus();
					break;
				
				case 4:
					atribuirOnibus();
					break;

				case 5:
					cadastrarMotorista();
					break;

				case 6:
					atribuirMotorista();
					break;
				
				case 7:
					desatribuirMotorista();
					break;

				case 8:
					apagarMotorista();
					break;

				case 9:
					//removerOnibus();
					System.out.println("Ainda não ta funcionando...");
					break;
					
				case 10:
					desatribuirOnibus();
					break;

				case 11:
					if(passageiros.size() == 0)
						System.out.println("Não há nenhum passageiro registrado no sistema");
					else
					{
						for(int i = 0; i < passageiros.size(); i++)
							passageiros.get(i).imprimirDados();
					}
					break;

				case 12:
					if(motoristas.size() == 0)
						System.out.println("Não há nenhum motorista registrado no sistema");
					else
					{
						for(int i = 0; i < motoristas.size(); i++)
							motoristas.get(i).imprimirDados();	
					}
					break;
				
				case 13:
					if(onibuses.size() == 0)
						System.out.println("Não há nenhum ônibus registrado no sistema");
					else
					{
						for(int i = 0; i < onibuses.size(); i++)
							onibuses.get(i).imprimirDados();	
					}
					break;
            }
        }while(opt != 0);
	}


/*
    ArrayList<Rotas> getRotas(){
        return this.rotas;
    }
*/
}
