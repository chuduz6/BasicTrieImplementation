import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class TestTrie {

	public static void main(String[] args) {
		
		int clock  = 0;
		int obamaCounter = 0;
		int romneyCounter = 0;
		int neutralCounter = 0;
		int actorA=0;
		int actorB=0;
		int badCount=0;
		int goodCount=0;
		StringTokenizer tokenizer;
		String temp;
		String temp2;
		Trie badwords = new Trie();		
		badwords = setupTrie(args[0]);
		//System.out.println("SHOOT1");		
		
		
		
		BufferedReader sc;
		BufferedWriter fw;  
		
		Trie goodwords = new Trie();		
		goodwords = setupTrie(args[1]);
		
		
		File inputFile = new File(args[2]);
		File outputFile = new File(args[3]);
		try {
			
			sc = new BufferedReader(new FileReader(args[2]));
			fw = new BufferedWriter(new FileWriter(args[3], true));
		
			//System.out.println("SHOOT1");
			
			
			while(sc.ready())
			{
				actorA=0;	
				actorB=0;
				badCount=0;
				goodCount=0;
				
				//System.out.println("SHOOT2");
				
				temp2=sc.readLine();
				clock++;
				tokenizer = new StringTokenizer(temp2);
				//System.out.println("SHOOT3");
				while(tokenizer.hasMoreTokens())
				{
					temp = tokenizer.nextToken();
					//temp = temp.toLowerCase();
					//System.out.println(temp);
					//temp=temp.replace("Â", "");
					
					//System.out.println("SHOOT4");
					if(temp.startsWith("obama") ||  temp.startsWith("barrackobama") || temp.startsWith("barrack") || temp.startsWith("odumbo") || temp.startsWith("nobama") || temp.startsWith("odummy") || temp.startsWith("bammy") || temp.startsWith("barak") || temp.startsWith("barack") || temp.startsWith("barrak") || temp.startsWith("barock") || temp.startsWith("barrock") || temp.startsWith("barrok") || temp.startsWith("barrok") || temp.startsWith("barok") || temp.startsWith("potus") )
					{
						//System.out.println("SHOOT5");
						fw.write(temp + " ");
						actorA=1;						
					}
					
					else if (temp.startsWith("romney") || temp.startsWith("mitt") || temp.startsWith("robme") || temp.startsWith("romnuts") || temp.startsWith("zomney") || temp.startsWith("romny") || temp.startsWith("romne") || temp.startsWith("romni") || temp.startsWith("romnesia") || temp.startsWith("mittens") )
					{
						actorB = 1;
						fw.write(temp + " ");
						
					}
					else if (badwords.search(temp))
					{
						//System.out.println("SHOOT6");
						fw.write(temp + "(bad) ");
						badCount++;
					}
					else if (goodwords.search(temp))
					{
						//System.out.println("SHOOT7");
						fw.write(temp + "(good) ");
						goodCount++;
					}
					else
					{
						//System.out.println("SHOOT8");
						fw.write(temp + " ");
					}
						
					
				}
				
				// calculation
				if((actorA==0) && (actorB == 0 ))
				{
					fw.write(" : NEUTRAL");
					neutralCounter++;
				}
				else if ((actorA==1) && (actorB == 0 ))
				{
					if(badCount >=1)
					{
						fw.write(" : ROMNEY");
						romneyCounter++;
					}
					else
					{
						fw.write(" : OBAMA");
						obamaCounter++;
					}
				}
				else if ((actorA==0) && (actorB == 1 ))
				{
					if(badCount < 1)
					{
						fw.write(" : ROMNEY");
						romneyCounter++;
					}
					else
					{
						fw.write(" : OBAMA");
						obamaCounter++;
					}
				}
				else
				{
					//big calculation
					fw.write(" : NEUTRAL");
					neutralCounter++;
				}
				
							
				fw.write("\n");				
			}
			
			sc.close();
			fw.close();
			//System.out.println("NOTE HERE");
		}
		catch (Exception e){
			//System.out.println("SHOOT");
			System.out.println("Errort");
			System.out.println(clock);
			e.printStackTrace();
			System.exit(1);			
		}
		
		
		System.out.println(obamaCounter + " tweets supported Obama.\n");
		System.out.println(romneyCounter + " tweets supported Romney.\n");
		System.out.println(neutralCounter + " tweets were Neutral.\n");
		
		if(obamaCounter > romneyCounter)
		{
			System.out.println("More tweets support OBAMA \n");
			
		}
		else if (obamaCounter < romneyCounter)
		{
			System.out.println("More tweets support ROMNEY \n");
		}
		else
		{
			System.out.println("There are equal number of tweets support both candidates \n");
		}
	

	}
	
	
	public static Trie setupTrie (String filename)
	{
		Trie temp = new Trie();
		String str;		
		File inputFile = new File(filename);
		try
		{
			Scanner sc = new Scanner (inputFile);
			while(sc.hasNextLine())
			{
				str = sc.nextLine();
				str = str.toLowerCase();
				temp.insert(str);
				
			}
			
			sc.close();
		}
		catch (Exception e){
			System.out.println("Error2233");		
			e.printStackTrace();
			System.exit(1);			
		}
		
		
		return temp;
	}

}
