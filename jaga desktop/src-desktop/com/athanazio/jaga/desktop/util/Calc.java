/*
 * Created on 16/02/2004 t23:14:11
 * 
 */
package com.athanazio.jaga.desktop.util;

import java.awt.Point;


/**
 * represents an Calc
 * 
 * 
 * @author Hamilton Lima
 * @version 16/02/2004 23:14:11
 */
public class Calc {

   /**
    * return the distance between two points
    * poin A and B 
    * SQRT( (A.x-b.X)^2 + (A.y-b.y)^2 )
    *   
    * @param a
    * @param b
    * @return
    */
   public static int distance(Point a, Point b) {
      return distance(a.x, a.y, b.x, b.y);
   }

   /**
    * return the distance between two points
    * poin A and B 
    * SQRT( (A.x-b.X)^2 + (A.y-b.y)^2 )
    *   
    * @param a
    * @param b
    * @return
    */
   public static int distance(Movement a, Movement b) {
      return distance(a.x, a.y, b.x, b.y);
   }

   /**
    * return the distance between two points
    * poin A and B 
    * SQRT( (A.x-b.X)^2 + (A.y-b.y)^2 )
    *   
    * @param a
    * @param b
    * @return
    */
   public static int distance(int x1, int y1, int x2, int y2) {
      return (int) Math.sqrt((double) ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
   }

   /**
    * Calculate the path based, on the following information<p>
    * <ul>
    * <li>source X,Y</li>
    * <li>angle</li> 
    * <li>units</li> 
    * <li>speed</li>
    * </ul>
    * the <b>speed</b> is the number of points that are add 
    * the each step in the path<p>
    * 
    * @param sourceX
    * @param sourceY
    * @param angle
    * @param units
    * @param speed
    * @return
    */
   public static Movement[] calculatePath(int sourceX, int sourceY, int angle, int units, int speed) {

      if (angle >= 0 && angle <= 90) {
         return calculatePath(sourceX, sourceY, angle, units, speed, 1, -1);
      } else {
         if (angle > 90 && angle <= 180) {
            return calculatePath(sourceX, sourceY, angle, units, speed, 1, 1);
         } else {
            if (angle > 180 && angle <= 270) {
               return calculatePath(sourceX, sourceY, angle, units, speed, -1, 1);
            } else {
               if (angle > 270 && angle <= 360) {
                  return calculatePath(sourceX, sourceY, angle, units, speed, -1, -1);
               } else {
                  throw new RuntimeException("Invalid angle " + angle);
               }
            }
         }

      }
   }

   /**
    * depending on the quadrant (1,2,3,4) use an correct multiplier
    * on X and Y 
    *  
    * @param sourceX
    * @param sourceY
    * @param angle
    * @param units
    * @param speed
    * @param multiplier for X (+1/-1)
    * @param multiplier for Y (+1/-1)
    * @return
    */
   private static Movement[] calculatePath(
      int sourceX,
      int sourceY,
      int angle,
      int units,
      int speed,
      int multX,
      int multY) {

      // speed equals zero = no movements = stoped :)
      if (speed <= 0) {
         return new Movement[0];
      }

      int targetX = (int) Math.round(units * Math.sin(angle));
      int targetY = (int) Math.round(units * Math.cos(angle));

      targetX = targetX < 0 ? targetX * -1 : targetX;
      targetY = targetY < 0 ? targetY * -1 : targetY;

      int steps = units / speed;

      int increaseX = targetX / steps;
      int increaseY = targetY / steps;

      // to recover the rounding difference
      int adjustValueX = targetX - (increaseX * steps);
      int adjustValueY = targetY - (increaseY * steps);

      int lastX = sourceX;
      int lastY = sourceY;

      Movement[] result = new Movement[steps < 0 ? steps * -1 : steps];
      for (int i = 0; i < steps; i++) {
         lastX = lastX + (increaseX * multX);
         lastY = lastY + (increaseY * multY);

         if (adjustValueX-- > 0) {
            lastX = lastX + multX;
         }

         if (adjustValueY-- > 0) {
            lastY = lastY + multY;
         }

         result[i] = new Movement(lastX, lastY, angle);
      }
      return result;
   }

}
