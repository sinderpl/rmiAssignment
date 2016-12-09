import java.io.File;

import ie.gmit.sw.algoType;
import ie.gmit.sw.Algorithms;
/**
 * 
 * @author G00313177
 *
 *Compares the algorithm input and returns the correct instance.
 *Singleton factory
 */
public class AlgorithmFactory {
	//Single instance variable of type factory
	AlgorithmFactory factory = new AlgorithmFactory();
	//private constructor since it is a singleton 
	private AlgorithmFactory(){};
	//Return the factory instance
	public AlgorithmFactory getInstance(){
		return factory;
	}
	//Chooses and creates the right instance of the algorithm specified returning it to the client
	public algoType createAlgorithm(String algoName){
		algoType returnAlgo;
		
		//Implemented from https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
		//Choose the algorithm based on a switch statement
		switch(algoName){
			case "Damerau-Levenshtein Distance":  returnAlgo = new DamerauLevenshtein();
			case "Hamming Distance":  returnAlgo = new HammingDistance();
			case "Levenshtein Distance":  returnAlgo = new Levenshtein();
			
			//future cases if neededs
			//case "Damerau-Levenshtein Distance":  returnAlgo = new DamerauLevenshtein();
			//case "Damerau-Levenshtein Distance":  returnAlgo = new DamerauLevenshtein();
			//case "Damerau-Levenshtein Distance":  returnAlgo = new DamerauLevenshtein();
			//case "Damerau-Levenshtein Distance":  returnAlgo = new DamerauLevenshtein();
			//case "Damerau-Levenshtein Distance":  returnAlgo = new DamerauLevenshtein();			
		}
	}
	
}
