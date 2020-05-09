import java.util.ArrayList;

public class Main {

    public static void main(String[]args){
        City_Loader loader = new City_Loader(12);
        ArrayList<City> cities = loader.getCities();

        Population pObject = new Population(cities);
        ArrayList<TSPChromosome> initial_population = pObject.getPopulation();
        Genetic_TSP tsp = new Genetic_TSP(cities,initial_population);
        tsp.printFitter();
    }

}
