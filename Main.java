/* [GridTest.java]
 * A program to demonstrate usage of DisplayGrid.java.
 * @author Mangat
 */

class Main {
    public static void main(String[] args) {

        MapDatabase map = new MapDatabase(100, 100, 100, 100, 0) ;


        // display the fake grid on Console
        //DisplayGridOnConsole(map);

        //Set up Grid Panel
        // DisplayGrid grid = new DisplayGrid(map);
        MatrixDisplayWithMouse grid = new MatrixDisplayWithMouse("title", map);

        while(true) {
            //Display the grid on a Panel
            grid.refresh();


            //Small delay
            try{ Thread.sleep(20); }catch(Exception e) {};

            map.growGrass();
            map.moveAll();

            //Display the grid on a Panel
            grid.refresh();
        }
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