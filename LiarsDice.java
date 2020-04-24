import java.util.*;

public class LiarsDice
{
	public static void main(String [] args)
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the number of players in the game:");
		int numPlayers = Integer.parseInt(scan.nextLine());

		System.out.println("Enter the bid you would like to make to calculate the probability that it is accurate:");
		System.out.println("Example bid: 6  4, to state that you believe there are 6 4's between everyone's dice.. ");
		System.out.println("Don't forget....1's are wild and can be counted as any number!");
		String [] instructions = scan.nextLine().split(" ");
		int countToReach = Integer.parseInt(instructions[0]);
		int diceNum = Integer.parseInt(instructions[1]);

		System.out.println("Enter your own 5 dice results: ");
		String [] myDice = scan.nextLine().split(" ");

		int [] allDiceRolls = new int [numPlayers*5];

		for(int i=0; i<5; i++)
		{
			allDiceRolls[i] = Integer.parseInt(myDice[i]);
		}

		double numWins=0.0;
		double numRounds=1000000.0;

		//Monte Carlo Simulation:
		for(int j=0; j<numRounds; j++)
		{
			for(int i=5; i<allDiceRolls.length; i++)
			{
				allDiceRolls[i] = (int)(Math.random()*6 + 1);
			}

			boolean win = checkDice(allDiceRolls, diceNum, countToReach);

			if(win)
			{
				numWins++;
			}
		}

		double probability = (numWins/numRounds)*100.0;

		int answer = (int)Math.round(probability);
		System.out.println("Probability that your bid is true: " + answer + "%");


	}

	public static boolean checkDice(int [] array, int number, int howMany)
	{
		boolean win = false;
		int count=0;

		for(int i=0; i<array.length; i++)
		{
			//System.out.println(array[i]);
			if(array[i]==number || array[i]==1)
			{
				count++;
			}
		}

		if(count>=howMany)
		{
			win = true;
		}

		return win;
	}

}
