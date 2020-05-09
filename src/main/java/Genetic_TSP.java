import java.util.ArrayList;
import java.util.List;

public class Genetic_TSP {

    private final int iterations=200;
    private ArrayList<TSPChromosome> population;
    private ArrayList<City> cities;
    private ChromosomeHandler handler;
    private ArrayList<TSPChromosome> better;

    public Genetic_TSP(ArrayList<City> cities,ArrayList<TSPChromosome> initialPopulation){
        this.population=initialPopulation;
        this.cities=cities;
        handler = new ChromosomeHandler(cities);
        better = new ArrayList<TSPChromosome>();
        start();
    }

    public void start(){

        for(int i=0;i<iterations;i++){
            ArrayList<TSPChromosome> newPopulation = new ArrayList<TSPChromosome>();
            ArrayList<TSPChromosome> sorted = handler.getSorted(population);
            List<TSPChromosome> elite = getElite(sorted,0.1,sorted.size());
            for(int j=0;j<population.size();j++){
                TSPChromosome parent1 = sorted.get(0);
                TSPChromosome parent2 = sorted.get(1);

                TSPChromosome offspring = handler.crossover(parent1,parent2);
                newPopulation.add(handler.mutation(offspring));
            }
            newPopulation=handler.applyElitism(newPopulation,elite);
            ArrayList<TSPChromosome> newSorted = handler.getSorted(newPopulation);
            System.out.println("Iteration "+i);
//            for(int j=0;j<newSorted.size();j++){
//                System.out.println(newSorted.get(j).chromosomeStr()+" "+newSorted.get(j).getTsp_distance());
//            }
//            System.out.println("Elite"+elite.size()+" \n");
//            for(int m=0;m<elite.size();m++){
//                System.out.println(elite.get(m).getTsp_distance());
//            }
            better.add(handler.getSorted(newPopulation).get(0));
            population=newPopulation;
        }
    }

    private List<TSPChromosome> getElite(ArrayList<TSPChromosome> sorted, double elitismRate, int populationSize){
        int num = (int)(elitismRate*populationSize);
        //System.out.println("eliiii: "+num+" "+elitismRate+" "+populationSize+" "+(int)(elitismRate*populationSize));
        return sorted.subList(0,num);
    }

    public void printFitter(){
        int k=0;
        for(TSPChromosome c:better){
            System.out.println("iteration : "+k+" "+c.chromosomeStr()+" distance: "+c.getTsp_distance());
            k++;
        }
    }


}
