package javafx_labs.lab_06_01.exercise_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Adam Johnston 2332003
 *         Class that allows the user to manage the saving and loading of a list
 *         of salaries.
 */
public class SalaryManager {
    /**
     * HashMap of salaries saved to the file.
     */
    private HashMap<Integer, Double> savedSalaries = new HashMap<>();

    /**
     * HashMap of salaries that are not yet saved to the file.
     */
    private HashMap<Integer, Double> unsavedSalaries = new HashMap<>();

    /**
     * ID of the last salary added to the unsaved salaries HashMap. Useful for
     * displaying unsaved salaries. Perhaps it's better to use a pseudo-random ID
     * system, rather than an ordered, incrementing one, but that seems to be
     * outside the scope of this assignment.
     */
    private Integer latestID = 0;

    /**
     * Path of the file to which salaries are saved.
     */
    private String path;

    /**
     * Sum of all salaries in the HashMap.
     */
    private Double sumOfSalaries = 0.0;

    SalaryManager(String path) {
        this.path = path;
        load();
    }

    public HashMap<Integer, Double> getSavedSalariesMap() {
        return savedSalaries;
    }

    public HashMap<Integer, Double> getUnsavedSalariesMap() {
        return unsavedSalaries;
    }

    /**
     * Add a salary to the unsaved salaries HashMap.
     * 
     * @param salary Sum to be added.
     */
    public void addSalary(Double salary) {
        for (int i = 0; i < savedSalaries.size() + unsavedSalaries.size() + 1; i++) {
            if (!unsavedSalaries.containsKey(i) && !savedSalaries.containsKey(i)) {
                sumOfSalaries += salary;
                unsavedSalaries.put(i, salary);
                latestID = i;
                return;
            }
        }
    }

    /**
     * Returns the ID of the last salary added to the unsaved salaries HashMap.
     * 
     * @return ID.
     */
    public Integer getLatestID() {
        return latestID;
    }

    /**
     * Returns the sum of all salaries (saved and unsaved).
     * 
     * @return Sum.
     */
    public Double getSumOfSalaries() {
        return sumOfSalaries;
    }

    /**
     * Removes all salaries that were not yet saved to the file from memory.
     */
    public void clearUnsaved() {
        for (int i = 0; i < unsavedSalaries.size() + savedSalaries.size(); i++) {
            if (unsavedSalaries.containsKey(i)) {
                sumOfSalaries -= unsavedSalaries.get(i);
            }
        }
        unsavedSalaries.clear();
    }

    /**
     * Removes all salaries, including those saved to the file. Clears the file too.
     */
    public void clearAll() {
        unsavedSalaries.clear();
        savedSalaries.clear();

        // Reset latest ID and sum.
        latestID = 0;
        sumOfSalaries = 0.0;

        File file = new File(path);
        if (!file.exists()) {
            return;
        }

        // Empty the file of its contents.
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file))) {
        } catch (FileNotFoundException ex) { // Exceptions should never occur.
        } catch (IOException ex) {
        }
    }

    /**
     * Load all saved salaries from the binary file into a HashMap.
     */
    private void load() {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(file))) {
            while (true) {
                Integer id = inputStream.readInt();
                Double salary = inputStream.readDouble();

                sumOfSalaries += salary; // Update the sum.

                savedSalaries.put(id, salary); // Add salary to HashMap.
            }
        } catch (EOFException ex) { // End of file reached; finished loading data.
            return;
        } catch (IOException ex) { // Failed to load salaries, continue without saved data.
            savedSalaries.clear();
            return;
        } finally {
        }
    }

    /**
     * Appends all unsaved salaries to the binary file.
     */
    public void save() {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) { // Exception should never occur.
            }
        }

        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file, true))) {
            // Append newly added salaries to the file.
            for (int key = 0; key < unsavedSalaries.size() + savedSalaries.size(); key++) {
                if (unsavedSalaries.containsKey(key)) {

                    // Write data to file.
                    outputStream.writeInt(key);
                    outputStream.writeDouble(unsavedSalaries.get(key));

                    // Add newly saved salary to saved salary map.
                    savedSalaries.put(key, unsavedSalaries.get(key));

                    // Remove newly saved salary from unsaved salary map.
                    unsavedSalaries.remove(key);
                }
            }
        } catch (FileNotFoundException ex) { // Exceptions should never occur.
        } catch (IOException ex) {
        }
    }
}
