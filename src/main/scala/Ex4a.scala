package org.eamonnh.aoc23

import scala.io.Source

object Ex4a extends App {

  val fileName = "input/Ex4Input.txt"

  val input = Source.fromFile(fileName).getLines

  var sum = 0

  input.foreach(line => {
    val winningNumbers = line.split(": ").last.split(" \\| ").head.split(' ').filterNot(_.isBlank).toSet
    val haveNumbers = line.split(": ").last.split(" \\| ").last.split(' ').filterNot(_.isBlank).toSet
    var points = 0
    haveNumbers.foreach(h => {
      if(winningNumbers.contains(h)) if (points==0) points += 1 else  points *= 2
    })
    sum += points

  })
  println(sum)

}
