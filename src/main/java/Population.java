import java.util.ArrayList;

//Population Class
public class Population {

    private ArrayList<City> cities;
    private int population_number=50;
    private ArrayList<TSPChromosome> population;
    ChromosomeHandler handler;

    public Population(ArrayList<City> cities){
        this.cities=cities;
        population = new ArrayList<TSPChromosome>();
        handler = new ChromosomeHandler(cities);
        initialize();
    }

    private void initialize(){
        for(int i=0;i<population_number;i++){
            population.add(handler.generateRandomChromosome());
        }
    }

    public ArrayList<TSPChromosome> getPopulation() {
        return population;
    }

    public int getPopulation_number() {
        return population_number;
    }

    public void setPopulation(ArrayList<TSPChromosome> population) {
        this.population = population;
    }
}
