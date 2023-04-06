package com.goku.ghostify.util

import com.johnsnowlabs.nlp.annotators.common.Sentence

object SentenceExtractor {

  private final val SentenceSplitter = "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=[.?!])\\s+".r

  def split(rawText: String): Seq[Sentence] = {
    val matches = SentenceSplitter.findAllMatchIn(rawText).toSeq
    val starts = 0 +: matches.map(_.end)
    val ends = matches.map(_.start) :+ rawText.length
    starts.zip(ends).filter {case (s, e) => s < e}.zipWithIndex.map { case ((s, e), i) =>
      Sentence(rawText.slice(s, e), s, e - 1, i, None)
    }
  }

}
