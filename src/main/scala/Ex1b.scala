package org.eamonnh.aoc23

import scala.io.Source

object Ex1b extends App {

  def stringToInt(str: String): List[Int] = {
    var ints: List[Int] = List.empty
    val charified = str.toCharArray
    charified.zipWithIndex.foreach {
      case (c, i) => {
        if (c.isDigit) {
          ints = (c.asDigit :: ints.reverse).reverse
        } else if(charified.length > i+2) {
          val first3 = s"${charified(i)}${charified(i+1)}${charified(i+2)}"
          if (first3 == "one") {
            ints = (1 :: ints.reverse).reverse
          } else if (first3 == "two") {
            ints = (2 :: ints.reverse).reverse
          } else if (first3 == "thr" && charified(i+3) == 'e'  && charified(i+4) == 'e') {
            ints = (3 :: ints.reverse).reverse
          } else if (first3 == "fou" && charified(i + 3) == 'r') {
            ints = (4 :: ints.reverse).reverse
          } else if (first3 == "fiv" && charified(i + 3) == 'e') {
            ints = (5 :: ints.reverse).reverse
          } else if (first3 == "six") {
            ints = (6 :: ints.reverse).reverse
          } else if (first3 == "sev" && charified(i + 3) == 'e' && charified(i + 4) == 'n') {
            ints = (7 :: ints.reverse).reverse
          } else if (first3 == "eig" && charified(i + 3) == 'h' && charified(i + 4) == 't') {
            ints = (8 :: ints.reverse).reverse
          } else if (first3 == "nin" && charified(i + 3) == 'e') {
            ints = (9 :: ints.reverse).reverse
          }
        }
      }

    }
  println(ints)
    ints

  }

  val fileName = "input/Ex1Input.txt"
  var lineNums: List[Int] = List.empty
  for (line <- Source.fromFile(fileName).getLines) {
    val num = s"${stringToInt(line).head}${ stringToInt(line).reverse.head}"
    lineNums = num.toInt :: lineNums
  }
  println(lineNums.sum)
}
