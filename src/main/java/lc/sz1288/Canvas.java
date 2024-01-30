package lc.sz1288;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Canvas {

  private final Map<Integer, Map<Integer, Pixel>> pixels;
  private final int width;
  private final int height;

  private int layerCount;

  public Canvas(int width, int height) {
    this.width = width;
    this.height = height;
    this.layerCount = 0;
    this.pixels = new HashMap<>();
    for (int i = 0; i < height; i++) {
      Map<Integer, Pixel> map = new HashMap<>();
      for (int j = 0; j < width; j++) {
        map.put(j, new Pixel());
      }
      pixels.put(i, map);
    }
  }

  /**
   * this method is used to create a brand new rectangle into the canvas
   *
   * @return the newly created shape object representing the rectangle
   */
  public Shape createRectangle(int row, int col, int width, int height, char color) {
    return addRectangle(row, col, width, height, color, ++layerCount);
  }

  public void moveRectangle(Shape shape, int newRow, int newCol) {
    deleteRectangle(shape);
    addRectangle(newRow, newCol, shape.width, shape.height, shape.color, shape.layer);
  }

  public void deleteRectangle(Shape shape) {
    for (int i = shape.row; i < shape.row + shape.height; i++) {
      for (int j = shape.col; j < shape.col + shape.width; j++) {
        Pixel pixel = pixels.get(i).get(j);
        pixel.remove(shape.layer);
      }
    }
  }

  public void printCanvas() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        System.out.print(pixels.get(i).get(j).show());
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  private Shape addRectangle(int row, int col, int width, int height, char color, int layer) {
    Shape shape = new Shape(row, col, width, height, color, layer);
    for (int i = row; i < row + height; i++) {
      for (int j = col; j < col + width; j++) {
        Pixel pixel = pixels.get(i).get(j);
        pixel.add(layer, color, shape);
      }
    }
    return shape;
  }

  /**
   * this class manages a single cell within the Canvas, it contains information of all shapes
   * that cover this cell, and is responsible for adding/removing shapes as well as rendering the
   * cell at any given moment.
   */
  static class Pixel {
    private final TreeMap<Integer, Layer> layers;

    public Pixel() {
      this.layers = new TreeMap<>();
      layers.put(0, new Layer('0', null));
    }

    public void add(int layerNumber, char color, Shape shape) {
      layers.put(layerNumber, new Layer(color, shape));
    }

    public void remove(int layerNumber) {
      layers.remove(layerNumber);
    }

    public char show() {
      return layers.lastEntry().getValue().getColor();
    }
  }

  @Value
  static class Layer {
    char color;
    Shape shape;
  }

  @Value
  static class Shape {
    int row;
    int col;
    int width;
    int height;
    char color;
    int layer;
  }
}
