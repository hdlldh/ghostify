package com.goku.ghostify.nlp

import com.goku.ghostify.common.{IndexedToken, Sentence}

import java.text.Normalizer
import scala.collection.mutable

/**
 * @param caseSensitive
 *   whether or not content should be case sensitive or not
 * @param hasBeginEnd
 *   whether or not the input sentence has already been tokenized before like in BERT and
 *   DistilBERT
 */
class BasicTokenizer(caseSensitive: Boolean = false, hasBeginEnd: Boolean = true) {

  def isWhitespace(char: Char): Boolean = {
    char == ' ' || char == '\t' || char == '\n' || char == '\r' || Character.isWhitespace(char)
  }

  def isControl(char: Char): Boolean = {
    if (char == '\t' || char == '\n' || char == '\r')
      return false

    Character.isISOControl(char)
  }

  def isToFilter(char: Char): Boolean = {
    val cp = char.toInt
    cp == 0 || cp == 0xfffd || isControl(char)
  }

  def isPunctuation(char: Char): Boolean = {
    val cp = char.toInt
    if (
      (cp >= 33 && cp <= 47) || (cp >= 58 && cp <= 64) ||
      (cp >= 91 && cp <= 96) || (cp >= 123 && cp <= 126)
    )
      return true

    try {
      val charCategory: String = Character.getName(char.toInt)
      val charCategoryString = charCategory match {
        case x: String => x
        case _ => ""
      }
      charCategoryString.contains("PUNCTUATION")
    } catch {
      case _: Exception =>
        false
    }

  }

  def stripAccents(text: String): String = {
    Normalizer
      .normalize(text, Normalizer.Form.NFD)
      .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
  }

  def isChinese(char: Char): Boolean = {
    // This defines a "chinese character" as anything in the CJK Unicode block:
    //   https://en.wikipedia.org/wiki/CJK_Unified_Ideographs_(Unicode_block)
    //   https://github.com/google-research/bert/blob/master/tokenization.py

    val c = char.toInt

    (c >= 0x4e00 && c <= 0x9fff) ||
    (c >= 0x3400 && c <= 0x4dbf) ||
    (c >= 0x20000 && c <= 0x2a6df) ||
    (c >= 0x2a700 && c <= 0x2b73f) ||
    (c >= 0x2b740 && c <= 0x2b81f) ||
    (c >= 0x2b820 && c <= 0x2ceaf) ||
    (c >= 0xf900 && c <= 0xfaff) ||
    (c >= 0x2f800 && c <= 0x2fa1f)
  }

  def normalize(text: String): String = {
    val result = stripAccents(text.trim())
      .filter(c => !isToFilter(c))
      .mkString("")

    if (caseSensitive)
      result
    else
      result.toLowerCase
  }

  /**
   * @param sentence
   *   input Sentence which can be a full sentence or just a token in type of Sentence
   * @return
   */
  def tokenize(sentence: Sentence): Array[IndexedToken] = {

    val tokens = mutable.ArrayBuffer[IndexedToken]()
    val s = sentence.content

    def append(start: Int, end: Int): Unit = {
      assert(end > start)

      val text = s.substring(start, end)
      val normalized = normalize(text)

      if (normalized.nonEmpty) {
        val token =
          if (hasBeginEnd)
            IndexedToken(normalized, sentence.start, end - 1 + sentence.start)
          else
            IndexedToken(normalized, start + sentence.start, end - 1 + sentence.start)
        tokens.append(token)
      }
    }

    var i = 0
    while (i < s.length) {
      // 1. Skip whitespaces
      while (i < s.length && isWhitespace(s(i)) && !isPunctuation(s(i))) i = i + 1

      // 2. Find Next separator
      var end = i
      while (
        end < s.length && !isToFilter(s(end)) && !isPunctuation(s(end)) && !isChinese(s(end))
        && !isWhitespace(s(end))
      ) end += 1

      // 3. Detect what tokens to add
      if (end > i)
        append(i, end)

      if (end < s.length && (isPunctuation(s(end)) || isChinese(s(end))))
        append(end, end + 1)

      i = end + 1
    }

    tokens.toArray
  }
}
