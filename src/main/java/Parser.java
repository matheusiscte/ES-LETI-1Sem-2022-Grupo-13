

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Parser{

	public void parser() throws IOException {

		String webcal = "webcal://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=mpclq@iscte.pt&password=xmL473npYB7oemMN431WH9nEJRIctc1xGX20iWj2peknw6XJ6XL3yJuTfbSwpO9gE64qIekOJtyhfGeFFWBwElxA6zxz14SHNsKbx4LZIX56YjhMvYa1hQQRFDoA53W8\r\n";

		webcal  = webcal.replace("webcal:", "https:");
		//cria um objecto do tipo URL baseado na string que damos
		URL url = new URL(webcal);
		//copia para um ficheiro o que o link nos dá
		Files.copy(url.openStream(), Paths.get("webcal.txt"), REPLACE_EXISTING);

		try {	
			Scanner	scan = new Scanner(new File("webcal.txt"));
			String dateStart = null;
			String dateEnd = null;
			
			while (scan.hasNextLine()) 
			{	
				BufferedWriter writer = new BufferedWriter(new FileWriter("agenda.txt",true));
				String a = scan.nextLine();	
				if(a.equals("BEGIN:VEVENT")) 
				{
					scan.nextLine();
					a = scan.nextLine();
					
					dateStart = a.split(":")[1];
					dateStart = dateStart.replace("T","");
					dateStart = dateStart.replace("Z","");
					
					a = scan.nextLine();		
					
					dateEnd = a.split(":")[1];	
					dateEnd = dateEnd.replace("T","");
					dateEnd = dateEnd.replace("Z","");
					
					String summary = scan.nextLine();	 
					a = scan.nextLine();	 
					if(!a.contains("UID:")) 
					{
						summary =summary+a.strip();		 	 
						
					}
					if (summary.contains("Teste:" ) || summary.contains("Exame:") || summary.contains("Avaliação Contínua:")) {summary = summary.split(":")[2] + " - Exame";
					summary = summary.strip();			
					} else {
						summary = summary.split(":")[1];
					}	 
					//System.out.println(summary+ "\n" +dateStart+ "\n" +dateEnd+ "\n");
					String l =summary+ "\n" +dateStart+ "\n" +dateEnd+ "\n \n";
					
					writer.write(l);
					writer.close();
				}  	 
			}
			scan.close();
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
DTSTART:20230329T170000Z 
DTEND:20230329T183000Z
SUMMARY:Desenvolvimento para A Internet e Aplicações Móveis - Development
  for Internet and Mobile Apps
UID:1692101150526471@fenix.iscte.pt

->quando a linha do scanner começar por DTSTART: damos split à linha pois queremos 
apenas a a posição 1 do vetor do split, a data
->depois disso passamos à próxima linha e fazemos o mesmo para a data do fim
-> passando para a linha do sumário, esta pode ter uma ou duas linhas, então fazemos um nextline para 
saber quando acaba o sumário, pois a linha após o sumário começa com UID
		 */

		}
	
	

	
	
}
