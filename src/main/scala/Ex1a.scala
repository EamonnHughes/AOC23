package org.eamonnh.aoc23

import scala.io.Source

object Ex1a extends App {

  val fileName = "input/Ex1aInput.txt"
  var ints: List[Int] = List.empty
for (line <- Source.fromFile(fileName).getLines) {
  val regex = "\\d+".r
  val numberized = regex.findAllIn(line).flatMap(_.map(_.toString.toInt)).toList
  val num = s"${numberized.head}${numberized.reverse.head}"
  ints = num.toInt :: ints
}
  println(ints.sum)
}
