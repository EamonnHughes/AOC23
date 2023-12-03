package org.eamonnh.aoc23

import scala.io.Source

object Ex2b extends App {
  var total = 0

  val fileName = "input/Ex2Input.txt"

  Source.fromFile(fileName).getLines.zipWithIndex.foreach {

    case (line, id) => {
      var condensedLine = line.split(": ")(1)
      condensedLine = condensedLine.replaceAll("red", "r")
      condensedLine = condensedLine.replaceAll("green", "g")
      condensedLine = condensedLine.replaceAll("blue", "b")
      condensedLine = condensedLine.replaceAll(",", "")
      condensedLine = condensedLine.replaceAll(" ", "")

      var minR = 0
      var minG = 0
      var minB = 0
      condensedLine.split(";").foreach(item => {
        val charified = item.toCharArray
        for(i <- 0 until charified.length) {
          if(i == 0 || charified(i - 1).isLetter ) {
            var n = 0
            var sum = ""
            while(charified(i + n).isDigit) {
              sum = sum+charified(i+n)
              n += 1
            }

            if(charified(i+n)=='r') minR = minR max sum.toInt
            else if(charified(i+n)=='g') minG =minG max sum.toInt
            else if(charified(i+n)=='b') minB =minB max sum.toInt

          }


        }
      })
      total += (minR*minG*minB)

    }
  }
  println(total)
}
