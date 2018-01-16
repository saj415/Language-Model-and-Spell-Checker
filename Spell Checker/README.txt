Sachin Joshi
Project 1 NLP 
CSE 498 Fall 2017

ProbEstimator.java
This class reads the file train_tokens.txt as input and outputs the following text files:  
Input: data/train_tokens.txt

Output:
1. results/bigarms.txt: Stores the bigrams obtained from the file data/train_tokens.txt.
2. results/freq.txt: Additional text file and contains the frequency of each bigram.
3. results/ff.txt: Contains the frequency of frequency. The result is stored in the form of 2 columns: C (frequency of bigram) and NC (frequency of bigrams with frequency C).
4. results/GTTable.txt: The file contains C* for each C and the corresponding P(w, v) using GT smoothing.
5. results/prob_Lap.txt: This file contains the frequency of each bigram and the corresponding probability using Laplacian Smoothing. 

Predictor.java
This class reads the following text files:
1. data/test_tokens_fake.txt: Contains the testing data with misspellings.
2. data/all_confusingWords.txt: Contains a list of ambiguous words.
3. results/bigrams.txt: Obtained from the above ProbEstimator.java class and contains the bigrams.
4. results/ff.txt: Obtained from the above ProbEstimator.java class and contains the frequency of frequency.
5. results/GTTable.txt: Obtained from the above ProbEstimator.java class and contains the value of possible C* for every C and the corresponding P(w, v) using GT smoothing.

The class outputs the following text files:
1. results/test_sent_fake.txt: This file contains the sentences formed from the unigrams present in the file test_tokens_fake.txt.
2. results/all_confusingWordsBigrams.txt:This file contains all the possible bigrams that contain the any of the confusing token.
3.test_predictions.txt: This file contains the file result with the sentence id and the location of the token where an error is found.           
Compiling and Running the Project
Use the bash files to compile and run the src files.
./build_java.sh tokenize
./build_java.sh estimate
./build_java.sh predict
./build_java.sh evaluate

./run_java.sh tokenize
./run_java.sh estimate
./run_java.sh predict
./run_java.sh evaluate

Test your bash scripts to make sure that, by running the above 8 lines, your project compiles and produces the desirable output.

