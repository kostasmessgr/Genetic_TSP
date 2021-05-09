
//city class
public class City {

    private double longitude;
    private double lattitude;
    private int city_id;
    private String city_name;

    public City(String city_name,int city_id,double longitude,double lattitude){
        this.longitude=longitude;
        this.lattitude=lattitude;
        this.city_id=city_id;
        this.city_name=city_name;
    }

    public double calculateDistance(City c){
        double long_distance = Math.abs(this.longitude-c.getLongitude());
        double latt_distance = Math.abs(this.lattitude-c.getLattitude());
        return Math.sqrt(Math.pow(long_distance,2)+Math.pow(latt_distance,2));
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String cityStr(){
        return city_name+" with id: "+city_id+" and coordinates("+lattitude+","+longitude+")";
    }
}
