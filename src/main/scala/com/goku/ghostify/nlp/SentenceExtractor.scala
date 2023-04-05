package com.goku.ghostify.nlp

import com.johnsnowlabs.nlp.annotators.common.Sentence

import scala.annotation.tailrec

object SentenceExtractor {

  private final val SentenceSplitter = "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?|!)\\s".r

  def split(rawText: String): Seq[Sentence] = {
    val rawSentences = SentenceSplitter.split(rawText).zipWithIndex
    splitHelper(rawText, rawSentences, 0, Seq.empty[Sentence])
  }

  @tailrec
  private def splitHelper(rawText: String, rawSentences: Seq[(String, Int)], lastCharPos: Int, sentences: Seq[Sentence]): Seq[Sentence] = {
    if (rawSentences.isEmpty) sentences
    else {
      val (rawSentence, index) = rawSentences.head
      val trimmed = rawSentence.trim
      val start = rawText.indexOf(trimmed, lastCharPos)
      val sentence = Sentence(trimmed, start, start + trimmed.length - 1, index)
      splitHelper(rawText, rawSentences.tail, sentence.end + 1, sentences :+ sentence)
    }
  }

}
