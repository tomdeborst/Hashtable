public interface RHashtableInt{

    public boolean put(Record r); // use linear probing to resolve collisions
    public int getCollisions(); // return count of collisions
    public int getTotalCollisions(); // return total amount of collisions over all put's
    public float getLoad(); //  return load factor for N records in table of size S:  (float) N/S

}