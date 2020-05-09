import java.util.*;

public class ChromosomeHandler {


    private int chromosomeSize;
    private ArrayList<City> cities;
    private final double mutationRate=0.1;
    private final double crossoverRate=0.3;
    Random r;

    public ChromosomeHandler(ArrayList<City> cities){
        this.cities=cities;
        chromosomeSize=cities.size();
        r= new Random();
    }

    public TSPChromosome generateRandomChromosome(){
        ArrayList<City> chromosome = new ArrayList<City>();
        for(City c:cities){
            chromosome.add(c);
        }
        Collections.shuffle(chromosome);
        TSPChromosome result =new TSPChromosome(chromosome);
        //System.out.println(result.chromosomeStr());
        return result;
    }

    public ArrayList<TSPChromosome> getSorted(ArrayList<TSPChromosome> chromosomes){
        Collections.sort(chromosomes, Comparator.comparing(s -> s.getFitness()));
        Collections.reverse(chromosomes);
//        for(TSPChromosome c:chromosomes){
//            System.out.println(c.getFitness());
//        }
        return chromosomes;
    }

    public ArrayList<TSPChromosome> applyElitism(ArrayList<TSPChromosome> population,List<TSPChromosome> elite){
        ArrayList<TSPChromosome> result = population;
        Collections.sort(result, Comparator.comparing(s -> s.getFitness()));
        int startDel = population.size()-elite.size();
        for(int i=startDel;i<population.size();i++){
            result.remove(i);
        }
        for(int j=0;j<elite.size();j++){
            result.add(elite.get(j));
        }
        //System.out.println(result.size()+" "+population.size());
        return result;
    }

    public boolean isValidTSP(TSPChromosome chromosome){
        Set <City> chromosomeAsSet = new HashSet<City>(chromosome.getChromosome());
        boolean everyCity = true;
        for(City c:cities){
            if(!chromosome.containsCity(c)) everyCity=false;
        }
        if((chromosome.getChromosome().size()==chromosomeAsSet.size())&&(everyCity==true)){
            return true;
        }
        return false;
    }

    public TSPChromosome mutation(TSPChromosome chromosome){
        TSPChromosome newChromosome = new TSPChromosome(chromosome.getChromosome());
        for(int i=0;i<chromosome.getChromosome().size();i++) {
            if(Math.random()<mutationRate) {
                int pos1 = (int) (Math.random() * chromosome.getChromosome().size());
                int pos2 = (int) (Math.random() * chromosome.getChromosome().size());

                Collections.swap(newChromosome.getChromosome(), pos1, pos2);
            }
        }
        return newChromosome;
    }

    public TSPChromosome crossover(TSPChromosome parent1,TSPChromosome parent2){
        if(Math.random()<crossoverRate) {
            int start = r.nextInt(parent1.getTour_size() - 1);
            int left = parent1.getTour_size() - start;
            int end = r.nextInt(left);
            return calcCrossover(parent1, parent2, start, end);
        }
        return parent1;
    }

    private TSPChromosome calcCrossover(TSPChromosome parent1,TSPChromosome parent2,int start,int end){
        ArrayList<City> result = new ArrayList<City>();
        for(int i=start;i<end;i++){
            result.add(parent1.getChromosome().get(i));
        }
        for(int j=0;j<parent2.getChromosome().size();j++){
            if(!result.contains(parent2.getChromosome().get(j))) result.add(parent2.getChromosome().get(j));
        }
        return new TSPChromosome(result);
    }

}
