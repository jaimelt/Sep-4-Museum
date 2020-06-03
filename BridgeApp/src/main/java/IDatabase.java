/**
 * The interface representing the repository for accessing the data storage.
 *
 * @author Marina Ionel
 * @version 1.0.0
 */
public interface IDatabase {
    /**
     * Insert a document with the specified parameters (sensor data) to the sep4 mongoDb database.
     *
     * @param co2         The value of co2 (in ppm) as a integer
     * @param humidity    The value of humidity as double
     * @param temperature The value of temperature as double
     * @param light       The value of light (in lumens) as double
     * @param roomId      The id corresponding to the room where the hardware is located
     */
    void insert(int co2, double humidity, double temperature, double light, int roomId);
}
