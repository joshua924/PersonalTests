package lc.sz1288;

import org.junit.Test;

public class CanvasTest {
  private final Canvas canvas = new Canvas(25, 10);

  @Test
  public void testMove() {
    System.out.println("----------------------------------------------------");
    canvas.printCanvas();
    System.out.println("----------------------------------------------------");
    canvas.createRectangle(0, 0, 6, 4, 'a');
    canvas.createRectangle(4, 21, 4, 6, 'b');
    Canvas.Shape c = canvas.createRectangle(2, 5, 5, 5, 'c');
    canvas.printCanvas();
    System.out.println("-------------------after move-----------------------");
    canvas.moveRectangle(c, 2, 7);
    canvas.printCanvas();
  }

  @Test
  public void testDelete() {
    System.out.println("----------------------------------------------------");
    canvas.createRectangle(0, 0, 6, 4, 'a');
    canvas.createRectangle(4, 21, 4, 6, 'b');
    Canvas.Shape c = canvas.createRectangle(2, 5, 5, 5, 'c');
    canvas.printCanvas();
    System.out.println("-------------------after delete-------------------");
    canvas.deleteRectangle(c);
    canvas.printCanvas();
  }
}