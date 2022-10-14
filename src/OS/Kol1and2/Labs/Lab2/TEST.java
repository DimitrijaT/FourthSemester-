package OS.Kol1and2.Labs.Lab2;


class InstanceTest {
    private static int x = 0;

    public InstanceTest() {
        x++;
    }

    public int getX() {
        return x;
    }
}


public class TEST {
    public static void main(String[] args) {
        InstanceTest instanceOne = new InstanceTest();
        InstanceTest instanceTwo = new InstanceTest();
        InstanceTest instanceThree = new InstanceTest();
        InstanceTest instanceFour = new InstanceTest();

        System.out.println(instanceOne.getX());
        System.out.println(instanceTwo.getX());
        System.out.println(instanceThree.getX());
        System.out.println(instanceFour.getX());
    }

}
