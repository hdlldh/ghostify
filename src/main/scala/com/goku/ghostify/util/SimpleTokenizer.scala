package com.goku.ghostify.util

import com.johnsnowlabs.nlp.annotators.common.{IndexedToken, Sentence, TokenizedSentence}

import java.util.regex.Pattern
import scala.util.matching.Regex

class SimpleTokenizer(
  targetPattern: String = "\\S+",
  caseSensitive: Boolean = true,
  contextChars: Array[String] = Array(".", ",", ";", ":", "!", "?", "*", "-", "(", ")", "\"", "'")
) {

  private val ProtectChar = "ↈ"
  private val BreakChar = "ↇ"
  private lazy val BreakPattern: Regex = ("[^(?:" + targetPattern + ")" + ProtectChar + "]").r
  private lazy val SplitPattern: Regex = ("[^" + BreakChar + "]+").r

  lazy val rules = {
    val quotedContext = Pattern.quote(contextChars.mkString(""))
    val processedPrefix = s"\\A([$quotedContext]*)"
    val processedSuffix = s"([$quotedContext]*)\\z"
    val processedInfixes = Array(s"([^$quotedContext](?:.*[^$quotedContext])*)")
    processedPrefix +: processedInfixes :+ processedSuffix
  }

  def encode(
    sentences: Seq[Sentence]
  ): Seq[TokenizedSentence] = {
    sentences.map { text =>
      val content = if (caseSensitive) text.content else text.content.toLowerCase
      val protectedText = BreakPattern.replaceAllIn(content, BreakChar)
      val regRules = rules.map(_.r)
      val matchedTokens = SplitPattern.findAllMatchIn(protectedText).toSeq

      val tokens = matchedTokens
        .flatMap { candidate =>
          val matched =
            regRules.flatMap(r => r.findFirstMatchIn(candidate.matched)).filter(_.matched.nonEmpty)
          if (matched.nonEmpty) {
            matched.flatMap { m =>
              var curPos = m.start
              (1 to m.groupCount)
                .flatMap { i =>
                  val target = m.group(i)

                  def defaultCandidate = {
                    val it = IndexedToken(
                      target,
                      text.start + candidate.start + curPos,
                      text.start + candidate.start + curPos + target.length - 1
                    )
                    curPos += target.length
                    Seq(it)
                  }
                  defaultCandidate
                }
            }
          } else {
            Array(
              IndexedToken(
                candidate.matched,
                text.start + candidate.start,
                text.start + candidate.end - 1
              )
            )
          }
        }
        .filter(t => t.token.nonEmpty)
        .toArray
      TokenizedSentence(tokens, text.index)
    }

  }

}
