/* [GridTest.java]
 * A program to demonstrate usage of DisplayGrid.java.
 * @author Mangat
 */

import java.sql.SQLOutput;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter the side length of the grid (Recommended 75 - 200): ");
        int sidelength = input.nextInt();

        System.out.println("Enter the amount of humans (Recommended = side length * 3): ");
        int humans = input.nextInt();

        System.out.println("Enter the amount of zombies (Recommended = humans/2): ");
        int zombies = input.nextInt();

        System.out.println("Enter the grass grow rate in percentage (Recommended 3% - 7%): " );
        double grassGrowRate = input.nextDouble() / 100.0;

        System.out.println("Enter the the tick length of the program (Recommended 30ms - 100ms): ");
        int delay = input.nextInt();

        System.out.println("Rapid reproduction mode? (Age and hunger does not matter - y/n): ");
        boolean rapid;
        if (input.next().equals("y")) {
            rapid = true;
        } else {
            rapid = false;
        }
        System.out.println("Starting in 3..");
        try{ Thread.sleep(1000); }catch(Exception e) {}
        System.out.println("2..");
        try{ Thread.sleep(1000); }catch(Exception e) {}
        System.out.println("1..");
        try{ Thread.sleep(1000); }catch(Exception e) {}
        System.out.println("0");

        MapDatabase map = new MapDatabase(sidelength, humans, zombies, grassGrowRate, rapid) ;

        MatrixDisplayWithMouse grid = new MatrixDisplayWithMouse("title", map);

        int counter = 0;
        while(true) {
            //Display the grid on a Panel
            grid.refresh();


            //Small delay
            try{ Thread.sleep(delay); }catch(Exception e) {};

            if (counter > 120) {
                counter = 0;
                map.makeNuke(50, 50, 25);
            }
            map.growGrass();
            map.moveAll();

            //Display the grid on a Panel
            grid.refresh();

            counter ++;
            if (zombies == -2000) {
                break;
            }
        }

        input.close();
    }

    //method to display grid a text for debugging
    public static void DisplayGridOnConsole(MapDatabase map) {
        for(int i = 0; i<map.getMap().length;i++){
            for(int j = 0; j<map.getMap()[0].length;j++)
                System.out.print(map.getMap()[i][j]+" ");
            System.out.println("");
        }
    }

}