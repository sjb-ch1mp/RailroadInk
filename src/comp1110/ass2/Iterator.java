package comp1110.ass2;

import java.util.HashMap;
import java.util.function.IntPredicate;

public class Iterator
{
    //The directions hash map makes it possible to iterate through each edge of a tile being evaluated: N, E, S, W
    private HashMap<Integer, Character> directions;

    //The tests hash map makes it possible to iterate through appropriate tests for whether an edge evaluation is necessary
    private HashMap<Integer, IntPredicate> tests;

    public Iterator()
    {
        directions = new HashMap<>(0);
        directions.put(0, 'N');
        directions.put(1, 'E');
        directions.put(2, 'S');
        directions.put(3, 'W');

        //The parameter x can be either a row or column, depending upon which edge is being evaluated
        tests = new HashMap<>(0);
        tests.put(0, x -> x > 65); //if the row is greater than 'A', IntPredicate returns true
        tests.put(1, x -> x < 6); //if the column is less than 6, IntPredicate returns true
        tests.put(2, x -> x < 71); //if the row is less than 'G', IntPredicate returns true
        tests.put(3, x -> x > 0); //if the column is greater than 0, IntPredicate returns true
    }

    public char getDirection(int i)
    {
        return directions.get(i);
    }

    public boolean edgeIsNotOnRim(int i, int testValue)
    {
        return tests.get(i).test(testValue);
    }
}
