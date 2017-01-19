import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;


public class Tests {

    private ArrayList<Integer> listA = new ArrayList<>();
    private ArrayList<Integer> listB = new ArrayList<>();
    private TreeSet<Integer> setA = new TreeSet<>();
    private TreeSet<Integer> setB = new TreeSet<>();
    private LinkedHashMap<String, String> map = new LinkedHashMap<>(false);

    @After
    public void afterTest(){
        listA.clear();
        listB.clear();
        setA.clear();
        setB.clear();
        map.clear();
    }



    // ArrayList

    @Test
    public void testListSize() {
        listA.add(1);
        listA.add(2);
        listA.add(3);
        Assert.assertTrue(listA.size() == 3);
    }

    @Test
    public void testListContains() {
        listA.add(1);
        listA.add(2);
        listA.add(3);
        Assert.assertTrue(listA.contains(1) && !setA.contains(4));
    }

    @Test
    public void testListAdd() {
        listA.add(1);
        listA.add(2);
        listA.add(3);
        Assert.assertTrue(listA.contains(1) && listA.contains(2) && listA.contains(3));
    }

    @Test
    public void testListGet() {
        listA.add(1);
        listA.add(2);
        listA.add(3);
        Assert.assertTrue(listA.get(2) == 3);
    }

    @Test
    public void testListRemove() {
        listA.add(1);
        listA.add(2);
        listA.add(3);
        listA.remove(2);
        Assert.assertTrue(listA.size() == 2);
    }


    // TreeSet

    @Test
    public void testSetSize() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(4);
        Assert.assertTrue(setA.size() == 4);
    }

    @Test
    public void testSetContains() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        Assert.assertTrue(setA.contains(1) && !setA.contains(4));
    }

    @Test
    public void testSetAdd() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(3);
        Assert.assertTrue(setA.size() == 3);
    }

    @Test
    public void testSetRemove() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.remove(1);
        Assert.assertTrue(setA.size() == 2);
    }




    //LinkedHashMap


    @Test
    public void testMapSize() {
        map.put("AB", "CD");
        map.put("EF", "GH");
        map.put("IJ", "KL");
        Assert.assertTrue(map.size() == 3);
    }

    @Test
    public void testMapContains() {
        map.put("AB", "CD");
        map.put("EF", "GH");
        map.put("IJ", "KL");
        Assert.assertTrue(map.containsKey("AB"));
    }

    @Test
    public void testMapRemove() {
        map.put("AB", "CD");
        map.put("EF", "GH");
        map.put("IJ", "KL");
        map.remove("AB");
        Assert.assertTrue(map.size() == 2);
    }

    @Test
    public void testMapGet() {
        map.put("AB", "CD");
        map.put("EF", "GH");
        map.put("IJ", "KL");
        Assert.assertTrue(map.get("AB").equals("CD"));
    }

    @Test
    public void testMapPut() {
        map.put("AB", "CD");
        map.put("EF", "GH");
        map.put("IJ", "KL");
        Assert.assertTrue(map.containsKey("AB") && map.containsKey("EF") && map.containsKey("IJ"));
    }


}