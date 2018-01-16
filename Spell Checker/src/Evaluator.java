import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashSet;

import java.lang.Math;

public class Evaluator {

	private Set<String> groundTruths;

	private Set<String> predictions;
	
	private Set<String> readLocations(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		String cur_line = null;

		Set<String> results = new HashSet<String>();

		while ((cur_line = br.readLine()) != null) {
			String[] parts = cur_line.split(":");
			String[] word_locations = parts[1].split(",");

			for (int i = 0; i < word_locations.length; ++i) {
				results.add(parts[0] + " " + word_locations[i]);
			}
		}
		return results;
	}

	private void readPredictions(String fileName) throws IOException {
		predictions = readLocations(fileName);
		// System.out.println("\nNumber of detected errors:" + predictions.size() + "\n");
	}

	private void readGroundTruths(String fileName) throws IOException {
		groundTruths = readLocations(fileName);
		// System.out.println("\nNumber of actual errors:" + groundTruths.size() + "\n");
	}

	private void calculatePerformance() {
		Set<String> hit = new HashSet<String>(groundTruths);
		hit.retainAll(predictions);
		double precision = (double) hit.size() / (double) predictions.size();
		double recall = (double) hit.size() / (double) groundTruths.size();
		double f1 = (precision * recall) / (precision + recall);

		System.out.println("\nPrecision: " + Double.toString(precision));
		System.out.println("\nRecall: " + Double.toString(recall));
		System.out.println("\nF1: " + Double.toString(f1));
	}

	public static void main(String[] args) throws IOException {
		Evaluator eval = new Evaluator();

		String groundTruthFileName = args[0];
		String predictionFileName = args[1];

		eval.readPredictions(predictionFileName);
		eval.readGroundTruths(groundTruthFileName);

		eval.calculatePerformance();
	}
}
