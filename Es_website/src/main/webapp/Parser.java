import java.io.BufferedReader;
import java.net.URL;
import java.util.Scanner;

public class Parserr {

	public void parse(String file) {
		
		// passar do link para fazer download do ficheiro do calendário e criar um file.txt para usar no scanner
		
		URL url = new URL(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("agenda.txt"));
		 int i;
		 while (i != -1){
		      i = br.read();
		      if (i != -1) {
		          bw.write((char) i);
		      }
		    } 
		    br.close();
		    bw.close();
		  }
		
		Scanner scan = new Scanner(new File("agenda.txt"));
		
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
		while (scan.hasNextLine()) {
			
			String a = scan.nextLine();
			String dateStart ;
			
			if(a.contains("DTSTART:")) {
				dateStart = a.split(":")[1];
			}
				   a = scan.nextLine();
			String dateEnd = a.split(":")[1];
			
			 a = scan.nextLine();
			 String summary = a.split(":")[1];
			 a = scan.nextLine();
			 
			 if(!a.contains("UID:")) {
				 summary = summary +a;
				}
			 System.out.println(dateStart+ "\n" +dateEnd+ "\n" +summary );
		}
	}
}
