package org.eamonnh.aoc23

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Ex6a extends  App {
  val fileName = "input/Ex6Input.txt"

  val input = Source.fromFile(fileName).getLines.toList

  val lines: ListBuffer[Line] = ListBuffer.empty

  val times = "\\d+".r.findAllIn(input.head).map(_.toLong).toList
  val dists = "\\d+".r.findAllIn(input.last).map(_.toLong).toList
  val timesDists = times.zip(dists)

  timesDists.foreach({
    case (time, dist) => lines.addOne(Line(time, dist, 0))
  })
  lines.foreach(line => {
    for(i <- 0L until line._1){
      if(i*(line._1-i) > line._2) line._3 += 1
    }
  })
  println(lines.map(_._3).product)

}

case class Line(_1: Long, _2: Long, var _3: Long)