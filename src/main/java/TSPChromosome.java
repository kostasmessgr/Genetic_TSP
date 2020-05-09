import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TSPChromosome {

    private int tour_size;
    private ArrayList<City> chromosome;
    private double fitness;
    private double tsp_distance;

    public TSPChromosome(ArrayList<City> chromosome){
        this.chromosome = chromosome;
        this.tour_size=chromosome.size();
        tsp_distance=calcTotalDistance();
        this.fitness=calculate_Fitness();
    }

    public ArrayList<City> getChromosome() {
        return chromosome;
    }

    public String chromosomeStr(){
        String res="";
        for(City c:chromosome){
            res+=c.getCity_name()+"-";
        }
        return res;
    }

    private double calculate_Fitness(){
        return 1/tsp_distance;
    }


    private double calcTotalDistance(){
        double total_distance=0;
        City from=null;
        City to=null;
        for(int i=0;i<chromosome.size()-1;i++) {
            from = chromosome.get(i);
            to = chromosome.get(i+1);
            total_distance += from.calculateDistance(to);
        }
        total_distance+=to.calculateDistance(chromosome.get(0));
        //System.out.println(total_distance);
        return total_distance;
    }

    public int getTour_size() {
        return tour_size;
    }

    public double getFitness() {
        return fitness;
    }

    public double getTsp_distance() {
        return tsp_distance;
    }

    public boolean containsCity(City c){
        return chromosome.contains(c);
    }
}
