#!/bin/bash

if [ "tokenize" = $1 ]; then
	# tokenize raw texts into sentences and tokens
	java -cp jars/stanford-corenlp-3.8.0.jar:bin/ Tokenizer data/train_reviews.txt data/train_tokens.txt
elif [ "estimate" = $1 ]; then
	# use training data to train a bigram model and Good-Turing estimations.
	java -cp jars/commons-math3-3.6.1.jar:bin/ ProbEstimator 
elif [ "predict" = $1 ]; then
	# generate predictions
	java -cp bin/ Predictor 
elif [ "evaluate" = $1 ]; then
	# evaluate the predictions
	java -cp bin/ Evaluator data/test_ground_truth.txt results/test_predictions.txt
fi
