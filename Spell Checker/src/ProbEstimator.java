/*
	Project 1, Fall 2017
	Name: Sachin Joshi
	Course: CSE 498 NLP
	Instructor: Prof. Sihong Xie
	Class: ProbEstimator.java	
*/

import java.io.*;
import java.util.*;

class ProbEstimator
{
	public static void main(String args[])throws IOException
	{
		// Open the file train_tokens.txt to extract the bigrams
		BufferedReader br = new BufferedReader(new FileReader("data/train_tokens.txt"));
		BufferedWriter br1 = new BufferedWriter(new FileWriter("results/bigrams.txt"));
		
		String w;
		String words[] = new String[691075];
		int pos = 0, ct = 0;
		
		// Store all the tokens from train_tokens.txt into an array
		while((w = br.readLine())!= null)
		{
			if((w.compareTo("</s>")) != 0)
			{
				words[pos] = w;
				pos++;
			}
		}
		
		// Extract two consecutive tokens (bigrams) and store it the file bigrams.txt 
		for(;ct < 659880;)
		{
			if((words[ct].compareTo("<s>")) != 0 && (words[ct+1].compareTo("<s>")) != 0)
			{
				System.out.println(words[ct]+words[ct+1]);
				br1.write(words[ct]+words[ct+1]+"\n");
				ct++;
			}
			if((words[ct].compareTo("<s>")) == 0 || (words[ct+1].compareTo("<s>")) == 0 )
			{
				ct++;
			}
		}
		br.close();
		br1.close();
		
		// open the file train_tokens.txt to read
        Scanner console = new Scanner(System.in);
        Scanner input2 = new Scanner(new File("data/train_tokens.txt"));
		
        // Count the number of unique unigrams present in the file train_tokens.txt excluding <s> and </s>
        Map<String, Integer> wordCounts2 = new TreeMap<String, Integer>();
        while (input2.hasNext()) {
            String next = input2.next().toLowerCase();
            if (!wordCounts2.containsKey(next)) {
                wordCounts2.put(next, 1);
            } else {
                wordCounts2.put(next, wordCounts2.get(next) + 1);
            }
        }
		int V = (wordCounts2.size())-2;
        //System.out.println("Total words = " + V);
		
        // open the file bigrams.txt to read
        console = new Scanner(System.in);
        Scanner input = new Scanner(new File("results/bigrams.txt"));
		
        // first count the frequencies of each bigram
        Map<String, Integer> wordCounts = new TreeMap<String, Integer>();
        while (input.hasNext()) {
            String next = input.next().toLowerCase();
            if (!wordCounts.containsKey(next)) {
                wordCounts.put(next, 1);
            } else {
                wordCounts.put(next, wordCounts.get(next) + 1);
            }
        }
		
		// get the frequency of each bigram and print and store it in a text file freq.txt
		// Also calculate the probability using Laplacian Smoothing and store the results in the file prob_Lap.txt
		BufferedWriter br2 = new BufferedWriter(new FileWriter("results/freq.txt"));
		BufferedWriter br5 = new BufferedWriter(new FileWriter("results/prob_Lap.txt"));
		br5.write("Bigram" + "\t" + "Freq." + "\t" + "P(Lap)" + "\n");
        System.out.println("Total words = " + wordCounts.size());
        for (String word : wordCounts.keySet()) {
				int count = wordCounts.get(word);
                System.out.println(word + "\t" + count);
				double plap = (((double)(count))+1)/(V+597496); // Formula to calculate the probability using Laplacian Smoothing
				br2.write(""+count+"\n");
				br5.write(word + "\t" + ""+count + "\t" + ""+plap + "\t" + "\n"); // Write the result to the file prob_Lap.txt
        }
		br2.close();
		br5.close();
		
		// Open the new text file freq.txt that stores the frequency of each bigram
		console = new Scanner(System.in);
        input = new Scanner(new File("results/freq.txt"));
		
		// count frequencies of frequencies
        Map<String, Integer> wordCounts1 = new TreeMap<String, Integer>();
        while (input.hasNext()) {
            String next = input.next().toLowerCase();
            if (!wordCounts1.containsKey(next)) {
                wordCounts1.put(next, 1);
            } else {
                wordCounts1.put(next, wordCounts1.get(next) + 1);
            }
        }
		
        // Report frequencies of frequencies in the new text file ff.txt
		ArrayList<Integer> f = new ArrayList<Integer>();
		ArrayList<Integer> ff = new ArrayList<Integer>();
		BufferedWriter br3 = new BufferedWriter(new FileWriter("results/ff.txt")); 
		br3.write("C" + "\t" + "N(C)" + "\n");
        System.out.println("Total ff = " + wordCounts1.size());
        for (String word : wordCounts1.keySet()) {
				int count = wordCounts1.get(word);
                System.out.println(word + "\t" + count);
				br3.write(word + "\t" + count + "\n");
				f.add(Integer.parseInt(word));
				ff.add(count);
		}
		br3.close();
		
		// Apply GT Smoothing and creating the GT Table and storing the result in the file GTTable.txt
		BufferedWriter br4 = new BufferedWriter(new FileWriter("results/GTTable.txt"));
		br4.write("C" + "\t" + "C*" + "\t" + "Prob(w,v)" + "\n");
		for(int i = 0; i < f.size(); i++)
		{
			for(int j = i+1; j < ff.size(); j++)
			{
				if((f.get(i))+1 == f.get(j))
				{
					double c = ((double)(ff.get(j))/(double)(ff.get(i)))*((f.get(i))+1); // GT smoothing formula to calculate C* for every C
					double prob = c/597496; // N = 597496, total number of bigrams found above
					br4.write(""+f.get(i) + "\t" + ""+c + "\t" + ""+prob + "\n");
				}
			}
		}
		br4.close();
    }
}