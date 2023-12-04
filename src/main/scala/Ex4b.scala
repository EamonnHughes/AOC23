package org.eamonnh.aoc23

import scala.io.Source

object Ex4b extends App {

  val fileName = "input/Ex4Input.txt"

  val input = Source.fromFile(fileName).getLines.toList

  var allCardsEver: List[String] = List.empty
  var currentCards  = toCards(input)

  while (currentCards.nonEmpty) currentCards.foreach(card => {
    for(n <- 0 until card.points) if(input.length > card.i + n) currentCards = toCard(input(card.i + n)) :: currentCards
    currentCards = currentCards.filterNot(c => c eq this)
  })
  println(allCardsEver.length)


  def toCard(line: String): Card = {
    val id = line.split(":").head.split(" ").last.toInt
    val winningNumbers = line.split(": ").last.split(" \\| ").head.split(' ').filterNot(_.isBlank).toSet
    val haveNumbers = line.split(": ").last.split(" \\| ").last.split(' ').filterNot(_.isBlank).toSet

    Card(id, winningNumbers, haveNumbers)
  }
  def toCards(value: List[String]): List[Card] = {

    value.map(line => {
      toCard(line)
    })
  }

}


case class Card(i: Int, winners: Set[String], have: Set[String]) {
  def points: Int = winners.intersect(have).toList.length
}