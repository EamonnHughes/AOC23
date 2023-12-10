package org.eamonnh.aoc23

import scala.collection.mutable
import scala.io.Source
import scala.language.postfixOps

object Ex4b extends App {

  val fileName = "input/Ex4Input.txt"

  val input = Source.fromFile(fileName).getLines.toList

  val counts = mutable.Map.empty[Int, Long]
  val initialCards = toCards(input)

  initialCards.foreach(card => {
    counts.update(card.i, 1)
  })

  initialCards.foreach(card => {
    for(i <- card.i + 1 to card.i + card.points) {
      counts.update(i, counts(i) + counts(card.i))
    }
  })

  println(counts.values.sum)

  def toCard(line: String): Card = {
    val s"Card $id: $wins | $haves" = line
    val winningNumbers = wins.split(" +").toSet
    val haveNumbers = haves.split(" +").toSet

    Card(id.trim.toInt, winningNumbers.filterNot(_.isBlank), haveNumbers.filterNot(_.isBlank))
  }
  def toCards(value: List[String]): Vector[Card] = {

    value.map(line => {
      toCard(line)
    }).toVector
  }


}


case class Card(i: Int, winners: Set[String], have: Set[String]) {
  val points: Int = winners.intersect(have).size
}