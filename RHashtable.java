/**
 * @author Thomas de Borst
 * 1004302
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

/*
RHashtable class
 */
public class RHashtable implements RHashtableInt {

    private static String fileName;
    private static int tableSize;
    private static int recordsInHash;
    private static int collisions = 0;
    private static int totalCollisions = 0;

    private static final Record[] table = new Record[1000];

    public RHashtable(int size)
    {
        tableSize = size;
    }

    /**
     * I used this main method for testing purposes.
     * @param args Takes a file as argument.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        fileName = args[0];
        RHashtable rHashtable = new RHashtable(1000);
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        //String to hold a line from the file
        String line = reader.readLine();
        //Read line to skip the column headers in CSV
        line = reader.readLine();


        //While a line has been read
        while (line != null) {

            //Split values into CSV
            String[] values = (line.split(","));
            //Place values into temporary array
            Record record = new Record((values[0]), values[1], values[2]);
            //Add record to HashTable
            rHashtable.put(record);
            //Increment record counter
            recordsInHash++;
            //Add total collisions to total collision count
            totalCollisions += collisions;
            //Read new line
            line = reader.readLine();
        }
        //Print info for user
        System.out.println("Total records in HashTable : " + recordsInHash);
        System.out.println("Total Collisions in HashTable : " + totalCollisions);
        System.out.println("LoadFactor of HashTable : " + rHashtable.getLoad() + "%");

        //Print program runtime
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println(elapsedTime + " ms");
    }


    /**
     * Override method for inserting a record object into the HashTable
     * @param r Takes a record objet as an argument
     * @return Returns True when Record has been successfully added and false when not.
     * Method finds an empty space in HashTable based on HashCode from Name variable held in Record.
     * Counts collisions along the way.
     * Uses Quadratic probing to find empty spaces in HashTable, older commented code used Linear probing. See README
     * for comparative analysis.
     */
    @Override
    public boolean put(Record r) {
        collisions = 0;
        int index = r.hashCode() % tableSize;

        //Quadratic Probing                 //89 Collisions out of 500 : 17.8%
        if(table[index] == null)
        {
            insert(r, index);
            return true;
        }
        else
        {
            //Search for empty space
            for (int i = index; i < table.length; i++)
            {
                int adjust = i*i / tableSize;
                if(table[adjust] != null)
                {
                    collisions++;
                }
                else
                {
                    insert(r, index);

                    return true;
                }
            }
            //Wrap back around to Zero, start search for empty space again
            for (int i = 0; i != index; i++)
            {
                //Quadratic Probing                 //89 Collisions out of 500 : 17.8%
                int adjust = i*i / tableSize;
                if(table[adjust] != null)
                {
                    collisions++;
                }
                else
                {
                    insert(r, index);

                    return true;
                }
            }
            return false;
        }

        /*
        Linear probing     Best case in testing     //138 Collisions out of 500 : 27.6%
         */
//            if(table[index] == null)
//            {
//                insert(r, index);
//                getCollisions();
//                return true;
//            }
//            else
//            {
//                for (int i = index; i < table.length; i++)
//                {
//                    if(table[i] != null)
//                    {
//                            collisions++;
//                    }
//                    else
//                    {
//                            insert(r, index);
//                            getCollisions();
//                            return true;
//                    }
//                }
//                for (int i = 0; i != index; i++)
//                {
//                    if(table[i] != null)
//                    {
//                        collisions++;
//                    }
//                    else
//                    {
//                        insert(r, index);
//                        getCollisions();
//                        return true;
//                    }
//                }
//                return false;
//            }
    }

    /**
     * Method for returning collision count
     * @return Returns collision count
     */
    @Override
    public int getCollisions() {
        return collisions;
    }

    /**
     * Method for returning total collision count
     * @return Returns collision count
     */
    @Override
    public int getTotalCollisions() {
        return totalCollisions += collisions;
    }

    /**
     * Method for getting the LoadFactor of the HashTable.
     * The total number of records in the table divided by the table size.
     * This shows how full the table is.
     * @return returns a float value, which represents the percentage of the table that is full.
     */
    @Override
    public float getLoad() {
        float data = 0.0f;
        data = (float)recordsInHash / (float)tableSize;
        return data;
    }

    /**
     * Method for inserting a Record object at a given hashKey
     * @param r Takes a record object as argument.
     * @param hashKey Takes a HashKey as argument
     */
    public void insert(Record r, int hashKey) {

        table[hashKey] = r;
        recordsInHash++;
        getTotalCollisions();
        getLoad();

    }
}
