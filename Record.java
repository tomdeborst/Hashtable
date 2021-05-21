/**
 * @author Thomas de Borst
 * 1004302
 */

public class Record {

    public static String _name;
    public static String _phone;
    public static String _eMail;
    
    public Record(String name, String phone, String eMail)
    {
        _name = name;
        _phone = phone;
        _eMail = eMail;
    }

    public String get_name() {
        return _name;
    }

    /**
     * Method to generate a hash code based on the name read from a CSV file
     * @return Returns an int where Record objects can be inserted or found.
     */
    @Override
    public int hashCode() {
        String s = this.get_name();
        int data = 0;

        // First iteration of method...

//        for (int i = 0; i < s.toCharArray().length; i++) {
//            data += s.toCharArray()[i]*31^2;
//        }
//        return data;                  //183 Collisions out of 500 : 36.6%

        // Second iteration of method...

//        int count = s.toCharArray().length;
//        for (int i = 0; i < s.toCharArray().length; i++) {
//            data += s.toCharArray()[i]*31^(count);
//            count--;
//        }
//        return data;                    //185 Collisions out of 500 : 37%

        // Third iteration of method...

//        int count = 10;
//        for (int i = 0; i < s.toCharArray().length; i++) {
//            data += s.toCharArray()[i]*31^(count);
//            count--;
//        }
//        return data;                       //140 Collisions out of 500 : 28%

        // Fourth iteration of method...

        int count = 7;
        for (int i = 0; i < s.toCharArray().length; i++) {
            data += s.toCharArray()[i]*31^(count);
            count--;
        }
        return data;                       //138 Collisions out of 500 : 27.6%


    }
}
