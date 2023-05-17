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
        MapDatabase map;

        int sidelength = input.nextInt();
        int delay = 25;
        if (sidelength == -1) {
            map = new MapDatabase(250, 750, 375, 0.05, 50) ;
        } else{
            System.out.println("Enter the amount of humans (Recommended = side length * 3): ");
            int humans = input.nextInt();

            System.out.println("Enter the amount of zombies (Recommended = humans/2): ");
            int zombies = input.nextInt();

            System.out.println("Enter the grass grow rate in percentage (Recommended 3% - 7%): " );
            double grassGrowRate = input.nextDouble() / 100.0;

            System.out.println("Enter the the tick length of the program (Recommended 30ms - 100ms): ");
            delay = input.nextInt();

            System.out.println("Enter the nuke radius (Recommended = side length/4): ");
            double radius = input.nextInt() + 0.5;



            map = new MapDatabase(sidelength, humans, zombies, grassGrowRate, radius) ;
        }

        MatrixDisplayWithMouse grid = new MatrixDisplayWithMouse("title", map);

        while(true) {
            //Display the grid on a Panel
            grid.refresh();


            //Small delay
            try{ Thread.sleep(delay); }catch(Exception e) {};

            map.growGrass();
            map.moveAll();

            //Display the grid on a Panel
            grid.refresh();


            if (delay == -2000) {
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