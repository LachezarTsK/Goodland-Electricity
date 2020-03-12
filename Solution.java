import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int numberOfCities = scanner.nextInt();
    int range_fromOneSide = scanner.nextInt();
    int[] cities = new int[numberOfCities];

    for (int i = 0; i < numberOfCities; i++) {
      cities[i] = scanner.nextInt();
    }
    scanner.close();
    int result = find_minimumNumber_of_powerPlants(cities, range_fromOneSide);
    System.out.println(result);
  }

  /**
   * Searches for the minimum possible power plants that cover all cities. Power plants are possible
   * to be built only in cities with an array value of '1'.
   *
   * Each side of a city with a power plant covers 'range_fromOneSide-1' number of cities. 
   * Thus, including the city with the power plant, the number of cities covered from both sides 
   * is '2*range_fromOneSide-1'.
   *
   * @return A positive integer, representing the minimum number of cities with power plants that
   *         cover all cities. If it is not possible to cover all cities, as per the stated conditions,
   *         it returns '-1'.
   */
  private static int find_minimumNumber_of_powerPlants(int[] cities, int range_fromOneSide) {

    int total_powerPlants = 0;
    int index_suitableCity = -1;

    /**
     * Checks for the first suitable city to build a power plant.
     * This checking covers only the first city/cities from one side
     * of the potential first power plant. All other checks are handled by
     * the while-loop after that.
     *
     */
    for (int i = 0; i < range_fromOneSide; i++) {
      if (cities[i] == 1) {
        index_suitableCity = i;
      }
    }
    if (index_suitableCity == -1) {
      return -1;
    }

    total_powerPlants++;
    int coveredCities = 1;
    int i = index_suitableCity + 1;

    /**
     * Having already the first power plant, the while-loop checks the distance in-between two
     * possible cities that are suitable for power plants, i.e. it checks the series of next cities,
     * each series having a maximum number of '2*range_fromOneSide -1' cities.
     *
     * The while-loop also hanldes the check for the distance from the last city with a power plant
     * to the last city in the array.
     */
    while (i < cities.length) {

      index_suitableCity = -1;
      while (i < cities.length && coveredCities < 2 * range_fromOneSide) {
        if (cities[i] == 1) {
          index_suitableCity = i;
        }
        i++;
        coveredCities++;
      }

      /**
       * The distance from the previous power plant to the last city is less than range_fromOneSide.
       */
      if (coveredCities < range_fromOneSide) {
        return total_powerPlants;
      }

      /**
       * The distance from the previous power plant to the next potentially suitable city
       * is greater than '2*range_fromOneSide-1'.
       */
      if (index_suitableCity == -1) {
        return -1;
      }

      /** 
      * The distance from the next power plant to the last city in the array is less than range_fromOneSide. 
      */
      if (cities.length - 1 - index_suitableCity < range_fromOneSide) {
        total_powerPlants++;
        return total_powerPlants;
      }

      coveredCities = 1;
      total_powerPlants++;
      i = index_suitableCity + 1;
    }
    return total_powerPlants;
  }
}
