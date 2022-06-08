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
					System.out.println("Deseja reservar qual ônibus?\nDigite o número da linha: ");
					numlinha = sc.nextInt(); //Lê o numero da linha a ser reservada
					for(j = 0; j < rotas.size(); j++)
						if(rotas.get(j).getIDRota() == numlinha)
						{
							Onibus bus = rotas.get(j).getOnibus();
							System.out.println("\nLinha encontrada\n");

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
        if(rotas.size() == 0)
            System.out.println("Não há rotas disponíveis!");
        else
        {
            for(int i = 0; i < rotas.size(); i++)
			{
				rota = rotas.get(i);
                System.out.println(rota.getIDRota() + ": " + rota.getOrigem() + " - " + rota.getDestino());
				//Usar um printf com %02d pros horarios
				System.out.println("Saída: " + rota.getHoraSaida().getHoras() + ":" + rota.getHoraSaida().getMinutos() + "\nChegada: " + rota.getHoraChegada().getHoras() + ":" + rota.getHoraChegada().getMinutos());
				System.out.println("Preço: " + rota.getValor());
			}
			
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
			System.out.println("8- Voltar");
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


		}while(opt != 8);
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
              if(rotas.get(i).getOnibus() == bus)
                System.out.println("Ônibus associado com sucesso à rota " + rotas.get(i).getDestino() + "!");
              break;
            }
          } 
          if(!encontrado)
            System.out.println("Rota não encontrada!");
        }

        
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
		
		for(int i = 0; i < onibuses.size(); i++) //Encontra a rota em questão
			if(onibuses.get(i).getPlaca().equals(placa))
			{	System.out.println("Encontrado!!");

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
							if(motoristas.get(j).estaAtribuido())
								System.out.println("Este motorista já está atribuido a um onibus");
							else
							{
								onibuses.get(i).setDriver(motoristas.get(j));
								onibuses.get(i).getDriver().setAtribuicao(true);
								onibuses.get(i).setTemMotorista(true); //LEMBRAR DE METER O FALSE QUANDO REMOVER
								System.out.println("Motorista atribuido com sucesso!");
							}
						}
					}
				}
				break;
			}
	} 
	public static void apagarMotorista()
	{
		System.out.print("Digite o CNH do motorista que deseja remover: ");
		Scanner sc = new Scanner(System.in);
		long CNH = sc.nextLong();
		for(int i = 0; i < motoristas.size(); i++)
		{
			if(motoristas.get(i).getCNH() == CNH)
			{ 
				System.out.println("Motorista encontrado!");
				Motorista driver = motoristas.get(i);
				driver.setAtribuicao(false);
				driver.getOnibus().setTemMotorista(false);
			 //Remove ele do array list dps	
			 	motoristas.remove(driver);
			}
		}
	}
		//função que desatribui um motorista de um onibus
	public static void desatribuirMotorista()	{
		System.out.print("Digite a CNH do motorista que deseja desatrelar: ");
		Scanner sc = new Scanner(System.in);
		long CNH = sc.nextLong();
		for(int i = 0; i < motoristas.size(); i++)
		{
			if(motoristas.get(i).getCNH() == CNH)
			{
				System.out.println("Motorista encontrado!");
				motoristas.get(i).getOnibus().setTemMotorista(false);
				motoristas.get(i).setAtribuicao(false);
			}
		}

		return;
	}

	public static void criarRota()	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o ID da rota: ");
		int auxID = sc.nextInt();
		
		for(int i = 0; i < passageiros.size(); i++)	{
			if(rotas.get(i).getIDRota() == auxID) {
				System.out.println("Esse ID já foi cadastrado!");
				return;
			}
		}

		System.out.print("Digite a cidade origem: ");
		sc.nextLine();
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
	
	public static void imprimirRotasADM()
    {
		Rotas rota;
        if(rotas.size() == 0)
            System.out.println("Não há rotas disponíveis!");
        else
        {
            for(int i = 0; i < rotas.size(); i++)
			{
				rota = rotas.get(i);
                System.out.println(rota.getIDRota() + ": " + rota.getOrigem() + " - " + rota.getDestino());
				System.out.println("Parada: " + rota.getParada());
				System.out.printf("Saída: %02d:%02d\nChegada: %02d:%02d\n",rota.getHoraSaida().getHoras(), rota.getHoraSaida().getMinutos(), rota.getHoraChegada().getHoras(), rota.getHoraChegada().getMinutos());
				System.out.printf("Preço: %.2f R$", rota.getValor());
				if(rota.getAtribBus())
				{
					rota.getOnibus().imprimirDados();
					
					if(motoristas.size() != 0 && rota.getOnibus().temMotorista()) { //teoricamente não precisa do motoristas.size(). Só pra garantir kk
						System.out.println("Motorista: " + rota.getOnibus().getDriver().getNome() + " [CNH: " + rota.getOnibus().getDriver().getCNH() + "]");
						Data data = rota.getOnibus().getDriver().getDataAdmissao(); //abreviação do texto ENORME
						System.out.printf("Data admissão %02d/%02d/%02d\n", data.getDia(), data.getMes(), data.getAno());
					}
				}
				else System.out.println("Ônibus: N/A");
			}
			
        }
    }
//----------------------------------------------------------------------------
	public static void menuADM()  {
        int opt;
        do{
	    	System.out.println("\n\nMenu Admin: ");
    		System.out.println("1- Criar rotas"); //Feita
    		System.out.println("2- Imprimir Rotas");
    		System.out.println("3- Cadastrar ônibus"); //Feita
    		System.out.println("4- Cadastrar motorista"); //Feita
			System.out.println("5- Atribuir motorista ao onibus"); //Feita; precisa testar
			System.out.println("6- Remover motorista de onibus"); //Feita
    		System.out.println("7- Excluir motorista"); //Feita, nao testada //LEMBRAR DE METER O FALSE QUANDO REMOVER
    		System.out.println("8- Destruir ônibus (self destruct)");
			System.out.println("9- Sair");

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
					cadastrarMotorista();
					break;

				case 5:
					atribuirMotorista();
					break;
				
				case 6:
					desatribuirMotorista();
					break;

				case 7:
					apagarMotorista();
					break;

				case 8:
					break;
            }
        }while(opt != 9);
	}


/*
    ArrayList<Rotas> getRotas(){
        return this.rotas;
    }
*/
}
