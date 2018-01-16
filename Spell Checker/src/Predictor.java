/*
	Project 1, Fall 2017
	Name: Sachin Joshi
	Course: CSE 498 NLP
	Instructor: Prof. Sihong Xie
	Class: Predictor.java	
*/

import java.io.*;
import java.util.*;

class Predictor
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("data/test_tokens_fake.txt"));
		BufferedWriter br1 = new BufferedWriter(new FileWriter("results/test_sent_fake.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("data/all_confusingWords.txt"));
		BufferedWriter br3 = new BufferedWriter(new FileWriter("results/all_confusingWordsBigrams.txt"));
		BufferedReader br4 = new BufferedReader(new FileReader("results/bigrams.txt"));
		BufferedReader br5 = new BufferedReader(new FileReader("results/ff.txt"));
		BufferedReader br6 = new BufferedReader(new FileReader("results/GTTable.txt"));
		
		ArrayList<String> test = new ArrayList<String>();
		ArrayList<String> confWord = new ArrayList<String>();
		String s;
		
		// Read the file test_tokens_fake.txt and store each unigram in an array and form sentences for further processing
		while((s = br.readLine()) != null)
		{
			test.add(s); // All the unigrams from the file test_tokens_fake.txt are stored in an array for further processing
			
			// Forming sentences from the unigrams and storing it in the file test_sent_fake.txt
			if((s.compareTo("<s>")) != 0 && (s.compareTo("</s>")) != 0)
			{
				br1.write(s + " ");
			}
			if((s.compareTo("</s>")) == 0)
			{
				br1.write("\n");
			}
			if((s.compareTo("<s>")) == 0)
			{
				// Do nothing, just read the token and move onto the next token
			}
		}
		br.close();
		br1.close();
		
		// Read the file all_confusingWords.txt and store each confusing word in an array 
		while((s = br2.readLine()) != null)
		{
			for(int i = 0; i < s.length(); i++)
			{
				if((s.charAt(i)) == ':')
				{
					confWord.add((s.substring(0, i)));
					confWord.add((s.substring(i+1, s.length())));
				}
			}
		}
		br2.close();
		
		/*for(int i = 0; i < confWord.size(); i++)
		{
			System.out.println(confWord.get(i));
		}*/
		
		// getting the confusing word and the previous word and storing the bigram in the file all_confusingWordsBigrams.txt
 		for(int i = 0; i < confWord.size(); i++)
		{
			for(int j = 0; j < test.size(); j++)
			{
				if(((confWord.get(i)).compareTo(test.get(j))) == 0 && ((test.get(j-1)).compareTo("<s>")) != 0)
				{
					br3.write(test.get(j-1) + test.get(j) + "\n");
				}
			}
		}
		br3.close();
		
		// open the file all_ConfusingWordsBigrams.txt to read
        Scanner console = new Scanner(System.in);
        Scanner input = new Scanner(new File("results/all_confusingWordsBigrams.txt"));
		
		Map<String, Integer> wordCounts = new TreeMap<String, Integer>();
        while (input.hasNext()) {
            String next = input.next().toLowerCase();
            if (!wordCounts.containsKey(next)) {
                wordCounts.put(next, 1);
            } else {
                wordCounts.put(next, wordCounts.get(next) + 1);
            }
        }
		
		// get the frequency of each bigram and print and store the count in an ArrayList to later compute the conditional probability
		ArrayList<Integer> countb = new ArrayList<Integer>();
        System.out.println("Total words = " + wordCounts.size());
        for (String word : wordCounts.keySet()) {
				int count = wordCounts.get(word);
				countb.add(count);
                System.out.println(word + "\t" + count);				
        }
		// System.out.println(countb.size());	
		
		// Creating an ArrayList that stores only the previous words alphabetically as present in the bigrams above
		ArrayList<String> prevw = new ArrayList<String>();
		prevw.add(",");
		prevw.add("-lrb-");
		prevw.add("...");
		prevw.add("actually");
		prevw.add("affect");
		prevw.add("again");
		prevw.add("an");
		prevw.add("and");
		prevw.add("any");
		prevw.add("background");
		prevw.add("bad");
		prevw.add("basic");
		prevw.add("cup");
		prevw.add("day.my");
		prevw.add("delay");
		prevw.add("detune");
		prevw.add("distortion");
		prevw.add("drastically");
		prevw.add("easiest");
		prevw.add("either");
		prevw.add("every");
		prevw.add("field");
		prevw.add("flats");
		prevw.add("foggy");
		prevw.add("good");
		prevw.add("great");
		prevw.add("guitar");
		prevw.add("i");
		prevw.add("incorrect");
		prevw.add("it");
		prevw.add("its");
		prevw.add("like");
		prevw.add("limitless");
		prevw.add("long");
		prevw.add("model");
		prevw.add("modulation");
		prevw.add("most");
		prevw.add("multifunction");
		prevw.add("my");
		prevw.add("n't");
		prevw.add("n't");
		prevw.add("no");
		prevw.add("nobody");
		prevw.add("not");
		prevw.add("octave");
		prevw.add("octving");
		prevw.add("ok");
		prevw.add("or");
		prevw.add("overdrive");
		prevw.add("particular");
		prevw.add("quality");
		prevw.add("reverb");
		prevw.add("similar");
		prevw.add("sound");
		prevw.add("subtle");
		prevw.add("tabletop");
		prevw.add("the");
		prevw.add("the");
		prevw.add("the");
		prevw.add("this");
		prevw.add("this");
		prevw.add("to");
		prevw.add("to");
		prevw.add("to");
		prevw.add("tremolo");
		prevw.add("units");
		prevw.add("unusable");
		prevw.add("will");
		prevw.add("working.my");
		prevw.add("would");
		
		// Creating an ArrayList that stores only the confusing words alphabetically as present in the bigrams above
		ArrayList<String> actConfw = new ArrayList<String>();
		actConfw.add("accept");
		actConfw.add("accept");
		actConfw.add("accept");
		actConfw.add("effect");
		actConfw.add("accept");
		actConfw.add("accept");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("accept");
		actConfw.add("affect");
		actConfw.add("accept");
		actConfw.add("advise");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("affect");        
		actConfw.add("affect"); 
		actConfw.add("affect");
		actConfw.add("except");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("accept");
		actConfw.add("affect");
		actConfw.add("accept");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("advice");
		actConfw.add("advise");
		actConfw.add("altar");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("accept");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("affect");	
		actConfw.add("advise");
		actConfw.add("affect");
		actConfw.add("advise");
		actConfw.add("altar");
		actConfw.add("effect");
		actConfw.add("affect");
		actConfw.add("accept");
		actConfw.add("altar");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("accept");
		actConfw.add("affect");
		actConfw.add("accept");
		actConfw.add("affect");
		actConfw.add("aloud");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("effect");
		actConfw.add("isle");
		actConfw.add("accept");
		actConfw.add("affect");
		actConfw.add("affect");
		actConfw.add("effect");
		actConfw.add("except");
		actConfw.add("affect");
		actConfw.add("accept");
		actConfw.add("accept");
		actConfw.add("effect");   
		actConfw.add("advise");
		actConfw.add("advice");	
		
		// now count the frequencies of previous word stored in ArrayList prevw and the confusing word stored in ArrayList actConfw in the corpus
		ArrayList<Integer> prevf = new ArrayList<Integer>();
		ArrayList<Integer> confwf = new ArrayList<Integer>();
		
		// Calculating the frequency of each of the previous word and storing it an ArrayList
		for(int i = 0; i < prevw.size(); i++)
		{
			int ct = 0;
			for(int j = 0; j < test.size(); j++)
			{
				if(((prevw.get(i)).compareTo(test.get(j))) == 0)
				{
					ct++;
				}
			}
			prevf.add(ct);
		}
		// System.out.println(prevf.size());
		
		// Calculating the frequency of each of the confusing word and storing it an ArrayList
		for(int i = 0; i < actConfw.size(); i++)
		{
			int ct = 0;
			for(int j = 0; j < test.size(); j++)
			{
				if(((actConfw.get(i)).compareTo(test.get(j))) == 0)
				{
					ct++;
				}
			}
			confwf.add(ct);
		}
		// System.out.println(confwf.size());
		
		// Calculating the conditional probability and calculating the sentence id and ciloumn position where error is found storing the result in test_predictions.txt
		BufferedWriter br7 = new BufferedWriter(new FileWriter("results/test_predictions.txt"));
		for(int i = 0; i < countb.size(); i++)
		{
			if((((double)(countb.get(i)))/(prevf.get(i))) < (((double)(countb.get(i)))/(confwf.get(i))))
			{
				for(int j = 1; j < test.size(); j++)
				{
					if((((test.get(j-1))+(test.get(j))).compareTo(((prevw.get(i))+(actConfw.get(i))))) == 0)
					{
						ArrayList<Integer> colpos = new ArrayList<Integer>(); 
						int sentid = 0, col = 0;
						for(int k = j-2 ; k >= 0; k--)
						{
							if(((test.get(k)).compareTo("<s>")) == 0)
							{
								sentid++;
								colpos.add(k);
							}														
						}
						col = j-(colpos.get(0))-1;
						br7.write(""+(sentid-1) + ":" + ""+col + "," + "\n");
					}
				}
			}
		}
		br7.close();
		br4.close();
		br5.close();
		br6.close();
	}
}