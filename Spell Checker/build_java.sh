#!/bin/bash

if [ "tokenize" = $1 ]; then
	javac -cp jars/stanford-corenlp-3.8.0.jar:./ src/Tokenizer.java -d bin/

elif [ "estimate" = $1 ]; then
	javac -cp jars/commons-math3-3.6.1.jar:./ src/ProbEstimator.java -d bin/

elif [ "predict" = $1 ]; then
	javac src/Predictor.java -d bin/

elif [ "generate" = $1 ]; then
	javac src/TestDataGenerator.java -d bin/

elif [ "evaluate" = $1 ]; then
	javac src/Evaluator.java -d bin/
fi

